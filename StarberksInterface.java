/*
 * Author: Cheng Zixin
 * Start date: 1 April 2016
 * End date: 19 Apr 2016
 * Description: The interface class to evaluate public methods of store class, 
 *              and receive all input from the user, display all output,
 *              and check for invalid inputs & display all error messages
 */
import java.util.Scanner;
public class StarberksInterface 
{
	// Claim new variables
    static Store store = new Store(); // Create a new store instance variable
    static Scanner scanner = new Scanner(System.in); // Create a new scanner
    
    // Variables for product
    static String name; 
    static int demandRate;
	static double setupCost;
	static double unitCost;
	static double inventoryCost;
	static double sellingPrice;	
	
	// Variables for calculation
	static double profit1; // A variable to store profit for record 1
	static double profit2; // A variable to store profit for record 2
	static double profit3; // A variable to store profit for record 3
	static int week1 = 0; // Week number for product 1
	static int week2 = 0; // Week number for product 2
	static int week3 = 0; // Week number for product 3
	
	/*
	 * Main function 
	 * Precondition: none
	 * Postcondition: Flow of control finished
	 */
	public static void main(String[] args) 
	{
		int menuSelection = 0; // Menu selection for user
		displayMenu(); // Display the menu
	    menuSelection = inputMenuSelection(); // Ask user to select a function from the menu
	    
	    // Main control flow for this program
	    switch(menuSelection)
	    {
	    case 1:
		    inputProduct();
		    main(args); // Return to the main menu after action finished
		    break;
	    case 2:
	    	showProduct();
	    	main(args); 
		    break;
	    case 3:
	    	showReplenishmentStrategy();
	    	main(args);
		    break;
	    case 4:
	    	showProfit();
		    break;
	    default:
		    System.out.println("Error: Please type in an integer from 1 to 4");
		    main(args);   
		} // Display menu after one action finished until user choose to exit the program.
	}
	
	public void run()
	{
		StarberksInterface intFace = new StarberksInterface();
        intFace.run();
	}
	
	/*
	 * Input menu selection, return selection
	 * Precondition: none
	 * Postcondition: Menu selection that selected by user had been stored
	 */
	private static int inputMenuSelection()
	{	
		int selection = scanner.nextInt();
		return selection;
	}
	
	/* 
	 * Display main menu
	 * Precondition: none
	 * Postcondition: Menu displayed on screen
	 */
	private static void displayMenu()
 	{
 		System.out.println("------------MENU-----------");
 		System.out.println("(1) input data for one product");
 		System.out.println("(2) show data from one product");
 		System.out.println("(3) show the replenishment strategy for a product");
 		System.out.println("(4) exit program");
 		System.out.println("---------------------------");
 		System.out.println("Please type in an integer from 1 to 4");
 	}
	
	/*
	 * Start to input a product 
	 * Precondition: variable menuSelection is equals to 1
	 * Postcondition: product name stored
	 */
	private static void inputProduct()
	 { 
        int createModel = 1; // 1 means create a new product, 2 means modify a existing product
        int modifyStatus = 1; // 1 means provide a new name, 2 means modity the data (if error happened)
        
		// Code for collect product information
		// Collect product name and validate it.
		System.out.println("Please enter the product name: ");
		name = scanner.next().toLowerCase(); // Lower case required
		int validState = store.isValidName(name);	
		
		// If product name is invalid, ask user to try again.
		while (validState != 0)
		{
			if (validState == 1)
			    System.out.println("Invalid product name, please try again. (String with 3 <= length <= 10 )");
			if (validState == 2)
				System.out.println("Product already exist. \nEnter in 1 to provide a new name\nEnter in 2 to modify the data");
			modifyStatus = scanner.nextInt();
			if (modifyStatus == 1)
			{
				// Provide a new name
			    System.out.println("Please enter the product name: ");
			    name = scanner.next().toLowerCase();
				validState = store.isValidName(name);
			}
			else
			{
				// Modify a existing product
				inputData();
				createModel = 2;
				break;
			}	
		}
		if (modifyStatus ==1)
		    inputData();
		createProduct(createModel);
    }
    
	/*
	 * Input product data 
	 * Precondition: function displayMenu() excuted and a valid name had been stored
	 * Postcondition: product data stored
	 */
	private static void inputData()
	{	
		boolean isValidNumber = false;		
		// Collect demand rate and validate it
		System.out.println("Please enter the demand rate: ");
		demandRate = scanner.nextInt();
		isValidNumber = store.isValidNumber(demandRate);
		// If demand rate is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid demand rate, please try again (Non-negative integer)");
			System.out.println("Please enter the demand rate: ");
			demandRate = scanner.nextInt();
			isValidNumber = store.isValidNumber(demandRate);	
		}
			
