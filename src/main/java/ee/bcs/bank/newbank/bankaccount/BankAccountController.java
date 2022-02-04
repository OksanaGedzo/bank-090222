package ee.bcs.bank.newbank.bankaccount;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Resource
    private BankAccountService bankAccountService;


    @PostMapping("/add/new/by/lastname")
    public void addNewAccountByLastName(@RequestBody BankAccountDto bankAccountDto, @RequestParam String lastName) {
        bankAccountService.addNewAccountByLastName(bankAccountDto, lastName);

    }



}
