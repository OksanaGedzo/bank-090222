package ee.bcs.bank.shop.order;

import ee.bcs.bank.shop.customer.EndCustomer;
import ee.bcs.bank.shop.customer.EndCustomerRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private EndCustomerRepository endCustomerRepository;


    @PostMapping("/add")
    public Order addOrder(@RequestParam Integer customerId) {
        Order order = new Order();
        order.setOrderDate(Instant.now());
        order.setOrderTotalPrice(0);
        order.setStatus("p");
        EndCustomer customer = endCustomerRepository.pleaseFindById(customerId);
        order.setEndCustomer(customer);
        orderRepository.save(order);
        return order;
    }

    @PostMapping("/add/by/lastname")
    public Order addOrderByLastName(@RequestParam String lastName) {
        Order order = new Order();
        order.setOrderDate(Instant.now());
        order.setOrderTotalPrice(0);
        order.setStatus("p");
        EndCustomer customer = endCustomerRepository.findByLastName(lastName);
        order.setEndCustomer(customer);
        orderRepository.save(order);
        return order;
    }



}
