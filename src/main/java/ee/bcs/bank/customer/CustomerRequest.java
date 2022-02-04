package ee.bcs.bank.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerRequest implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String idCode;
}
