package ee.bcs.bank.newbank.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private final String firstName;
    private final String lastName;
}
