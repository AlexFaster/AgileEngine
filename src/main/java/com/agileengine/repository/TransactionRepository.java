package com.agileengine.repository;

import com.agileengine.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    Optional<Transaction> findByHash(final String hash);
}
