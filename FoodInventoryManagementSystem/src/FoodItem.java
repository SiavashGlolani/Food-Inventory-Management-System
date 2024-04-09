import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.*;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;


public class FoodItem implements Comparable<FoodItem> {

	protected int itemCode;
	protected String itemName;
	protected float itemPrice;
	protected int itemQuantityInStock;
	protected float itemCost;
	
	// Default constructor, initializes all member variables to their default values
	FoodItem(){
		
		itemCode = 0;
		itemName = "";
		itemPrice = 0;
		itemQuantityInStock = 0;
		itemCost = 0;	
	}
	
	// Parameterized constructor, initializes all member variables to passed in values
	FoodItem(int code, String n, float price, int stock, float cost) {
		
		itemCode = code;
		itemName = n;
		itemPrice = price;
		itemQuantityInStock = stock;
		itemCost = cost;		
	}
	
	// Returns a formatted string representing the FoodItem
	public String toString() {
		String item = String.format("Item: %d %s %d price: $%.2f cost: $%.2f", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
		return item;
	}
	
	/**
	 * Update the quantity of the item
	 * @param amount the quantity to be added (if positive) or removed (if negative)
	 * @return true if the operation was successful, false otherwise
	 */
	public boolean updateItem(int amount) {
		int quantity = itemQuantityInStock + amount;
		if(quantity >= 0) 
		{
			itemQuantityInStock = quantity;
			return true;
		}
		return false;	
	}
	
	
	/**
	 * Add a new item using the given scanner object for user input
	 * @param scanner the Scanner object for user input
	 * @return true if the item is added successfully, false otherwise
	 */
	public boolean addItem(Scanner scanner) {
		boolean isValid = false;
		
		do {
			System.out.print("Enter the name for the item: ");
			if(scanner.hasNextLine())
			{
				itemName = scanner.nextLine();
				if (itemName.equals("")) 
				{
					itemName = scanner.nextLine();
				}
				isValid = true;
				/*if(itemName.matches("^[a-zA-Z]*$"))
				{
					isValid = true;
				}*/
			}
			else 
			{
				System.out.println("Invalid entry");
				isValid = false;
			}	
		}while(!isValid);
		
		
		do {
			System.out.print("Enter the quantity for the item: ");
			if(scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				isValid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				isValid = false;
			}
		} while(!isValid);
		
		do {
			System.out.print("Enter the cost of the item: ");
			if(scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				isValid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				isValid = false;
			}
		} while(!isValid);
		
		do {
			System.out.print("Enter the sales price of the item: ");
			if(scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				isValid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				isValid = false;
			}
		} while(!isValid);
		
		return true;
	}
	
	/**
	 * Read the item code from the user input using the provided scanner object
	 * @param scanner the Scanner object for user input
	 * @return true if the code is entered successfully, false otherwise
	 */
	public boolean inputCode(Scanner scanner)
	{
		boolean valid = false;
		String dummy = "";
		
		do
		{
			valid = true;
			try {
				
				System.out.print("Enter the code for the item: ");
				dummy = scanner.nextLine();
				if (dummy.equals("")) 
				{
					dummy = scanner.nextLine();
				}
				itemCode = Integer.parseInt(dummy);
			}catch(NumberFormatException e) {
				System.out.println("Invalid Entry");
				valid = false;
			}
		}
		while(!valid);
		
		return true;
	}
	
	/**
	 * Checks if this FoodItem is equal to another based on their itemCode
	 * @param f the other FoodItem to compare with
	 * @return true if the itemCodes are equal, false otherwise
	 */
	public boolean isEqual(FoodItem f)
	{
		return itemCode == f.itemCode;
	}
	
	
	/*
	 * @return itemCode
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	// Returns a formatted string representing the FoodItem
	public String outputItem() {
		return String.format("%d\n%s\n%d\n%.2f\n%.2f", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice);
	}

	/*
	 * This method is used to compare the current FoodItem to another FoodItem based on their itemCode.
	 * The method is part of the Comparable interface that FoodItem class implements.
	 * The result can be used to sort FoodItem objects.
	 * @param f The FoodItem that the current FoodItem is to be compared with.
	 * @return A negative integer if this itemCode is less than the argument's itemCode.
	 * Zero if this itemCode is equal to the argument's itemCode.
	 * A positive integer if this itemCode is greater than the argument's itemCode. 
	 */
	@Override
	public int compareTo(FoodItem f) {
		return this.itemCode - f.itemCode;
	}
}	
