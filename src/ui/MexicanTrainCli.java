package ui;

import gamengine.*;

import java.util.Scanner;

import static gamengine.BoardSetup.gameInit;
import static gamengine.BoardSetup.shuffle;
import static gamengine.ComputerTurn.computerTurn;
import static gamengine.Round.*;
import static gamengine.Turn.*;

/*main game class for CLI version of Mexican Train*/
public class MexicanTrainCli {

    private static final Board board = new Board();

    public static void main(String[] args) {

        System.out.println("How many human players?");
        Scanner scanner = new Scanner(System.in);
        int numPlayer = scanner.nextInt();
        int numCompPlayer = 0;
        if (numPlayer < 4) {
            scanner.nextLine();
            System.out.println("How many computer players?");
            numCompPlayer = scanner.nextInt();
        }
        scanner.nextLine();
        try {
            gameInit(numPlayer, numCompPlayer, board, board.getEngineValue());

        }catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter 2-4 players");
        }
        playStart();
    }

    public static void playStart() {

        while (checkGameEnd(board))
            playMenu(board);

        Player winner = checkGameWinner(board.getPlayerList());
        System.out.println("Player " + winner.getId() + " wins!!");
        System.out.println(board.toString());


    }

    public static void playMenu(Board board) {

        /*check for round end*/
        if (roundEnd(board.getPlayerList(), board)) {
            verbose("Round end, shuffling...");
            shuffle(board);
            return;
        }

        int turn = board.getPlayerTurn();

        Player player = board.getPlayerList().get((turn - 1));

        System.out.println("Player " + turn + "'s Turn:\n");

        if (player.isComputer()) {
            computerTurn(board, player);
            return;
        }

        player.setOpenTrain(true);

        System.out.println(board.toString());
        System.out.println("[p] play domino\n" + "[d] draw domino\n" + "[s] pass\n" + "[q] quit\n");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next(".").charAt(0);
        if (choice == 'p') {
            if (player.checkValidMove(player.getHand().getHand(), player.getTrain(), board)) {
                Train train = new Train();
                int handIndex;
                System.out.println("Which domino?");
                handIndex = scanner.nextInt() - 1;
                if (handIndex < 0 || handIndex + 1 > player.getHand().getHand().size())
                    playMenu(board);
                scanner.nextLine();
                System.out.println("Which train? (Mexican = [m], Player Train = [1,2,3,4])");
                String s = scanner.nextLine();
                switch (s) {
                    case "m":
                        train = board.getMexican();
                        break;
                    case "1":
                        if (board.getPlayerList().get(0).isOpenTrain() || board.getPlayerList().get(0).getTrain().isMustBePlayed()
                                || player.getTrain() == board.getPlayerList().get(0).getTrain())
                            train = board.getPlayerList().get(0).getTrain();
                        break;
                    case "2":
                        if (board.getPlayerList().get(1).isOpenTrain() || board.getPlayerList().get(1).getTrain().isMustBePlayed()
                                || player.getTrain() == board.getPlayerList().get(1).getTrain())
                            train = board.getPlayerList().get(1).getTrain();
                        break;
                    case "3":
                        if (board.getPlayerList().size() >= 3) {
                            if (board.getPlayerList().get(2).isOpenTrain() || board.getPlayerList().get(2).getTrain().isMustBePlayed()
                                    || player.getTrain() == board.getPlayerList().get(2).getTrain())

                                train = board.getPlayerList().get(2).getTrain();
                        }
                        break;
                    case "4":
                        if (board.getPlayerList().size() >= 4) {
                            if (board.getPlayerList().get(3).isOpenTrain() || board.getPlayerList().get(3).getTrain().isMustBePlayed()
                                    || player.getTrain() == board.getPlayerList().get(3).getTrain())
                                train = board.getPlayerList().get(3).getTrain();
                        }
                        break;
                    default:
                        System.out.println("You selected an invalid Train");
                        return;

                }
                if (train.getValue() == -1)
                    Turn.play(train, player.getHand().getHand(), handIndex, board.getEngineValue(), player, board);
                else Turn.play(train, player.getHand().getHand(), handIndex, train.getValue(), player, board);

            }
            else {
                verbose("No valid play, draw or pass");
            }
        }

        else if (choice == 'd') {
            draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), player.getHand(), player);
        }
        else if (choice == 'q') {
            board.setEngineValue(-1);
            playStart();
        }
        else if (choice == 's') {
            Turn.pass(player, board, turn);
        }
        else if (choice == 'b') {
            System.out.println(board.getEngineValue());
            System.out.println(board.getPlayerTurn());
            System.out.println(board.getPlayerList().get(1).getTrain());
        }

    }

}