		// Collect setup cost and validate it
		System.out.println("Please enter the setup cost: ");
		setupCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If setup cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid setup cost, please try again (Non-negative integer)");
		    System.out.println("Please enter the setup cost: ");
			setupCost = scanner.nextDouble();
			isValidNumber = store.isValidNumber(setupCost);
		}

		//Collect unit cost and validate it
		System.out.println("Please enter the unit cost: ");
		unitCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If unit cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid unit cost, please try again (Non-negative integer)");
			System.out.println("Please enter the unit cost: ");
		    unitCost = scanner.nextDouble();
		   	isValidNumber = store.isValidNumber(unitCost);
		}	
			
		// Collect inventory cost and validate it
		System.out.println("Please enter the inventory cost: ");
		inventoryCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If inventory cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid inventory cost, please try again (Non-negative integer)");
			System.out.println("Please enter the inventory cost: ");
			inventoryCost = scanner.nextDouble();
			isValidNumber = store.isValidNumber(inventoryCost);
		}
		
		// Collect selling price and validate it
		System.out.println("Please enter the selling price: ");
		sellingPrice = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If selling price is invalid, ask user to try again.
		while (isValidNumber == false)
		{
			System.out.println("Invalid selling price, please try again (Non-negative integer)");
			System.out.println("Please enter the selling price: ");
			sellingPrice = scanner.nextDouble();
			isValidNumber = store.isValidNumber(setupCost);
		}
	}
    
	/*
	 * Create new product
	 * Ask for input model, 1 means create a new record, 2 means modify existing record
	 * Precondition: function inputProduct() excuted and valid product data had been stored
	 * Postcondition: product created, 
	 *                or a product record had been removed (when the space is full and user ask to delete), 
	 *                or a product had been modified (when product name already exist in the database).
	 */
	private static void createProduct(int createModel)
	{
		// Create a new product
		if (createModel == 1)
		{
			int recordNumber = store.createProduct(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
			if (recordNumber == 0)
			{
				System.out.println("There is no more room in the product database.");
				System.out.println("Do you wish to remove a specific product record?");
				System.out.println("Enter in 1 to remove \nEnter in 2 to go back to main menu ");
				int deleteStatus = scanner.nextInt(); // Whether user want to delete a record
				if (deleteStatus == 1)
				{
					System.out.println("Which record do you want to remove");
					String deleteRecord = scanner.next(); // Which record do user want to delete
					int deleteNumber = store.selectProduct(deleteRecord);
					while (deleteNumber == 0) // If user enter a record that is not exist in the database
					{
						System.out.println("Record not found, please try again, or enter Q to return back to main menu");
						deleteRecord = scanner.next();
						if (deleteRecord == "Q" || deleteRecord == "q") // return back to main menu
							main(null);
						else
						    deleteNumber = store.selectProduct(deleteRecord);
					}
					store.removeRecord(deleteNumber);
				}
				else
					main(null); // Return back to main menu
			}
		}
		// Modify existing record
		else
		{
			store.modifyRecord(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
		}
		
	}
	
	/*
	 *  Ask user which product need to show and send product name to store.showProduct(), 
	 *  then receive product number and show product data
	 *  Precondition: variable menuSelection is equals to 2
	 *  Postcondition: Product data shows on the screen,
	 *                 or error message shows on the screen (Product not found, or no product record in database).
	 */
	private static void showProduct()
	{
		if (store.getName(1) == null && store.getName(2) == null && store.getName(3) == null)
			System.out.println("No data in the database");
		else
		{
		    System.out.println("Enter the product name: ");
		    String name = scanner.next().toLowerCase(); // Variable name: product name that need to check
		    int productNumber = store.selectProduct(name);
		    while (productNumber == 0)
		    {
		    	System.out.println("Product not found, please try again");
		    	System.out.println("Enter the product name: ");
			    name = scanner.next().toLowerCase(); // Variable name: product name that need to check
			    productNumber = store.selectProduct(name);
		    }
		    // Show the output
    	    System.out.println("Product name: " + store.getName(productNumber));
	   	    System.out.println("Demand rate: " +  store.getDemandRate(productNumber));
	        System.out.println("Setup cost: " +  store.getSetupCost(productNumber));
	   	    System.out.println("Unit cost: " +  store.getUnitCost(productNumber));
            System.out.println("Inventory cost: " +  store.getInventoryCost(productNumber));
		    System.out.println("Selling price: " +  store.getSellingPrice(productNumber));
	        System.out.println("------------------------------");
		    
		}
	}
    
	/*
	 * Ask user the product which need to provide replenishment strategy and show it
	 * Precondition: variable menuSelection is equals to 3
	 * Postcondition: Replenishment strategy shows on the screen,
	 *                or error message shows on the screen (Invalid week number, or no feasible replenishment strategy)
	 */
	private static void showReplenishmentStrategy()
	{
		if (store.getName(1) == null && store.getName(2) == null && store.getName(3) == null)
			System.out.println("No data in the database");
		else
		{
			System.out.println("Enter the product name: ");
		    String name = scanner.next().toLowerCase(); // Variable name: product name that need to check
		    int recordNumber = store.selectProduct(name);
		    while (recordNumber == 0)
		    {
		    	System.out.println("Product not found, please try again");
		    	System.out.println("Enter the product name: ");
			    name = scanner.next().toLowerCase(); // Variable name: product name that need to check
			    recordNumber = store.selectProduct(name);
		    }
		    System.out.println("Please enter the week number: ");
		    int week = scanner.nextInt();
		    // validate week number
		    while(week <=0)
		    {
		    	System.out.println("Week number should be more than or equals to 1 ");
		    	System.out.println("Please enter the week number: ");
			    week = scanner.nextInt();
		    }
		    // get required values of a specific product to generate replenishment strategy
		    int demandRate = store.getDemandRate (recordNumber);
		    double setupCost = store.getSetupCost(recordNumber);
		    double inventoryCost = store.getInventoryCost(recordNumber);
		    // Calculate and display the replenishment strategy
		    System.out.println("Week\tQuantity Order\tDemand\tInventory");
		    int EOQ = store.calculateEOQ(demandRate, setupCost, inventoryCost);
		    // If EOQ is less than demandRate, the replenishment strategy will be not available
		    if (EOQ < demandRate)
		    {
		    	System.out.println("Is not possible to have a replacement strategy with the inputs, please type in the data again.");
		    	createProduct(2);
		    }
		    for (int count = 1; count <= week; count++)
		    {
		    	store.calculateReplenishmentStrategy(count, demandRate, EOQ);
		    	int replenishQuantity = store.getReplenishQuantity();
		    	int inventory =store.getInventory();
		    	System.out.println(count + "\t" + replenishQuantity + "\t\t" + demandRate + "\t" + inventory);
		    }
		    // Store week number and get profit
		    if (recordNumber == 1 )
		    {
		    	week1 = week;
		    	profit1 = store.getProfit(recordNumber, week1);
		    }
		    else if (recordNumber == 2 )
		    {
		    	week2 = week;
		    	profit2 = store.getProfit(recordNumber, week2);
		    }
		    else
		    {
		    	week3 = week;
		    	profit3 = store.getProfit(recordNumber, week3);
		    }
		}
	}

	/*
	 * Show the product and its profit which has the highest profit
	 * If no product, exit the program;
	 * Precondition: variable menuSelection is equals to 4
	 * Postcondition: Exit program (when user never ask to show any replenishment strategy or no product data in database),
	 *                or show the product which has the highest profit value and its profit value (when user asked to display replenishment before)
	 */
	private static void showProfit()
	{
		if (week1 == 0 && week2 == 0 && week3 == 0)
			System.exit(0); //Exit the program
		else
		{
			// Show product with the highest profit
			if (week1 != 0 && week2 == 0 && week3 == 0)
				System.out.println("Product " + store.getName(1) + " has the highest profit: " + profit1);
			else if (week1 == 0 && week2 != 0 && week3 == 0)
				System.out.println("Product " + store.getName(2) + " has the highest profit: " + profit2);
			else if (week1 == 0 && week2 == 0 && week3 != 0)
				System.out.println("Product " + store.getName(3) + " has the highest profit: " + profit3);
			else if (week1 !=0 && week2 !=0 && week3 == 0)
			{
				if (profit1 >= profit2)
					System.out.println("Product " + store.getName(1) + " has the highest profit: " + profit1);
				else
					System.out.println("Product " + store.getName(2) + " has the highest profit: " + profit2);
			}
			else if (week1 != 0 && week2 == 0 && week3 != 0)
			{
				if (profit1 >= profit3)
					System.out.println("Product " + store.getName(1) + " has the highest profit: " + profit1);
				else
					System.out.println("Product " + store.getName(3) + " has the highest profit: " + profit3);
			}
			else if (week1 == 0 && week2 != 0 && week3 != 0)
			{
				if (profit2 >= profit3)
					System.out.println("Product " + store.getName(2) + " has the highest profit: " + profit2);
				else
					System.out.println("Product " + store.getName(3) + " has the highest profit: " + profit3);
			}
			else if (week1 != 0 && week2 != 0 && week3 != 0)
			{
				if (profit1 >= profit2 && profit1 >= profit3)
					System.out.println("Product " + store.getName(1) + " has the highest profit: " + profit1);
				else if (profit2 >= profit1 && profit2 >= profit3)
					System.out.println("Product " + store.getName(2) + " has the highest profit: " + profit2);
				else if (profit3 >= profit1 && profit3 >= profit2)
					System.out.println("Product " + store.getName(3) + " has the highest profit: " + profit3);
			}
		}
	}
}
