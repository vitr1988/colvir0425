package com.colvir.repository;

import com.colvir.domain.Processing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessingRepository extends JpaRepository<Processing, Long> {
    Processing findByCard(String card);

    @Query("""
        select p from Processing p
        where p.accountId = :accountId
        order by p.id desc
    """)
    List<Processing> findByAccountId(Long accountId);
}
