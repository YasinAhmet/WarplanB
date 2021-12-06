import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class Keybinds {
    private JLabel obj1 = new JLabel();
    private Ship ship;


    public void keybind() {

        Action leftAction = new LeftAction();
        Action rightAction = new RightAction();
        Action upAction = new UpAction();
        Action downAction = new DownAction();

        obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "upAction");
        obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "downAction");
        obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "rightAction");
        obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "leftAction");

        obj1.getActionMap().put("upAction", upAction);
        obj1.getActionMap().put("rightAction", rightAction);
        obj1.getActionMap().put("downAction", downAction);
        obj1.getActionMap().put("leftAction", leftAction);
    }


    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int y = ship.getY();
            ship.setY(y + 10);
        }
    }
    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int y = ship.getY();
            ship.setY(y - 10);
        }
    }
    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x = ship.getX();
            ship.setX(x + 10);
        }
    }
    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
             int x = ship.getX();
             ship.setX(x - 10);
        }
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
    public JLabel getObj1() {
        return obj1;
    }

}

