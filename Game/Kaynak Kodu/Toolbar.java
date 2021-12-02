import javax.swing.JToolBar;
import javax.swing.JButton;

public class Toolbar extends JToolBar {
    private final JToolBar toolBar = new JToolBar();
    private final JButton button = new JButton();

    public void setBar() {
        button.setText("Next Turn");
        this.add(button);
    }

    public JButton getButton() {
        return button;
    }
}
