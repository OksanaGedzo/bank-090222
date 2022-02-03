package ee.bcs.bank.newbank.bankaccount;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Resource
    private BankAccountService bankAccountService;

    // Sisse tuleb BankAccountDto bankAccount objekt ja last name
    // leida by customer by last name (entity)
    // luua uus BankAccount  (entity objekt) -
    // set bankAccount objektil customer
    // ja salvestada see andmebaasi
    // kogu lahendus service klassis
    // Pathiks /add/new/by/lastname

    @PostMapping("/add/new/by/lastname")
    public void addNewAccountByLastName(@RequestBody BankAccountDto bankAccountDto, @RequestParam String lastName) {
        bankAccountService.addNewAccountByLastName(bankAccountDto, lastName);

    }



}
