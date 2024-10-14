// Filename: Food.java
// Written by Padley Perard on 09/10/2024
// Description: Food class where food objects will be instantiated from.

import java.util.Scanner;

public class Food
{
	public String name;
	private String bread;
	private String meat;
	private String sauce;
	private String condiment;
	// private String size;
	private double sandwichPrice = 0;

	Scanner keyboard = new Scanner(System.in);

	static Food FoodObjectsList [] = new Food[3];
	
	public Food(String foodName, String breadStyle, String meat, String sauce, double price)
	{
		name = foodName;
		bread = breadStyle;
		this.meat = meat;
		this.sauce = sauce;
		this.sandwichPrice = price;
	}
	private static void setMenu()
	{
		FoodObjectsList[0] = new Food("We Big", "baguette", "beef", "spicy", 32.35);
        FoodObjectsList[0].setCondiments("tomatoes", "zuchinis");
        FoodObjectsList[1] = new Food("Delicio", "burger", "turkey", "barbecue", 35.45);
        FoodObjectsList[1].setCondiments("pickles", "onions", "mushrooms");
		FoodObjectsList[2] = new Food("Turich", "burger", "chicken", "alfredo", 30.52);
		FoodObjectsList[2].setCondiments("onions", "jalapeño", "ranch");
	}
	public static Food[] getMenu()
	{
		// Welcome the user to the restaurant
		System.out.println("Welcome to Harendy's!");
		System.out.println("Here is our brand new menu\n");
		
		setMenu();
		for(int u = 0; u < FoodObjectsList.length; ++u)
		{
			FoodObjectsList[u].showFood();
			System.out.println();
		}
		return FoodObjectsList;
	}
	public void showFood()
	{
		System.out.println(name + " $" + sandwichPrice);
		System.out.println("Combination of a well cooked " + meat + " covered of " + sauce + " sauce, extended with some " + getCondiments() + ". Coming in a " + bread + " type of bread.");
	}
	public void setCondiments(String cond1, String cond2)
	{
		condiment = cond1 + " and " + cond2;
	}
	public void setCondiments(String cond1, String cond2, String cond3)
	{
		condiment = cond1 + ", " + cond2 + " and " + cond3;
	}
	public String getCondiments()
	{
		return condiment;
	}
	public double getSandwichPrice()
	{
		return sandwichPrice;
	}
	public String getFoodName()
	{
		return name;
	}

/*
	public void setName(String foodName)
	{
		name = foodName;
	}
	public void setBreadTypes(String breadType)
	{
		bread = breadType;
	}
	public void setSize(char size)
	{
	}
	public void setIngredients(String meatchoice, String sauceChoice, String condimentChoice)
	{
		meat = meatchoice;
		sauce = sauceChoice;
		condiment = condimentChoice;
		String[] ingredients = {meat, sauce, condiment};
		allIngredients = ingredients.toString();

	}
	public void setPrice(double foodPrice)
	{
		price = foodPrice;
	}

	public String getName()
	{
		return name;
	}
	public String getBreadTypes()
	{
		return bread;
	}
	public String getSize()
	{
		return size;
	}
	public String getIngredients()
	{
		return allIngredients;
	}
	public double getPrice()
	{
		return price;
	}
*/

}
