package ee.bcs.bank.restbank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController
//@RequestMapping("/solution")
public class BankController {

    public static Bank bank = new Bank();

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    @Resource
    private BankService bankService;

    @Resource
    private BankStatementService bankStatementService;

    @GetMapping("/bank")
    public Bank controllerGetBank() {
        return bank;
    }

    @GetMapping("/example/account")
    public AccountDto controllerGetExampleAccount() {
        AccountDto account = accountService.createExampleAccount();
        return account;
    }

    @GetMapping("/example/transaction")
    public TransactionDto controllerGetExampleTransaction() {
        TransactionDto transaction = transactionService.createExampleTransaction();
        return transaction;
    }

    @PostMapping("/new/account")
    public RequestResult controllerAddAccountToBank(@RequestBody AccountDto accountDto) {
        RequestResult result = bankService.addAccountToBank(bank, accountDto);
        return result;
    }

    @PostMapping("/new/transaction")
    public RequestResult controllerAddNewTransaction(@RequestBody TransactionDto transactionDto) {
        RequestResult result = transactionService.addNewTransaction(bank, transactionDto);
        return result;
    }

    @PostMapping("/receive/transaction")
    public RequestResult controllerReceiveNewTransaction(@RequestBody TransactionDto transactionDto) {
        RequestResult result = transactionService.receiveNewTransaction(bank, transactionDto);
        return result;
    }

    @PutMapping("/update/owner")
    public RequestResult controllerUpdateOwnerDetails(@RequestBody AccountDto accountDto) {
        RequestResult result = accountService.updateOwnerDetails(bank.getAccounts(), accountDto);
        return result;
    }

    @DeleteMapping("/delete/account")
    public RequestResult controllerDeleteAccount(@RequestParam int accountId) {
        RequestResult result = accountService.deleteAccount(bank.getAccounts(), accountId);
        return result;
    }

    @PutMapping("/lock/status")
    public RequestResult controllerSwitchLockStatus(int accountId) {
        RequestResult result = accountService.switchLockStatus(bank.getAccounts(), accountId);
        return result;
    }

    @GetMapping("/bankstatement/by/lastname")
    public List<TransactionDto> controllerBankStatementByName(@RequestParam String lastName) {
        List<TransactionDto> result = bankStatementService.getStatementByLastName(bank, lastName);
        return result;
    }

    @PostMapping("/deposit")
    public RequestResult controllerMakeDeposit(@RequestBody TransactionDto transactionDto) {
        RequestResult result = bankService.makeDeposit(bank, transactionDto);
        return result;
    }

    // todo uued teenused

    // KÕIK TEENUSED BankService'i alla
    // uus endpoint /withdraw
    // uus endpoint /send/money
    // /new/account -  konto lisamisel lisatakse ka tühi transaction nagu töötab "transactionType": "n"


}
