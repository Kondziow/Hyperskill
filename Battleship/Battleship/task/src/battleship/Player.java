package battleship;

import battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Field field;
    private List<Ship> listOfShips = new ArrayList<Ship>();

    public Player(String name, Field field) {
        this.name = name;
        this.field = field;
        this.listOfShips = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<Ship> getListOfShips() {
        return listOfShips;
    }

    public void setListOfShips(ArrayList<Ship> listOfShips) {
        this.listOfShips = listOfShips;
    }
}
