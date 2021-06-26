package Project.Ecommerce;
import java.util.*;
public class Seller extends Person{
    private String CNIC;
    private double account;
    private int[] shopRatings;
    
    private Products<String ,Double,Integer> product;
    private ArrayList<Products<String ,Double,Integer>> totalProducts;
    public Seller(String name, String contact, String address, String CNIC){
        super.setName(name);
        super.setContact(contact);
        super.setAddress(address);
        this.CNIC = CNIC;
        account = 0;
        this.totalProducts = new ArrayList<Products<String ,Double,Integer>>();
        shopRatings = new int[5];
    }
    public void addProduct(String item, Double price, Integer quantity){
        product = new Products<>(item, price, quantity);
        totalProducts.add(product);
    }
    public void setCNIC(String CNIC){
        this.CNIC = CNIC;
    }
    public void deposit(double amount){
        this.account+=amount;
    }
    public void setRatings(int rating){
            if(rating == 1){
                shopRatings[0]+=1;
            }
            else if(rating ==2){
                shopRatings[1]+=1;
            }
            else if(rating ==3){
                shopRatings[2]+=1;
            }
            else if(rating ==4){
                shopRatings[3]+=1;
            }
            else if(rating ==5){
                shopRatings[4]+=1;
            }
    }
    public double getAccount(){
        return account;
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
    public String getCNIC(){
        return CNIC;
    }
    public double getRating() {
        return (5*shopRatings[4]+4*shopRatings[3]+3*shopRatings[2]+2*shopRatings[1]+1*shopRatings[0])/(shopRatings[4]+shopRatings[3]+shopRatings[2]+shopRatings[1]+shopRatings[0]);
    }
    public ArrayList<Products<String,Double,Integer>> getTotalProduct(){
        return this.totalProducts;
    }
    public void ShowProducts(){
        for(int i =0; i<totalProducts.size(); i++){
            System.out.printf("%-10s\t  Rs%-5.2f  \t\t%-10d",totalProducts.get(i).getItem(),(double)totalProducts.get(i).getPrice(),totalProducts.get(i).getQuantity());
            
            System.out.println();
        }
    }
}