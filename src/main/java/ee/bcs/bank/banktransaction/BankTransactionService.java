package ee.bcs.bank.banktransaction;

import ee.bcs.bank.RequestResponse;
import ee.bcs.bank.bankaccount.BankAccount;
import ee.bcs.bank.bankaccount.BankAccountRepository;
import net.bytebuddy.build.Plugin;
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

            //depositTransaction.setSenderAccountNumber(ATM);
            //depositTransaction.setReceiverAccountNumber(bankAccount.getAccountNumber());
            //depositTransaction.setAmount(amount);

            depositTransaction.setBankAccount(bankAccount);
            depositTransaction.setType(DEPOSIT);
            depositTransaction.setTransactionDateTime(Instant.now());

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
        // M??tle andmetele, et mis tulevad requestiga siia meetodisse sisse. V??id ka swaggeri ja debbugeriga vaadata.

        // Pangaautomaat kasutab meie infos??steemi.
        // Idee j??rgi ei saa meie teenusesse sisse tulla sellist accountNumber'it, mis meie pangas ei eksisteeri
        // aga kaitse peaks siiski olemas olema, et kaitsta meie pangas??steemi ka k??berkuritegevuse osas.

        // Laiem eesm??rk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse ??ks uus DEPOSIT tehingu kanne. Tehingu kanne peab sisaldama ka k??nealuse konto 'bank_account_id'd
        //      ??htlasi oleks vaja uuendada bank_account tabelis vastava konto kontoj????ki (balance)

        // alam-eesm??rk nr 1 oleks kontrollida, et kas selline accountNumber ??ldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada k??tte deposiidi saaja konto andmed (by accountNumber)
        //      a) pangakontot oleks vaja selle p??rast, et meil oleks vaja teada selle konto hetke kontoj????ki (balance)
        //      b) pangakontot oleks vaja selle p??rast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i k??ljes kui BankAccount t????pi objekt
        //      m??tle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad k??tte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesm??rk nr 2 on tekitada ??ks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa ??kki saad kasutada ??ra mingit BankTransactionMapper'is olevat m??pperit,
        //      millega saad kenasti luua uue m??pitud andmetega entity objekti.
        //      M??tle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      ??ra unusta teha ka vajalike arvutusi (newBalance), ning ??ra unusta neid ka ??igesse kohta k??lge panna
        //      M??tle milliseid muutujanimesi v??iks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab ??nnelikult tehtud [save()], siis v??iks response MESSAGE k??lge panna s??numi "Deposiit edukalt sooritatud!"


    }

    public RequestResponse withdrawMoney(OwnerAccountTransactionRequest request) {

        RequestResponse response = new RequestResponse();
        String accountNumber = request.getAccountNumber();
        Integer amount = request.getAmount();

        if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
            BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
            BankTransaction withdrowTransaction = bankTransactionMapper.toWithdrawTransaction(request);

            // withdrowTransaction.setSenderAccountNumber(bankAccount.getAccountNumber());
            //withdrowTransaction.setReceiverAccountNumber(WITHDRAW);
            //withdrowTransaction.setAmount(amount);

            withdrowTransaction.setBankAccount(bankAccount);
            withdrowTransaction.setType(WITHDRAW);
            withdrowTransaction.setTransactionDateTime(Instant.now());


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


        // WITHDRAW MAKSE (ATM - v??etakse pangaautomaadist raha v??lja)
        // M??tle andmetele, et mis tulevad requestiga siia meetodisse sisse. V??id ka swaggeri ja debbugeriga vaadata.

        // Pangaautomaat kasutab meie infos??steemi.
        // Idee j??rgi ei saa meie teenusesse sisse tulla sellist accountNumber'it, mis meie pangas ei eksisteeri
        // aga kaitse peaks siiski olemas olema, et kaitsta meie pangas??steemi ka k??berkuritegevuse osas.

        // Laiem eesm??rk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse ??ks uus WITHDRAW tehingu kanne. Tehingu kanne peab sisaldama ka k??nealuse konto 'bank_account_id'd
        //      ??htlasi oleks vaja uuendada bank_account tabelis vastava konto kontoj????ki (balance)

        // alam-eesm??rk nr 1 oleks kontrollida, et kas selline accountNumber ??ldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada k??tte deposiidi saaja konto andmed (by accountNumber)
        //      a) pangakontot oleks vaja selle p??rast, et meil oleks vaja teada selle konto hetke kontoj????ki (balance)
        //      b) pangakontot oleks vaja selle p??rast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i k??ljes kui BankAccount t????pi objekt
        //      m??tle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad k??tte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesm??rk nr 2 on tekitada ??ks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa ??kki saad kasutada ??ra mingit BankTransactionMapper'is olevat m??pperit,
        //      millega saad kenasti luua uue m??pitud andmetega entity objekti.
        //      M??tle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      ??ra unusta teha ka vajalike arvutusi (newBalance), ning ??ra unusta neid ka ??igesse kohta k??lge panna
        //      M??tle milliseid muutujanimesi v??iks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab ??nnelikult tehtud [save()], siis v??iks response MESSAGE k??lge panna s??numi "Deposiit edukalt sooritatud!"

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
            response.setError("??lekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti! ");
        }
        return response;

        // V??LJUV MAKSE
        // M??tle andmetele, et mis tulevad requestiga siia meetodisse sisse. V??id ka swaggeri ja debbugeriga vaadata.

        // Laiem eesm??rk nr 1:
        //      Oleks vaja lisada 'bank_transaction' tabelisse ??ks uus SEND MONEY (v??ljuv) tehingu kanne. Tehingu kanne peab sisaldama ka k??nealuse konto 'bank_account_id'd
        //      ??htlasi oleks vaja uuendada bank_account tabelis vastava konto kontoj????ki (balance)

        // alam-eesm??rk nr 1 oleks kontrollida, et kas selline senderAccountNumber ??ldse eksisteerib meie andmebaasis
        // Kui selline konto eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada k??tte saatja konto andmed (by senderAccountNumber)
        //      a) pangakontot oleks vaja selle p??rast, et meil oleks vaja teada selle konto hetke kontoj????ki (balance)
        //      b) pangakontot oleks vaja selle p??rast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i k??ljes kui BankAccount t????pi objekt
        //      M??tle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad k??tte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesm??rk nr 2 on tekitada ??ks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa ??kki saad kasutada ??ra mingit BankTransactionMapper'is olevat m??pperit,
        //      millega saad kenasti luua uue m??pitud andmetega entity objekti.
        //      M??tle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      ??ra unusta teha ka vajalike arvutusi (newBalance), ning ??ra unusta neid ka ??igesse kohta k??lge panna
        //      M??tle milliseid muutujanimesi v??iks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

        // Kui tehing saab ??nnelikult tehtud [save()], siis v??iks response MESSAGE k??lge panna s??numi "Raha ??lekanne edukalt sooritatud!"

        // juhul kui senderAccountNumber'it meie andmebaasis ei leidu, siis v??iks tagastatavas responses ERROR'i s??numile k??lge panna s??numi:
        // "??lekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti!");


        // LAEKUV MAKSE
        // Laiem eesm??rk nr 2:
        //      Oleks vaja kontrollida, et kas ??lekande saaja (receiver) on juhuslikult meie klient.
        //      Kui jah, siis sellisel juhul tuleks lisada 'bank_transaction' tabelisse veel ??ks uus tehingu kanne - RECEIVE MONEY (laekuv kanne).
        //          Tehingu kanne peab sisaldama ka k??nealuse saajakonto 'bank_account_id'd
        //      ??htlasi oleks vaja uuendada bank_account tabelis vastava saajakonto kontoj????ki (balance)

        // alam-eesm??rk nr 1 oleks kontrollida, et kas ??kki selline receiverAccountNumber eksisteerib meie andmebaasis (kas ??lekande saajakonto number on ka meie panga klient)
        // Kui selline konto number eksisteerib, siis oleks meil vaja 'bank_account' tabelist saada k??tte saatja konto andmed (by receiverAccountNumber)
        //      a) pangakontot oleks vaja selle p??rast, et meil oleks vaja teada selle konto hetke kontoj????ki (balance)
        //      b) pangakontot oleks vaja selle p??rast, et seda kontot on vaja ka tehingu kandele juurde lisada.
        //         bank_transaction tabelis on see info kui foreign key 'bank_account_id', aga Javas on see BankTransaction'i k??ljes kui BankAccount t????pi objekt
        //      M??tle, et millise repository abil sa saaksid sellele bank_account tabelile ligi
        //      Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad k??tte need andmed, mis sind huvitavad.
        //
        //
        // alam-eesm??rk nr 2 on tekitada ??ks uus BankTransaction entity objekt.
        //      Sul on juba mingid andmed kenasti request'is (dto) olemas
        //      Vaata, et kas sa ??kki saad kasutada ??ra mingit BankTransactionMapper'is olevat m??pperit,
        //      millega saad kenasti luua uue m??pitud andmetega entity objekti.
        //      M??tle, et millised andmed sul on juba seal transaction objektis olemas (vajadusel uuri debuggeriga) ja millised tuleks SETTERITEGA juurde lisada
        //      M??tle milliseid muutujanimesi v??iks kasutada (depositTransaction, withdrawTransaction, senderTransaction, receiverTransaction, vms)

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
            receiveMoneyTransaction.setBankAccount(bankAccount);
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
            response.setError("??lekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti! ");
        }
        return response;

        // M??tle andmetele, et mis tulevad requestiga siia meetodisse sisse. V??id ka swaggeri ja debbugeriga vaadata.

        // LAEKUV MAKSE
        // suht sarnane asi nagu sendMoney, ainult, et n????d on sisse laekuv makse

        // Kui tehing saab ??nnelikult tehtud [save()], siis v??iks response MESSAGE k??lge panna s??numi "Raha ??lekanne edukalt sooritatud!"

        // juhul kui senderAccountNumber'it meie andmebaasis ei leidu, siis v??iks tagastatavas responses ERROR'i s??numile k??lge panna s??numi:
        // "??lekannet ei saanud teha, kuna pole sellise kontonumbriga EEXXXX klienti!");

    }

    public List<BankTransactionResponse> findAllTransactions() {
        List<BankTransaction> allTransactions = bankTransactionRepository.findAll();
        List<BankTransactionResponse> responses = bankTransactionMapper.toResponseList(allTransactions);
        return responses;
    }

    public List<BankTransactionResponse> findAllTransactionsByIdCode(String idCode) {
        List<BankTransaction> bankTransactions = bankTransactionRepository.findByBankAccount_Customer_PersonalIdentificationCode(idCode);
        List<BankTransactionResponse> bankTransactionResponses = bankTransactionMapper.toResponseList(bankTransactions);
        return  bankTransactionResponses;
        // Vaata eelmist lahendust ja m??tle :)
        // M??tle, et millise repository abil sa saaksid sellele bank_transaction tabeli andmetele ligi
        // Vajadusel kasuta JPA Pallete abi, et luua vajalik repository meetod, millega saad k??tte need andmed, mis sind huvitavad.

    }
}
