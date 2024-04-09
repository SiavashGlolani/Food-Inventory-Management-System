import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;


public class Inventory {
	
	private ArrayList<FoodItem> arrayItems; // The array of FoodItem objects.
	//private static final int NUM = 20; // The maximum number of items that can be stored.
	//private int numItems; // The current number of items in the inventory.
	
	/**
	 * Default constructor that initializes arrayItems to a new array with size NUM and numItems to 0.
	 */
	Inventory(){
		arrayItems = new ArrayList<FoodItem>();
		//arrayItems = new FoodItem[NUM];
		//numItems = 0;

	}
	
	
	/**
	 * Overrides toString() in Object class.
	 * Returns a string representation of the inventory.
	 * @return The string representation of the inventory.
	 */
	@Override 
	public String toString() {
		int i;
		String Inventory = "Inventory: ";
		
		for(i = 0; i < arrayItems.size(); ++i)
			Inventory = Inventory + "\n" + arrayItems.get(i).toString();
			//arryItems[i].toString();
		return Inventory;
	}
	
	
	/**
	 * Checks if an item already exists in the inventory.
	 *
	 * @param item The item to be checked.
	 * @return The index of the item in the array if it exists, -1 otherwise.
	 */
	public int alreadyExists(FoodItem item) {
			
			for(int i = 0; i < arrayItems.size(); i++){
				//arrayItem[i].isEqual(item)
				//arrayItem.get(i).isEqual(item)
				if(item.isEqual(arrayItems.get(i)))
				{
					return i; // or break?
				}				
			}
			 return -1;
		}
	
	/**
	 * Adds a new item to the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the item is successfully added, false otherwise.
	 */
	public boolean addItem(Scanner scanner) {
		
		String type;
		boolean isValid = false;
		FoodItem newItem = null;
		
		do {
			System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p)?, milk(m)? ");
			type = scanner.nextLine();
			if (type.equals("")) type = scanner.nextLine();
			if(!type.equals("f") && !type.equals("v") && !type.equals("p") && !type.equals("m"))
			{
				System.out.println("Invalid entry");
				return false;
			}
		}while((!type.equals("f") && !type.equals("v") && !type.equals("p")) && !type.equals("m") );
		
		/*do {
			System.out.print("Enter the code for the item: ");
			if(scanner.hasNextInt())
			{
				code = scanner.nextInt();
				FoodItem item = new FoodItem(code, "", 0.0f, 0, 0.0f);
				if (alreadyExists(item) != -1) {
					System.out.println("Item code already exists");
					isValid = false;
				}
				else isValid = true;
			}
			else 
			{
				System.out.println("Invalid code");
				scanner.next();
				isValid = false;
			}	
		}while(!isValid);*/
		
		switch(type)
		{
			case "f":
				newItem = new Fruit(0, "", 0.0f, 0, 0.0f, "");
				break;
			case "v":
				newItem = new Vegetable(0, "", 0.0f, 0, 0.0f, "");
				break;
			case "p":
				newItem = new Preserve(0, "", 0.0f, 0, 0.0f, 0);
				break;
			
			case "m":
				newItem = new Milk(0, "", 0.0f, 0, 0.0f, 0);
			
		}
		
		do
		{
			newItem.inputCode(scanner);
			if (alreadyExists(newItem) != -1) {
				System.out.println("Item code already exists");
				isValid = false;
			}
			else isValid = true;
	
		}
		while(!isValid);
	
		newItem.addItem(scanner);
		boolean possible = arrayItems.add(newItem);
		if (!possible) return false;
		
		//System.out.println(newItem.getClass().toString());
		//shifting
		int i;
		FoodItem temp;
		for(i = arrayItems.size() - 1; i > 0; i--) {
			if(arrayItems.get(i).compareTo(arrayItems.get(i - 1)) < 0)
			{
				temp = arrayItems.get(i);
				arrayItems.set(i, arrayItems.get(i - 1));
				arrayItems.set(i - 1, temp);			
			}
			else break;
		}
		
