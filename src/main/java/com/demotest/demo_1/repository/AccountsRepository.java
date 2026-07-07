package com.demotest.demo_1.repository;

import com.demotest.demo_1.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
