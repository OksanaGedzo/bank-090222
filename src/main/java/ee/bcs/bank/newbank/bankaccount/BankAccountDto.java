package ee.bcs.bank.newbank.bankaccount;

import ee.bcs.bank.newbank.customer.CustomerDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class BankAccountDto implements Serializable {
    private final CustomerDto customer;
    private final String accountNumber;
    private final Integer balance;
    private final Boolean locked;
}
