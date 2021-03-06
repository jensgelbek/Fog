package infrastructure;

import java.util.ArrayList;


public class Lists {
    int[] carportMeasure = {2400, 2700, 3000, 3300, 3600, 3900, 4200, 4500, 4800, 5100, 5400, 5700, 6000, 6300, 6600, 6900, 7200, 7500};
    String[] tag = {"Plasttrapezplader"};
    String[] tagRejs = {"Betontagsten - Rød", "Betontagsten - Teglrød", "Betontagsten - Brun", "Betontagsten - Sort", "Eternittag B6 - Grå", "Eternittag B6 - Sort", "Eternittag B6 - Mokka (brun)", "Eternittag B6 - Rødbrun", "Eternittag B6 - Teglrød", "Eternittag B7 - Grå", "Eternittag B7 - Sort", "Eternittag B7 - Mokka (brun)", "Eternittag B7 - Rødbrun", "Eternittag B7 - Teglrød", "Eternittag B7 - Rødflammet"};
    int[] tagHaldning = {25, 30, 35, 40, 45};
    int[] shedWidthArr = {1800, 2100, 2700, 3000, 3300, 3600, 3900, 4200, 4500, 4800, 5100, 5400, 5700, 6000, 6300, 6600, 6900, 7200};
    int[] shedLengthArr = { 1800, 2100, 2700, 3000, 3300, 3600, 3900};

    public Lists() {
        this.carportMeasure = carportMeasure;
        this.tag = tag;
        this.tagRejs = tagRejs;
        this.tagHaldning = tagHaldning;
        this.shedWidthArr = shedWidthArr;
        this.shedLengthArr = shedLengthArr;
    }


    public ArrayList carportMeasure() {
        ArrayList carporOutput = new ArrayList<Integer>();
        for (int i = 0; i < carportMeasure.length; i++) {
            carporOutput.add(carportMeasure[i]);
        }
        return carporOutput;
    }

    public ArrayList tag() {
        ArrayList tagOutput = new ArrayList<String>();
        for (int i = 0; i < tag.length; i++) {
            tagOutput.add(tag[i]);
        }
        return tagOutput;
    }

    public ArrayList shedwidth() {
        ArrayList shedWidthOutput = new ArrayList<Integer>();
        for (int i = 0; i < shedWidthArr.length; i++) {
            shedWidthOutput.add(shedWidthArr[i]);
        }
        return shedWidthOutput;
    }
    public ArrayList shedlength() {
        ArrayList shedLengthOutput = new ArrayList<Integer>();
        for (int i = 0; i < shedLengthArr.length; i++) {
            shedLengthOutput.add(shedLengthArr[i]);
        }
        return shedLengthOutput;
    }

    public ArrayList tagRejs() {
        ArrayList tagRejsOutput = new ArrayList<String>();
        for (int i = 0; i < tagRejs.length; i++) {
            tagRejsOutput.add(tagRejs[i]);
        }
        return tagRejsOutput;
    }


    public ArrayList tagHaldning() {
        ArrayList tagHaldningOutput = new ArrayList<Integer>();
        for (int i = 0; i < tagHaldning.length; i++) {
            tagHaldningOutput.add(tagHaldning[i]);
        }
        return tagHaldningOutput;
    }

    public int[] getCarportMeasure() {
        return carportMeasure;
    }

    public String[] getTag() {
        return tag;
    }

    public String[] getTagRejs() {
        return tagRejs;
    }

    public int[] getTagHaldning() {
        return tagHaldning;
    }

    public int[] getShedLengthArr() {
        return shedLengthArr;
    }
    public int[] getShedWidthArr() {return shedWidthArr;}
}
