package ee.bcs.bank.banktransaction;

import ee.bcs.bank.RequestResponse;
import ee.bcs.bank.bankaccount.BankAccount;
import ee.bcs.bank.bankaccount.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.time.Instant;
import java.util.List;

@Service
public class BankTransactionService {

    public static final String SEND = "s";
    public static final String RECEIVE = "r";
    public static final String DEPOSIT = "d";
    public static final String WITHDRAW = "w";
    public static final String ATM = "ATM";


    @Resource
    private BankAccountRepository bankAccountRepository;

    @Resource
    private BankTransactionRepository bankTransactionRepository;

    @Resource
    private BankTransactionMapper bankTransactionMapper;

    public RequestResponse depositMoney(OwnerAccountTransactionRequest request) {

        RequestResponse response = new RequestResponse();
        String accountNumber = request.getAccountNumber();
        Integer amount = request.getAmount();

        if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
            BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
            // BankTransaction depositTransaction = bankTransactionRepository.findByBankAccount(bankAccount);
            BankTransaction depositTransaction = bankTransactionMapper.toDepositTransaction(request);
            // BankTransactionResponse response = bankTransactionMapper.toResponse(depositTransaction)

            depositTransaction.setSenderAccountNumber(ATM);
            depositTransaction.setReceiverAccountNumber(bankAccount.getAccountNumber());
            depositTransaction.setAmount(amount);

            Integer balance = bankAccount.getBalance();
            Integer newBalance = balance + amount;
            bankAccount.setBalance(newBalance);
            depositTransaction.setBalance(newBalance);
            bankTransactionRepository.save(depositTransaction);

            //bankTransactionMapper.updateBankTransaction(response, depositTransaction);
            response.setMessage("Deposit is sucsessfuly added to account " + accountNumber);
        } else {
            response.setError("Sorry, this account does not exist ");
        }
        return response;

        // DEPOSIIT MAKSE (ATM - pannakse pangaautomaadist raha juurde)
        // Mõtle andmetele, et mis tulevad requestiga siia meetodisse sisse. Võid ka swaggeri ja debbugeriga vaadata.

        // Pangaautomaat kasutab meie infosüsteemi.
        // Idee järgi ei saa meie teenusesse sisse tulla sellist accountNumber'it, mis meie pangas ei eksisteeri
        // aga kaitse peaks siiski olemas olema, et kaitsta meie pangasüsteemi ka küberkuritegevuse osas.

        // Laiem eesmärk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse üks uus DEPOSIT tehingu kanne. Tehingu kanne peab sisaldama ka kõnealuse konto 'bank_account_id'd
        //      Ühtlasi oleks vaja uuendada bank_account tabelis vastava konto kontojääki (balance)

