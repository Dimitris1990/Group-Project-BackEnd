
package com.financialservices.repository;

import com.financialservices.models.Userlogs;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserlogsRepository extends JpaRepository<Userlogs, Long>{

    public List<Userlogs> findByUserId(Long id);
    
}
