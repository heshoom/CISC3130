class Shopper implements Comparable<Shopper>{
	
	private String firstName;
	private String lastName;
	private ShoppingCart cart;
	
	Shopper(String firstName, String lastName){
		
		this.firstName = firstName;
		this.lastName = lastName;
		cart = new ShoppingCart();
		
		
	}
	
	void addItemToCart(Item item, int numItems) {
		
		for(int i=0;i<numItems;i++){
            cart.addItem(item);
        }

	}
	
	 public int amountOwed(){
	        final double TAX = .08875;
	        int total = cart.grandTotal();
	        double taxed = Math.ceil(total*TAX);
	        return ((int)(total+taxed));

	    }
	    
	 public String toString(){
	        String output= firstName +" "+ lastName + 
	        		" " + cart;
	        return output;
	    }


	@Override
	public int compareTo(Shopper o) {
		 
		return o.cart.grandTotal() - this.cart.grandTotal();

	}

}