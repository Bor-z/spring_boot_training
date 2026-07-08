package com.demotest.demo_1.repository;

import com.demotest.demo_1.entity.Accounts;
import com.demotest.demo_1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

}
