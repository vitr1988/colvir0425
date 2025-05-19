package com.colvir.webinar10.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface JpaRepositoryWithEntityManager<T, ID extends Serializable> extends JpaRepository<T, ID> {

    EntityManager getEntityManager();
}
