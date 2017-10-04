

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;

import objects.Dispense;
import objects.Order;

public class Tests {

	/*
	 * Tests to see if the FIFO output matches the required ouput
	 */
	@Test
	public void FifoOutputTest() {
		
		Gson gson = new Gson();
		String line = "", input = "", output = "";
		try  {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		    while ((line = br.readLine()) != null) {
		    	input += line;
		    }
		    
			br = new BufferedReader(new FileReader("output.txt"));
		    while ((line = br.readLine()) != null) {
		    	output += line;
		    }

		    Order[] orders = gson.fromJson(input, Order[].class);

		    String resultOutput = Driver.Fifo(orders);
		    
		    Dispense[] requiredOutput = gson.fromJson(output, Dispense[].class);
		    Dispense[] myOutput = gson.fromJson(resultOutput, Dispense[].class);
		    
		    assertTrue(requiredOutput.length == myOutput.length);
		    
		    for(int i=0; i<requiredOutput.length; i++) {
		    	assertTrue(requiredOutput[i].getBaristaId() == myOutput[i].getBaristaId());
		    	assertTrue(requiredOutput[i].getOrderId() == myOutput[i].getOrderId());
		    	assertTrue(requiredOutput[i].getOrderTime() == myOutput[i].getOrderTime());
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Tests to see if the custom method is just as or more efficient than the FIFO method
	 */
	@Test
	public void customMethodTest() {
		
		Gson gson = new Gson();
		String line = "", input = "", output = "";
		try  {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		    while ((line = br.readLine()) != null) {
		    	input += line;
		    }
		    
			br = new BufferedReader(new FileReader("output.txt"));
		    while ((line = br.readLine()) != null) {
		    	output += line;
		    }
		    
		    Order[] orders = gson.fromJson(input, Order[].class);

		    String resultOutput = Driver.imadMethod(orders, 4, 3);
		    
		    Dispense[] requiredOutput = gson.fromJson(output, Dispense[].class);
		    Dispense[] myOutput = gson.fromJson(resultOutput, Dispense[].class);
		    
		    assertTrue(requiredOutput.length <= myOutput.length);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
