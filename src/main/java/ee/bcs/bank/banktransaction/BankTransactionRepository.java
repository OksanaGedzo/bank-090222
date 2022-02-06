package ee.bcs.bank.banktransaction;

import ee.bcs.bank.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer> {
    @Query("select b from BankTransaction b where b.bankAccount = :bankAccount")
    BankTransaction findByBankAccount(@Param("bankAccount") BankAccount bankAccount);


}