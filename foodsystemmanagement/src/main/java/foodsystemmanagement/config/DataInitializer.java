/*package foodsystemmanagement.config;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import foodsystemmanagement.entity.Material;
import foodsystemmanagement.entity.Order;
import foodsystemmanagement.entity.Product;
import foodsystemmanagement.entity.ProductionPlan;
import foodsystemmanagement.entity.User;
import foodsystemmanagement.repository.MaterialRepository;
import foodsystemmanagement.repository.OrderRepository;
import foodsystemmanagement.repository.ProductRepository;
import foodsystemmanagement.repository.ProductionPlanRepository;
import foodsystemmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;
    private final OrderRepository orderRepository;
    private final ProductionPlanRepository productionPlanRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
            User admin = new User();
            admin.setLoginId("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setUserName("管理者");
            admin.setRole("ADMIN");
            userRepository.save(admin);
            
            User planner = new User();
            planner.setLoginId("planner");
            planner.setPassword(passwordEncoder.encode("planner123"));
            planner.setUserName("製造計画担当");
            planner.setRole("PLANNER");
            userRepository.save(planner);

            User worker = new User();
            worker.setLoginId("worker");
            worker.setPassword(passwordEncoder.encode("worker123"));
            worker.setUserName("製造担当");
            worker.setRole("WORKER");
            userRepository.save(worker);
            
            
            //商品登録
            Product product1 = new Product();
            product1.setProductNumber("P001");
            product1.setProductName("チョコレートケーキ");
            product1.setPrice(new BigDecimal("1200"));
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setProductNumber("P002");
            product2.setProductName("ショートケーキ");
            product2.setPrice(new BigDecimal("900"));
            productRepository.save(product2);

            Product product3 = new Product();
            product3.setProductNumber("P003");
            product3.setProductName("プリン");
            product3.setPrice(new BigDecimal("350"));
            productRepository.save(product3);
           
            
            //原材料登録
            Material material1 = new Material();
            material1.setMaterialCode("M001");
            material1.setMaterialName("小麦粉");
            material1.setStandard("1kg");
            material1.setPrice(new BigDecimal("1980"));
            material1.setExpirationDate(LocalDate.now().plusMonths(6));
            material1.setUnit("袋");
            material1.setStockQuantity(50);
            materialRepository.save(material1);

            Material material2 = new Material();
            material2.setMaterialCode("M002");
            material2.setMaterialName("砂糖");
            material2.setStandard("500g×5袋");
            material2.setPrice(new BigDecimal("2600"));
            material2.setExpirationDate(LocalDate.now().plusMonths(10));
            material2.setUnit("ケース");
            material2.setStockQuantity(80);
            materialRepository.save(material2);

            Material material3 = new Material();
            material3.setMaterialCode("M003");
            material3.setMaterialName("卵");
            material3.setStandard("100個");
            material3.setPrice(new BigDecimal("3400"));
            material3.setExpirationDate(LocalDate.now().plusMonths(1));
            material3.setUnit("ケース");
            material3.setStockQuantity(200);
            materialRepository.save(material3);
            
            //受注登録
            Order order1 = new Order();
            order1.setOrderDate(LocalDate.now());
            order1.setDueDate(LocalDate.now().plusDays(3));
            order1.setProduct(product1);
            order1.setOrderQuantity(20);
            order1.setStatus("受付");
            orderRepository.save(order1);

            Order order2 = new Order();
            order2.setOrderDate(LocalDate.now());
            order2.setDueDate(LocalDate.now().plusDays(5));
            order2.setProduct(product2);
            order2.setOrderQuantity(15);
            order2.setStatus("製造中");
            orderRepository.save(order2);
            
            //製造計画登録
            ProductionPlan plan1 = new ProductionPlan();
            plan1.setOrder(order1);
            plan1.setProduct(product1);
            plan1.setPlannedQuantity(20);
            plan1.setAssignedUser(planner);
            plan1.setStatus("未着手");
            plan1.setProductionDate(LocalDate.now());
            productionPlanRepository.save(plan1);

            ProductionPlan plan2 = new ProductionPlan();
            plan2.setOrder(order2);
            plan2.setProduct(product2);
            plan2.setPlannedQuantity(15);
            plan2.setAssignedUser(worker);
            plan2.setStatus("製造中");
            plan2.setProductionDate(LocalDate.now());
            productionPlanRepository.save(plan2);
            
            System.out.println("初期データ登録完了");
    }
}*/