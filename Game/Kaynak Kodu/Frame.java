import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    private final int Width = 1366;
    private final int Height = 720;
    private final FirstPanel panel = new FirstPanel();
    private final Toolbar toolbar = new Toolbar();

    private void setup(String div, String map, String edge) {
        setBackground(new Color(24, 154, 180));
        setSize(Width, Height);
        setVisible(true);
        setResizable(false);
        setBackground(new Color(24,154,180));

        panel.startPanel(div, map, edge);
        add(panel);
        add(panel.getInfoPanel());

        useToolbar();

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(24,154,180));
        panel2.setBounds(0,50,1366,20);
        add(panel2);

        addKeyListener(panel.returnCameraKey());
        requestFocusInWindow();
        setDefaultCloseOperation(Close());

    }

    public void useToolbar() {
        toolbar.setBar();
        toolbar.getButton().addActionListener(this::NextTurn);
        toolbar.getButtonSave().addActionListener(this::Save);
        add(toolbar, BorderLayout.NORTH);
    }

    public void startFrame(String div, String map, String edge) {
        setup(div, map, edge);
    }

    public void NextTurn(ActionEvent e) {
        panel.NextTurn(); requestFocusInWindow();
    }
    public void Save(ActionEvent e) {
        panel.Save(); requestFocusInWindow();
    }

    public int Close() {
        panel.Save();
        return EXIT_ON_CLOSE;
    }
}
