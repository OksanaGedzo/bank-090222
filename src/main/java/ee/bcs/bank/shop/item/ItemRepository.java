package ee.bcs.bank.shop.item;

import ee.bcs.bank.shop.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}