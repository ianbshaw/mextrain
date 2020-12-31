package gamengine;

import java.util.List;

/*Class containing methods relating to round and game end logic*/
public class Round {

    /*method to check for round end*/
    public static boolean roundEnd(List<Player> playerList, Board board) {

        if (board.getBoneyard().getBoneyardNum() == 0) {
            scoreTally(playerList);
            return true;
        }
        for (Player p : playerList) {
            if (p.getHand().getHand().size() == 0) {
                scoreTally(playerList);
                return true;
            }
        }
        return false;
    }


    /*method to tally score at round end*/
    private static void scoreTally(List<Player> playerList) {
        for(Player player : playerList) {
            for(Domino domino : player.getHand().getHand())
                player.setScore(player.getScore() + domino.getValue());
        }
    }


    /*method to check for game end*/
    public static boolean checkGameEnd(Board board) {
        if (board.getCenter().getTrain().isEmpty())
            board.setEngineValue(-1);
        return board.getEngineValue() != -1;
    }

    /*method to check for game winner*/
    public static Player checkGameWinner(List<Player> playerList) {
        Player winner = playerList.get(0);

        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getScore() < winner.getScore()) winner = playerList.get(i);
        }
        return winner;
    }

}
