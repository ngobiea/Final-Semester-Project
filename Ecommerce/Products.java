package Project.Ecommerce;

public class Products<Item ,Price, Quantity> {
    private Item item;
    private Price price;
    private Quantity quantity;
    public Products(Item item, Price price, Quantity quantity){
        this.item = item;
        this.price = price;
        this.quantity =quantity;
    }
    public Item getItem() {
        return item;
    }
    public Price getPrice() {
        return price;
    }
    public Quantity getQuantity() {
        return quantity;
    }
    public void setQuantity(Quantity quantity){
        this.quantity = quantity;
    }
    public void setPrice(Price price){
        this.price = price;
    }
}