		return possible;
	}
	
	/**
	 * Updates the quantity of an existing item in the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @param buyOrSell True if the operation is buy, false if it's sell.
	 * @return True if the operation is successful, false otherwise.
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		boolean valid = false;
		String dummy = "";
		int code = 0;
				
		do
		{
			valid = true;
			try {
				
				System.out.print("Enter the item code: ");
				dummy = scanner.nextLine();
				if (dummy.equals("")) dummy = scanner.nextLine();
				code = Integer.parseInt(dummy);
				
			}catch(NumberFormatException e) {
				System.out.println("Invalid Entry");
				valid = false;
			}
		}
		while(!valid);
		
		FoodItem item = new FoodItem(code, "", 0.0f, 0, 0.0f);
		int amount = 0;
		int index = alreadyExists(item);
		//System.out.printf("Number of Items = %d\n", numItems);
		//System.out.printf("Item1 = %d\n")
		
		if (index == -1)
		{
			if (buyOrSell) System.out.println("Error... could not buy item");
			else System.out.println("Error... could not sell item");
			return false;
		}
		else
		{
			if (buyOrSell)
			{
				do
				{
					valid = true;
					try {
						
						System.out.print("Enter the quantity to buy: ");
						dummy = scanner.nextLine();
						if (dummy.equals("")) dummy = scanner.nextLine();
						amount = Integer.parseInt(dummy);						
					}catch(NumberFormatException e) {
						System.out.println("Invalid Entry");
						valid = false;
					}
				}
				while(!valid);
				
				if (amount < 0)
				{
					System.out.println("Invalid quantity...");
					System.out.println("Error...could not buy item");
					return false;
				}
				else arrayItems.get(index).updateItem(amount);
			}
			else
			{
				do
				{
					valid = true;
					try {
						
						System.out.print("Enter the quantity to sell: ");
						dummy = scanner.nextLine();
						if (dummy.equals("")) dummy = scanner.nextLine();
						amount = Integer.parseInt(dummy);						
					}catch(NumberFormatException e) {
						System.out.println("Invalid Entry");
						valid = false;
					}
				}
				while(!valid);
				
				if (amount < 0)
				{
					System.out.println("Invalid quantity...");
					System.out.println("Error...could not sell item");
					return false;
				}
				else if (!arrayItems.get(index).updateItem(-amount))
				{
					System.out.println("Insufficient stock in inventory...");
					System.out.println("Error...could not sell item");
					return false;
				}
			}
		}
		return true;
	}
		/*
		 * This method is used to search for a FoodItem in the inventory based on a provided item code.
		 * It prompts the user to input an item code and checks if it exists in the inventory. 
		 * If a matching item is found, the item details are printed.
		 * If no match is found, it outputs a message stating the item code was not found in the inventory. 
		 * @param scanner a Scanner object used for user input.
		 * 
		 * NOT USING THIS METHOD, JUST WANTED TO COMPARE THE CODE I WROTE IN THIS METHOD TO CODE IN searchForItem() AS REFERENCE
		 */
		public void searchForItem2(Scanner scanner) {
			int i;
			int code = 0;
			boolean found = false;
			String value = "";
			try {
				
				System.out.print("Enter the code for the item: ");
				value = scanner.nextLine();
				if (value.equals("")) value = scanner.nextLine();
				code = Integer.parseInt(value);
				
				// Search the inventory for the entered item code
				for(i = 0; i < arrayItems.size(); i++) {
					if(code == arrayItems.get(i).getItemCode()) 
					{	
						// If found, set found to true, print item details and break the loop
						found = true;
						System.out.print(arrayItems.get(i).toString());
						break;
					}
				}
				
				// If item code not found in inventory, print message
				if(!found) 
				{
					System.out.println("Code not found in inventory...");
				}

			}catch(NumberFormatException e) {
				System.out.println("Error: item code must be digits"); //catching any non integer-values values and prompting user to try again 
				scanner.nextLine(); //clearing the buffer so that when user trys again, program does not crash
				
			}
			
		}
		
		
		/*
		 * this binary search will be used in searchForItem() below. The purpose is to retrieve item when user enters item
		 * code using binary search
		 * @param searchCode, the searchCode to be checked
		 * @return currentIndex or -1
		 */
		public int binarySearchForItemCode(int searchCode) {
		     int lowerBound = 0;
		     int upperBound = arrayItems.size() - 1;
		     int currentIndex;

		     while(true){
		        currentIndex = (lowerBound + upperBound ) / 2;
		        if(arrayItems.get(currentIndex).getItemCode() == searchCode)
		           return currentIndex;              
		        else if(lowerBound > upperBound)
		           break;            
		        else
		        {
		           if(arrayItems.get(currentIndex).getItemCode() < searchCode)
		              lowerBound = currentIndex + 1; 
		           else
		              upperBound = currentIndex - 1; 
		           }  
		        }
		     
		     return -1;
		}
		
		
		/*
		 * 
		 * This method is used to search for an item in the inventory based on the item code. The user 
		 * is prompted to input the item code and the method will check if an item with that code exists
		 * in the inventory. If it does, it will print out the details of the item. If not, it will
		 * inform the user that the code was not found in the inventory.
		 * @param scanner This is a Scanner object that is used to accept user input.
		 */
		public void searchForItem(Scanner scanner) {
			//search takes an item and returns its index in the array (-1 if the item does not exist in the array)
			//find takes an item and returns a boolean (true if the item is in the array, false if it is not)
			//int search(FoodItem food)
			//boolean findItem(FoodItem f)
			//{
				
			//	int ind = search(f);
			//	if(ind == -1)  // if (search(f) == -1)
				{
					//return false
				}
				//return true;
				
				//return !(search(f) == -1);
			//}
			
			// Create a new FoodItem object
			FoodItem f = new FoodItem();
			
			// Prompt the user to input the item code
			f.inputCode(scanner);
			
			// Check if an item with the inputted code exists in the inventory
			int indexFound = binarySearchForItemCode(f.getItemCode());
			
			// If the item does not exist (indicated by an index of -1),
			// inform the user that the code was not found
			 if(indexFound == -1) {
				System.out.println("Code not found in inventory...");
			 }
				// If the item exists, print out its details
			 else
			 {
				 System.out.println(arrayItems.get(indexFound).toString());
			 }
		}

		
		
		/*
		 * This method saves the current state of the inventory to a file. The user is prompted to 
		 * enter the name of the file to save to. Each item in the inventory is written to this file
		 * using the FoodItem's outputItem() method to format the item data.
		 * @param scanner This is a Scanner object used to accept user input for the filename.
		 * 
		 */
		public void saveToFile(Scanner scanner) {
			FoodItem fItem = new FoodItem();
			
			// Prompt user for the filename to save to
			System.out.println("Enter the filename to save to:");
			String filename = scanner.nextLine();
			if (filename.equals("")) filename = scanner.nextLine();
			
			// Attempt to create a FileWriter object to write to the file
			// and loop through the array of items, writing each one to the file
			try {
				FileWriter fwr = new FileWriter(filename);
				
				// Loop through each item in the inventory
				for(int i = 0; i < arrayItems.size(); i++) {
					// Get the formatted string for the current item
					String formattedItem = arrayItems.get(i).outputItem();
					// Write the formatted string to the file, followed by a newline
					fwr.write(formattedItem + "\n");
					//fwr.write(arrayItems.get(i).outputItem());
				}
				// After writing all items to the file, close the FileWriter
				fwr.close();
			} catch (IOException e) {
				System.out.println(filename + " could not be created!");
				e.printStackTrace();
			}
		}
		
		/*
		 * This method reads item details from a file and adds them to the inventory.
		 * The file should contain information on each item in the following format:
		 * type, code, name, quantity, price, cost, and a field specific to the type (farmName, supplierName, preserveSize, or milkSize).
		 * If an invalid item type or duplicate item code is found in the file, the file reading process is aborted.
		 * @param scanner This is a Scanner object used to accept user input for the filename.
		 * 
		 */
		public void readItemFile(Scanner scanner) {
			String type;
			int code, quantity;
			String name;
			float price, cost;
			
			FoodItem newItem = null;
			
			System.out.println("Name of the file to read to:");
			String fname = scanner.nextLine();
			if (fname.equals("")) fname = scanner.nextLine();
			
			File file = new File(fname);
			
			try 
			{
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				while(true)
				{
					type = br.readLine();
					if (type == null) break;
					
					if (!(type.equals("f") || type.equals("v") || type.equals("p") || type.equals("m")))
					{
						System.out.println("Invalid item type in the file, aborting the file reading process ");
						break;
					}
					
					code = Integer.parseInt(br.readLine());
					
					FoodItem dummy = new FoodItem(code, "", 0.0f, 0, 0.0f);
					if (alreadyExists(dummy) != -1)
					{
						System.out.println("Duplicate item in the file, aborting the file reading process ");
						break;
					}
					
					name = br.readLine();
					
					quantity = Integer.parseInt(br.readLine());
					price = Float.parseFloat(br.readLine());
					cost = Float.parseFloat(br.readLine());
						
					switch(type)
					{
						case "f":
							String farmName = br.readLine();
							newItem = new Fruit(code, name, price, quantity, cost, farmName);
							break;
						case "v":
							String supplierName = br.readLine();
							newItem = new Vegetable(code, name, price, quantity, cost, supplierName);
							break;
						case "p":
							 int preserveSize =  Integer.parseInt(br.readLine());
							newItem = new Preserve(code, name, price, quantity, cost, preserveSize);
							break;
						
						case "m":
							int milkSize = Integer.parseInt(br.readLine());
							newItem = new Milk(code, name, price, quantity, cost, milkSize);	
					}
					
					arrayItems.add(newItem);
					
					int i;
					FoodItem temp;
					for(i = arrayItems.size() - 1; i > 0; i--) {
						if(arrayItems.get(i).compareTo(arrayItems.get(i - 1)) < 0)
						{
							temp = arrayItems.get(i);
							arrayItems.set(i, arrayItems.get(i - 1));
							arrayItems.set(i - 1, temp);			
						}
						else break;
					}

				}
				
				br.close();
			}catch (FileNotFoundException e) {
				System.out.println("File not found: " + e.getMessage());
			}catch (IOException e ) {
				System.out.println("Error reading file: " + e.getMessage());
			}catch(NumberFormatException e) {
				System.out.println("Invalid Data Type, aborting the file reading process");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
}
	
	