package ee.bcs.bank.newbank.banktransaction;

import ee.bcs.bank.newbank.bankaccount.BankAccount;
import ee.bcs.bank.newbank.bankaccount.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;

@Service
public class BankTransactionService {

    public static final String SEND = "s";

    @Resource
    private BankAccountRepository bankAccountRepository;

    @Resource
    private BankTransactionRepository bankTransactionRepository;

    @Resource
    private BankTransactionMapper bankTransactionMapper;

    public void sendMoneyBySenderLastName(BankTransactionDto bankTransactionDto, String lastName) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByCustomerLastName(lastName);
        BankTransaction bankTransaction = bankTransactionMapper.toEntity(bankTransactionDto);
        Integer balance = bankAccount.getBalance();
        Integer amount = bankTransactionDto.getAmount();
        Integer newBalance = balance - amount;
        bankAccount.setBalance(newBalance);
        bankTransaction.setBalance(newBalance);
        bankTransaction.setBankAccount(bankAccount);
        bankTransaction.setSenderAccountNumber(bankAccount.getAccountNumber());
        bankTransaction.setType(SEND);
        bankTransaction.setTransactionDateTime(Instant.now());
        bankTransactionRepository.save(bankTransaction);
    }


    // SISSETULEV INFO (dto)
    // receiverAccountNumber;
    // amount;
    // balance;
    // type ("s");

    // Pathiks /send/money/by/sender/lastname

    // Sisse tuleb BankTransactionDto 'bankTransactionDto' objekt ja last name
    // leida BankAccount 'bankAccount' (entity) by last name
    // 'bankAccount' objekt leia 'bankAccountRepository' kaudu
    // mäppida Dto'st andmed BankTransaction 'bankTransaction' (entity) objekti
    // pane 'bankTransaction' (entity) objektile külge 'bankAccount' entity objekt
    // pane 'bankTransaction'ile külge ka timestamp (Instant.now)
    // salvestada 'bankTransaction' (entity) andmebaasi (save meetod)
    // kogu lahendus service klassis
}
