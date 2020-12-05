package infrastructure;

import java.util.ArrayList;


public class Lists {
    int[] carportMeasure = {240, 270, 300, 330, 360, 390, 420, 450, 480, 510, 540, 570, 600, 630, 660, 690, 720, 750};
    String[] tag = {"Plasttrapezplader"};
    String[] tagRejs = {"Betontagsten - Rød", "Betontagsten - Teglrød", "Betontagsten - Brun", "Betontagsten - Sort", "Eternittag B6 - Grå", "Eternittag B6 - Sort", "Eternittag B6 - Mokka (brun)", "Eternittag B6 - Rødbrun", "Eternittag B6 - Teglrød", "Eternittag B7 - Grå", "Eternittag B7 - Sort", "Eternittag B7 - Mokka (brun)", "Eternittag B7 - Rødbrun", "Eternittag B7 - Teglrød", "Eternittag B7 - Rødflammet"};
    int[] tagHaldning = {25, 30, 35, 40, 45};
    int[] shed = {150, 180, 210, 270, 300, 330, 360, 390, 420, 450, 480, 510, 540, 570, 600, 630, 660, 690, 720};

    public Lists() {
        this.carportMeasure = carportMeasure;
        this.tag = tag;
        this.tagRejs = tagRejs;
        this.tagHaldning = tagHaldning;
        this.shed = shed;
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

    public ArrayList shed() {
        ArrayList shedOutput = new ArrayList<Integer>();
        for (int i = 0; i < shed.length; i++) {
            shedOutput.add(shed[i]);
        }
        return shedOutput;
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

    public int[] getShed() {
        return shed;
    }
}
