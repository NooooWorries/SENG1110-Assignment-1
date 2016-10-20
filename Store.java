/*
 * Author: Cheng Zixin
 * Start date: 1 April 2016
 * End date: 19 Apr 2016
 * Description: Accessing and modifying the data in a product object,
 *              and return product data back to interface class.
 */
public class Store 
{
	// Constructor
	public Store()
	{
		
	}
    // Claim 3 instance variables
	private Product product1 = new Product();
    private Product product2 = new Product();
    private Product product3 = new Product();
    private int replenishQuantity = 0;
    private int inventory = 0;
	int totalReplenishQuantity = 0;
	int replenishCount = 0;
	int totalInventory = 0;
    
 	/*
	 * Validate data (number)
	 * Precondition: user already type in a number
	 * Postcondition: return the result - whether the name is valid
	 */
 	public boolean isValidNumber (double number)
 	{
 		boolean isValid = false;
 		if (number >= 0)
 			isValid = true;
 		return isValid;
 	}
 	
 	/*
 	 * Validate data (name)
  	 * validStatus: 0 - valid name, 1 - invalid length, 2 - name clash
  	 * Precondition: user already type in a name
  	 * Postcondition: return the result - valid status
 	 */
  	public int isValidName (String name)
  	{
  		int validStatus = 0;
  		if (name.length() <=3 || name.length() >= 10)
             validStatus = 1;
  		if (name.equals(product1.getName())|| name.equals(product2.getName()) || name.equals(product3.getName()))
 			validStatus = 2;
  		return validStatus;
  	}
 	
  	/*
  	 *  Remove a specific product record
  	 *  Precondition: have a valid record number
  	 *  Postcondition: a specific product record becomes to null
  	 */
 	public void removeRecord(int record)
 	{
 		Product nullProduct = new Product();
 		// Change product value to null
 		if (record == 1)
 			product1 = nullProduct;
 		else if (record == 2)
 			product2 = nullProduct;
 		else if (record == 3)
 			product3 = nullProduct;
 	}
 	
 	/*
 	 * Check which product need to show and return back to interface class
 	 * 0 means not found
 	 * Precondition: have a valid name
 	 * Postcondition: return back product number based on product name
 	 */
 	public int selectProduct (String name)
 	{
 		int productNumber = 0;
 		if (name.equals(product1.getName()))
 			productNumber = 1;
 		else if (name.equals(product2.getName()))
 			productNumber = 2;
 		else if (name.equals(product3.getName()))
 			productNumber = 3;
 		return productNumber;
 	}
	
 	/*
 	 * Get name of a specific product
 	 * Precondition: have a record number
 	 * Postcondition: return back product name based on product number
 	 */
	public String getName(int recordNumber)
	{
		String name = null; // Initialize name
		if (recordNumber == 1)
			name = product1.getName();
		else if (recordNumber == 2)
			name = product2.getName();
		else if (recordNumber == 3)
			name = product3.getName();
		return name;
	}
	
	/*
	 * Get demand rate of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back demand rate based on product number
	 */
    public int getDemandRate (int recordNumber)
	{
	    int demandRate = 0;
	    if (recordNumber == 1)
			demandRate = product1.getDemandRate();
		else if (recordNumber == 2)
			demandRate = product2.getDemandRate();
		else if (recordNumber == 3)
			demandRate = product3.getDemandRate();
		return demandRate;
	}
	
    /*
	 * Get setup cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back setup cost based on product number
	 */
	public double getSetupCost (int recordNumber)
	{
		double setupCost = 0;
	    if (recordNumber == 1)
	    	setupCost = product1.getSetupCost();
		else if (recordNumber == 2)
			setupCost = product2.getSetupCost();
		else if (recordNumber == 3)
			setupCost = product3.getSetupCost();
		return setupCost;
	}
	
	/*
	 * Get unit cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back unit cost based on product number
	 */
	public double getUnitCost (int recordNumber)
	{
		double unitCost  = 0;
		if (recordNumber == 1)
			unitCost = product1.getUnitCost();
		else if (recordNumber == 2)
			unitCost = product2.getUnitCost();
		else if (recordNumber == 3)
			unitCost = product3.getUnitCost();
		return unitCost;
	}
	
	/*
	 * Get inventory cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back inventory cost based on product number
	 */
	public double getInventoryCost (int recordNumber)
	{
		double inventoryCost = 0;
	    if (recordNumber == 1)
	    	inventoryCost = product1.getInventoryCost();
		else if (recordNumber == 2)
			inventoryCost = product2.getInventoryCost();
		else if (recordNumber == 3)
			inventoryCost = product3.getInventoryCost();
		return inventoryCost;
	}
	
	/*
	 * Get selling price of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back selling price based on product number
	 */
	public double getSellingPrice (int recordNumber)
	{
		double sellingPrice = 0;
	    if (recordNumber == 1)
	    	sellingPrice = product1.getSellingPrice();
		else if (recordNumber == 2)
			sellingPrice = product2.getSellingPrice();
		else if (recordNumber == 3)
			sellingPrice = product3.getSellingPrice();
		return sellingPrice;
	}
	
