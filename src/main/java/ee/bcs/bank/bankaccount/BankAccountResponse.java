package ee.bcs.bank.bankaccount;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class BankAccountResponse implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String accountNumber;
    private final Integer balance;
}