package com.colvir.webinar19.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Entity
@Table(name = "cities")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class City {

    @Id
    private Long id;

    private String name;
}
