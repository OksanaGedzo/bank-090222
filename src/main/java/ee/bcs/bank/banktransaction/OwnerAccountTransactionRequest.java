package ee.bcs.bank.banktransaction;

import lombok.Data;

@Data
public class OwnerAccountTransactionRequest {
    private String accountNumber;
    private Integer amount;
}
