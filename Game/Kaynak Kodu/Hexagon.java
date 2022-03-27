import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Hexagon {

    private ArrayList<Divisions> divisions = new ArrayList<>();
    private int hexagonX = 0;
    private int hexagonY = 0;
    private int b = hexagonX;
    private int c = hexagonY;
    private int cameraX = 0;
    private int cameraY = 0;
    private int row = 1;
    private int column = 1;
    private String hextype = "null";
    private ArrayList<String> edges = new ArrayList<>();

    private int x[] = {b-50-cameraX, b-cameraX, b+25-cameraX, b-cameraX, b-50-cameraX, b-75-cameraX, b-50-cameraX},
            y[] = {c-50-cameraY, c-50-cameraY, c-cameraY, c+50-cameraY, c+50-cameraY, c-cameraY, c-50-cameraY};

    Area hexes = new Area(new Polygon(x, y, 6));


    private Color color;

    public void hexagon(int x[], int y[], int hexagonX, int hexagonY, Color color, int row, int column) {
        this.x = x;
        this.y = y;
        this.hexagonX = hexagonX;
        this.hexagonY = hexagonY;
        this.color = color;
        this.row = row;
        this.column = column;
        edges("normal","normal","normal","normal","normal","normal");
    }

    public void edges(String north, String northeast, String southeast, String south, String southwest, String northwest) {
        this.edges.add(north);
        this.edges.add(northeast);
        this.edges.add(southeast);
        this.edges.add(south);
        this.edges.add(southwest);
        this.edges.add(northwest);
    }

    public String north() {
        return edges.get(0);
    }

    public String northeast() {
        return edges.get(1);
    }

    public String southeast() {
        return edges.get(2);
    }

    public String south() {
        return edges.get(3);
    }

    public String southwest() {
        return edges.get(4);
    }

    public String northwest() {
        return edges.get(1);
    }



    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setHextype(String hextype) {
        this.hextype = hextype;
    }

    public String getHextype() {
        return hextype;
    }

    public int[] getX() { return x; }
    public int[] getY() {
        return y;
    }
    public int getHexagonX() {return hexagonX;}
    public int getHexagonY() {return hexagonY;}
    public Color getColor() {return color;}
    public ArrayList<Divisions> getDivisions() {
        return divisions;
    }

    public void setX(int x[], int b, int zoom) { this.x = new int[]{(b-50-cameraX)/zoom, (b-cameraX)/zoom, (b+25-cameraX)/zoom, (b-cameraX)/zoom, (b-50-cameraX)/zoom, (b-75-cameraX)/zoom, (b-50-cameraX)/zoom};}
    public void setY(int y[], int c, int zoom) { this.y = new int[]{(c-50-cameraY)/zoom, (c-50-cameraY)/zoom, (c-cameraY)/zoom, (c+50-cameraY)/zoom, (c+50-cameraY)/zoom, (c-cameraY)/zoom, (c-50-cameraY)/zoom};}
    public void setColor(Color color) {
        this.color = color;
    }

    public void setHexagonX(int hexagonX) {this.hexagonX = hexagonX;}
    public void setHexagonY(int hexagonY) {this.hexagonY = hexagonY;}

    public void setCameraX(int cameraX) {this.cameraX = cameraX;}
    public void setCameraY(int cameraY) {this.cameraY = cameraY;}
    public void setDivisions(ArrayList<Divisions> divisions) {
        this.divisions = divisions;
    }
    public void addDivision(Divisions division) {
        divisions.add(division);
    }

    public void setHexes(Area hexes) {
        this.hexes = hexes;
    }

    public Area getHexes() {
        return hexes;
    }
    public ArrayList<String> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<String> edges) {
        this.edges = edges;
    }
}
