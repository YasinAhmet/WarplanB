import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JFrame {
    private final MenuPanel panel = new MenuPanel();
    private final Toolbar toolbar = new Toolbar();


    public void setFrame() {
        setBackground(new Color(24, 154, 180));

        toolbar.setBar();

        toolbar.getButton().setText("Start Scenario");
        MenuListener menuListener1 = new MenuListener();
        menuListener1.setAll(1,this,panel);
        toolbar.getButton().addMouseListener(menuListener1);


        toolbar.remove(toolbar.getButtonSave());
        //toolbar.getButtonSave().setText("Start Map Editor");
        //MenuListener replicaListener = menuListener1;
        //replicaListener.setAll(2,this,panel);

        add(toolbar, BorderLayout.NORTH);
        panel.setPanel();
        add(panel);

        setSize(320, 366);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void StartGame(String divisionloc, String maploc, String edgeloc) {
        Frame frame = new Frame();
        frame.startFrame(divisionloc, maploc, edgeloc);

        dispose();
    }

    public void StartEditor(String divisionloc, String maploc, String edgeloc) {

    }


}
