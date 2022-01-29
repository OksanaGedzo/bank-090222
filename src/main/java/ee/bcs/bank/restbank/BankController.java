package ee.bcs.bank.restbank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// @RestController annab Springile märku, et siin klassis on mingid endpoint'id (controllerid)
@RestController
//  @RequestMapping'uga saab defineerida mingi pathi (raja) osa,
//  mis on iga SIIN KLASSIS oleva endpointi (controlleri) raja osa
//  ehk siis http://localhost:8080/solution
@RequestMapping("/solution")
public class BankController {


    // Kui meie veebirakendus tõmmatakse käima, siis taustal toimetab servlet dispatcher,
    // kes teab kõiki meie controllerite endpoinide definitsioone
    // Kui see teema on veel endiselt segane, siis palun vaata uuesti "Spring HelloWorld":
    // https://youtu.be/pc9irBCk6rg


    // siin on meil defineeritud static Bank tüüpi objekt
    // static tähendab seda, et see objekt luuakse ainult ühe korra rakenduse käivitamisel
    // Selle objekti eluiga kestab seni kaua kuni meie rakendus käib.
    // Kui rakendus jääb seisma või meie programm peatub, siis seda objekti meil enam pole.
    // Oma ülesandes kasutame me seda objekti nagu andmebaasi
    public static Bank bank = new Bank();


    // @Resource abiga saab Spring teha meile ligipääsu:
    // @Service, @Component, @Mapper, @Repository, jne annotatsiooniga klassidele
    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    @Resource
    private BankService bankService;


    //  ÜLESANNE
    //  Et saada bank objektist JSON näidis,
    //  siis loome uue controlleri endpoint'i       /bank


    // Kui meie veebiserverisse tuleb sisse http päring 'GET' 'http://localhost:8080/solution/bank'
    // siis käivitatakse selle mäppingu all olev meetod controllerGetBank()
    // Kui @GetMapping teema on veel endiselt segane, siis palun vaata uuesti "Spring HelloWorld":
    // https://youtu.be/pc9irBCk6rg
    @GetMapping("/bank")
    public Bank controllerGetBank() {
        // Kuna controllerGetBank() meetod, on defineeritud nii, et see tagastab Bank tüüpi objekti,
        // siis return'is peab olema Bank tüüpi objekt
        // meil on siin samas klassis defineeritud bank objekt (public static Bank bank = new Bank())
        // selle sama bank objekti me tagastamegi
        // Spring teeb tagastades sellest Java objektist JSON objekti.
        // Kui see JSON teema on veel endiselt segane, siis palun vaata uuesti:
        // https://youtu.be/dyZUWR3Cchw
        // http päringule tagastatakse 'bank' objekt JSON'i kujul
        // Swaggeris on iga kontrolleri all ka response JSON näidis
        return bank;
    }


    //  ÜLESANNE
    //  Et saada üks accounts JSON'i näidis,
    //  siis loome uue controlleri endpoint'i:      /example/account


    // Kui meie veebiserverisse tuleb sisse http päring 'GET' 'http://localhost:8080/solution/example/account'
    // siis käivitatakse selle mäppingu all olev meetod controllerGetExampleAccount()
    // Kuna controllerGetExampleAccount() meetod, on defineeritud nii, et see tagastab AccountDto tüüpi objekti,
    // siis return'is peab olema AccountDto tüüpi objekt
    // Kui @GetMapping teema on veel endiselt segane, siis palun vaata uuesti "Spring HelloWorld":
    // https://youtu.be/pc9irBCk6rg
    @GetMapping("/example/account")
    public AccountDto controllerGetExampleAccount() {

        // Kutsume välja meie poolt defineeritud meetodi nimega createExampleAccount()
        // See meetod on meil ära defineeritud AccountService klassis
        // mingeid parameetreid (andmeid) see createExampleAccount() meetod sisse ei võta.
        // me ei anna meetodi välja kutsumisel talle midagi kaasa.
        // createExampleAccount() meetod on defineeritud nii, et see tagastab AccountDto tüüpi objekti
        // siin all me loome uue AccountDto tüüpi objekti 'account' mille sisse väärtustatakse
        // selle createExampleAccount() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        AccountDto account = accountService.createExampleAccount();

        // http päringule tagastatakse 'account' objekt JSON'i kujul
        return account;
    }


