import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Milk extends FoodItem{
	
	private int litreSize; // The size of the milk container in litres.
	
	
	/**
	 * Default constructor that initializes litreSize to 0.
	 */
	Milk(){
		
		litreSize = 0;
	}
	

	/**
	 * Constructor with parameters that initializes all fields of the milk including inherited fields from FoodItem.
	 *
	 * @param code The item code.
	 * @param n The name of the milk.
	 * @param price The price of the milk.
	 * @param stock The stock quantity of the milk.
	 * @param cost The cost of the milk.
	 * @param size The size of the milk container in litres.
	 */
	Milk(int code, String n, float price, int stock, float cost, int size){
		super(code, n, price, stock, cost);
		litreSize = size;
	}
	
	/**
	 * Overrides toString() in FoodItem class.
	 * Returns a string representation of the milk item including its litre size.
	 *
	 * @return The string representation of the milk.
	 */
	@Override 
	public String toString() {
		String milk = super.toString();
		String Milk = String.format("%s litres: %s", milk, litreSize);
		return Milk;
	}
	
	/**
	 * Overrides addItem() in FoodItem class.
	 * Adds a new milk item to the inventory.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the milk item is successfully added.
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
				litreSize = Integer.parseInt(dummy);
				
			}catch(NumberFormatException e) {
				System.out.println("Invalid Entry");
				valid = false;
			}
		}
		while(!valid);
			
		/*do {
			valid = true;
			System.out.print("Enter the size of the container in litres: ");
			if(scanner.hasNextInt()) 
			{
				litreSize = scanner.nextInt();
			}
			else //if (dummy.equals(""))
			{
				System.out.println("Invalid entry");
				dummy = scanner.nextLine();
				System.out.printf("Dummy = %s %d\n ", dummy, dummy.length());
				valid = false;
			}
		}while(!valid);*/
		
		return true;
		
	}
	
	/*
	 * Overrides outputItem() in FoodItem class
	 * Returns a string representation of the milk item including its litre size.
	 */
	@Override
	public String outputItem() {
		return String.format("m\n%s\n%d", super.outputItem(), litreSize);	
	}

}
	
