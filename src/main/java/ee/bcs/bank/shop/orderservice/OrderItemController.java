package ee.bcs.bank.shop.orderservice;

import ee.bcs.bank.shop.item.Item;
import ee.bcs.bank.shop.item.ItemRepository;
import ee.bcs.bank.shop.order.Order;
import ee.bcs.bank.shop.order.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/order/item")
public class OrderItemController {

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private ItemRepository itemRepository;

    @PostMapping("/add")
    public OrderItem addItemToOrder(@RequestParam Integer itemId, @RequestParam Integer orderId) {
        OrderItem orderItem = new OrderItem();

        Item item = itemRepository.getById(itemId);
        Optional<Item> byId = itemRepository.findById(itemId);

        Order order = orderRepository.getById(orderId);

        return null;
    }
}
