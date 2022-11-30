package at.kaindorf.springburger.data;

import at.kaindorf.springburger.Beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}