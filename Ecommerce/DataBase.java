package Project.Ecommerce;
import java.util.*;
import java.io.*;

public class DataBase{
    private ArrayList<Seller> sellers;
    private ArrayList<Buyer> buyers;
    public DataBase(){
        sellers = new ArrayList<>();
        buyers = new ArrayList<>();
    }
    public void addBuyer(Buyer buyer){
        buyers.add(buyer);
    }
    public void addSeller(Seller seller){
        sellers.add(seller);
    }
    public void printProducts(){
        System.out.println("Name\t\t  Price\t\t   Quantity");
        for(int i=0; i<sellers.size(); i++){
            sellers.get(i).ShowProducts();
        }
    }
    public void updateCart(String contact, String item,Integer quantity){
        for(int i=0; i<buyers.size(); i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                buyers.get(i).updateCart(item,getPrice(item)*quantity,quantity);
            }
        }
    }
    public void emptyCart(String contact){
        for(int i = 0; i < buyers.size(); i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                buyers.get(i).emptyCart();
            }
        }
    }
    public void printCart(String contact){
        double total_cost =0;
        for(int  i=0; i<buyers.size(); i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                System.out.println("Item\t\t  Price\t\tQuantity");
                for(int j=0; j<buyers.get(i).getCart().size(); j++){
                    System.out.printf("%-10s\t  Rs%-5.2f\t\t%-10d\n",buyers.get(i).getCart().get(j).getItem(),buyers.get(i).getCart().get(j).getPrice(),buyers.get(i).getCart().get(j).getQuantity());
                    total_cost+=buyers.get(i).getCart().get(j).getPrice();
                }
            }
        }
        System.out.printf("The total price is: Rs%.2f\n",total_cost);
    }
    public String[] verifyContact(String contact){
        String[] con = new String[2];
        for(int i=0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                con[0]="Seller";
                con[1]=sellers.get(i).getName();
                return con;
            }
        }
        for(int i=0; i<buyers.size();i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                con[0]="Buyer";
                con[1]=buyers.get(i).getName();
                return con;
            }
        }
        con[0]=" ";
        return con;
    }
    public Double getPrice(String item){
        Double price= 0.0;
        for(int i=0; i<sellers.size(); i++){
            for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                if(sellers.get(i).getTotalProduct().get(j).getItem().equalsIgnoreCase(item)){
                    price = sellers.get(i).getTotalProduct().get(j).getPrice();
                }
            }
        }
        return price;
    }
    public boolean buyItem(String item, Integer quantity){
        boolean result = false;
        for(int i=0; i<sellers.size(); i++){
            for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                if(item.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                    if(quantity.compareTo(sellers.get(i).getTotalProduct().get(j).getQuantity())==1){
                        System.out.println("This item is unavailable buy to quantiy remaining");
                        result= false;
                    }else{
                        sellers.get(i).deposit((sellers.get(i).getTotalProduct().get(j).getPrice())*quantity);
                        sellers.get(i).getTotalProduct().get(j).setQuantity(sellers.get(i).getTotalProduct().get(j).getQuantity()-quantity);
                        System.out.printf("Bought "+quantity+" "+sellers.get(i).getTotalProduct().get(j).getItem()+" For Rs%.2f\n\n",sellers.get(i).getTotalProduct().get(j).getPrice()*quantity);
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    public void addProduct(String name, String item, Double price, Integer quantity) {
        for(int i = 0; i <sellers.size(); i++){
            if(name.equalsIgnoreCase(sellers.get(i).getContact())){
                sellers.get(i).addProduct(item, price, quantity);
                System.out.println("Successfully added a new Item");
            }
        }
    }
    public void updateItem(String contact, String item, Double price, Integer quantity) {
        for(int i =0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                    if(item.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                        sellers.get(i).getTotalProduct().get(j).setPrice(price);
                        sellers.get(i).getTotalProduct().get(j).setQuantity(quantity);
                        System.out.println("Successfully update Item");
                    }
                }
            }
        }
    }
    public void updateQuantity(String contact, String item, Integer quantity) {
        for(int i =0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                    if(item.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                        sellers.get(i).getTotalProduct().get(j).setQuantity(quantity);
                        System.out.println("Successfully update Quantity");
                    }
                }
            }
        }
    }
    public void updatePrice(String contact, String item, Double price) {
        for(int i =0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                    if(item.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                        sellers.get(i).getTotalProduct().get(j).setPrice(price);
                        System.out.println("Successfully update Price");
                    }
                }
            }
        }
    }
    public Double getAccount(String contact) {
        Double account=0.0;
        for(int i =0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                account= sellers.get(i).getAccount();
            }
        }
        return account;
    }
    public void showItems(String contact) {
        System.out.println("Name\t\t  Price\t\t   Quantity");
        for(int i = 0; i <sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
               sellers.get(i).ShowProducts();
            }
        }
    }
    public double getRatings(String contact) {
        try {
            for(int i = 0; i <sellers.size(); i++){
                if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                    return sellers.get(i).getRating();
                }
            }
        } catch (Exception e) {
            System.out.println("No rating for your products");
        }
        return 0;
    }
    public void setRatings(String contact,String item, int d){
        for(int i =0; i<sellers.size(); i++){
            for(int j =0; j< sellers.get(i).getTotalProduct().size(); j++){
                if(sellers.get(i).getTotalProduct().get(j).getItem().equalsIgnoreCase(item)){
                    sellers.get(i).setRatings(d);
                }
            }
           
        }
    }
    public boolean checkCart(String contact) {
        boolean ans= false;
        for(int i=0; i<buyers.size(); i++){
            if(buyers.get(i).getContact().equalsIgnoreCase(contact)){
                if(!buyers.get(i).getCart().isEmpty()){
                    ans = true;
                }
            }
        }
        return ans;
    }
    public boolean checkContact(String contact) {
        boolean ans = false;
        for(int i=0; i<sellers.size(); i++){
            if(contact.equalsIgnoreCase(sellers.get(i).getContact())){
                contact=sellers.get(i).getContact();
                ans = true;
                return ans;
            }
        }
        for(int i=0; i<buyers.size();i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                contact=buyers.get(i).getContact();
                ans = true;
                return ans;
            }
        }
        return ans;
    }
    public boolean cheakItem(String product) {
        for(int i = 0; i < sellers.size(); i++){
            for(int j = 0; j <sellers.get(i).getTotalProduct().size(); j++){
                if(product.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                    return true;
                }
            }
        }
        return false;
    }
    public void deleteItemInCart(String product,String contact){
        for(int i=0;i<buyers.size();i++){
            if(contact.equalsIgnoreCase(buyers.get(i).getContact())){
                for(int j=0; j<buyers.get(i).getCart().size(); j++){
                    if(product.equalsIgnoreCase(buyers.get(i).getCart().get(j).getItem())){
                        buyers.get(i).getCart().remove(j);
                    }
                }
            }
        }
    }
    public String getFirstItemInCart(String contact) {
        String item=" ";
        for(int i=0; i<buyers.size(); i++) {
            if(contact.equals(buyers.get(i).getContact())) {
                item =buyers.get(i).getCart().get(0).getItem();
            }
        }
        return item;
    }
    public void writeSellersReport(String product,Integer quantity){
        String contact = " ";
        Double price=0.0;
        for(int i=0; i<sellers.size(); i++){
            for(int j=0; j<sellers.get(i).getTotalProduct().size(); j++){
                if(product.equalsIgnoreCase(sellers.get(i).getTotalProduct().get(j).getItem())){
                    contact= sellers.get(i).getContact();
                    price=sellers.get(i).getTotalProduct().get(j).getPrice();
                }
            }
        }
        try {
            DataOutputStream wr = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(contact+"report.dat",true)));
            wr.writeUTF("Date Sold: ");
            wr.writeUTF(new Date().toString()+"\n");
            wr.writeUTF("Item: ");
            wr.writeUTF(product+"\n");
            wr.writeUTF("Price: ");
            wr.writeUTF(String.format("Rs"+"%.2f",price*quantity)+"\n");
            wr.writeUTF("Quantity: ");
            wr.writeUTF(Integer.toString(quantity)+"\n\n\n");
            wr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void readSellersReport(String contact){
        System.out.println("\nSales Report\n");
        try {
            
            DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(contact+"report.dat")));
            while(true){
                System.out.print(reader.readUTF());        
            }
        }catch (EOFException e) {
            System.out.println("End of report");
        }
         catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
