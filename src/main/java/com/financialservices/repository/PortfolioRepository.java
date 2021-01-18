
package com.financialservices.repository;

import com.financialservices.models.Portfolio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    public List<Portfolio> findByUseraccountId(Long id);
 
}
