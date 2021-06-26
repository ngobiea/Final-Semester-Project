package Project.Ecommerce;

public abstract class Person {
    protected String name;
    protected String address;
    protected String contact;

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
    abstract public String getContact();
    abstract public String getAddress();
    abstract public String getName();
}
