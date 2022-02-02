package ee.bcs.bank.shop.order;

import ee.bcs.bank.shop.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}