    //  ÜLESANNE
    //  Et saada üks transaction JSON'i näidis,
    //  siis loome uue controlleri endpoint'i:      /example/transaction
    //  loo transactionService alla uus teenus:     createExampleTransaction()

    // Kui meie veebiserverisse tuleb sisse http päring 'GET' 'http://localhost:8080/solution/example/transaction'
    // siis käivitatakse selle mäppingu all olev meetod controllerGetExampleTransaction()
    // Kuna controllerGetExampleTransaction() meetod, on defineeritud nii, et see tagastab TransactionDto tüüpi objekti,
    // siis return'is peab olema TransactionDto tüüpi objekt
    // Kui @GetMapping teema on veel endiselt segane, siis palun vaata uuesti "Spring HelloWorld":
    // https://youtu.be/pc9irBCk6rg
    @GetMapping("/example/transaction")
    public TransactionDto controllerGetExampleTransaction() {

        // Kutsume välja meie poolt defineeritud meetodi nimega createExampleTransaction()
        // See meetod on meil ära defineeritud TransactionService klassis
        // mingeid parameetreid (andmeid) see createExampleTransaction() meetod sisse ei võta.
        // me ei anna meetodi välja kutsumisel talle midagi kaasa.
        // createExampleTransaction() meetod on defineeritud nii, et see tagastab TransactionDto tüüpi objekti
        // siin all me loome uue TransactionDto tüüpi objekti 'transaction' mille sisse väärtustatakse
        // selle createExampleTransaction() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        TransactionDto transaction = transactionService.createExampleTransaction();

        // http päringule tagastatakse 'transaction' objekt JSON'i kujul
        return transaction;
    }


    //  ÜLESANNE
    //  Et lisada uus account,
    //  siis loome uue controlleri endpoint'i:      /new/account
    //  võtame RequestBodyst sisse AccountDto tüüpi objekti:         accountDto
    //  loome bankService alla uue teenuse:         controllerAddAccountToBank()
    //  teenus võiks tagastada RequestResult objekti koos koos loodava konto id ja transaktsiooni id'ga


    // Kui meie veebiserverisse tuleb sisse http päring 'POST' 'http://localhost:8080/solution/new/account'
    // siis käivitatakse selle mäppingu all olev meetod controllerAddAccountToBank()
    // Kuna controllerAddAccountToBank meetod, on defineeritud nii, et see tagastab RequestResult tüüpi objekti,
    // siis return'is peab olema RequestResult tüüpi objekt
    // Kuna meetodi parameetris on ära defineeritud @RequestBody AccountDto tüüpi objekt,
    // siis Spring annab endast parima, et mäppida ära sisse tulev JSON sõnum selleks Java AccountDto tüüpi objektiks
    // Swaggeri'is on olemas ka request body näidis sellisest JSON'ist, mille Spring peaks suutma ära mäppida:
    //    {
    //        "id": 0,
    //        "accountNumber": "string",
    //        "firstName": "string",
    //        "lastName": "string",
    //        "balance": 0,
    //        "locked": true
    //    }
    // Kui @RequestBody teema on veel endiselt segane, siis palun vaata uuesti "Spring @RequestBody":
    // https://youtu.be/mIYDl4pf1P0
    @PostMapping("/new/account")
    public RequestResult controllerAddAccountToBank(@RequestBody AccountDto accountDto) {
        // Võtame addAccountToBank() signatuuri parameetris sisse AccountDto tüüpi objekti
        // Siin signatuuris antakse sellele objektidele nimeks 'accountDto'

        // Kutsume välja meie poolt defineeritud meetodi nimega addAccountToBank()
        // See meetod on meil ära defineeritud BankService klassis
        // addAccountToBank() on meil selliselt defineeritud, et see võtab sisse parameetritena:
        // Bank ja AccountDto tüüpi objektid
        // See on selleks vajalik, et me saaksime anda meetodisse kaasa anda objektid, kus sees on mingid andmed,
        // millega me soovime kuidagi toimetada.
        // addAccountToBank() meetod on defineeritud nii, et see tagastab RequestResult tüüpi objekti
        // siin all me loome uue RequestResult tüüpi objekti 'result' mille sisse väärtustatakse
        // selle addAccountToBank() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        RequestResult result = bankService.addAccountToBank(bank, accountDto);

        // http päringule tagastatakse 'result' objekt JSON'i kujul
        return result;
    }


