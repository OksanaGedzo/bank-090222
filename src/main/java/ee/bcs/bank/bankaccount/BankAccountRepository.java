package ee.bcs.bank.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    boolean existsByAccountNumber(String accountNumber);

    @Query("select b from BankAccount b where b.accountNumber = :accountNumber")
    BankAccount findByAccountNumber(@Param("accountNumber") String accountNumber);





}