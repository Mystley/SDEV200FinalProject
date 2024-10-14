// filename: Main.java
// Created by Padley Perard on 09/12/2024
// Modified on: 09/26/2024
// Description: The main food ordering program where the user place an order and pay.
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.util.*;
public class Main
{
    public static Scanner keyboard = new Scanner(System.in);
    static Order anOrder = new Order();
    static boolean isValid = false;
    static boolean isCanceled = false;
    
    public static void main(String[] args)
    {
        char anotherOrder;
        final int MAX = 2;

        try
        {
            Food.getMenu();

            // Take the order from the customer
            Order.setOrderNum(); // Labeling the order
            makeOrder();
            Order.setNameSizePrice();

            System.out.print("\nPlacing another order? (\'Y\' for yes; \'N\' for no): ");
            anotherOrder = keyboard.next().charAt(0);
            while (anotherOrder == 'Y' || anotherOrder == 'y')
            {
                keyboard.nextLine();
                Order.setOrderNum();
                makeOrder();
                Order.setNameSizePrice();
                                
                System.out.print("\nPlacing another order? (\'Y\' for yes; \'N\' for no): ");
                anotherOrder = keyboard.next().charAt(0);
                //keyboard.nextLine();
            }
            if (anotherOrder == 'N' || anotherOrder == 'n')
            {
                System.out.println("We got everything!");
                keyboard.nextLine();
                makePayment();
            }
            newspaper();
            keyboard.close();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Sorry you cannot add more than " + (MAX + 1) + " orders under one name.");
            makePayment();
        }
    }
    public static void makeOrder()
    /*This method allows the user to select the sandwich they want by entering the name.
    If the name is found in the menu, then the user is asked for the size. All information asked
    from the user are processed in the Order class.*/
    {
        String sandwichName;
        char sandwichSize;
        
        if(isCanceled == true)
        {
            anOrder.setSandwichPrice();
        }
        isCanceled = false;
        System.out.print("Enter the name of the sandwich you want >> ");
        sandwichName = keyboard.nextLine();
        anOrder.setSandwichName(sandwichName);
        if(Order.inMenu == true)
        {
            System.out.print("Enter the size of your sandwich ('S', 'M', 'L') >> ");
            sandwichSize = keyboard.next().charAt(0);
            anOrder.setSandwichSize(sandwichSize);
            if(Main.isCanceled == false)
            {
                anOrder.setSandwichPrice();
            }
            anOrder.showPriceCalculations();
        }
        Order.inMenu = false;
    } // end of makeOrder()
    public static void makePayment()
    /*This method is to process the payment of the order made by the user.
     * It summarize the order for the customer and calculate the total price including tax.
     * Next, it ask the user if they are ready to pay (or if all the information about the order
     * look correct)
     * if they say yes, they go to the next step: make a payment
     * if they say no, they are asked what do they want to do with the order:
     *      cancel: where the previous order is eliminated and the whole program restarts
     *      pay: where they proceed to the next step: make a payment
     */
    {
        char payConfirm;
        char payMoCa;
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < Order.ordersList.length; ++ i)
        {
            if(Order.ordersList[i] != null)
            {
                System.out.println(Order.ordersList[i]);
                System.out.printf("%.2f\n", Order.pricesList[i]);
            }
        }

        System.out.printf("%10s", "Tax");
        System.out.printf("%15.2f\n", Order.TAX_RATE);
        System.out.printf("%10s", "TOTAL");
        System.out.printf("%15.2f\n", Order.getTotalPrice());

