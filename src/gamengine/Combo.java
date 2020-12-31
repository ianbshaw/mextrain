package gamengine;

import java.util.ArrayList;
import java.util.List;

/*Class representing a computer combo*/
public class Combo {

    /*reference variables*/
    private final List<Domino> combo;
    private int score;

    public Combo(Combo c) {
        this.combo = new ArrayList<>(c.combo);
        this.score = c.score;
    }

    public Combo() {
        this.combo = new ArrayList<>();
        this.score = 0;
    }

    public List<Domino> getCombo() {
        return combo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
