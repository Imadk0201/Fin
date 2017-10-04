import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;
import com.google.gson.Gson;
import objects.Dispense;
import objects.Order;

/*
 * Main class that reads input from the file of orders, parses the JSON, and delivers the appropriate output
 */
public class Driver {

	// Daily time limit per day in minutes
	public static final int TIME_LIMIT = 100;
	
	public static void main(String [] args){
		// Basic file reading performed by buffered reader to accumulate all file text into a string
		String line = "", input="";
		Gson gson = new Gson();
		
		// Hyper-Parameters used to test the creative non-Fifo method
		int blockSize = 4;
		int timeStride = 3;
		
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
		    while ((line = br.readLine()) != null) {
		    	input += line;
		    }
		    // Parses the file into a Json array using a pre-defined 'Order' class
		    Order[] orders = gson.fromJson(input, Order[].class);
		    
		    // Performs the FIFO method on the Json array of orders
		    System.out.println(Fifo(orders));
		    
		    // Performs the creative method on Json array or orders
		    System.out.println(imadMethod(orders, blockSize, timeStride));
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// Simple Fifo method that takes in the array and processes the orders in chronological order
	public static String Fifo(Order[] arr){
		// Performs quick null-check to test validity of input
		if(arr == null || arr.length < 1) 
			return null;
		
		// Takes the Json array passed in and converts it into a Queue for processing in-order
		Queue<Order> orders = new LinkedList<>(Arrays.asList(arr));
		List<Dispense> processed = new ArrayList<Dispense>();
		Gson gson = new Gson();
		
		int barista1Time = orders.peek().getOrderTime();;
		int barista2Time = orders.peek().getOrderTime();
		
		Dispense makingOrder;
		Order currentOrder;
		int baristaId;
		
		// Loops through the queue one by one and processes the orders in the order they are received
		while(!orders.isEmpty() && (barista1Time <= TIME_LIMIT || barista2Time <= TIME_LIMIT)) {
			currentOrder = orders.poll();
	
			// Assigns the order to the barista who is available first
			if(barista1Time <= barista2Time){
				baristaId = 1;
				
				makingOrder = new Dispense(currentOrder.getOrderId(), barista1Time, baristaId);
				barista1Time += Order.Drinks.fromString(currentOrder.getOrderType()).brewTime;
			} else {
				baristaId = 2;
								
				makingOrder = new Dispense(currentOrder.getOrderId(), barista2Time, baristaId);
				barista2Time += Order.Drinks.fromString(currentOrder.getOrderType()).brewTime;
			}
			processed.add(makingOrder);	
		}
		// Converts the output back to Json using pre-defined 'Dispense' class
		String result = gson.toJson(processed);
				
		return result;
	}
	/*
	 * Creative method that processes orders in blocks of orders or time, depending on which one
	 * is less restrictive. Takes in the Json array, a block size, and time stride
	 */
	public static String imadMethod(Order[] arr, int blockSize, int timeStride){
		// Null check
		if(arr == null || arr.length < 1) 
			return null;
		// Instantiates variables to keep track of the index, block sizes, and time strides
		int i = 0;
		int count = 0;
		int currentTime;
		// Starts both baristas off at the time of the first orders placed
		int barista1Time = arr[0].getOrderTime();
		int barista2Time = arr[0].getOrderTime();
		// Creates a list of all processed drinks
		List<Dispense> processed = new ArrayList<Dispense>();
		
		Gson gson = new Gson();
		Dispense makingOrder;
		Order currentOrder;
		int baristaId;

		while(i < arr.length){
			count = 0;
			currentTime = arr[i].getOrderTime();
			// Places blocks into a treeset to order drinks by time required to make each drink
			TreeSet<Order> group = new TreeSet<Order>();
			// Loops through the array in blocks and adds each block into a Treeset 
			while(i < arr.length && (count <= blockSize && arr[i].getOrderTime()-currentTime <= timeStride)) {
				group.add(arr[i]);
				count++;
				i++;
			}
			// Loops through each block after and processes them by both baristas
			for(Order r: group){
				if(barista1Time <= TIME_LIMIT || barista2Time <= TIME_LIMIT) {
					currentOrder = r;

					if(barista1Time <= barista2Time){
						baristaId = 1;
						
						makingOrder = new Dispense(currentOrder.getOrderId(), barista1Time, baristaId);
						barista1Time += Order.Drinks.fromString(currentOrder.getOrderType()).brewTime;
					} else {
						baristaId = 2;
										
						makingOrder = new Dispense(currentOrder.getOrderId(), barista2Time, baristaId);
						barista2Time += Order.Drinks.fromString(currentOrder.getOrderType()).brewTime;
					}
					processed.add(makingOrder);	
				}
			}
			i = i <  arr.length ? i++ : i;
		}
		// Converts the returned list into a Json array using pre-defined 'Dispense' class
		String result = gson.toJson(processed);
		
		return result;
	}
}
