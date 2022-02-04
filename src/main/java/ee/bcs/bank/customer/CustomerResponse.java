package ee.bcs.bank.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerResponse  implements Serializable {
    private final String firstName;
    private final String lastName;
}
