import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class FirstPanel extends JPanel {
    public Frame master;
    public String divlocation;
    public String maplocation;
    public String edgelocation;
    public boolean debugmod = false;
    public String location;
    private int Width = 1000;
    private int Height = 680;
    public int setupy = 35;
    private final Color panelColor = new Color(23, 88, 52);

    public CameraKey cameraKey;
    public Mouselistener mouselistener;

    private Timer timer;
    public int hexagonCreationX = 0;
    public int hexagonCreationY = 0;
    private ArrayList<Hexagon> hexagons;
    private int hexagonAddingRoute = 1;
    private ArrayList<String> lines = new ArrayList<>();

    private ImageIcon img = new ImageIcon("unit\\untitled.png");
    private ImageIcon NatoINF = new ImageIcon("unit\\untitled.png");

    public DivisionReader divisionReader;
    private ArrayList<Divisions> divisions = new ArrayList<>();
    private String clientSide = "first";
    private HexagonReader hexagonReader;
    private DivisionWriter divisionWriter;
    private int column = 0;

    private final InfoPanel infoPanel = new InfoPanel();


    public void setup() {
        setBounds(0, setupy, Width, Height);
        setBackground(panelColor);
        setVisible(true);
    }

    public void usePhotos() {
        img = new ImageIcon(location+"\\unit\\untitled.png");
        NatoINF = new ImageIcon(location+"\\unit\\untitled.png");
        infoPanel.setBackground(location+"\\unit\\infoPanelBackground.png");
    }

    public void useCamera() {
        cameraKey = new CameraKey();
        cameraKey.setCamera(new Camera());
    }

    public void useDivisionReader() {
        divisionReader = new DivisionReader();
        divisionReader.setHexFile(new File(divlocation));
        divisionReader.setDivReader(divlocation);
        divisions = new ArrayList<>();
        divisions = divisionReader.ReadFile();

    }

    public void useTimer() {
        timer = new Timer(17, this::Repainting);
        timer.start();
    }

    public void useLocation(String div, String map, String edge) {
        location = FirstPanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        location = (new File(location)).getParentFile().getPath();

        divlocation = location+"\\data\\"+div+".txt";
        maplocation = location+"\\data\\"+map+".txt";
        edgelocation = location+"\\data\\"+edge+".txt";
    }

    public void startingGameObjects() {
        hexagons = new ArrayList<>();
    }

    public void useHexList() {
        hexagonReader = new HexagonReader();

        hexagonReader.setHexReader(maplocation);
        hexagonReader.ReadFile();
        lines = hexagonReader.getLines();
    }

    public Hexagon addJustOneHex () {
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
            hexagon.hexthing = "notmiddle";
         }

        else if (hexagonAddingRoute == 2) {
            hexagonCreationX = hexagonCreationX + 78;
            hexagonCreationY = hexagonCreationY - 51;
            hexagonAddingRoute++;
            hexagon.hexthing = "middle";
        } else if (hexagonAddingRoute == 3) {
            hexagonCreationY = hexagonCreationY + 51;
            hexagonCreationX = hexagonCreationX + 78;
            hexagonAddingRoute = 2;
            hexagon.hexthing = "notmiddle";
        }

        return hexagon;
    }

    public void addHexagons() {
        hexagons = new ArrayList<>();

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                Hexagon hexagon = addJustOneHex();

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
                } else if (line.charAt(i) == 'e') {
                    hexagon.setHextype("empty");
                }

                hexagon.hexagon(hexagon.getX(), hexagon.getY(), hexagon.getHexagonX(), hexagon.getHexagonY(), Color.gray, i, column);

                if (!(line.charAt(i) == 's')) {

                    for (int g = 0; g < divisions.size(); g++) {

                        if (divisions.get(g).getRow() == hexagon.getRow() && divisions.get(g).getColumn() == hexagon.getColumn()) {
                            addDivisions(hexagon, divisions.get(g).getMen(), divisions.get(g).getMahiyet(), divisions.get(g).getSaldırı(), divisions.get(g).getSavunma(), divisions.get(g).getSide(), divisions.get(g).getDivisionBackground(), divisions.get(g).getImgpath());
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

    public void addDivisions(Hexagon hex, int men, char mahiyet, int saldırı, int savunma, String side, ImageIcon background, String path) {
        Divisions division = new Divisions();
        division.Division(NatoINF, men, mahiyet, saldırı, savunma, background, side, 20);
        division.setImgpath(path);
        hex.addDivision(division);
    }

    public void backgroundMech() {
        startingGameObjects();
    }

    public void useMouseListener(InfoPanel infoPanel) {
        mouselistener = new Mouselistener();
        mouselistener.setFirstPanel(this);
        mouselistener.ms(this, cameraKey.getCamera());

        mouselistener.setInfoPanel(infoPanel);
        addMouseListener(mouselistener);

    }

    public void useInfoPanel() {
        clientSide = divisionReader.getSide();
        infoPanel.setPanel(clientSide);
    }

    public void useDivisionWriter() {
        divisionWriter = new DivisionWriter();
        divisionWriter.setWriter(new File(divlocation));
    }

    public void startPanel(String div, String map, String edge) {
        useLocation(div, map, edge);
        useDivisionReader();
        usePhotos();
        setup();
        backgroundMech();
        useCamera();
        useTimer();
        useHexList();
        useInfoPanel();

        useMouseListener(infoPanel);

        addHexagons();
        useHexData();
        useDivisionWriter();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setup();
        infoPanel.setPanel(clientSide);

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

            drawHex(g,getI);

            getI.setCameraX(cameraKey.getCamera().getX());
            getI.setCameraY(cameraKey.getCamera().getY());
            getI.setX(getI.getX(), getI.getHexagonX(), cameraKey.getCamera().getZoom());
            getI.setY(getI.getY(), getI.getHexagonY(), cameraKey.getCamera().getZoom());
            setImages();


            int k = 0;
            for (int d = 0; d < getI.getDivisions().size(); d++) {
                img.paintIcon(this, g, (getI.getHexagonX() - cameraKey.getCamera().getX() - 50 + k * 3) / cameraKey.getCamera().getZoom(), (getI.getHexagonY() - cameraKey.getCamera().getY() - 20 - k * 3) / cameraKey.getCamera().getZoom());
                k++;
            }


            if(getI.equals(mouselistener.hex)) {
                g.setColor(new Color(150,30,5));
                g.drawPolygon(getI.getX(), getI.getY(), 6);
            }

        }

    }

    public void setImages() {
        if (cameraKey.getCamera().getZoom() == 2) {
            img = new ImageIcon(location+"\\unit\\untitledZ2.png");
        } else if (cameraKey.getCamera().getZoom() == 1) {
            img = new ImageIcon(location+"\\unit\\untitled.png");
        }
    }

    public CameraKey returnCameraKey() {
        cameraKey.addUpdate(this); return cameraKey;
    }

    public void useHexData() {
        HexDataReader hexDataReader = new HexDataReader();
        hexDataReader.setDivReader(edgelocation);
        hexagons = hexDataReader.ReadFile(hexagons);

    }

    //public void HexagonCreation(ActionEvent e) {addHexagon();}

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

            for (Hexagon hexagon : hexagons) {

                try {
                    for (Divisions divisions : hexagon.getDivisions()) {

                        if (divisions.getSide().equals("second")) {
                            divisions.setMovementPoint(20);
                            divisions.setSelected(false);
                        }

                        playAITurn(hexagon, hexagons);

                    }
                } catch (ConcurrentModificationException e) {
                    System.out.println("Sir unit go boom boom");
                }

            }

            setClientSide("first");
        }
        infoPanel.repaint();
        master.Save();
    }

    public void drawHex(Graphics g, Hexagon getI) {
        g.setColor(getI.getColor());
        g.fillPolygon(getI.getX(), getI.getY(), 6);
        if (getI.getHextype().equals("desert")) {
            g.setColor(new Color(170,190,80));
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        if (getI.getHextype().equals("forrest")) {
            g.setColor(new Color(10,70,10));
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        if (getI.getHextype().equals("grassland")) {
            g.setColor(new Color(100,170,90));
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        if (getI.getHextype().equals("sea")) {
            g.setColor(new Color(50,150,200));
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        if (getI.getHextype().equals("city")) {
            g.setColor(Color.gray);
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        if (getI.getHextype().equals("empty")) {
            g.setColor(Color.black);
            g.fillPolygon(getI.getX(), getI.getY(), 6);
        }
        g.setColor(getI.getColor());
        g.drawPolygon(getI.getX(), getI.getY(), 6);
        drawedges(g,getI);

        if(debugmod) {
            g.drawString(getI.getColumn() + " " + getI.getRow(), (getI.getHexagonX()-45 - cameraKey.getCamera().getX()) / cameraKey.getCamera().getZoom(), (getI.getHexagonY()+30 - cameraKey.getCamera().getY()) / cameraKey.getCamera().getZoom());
            g.drawString(getI.hexthing, (getI.getHexagonX()-45 - cameraKey.getCamera().getX()) / cameraKey.getCamera().getZoom(), (getI.getHexagonY() + 40 - cameraKey.getCamera().getY()) / cameraKey.getCamera().getZoom());
        }
    }

    public void drawedges(Graphics g, Hexagon getI) {
        int posy = getI.getHexagonY()-cameraKey.getCamera().getY();
        int posx = getI.getHexagonX()-cameraKey.getCamera().getX();

        int zoom = cameraKey.getCamera().getZoom();

        //north
        g.setColor(edgeCheck(0,getI));
        g.drawLine((posx-50)/zoom, (posy-48)/zoom, (posx-2)/zoom, (posy-48)/zoom);
        g.drawLine((posx-50)/zoom, (posy-49)/zoom, (posx-2)/zoom, (posy-49)/zoom);
        g.drawLine((posx-50)/zoom, (posy-50)/zoom, (posx-2)/zoom, (posy-50)/zoom);

        //north-east
        g.setColor(edgeCheck(1,getI));
        g.drawLine((posx-2)/zoom, (posy-50)/zoom, (posx+23)/zoom, (posy)/zoom);
        g.drawLine((posx-1)/zoom, (posy-50)/zoom, (posx+24)/zoom, (posy)/zoom);
        g.drawLine((posx)/zoom, (posy-50)/zoom, (posx+25)/zoom, (posy)/zoom);

        //south-east
        g.setColor(edgeCheck(2,getI));
        g.drawLine((posx+23)/zoom, (posy)/zoom, (posx-2)/zoom, (posy+50)/zoom);
        g.drawLine((posx+24)/zoom, (posy)/zoom, (posx-1)/zoom, (posy+50)/zoom);
        g.drawLine((posx+25)/zoom, (posy)/zoom, (posx)/zoom, (posy+50)/zoom);

        //south
        g.setColor(edgeCheck(3,getI));
        g.drawLine((posx-50)/zoom, (posy+48)/zoom, (posx-2)/zoom, (posy+48)/zoom);
        g.drawLine((posx-50)/zoom, (posy+49)/zoom, (posx-2)/zoom, (posy+49)/zoom);
        g.drawLine((posx-50)/zoom, (posy+50)/zoom, (posx-2)/zoom, (posy+50)/zoom);

        //south-west
        g.setColor(edgeCheck(4,getI));
        g.drawLine((posx-74)/zoom, (posy)/zoom, (posx-49)/zoom, (posy+50)/zoom);
        g.drawLine((posx-75)/zoom, (posy)/zoom, (posx-50)/zoom, (posy+50)/zoom);
        g.drawLine((posx-76)/zoom, (posy)/zoom, (posx-51)/zoom, (posy+50)/zoom);

        //north-east
        g.setColor(edgeCheck(5,getI));
        g.drawLine((posx-51)/zoom, (posy-50)/zoom, (posx-76)/zoom, (posy)/zoom);
        g.drawLine((posx-50)/zoom, (posy-50)/zoom, (posx-75)/zoom, (posy)/zoom);
        g.drawLine((posx-49)/zoom, (posy-50)/zoom, (posx-74)/zoom, (posy)/zoom);


    }

    public Color edgeCheck(int edgeNumber, Hexagon hexagon) {

        return switch (hexagon.getEdges().get(edgeNumber)) {
            case "river" -> Color.cyan;
            default -> Color.black;
        };
    }

    public void playAITurn(Hexagon hexagon, ArrayList<Hexagon> hexagons) {

        for (Hexagon hex : hexagons) {
            int targetRow = hex.getRow(), targetCol = hex.getColumn();
            int currentRow = hexagon.getRow(), currentCol = hexagon.getColumn();

            if(hex.getDivisions().size() != 0 && hex.getDivisions().get(0).getSide().equals("second") && !((targetCol == currentCol)&&(targetRow == currentRow))) {
                mouselistener.hex = hex;
                infoPanel.currentHex = hex;
                infoPanel.reformPanel(hex.getDivisions());
                infoPanel.setHexX(hex.getRow());
                infoPanel.setHexY(hex.getColumn());

                System.out.println("Is it the true one: " + mouselistener.isTrueColRow(currentCol, currentRow, targetRow, targetCol, hex));
                System.out.println("CurrentPanel: " + currentCol + " " + currentRow);
                System.out.println("TargetPanel: " + targetCol + " " + targetRow);

                        if (mouselistener.isTrueColRow(currentCol, currentRow, targetRow, targetCol, hexagon)) {
                            damage(hexagon,hex);
                        }

            }


        }
    }

    public void damage(Hexagon curenthex, Hexagon attackhex) {
            infoPanel.setHexDivisions(attackhex.getDivisions());

            for (int i = 0; i < 2; i++) {
                for (Divisions div : infoPanel.getHexDivisions()) {
                    div.setSelected(true);
                }
                mouselistener.Attack(curenthex);
            }
    }

    public ArrayList<Hexagon> getHexagons() {
        return hexagons;
    }
}
