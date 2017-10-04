package objects;

public class Order implements Comparable<Order>{
	
	private int order_id, order_time;
	private String type;
	
	public enum Drinks {
		TEA (3, 2), LATTE(4, 3), AFFOGATO(7, 5);
		
		public int brewTime;
		public int profit;
		
		Drinks(int brewTime, int profit){
			this.brewTime = brewTime;
			this.profit = profit;
		}
		
		public static Drinks fromString(String text) {
			if(text.equalsIgnoreCase("tea"))
				return TEA;
			else if(text.equalsIgnoreCase("latte"))
				return LATTE;
			else
				return AFFOGATO;
		}
	}
	
	public Order(int id, int time, String type){
		this.order_id = id;
		this.order_time = time;
		this.type = type;
	}
	
	public int getOrderId(){
		return order_id;
	}
	
	public int getOrderTime(){
		return order_time;
	}
	
	public String getOrderType(){
		return type;
	}

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		int timeDiff = Drinks.fromString(this.getOrderType()).brewTime - Drinks.fromString(o.getOrderType()).brewTime;
		return timeDiff != 0 ? timeDiff: this.getOrderTime() - o.getOrderTime();
	}
	
	public String toString(){
		return "(Order ID: " + order_id + ", Brew Time: " + Drinks.fromString(this.getOrderType()).brewTime + ")";
	}
	
}


