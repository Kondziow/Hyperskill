package battleship;

import battleship.ships.AircraftCarrier;
import battleship.ships.Ship;

import java.util.ArrayList;
import java.util.Scanner;

public class Field {
    private final static int fieldSize = 11;   //pole do gry -> 10
    private final static char fogOfWar = '~';
    private final static char placedShip = 'O';
    private final static char missed = 'M';
    private final static char hit = 'X';
    private final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final char[][] field;
    private ArrayList<Ship> listOfShips = new ArrayList<Ship>();

    public Field() {
        field = new char[fieldSize][fieldSize];
        InitField();
    }

    public void shoot(char letter, char number){
        char let = (char) (letter - 'A' + 1);
        int num = number - '0';
        if(this.field[let][num] == placedShip){
            this.field[let][num] = hit;
            printFoggedField();
            System.out.printf("You hit a ship!\n");
        }
        else {
            this.field[let][num] = missed;
            printFoggedField();
            System.out.println("You missed!\n");
        }


    }

    private void InitField() {
        for (int i = 0; i < fieldSize; i++) {   //wiersz - i
            for (int j = 0; j < fieldSize; j++) {    //kolumna - j
                if (i == 0 && j == 0) field[i][j] = ' ';
                else if (i == 0) field[i][j] = (char) (j + '0'); //pierwszy wiersz -> numer kolumny
                else if (j == 0) field[i][j] = letters[i - 1];//pierwsza kolumna -> litera wiersza
                else field[i][j] = fogOfWar;
            }
        }
    }

    public void printField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == ':') System.out.print("10");
                else System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFoggedField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == ':') System.out.print("10");
                else if(field[i][j] == placedShip) System.out.print(fogOfWar + " ");
                else System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeShip(Ship ship, char letter1, char number1, char letter2, char number2) {
        ship.placeShip(letter1, number1, letter2, number2);
        listOfShips.add(ship);

        for (int i = 0; i < ship.getLength(); i++) {
            int letterPosition = 0;
            int numberPosition = (ship.getPosition()[1][i] - '0');
            for (int j = 0; j < letters.length; j++)
                if (ship.getPosition()[0][i] == letters[j]) {
                    letterPosition = j + 1;
                    break;
                }
            field[letterPosition][numberPosition] = placedShip;
        }
    }

    public boolean canPlace(char letter1, char number1, char letter2, char number2, int length) {

        if (letter1 > letter2) {
            char temp = letter1;
            letter1 = letter2;
            letter2 = temp;
        }
        if (number1 > number2) {
            char temp = number1;
            number1 = number2;
            number2 = temp;
        }
        char let1 = (char) (letter1 - 'A' + 1);
        char let2 = (char) (letter2 - 'A' + 1);
        for (int i = let1; i <= let2; i++) {
            for (int j = (number1 - '0'); j <= (number2 - '0'); j++) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if(i+x > 0 && i+x < field.length && j + y > 0 && j + y < field.length)
                            if (field[i + x][j + y] == placedShip) return false;
                    }
                }
            }
        }
        return true;
    }

}
