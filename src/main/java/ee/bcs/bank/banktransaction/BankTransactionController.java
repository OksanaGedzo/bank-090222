package ee.bcs.bank.banktransaction;

import ee.bcs.bank.RequestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class BankTransactionController {

    @Resource
    private BankTransactionService bankTransactionService;

    @PostMapping("/deposit/money")
    public RequestResponse depositMoney(@RequestBody OwnerAccountTransactionRequest request) {
        // see alljärgnev meetod vajab lahendamist
        RequestResponse response = bankTransactionService.depositMoney(request);
        return response;
    }

    @PostMapping("/withdraw/money")
    public RequestResponse withdrawMoney(@RequestBody OwnerAccountTransactionRequest request) {
        // see alljärgnev meetod vajab lahendamist
        RequestResponse response = bankTransactionService.withdrawMoney(request);
        return response;
    }

    @PostMapping("/send/money")
    public RequestResponse sendMoney(@RequestBody TransactionRequest request) {
        // see alljärgnev meetod vajab lahendamist
        RequestResponse response = bankTransactionService.sendMoney(request);
        return response;
    }

    @PostMapping("/receive/money")
    public RequestResponse receiveMoney(@RequestBody TransactionRequest request) {
        // see alljärgnev meetod vajab lahendamist
        RequestResponse response = bankTransactionService.receiveMoney(request);
        return response;
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankTransactionResponse> findAllTransactions() {
        // VAATA SELLE NÄIDISE LAHENDUST ja mõtle, et kuidas saaks ära selle viimase controlleri lahenduse.
        List<BankTransactionResponse> response = bankTransactionService.findAllTransactions();
        return response;
    }

    @GetMapping("/all/by/id/code")
    public List<BankTransactionResponse> findAllTransactionsByIdCode(@RequestParam String idCode) {
        // see alljärgnev meetod vajab lahendamist
        List<BankTransactionResponse> response = bankTransactionService.findAllTransactionsByIdCode(idCode);
        return response;
    }

}