    // Kui meie veebiserverisse tuleb sisse http päring 'POST' 'http://localhost:8080/solution/new/transaction'
    // siis käivitatakse selle mäppingu all olev meetod controllerAddNewTransaction()
    // Kuna controllerAddNewTransaction meetod, on defineeritud nii, et see tagastab RequestResult tüüpi objekti,
    // siis return'is peab olema RequestResult tüüpi objekt
    // Kuna meetodi parameetris on ära defineeritud @RequestBody TransactionDto tüüpi objekt,
    // siis Spring annab endast parima, et mäppida ära sisse tulev JSON sõnum selleks Java TransactionDto tüüpi objektiks
    // Kindlasti peab meetodi signatuuris olema ka see @RequestBody annotatsioon, sest muidu Spring ei tea,
    // et siia enpoint'ile saab saata sõnumit koos request body'iga (json)
    // Kui @RequestBody teema on veel endiselt segane, siis palun vaata uuesti "Spring @RequestBody":
    // https://youtu.be/KtZfO5z_JzQ
    // Swaggeri'is on olemas ka request body näidis sellisest JSON'ist, mille Spring peaks suutma ära mäppida:
    //    {
    //        "id": 0,
    //        "accountId": 0,
    //        "senderAccountNumber": "string",
    //        "receiverAccountNumber": "string",
    //        "amount": 0,
    //        "balance": 0,
    //        "localDateTime": "2022-01-29T07:52:39.645Z",
    //        "transactionType": "string"
    //    }
    @PostMapping("/new/transaction")
    public RequestResult controllerAddNewTransaction(@RequestBody TransactionDto transactionDto) {
        // Võtame controllerAddNewTransaction() signatuuri parameetris sisse TransactionDto tüüpi objekti
        // Siin signatuuris antakse sellele objektidele nimeks 'transactionDto'

        // Kutsume välja meie poolt defineeritud meetodi nimega addNewTransaction()
        // See meetod on meil ära defineeritud TransactionService klassis
        // addNewTransaction() on meil selliselt defineeritud, et see võtab sisse parameetritena:
        // Bank ja AccountDto tüüpi objektid
        // See on selleks vajalik, et me saaksime anda meetodisse kaasa anda objektid, kus sees on mingid andmed,
        // millega me soovime kuidagi toimetada.
        // addNewTransaction() meetod on defineeritud nii, et see tagastab RequestResult tüüpi objekti
        // siin all me loome uue RequestResult tüüpi objekti 'result' mille sisse väärtustatakse
        // selle addNewTransaction() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        RequestResult result = transactionService.addNewTransaction(bank, transactionDto);

        // http päringule tagastatakse 'result' objekt JSON'i kujul
        return result;
    }

