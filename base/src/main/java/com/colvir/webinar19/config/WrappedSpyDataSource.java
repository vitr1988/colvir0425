package com.colvir.webinar19.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import net.sf.log4jdbc.sql.Spy;
import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class WrappedSpyDataSource extends HikariDataSource {

    @Delegate(types = {DataSource.class, Spy.class})
    private final DataSourceSpy dataSourceSpy;

    @Delegate(types = HikariDataSource.class, excludes = DataSource.class)
    private final HikariDataSource original;
}