        // alam-eesmärk nr 1 oleks kontrollida, et kas selline accountNumber üldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada kätte deposiidi saaja konto andmed (by accountNumber)
        //      a) pangakontot oleks vaja selle pärast, et meil oleks vaja teada selle konto hetke kontojääki (balance)
        //      b) pangakontot oleks vaja selle pärast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i küljes kui BankAccount tüüpi objekt
        //      mõtle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad kätte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesmärk nr 2 on tekitada üks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa äkki saad kasutada ära mingit BankTransactionMapper'is olevat mäpperit,
        //      millega saad kenasti luua uue mäpitud andmetega entity objekti.
        //      Mõtle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      Ära unusta teha ka vajalike arvutusi (newBalance), ning ära unusta neid ka õigesse kohta külge panna
        //      Mõtle milliseid muutujanimesi võiks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab õnnelikult tehtud [save()], siis võiks response MESSAGE külge panna sõnumi "Deposiit edukalt sooritatud!"


    }

    public RequestResponse withdrawMoney(OwnerAccountTransactionRequest request) {

        RequestResponse response = new RequestResponse();
        String accountNumber = request.getAccountNumber();
        Integer amount = request.getAmount();

        if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
            BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
            BankTransaction withdrowTransaction = bankTransactionMapper.toWithdrawTransaction(request);

            withdrowTransaction.setSenderAccountNumber(bankAccount.getAccountNumber());
            withdrowTransaction.setReceiverAccountNumber(WITHDRAW);
            withdrowTransaction.setAmount(amount);

            Integer balance = bankAccount.getBalance();
            Integer newBalance = balance - amount;
            bankAccount.setBalance(newBalance);
            withdrowTransaction.setBalance(newBalance);
            bankTransactionRepository.save(withdrowTransaction);

            response.setMessage("Please, take the money ");
        } else {
            response.setError("Sorry, this account does not exist ");
        }
        return response;


        // WITHDRAW MAKSE (ATM - võetakse pangaautomaadist raha välja)
        // Mõtle andmetele, et mis tulevad requestiga siia meetodisse sisse. Võid ka swaggeri ja debbugeriga vaadata.

        // Pangaautomaat kasutab meie infosüsteemi.
        // Idee järgi ei saa meie teenusesse sisse tulla sellist accountNumber'it, mis meie pangas ei eksisteeri
        // aga kaitse peaks siiski olemas olema, et kaitsta meie pangasüsteemi ka küberkuritegevuse osas.

        // Laiem eesmärk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse üks uus WITHDRAW tehingu kanne. Tehingu kanne peab sisaldama ka kõnealuse konto 'bank_account_id'd
        //      Ühtlasi oleks vaja uuendada bank_account tabelis vastava konto kontojääki (balance)

        // alam-eesmärk nr 1 oleks kontrollida, et kas selline accountNumber üldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada kätte deposiidi saaja konto andmed (by accountNumber)
        //      a) pangakontot oleks vaja selle pärast, et meil oleks vaja teada selle konto hetke kontojääki (balance)
        //      b) pangakontot oleks vaja selle pärast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i küljes kui BankAccount tüüpi objekt
        //      mõtle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad kätte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesmärk nr 2 on tekitada üks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa äkki saad kasutada ära mingit BankTransactionMapper'is olevat mäpperit,
        //      millega saad kenasti luua uue mäpitud andmetega entity objekti.
        //      Mõtle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      Ära unusta teha ka vajalike arvutusi (newBalance), ning ära unusta neid ka õigesse kohta külge panna
        //      Mõtle milliseid muutujanimesi võiks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab õnnelikult tehtud [save()], siis võiks response MESSAGE külge panna sõnumi "Deposiit edukalt sooritatud!"

    }

    public RequestResponse sendMoney(TransactionRequest request) {

        RequestResponse response = new RequestResponse();
        String senderAccountNumber = request.getSenderAccountNumber();
        String receiverAccountNumber = request.getReceiverAccountNumber();
        Integer amount = request.getAmount();

        if (bankAccountRepository.existsByAccountNumber(senderAccountNumber)) {
            BankAccount bankAccount = bankAccountRepository.findByAccountNumber(senderAccountNumber);
            BankTransaction sendMoneyTransaction = bankTransactionMapper.toBankTransaction(request);

            sendMoneyTransaction.setSenderAccountNumber(senderAccountNumber);
            sendMoneyTransaction.setReceiverAccountNumber(receiverAccountNumber);
            sendMoneyTransaction.setType(SEND);
            sendMoneyTransaction.setAmount(amount);
            sendMoneyTransaction.setBalance(bankAccount.getBalance());
            sendMoneyTransaction.setTransactionDateTime(Instant.now());

            Integer balance = bankAccount.getBalance();
            Integer newBalance = balance - amount;

            bankAccount.setBalance(newBalance);
            sendMoneyTransaction.setBalance(newBalance);
            bankTransactionRepository.save(sendMoneyTransaction);

            response.setMessage("Congratulations, the money was send ");
        } else {
            response.setError("Ülekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti! ");
        }
        return response;

        // VÄLJUV MAKSE
        // Mõtle andmetele, et mis tulevad requestiga siia meetodisse sisse. Võid ka swaggeri ja debbugeriga vaadata.

        // Laiem eesmärk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse üks uus SEND MONEY (väljuv) tehingu kanne. Tehingu kanne peab sisaldama ka kõnealuse konto 'bank_account_id'd
        //      Ühtlasi oleks vaja uuendada bank_account tabelis vastava konto kontojääki (balance)

        // alam-eesmärk nr 1 oleks kontrollida, et kas selline senderAccountNumber üldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada kätte saatja konto andmed (by senderAccountNumber)
        //      a) pangakontot oleks vaja selle pärast, et meil oleks vaja teada selle konto hetke kontojääki (balance)
        //      b) pangakontot oleks vaja selle pärast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i küljes kui BankAccount tüüpi objekt
        //      Mõtle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad kätte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesmärk nr 2 on tekitada üks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa äkki saad kasutada ära mingit BankTransactionMapper'is olevat mäpperit,
        //      millega saad kenasti luua uue mäpitud andmetega entity objekti.
        //      Mõtle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      Ära unusta teha ka vajalike arvutusi (newBalance), ning ära unusta neid ka õigesse kohta külge panna
        //      Mõtle milliseid muutujanimesi võiks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab õnnelikult tehtud [save()], siis võiks response MESSAGE külge panna sõnumi "Raha ülekanne edukalt sooritatud!"

        // juhul kui senderAccountNumber'it meie andmebaasis ei leidu, siis võiks tagastatavas responses ERROR'i sõnumile külge panna sõnumi:
        // "Ülekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti!");


        // LAEKUV MAKSE
        // Laiem eesmärk nr 2:
        //      Oleks vaja kontrollida, et kas ülekande saaja (receiver) on juhuslikult meie klient.
        //      Kui jah, siis sellisel juhul tuleks lisada 'bank_transaction' tabelisse veel üks uus tehingu kanne - RECEIVE MONEY (laekuv kanne).
        //          Tehingu kanne peab sisaldama ka kõnealuse saajakonto 'bank_account_id'd
        //      Ühtlasi oleks vaja uuendada bank_account tabelis vastava saajakonto kontojääki (balance)

        // alam-eesmärk nr 1 oleks kontrollida, et kas äkki selline receiverAccountNumber eksisteerib meie andmebaasis (kas ülekande saajakonto number on ka meie panga klient)
        // Kui selline konto number eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada kätte saatja konto andmed (by receiverAccountNumber)
        //      a) pangakontot oleks vaja selle pärast, et meil oleks vaja teada selle konto hetke kontojääki (balance)
        //      b) pangakontot oleks vaja selle pärast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i küljes kui BankAccount tüüpi objekt
        //      Mõtle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad kätte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesmärk nr 2 on tekitada üks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa äkki saad kasutada ära mingit BankTransactionMapper'is olevat mäpperit,
        //      millega saad kenasti luua uue mäpitud andmetega entity objekti.
        //      Mõtle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      Mõtle milliseid muutujanimesi võiks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // juhul kui receiverAccountNumber'it meie andmebaasis ei leidu, siis receive kannet pole lihtsalt vaja teha

    }

    public RequestResponse receiveMoney(TransactionRequest request) {

        RequestResponse response = new RequestResponse();
        String receiverAccountNumber = request.getReceiverAccountNumber();
        String senderAccountNumber = request.getSenderAccountNumber();
        Integer amount = request.getAmount();

        if (bankAccountRepository.existsByAccountNumber(receiverAccountNumber)) {
            BankAccount bankAccount = bankAccountRepository.findByAccountNumber(receiverAccountNumber);
            BankTransaction receiveMoneyTransaction = bankTransactionMapper.toBankTransaction(request);
            receiveMoneyTransaction.setReceiverAccountNumber(receiverAccountNumber);
            receiveMoneyTransaction.setSenderAccountNumber(senderAccountNumber);

            receiveMoneyTransaction.setType(RECEIVE);
            receiveMoneyTransaction.setAmount(amount);
            receiveMoneyTransaction.setBalance(bankAccount.getBalance());
            receiveMoneyTransaction.setTransactionDateTime(Instant.now());

            Integer balance = bankAccount.getBalance();
            Integer newBalance = balance + amount;

            bankAccount.setBalance(newBalance);
            receiveMoneyTransaction.setBalance(newBalance);
            bankTransactionRepository.save(receiveMoneyTransaction);

            response.setMessage("Congratulations, the money was received ");
        } else {
            response.setError("Ülekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti! ");
        }
        return response;

        // Mõtle andmetele, et mis tulevad requestiga siia meetodisse sisse. Võid ka swaggeri ja debbugeriga vaadata.

        // LAEKUV MAKSE
        // suht sarnane asi nagu sendMoney, ainult, et nüüd on sisse laekuv makse

        // Kui tehing saab õnnelikult tehtud [save()], siis võiks response MESSAGE külge panna sõnumi "Raha ülekanne edukalt sooritatud!"

        // juhul kui senderAccountNumber'it meie andmebaasis ei leidu, siis võiks tagastatavas responses ERROR'i sõnumile külge panna sõnumi:
        // "Ülekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti!");

    }

    public List<BankTransactionResponse> findAllTransactions() {
        List<BankTransaction> allTransactions = bankTransactionRepository.findAll();
        List<BankTransactionResponse> responses = bankTransactionMapper.toResponseList(allTransactions);
        return responses;
    }

    public List<BankTransactionResponse> findAllTransactionsByIdCode(String idCode) {
        // Vaata eelmist lahendust ja mõtle :)
        // Mõtle, et millise repository abil sa saaksid sellele bank_transaction tabeli andmetele ligi
        // Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad kätte need andmed, mis sind huvitavad.

        return null;
    }
}
