package foodsystemmanagement.service;

import org.springframework.stereotype.Service;

import foodsystemmanagement.dto.TopDashboardDto;

@Service
public class TopService {
	/*	 private final ProductService productService;
		 private final MaterialService materialService;*/
	 private final ProductionPlanService productionPlanService;
	 private final OrderService orderService;
	    public TopService(ProductService productService,MaterialService materialService,ProductionPlanService productionPlanService,OrderService orderService){
			/*	        this.productService = productService;
			    this.materialService = materialService;*/
	        this.productionPlanService = productionPlanService;
	        this.orderService = orderService;
	    }
	    public TopDashboardDto getDashboardData() {
	    	TopDashboardDto dto = new TopDashboardDto();
			/*//商品件数
			dto.setProductCount(productService.findAll().size());
			//原材料件数
			dto.setMaterialCount(materialService.findAll().size());*/
	    	//製造予定件数
	    	dto.setProductionPlanCount(productionPlanService.findTodayPlans().size());
	    	//未完了受注案件
	    	dto.setUnfinishedOrderCount(orderService.findUnfinishedOrders().size());
	    	//売上
	    	dto.setTodaySales(orderService.getTodaySales());
	    	dto.setWeekSales(orderService.getWeekSales());
	    	dto.setMonthSales(orderService.getMonthSales());
		    return dto;
	    }

}
