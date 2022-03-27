import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuListener implements MouseListener {
    private int Event = 1;
    private Menu menu = null;
    private MenuPanel panel = null;

    public void setAll(int i, Menu menu, MenuPanel panel) {
        Event = i;
        this.menu = menu;
        this.panel = panel;
    }
    public int getEvent() {
        return Event;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Event == 1) {
            menu.StartGame(panel.getTextFieldText(), panel.getTextField2Text(), panel.getTextField3Text());
        } else {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
