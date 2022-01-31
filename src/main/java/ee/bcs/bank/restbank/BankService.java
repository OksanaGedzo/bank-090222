package ee.bcs.bank.restbank;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static ee.bcs.bank.restbank.TransactionService.*;


@Service
public class BankService {

    @Resource
    private AccountService accountService;


    public RequestResult addAccountToBank(Bank bank, AccountDto account) {
        int accountId = bank.getAccountIdCounter();
        account.setId(accountId);
        account.setBalance(0);
        account.setLocked(false);
        bank.addAccountToAccounts(account);
        bank.incrementAccountIdCounter();
        RequestResult result = new RequestResult();
        result.setAccountId(accountId);
        result.setMessage("Added new account");
        return result;
    }

    public RequestResult makeDeposit(Bank bank, TransactionDto transaction) {
        int accountId = transaction.getAccountId();
        Integer amount = transaction.getAmount();

        RequestResult result = new RequestResult();
        if (!accountService.accountIdExist(bank.getAccounts(), accountId)) {
            result.setAccountId(accountId);
            result.setError("Account with " + accountId + " does not exist!");
            return result;
        }

        AccountDto account = accountService.getAccountById(bank.getAccounts(), accountId);
        String accountNumber = account.getAccountNumber();
        Integer balance = account.getBalance();
        int newBalance = balance + amount;

        int transactionIdCounter = bank.getTransactionIdCounter();
        transaction.setId(transactionIdCounter);
        transaction.setAccountId(accountId);
        transaction.setSenderAccountNumber(ATM);
        transaction.setReceiverAccountNumber(accountNumber);
        transaction.setBalance(newBalance);
        transaction.setLocalDateTime(LocalDateTime.now());
        transaction.setTransactionType(DEPOSIT);

        bank.addTransactionToTransactions(transaction);
        bank.incrementTransactionId();

        account.setBalance(newBalance);

        result.setAccountId(accountId);
        result.setTransactionId(transactionIdCounter);
        result.setMessage("Successful deposit £€" + amount + " to account number " + accountNumber);
        return result;
    }
}
