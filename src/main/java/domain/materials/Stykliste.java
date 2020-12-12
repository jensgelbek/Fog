package domain.materials;

import domain.items.Carport;

import java.util.ArrayList;
import java.util.List;

public class Stykliste {

    public List<StykListeLinje> volumenListe;
    public List<StykListeLinje> unitListe;

    public Stykliste() {
        this.volumenListe = new ArrayList<>();
        this.unitListe = new ArrayList<>();
    }




    @Override
    public String toString() {
        return "Stykliste{" +
                "volumenListe=" + volumenListe +
                ", unitListe=" + unitListe +
                '}';
    }
}