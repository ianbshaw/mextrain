package gamengine;

import java.util.ArrayList;
import java.util.List;

/*Class representing a player hand object*/
public class Hand {
    private final List<Domino> hand = new ArrayList<>();

    public List<Domino> getHand() {
        return this.hand;
    }

    @Override
    public String toString() {
        StringBuilder bottom = new StringBuilder("\t\t [");
        StringBuilder top = new StringBuilder();

        if (hand.size() == 0) {
            bottom.append("]");

        } else {
            for (int i = 0; i < hand.size() - 1; i++) {
                if (i < 9) top.append("     ").append(i + 1).append("     ");
                else top.append("     ").append(i + 1).append("    ");

                bottom.append(hand.get(i).toString()).append(", ");
            }
            top.append("     ").append(hand.size()).append("      ");
            bottom.append(hand.get(hand.size() - 1)).append("]");
        }

        return top.toString() + "\n" + bottom.toString();
    }
}
