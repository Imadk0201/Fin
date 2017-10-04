package objects;

public class Dispense {
	
	private int barista_id, start_time, order_id;
	
	public Dispense(int order_id, int time, int id){
		this.order_id = order_id;
		this.start_time = time;
		this.barista_id = id;

	}
	
	public int getBaristaId(){
		return barista_id;
	}
	
	public int getOrderTime(){
		return start_time;
	}
	
	public int getOrderId(){
		return order_id;
	}
}


