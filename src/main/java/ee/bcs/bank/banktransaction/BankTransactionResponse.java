package ee.bcs.bank.banktransaction;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BankTransactionResponse implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String idCode;
    private final String senderAccountNumber;
    private final String receiverAccountNumber;
    private final Integer amount;
    private final Integer balance;
    private final String type;
    private final Instant transactionDateTime;
}
