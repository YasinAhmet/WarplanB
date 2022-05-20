import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Editor extends JFrame{
    public String maploc, divloc, edgeloc;
    public FirstPanel firstPanel;
    public Mouselistener mouselistener = new Mouselistener();
    public HexagonWriter hexagonWriter = new HexagonWriter();
    public DivisionWriter divisionWriter = new DivisionWriter();
    public EdgeWriter edgewriter = new EdgeWriter();
    public EditorPanel editorPanel;
    public CameraKey listener;
    public WindowAdapter windowAdapter;

    public void startEditor(FirstPanel firstPanel) {
        this.setBounds(0,0,1285,719);
        this.setBackground(new Color(222, 121, 44));

        this.firstPanel = firstPanel;
        this.addMouseListener(firstPanel.mouselistener);

        editorPanel = new EditorPanel();
        editorPanel.firstPanel = this.firstPanel;
        editorPanel.launchPanelStuff(this);

        add(firstPanel, BorderLayout.CENTER);
        add(editorPanel, BorderLayout.EAST);

        this.setVisible(true);
        this.setResizable(false);

        listener = firstPanel.returnCameraKey();
        addKeyListener(listener);
        requestFocusInWindow();

        useWindowAdapter();
        addWindowListener(windowAdapter);
    }

    public void useWindowAdapter() {
        windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Close();
                System.exit(0);
            }
        };
    }

    public void refreshPanel() {
        remove(firstPanel);
        firstPanel = new FirstPanel();

        editorPanel.firstPanel = firstPanel;
        firstPanel.startPanel(divloc,maploc,edgeloc);
        this.addMouseListener(firstPanel.mouselistener);

        firstPanel.cameraKey = listener;
        add(firstPanel);
    }

    public int Close() {
        System.out.println("Closing..");
        hexagonWriter.Write(firstPanel.getHexagons(), firstPanel.maplocation);
        edgewriter.Write(firstPanel.getHexagons(), firstPanel.edgelocation);
        divisionWriter.Write(firstPanel.getDivisions(), firstPanel.divlocation);
        return EXIT_ON_CLOSE;
    }

    public void setlocs(String map, String div, String edge) {
        maploc = map; divloc = div; edgeloc = edge;
    }

    public void windowClosing(WindowEvent evt) {
        Close();
    }
}
