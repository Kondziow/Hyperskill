package battleship;

import battleship.ships.*;

import java.util.Scanner;

public class Game {
    private Field field;

    public void start() {
        char[] tab;
        Ship ship;
        Ship ships[] = {new AircraftCarrier(), new Battleship(), new Submarine(), new Cruiser(), new Destroyer()};

        for(int i = 0; i < 5; i++){
            ship = ships[i];
            tab = readCordsToPlaceShip(ship.getLength(), ship.getName());
            this.field.placeShip(ship, tab[0], tab[1], tab[2], tab[3]);
            this.field.printField();
        }

        /*ship = new AircraftCarrier();
        tab = readCordsToPlaceShip(ship.getLength(), ship.getName());
        this.field.placeShip(ship, tab[0], tab[1], tab[2], tab[3]);
        this.field.printField();

        ship = new Battleship();
        tab = readCordsToPlaceShip(4);
        this.field.placeShip(new Battleship(), tab[0], tab[1], tab[2], tab[3]);
        this.field.printField();

        ship = new Battleship();
        tab = readCordsToPlaceShip(3);
        this.field.placeShip(new Submarine(), tab[0], tab[1], tab[2], tab[3]);
        this.field.printField();

        ship = new Battleship();
        tab = readCordsToPlaceShip(3);
        this.field.placeShip(new Cruiser(), tab[0], tab[1], tab[2], tab[3]);
        this.field.printField();

        ship = new Battleship();
        tab = readCordsToPlaceShip(2);
        this.field.placeShip(new Destroyer(), tab[0], tab[1], tab[2], tab[3]);
        this.field.printField();*/
    }

    private char[] readCordsToPlaceShip(int length, String shipName) {
        System.out.println("Enter the coordinates of the " + shipName + " (" + length + " cells):");
        boolean done = false;
        char letter1 = 0;
        char number1 = 0;
        char letter2 = 0;
        char number2 = 0;
        int num1 = 0;
        int num2 = 0;
        while (!done) {
            done = true;
            Scanner scanner = new Scanner(System.in);

            String input = scanner.next();
            letter1 = input.charAt(0);
            number1 = input.charAt(1);
            if(input.length() == 3) number1+= 9;


            input = scanner.next();
            letter2 = input.charAt(0);
            number2 = input.charAt(1);
            if(input.length() == 3) number2+= 9;

            if (Math.abs(letter1 - letter2) + 1 != length && Math.abs(number1 - number2) + 1 != length) {   //Jezeli dlugosc nieodpowiednia
                done = false;
                System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
            } else if (letter1 != letter2 && number1 != number2) {  //Jezeli nie w lini prostej
                done = false;
                System.out.println("Error! Wrong " + shipName + " location! Try again:");
            } else if (!field.canPlace(letter1, number1, letter2, number2, length)) {
                done = false;
                System.out.println("Error! You placed it too close to another one. Try again:");
            }
        }

        char[] tab = new char[]{letter1, number1, letter2, number2};
        return tab;
    }

    public Game() {
        this.field = new Field();
    }

    public Field getField() {
        return field;
    }
}
