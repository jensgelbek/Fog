package domain.items;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    int orderID;
    LocalDate tilbudsdato;
    LocalDate ordredato;
    LocalDate leveringsDato;
    String kundeEmail;

    int carportId;
    int price;
    String status;

    public Order(LocalDate tilbudsdato, LocalDate ordredato, LocalDate leveringsDato, String kundeEmail, int carportId, int price, String status) {
        this.tilbudsdato = tilbudsdato;
        this.ordredato = ordredato;
        this.leveringsDato = leveringsDato;

        this.kundeEmail = kundeEmail;

        this.carportId = carportId;
        this.price = price;
        this.status = status;
    }

    public String getKundeEmail() {
        return kundeEmail;
    }

    public void setKundeEmail(String kundeEmail) {
        this.kundeEmail = kundeEmail;
    }

    public Order(int orderID, int kundeid, int carportId, int price, String status) {
        this.orderID = orderID;
        this.carportId = carportId;
        this.price = price;
        this.status = status;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setTilbudsdato(LocalDate tilbudsdato) {
        this.tilbudsdato = tilbudsdato;
    }

    public void setOrdredato(LocalDate ordredato) {
        this.ordredato = ordredato;
    }

    public void setLeveringsDato(LocalDate leveringsDato) {
        this.leveringsDato = leveringsDato;
    }



    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public LocalDate getTilbudsdato() {
        return tilbudsdato;
    }

    public LocalDate getOrdredato() {
        return ordredato;
    }

    public LocalDate getLeveringsDato() {
        return leveringsDato;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", tilbudsdato=" + tilbudsdato +
                ", ordredato=" + ordredato +
                ", leveringsDato=" + leveringsDato +
                ", kundeEmail='" + kundeEmail + '\'' +

                ", carportId=" + carportId +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }


    public int getCarportId() {
        return carportId;
    }

    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderID == order.orderID && carportId == order.carportId && price == order.price && Objects.equals(tilbudsdato, order.tilbudsdato) && Objects.equals(ordredato, order.ordredato) && Objects.equals(leveringsDato, order.leveringsDato) && Objects.equals(kundeEmail, order.kundeEmail) && Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, tilbudsdato, ordredato, leveringsDato, kundeEmail, carportId, price, status);
    }
}
