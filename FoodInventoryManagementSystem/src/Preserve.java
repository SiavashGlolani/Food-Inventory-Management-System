import java.util.Formatter;
import java.util.Scanner;


public class Preserve extends FoodItem{

	private int jarSize; // Size of the jar container for this preserve.
	
	/**
	 * Default constructor that initializes jarSize to zero.
	 */
	Preserve(){
		
		jarSize = 0;
	}
	
	/**
	 * Constructor with parameters that initializes all fields of the preserve including inherited fields from FoodItem.
	 *
	 * @param code The item code.
	 * @param n The name of the preserve.
	 * @param price The price of the preserve.
	 * @param stock The stock quantity of the preserve.
	 * @param cost The cost of the preserve.
	 * @param size The size of the jar in litres.
	 */
	
	Preserve(int code, String n, float price, int stock, float cost, int size){
		super(code, n, price, stock, cost);
		jarSize = size;
	}
	
	/**
	 * Overrides toString() in FoodItem class.
	 * Returns a string representation of the preserve item including its jar size.
	 *
	 * @return The string representation of the preserve.
	 */
	@Override
	public String toString() {
		String pres = super.toString();
		String Pres = String.format("%s size %s ", pres, jarSize);
		return Pres;
	}
	
	/**
	 * Overrides addItem() in FoodItem class.
	 * Adds a new preserve item to the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the preserve item is successfully added.
	 */	
	@Override
	public boolean addItem(Scanner scanner) {
		boolean valid = false;
		String dummy = "";
		
		super.addItem(scanner);
		
		do
		{
			valid = true;
			try {
				
				System.out.print("Enter the size of the container in litres: ");
				dummy = scanner.nextLine();
				if (dummy.equals("")) dummy = scanner.nextLine();
				jarSize = Integer.parseInt(dummy);
				
			}catch(NumberFormatException e) {
				System.out.println("Invalid Entry");
				valid = false;
			}
		}
		while(!valid);
		
		return true;
		
	}
	
	/*
	 * Overrides outputItem() in FoodItem class
	 * Returns a string representation of the preserve item including its jar size.
	 */
	@Override
	public String outputItem() {
		return String.format("p\n%s\n%d", super.toString(), jarSize);	
	}
	
}
