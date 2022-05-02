import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Menu extends JFrame {
    private final MenuPanel panel = new MenuPanel();
    private final Toolbar toolbar = new Toolbar();


    public void setFrame() {
        setBackground(new Color(24, 154, 180));

        toolbar.setBar();

        toolbar.getButton().setText("Start Scenario");
        toolbar.getButton().addActionListener(this::StartGame);


        toolbar.getButtonSave().setText("Start Map Editor");
        toolbar.getButtonSave().addActionListener(this::StartEditor);

        add(toolbar, BorderLayout.NORTH);
        panel.setPanel();
        add(panel);

        setSize(320, 366);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void StartGame(ActionEvent e) {
        StartGame(panel.getTextFieldText(),panel.getTextField2Text(),panel.getTextField3Text());
    }

    public void StartEditor(ActionEvent actionEvent) {
        StartEditor(panel.getTextFieldText(),panel.getTextField2Text(),panel.getTextField3Text());
    }

    public void StartGame(String divisionloc, String maploc, String edgeloc) {
        Frame frame = new Frame();
        frame.startFrame(divisionloc, maploc, edgeloc);

        dispose();
    }

    public void StartEditor(String divisionloc, String maploc, String edgeloc) {
        Editor editor = new Editor();

        FirstPanel firstPanel = new FirstPanel();
        firstPanel.setupy = 0;
        firstPanel.startPanel(divisionloc, maploc, edgeloc);

        editor.setlocs(maploc, divisionloc, edgeloc);
        editor.startEditor(firstPanel);
        editor.maploc = maploc;
        dispose();
    }


}
