package domain.items;

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
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }


    public boolean isRejsning() {
        return rejsning;
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
}
