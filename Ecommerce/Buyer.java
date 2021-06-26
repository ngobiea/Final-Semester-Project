package Project.Ecommerce;
import java.util.*;
public class Buyer extends Person{
    private ArrayList<Products<String,Double,Integer>> cart;
    private Products<String,Double,Integer> product;
    public Buyer(String name, String contact, String address){
        super.setName(name);
        super.setContact(contact);
        super.setAddress(address);
        cart = new ArrayList<Products<String,Double,Integer>>();
    }
    public void updateCart(String item, Double price,Integer quantity){
        product = new Products<>(item, price, quantity);
        cart.add(product);
    }
    public String getContact(){
        return super.contact;
    }
    public String getAddress(){
        return super.address;
    }
    public String getName(){
        return super.name;
    }
    public ArrayList<Products<String,Double,Integer>> getCart(){
        return cart;
    }
    public void emptyCart(){
        cart.clear();
    }
        
}
