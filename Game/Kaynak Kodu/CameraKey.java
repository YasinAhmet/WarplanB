import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CameraKey implements KeyListener{

    private Camera camera = new Camera();
    private JPanel panel;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int x = camera.getX();
        int y = camera.getY();

        if (e.getKeyCode() == 37) {
            camera.setX(x - 10);

        }

        else if (e.getKeyCode() == 39) {
            camera.setX(x + 10);

        }

        else if (e.getKeyCode() == 38) {
            camera.setY(y - 10);

        }

        else if (e.getKeyCode() == 40) {
            camera.setY(y + 10);

        }

        if(panel != null) {
            panel.repaint();
        }

        }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }
    public void addUpdate(JPanel panel) {
        this.panel = panel;
    }
}
