package ee.bcs.bank.bankaccount;

import ee.bcs.bank.RequestResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Resource
    private BankAccountService bankAccountService;

    // Lisab uue konto
    // Proovi näiteks lisada swaggeris kõigepealt uus klient Kadri, Kukk, 40000000005 (customeri teenus)
    // ja siis lisa talle pangakonto numbriga "EE777" selliselt, et tal on ka kohe €100 arvel (balance
    @PostMapping("/add/new")
    public RequestResponse addNewAccountByLastName(@RequestBody BankAccountRequest request) {
        RequestResponse response = bankAccountService.addNewAccount(request);
        return response;
    }

    // TODO: BOONUS ÜLESSANNE - niisama harjutamiseks
    // Leia ülesse kõik pangakontod (pangakontod, mitte kliendid) mille kasutaja eesnimi algab....
    // (näiteks kui sisendiks on "k", siis peaks tagastama, Keit'i ja Kadi kontod)
    @GetMapping("/first/name/starts/with")
    public List<BankAccountResponse> getAccountsByFirstNameStartsWith(@RequestParam String nameStartsWith) {
        // see alljärgnev meetod vajab lahendamist
        List<BankAccountResponse> response = bankAccountService.getAccountsByFirstNameStartsWith(nameStartsWith);
        return response;
    }




}
