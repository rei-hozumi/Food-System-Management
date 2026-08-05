package foodsystemmanagement.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
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

    //ダッシュボードの未完了受注数取得
    public List<Order>findUnfinishedOrders(){
    	return orderRepository.findByStatusNot("完了");
    }
    //売上取得
    public int getTodaySales() {
        LocalDate today = LocalDate.now();
        List<Order> orders = orderRepository.findByOrderDate(today);
        return calculateSales(orders);
    }
    public int getWeekSales(){
        LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate end = LocalDate.now();
        List<Order> orders = orderRepository.findByOrderDateBetween(start,end);
        return calculateSales(orders);
    }
    public int getMonthSales(){
        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1);
        LocalDate end = now;
        List<Order> orders = orderRepository.findByOrderDateBetween(start,end);
        return calculateSales(orders);
    }
    private int calculateSales(List<Order>orders) {
    	int total =0;
    	for(Order o:orders) {
    		int price = o.getProduct().getPrice().intValue();
    		int quantity = o.getOrderQuantity();
    		total += price*quantity;
    	}
    	return total;
    }
    
    // 注文一覧取得
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    
    
    // 注文登録
    public void save(Order order) {
    	order.setOrderNumber(createOrderNumber());
        orderRepository.save(order);
    }

    //受注番号自動生成
    private String createOrderNumber() {
    	Order latestOrder = orderRepository.findTopByOrderByIdDesc();
    	int number = 1;
    	if(latestOrder != null) {
    		String lastNo = latestOrder.getOrderNumber();
    		number = Integer.parseInt(lastNo.replace("ORD", ""))+1;
    	}
    	return String.format("ORD%04d",number);
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
	//検索
	public List<Order> search(String keyword){
		System.out.println("検索文字："+keyword);
		List<Order>list=orderRepository.findByOrderNumberContaining(keyword);
		System.out.println("件数："+list.size());
		return list;
	}
}
