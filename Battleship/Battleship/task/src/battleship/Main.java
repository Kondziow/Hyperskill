package battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.getField().printField();
        game.start();
        //game.getField().placeShips();
        //game.getField().printField();
       /* Field field = new Field();
        field.printField();
        field.placeShips();
        field.printField();*/
    }
}
