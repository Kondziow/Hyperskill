package battleship;

import battleship.ships.*;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player notCurrentPlayer;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        char[] tab;
        Ship ship;
        Ship ships[] = {new AircraftCarrier(), new Battleship(), new Submarine(), new Cruiser(), new Destroyer()};
        //Ship ships[] = {new AircraftCarrier()};

        for(int j = 0; j< 2; j++) {
            currentPlayer.getField().printField();
            for (int i = 0; i < ships.length; i++) {
                ship = ships[i];
                tab = readCordsToPlaceShip(ship.getLength(), ship.getName());
                this.currentPlayer.getField().placeShip(ship, tab[0], tab[1], tab[2], tab[3]);
                currentPlayer.getListOfShips().add(ship);
                this.currentPlayer.getField().printField();
            }
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
            changePlayer();

        }
        changePlayer();

        System.out.println("The game starts!\n");
        currentPlayer.getField().printFoggedField();
        System.out.println("Take a shot!\n");
        while(!this.currentPlayer.getField().isEndOfGame()) {
            changePlayer();
            this.notCurrentPlayer.getField().printFoggedField();
            System.out.println("---------------------");
            this.currentPlayer.getField().printField();
            shoot();
        }
       // field.printField();

    }
    private void changePlayer(){
        if(currentPlayer == player1) {
            currentPlayer=null;
            notCurrentPlayer=null;
            currentPlayer = player2;
            currentPlayer.setField(player2.getField());
            notCurrentPlayer = player1;
            notCurrentPlayer.setField(player1.getField());
        }
        else {
            currentPlayer=null;
            notCurrentPlayer=null;
            currentPlayer = player1;
            currentPlayer.setField(player1.getField());
            notCurrentPlayer = player2;
            notCurrentPlayer.setField(player2.getField());
        }
    }
    private void shoot() {
        System.out.println(this.currentPlayer.getName() + ", it's your turn:");
        char []tab = readCordsToShoot();
        currentPlayer.getField().shoot(tab[0], tab[1],notCurrentPlayer);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private char[] readCordsToShoot() {
        char letter = 0;
        char number = 0;

        do {
            Scanner scanner = new Scanner(System.in);

            String input = scanner.next();
            letter = input.charAt(0);
            number = input.charAt(1);
            if (input.length() == 3){
                char number2 = input.charAt(2);
                number = (char) (number + 9 + (number2 - '0'));
            }

        } while (!validateShooting(letter, number));

        char[] tab = new char[]{letter, number};
        return tab;
    }

    private boolean validateShooting(char letter, char number) {
        if (number < 0 || number > ('1' + 9) || letter < 'A' || letter > 'J'){
            System.out.println("Error! You entered the wrong coordinates! Try again: \n");
            return false;
        }
        return true;
    }

    private char[] readCordsToPlaceShip(int length, String shipName) {
        System.out.println("Enter the coordinates of the " + shipName + " (" + length + " cells):\n");
        char letter1 = 0;
        char number1 = 0;
        char letter2 = 0;
        char number2 = 0;

        do {
            Scanner scanner = new Scanner(System.in);

            String input = scanner.next();
            letter1 = input.charAt(0);
            number1 = input.charAt(1);
            //System.out.println(number1);
            if (input.length() == 3) number1 += 9;


            input = scanner.next();
            letter2 = input.charAt(0);
            number2 = input.charAt(1);
            if (input.length() == 3) number2 += 9;

        } while (!validatePlacingShip(letter1, number1, letter2, number2, length, shipName));

        char[] tab = new char[]{letter1, number1, letter2, number2};
        return tab;
    }

    private boolean validatePlacingShip(char letter1, char number1, char letter2, char number2, int length, String shipName) {
        if (Math.abs(letter1 - letter2) + 1 != length && Math.abs(number1 - number2) + 1 != length) {   //Jezeli dlugosc nieodpowiednia
            System.out.println("Error! Wrong length of the " + shipName + "! Try again:\n");
            return false;
        } else if ((letter1 != letter2 && number1 != number2) || letter1 > 'J' || letter2 > 'J') {  //Jezeli nie w lini prostej
            System.out.println("Error! Wrong " + shipName + " location! Try again:\n");
            return false;
        } else if (!currentPlayer.getField().canPlace(letter1, number1, letter2, number2, length)) {
            System.out.println("Error! You placed it too close to another one. Try again:\n");
            return false;
        }
        return true;
    }

    public Game() {
        this.player1 = new Player("Player 1", new Field());
        this.player2 = new Player("Player 2", new Field());
        this.currentPlayer = player1;
        this.notCurrentPlayer = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
