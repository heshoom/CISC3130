import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

class HardwareStore {

	    //This represents the products available in our store, and will be filled up by the data in the Inventory file
	    private ArrayList<Item> inventory;

	    //This data structure will contain all the Shoppers that are ready to checkout, and will be populated by Event file
	    private PriorityQueue<Shopper> readyToCheckout;

	    private int totalRev;

	    //This data structure represents all the cash registers in the store. Queues(that represent each
	    // individual register). I use a TreeSet to store the Queues of the store because the structure stores its data
	    //in sorted order. This allows me to obtain the Queue with the fewest/greatest size with ease, which is important
	    //when for the checkoutAllShoppers method. Yet Queues are not naturally Comparable, so I had to pass in
	    // a Comparator that sorts Queues base off the size of the Queue.
	    private TreeSet<Queue<Shopper>> registers = new TreeSet<>(new Comparator<Queue<Shopper>>() {
	        @Override
	        public int compare(Queue<Shopper> o1, Queue<Shopper> o2) {
	            //If o1 is bigger, returns positive. If same, returns 0. If o1 is smaller, returns negative.
	            return o1.size()-o2.size();
	        }
	    });

	    //Instantiating all the fields. With the number of registers given, you can that many Queues to the the TreeSet.
	    public HardwareStore(int numRegisters){
	        totalRev=0;
	        inventory= new ArrayList<>();
	        readyToCheckout = new PriorityQueue<>();
	        for(int i=0;i<numRegisters;i++){
	            registers.add(new LinkedList<>());
	        }
	    }

	    //add the Item in the inventory file to the Hardware Store's inventory
	    public void addProductToInventory(Item product){
	        inventory.add(product);
	    }

	    //Returning the product from the inventory that corresponds to the String passed in from the Event file
	    public Item getProductFromInventory(String name){
	        for(Item product: inventory){
	            if(product.getName().equalsIgnoreCase(name)){
	                return product;
	            }
	        }
	        return null;
	    }

	    //Adding a new Shopper to the line of people that are ready to checkout.
	    public void addShopperToLine(Shopper shopper){
	        readyToCheckout.add(shopper);
	    }


	    public void checkoutAllShoppers(){
	        //This loop polls out all the people ready to checkout from the store and adds them to the
	        //queue(register) with the fewest amount of people. The Queue with the fewest number of people is decided
	        //with the TreeSet's .first method that returns the minimum key stored(Queue with the smallest size).
	        while(!readyToCheckout.isEmpty()){
	            Queue<Shopper> smallestRegister = registers.first();
	            Shopper highestInLine = readyToCheckout.poll();
	            smallestRegister.add(highestInLine);
	        }
	        //All people are on their register and are now ready to be processed and checked out

	        //This loop "checks out" all the registers until all people are checked out. First checks out register with
	        //the most people, and obtains this with the TreeSet's .pollLast() method,which removes the largest key(biggest
	        //Queue).
	        while(!registers.isEmpty()){
	            Queue<Shopper> biggestRegister = registers.pollLast();

	            //checks out all the Shoppers in the Queue by obtaining the amountOwed of each shopper, adding it to the
	            //total revenue of the store, and then polling them out of the Queue.
	            while(!biggestRegister.isEmpty()){
	                int amountOwed = biggestRegister.poll().amountOwed();
	                totalRev+=amountOwed;
	            }
	        }
	    }

	    //Total revenue should be a dollar value, so convert cents to dollar.
	    public double totalRevenue(){
	        double result = totalRev;
	        result/=100;
	        return result;
	    }


	}
