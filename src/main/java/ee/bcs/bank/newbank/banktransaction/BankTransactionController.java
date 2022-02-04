package ee.bcs.bank.newbank.banktransaction;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/transaction")
public class BankTransactionController {

    @Resource
    private BankTransactionService bankTransactionService;



    @PostMapping("/send/money/by/sender/lastname")
    public void sendMoneyBySenderLastName(@RequestBody BankTransactionDto bankTransactionDto, @RequestParam String lastName) {
        bankTransactionService.sendMoneyBySenderLastName(bankTransactionDto, lastName);
    }



}
