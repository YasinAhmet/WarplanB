import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    private final int Width = 1366;
    private final int Height = 720;
    private final FirstPanel firstPanel = new FirstPanel();
    private final Toolbar toolbar = new Toolbar();
    public HexagonWriter hexagonWriter = new HexagonWriter();
    public DivisionWriter divisionWriter = new DivisionWriter();
    public EdgeWriter edgewriter = new EdgeWriter();

    private void setup(String div, String map, String edge) {
        firstPanel.master = this;
        setBackground(new Color(24, 154, 180));
        setSize(Width, Height);
        setVisible(true);
        setResizable(false);
        setBackground(new Color(24,154,180));

        firstPanel.startPanel(div, map, edge);
        add(firstPanel);
        add(firstPanel.getInfoPanel());

        useToolbar();

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(24,154,180));
        panel2.setBounds(0,50,1366,20);
        add(panel2);

        addKeyListener(firstPanel.returnCameraKey());
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
        firstPanel.NextTurn(); requestFocusInWindow();
    }
    public void Save(ActionEvent e) {
        Save(); requestFocusInWindow();
    }

    public void Save() {
        hexagonWriter.Write(firstPanel.getHexagons(), firstPanel.maplocation);
        edgewriter.Write(firstPanel.getHexagons(), firstPanel.edgelocation);
        divisionWriter.Write(firstPanel.getDivisions(), firstPanel.divlocation);
    }

    public int Close() {
        System.out.println("Closing..");
        Save();
        return EXIT_ON_CLOSE;
    }
}
