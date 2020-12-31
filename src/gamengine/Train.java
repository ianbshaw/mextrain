package gamengine;

import java.util.ArrayList;
import java.util.List;

/*Class representing a train object*/
public class Train {

    /*reference variables*/
    private final List<Domino> train = new ArrayList<>();
    private int value;
    private boolean mustBePlayed = false;

    public Train() {
        this.value = -1;
    }

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public void add(Domino domino) {
        this.train.add(domino);
    }

    public boolean isMustBePlayed() {
        return mustBePlayed;
    }

    public void setMustBePlayed(boolean mustBePlayed) {
        this.mustBePlayed = mustBePlayed;
    }

    public List<Domino> getTrain() { return train; }

    @Override
    public String toString() {
        StringBuilder top = new StringBuilder();
        StringBuilder bottom = new StringBuilder("\n\t\t\t");

        if (this.train.size() == 0) {
            top.append("[]");
            return top.toString();
        } else {
            for (int i = 0; i < this.train.size(); i++) {
                if (i == 0) top.append(this.train.get(i).toString());
                else if ((i % 2 ) == 0 )
                    top.append(this.train.get(i).toString());
                else bottom.append(this.train.get(i).toString());
            }
        }
        return top.toString() + bottom.toString();
    }
}
