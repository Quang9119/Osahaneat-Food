package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.entity.*;
import com.cybersoft.Osahaneat.entity.keys.KeyOrderItem;
import com.cybersoft.Osahaneat.payload.request.OrderRequest;
import com.cybersoft.Osahaneat.repository.OrderItemRepository;
import com.cybersoft.Osahaneat.repository.OrderRepository;
import com.cybersoft.Osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users users = new Users();
            users.setId(orderRequest.getUserId());
            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());



            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);
            orderRepository.save(orders);

            List<OrderItem> items = new ArrayList<>();
            for (int idFood:orderRequest.getFoodId()) {
                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(),idFood);
                orderItem.setKeys(keyOrderItem);
                items.add(orderItem);
            }

            orderItemRepository.saveAll(items);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error insert order" + e.getMessage());
            return false;
        }


    }
}
