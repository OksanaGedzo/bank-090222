package ee.bcs.bank.restbank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/solution")
public class BankController {

    public static Bank bank = new Bank();

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    @Resource
    private BankService bankService;

    //  Et saada bank objektist JSON näidis,
    //  siis loome uue controlleri endpoint'i       /bank
    @GetMapping("/bank")
    public Bank getBank() {
        return bank;
    }


    //  Et saada üks accounts JSON'i näidis,
    //  siis loome uue controlleri endpoint'i:      /example/account
    @GetMapping("/example/account")
    public AccountDto getExampleAccount() {
        return accountService.createExampleAccount();
    }


    //  Et saada üks transaction JSON'i näidis,
    //  siis loome uue controlleri endpoint'i:      /example/transaction
    //  loo transactionService alla uus teenus:     createExampleTransaction()
    @GetMapping("/example/transaction")
    public TransactionDto getExampleTransaction() {
        return transactionService.createExampleTransaction();
    }


    //  Et lisada uus account,
    //  siis loome uue controlleri endpoint'i:      /new/account
    //  võtame RequestBodyst sisse objekti:         accountDto
    //  loome bankService alla uue teenuse:         addAccountToBank()
    //  teenus võiks tagastada RequestResult objekti koos koos loodava konto id ja transaktsiooni id'ga

    @PostMapping("/new/account")
    public RequestResult addAccountToBank(@RequestBody AccountDto accountDto) {
        return bankService.addAccountToBank(bank, accountDto);
    }

    @PostMapping("/new/transaction")
    public RequestResult addNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.addNewTransaction(bank, transactionDto);
    }



    //  loo transactionService alla uus teenus                                      createTransactionForNewAccount()
    //  loo bankService alla uus teenus                                             addTransaction()


}
