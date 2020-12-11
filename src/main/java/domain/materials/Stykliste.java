package domain.materials;

import domain.items.Carport;

import java.util.ArrayList;
import java.util.List;

public class Stykliste {

    public Stykliste() {
        this.volumenListe = volumenListe;
        this.unitListe = unitListe;
    }

    public List<StykListeLinje> volumenListe = new ArrayList<>();
    public List<StykListeLinje> unitListe = new ArrayList<>();

    @Override
    public String toString() {
        return volumenListe +
                ", unitListe=" + unitListe +
                '}';
    }
}