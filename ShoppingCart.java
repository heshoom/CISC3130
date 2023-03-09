import java.util.ArrayList;

class ShoppingCart{

	private ArrayList<Item> cart;
	int num;

	public ShoppingCart() {

		cart = new ArrayList<Item>();
		num=0;
	}

	void addItem(Item item) {

		cart.add(item);
	}

	int grandTotal() {

		//Initialize grand total 
		int sum = 0;

		//for each loop to get the price of each item in the cart and add it to the grand total
		for( Item price : cart) {

			sum += price.getPrice();
		}

		//return grand total
		return sum;
	}

	int numItems() {

		return cart.size();

	}

	public String toString() {

		String output = "";

		for( Item items : cart) {

			output += items.toString();
		}

		return output;
	}

}
