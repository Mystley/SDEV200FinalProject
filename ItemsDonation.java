public class ItemsDonation extends Donations
{
    public static String[] neededItems = {"Tooth paste", "Tooth brush", "Tissue", "Deodorant", "Shampoo", "Diaper", "Soap", "Body wash"};
    private String item;
    public ItemsDonation(String item)
    {
        this.item = item;
        usage(this.item);
    }
    @Override
    public void usage(String item)
    {
        System.out.println("This " + item + " is greatly appreciated. We'll use it to support kids with cancer");
    }
}
