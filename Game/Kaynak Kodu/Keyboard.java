import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

    private Ship ship = new Ship();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int x = ship.getX();
        int y = ship.getY();

        switch (e.getKeyChar()) {
            case 'a' -> ship.setX(x - 10);
            case 'd' -> ship.setX(x + 10);
            case 'w' -> ship.setY(y - 10);
            case 's' -> ship.setY(y + 10);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }
}