    // Kui meie veebiserverisse tuleb sisse http päring 'POST' 'http://localhost:8080/solution/receive/transaction'
    // siis käivitatakse selle mäppingu all olev meetod controllerReceiveNewTransaction()
    // Kuna controllerReceiveNewTransaction meetod, on defineeritud nii, et see tagastab RequestResult tüüpi objekti,
    // siis return'is peab olema RequestResult tüüpi objekt
    // Kuna meetodi parameetris on ära defineeritud @RequestBody TransactionDto tüüpi objekt,
    // siis Spring annab endast parima, et mäppida ära sisse tulev JSON sõnum selleks Java TransactionDto tüüpi objektiks
    // Kindlasti peab meetodi signatuuris olema ka see @RequestBody annotatsioon, sest muidu Spring ei tea,
    // et siia enpoint'ile saab saata sõnumit koos request body'iga (json)
    // Kui @RequestBody teema on veel endiselt segane, siis palun vaata uuesti "Spring @RequestBody":
    // https://youtu.be/KtZfO5z_JzQ
    // Swaggeri'is on olemas ka request body näidis sellisest JSON'ist, mille Spring peaks suutma ära mäppida:
    //    {
    //        "id": 0,
    //        "accountId": 0,
    //        "senderAccountNumber": "string",
    //        "receiverAccountNumber": "string",
    //        "amount": 0,
    //        "balance": 0,
    //        "localDateTime": "2022-01-29T07:52:39.645Z",
    //        "transactionType": "string"
    //    }
    @PostMapping("/receive/transaction")
    public RequestResult controllerReceiveNewTransaction(@RequestBody TransactionDto transactionDto) {
        // Võtame controllerReceiveNewTransaction() signatuuri parameetris sisse TransactionDto tüüpi objekti
        // Siin signatuuris antakse sellele objektidele nimeks 'transactionDto'

        // Kutsume välja meie poolt defineeritud meetodi nimega receiveNewTransaction()
        // See meetod on meil ära defineeritud TransactionService klassis
        // receiveNewTransaction() on meil selliselt defineeritud, et see võtab sisse parameetritena:
        // Bank ja AccountDto tüüpi objektid
        // See on selleks vajalik, et me saaksime anda meetodisse kaasa anda objektid, kus sees on mingid andmed,
        // millega me soovime kuidagi toimetada.
        // receiveNewTransaction() meetod on defineeritud nii, et see tagastab RequestResult tüüpi objekti
        // siin all me loome uue RequestResult tüüpi objekti 'result' mille sisse väärtustatakse
        // selle receiveNewTransaction() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        RequestResult result = transactionService.receiveNewTransaction(bank, transactionDto);

        // http päringule tagastatakse 'result' objekt JSON'i kujul
        return result;
    }

    // Kui meie veebiserverisse tuleb sisse http päring 'PUT' 'http://localhost:8080/solution/update/owner'
    // siis käivitatakse selle mäppingu all olev meetod controllerReceiveNewTransaction()
    // Kuna controllerUpdateOwnerDetails meetod, on defineeritud nii, et see tagastab RequestResult tüüpi objekti,
    // siis return'is peab olema RequestResult tüüpi objekt
    // Kuna meetodi parameetris on ära defineeritud @RequestBody AccountDto tüüpi objekt,
    // siis Spring annab endast parima, et mäppida ära sisse tulev JSON sõnum selleks Java AccountDto tüüpi objektiks
    // Kindlasti peab meetodi signatuuris olema ka see @RequestBody annotatsioon, sest muidu Spring ei tea,
    // et siia enpoint'ile saab saata sõnumit koos request body'iga (json)
    // Kui @RequestBody teema on veel endiselt segane, siis palun vaata uuesti "Spring @RequestBody":
    // https://youtu.be/KtZfO5z_JzQ
    // Swaggeri'is on olemas ka request body näidis sellisest JSON'ist, mille Spring peaks suutma ära mäppida:
    //    {
    //        "id": 0,
    //        "accountNumber": "string",
    //        "firstName": "string",
    //        "lastName": "string",
    //        "balance": 0,
    //        "locked": true
    //    }
    @PutMapping("/update/owner")
    public RequestResult controllerUpdateOwnerDetails(@RequestBody AccountDto accountDto) {
        // Võtame controllerUpdateOwnerDetails() signatuuri parameetris sisse AccountDto tüüpi objekti
        // Siin signatuuris antakse sellele objektidele nimeks 'accountDto'

        // Kutsume välja meie poolt defineeritud meetodi nimega updateOwnerDetails()
        // See meetod on meil ära defineeritud TransactionService klassis
        // updateOwnerDetails() on meil selliselt defineeritud, et see võtab sisse parameetritena:
        // List<AccountDto> ja AccountDto tüüpi objektid
        // See on selleks vajalik, et me saaksime anda meetodisse kaasa anda objektid, kus sees on mingid andmed,
        // millega me soovime kuidagi toimetada.
        // updateOwnerDetails() meetod on defineeritud nii, et see tagastab RequestResult tüüpi objekti
        // siin all me loome uue RequestResult tüüpi objekti 'result' mille sisse väärtustatakse
        // selle updateOwnerDetails() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        RequestResult result = accountService.updateOwnerDetails(bank.getAccounts(), accountDto);

        // http päringule tagastatakse 'result' objekt JSON'i kujul
        return result;
    }

