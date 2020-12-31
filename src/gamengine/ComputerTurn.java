package gamengine;

import java.util.List;

import static gamengine.Turn.*;

/*Class for methods relating to a computer turn*/
public class ComputerTurn {

    /*main method for computer turn handling*/
    private static void computerPlay (List<Domino> hand, Computer computer, Board board) {

        if (!computer.checkValidMove(hand, computer.getTrain(), board)) {
            if (computer.getMarker()) {
                pass(computer, board, board.getPlayerTurn());
            } else {
                draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), computer.getHand(), computer);
            }
            return;
        }

        /*check for open doubles*/
        for (Train t : board.getTrainList()) {
            if (t.isMustBePlayed()) {
                for (Domino d :
                        hand) {
                    if (d.getLeft() == t.getValue() || d.getRight() == t.getValue()) {
                        play(t, hand, hand.indexOf(d), t.getValue(), computer, board);
                        return;
                    }
                }
                if (computer.getMarker()) {
                    pass(computer, board, board.getPlayerTurn());
                    return;
                }
                else {
                    verbose("Open double, no match found, drawing");
                    draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), computer.getHand(), computer);
                }
                return;
            }
        }

        /*check for open train play outside of combo*/
        if (board.getMexican().getValue() != -1 && computer.getTrain().getValue() != -1) {
            for (Player p :
                    board.getPlayerList()) {
                if (p.isOpenTrain() && p.getTrain() != computer.getTrain()) {
                    for (Domino d :
                            hand) {
                        if (!(computer.getRoundCombo().contains(d)) && (d.getLeft() == p.getTrain().getValue() ||
                                d.getRight() == p.getTrain().getValue() || (p.getTrain().getValue() == -1 &&
                                (d.getLeft() == board.getEngineValue() || d.getRight() == board.getEngineValue())
                        ))) {
                            play(p.getTrain(), hand, hand.indexOf(d), p.getTrain().getValue(), computer, board);
                            verbose("Playing on open train");
                            return;
                        }
                    }
                }
            }
        }

        /*check for mexican train play outside of combo*/
        for (Domino d:
                hand) {
            if (!(computer.getRoundCombo().contains(d)) && (d.getLeft() == board.getMexican().getValue() ||
                    d.getRight() == board.getMexican().getValue() || (board.getMexican().getValue() == -1 &&
                    (d.getLeft() == board.getEngineValue() || d.getRight() == board.getEngineValue())
            ))){
                play(board.getMexican(), hand, hand.indexOf(d), board.getMexican().getValue(), computer, board);
                verbose("Playing on mexican train");
                return;
            }
        }

        int handIndex = computer.getHandIndex(computer.getHand());

        if (computer.getRoundCombo().isEmpty()) {
            if (computer.getMarker())
                pass(computer, board, board.getPlayerTurn());
            else {
                draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), computer.getHand(), computer);
                computer.getRoundCombo().clear();
                Combo combo = new Combo();
                computer.searchCombo(computer.getHand(), computer.getTrain().getValue(), combo);
                computer.findBestCombo(computer.getComboList());
            }
            return;
        }

        if (computer.getTrain().getValue() == -1) {
            if (computer.getRoundCombo().peek().getLeft() != board.getEngineValue()
                    && computer.getRoundCombo().peek().getRight() != board.getEngineValue()) {
                computer.getRoundCombo().clear();
                Combo combo = new Combo();
                computer.searchCombo(computer.getHand(), board.getEngineValue(), combo);
                computer.findBestCombo(computer.getComboList());
                return;
            }
        } else {
            if (computer.getRoundCombo().peek().getRight() != computer.getTrain().getValue() &&
                    computer.getRoundCombo().peek().getLeft() != computer.getTrain().getValue()) {
                computer.getRoundCombo().clear();
                Combo combo = new Combo();
                computer.searchCombo(computer.getHand(), computer.getTrain().getValue(), combo);
                computer.findBestCombo(computer.getComboList());
                return;
            }
        }

        if (handIndex < 0) {
            Combo combo = new Combo();
            computer.getRoundCombo().clear();
            computer.searchCombo(computer.getHand(), computer.getTrain().getValue(), combo);
            computer.findBestCombo(computer.getComboList());
            handIndex = computer.getHandIndex(computer.getHand());
            if (handIndex < 0)
                return;
        }

        if (computer.getTrain().getValue() == -1 && (hand.get(handIndex).getLeft() == board.getEngineValue()
                || hand.get(handIndex).getRight() == board.getEngineValue())) {
            play(computer.getTrain(), hand, handIndex, board.getEngineValue(),
                    computer, board);
            computer.getRoundCombo().remove();
            verbose("Playing on personal train");
        }
        else if (hand.get(handIndex).getLeft() == computer.getTrain().getValue()
                || hand.get(handIndex).getRight() == computer.getTrain().getValue()){
            play(computer.getTrain(), hand, handIndex,
                    computer.getTrain().getValue(), computer, board);
            computer.getRoundCombo().remove();
            verbose("Playing on personal train");
        }

    }

    /*method for checking for computer and starting turn*/
    public static void computerTurn(Board board, Player player) {
        if (player.isComputer()) {
            Computer computer = (Computer) player;

            if (computer.checkValidMove(computer.getHand().getHand(), computer.getTrain(), board)) {
                if (!(computer.getRoundCombo().isEmpty())) {
                    computerPlay(computer.getHand().getHand(), computer, board);
                    System.out.println(board);
                    return;
                }
                Combo combo = new Combo();
                if (computer.getTrain().getValue() == -1) {
                    computer.searchCombo(computer.getHand(), board.getEngineValue(), combo);
                } else {
                    computer.searchCombo(computer.getHand(), computer.getTrain().getValue(), combo);
                }
                computer.findBestCombo(computer.getComboList());
                computerPlay(computer.getHand().getHand(), computer, board);
                System.out.println(board);
            }else
            if (computer.getMarker()) {
                pass(computer, board, board.getPlayerTurn());
            }else {
                draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), computer.getHand(), computer);
            }
        }
    }
}
