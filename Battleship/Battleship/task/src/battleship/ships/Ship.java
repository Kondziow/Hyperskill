package battleship.ships;

public abstract class Ship {
    private final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private int length;
    private String name;
    private char[][] position;     //[0,:] - literka, [1,:] - cyferka

    public Ship(int length, String name) {
        this.length = length;
        position = new char[2][length];
        this.name = name;
    }

    public void placeShip(char letter1, char number1,char letter2, char number2){
        if(letter1 == letter2){
            if(number1 > number2){
                char temp = number1;
                number1 = number2;
                number2 = temp;
            }
            for(int i = 0; i < length; i++){
                position[0][i] = letter1 ;
                position[1][i] = (char) (number1 + (char)i);
                //System.out.println(position[1][i]);
            }
        } else if (number1 == number2) {
            if(letter1 > letter2){
                char temp = letter1;
                letter1 = letter2;
                letter2 = temp;
            }
            for(int i = 0; i < length; i++){
                position[0][i] = (char) (letter1 + (char)i);
                position[1][i] = number1;
                //System.out.println(position[0][i]);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public char[][] getPosition() {
        return position;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPosition(char[][] position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}
