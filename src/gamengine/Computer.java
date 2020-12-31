package gamengine;

import java.util.*;

/*Class representing computer player, extends player class*/
public class Computer extends Player {

    /*reference variables*/
    private final List<Combo> comboList = new ArrayList<>();
    private final Queue<Domino> roundCombo;

    public Computer() {
        this.setComputer(true);
        this.roundCombo = new LinkedList<>();
    }

    /*find combos from hand based on personal train and adds to list*/
    public void searchCombo(Hand hand, int checkValue, Combo combo) {

        for (int i = 0; i < hand.getHand().size(); i++) {
            if (hand.getHand().get(i).getLeft() == checkValue && !(combo.getCombo().contains(hand.getHand().get(i)))) {
                combo.getCombo().add(hand.getHand().get(i));
                combo.setScore(combo.getScore() + hand.getHand().get(i).getValue());
                searchCombo(hand, hand.getHand().get(i).getRight(), combo);

            } else if (hand.getHand().get(i).getRight() == checkValue && !(combo.getCombo().contains(hand.getHand().get(i)))) {
                combo.getCombo().add(hand.getHand().get(i));
                combo.setScore(combo.getScore() + hand.getHand().get(i).getValue());
                searchCombo(hand, hand.getHand().get(i).getLeft(), combo);

            }
        }
        Combo foundCombo = new Combo(combo);
        comboList.add(foundCombo);
        if (combo.getCombo().size() > 0) {
            combo.setScore(combo.getScore() - combo.getCombo().get(combo.getCombo().size() - 1).getValue());
            combo.getCombo().remove(combo.getCombo().size() - 1);
        }
    }

    /*find best score combo from list of possible*/
    public void findBestCombo(List<Combo> comboList) {

        Combo c = comboList.get(0);

        for (int i = 1; i < comboList.size(); i++) {
            if (c.getScore() < comboList.get(i).getScore())
                c = comboList.get(i);
        }
        comboList.clear();
        roundCombo.addAll(c.getCombo());
    }

    public int getHandIndex(Hand hand) {
        return hand.getHand().indexOf(roundCombo.peek());
    }

    public List<Combo> getComboList() {
        return comboList;
    }

    public Queue<Domino> getRoundCombo() {
        return roundCombo;
    }
}
