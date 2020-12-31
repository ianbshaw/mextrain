package gamengine;

import java.util.ArrayList;
import java.util.List;

/*Class representing boneyard object*/
public class Boneyard {

    /*reference variables*/
    private final List<Domino> boneyard = new ArrayList<>();
    private int boneyardNum;

    public Boneyard() {
        generate();
    }

    public List<Domino> getBoneyard() {
        return boneyard;
    }

    public int getBoneyardNum() {
        return boneyardNum;
    }

    public void setBoneyardNum(int boneyardNum) {
        this.boneyardNum = boneyardNum;
    }

    /*method to generate boneyard on init*/
    private void generate() {
        boneyardNum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i >= j) {
                    boneyardNum++;
                    boneyard.add(new Domino(i, j));
                }
            }
        }
    }

    /*CLI object*/
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");

        for (int i = 0; i < boneyard.size() - 1; i++) {
            s.append(boneyard.get(i).toString()).append(", ");
        }
        s.append(boneyard.get(boneyard.size() - 1)).append("]");

        return s.toString();
    }
}
