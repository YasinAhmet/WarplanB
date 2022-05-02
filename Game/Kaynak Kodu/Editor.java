import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Editor extends JFrame{
    public String maploc, divloc, edgeloc;
    public FirstPanel firstPanel;
    public Mouselistener mouselistener = new Mouselistener();
    public HexagonWriter hexagonWriter = new HexagonWriter();
    public EditorPanel editorPanel;
    public CameraKey listener;
    private final Toolbar toolbar = new Toolbar();

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
        setDefaultCloseOperation(Close());
    }

    public void useToolbar() {
        toolbar.setBar();
        add(toolbar, BorderLayout.NORTH);
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
        hexagonWriter.Write(firstPanel.getHexagons(), firstPanel.maplocation);
        firstPanel.Save();
        return EXIT_ON_CLOSE;
    }

    public void setlocs(String map, String div, String edge) {
        maploc = map; divloc = div; edgeloc = edge;
    }
}
