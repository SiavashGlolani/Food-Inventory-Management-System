import java.util.Formatter;
import java.util.Scanner;

public class Fruit extends FoodItem {
	
	private String orchardName; // The name of the orchard that supplies the fruit.
	
	
	/**
	 * Default constructor that initializes orchardName to an empty string.
	 */
	Fruit(){
		
		orchardName = "";
	}
	
	/**
	 * Constructor with parameters that initializes all fields of the fruit including inherited fields from FoodItem.
	 *
	 * @param code The item code.
	 * @param n The name of the fruit.
	 * @param price The price of the fruit.
	 * @param stock The stock quantity of the fruit.
	 * @param cost The cost of the fruit.
	 * @param name The name of the orchard supplier.
	 */
	Fruit(int code, String n, float price, int stock, float cost, String name){
		super(code, n, price, stock, cost);
		orchardName = name;
	}
	
	/**
	 * Overrides toString() in FoodItem class.
	 * Returns a string representation of the fruit item including its orchard name.
	 *
	 * @return The string representation of the fruit.
	 */
	@Override 
	public String toString() {
		String fruit = super.toString();
		String Fruit = String.format("%s orchard supplier %s", fruit, orchardName);
		return Fruit;
	}
	
	/**
	 * Overrides addItem() in FoodItem class.
	 * Adds a new fruit item to the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the fruit item is successfully added.
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		super.addItem(scanner);
		System.out.print("Enter the name of the orchard supplier: ");
		if(scanner.hasNextLine()) 
		{
			orchardName = scanner.nextLine();
			if (orchardName.equals("")) orchardName = scanner.nextLine();
		}
		return true;
	}
	
	
	/*
	 * Overrides outputItem() in FoodItem class
	 * 	Returns a string representation of the fruit item including its orchard name.
	 */
	@Override
	public String outputItem() {
		return String.format("f\n%s\n%s", super.outputItem(), orchardName);	
	}

}
