import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    private final int Width = 1366;
    private final int Height = 720;
    private final FirstPanel panel = new FirstPanel();
    private final Toolbar toolbar = new Toolbar();

    private void setup() {
        setSize(Width, Height);
        setVisible(true);
        setResizable(false);

        toolbar.setBar();
        toolbar.getButton().addActionListener(this::NextTurn);
        toolbar.getButtonSave().addActionListener(this::Save);
        add(toolbar, BorderLayout.NORTH);

        panel.startPanel();
        add(panel);
        add(panel.getInfoPanel());


        addKeyListener(panel.returnCameraKey());
        setDefaultCloseOperation(Close());

    }

    public void startFrame() {
        setup();
    }

    public void NextTurn(ActionEvent e) {
        panel.NextTurn();
    }
    public void Save(ActionEvent e) {
        panel.Save();
    }

    public int Close() {
        panel.Save();
        return EXIT_ON_CLOSE;
    }
}