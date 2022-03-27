import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {
    private JTextField textField;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel label;
    private JLabel label2;
    private JLabel label3;

    public void setPanel() {
        setBackground(new Color(24, 154, 180));
        setSize(720, 366);
        Border border = BorderFactory.createLineBorder(new Color(32, 159, 170));
        Color bckColor = new Color(44, 134, 150);

        label = new JLabel();
        label.setText("Scenario's division file name:");
        add(label);

        textField = new JTextField(20);
        textField.setBackground(bckColor);
        textField.setBorder(border);
        add(textField);
        textField.setText("divisions");

        label2 = new JLabel();
        label2.setText("Scenario's map file name:");
        add(label2);

        textField2 = new JTextField(20);
        textField2.setBackground(bckColor);
        textField2.setBorder(border);
        add(textField2);
        textField2.setText("hexagons");

        label3 = new JLabel();
        label3.setText("Scenario's map file name:");
        add(label3);

        textField3 = new JTextField(20);
        textField3.setBackground(bckColor);
        textField3.setBorder(border);
        add(textField3);
        textField3.setText("edges");
    }

    public String getTextFieldText() {
        return textField.getText();
    }
    public String getTextField2Text() {
        return textField2.getText();
    }

    public String getTextField3Text() {
        return textField3.getText();
    }
}