    // Kui meie veebiserverisse tuleb sisse http päring 'DELETE' 'http://localhost:8080/solution/delete/account?accountId=1'
    // sellisel juhul oleks RequestParam accountId sisendiks '1'
    // siis käivitatakse selle mäppingu all olev meetod controllerDeleteAccount()
    // Kuna controllerDeleteAccount meetod, on defineeritud nii, et see tagastab RequestResult tüüpi objekti,
    // siis return'is peab olema RequestResult tüüpi objekt
    // Kuna meetodi parameetris on ära defineeritud @RequestParam int tüüpi objekt,
    // siis Spring eeldab, et http sõnumile antakse kaasa request parameetet 'accountId'
    // Kindlasti peab meetodi signatuuris olema ka see @RequestParam annotatsioon, sest muidu Spring ei tea,
    // et siia enpoint'ile peaks ka sisse tulema selline parameeter
    // Kui @RequestParam teema on veel endiselt segane, siis palun vaata uuesti "Spring @RequestParam":
    // https://youtu.be/9ovmRakMRBY
    @DeleteMapping("/delete/account")
    public RequestResult controllerDeleteAccount(@RequestParam int accountId) {

        // Võtame controllerDeleteAccount() signatuuri parameetris sisse int tüüpi väärtuse
        // Siin signatuuris antakse sellele objektidele nimeks 'accountId'

        // Kutsume välja meie poolt defineeritud meetodi nimega deleteAccount()
        // See meetod on meil ära defineeritud AccountService klassis
        // deleteAccount() on meil selliselt defineeritud, et see võtab sisse parameetritena:
        // List<AccountDto> ja int tüüpi objektid
        // See on selleks vajalik, et me saaksime anda meetodisse kaasa anda objektid, kus sees on mingid andmed,
        // millega me soovime kuidagi toimetada.
        // deleteAccount() meetod on defineeritud nii, et see tagastab RequestResult tüüpi objekti
        // siin all me loome uue RequestResult tüüpi objekti 'result' mille sisse väärtustatakse
        // selle deleteAccount() meetodi poolt tagastatav tulemus.
        // Kui meetodite teema on veel endiselt segane, siis palun vaata uuesti "Meetodite loomine" ja "Public ja Private meetodid":
        // https://youtu.be/KtZfO5z_JzQ
        // https://youtu.be/vJn0BuWFrBE
        // vaata ka kommentaare selle meetodi sees
        RequestResult result = accountService.deleteAccount(bank.getAccounts(), accountId);

        // http päringule tagastatakse 'result' objekt JSON'i kujul
        return result;
    }

    // todo: endpoint millega saab lukku switchida (lukustada/avada). Kontrollige ka ID olemasolu
    //  lock/status


//    todo: Loo endpoint /bankstatement/by/lastname
//     teenus (konto väljavõte) peab tagastama Listi TransactionDto'st, mis kuulub omanikule (by last name)
//     teete uue tühja Listi TransactionDto'st ja sinna lisate juurde
//     add()'iga vaid need TransactionDto'd, mis kuuluvad omanikule.
//     Kes tahab võib teha uue teenuse BankStatementService
//     (võite ka teha lihtsatl selle tyeenuse transactionServive alla)


}
