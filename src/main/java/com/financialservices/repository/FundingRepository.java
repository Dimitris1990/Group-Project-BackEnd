
package com.financialservices.repository;

import com.financialservices.models.Funding;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long>{
     
    public List<Funding> findByUseraccountId(Long id);
}
