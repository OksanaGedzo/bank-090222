package ee.bcs.bank.restbank;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BankStatementService {

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    public List<TransactionDto> getStatementByLastName(Bank bank, String lastName) {
        int accountId = accountService.getAccountIdByLastName(bank.getAccounts(), lastName);
        List<TransactionDto> allTransactions = bank.getTransactions();
        List<TransactionDto> resultTransactions = transactionService.getTransactionsByAccountId(accountId, allTransactions);
        return resultTransactions;
    }

}
