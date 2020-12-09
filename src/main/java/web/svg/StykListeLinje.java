package web.svg;

public class StykListeLinje {

    int id;
    private String name;
    private double length;
    private int unit;
    private int price;
    private int sum;


    public StykListeLinje(String name, double length, int unit, int price, int sum) {
        this.name = name;
        this.length = length;
        this.unit = unit;
        this.price = price;
        this.sum = sum;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return " Beskrivelse: " + name + '\n' +
                " - Længde: " + length + " mm" +
                " - Antal: " + unit +
                " - Pris: " + price + " pr/meter" +
                " - Total: " + sum + " kr";
    }
}
