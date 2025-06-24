#!/usr/bin/env bash

set -e
set -u

function admin_psql() {
    psql -v ON_ERROR_STOP=1 --username="$POSTGRES_USER" --host="$POSTGRES_HOST" -t $@
}

function user_psql() {
    POSTGRES_USER=$1
    export PGPASSWORD=$2
    shift
    shift
    psql -v ON_ERROR_STOP=1 --username="$POSTGRES_USER" --host="$POSTGRES_HOST" -t $@
    unset PGPASSWORD
}

function create_user_and_database() {
	local database=$1
	local password=$2

    export PGPASSWORD="$POSTGRES_PASSWORD"

	while ! pg_isready --timeout=5 --username="$POSTGRES_USER" --host="$POSTGRES_HOST" > /dev/null; do
        echo -e "\tWaiting for postgresql server will ready."
        sleep 1
	done

    echo
	echo -e "\tCreating user and database '$database'"

    if ! echo "SELECT 1 FROM pg_catalog.pg_roles WHERE rolname = '$database'" | admin_psql | grep -q 1; then
        if ! echo "CREATE USER $database WITH ENCRYPTED PASSWORD '$password'" | admin_psql > /dev/null; then
            echo -e "\t\tError creating user '$database'"
            exit 1
        fi
        echo -e "\t\tUser '$database' successfully created."
    else
        echo -e "\t\tUser '$database' already exist. Skipping."
        if ! bash -c "PGPASSWORD=$password psql --username=""$database"" --host=""$POSTGRES_HOST"" -c 'SELECT 1'" > /dev/null; then
            echo -e "\t\tUser '$database' has unknown password. Try to reset it"
            if ! echo "ALTER USER $database WITH ENCRYPTED PASSWORD '$password'" | admin_psql > /dev/null; then
                echo -e "\t\tError changing password for user '$database'"
                exit 1
            fi
            echo -e "\t\tUser's '$database' password successfully changed."
        fi
    fi

    if ! echo "SELECT 1 FROM pg_catalog.pg_database WHERE datname = '$database'" | admin_psql | grep -q 1; then
        if ! echo "CREATE DATABASE $database OWNER $database" | admin_psql > /dev/null; then
            echo -e "\t\tError creating database '$database'"
            exit 1
        fi
        echo -e "\t\tDatabase '$database' successfully created."
    else
        echo -e "\t\tDatabase '$database' already exist. Skipping."
    fi

    if ! echo "SELECT 1 FROM pg_catalog.pg_namespace WHERE nspname = '$database'" | user_psql $database $password | grep -q 1; then
        if ! echo "CREATE SCHEMA $database AUTHORIZATION $database" | user_psql $database $password > /dev/null; then
            echo -e "\t\tError creating schema '$database'"
            exit 1
        fi
        echo -e "\t\tSchema '$database' successfully created."
    else
        echo -e "\t\tSchema '$database' already exist. Skipping."
    fi

    if ! echo "SELECT 1 FROM pg_available_extensions WHERE name = 'uuid-ossp' and installed_version is not null" | admin_psql -d $database | grep -q 1; then
        if ! echo "CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";COMMIT;" | admin_psql -d $database > /dev/null; then
            echo -e "\t\tError installing uuid-ossp"
            exit 1
        fi
        echo -e "\t\tEXTENSION uuid-ossp successfully installed."
    else
        echo -e "\t\tEXTENSION uuid-ossp already exist. Skipping."
    fi

    echo -e "\tDatabase and user '$database' created."

    unset PGPASSWORD
}

if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
	echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"
	for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ',' ' '); do
		create_user_and_database $(echo $db | cut -d: -f1) $(echo $db | cut -d: -f2)
	done
    echo
	echo "Multiple databases created"
fi
