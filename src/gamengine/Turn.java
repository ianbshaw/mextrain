package gamengine;

import java.util.List;
import java.util.Random;

/*Class containing methods relating to turn logic for players*/
public class Turn {

    /*function to draw domino from boneyard*/
    public static void draw(Boneyard boneyard, int boneyardNum, Hand hand, Player player) {
        if (player.getMarker()) {
            verbose("Already drawn, play or pass");
            return;
        }
        if (boneyardNum == 0)
            return;
        player.setMarker(true);
        Random r = new Random();
        int index = 0;
        if (boneyardNum > 1)
            index = r.nextInt(boneyardNum - 1);

        hand.getHand().add(boneyard.getBoneyard().get(index));
        boneyard.getBoneyard().remove(index);
        boneyard.setBoneyardNum(boneyardNum - 1);
        verbose("Player " + player.getId() + " draws");

    }

    /*function to handle player turn passing*/
    public static void pass(Player player, Board board, int turn) {
        if(!player.getMarker()) {
            return;
        } else {
            player.setOpenTrain(true);
            player.setMarker(false);
        }
        board.setPlayerTurn(turn + 1);
        if (board.getPlayerTurn() > board.getPlayerList().size()) board.setPlayerTurn(1);
        verbose("Player " + board.getPlayerTurn() + "'s turn");
    }

    /*logic for handling playing a domino*/
    public static void play(Train train, List<Domino> hand, int handIndex, int value, Player player, Board board) {

        if (board.getCenter().getTrain().get(0).getLeft() != board.getEngineValue()) {
            board.setEngineValue(board.getCenter().getTrain().get(0).getLeft());
        }

        /*check for trains with doubles*/
        for (Train t : board.getTrainList()) {
            if (t.isMustBePlayed()) {
                if (train == t  || player.isPlayedDouble()) {
                    break;
                }
                else if (hand.get(handIndex).isDouble()) break;
                else {
                    verbose("Must close open double or play double");
                    return;
                }
            }
        }

        /*Check if train value is on right side of played domino*/
        if (hand.get(handIndex).getRight() == value) {
            int temp = hand.get(handIndex).getLeft();
            hand.get(handIndex).setLeft(hand.get(handIndex).getRight());
            hand.get(handIndex).setRight(temp);
        }

        /*Check if mexican train or personal train have been started*/
        if (board.getMexican().getValue() == -1 && player.getTrain().getValue() == -1) {
            if ( train == board.getMexican() || train == player.getTrain() ) {
                if ( player.getHand().getHand().get(handIndex).getLeft() != board.getEngineValue() &&
                        player.getHand().getHand().get(handIndex).getRight() != board.getEngineValue() ) {
                    verbose("Must start personal train or Mexican train with engine");
                    return;
                }
            }else {
                verbose("Must start personal train or Mexican train with engine");
                verbose(board.getMexican().toString());
                return;
            }
        }


        /*Check if train value matches domino played
          Place domino in correct place*/
        if ((hand.get(handIndex).getLeft() == train.getValue() || train.getValue() == -1)) {
            train.setValue(hand.get(handIndex).getRight());   // set new train value
            train.add(hand.get(handIndex));                   // add domino to train

            if (train.isMustBePlayed()) {
                for (Player p:
                     board.getPlayerList()) {
                    if (p.getTrain() == train) {
                        p.setOpenTrain(false);
                        break;
                    }
                }
            }

            if (train == player.getTrain()) {                           // turn off marker and close train
                player.setMarker(false);
                player.setOpenTrain(false);
            }
            if (!player.getMarker() || !train.isMustBePlayed())
                player.setOpenTrain(false);

            if (!hand.get(handIndex).isDouble()) {             // update player turn
                board.setPlayerTurn(board.getPlayerTurn() + 1);
                train.setMustBePlayed(false);
                player.setPlayedDouble(false);
            }
            else {
                player.setPlayedDouble(true);
                train.setMustBePlayed(true);                            // set flag for double
                verbose("Opening train to close double");
                hand.remove(handIndex);
                return;
            }


            hand.remove(handIndex);


            if (board.getPlayerTurn() > board.getPlayerList().size())
                board.setPlayerTurn(1);


        } else {
            train.setValue(value);
            verbose("Invalid play");
        }
    }

    public static void verbose(String s) {
        System.out.println(s);
    }

}
