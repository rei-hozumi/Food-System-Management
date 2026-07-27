package foodsystemmanagement.service;


import java.util.List;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 注文一覧取得
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    // 注文登録
    public void save(Order order) {
        orderRepository.save(order);
    }

    // 注文取得（ID検索）
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    // 注文更新
    public void update(Order order) {
        orderRepository.save(order);
    }

    // 注文削除
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
