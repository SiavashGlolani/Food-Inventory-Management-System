import java.util.Scanner;
import java.util.*;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;



public class Assign2 {

	


		
		private static final int ADD = 1;
		private static final int DISPLAY = 2;
		private static final int BUY = 3;
		private static final int SELL = 4;
		private static final int SEARCH = 5;
		private static final int SAVE = 6;
		private static final int READ = 7;
		private static final int EXIT = 8;
		
		/**
		 * Displays the main menu of the program.
		 */
		public static void displayMenu()
		{
			System.out.println("Please select one of the following:");
			System.out.println(ADD + ": Add item to inventory");
			System.out.println(DISPLAY + ": Display item to inventory");
			System.out.println(BUY + ": Buy item(s)");
			System.out.println(SELL + ": Sell item(s)");
			System.out.println(SEARCH + ": Search for Item");
			System.out.println(SAVE + ": Save Inventory to File");
			System.out.println(READ + ": Read Inventory from File");
			System.out.println(EXIT + ": To Exit");
		}
		
		/**
		 * The main method of the program.
		 * It manages the main loop of the program which includes displaying menu, 
		 * getting user input, and performing corresponding operations.
		 *
		 * @param args The command-line arguments for the program.
		 */
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			int input = 0;
			Inventory inv = new Inventory();
				
			do {
				
				boolean valid = false;
				String dummy = "";
				
				do
				{
					valid = true;
					try {
						displayMenu();
						dummy = scan.nextLine();
						if (dummy.equals("")) dummy = scan.nextLine();
						input = Integer.parseInt(dummy);
					}catch(NumberFormatException e) {
						System.out.println("Invalid Entry");
						valid = false;
					}
				}
				while(!valid);
				
				switch(input) {
				case ADD:
					inv.addItem(scan);
					break;
					
				case DISPLAY:
					System.out.println(inv.toString());
					break;
				
				case BUY:
					inv.updateQuantity(scan, true);
					break;
					
				case SELL:
					inv.updateQuantity(scan, false);
					break;
					
				case SEARCH:
					inv.searchForItem(scan);
					break;
					
				case SAVE:
					inv.saveToFile(scan);
					break;
					
				case READ:
					inv.readItemFile(scan);
					break;
					
				case EXIT:
					System.out.println("Exiting...");
					break;
					
				default:
					System.out.println("invalid input...please try again...");
				}
				
			}while(input != EXIT);
			
			scan.close();

		}

	}



