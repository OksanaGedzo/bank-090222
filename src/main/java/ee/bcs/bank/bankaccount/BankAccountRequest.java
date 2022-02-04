package ee.bcs.bank.bankaccount;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankAccountRequest implements Serializable {
    private final String personalIdentificationCode;
    private final String accountNumber;
    private final Integer balance;
}
