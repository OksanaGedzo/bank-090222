package ee.bcs.bank.shop.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EndCustomerRepository extends JpaRepository<EndCustomer, Integer> {

    @Query("select e from EndCustomer e where upper(e.address) = upper(:address) order by e.address")
    List<EndCustomer> findByAddress(@Param("address") String address);


    @Query("select e from EndCustomer e where e.id = :integer")
    EndCustomer pleaseFindById(@Param("integer") Integer integer);
}