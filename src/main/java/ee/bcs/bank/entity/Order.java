package ee.bcs.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "end_customer_id", nullable = false)
    private EndCustomer endCustomer;

    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @Column(name = "order_total_price", nullable = false)
    private Integer orderTotalPrice;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

}