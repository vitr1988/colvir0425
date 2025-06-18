package com.colvir.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Processing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String card;
    private Long accountId;

    public Processing(Long accountId, String card) {
        this.accountId = accountId;
        this.card = card;
    }

}
