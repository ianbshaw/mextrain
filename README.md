# Mexican Train
Ian Bradshaw

Simulates a game of Mexican Train through a command line or a graphic interface

To run GUI: `java -jar MexicanTrainGui.jar`
To run CLI: `java -jar MexicanTrainCli.jar`

CLI
---
Create 2-4 players human or computer

To play a domino press 'p' and select '1-4' player trains or 'm' for the mexican train

To draw press 'd'

To pass press 's'

To quit out of the game press 'q'


GUI
---
Create 2-4 players human or computer

Drop menus are provided for the dominoes in hand and the trains

*The Mexican train will always be the last train on the list*

Gui is set with static locations for objects, so potential for things looking weird there with more players added


Game Engine
---
Game engine is created with a single static board that holds player objects, mexican train and boneyard

players hold hands and personal trains


Turn class is created to handle the logic of the single turn

ComputerTurn is created to handle computer turn logic in tandem with the Turn class

Bugs
---
After closer inspection, the game end logic to determine the winner, was needlessly complicated
and results in errors


    public static Player checkGameWinner(List<Player> playerList) {
        Player winner = playerList.get(0);

        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getScore() < winner.getScore()) winner = playerList.get(i);
        }
        return winner;
    }


is cleaner and works



