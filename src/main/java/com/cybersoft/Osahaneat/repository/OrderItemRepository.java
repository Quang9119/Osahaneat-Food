package com.cybersoft.Osahaneat.repository;

import com.cybersoft.Osahaneat.entity.OrderItem;
import com.cybersoft.Osahaneat.entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
