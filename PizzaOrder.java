import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class PizzaOrder
{
	public static void main(String [] args){		
		Scanner keyboard = new Scanner(System.in);
		boolean discount = false;
		char choice;
		String input;
		String[] orders = new String[10];
		int numOfOrders = 0;


		System.out.println("====================================");
		System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
		System.out.println("====================================");
		System.out.print("Today is: ");
		printCurrentDate();
		System.out.println();
		System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID)  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y'){
			discount = true;
		}
		if (choice == 'N' || choice == 'n'){
			discount = false;
		}

		orders[numOfOrders++] = orderPizza();
		previewOrder(orders , discount);


	    do {

			printMenu();

			input = keyboard.nextLine();
			choice = input.charAt(0);

			switch (choice) {


				case '1':
					confirmOrder(orders, discount);
					break;


				case '2':
					orders[numOfOrders++] = orderPizza();
					previewOrder(orders, discount);
					break;


				case '3':
					System.out.println("Good bye!");
					System.exit(0);

				default:
					System.exit(0);
			}
			if(choice == '1'){break;}
		}
		while (true);

	}




	public static void printMenu(){
		System.out.println("------------MENU-------------");
		System.out.println("1 - Complete");
		System.out.println("2 - Add another order");
		System.out.println("3 - Exit"); 
		System.out.print("> Choose one of the above (Enter a number): ");
	}


	public static String orderPizza(){ 
		Scanner keyboard = new Scanner(System.in);
		String input;
		char choice;
		int size;
		int cost = 0;
		int numberOfToppings = 0;
		String toppings = "Cheese";
		String result = "";
		final int toppingCost = 200;


		System.out.println("-----------------------------");
		System.out.println("Pizza Size (cm)      Cost");
		System.out.println("       20            1000 T");
		System.out.println("       30            1500 T");
		System.out.println("       40            2000 T");
		System.out.println("What size pizza would you like?"); 
		System.out.print("> 20, 30, or 40 (enter the number only): ");
		size = keyboard.nextInt();
		if(size == 20){cost = 1000;}
		if(size == 30){cost = 1500;}
		if(size == 40){cost = 2000;}

		keyboard.nextLine(); 
		                

		System.out.println("-----------------------------");              
		System.out.println("All pizzas come with cheese."); 
		System.out.println("Additional toppings are 200T each," + " choose from:");
		System.out.println("- Meat, Sausage, Vegetables, Mushroom");


		System.out.print("> Do you want Meat?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Meat";
			cost += 200;
		}

		System.out.print("> Do you want Sausage?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Sausage";
			cost += 200;
		}

		System.out.print("> Do you want Vegetables?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Vegetables";
			cost += 200;
		}

		System.out.print("> Do you want Mushroom?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Mushroom";
			cost += 200;
		}

		result += size + "cm pizza, " + toppings;
		result += ":" + cost;
		return result;
   	}


	public static void previewOrder(String[] orders, boolean discount){
		System.out.println("------------------------------------------------");
		System.out.println("Your order: ");


		int i = 0;
		while(orders[i] != null)
		{
			i++;
		System.out.printf("%d) %s",i,orders[i-1]);
		System.out.println();
		}
		
		// print total cost
		System.out.println("Total: " + getTotalCost(orders) + " T");
	}


	public static int getTotalCost(String[] orders){
		int totalcost = 0;
		int i = 0;
		String c ;
		String s ;
		int sk ;
		int cost;
		while(orders[i] != null) {
			c = orders[i];
			sk = c.indexOf(":");
			s = c.substring(sk+1);
			cost = Integer.parseInt(s);
			totalcost += cost;
			i++;
		}
		return totalcost;
	}


	public static void confirmOrder(String[] orders, boolean discount){
		final int DISCOUNT_AMOUNT = 10;	// discount amount in percentage

		//display order confirmation
		System.out.println("#############################################");


		previewOrder(orders , discount);


		int cost = getTotalCost(orders);
		int amount ;
		if(discount == true){
			amount = (cost * DISCOUNT_AMOUNT) / 100;
			cost = cost - amount;
			System.out.println("-----------------------------");
			System.out.println("TOTAL with DISCOUNT (on presenting ID only!): \n" + cost + "T");
		}

		
		System.out.println("-----------------------------");
		System.out.println("Your order will be ready for pickup in 30 minutes.");

		System.out.print("Date: ");
		printCurrentDate();

		System.out.print("\tTime: ");
		printCurrentTime();
		System.out.println();

		System.out.printf("Order ID: %04d",  generateCode());
		System.out.println();
	}


	public static void printCurrentDate(){
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.print(dateFormat.format(currentDate));
	} 


	public static void printCurrentTime(){
		Date currentTime = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		System.out.print(timeFormat.format(currentTime));
	}


	public static int generateCode(){
		int id = (int)(Math.random()*10000-1);
		return id;
	}
}