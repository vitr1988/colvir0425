package com.colvir.webinar10.repository;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

@Getter
public class JpaRepositoryWithEntityManagerImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaRepositoryWithEntityManager<T, ID> {

    private final EntityManager entityManager;

    public JpaRepositoryWithEntityManagerImpl(JpaEntityInformation<T, ?> entityInformation,
                                              EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
}
