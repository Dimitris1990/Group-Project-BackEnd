
package com.financialservices.repository;

import com.financialservices.models.Useraccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseraccountRepository extends JpaRepository<Useraccount, Long>{
    
}
