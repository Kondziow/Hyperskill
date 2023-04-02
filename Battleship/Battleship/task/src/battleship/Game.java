package battleship;

import battleship.ships.*;

import java.util.Scanner;

public class Game {
    private Field field;

    public void start() {
        char[] tab;
        Ship ship;
        Ship ships[] = {new AircraftCarrier(), new Battleship(), new Submarine(), new Cruiser(), new Destroyer()};
        //Ship ships[] = {new AircraftCarrier()};

        for (int i = 0; i < ships.length; i++) {
            ship = ships[i];
            tab = readCordsToPlaceShip(ship.getLength(), ship.getName());
            this.field.placeShip(ship, tab[0], tab[1], tab[2], tab[3]);
            this.field.printField();
        }

        System.out.println("The game starts!\n");
        field.printFoggedField();
        System.out.println("Take a shot!\n");
        while(!this.field.isEndOfGame()) {
            shoot();
        }
       // field.printField();

    }

    private void shoot() {
        char []tab = readCordsToShoot();
        field.shoot(tab[0], tab[1]);
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
        } else if (!field.canPlace(letter1, number1, letter2, number2, length)) {
            System.out.println("Error! You placed it too close to another one. Try again:\n");
            return false;
        }
        return true;
    }

    public Game() {
        this.field = new Field();
    }

    public Field getField() {
        return field;
    }
}
