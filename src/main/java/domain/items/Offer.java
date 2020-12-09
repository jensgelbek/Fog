package domain.items;

import domain.materials.Material;

import java.time.LocalDate;

public class Offer {
    Material material;
    int offerID;
    int price;
    Customer customer;
    LocalDate date;
    boolean openOffer;

    public Offer(Material material, int offerID, int price, Customer customer, LocalDate date, boolean openOffer) {
        this.material = material;
        this.price = price;
        this.customer = customer;
        this.date = date;
        this.openOffer = openOffer;
        this.offerID = offerID;
    }

    public Material getMaterials() {
        return material;
    }

    public void setMaterials(Material material) {
        this.material = material;
    }

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isOpenOffer() {
        return openOffer;
    }

    public void setOpenOffer(boolean openOffer) {
        this.openOffer = openOffer;
    }
}
