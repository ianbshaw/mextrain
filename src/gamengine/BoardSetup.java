package gamengine;

import java.util.List;

import static gamengine.Turn.draw;
import static gamengine.Turn.verbose;

/*Class containing methods used to create and update game objects*/
public class BoardSetup {

    /*initial board setup*/
    public static void gameInit(int numPlayers, int numCompPlayers, Board board, int engineValue) {
        board.setPlayerTurn(1);
        setEngine(board, engineValue);
        playerCreation(numPlayers, board);
        computerCreation(numCompPlayers, board, numPlayers);
        drawHands(board.getPlayerList(), numPlayers + numCompPlayers, board);
    }

    /*method to find double domino based on current engine*/
    private static void setEngine(Board board, int engineValue) {
        Boneyard boneyard = board.getBoneyard();
        board.setEngineValue(engineValue);
        verbose("Engine Value " + engineValue);

        /*Set center train with double 9*/
        for (int i =0; i < boneyard.getBoneyardNum(); i++) {
            if (boneyard.getBoneyard().get(i).getValue() == engineValue * 2
                    && boneyard.getBoneyard().get(i).isDouble()) {
                board.getCenter().getTrain().add(boneyard.getBoneyard().get(i));
                boneyard.getBoneyard().remove(i);
                boneyard.setBoneyardNum(boneyard.getBoneyardNum() - 1);
                break;
            }
        }
    }

    /*Create players and draw initial hands*/
    private static void playerCreation(int numPlayers, Board board) {

        for (int i = 0;  i < numPlayers; i++) {
            Player player = new Player();
            player.setId(i + 1);
            board.getPlayerList().add(player);
            board.getTrainList().add(player.getTrain());
        }
    }

    /*Create computers and draw initial hands*/
    private static void computerCreation(int numComputers, Board board, int numPlayers) {

        for (int i = 0;  i < numComputers; i++) {
            Computer computer = new Computer();
            computer.setId(numPlayers + i + 1);
            board.getPlayerList().add(computer);
            board.getTrainList().add(computer.getTrain());
        }
    }

    /*method to draw hands at round start*/
    private static void drawHands(List<Player> playerList, int numPlayers, Board board) {
        int drawInit;
        if (numPlayers == 2) {
            drawInit = 15;
        } else if (numPlayers == 3) {
            drawInit = 13;
        } else if (numPlayers == 4) {
            drawInit = 10;
        } else {
            System.out.println(numPlayers);
            return;
        }

        for (Player player : playerList) {
            for (int j = 0; j < drawInit; j++) {
                player.setMarker(false);
                draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), player.getHand(),
                        player);
                player.setMarker(false);
            }
        }
    }

    /*resets board for next round*/
    public static void shuffle(Board board) {

        /*cycle through players and add their dominoes to boneyard and clear hands*/
        for (Player p : board.getPlayerList()) {
            for (int i = 0; i < p.getHand().getHand().size(); i++) {
                board.getBoneyard().getBoneyard().add(p.getHand().getHand().get(i));
                board.getBoneyard().setBoneyardNum(board.getBoneyard().getBoneyardNum() + 1);
            }
            p.getHand().getHand().clear();
            p.setOpenTrain(false);
            if (p.isComputer()) {
                Computer c = (Computer) p;
                c.getRoundCombo().clear();
            }
        }

        /*cycle through trains and add to boneyard*/
        for (Train t : board.getTrainList()) {
            for (int i = 0; i < t.getTrain().size(); i++) {
                board.getBoneyard().getBoneyard().add(t.getTrain().get(i));
                board.getBoneyard().setBoneyardNum(board.getBoneyard().getBoneyardNum() + 1);
            }
            t.setValue(-1);
            t.setMustBePlayed(false);
            t.getTrain().clear();
        }
        board.getBoneyard().getBoneyard().add(board.getCenter().getTrain().get(0));
        board.getCenter().getTrain().clear();

        /*set engine to 9 counting down, draw new hands and reset turn counter*/
        setEngine(board, board.getEngineValue() - 1);
        board.getCenter().setValue(board.getEngineValue());
        drawHands(board.getPlayerList(), board.getPlayerList().size(), board);
        board.setPlayerTurn(1);
    }
}
