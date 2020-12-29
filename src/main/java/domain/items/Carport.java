package domain.items;

import java.util.Objects;

public class Carport {

    int carportID;
    int width;
    int lenght;

    boolean rejsning;
    String tag;
    int shedWidth;
    int shedLength;


    public Carport(int width, int lenght, boolean rejsning, String tag, int shedWidth, int shedLength) {
        this.width = width;
        this.lenght = lenght;
        this.tag = tag;
        this.rejsning = rejsning;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public Carport() {
        this.width = width;
        this.lenght = lenght;
        this.tag = tag;
        this.rejsning = rejsning;

    }


    public boolean isRejsning() {
        return rejsning;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void setRejsning(boolean rejsning) {
        this.rejsning = rejsning;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCarportID() {
        return carportID;
    }

    public int getWidth() {
        return width;
    }

    public int getLenght() {
        return this.lenght;
    }

    public String getTag() {
        return tag;
    }

    public boolean getRejsning() {
        return rejsning;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getShedLength() {
        return shedLength;

    }

    public void setCarportID(int carportID) {
        this.carportID = carportID;
    }


    @Override
    public String toString() {
        return "Carport{" +
                "carportID=" + carportID +
                ", width=" + width +
                ", lenght=" + lenght +
                ", tag='" + tag + '\'' +
                ", rejsning=" + rejsning +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carport)) return false;
        Carport carport = (Carport) o;
        return carportID == carport.carportID && width == carport.width && lenght == carport.lenght && rejsning == carport.rejsning && shedWidth == carport.shedWidth && shedLength == carport.shedLength && Objects.equals(tag, carport.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carportID, width, lenght, rejsning, tag, shedWidth, shedLength);
    }
}
