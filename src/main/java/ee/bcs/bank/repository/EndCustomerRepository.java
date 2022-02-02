package ee.bcs.bank.repository;

import ee.bcs.bank.entity.EndCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndCustomerRepository extends JpaRepository<EndCustomer, Integer> {
}