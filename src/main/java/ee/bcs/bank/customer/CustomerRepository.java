package ee.bcs.bank.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select (count(c) > 0) from Customer c where c.personalIdentificationCode = ?1")
    boolean existsByIdCode(String idCode);

    @Query("select c from Customer c where c.personalIdentificationCode = ?1")
    Customer findByIdCode(String idCode);

    Customer findByFirstNameAndLastName(String firstName, String lastName);

}