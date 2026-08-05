package foodsystemmanagement.dto;


public class TopDashboardDto {
	/*		//商品件数
			private long productCount;
			//原材料件数
			private long materialCount;*/
		//製造予定件数
		private long productionPlanCount;
		//未完了受注件数
		private long unfinishedOrderCount;
		//本日の売り上げ
		private int todaySales;
		//今週の売り上げ
		private int weekSales;
		//今月の売り上げ
		private int monthSales;

		/*		public long getProductCount() {
					return productCount;
				}
				public void setProductCount(long productCount) {
					this.productCount = productCount;
				}
				public long getMaterialCount() {
					return materialCount;
				}
				public void setMaterialCount(long materialCount) {
					this.materialCount = materialCount;
				}*/
		public long getProductionPlanCount() {
			return productionPlanCount;
		}
		public void setProductionPlanCount(long productionPlanCount) {
			this.productionPlanCount = productionPlanCount;
		}
		public long getUnfinishedOrderCount() {
			return unfinishedOrderCount;
		}
		public void setUnfinishedOrderCount(long unfinishedOrderCount) {
			this.unfinishedOrderCount = unfinishedOrderCount;
		}
		public int getTodaySales() {
			return todaySales;
		}
		public void setTodaySales(int todaySales) {
			this.todaySales = todaySales;
		}
		public int getWeekSales() {
			return weekSales;
		}
		public void setWeekSales(int weekSales) {
			this.weekSales = weekSales;
		}
		public int getMonthSales() {
			return monthSales;
		}
		public void setMonthSales(int monthSales) {
			this.monthSales = monthSales;
		}
		
		
}
