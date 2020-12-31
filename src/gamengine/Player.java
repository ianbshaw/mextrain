package gamengine;

import java.util.List;

/*Class representing a player object*/
public class Player {

    private final Hand hand = new Hand();
    private final Train train = new Train();
    private int id, score;
    private boolean openTrain, marker, playedDouble, isComputer  = false;

    public Player() {
        score = 0;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Train getTrain() {
        return train;
    }

    public boolean isOpenTrain() { return openTrain; }

    public void setOpenTrain(boolean openTrain) { this.openTrain = openTrain; }

    public boolean getMarker() {
        return this.marker;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

    public boolean isPlayedDouble() { return playedDouble; }

    public void setPlayedDouble(boolean playedDouble) { this.playedDouble = playedDouble; }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    /*Method to check if player has valid move*/
    public boolean checkValidMove(List<Domino> hand, Train train, Board board) {
        boolean validMove = false;

        /*check hand for valid moves*/
        for (Domino domino : hand) {
            int checkLeft = domino.getLeft();
            int checkRight = domino.getRight();
            int mexicanValue = board.getMexican().getValue();
            int e = board.getEngineValue();

            /*check for players with open trains*/
            for (Player p : board.getPlayerList()) {
                int v = p.getTrain().getValue();
                if (p.isOpenTrain() && (checkLeft == v || checkRight == v ||
                        (v == -1 && (checkLeft == e || checkRight == e)))) {
                    validMove = true;
                    break;
                }
            }

            /*check values and empty trains*/
            if (checkLeft == mexicanValue || checkRight == mexicanValue
                    || checkLeft == train.getValue() || checkRight == train.getValue()) {
                validMove = true;
                break;
            } else if ((train.getValue() == -1 || mexicanValue == -1) && (checkLeft == e || checkRight == e)) {
                validMove = true;
                break;
            }
        }
        return validMove;
    }

    @Override
    public String toString() {
        return "Player" + getId() + ": " + hand.toString();
    }
}
