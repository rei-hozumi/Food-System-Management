package foodsystemmanagement.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.repository.OrderRepository;
import foodsystemmanagement.repository.ProductionPlanRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductionPlanRepository productionPlanRepository;

    public OrderService(OrderRepository orderRepository,ProductionPlanRepository productionPlanRepository) {
        this.orderRepository = orderRepository;
        this.productionPlanRepository = productionPlanRepository;
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
    	
    	 System.out.println("注文件数：" + orders.size());
    	 
    	for(Order o:orders) {
    		
    		
            System.out.println("注文日：" + o.getOrderDate());
            System.out.println("商品：" + o.getProduct().getProductName());
            System.out.println("価格：" + o.getProduct().getPrice());
            System.out.println("数量：" + o.getOrderQuantity());
            
            
    		int price = o.getProduct().getPrice().intValue();
    		int quantity = o.getOrderQuantity();
    		total += price*quantity;
    	}
    	
    	   System.out.println("売上：" + total);
    	   
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
    @Transactional
    public void delete(Long id) {
    	//先に製造計画を削除、その後受注削除
        productionPlanRepository.deleteByOrderId(id);
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