	/*
	 * Create new product, return which record store the data
	 * Return 0 means no more space to store the product data
	 * Precondition: Have all valid data of a product
	 * Postcondition: a new product created and return back the record number,
	 *                or failed to create a product and return back 0
	 */
	public int createProduct (String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice)
	{
		int recordNumber = 0;
		if (getName(1) == null)
		{
            product1 = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);
            recordNumber = 1;
		}
		else if (getName(2) == null)
		{
			product2 = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);
			recordNumber = 2;
		}
		else if (getName(3) == null)
		{
			product3 = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);
			recordNumber = 3;
		}
		return recordNumber;
	}
	
	/*
	 * Modify an existing record
	 * Precondition: Have all valid data of a product
	 * Postcondition: the data of an existing product record had been changed based on the value of parameters
	 */
	public void modifyRecord (String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice)
	{
		int productNumber = selectProduct(name);
		if (productNumber == 1)
		{
			product1.setDemandRate(demandRate);
			product1.setSetupCost(setupCost);
			product1.setUnitCost(unitCost);
			product1.setInventoryCost(inventoryCost);
			product1.setSellingPrice(sellingPrice);
		}
		else if (productNumber == 2)
		{
			product2.setDemandRate(demandRate);
			product2.setSetupCost(setupCost);
			product2.setUnitCost(unitCost);
			product2.setInventoryCost(inventoryCost);
			product2.setSellingPrice(sellingPrice);
		}
		else
		{
			product3.setDemandRate(demandRate);
			product3.setSetupCost(setupCost);
			product3.setUnitCost(unitCost);
			product3.setInventoryCost(inventoryCost);
			product3.setSellingPrice(sellingPrice);
		}
	}

	/*
	 * Calculate and return round EOQ value
	 * Precondition: Have valid demand rate, setup cost and inventory cost
	 * Post condition: return back the EOQ value (round to a integer)
	 */
	public int calculateEOQ (double demandRate, double setupCost, double inventoryCost)
	{
		double EOQ = Math.sqrt(2 * setupCost * demandRate / inventoryCost);
		return (int) Math.round(EOQ);
	}
		
	/*
	 * Calculate replenish strategy for a specific week 
	 * Return the total replenishment quantity, then use this data to calculate profit.
	 * Precondition: function calculateEOQ() had already beed executed and get a valid EOQ number, 
	 *               and user already provided a valid week number
	 * Postconditions: Get the result of inventory, replenish count, replenish quantity, total inventory and total replenish quantity,
	 *                 and stored to the relevant variables
	 */
	public void calculateReplenishmentStrategy(int week, int demandRate, int EOQ)
	{
		inventory = EOQ; // Initial inventory value
        replenishCount = 0;
		for (int count = 1; count <= week; count ++)
		{
			if (inventory < demandRate)
			{
				replenishQuantity = EOQ / demandRate * demandRate - inventory;
				inventory = inventory + replenishQuantity - demandRate;	
				replenishCount ++;
			}
			else
			{
				inventory = inventory - demandRate;
				if (count == 1)
				{
					replenishQuantity = EOQ;
					replenishCount ++;
				}
				else
				    replenishQuantity = 0;
			}
			
		}
		totalReplenishQuantity += replenishQuantity;
		totalInventory += inventory;
	}
	
	/*
	 * Get replenish quantity of a specific product
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and get a valid value of replenish quantity
     * Postcondition: return back replenish quantity of a specific product
	 */
	public int getReplenishQuantity()
	{
		return replenishQuantity;
	}
	
	/*
	 * Get inventory of a specific product in a specific week
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and get a valid value of inventory
     * Postcondition: return back inventory of a specific product and a specific week
	 */
	public int getInventory()
	{
		return inventory;
	}
	
	/*
	 * Calculate profit value a specific product
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and user already provided a valid week number, 
     *               and the program provide a valid record number
     * Postcondition: return back the profit value of a specifc product based on the replenishment strategy
	 */
	public double getProfit(int recordNumber, int week)
	{
		double profit = 0;
		double cost = 0;
		if (recordNumber == 1)
		{
			cost = replenishCount * product1.getSetupCost() + totalReplenishQuantity * product1.getUnitCost() + totalInventory * product1.getInventoryCost();
			profit = (product1.getDemandRate() * week * product1.getSellingPrice()) - cost;
		}
		else if (recordNumber == 2)
		{
			cost = replenishCount * product2.getSetupCost() + totalReplenishQuantity * product2.getUnitCost() + totalInventory * product2.getInventoryCost();
			profit = (product2.getDemandRate() * week * product2.getSellingPrice()) - cost;
		}
		else
		{
			cost = replenishCount * product3.getSetupCost() + totalReplenishQuantity * product3.getUnitCost() + totalInventory * product3.getInventoryCost();
			profit = (product3.getDemandRate() * week * product3.getSellingPrice()) - cost;
		}
		return profit;
	}
}
