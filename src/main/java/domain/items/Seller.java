package domain.items;

import java.util.Arrays;
import java.util.Objects;

public class Seller {
    int sellerID;
    String username;
    String name;

    private byte[] salt;
    private byte[] secret;


    public Seller(String username, String name, byte[] salt, byte[] secret) {
        this.username = username;
        this.name = name;
        this.salt = salt;
        this.secret = secret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSecret() {
        return secret;
    }

    public void setSecret(byte[] secret) {
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

    @Override
    public String toString() {
        return "Seller{" +
                "sellerID=" + sellerID +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;
        Seller seller = (Seller) o;
        return sellerID == seller.sellerID && Objects.equals(username, seller.username) && Objects.equals(name, seller.name) && Arrays.equals(salt, seller.salt) && Arrays.equals(secret, seller.secret);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sellerID, username, name);
        result = 31 * result + Arrays.hashCode(salt);
        result = 31 * result + Arrays.hashCode(secret);
        return result;
    }
}
