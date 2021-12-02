import javax.swing.JToolBar;
import javax.swing.JButton;

public class Toolbar extends JToolBar {
    private final JToolBar toolBar = new JToolBar();
    private final JButton button = new JButton();
    private final JButton buttonSave = new JButton();

    public void setBar() {
        button.setText("Next Turn");
        buttonSave.setText("Save");
        this.add(button);
        this.add(buttonSave);
    }

    public JButton getButton() {
        return button;
    }
    public JButton getButtonSave() {
        return  buttonSave;
    }
}
