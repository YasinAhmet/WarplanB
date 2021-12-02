import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class FirstPanel extends JPanel {
    private String location;
    private final int Width = 1000;
    private final int Height = 680;
    private final Color panelColor = new Color(23, 88, 52);

    private ShipCreation shipCreation;
    private CameraKey cameraKey;

    private Timer timer;
    private int hexagonCreationX = 0;
    private int hexagonCreationY = 0;

    private ArrayList<Hexagon> hexagons;
    int hexagonAddingRoute = 1;
    private ArrayList<String> lines;
    private Keybinds keybinds;

    private ImageIcon img = new ImageIcon("unit\\untitled.png");
    private ImageIcon NatoINF = new ImageIcon("unit\\untitled.png");
    private ImageIcon SovietINF = new ImageIcon("unit\\soviet.png");
    private ImageIcon GermanINF = new ImageIcon("unit\\german.png");

    private ImageIcon desert = new ImageIcon("map\\desert1.png");
    private ImageIcon coast = new ImageIcon("map\\coast1.png");
    private ImageIcon grassland = new ImageIcon("map\\grassland1.png");
    private ImageIcon sea = new ImageIcon("map\\sea1.png");
    private ImageIcon city = new ImageIcon("map\\city1.png");

    private DivisionReader divisionReader;
    private ArrayList<Divisions> divisions;
    private String clientSide = "first";
    private HexagonReader hexagonReader;
    private DivisionWriter divisionWriter;
    private int column = 0;

    private final InfoPanel infoPanel = new InfoPanel();


    private void setup() {
        setBounds(0, 35, Width, Height);
        setBackground(panelColor);
        setVisible(true);
    }

    private void usePhotos() {
        img = new ImageIcon(location+"\\unit\\untitled.png");
        NatoINF = new ImageIcon(location+"\\unit\\untitled.png");
        SovietINF = new ImageIcon(location+"\\unit\\soviet.png");
        GermanINF = new ImageIcon(location+"\\unit\\german.png");

        desert = new ImageIcon(location+"\\map\\desert1.png");
        grassland = new ImageIcon(location+"\\map\\grassland1.png");
        coast = new ImageIcon(location+"\\map\\coast1.png");
        sea = new ImageIcon(location+"\\map\\sea1.png");
        city = new ImageIcon(location+"\\map\\city1.png");

        infoPanel.setBackground(location+"\\unit\\infoPanelBackground.png");
    }

    public void bindBoard() {
        keybinds = new Keybinds();
        keybinds.setShip(shipCreation.getShip());
        keybinds.keybind();

        add(keybinds.getObj1());
    }

    private void useKeyboard() {
        shipCreation = new ShipCreation();
        shipCreation.createShip(shipCreation.getShip(), shipCreation.getKeyboard());
    }

    private void useCamera() {
        cameraKey = new CameraKey();
        cameraKey.setCamera(new Camera());
    }

    private void useDivisionReader() {
        divisionReader = new DivisionReader();
        divisionReader.setHexFile(new File(location+"\\data\\divisions.txt"));
        divisionReader.setDivReader(location);
        divisions = new ArrayList<>();
        divisions = divisionReader.ReadFile();

    }

    private void useTimer() {
        timer = new Timer(17, this::Repainting);
        timer.start();
    }

    private void useLocation() {
        location = FirstPanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        location = (new File(location)).getParentFile().getPath();
    }

    private void startingGameObjects() {
        hexagons = new ArrayList<>();
    }

    public void useHexList() {
        hexagonReader = new HexagonReader();

        hexagonReader.setHexReader(location);
        hexagonReader.ReadFile();
        lines = hexagonReader.getLines();
    }

    public void addHexagon() {


        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                Hexagon hexagon = new Hexagon();
                hexagon.setHexagonX(hexagonCreationX);
                hexagon.setHexagonY(hexagonCreationY);

                hexagon.setX(hexagon.getX(), hexagon.getHexagonX(), cameraKey.getCamera().getZoom());
                hexagon.setY(hexagon.getY(), hexagon.getHexagonY(), cameraKey.getCamera().getZoom());
                hexagon.setColor(Color.gray);

                if (hexagonAddingRoute == 1) {
                    hexagonCreationY = hexagonCreationY + 51;
                    hexagonCreationX = hexagonCreationX + 78;
                    hexagonAddingRoute++;
                } else if (hexagonAddingRoute == 2) {
                    hexagonCreationX = hexagonCreationX + 78;
                    hexagonCreationY = hexagonCreationY - 51;
                    hexagonAddingRoute++;
                } else if (hexagonAddingRoute == 3) {
                    hexagonCreationY = hexagonCreationY + 51;
                    hexagonCreationX = hexagonCreationX + 78;
                    hexagonAddingRoute = 2;
                }


                if (line.charAt(i) == 'd') {
                    hexagon.setHextype("desert");
                } else if (line.charAt(i) == 'c') {
                    hexagon.setHextype("forrest");
                } else if (line.charAt(i) == 'g') {
                    hexagon.setHextype("grassland");
                } else if (line.charAt(i) == 's') {
                    hexagon.setHextype("sea");
                } else if (line.charAt(i) == 'k') {
                    hexagon.setHextype("city");
                }

                hexagon.hexagon(hexagon.getX(), hexagon.getY(), hexagon.getHexagonX(), hexagon.getHexagonY(), Color.gray, i, column);

                if (!(line.charAt(i) == 'l')) {

                    for (int g = 0; g < divisions.size(); g++) {

                        if (divisions.get(g).getRow() == hexagon.getRow() && divisions.get(g).getColumn() == hexagon.getColumn()) {

                            if (divisions.get(g).getSide().equals("first")) {
                                divisions.get(g).setDivisionBackground(GermanINF);
                            } else if (divisions.get(g).getSide().equals("second")) {
                                divisions.get(g).setDivisionBackground(SovietINF);
                            }

                            addDivisions(hexagon, divisions.get(g).getMen(), divisions.get(g).getMahiyet(), divisions.get(g).getSaldırı(), divisions.get(g).getSavunma(), divisions.get(g).getSide(), divisions.get(g).getDivisionBackground());
                        }
                    }
                }
                hexagons.add(hexagon);
            }

            column++;
            hexagonCreationY = column * 102;
            hexagonCreationX = 0;
            hexagonAddingRoute = 1;
        }
    }

    public void addDivisions(Hexagon hex, int men, char mahiyet, int saldırı, int savunma, String side, ImageIcon background) {
        Divisions division = new Divisions();
        division.Division(NatoINF, men, mahiyet, saldırı, savunma, background, side, 20);
        hex.addDivision(division);
    }

    public void backgroundMech() {
        startingGameObjects();
    }

    public void useMouseListener(InfoPanel infoPanel) {
        Mouselistener mouselistener = new Mouselistener();
        mouselistener.ms(this, hexagons, cameraKey.getCamera());

        mouselistener.setInfoPanel(infoPanel);
        addMouseListener(mouselistener);
    }

    public void useInfoPanel() {

        clientSide = divisionReader.getSide();
        infoPanel.setPanel(clientSide);
    }

    public void useDivisionWriter() {
        divisionWriter = new DivisionWriter();
        divisionWriter.setWriter(new File(location+"\\data\\divisions.txt"));
    }

    public void startPanel() {
        useLocation();
        useDivisionReader();
        useDivisionWriter();
        usePhotos();
        setup();
        backgroundMech();
        useKeyboard();
        bindBoard();
        useCamera();
        useTimer();
        useHexList();
        useInfoPanel();

        useMouseListener(infoPanel);


        addHexagon();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);

        for (int i = 0; i < hexagons.size(); i++) {
            Hexagon getI = hexagons.get(i);

            if (getI.getHexagonX() - cameraKey.getCamera().getX() < -50 * cameraKey.getCamera().getZoom()) {
                continue;
            } else if (getI.getHexagonX() - cameraKey.getCamera().getX() > (getSize().getWidth() + 50) * cameraKey.getCamera().getZoom()) {
                continue;
            }

            if (getI.getHexagonY() - cameraKey.getCamera().getY() < -50 * cameraKey.getCamera().getZoom()) {
                continue;
            } else if (getI.getHexagonY() - cameraKey.getCamera().getY() > (getSize().getHeight() + 50) * cameraKey.getCamera().getZoom()) {
                continue;
            }

            g.setColor(getI.getColor());

            getI.setCameraX(cameraKey.getCamera().getX());
            getI.setCameraY(cameraKey.getCamera().getY());
            getI.setX(getI.getX(), getI.getHexagonX(), cameraKey.getCamera().getZoom());
            getI.setY(getI.getY(), getI.getHexagonY(), cameraKey.getCamera().getZoom());


            if (cameraKey.getCamera().getZoom() == 2) {
                img = new ImageIcon(location+"\\unit\\untitledZ2.png");
                desert = new ImageIcon(location+"\\map\\desert2.png");
                grassland = new ImageIcon(location+"\\map\\grassland2.png");
                coast = new ImageIcon(location+"\\map\\coast2.png");
                sea = new ImageIcon(location+"\\map\\sea2.png");
                city = new ImageIcon(location+"\\map\\city2.png");
            } else if (cameraKey.getCamera().getZoom() == 1) {
                img = new ImageIcon(location+"\\unit\\untitled.png");
                desert = new ImageIcon(location+"\\map\\desert1.png");
                grassland = new ImageIcon(location+"\\map\\grassland1.png");
                coast = new ImageIcon(location+"\\map\\coast1.png");
                sea = new ImageIcon(location+"\\map\\sea1.png");
                city = new ImageIcon(location+"\\map\\city1.png");

            }

            g.fillPolygon(getI.getX(), getI.getY(), 6);
            if (getI.getHextype().equals("desert") && cameraKey.getCamera().getZoom() == 1) {
                desert.paintIcon(this, g, (getI.getHexagonX() - (75)) - cameraKey.getCamera().getX(), (getI.getHexagonY() - 50) - cameraKey.getCamera().getY());
            } else if (getI.getHextype().equals("desert") && cameraKey.getCamera().getZoom() == 2) {
                desert.paintIcon(this, g, (getI.getHexagonX() / 2 - (37) - cameraKey.getCamera().getX() / 2), (getI.getHexagonY() / 2 - 25) - cameraKey.getCamera().getY() / 2);
            }
            if (getI.getHextype().equals("forrest") && cameraKey.getCamera().getZoom() == 1) {
                coast.paintIcon(this, g, (getI.getHexagonX() - (75)) - cameraKey.getCamera().getX(), (getI.getHexagonY() - 50) - cameraKey.getCamera().getY());
            } else if (getI.getHextype().equals("forrest") && cameraKey.getCamera().getZoom() == 2) {
                coast.paintIcon(this, g, (getI.getHexagonX() / 2 - (37)) - cameraKey.getCamera().getX() / 2, (getI.getHexagonY() / 2 - 25) - cameraKey.getCamera().getY() / 2);
            }
            if (getI.getHextype().equals("grassland") && cameraKey.getCamera().getZoom() == 1) {
                grassland.paintIcon(this, g, (getI.getHexagonX() - (75)) - cameraKey.getCamera().getX(), (getI.getHexagonY() - 50) - cameraKey.getCamera().getY());
            } else if (getI.getHextype().equals("grassland") && cameraKey.getCamera().getZoom() == 2) {
                grassland.paintIcon(this, g, (getI.getHexagonX() / 2 - (37)) - cameraKey.getCamera().getX() / 2, (getI.getHexagonY() / 2 - 25) - cameraKey.getCamera().getY() / 2);
            }
            if (getI.getHextype().equals("sea") && cameraKey.getCamera().getZoom() == 1) {
                sea.paintIcon(this, g, (getI.getHexagonX() - (75)) - cameraKey.getCamera().getX(), (getI.getHexagonY() - 50) - cameraKey.getCamera().getY());
            } else if (getI.getHextype().equals("sea") && cameraKey.getCamera().getZoom() == 2) {
                sea.paintIcon(this, g, (getI.getHexagonX() / 2 - (37)) - cameraKey.getCamera().getX() / 2, (getI.getHexagonY() / 2 - 25) - cameraKey.getCamera().getY() / 2);
            }
            if (getI.getHextype().equals("city") && cameraKey.getCamera().getZoom() == 1) {
                city.paintIcon(this, g, (getI.getHexagonX() - (75)) - cameraKey.getCamera().getX(), (getI.getHexagonY() - 50) - cameraKey.getCamera().getY());
            } else if (getI.getHextype().equals("city") && cameraKey.getCamera().getZoom() == 2) {
                city.paintIcon(this, g, (getI.getHexagonX() / 2 - (37)) - cameraKey.getCamera().getX() / 2, (getI.getHexagonY() / 2 - 25) - cameraKey.getCamera().getY() / 2);
            }
            g.drawPolygon(getI.getX(), getI.getY(), 6);


            int k = 0;
            for (int d = 0; d < getI.getDivisions().size(); d++) {
                img.paintIcon(this, g, (getI.getHexagonX() - cameraKey.getCamera().getX() - 50 + k * 5) / cameraKey.getCamera().getZoom(), (getI.getHexagonY() - cameraKey.getCamera().getY() - 20 + k * 5) / cameraKey.getCamera().getZoom());
                k++;
            }


        }

    }

    public Keyboard returnKeyboard() {
        return shipCreation.getKeyboard();
    }

    public CameraKey returnCameraKey() {
        return cameraKey;
    }

    public void HexagonCreation(ActionEvent e) {
        addHexagon();
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void Repainting(ActionEvent a) {
        repaint();
    }

    public void setClientSide(String clientSide) {
        this.clientSide = clientSide;
    }

    public String getClientSide() {
        return clientSide;
    }

    public ArrayList<Divisions> getDivisions() {
        return divisions;
    }

    public void NextTurn() {
        if (getClientSide().equals("first")) {

            for (Hexagon hexagon : hexagons) {

                for (Divisions divisions : hexagon.getDivisions()) {

                    if (divisions.getSide().equals("first")) {
                        divisions.setMovementPoint(20);
                        divisions.setSelected(false);

                    }

                }

            }

            setClientSide("second"); infoPanel.getInfoListener().setSide("second");
        } else if (getClientSide().equals("second")) {

            for (Hexagon hexagon : hexagons) {

                for (Divisions divisions : hexagon.getDivisions()) {

                    if (divisions.getSide().equals("second")) {
                        divisions.setMovementPoint(20);
                        divisions.setSelected(false);
                    }

                }

            }

            setClientSide("first"); infoPanel.getInfoListener().setSide("first");
        }
        infoPanel.repaint();
        Save();

    }

    public String gelocation() {
        return location;
    }

    public void Save() {

        divisionWriter.writeItAll(hexagons, clientSide);

    }
}
