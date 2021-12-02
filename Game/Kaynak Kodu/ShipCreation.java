// Bu classı oyunu space invaders yapmaya çalışırken oluşturmuştum
public class ShipCreation{
    private Keyboard keyboard = new Keyboard();
    private Ship ship = new Ship();

    public void createShip(Ship ship, Keyboard keyboard) {

    }

    public void setKeyboard(Keyboard keyboard) {
    this.keyboard = keyboard;
    }
    public void setShip(Ship ship) {
    this.ship = ship;
    }

    public Keyboard getKeyboard() {
    return keyboard;
    }
    public Ship getShip() {
    return ship;
    }

}
