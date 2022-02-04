package ee.bcs.bank.banktransaction;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionRequest implements Serializable {
    private final String senderAccountNumber;
    private final String receiverAccountNumber;
    private final Integer amount;
}
