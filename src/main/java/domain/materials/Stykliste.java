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

    public List<StykListeLinje> getVolumenListe() {
        return volumenListe;
    }

    public void setVolumenListe(List<StykListeLinje> volumenListe) {
        this.volumenListe = volumenListe;
    }

    public List<StykListeLinje> getUnitListe() {
        return unitListe;
    }

    public void setUnitListe(List<StykListeLinje> unitListe) {
        this.unitListe = unitListe;
    }

    @Override
    public String toString() {
        return "Stykliste\n" +
                "volumenListe=\n" + volumenListe +"\n"+
                ", unitListe=\n" + unitListe ;
    }
}