package gamengine;
import java.util.ArrayList;
import java.util.List;

/*Class representing game board object*/
public class Board {

    /*Game Objects created at game start*/
    private static final List<Player> playerList = new ArrayList<>();
    private static final Boneyard boneyard = new Boneyard();
    private static final Train center = new Train();
    private static final Train mexican = new Train();
    private static final List<Train> trainList = new ArrayList<>();

    /*Reference values*/
    private static int playerTurn, engineValue;

    public Board() {
        center.setValue(-2);
        trainList.add(mexican);
        engineValue = 9;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Boneyard getBoneyard() {
        return boneyard;
    }

    public Train getCenter() {
        return center;
    }

    public Train getMexican() {
        return mexican;
    }

    public int getPlayerTurn() { return playerTurn; }

    public void setPlayerTurn(int playerTurn2) {
        playerTurn = playerTurn2;
    }

    public List<Train> getTrainList() {
        return trainList;
    }

    public int getEngineValue() {
        return engineValue;
    }

    public void setEngineValue(int engineValue) {
        Board.engineValue = engineValue;
    }

    /*CLI board*/
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder scores = new StringBuilder("Game State:-------------------------" +
                "---------------------------------------------------------------------------------\n");

        for (Player p : playerList) {
            scores.append("Player ").append(p.getId()).append(": ").append(p.getScore()).append("\n");
            s.append(p.toString()).append("\n");
        }
        s.append("\nCenter:").append(center.toString());
        s.append("\n\nMexican:").append(mexican.toString());

        for (Train t: trainList) {
            if (t.isMustBePlayed())
                s.append("\n\nDouble:").append(t.toString());
        }

        for (Player p : playerList) {
            if (p.isOpenTrain() || p.getTrain().isMustBePlayed())
                s.append("\n\nP").append(p.getId()).append("Train:").append(p.getTrain().toString());
        }

        s.append("\n\nBoneyard: ").append(boneyard.getBoneyard().toString());

        return scores.toString() + s.toString();
    }
}
