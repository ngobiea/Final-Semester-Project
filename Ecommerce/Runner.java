package Project.Ecommerce;
import java.util.*;
public class Runner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Program start with initially 3 sellers since for an ecommerce system a seller is first needed to add new product
        Seller s1 = new Seller("Augustine","03185976460","Street No8","ER081216");
        s1.addProduct("Apple",Math.random()*100, (int)(Math.random()*10));
        s1.addProduct("Orange",Math.random()*100, (int)(Math.random()*10));
        s1.addProduct("Watermelon",Math.random()*100, (int)(Math.random()*10));
        s1.addProduct("Strawberry",Math.random()*100, (int)(Math.random()*10));
        s1.addProduct("Banana",Math.random()*100, (int)(Math.random()*10));

        Seller s2 = new Seller("DANIYAL","03334839846","Street No2","PK404828");
        s2.addProduct("Iphone",Math.random()*100000, (int)(Math.random()*10));
        s2.addProduct("Samsung",Math.random()*100000, (int)(Math.random()*10));
        s2.addProduct("Oppo",Math.random()*100000, (int)(Math.random()*10));
        s2.addProduct("Infinix",Math.random()*100000, (int)(Math.random()*10));
        s2.addProduct("Vivo",Math.random()*100000, (int)(Math.random()*10));
        
        Seller s3 = new Seller("Afnan","03426504532","Street No5","PK86491");
        s3.addProduct("Toyota",Math.random()*1000000, (int)(Math.random()*10));
        s3.addProduct("Benz",Math.random()*1000000, (int)(Math.random()*10));
        s3.addProduct("Honda",Math.random()*1000000, (int)(Math.random()*10));
        s3.addProduct("BMW",Math.random()*1000000, (int)(Math.random()*10));
        s3.addProduct("Ford",Math.random()*1000000, (int)(Math.random()*10));

        DataBase database = new DataBase();
        database.addSeller(s1);
        database.addSeller(s2);
        database.addSeller(s3);
        
      
        String options;
        do{
              System.out.println("Welcome to AAD E-commerce Platform\n");
            System.out.println("Select your options to register or login as and existing user\n");
            System.out.println("1.New user\n2.Existing User\n3.Exit App");
            options = input.nextLine();
            if(options.equals("1")){
                
                System.out.println("\n\n1.Seller\n2.Buyer");
                options = input.nextLine();
                System.out.println();
                if(options.equals("1")) {
                    System.out.print("Enter Shop Name:");
                    String name = input.nextLine();
                    System.out.print("Enter Address:");
                    String address = input.nextLine();
                    System.out.print("Enter Phone Number or Email Address:");
                    String contact = input.nextLine();
                    if(database.checkContact(contact)){
                        System.out.println("User already exist login to the system with your existing contact");
                        continue;
                    }
                    System.out.print("Enter CNIC:");
                    String cnic = input.nextLine();
                    Seller s4 = new Seller(name,contact,address,cnic);
                    database.addSeller(s4);
                    sellerActivity(database, contact);
                }
                else if(options.equals("2")){
                    System.out.print("Enter Name:");
                    String name = input.nextLine();
                    System.out.print("Enter Address:");
                    String address = input.nextLine();
                    System.out.print("Enter Phone Number or Email Address:");
                    String contact = input.nextLine();
                    if(database.checkContact(contact)){
                        System.out.println("User already exist login to the system with your existing contact");
                        continue;
                    }
                    Buyer b1 = new Buyer(name,contact,address);
                    database.addBuyer(b1);
                    System.out.println("\n\n");
                    buyerActivity(database,contact);
                }else{
                    System.out.println("Incorrect Input!");
                }
            }else if(options.equals("2")){
                System.out.println("Enter email or phone number to login");
                String contact =input.nextLine();
                String[] con= database.verifyContact(contact);
                if(con[0].equals("Seller")){
                    System.out.println("Welcome "+ con[1]);
                    sellerActivity(database, contact);
                }
                else if(con[0].equals("Buyer")){
                    System.out.println("Welcome "+ con[1]);
                    buyerActivity(database, contact);
                } else{
                    System.out.println("User does not exist \nRegister to buy/sell  an item");
                }
            }
            else if(options.equals("3")){
                break;
            }
        }while(true); 
        input.close();
    }
    public static void buyerActivity(DataBase database,String contact){
        Scanner input = new Scanner(System.in);
        while(true){
            database.printProducts();
            System.out.print("\n\nAdd to cart?(Yes/No):");
            String answer = input.nextLine();
            if(answer.equalsIgnoreCase("yes")){
                while(answer.equalsIgnoreCase("yes")){
                    System.out.print("Enter the item you want to add to the cart:");
                    String product = input.nextLine();
                    if(database.cheakItem(product)){
                        System.out.print("Enter the quantity:");
                        String quantity = input.nextLine();
                        System.out.println();
                        if(database.buyItem(product,Integer.parseInt(quantity))){
                            database.updateCart(contact,product,Integer.parseInt(quantity));
                            database.writeSellersReport(product, Integer.parseInt(quantity));
                        }
                        System.out.print("Add to cart?(Yes/No): ");
                        answer = input.nextLine();
                    }else{
                        System.out.println("Item not found\n\n");
                        database.printProducts();
                        continue;
                    }
                }
                if(database.checkCart(contact)){
                    while(true){
                        database.printCart(contact);
                        System.out.println("\n\n\t\tPayment Method\n1.Pay Online\n2.Pay Cash on Delivery");
                        String paymethod = input.nextLine();
                        if(paymethod.equals("1")){
                            Payable pay = new OnlinePayment();
                            System.out.println(pay.pay());
                            break;
                        }else if(paymethod.equals("2")){
                            Payable pay = new CashOnDelivery();
                            System.out.println(pay.pay());
                            break;
                        }else{
                            System.out.println("Wrong input!!!");
                        }
                    }
                }
                System.out.print("1.Rate(yes/no):");
                        String ans = input.nextLine();
                if(ans.equalsIgnoreCase("yes")){
                    while(database.checkCart(contact)){
                        System.out.println();
                        System.out.println("Enter Ratings for "+database.getFirstItemInCart(contact));
                        System.out.println("1. *\n2. **\n3. ***\n4. ****\n5. *****\n6. skip rating for this item:");
                        String rate = input.nextLine();
                        if(rate.equals("1")||rate.equals("2")||rate.equals("3")||rate.equals("4")||rate.equals("5")){
                            database.setRatings(contact,database.getFirstItemInCart(contact),Integer.parseInt(rate));
                        }
                        database.deleteItemInCart(database.getFirstItemInCart(contact), contact);
                        System.out.println();
                    }
                }
            }else if(answer.equalsIgnoreCase("no")){
                System.out.println("You Cart is empty");
            }else{
                System.out.println("Wrong Input");
            }
            System.out.println("\n1.Continue Shopping\n2.Exit");
            String ans = input.nextLine();
            if(ans.equals("2")){
                break;
            }
        }
    }
    public static void sellerActivity(DataBase database, String contact){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println();
            database.showItems(contact);
            System.out.println("\tSelect Option");
            System.out.println("1.Add new item to store\n2.Update an Item\n3.Check sales Account\n4.Check ratings\n5.Generate sales Report\n6.Exit");
            String answer = input.nextLine();
            System.out.println();
            if(answer.equals("1")){
                System.out.print("Enter the name of Item:");
                String item = input.nextLine();
                System.out.print("Enter the price:");
                String price = input.nextLine();
                System.out.print("Enter the quantity:");
                String quantity = input.nextLine();
                database.addProduct(contact,item,Double.parseDouble(price),Integer.parseInt(quantity));
            }
            else if(answer.equals("2")){
                System.out.print("Enter the item you want to update:");
                String item = input.nextLine();
                System.out.println("1.Update Price\n2.Update Quantity\n3.Update Price and Quantity");
                String ans = input.nextLine();
                if(ans.equals("1")){
                    System.out.print("Enter the price:");
                    String price = input.nextLine();
                    database.updatePrice(contact,item,Double.parseDouble(price));
                }else if(ans.equals("2")){
                    System.out.print("Enter the quantity:");
                    String quantity = input.nextLine();
                    database.updateQuantity(contact,item,Integer.parseInt(quantity));
                }else if(ans.equals("3")){
                    System.out.print("Enter the price:");
                    String price = input.nextLine();
                    System.out.print("Enter the quantity:");
                    String quantity = input.nextLine();
                    database.updateItem(contact,item,Double.parseDouble(price),Integer.parseInt(quantity));
                } 
            }
            else if(answer.equals("3")){
                System.out.printf("Account:Rs%.2f\n",database.getAccount(contact));
            }else if(answer.equals("4")){
                System.out.println("Ratings:"+database.getRatings(contact));
            }else if(answer.equals("5")){
                database.readSellersReport(contact);
            }else if(answer.equals("6")){
                break;
            }
            System.out.println();
        }
       
    }

}
