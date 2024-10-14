

public class Order
{
    private String sizeString;
    public static int orderIndex = 0; // private makes it not visible in Main.java
    public static int priceIndex = 0;
    String nameOfSandwich;
    String sandwichName;
    public static final double TAX_RATE = 0.07;
    private static double sandwichPrice;
    private static double sizePrice;
    static double orderPrice;
    public static double wholePrice = 0;
    public static boolean inMenu = false;

    final static int MAX_ORDER = 3;
    static String [] ordersList = new String[MAX_ORDER];
    static double [] pricesList = new double[MAX_ORDER];



    public Order()
    {
        //orderNum = orderNum + 1;
    }

    public void setSandwichName(String sandwich)
    {
        for(int i = 0; i < Food.FoodObjectsList.length; ++i)
        {
            if(sandwich.equalsIgnoreCase(Food.FoodObjectsList[i].name))
            {
                inMenu = true;
                nameOfSandwich = sandwich;
                //setSandwichPrice();
            }
            if((i == Food.FoodObjectsList.length - 1) && inMenu == false)
            {
                System.out.println("We don't have this in our menu, please try again");
                //Main.keyboard.nextLine();
                Main.makeOrder();
            }
        }
    }
    public String getSandwichName()
    {
        return nameOfSandwich;
    }
    public void setSandwichSize(char size)
    {
        char small = 'S';
		char medium = 'M';
		if (size == small)
		{
			sizeString = "Small";
            sizePrice = 2.5;
		}
		else if (size == medium)
		{
			sizeString = "Medium";
            sizePrice = 5.5;
		}
		else
		{
			sizeString = "Large";
            sizePrice = 10;
		}
    }
    public String getSandwichSize()
    {
        return sizeString;
    }
    public void setSandwichPrice()
    {
        for(int p = 0; p < Food.FoodObjectsList.length; ++p) //keeps looping until it finds the one that matches the sandwich name the user entered
        {
            if (inMenu = true && Main.isCanceled == false)
            {
                if (nameOfSandwich.equalsIgnoreCase(Food.FoodObjectsList[p].getFoodName())) // if the name user entered matches one of the sandwich in the menu
                {
                    //inMenu = true;
                    sandwichPrice = Food.FoodObjectsList[p].getSandwichPrice(); // assigns the price of that sandwich to the variable sandwichPrice
                }
            }
        }
        if(Main.isCanceled == true)
        {
            //System.out.println("Order was canceled");
            sandwichPrice = 0;
        }
    }
    public static double getSandwichPrice()
    {
        return sandwichPrice;
    }
    public void showPriceCalculations()
    {
        System.out.printf("%10s", nameOfSandwich);
        System.out.printf("%15.2f\n", getSandwichPrice());
        System.out.printf("%10s", getSandwichSize());
        System.out.printf("%15.2f\n", sizePrice);
    }
    public static double getTotalPrice()
    {
        
        for (int i = 0; i < MAX_ORDER; ++i)
        {
            wholePrice += pricesList[i];
        }
        if(Main.isCanceled == false)
        {
            orderPrice = wholePrice + (wholePrice * TAX_RATE);
            //System.out.println("orderPrice assigned price");
        }
        
        
        

        /*
        orderPrice = (((orderPrice * 100) + 0.5))/100; // eliminate imprecision
        orderPrice = Math.ceil(orderPrice); // round up to the nearest integer.
        */
        return orderPrice;
    }

    public static void setOrderNum()
    {
        String numOfOrder = Integer.toString(orderIndex + 1); // orderNum = 0; orderNum + 1 = 1;
        ordersList[orderIndex] = "\nORDER " + numOfOrder; // OrderList[0] = "Order 1"
        System.out.println(ordersList[orderIndex]);
        orderIndex++;
    }
    public static void setNameSizePrice()
    {
        pricesList[priceIndex] = getSandwichPrice() + sizePrice;
        priceIndex++;
    }
    public double getNameSizePrice()
    {
        return pricesList[priceIndex];
    }
}
// Undo edit limit