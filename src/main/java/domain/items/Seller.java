package domain.items;

public class Seller {
    int sellerID;
    String name;
    String userName;
    private byte[] salt;
    private byte[] secret;

    public Seller(String name, String userName, byte[] salt, byte[] secret) {
        this.sellerID = sellerID;
        this.name = name;
        this.userName = userName;
        this.salt = salt;
        this.secret = secret;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
