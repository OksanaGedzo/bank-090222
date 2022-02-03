package ee.bcs.bank.newbank.bankaccount;

import ee.bcs.bank.newbank.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "account_number", nullable = false, length = 11)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

}