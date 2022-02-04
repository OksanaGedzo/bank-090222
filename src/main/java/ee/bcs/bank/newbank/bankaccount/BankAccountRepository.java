package ee.bcs.bank.newbank.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    @Query("select b from BankAccount b where upper(b.customer.lastName) = upper(?1)")
    BankAccount findBankAccountByCustomerLastName(String lastName);


}