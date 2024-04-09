import java.util.Formatter;
import java.util.Scanner;


public class Vegetable extends FoodItem {

	private String farmName; // Name of the farm that supplies this vegetable

	/**
	 * Default constructor that initializes farmName to an empty string.
	 */
	Vegetable(){
		
		farmName = "";
	}
	
	/**
	 * Constructor with parameters that initializes all fields of the vegetable including inherited fields from FoodItem.
	 *
	 * @param code The item code.
	 * @param n The name of the vegetable.
	 * @param price The price of the vegetable.
	 * @param stock The stock quantity of the vegetable.
	 * @param cost The cost of the vegetable.
	 * @param name The name of the farm supplier.
	 */
	
	Vegetable(int code, String n, float price, int stock, float cost, String name){
		super(code, n, price, stock, cost);
		farmName = name;
	}
	
	/**
	 * Overrides toString() in FoodItem class.
	 * Returns a string representation of the vegetable item including its farm supplier.
	 *
	 * @return The string representation of the vegetable.
	 */
	@Override
	public String toString() {
		String veg = super.toString();
		String Name = String.format("%s farm supplier: %s", veg, farmName); 
		return Name;
	}
	
	/**
	 * Overrides addItem() in FoodItem class.
	 * Adds a new vegetable item to the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the vegetable item is successfully added.
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		super.addItem(scanner);

		System.out.print("Enter the name of the farm supplier: ");
		if(scanner.hasNextLine()) 
		{
			farmName = scanner.nextLine();
			if(farmName.equals("")) 
			{
				farmName = scanner.nextLine();
			}
		}
		return true;
	}
	
	/*
	 * Overrides outputItem() in FoodItem class
	 * Returns a string representation of the vegetable item including its farm supplier.
	 */
	@Override
	public String outputItem() {
		return String.format("v\n%s\n%s", super.outputItem(), farmName);	
	}
	
	
}
