package ee.bcs.bank.newbank.banktransaction;

import ee.bcs.bank.newbank.bankaccount.BankAccountDto;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BankTransactionDto implements Serializable {
    private final BankAccountDto bankAccount;
    private final String senderAccountNumber;
    private final String receiverAccountNumber;
    private final Integer amount;
    private final Integer balance;
    private final String type;
    private final Instant transactionDateTime;
}