        System.out.print("\nReady to pay? (\'Y\' for yes; \'N\' for no): ");
        payConfirm = keyboard.next().charAt(0);
        if (payConfirm == 'Y' || payConfirm == 'y')
        {
            cardValidation();
            pay(isValid);
            // proceed with the payment code: take card info/ cash amount, calculate the change if any and thanks the customer for the order 
        }
        else if (payConfirm == 'N' || payConfirm == 'n')
        {
            System.out.println("You want to cancel (\'C\') the order or you want to proceed to payment (\'P\')?: ");
            payMoCa = keyboard.next().charAt(0);
            
            switch (payMoCa)
            {
                case 'P', 'p':
                    makePayment();
                    break;
                case 'C', 'c': 
                    Order.orderIndex = 0;
                    Order.priceIndex = 0;
                    for (int i = 0; i < Order.ordersList.length; ++ i)
                    {
                        Order.ordersList[i] = null;
                        Order.pricesList[i] = 0;
                    }
                    isCanceled = true;
                    Order.wholePrice = 0;
                    keyboard.nextLine();
                    Main.main(null);
                    break;
                default:
                System.out.println("\n Sorry, I didn't get that, please try again");
                System.out.print("You want to cancel (\'C\') the order or you want to proceed to payment (\'P\')?: ");
                payMoCa = keyboard.next().charAt(0);
            }            
        }        
        keyboard.close();
    } // end of makePayment()
    public static boolean cardValidation()
    /* This method have the user enter a card number and determines if it is a valid card number or no.
     * To do this, the method use the Luhn's algorithm. A good resources that explains this algorithm is: https://www.youtube.com/watch?v=wsphC8V36i0
     * I have commented out the codes that print the steps of the algorithm as they are irrelevant for the user to see the process.
     */
    {
        Scanner keyboard = new Scanner(System.in);
        int totalOddPlace = 0;
        int x, y;
        int digitDouble = 0;
        int totalEvenPlaceDoubled = 0;
        
        String digitDoubleS = Integer.toString(digitDouble);
        
        try
        {
            System.out.print("Enter a valid card number >> ");
            String numbers = keyboard.nextLine();                     // "  4012 9879 "
            numbers = numbers.strip();                                // "4012 9879"
            numbers = numbers.replaceAll(" ", "");  // "40129879"
            long [] cardDigits = new long[numbers.length()];                      

            for(int i = 0; i < numbers.length(); ++i) // Gather the digits of the card    
            {
                cardDigits[i] = Integer.parseInt(numbers.substring(i, i+1));
                //System.out.print(cardDigits[i]);
            }
            System.out.println();
            for (int u = numbers.length() - 1; u >= 0; u-=2) // Gather the digits in the odd places and add them together
            {
                //System.out.println(cardDigits[u]);
                totalOddPlace += cardDigits[u];

            }
            //System.out.println("= " + totalOddPlace); // Sum of odd places
            //System.out.println();

            for(int u = numbers.length() - 2; u >= 0; u-=2) // Gather the digits in the even place, double them and add their doubles together
            {
            // System.out.println(cardDigits[u]);
                digitDouble = (int) cardDigits[u] * 2;
                if(digitDouble >= 10) // in the case that the double result to a 2 digits number, add their digits and use that new digit. 9 * 2 = 18; 1 + 8 = 9;
                {
                    digitDoubleS = Integer.toString(digitDouble);
                    x = Integer.parseInt(digitDoubleS.substring(0,1));
                    y = Integer.parseInt(digitDoubleS.substring(1, 2));
                    digitDouble = x + y;
                }
                //System.out.println(digitDouble);            
                totalEvenPlaceDoubled += digitDouble;
            }
        
            //System.out.println("= " + totalEvenPlaceDoubled); // sum of double of digits in even places
            int totalsum = totalEvenPlaceDoubled + totalOddPlace;
            if (((totalsum % 10) == 0) && totalsum != 0)
            {
                isValid = true;
                //System.out.println("card is valid");
            }
            else
            {
                System.out.println("Non valid card");
                cardValidation();
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong, please try again");
            cardValidation();
        }
        keyboard.close();
        return isValid;
    }
    public static void pay(boolean cardValid)
    {
        if (cardValid == true && Order.orderPrice != 0)
        {
            System.out.print("A total of $ ");
            System.out.printf("%.2f", Order.orderPrice);
            System.out.println(" has been deducted from your card.\nThanks for your order! Come see us again!");
        }
        
            
    }
    public static void newspaper()
    {
        Path file = Paths.get("C:\\Users\\perar\\OneDrive\\Documents\\Ivy Tech\\Classes\\SDEV\\SDEV 200\\finalProject\\newspaper.txt");
        String s1 = String.format("%.2f", Order.orderPrice);
        String s = "Harendy's is growing! Their last order was of $ " + s1;
        byte[] data = s.getBytes();
        OutputStream output = null;
        try
        {
            output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            output.write(data);
            output.flush();
            output.close();
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }
    }
    
    /* THIS IS A TEST ZONE. The line of codes below are under development and are subject to frequent modifications.
     * If there should be a problem arising by the effect of these code, it is here separated and isolated by this comment
     * to easily recognize and handle it.
     */
}
// Edit undo limit