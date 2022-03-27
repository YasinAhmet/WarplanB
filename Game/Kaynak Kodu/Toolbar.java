import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Toolbar extends JToolBar {
    private final JButton button = new JButton();
    private final JButton buttonSave = new JButton();

    public void setBar() {
        setBackground(new Color(24,154,180));
        button.setText("Next Turn");
        buttonSave.setText("Save");

        button.setForeground(new Color(24,154,180));
        buttonSave.setForeground(new Color(24,154,180));

        button.setBackground(new Color(5,68,94));
        buttonSave.setBackground(new Color(5,68,94));

        Border border = BorderFactory.createLineBorder(new Color(5,68,94));
        Border border2 = BorderFactory.createLineBorder(new Color(24,154,180));

        this.setBorder(border2);
        button.setBorder(border);
        buttonSave.setBorder(border);

        this.add(button);
        addSeparator();
        this.add(buttonSave);
    }

    public JButton getButton() {
        return button;
    }
    public JButton getButtonSave() {
        return  buttonSave;
    }
}
