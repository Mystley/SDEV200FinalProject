import java.util.*;
import javax.swing.*;
public class ItemsDonationsTest // equivalent of the Main class
{
    private static int userChoice; // make sure this is instantiated in the Main program at the same location.
    private static String userSelect;
    private static LinkedList<String> donations = new LinkedList<String>();
    private static final int QUIT = 999;
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 100;
    private static Scanner kb = new Scanner(System.in);
    public static void main(String[] args)
    {
        

        for(int d = 0; d < ItemsDonation.neededItems.length; ++ d)
        {
            System.out.println(d + 1 + " - " + ItemsDonation.neededItems[d]);
        }

        
        while (userChoice != QUIT)
        {
            System.out.println("At any time, enter 999 to stop donating");
            makeDonation();
            ItemsDonation item = new ItemsDonation(userSelect);
            item.thankDonator();
        }
        JFrame aFrame = new JFrame("Donations information");
        aFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        aFrame.setVisible(true);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel info = new JLabel(donations.toString() + " were donated");
        aFrame.add(info);
        showDonations(donations);

    }
    public static String makeDonation()
    {
        try
        {
            System.out.print("Enter the number of one item you would like to donate >> ");
            userChoice = kb.nextInt();
            userSelect = ItemsDonation.neededItems[userChoice - 1];
            donations.add(userSelect);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            if(userChoice != QUIT)
            {
                System.out.println("Please make sure to choose from 1 to " + ItemsDonation.neededItems.length);
                makeDonation();
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Please make sure to enter numbers, not letters.");
            kb.nextLine();
            makeDonation();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return userSelect;
    }
    public static void showDonations(LinkedList<String> donations)
    {
        System.out.println(donations);
    }
}
//Program was good at this point before last modifications.