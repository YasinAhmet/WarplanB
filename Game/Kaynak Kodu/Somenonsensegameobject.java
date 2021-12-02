import java.awt.Color;

public class Somenonsensegameobject {
    private int x = 500;
    private int y = 200;
    private int width = 50;
    private int height = 20;
    private Color color = Color.green;

    public void setGameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {this.height = height;}
    public void color(Color color) {this.color = color;}

    public int getY() {
        return this.y;
    }
    public int getX() {
        return this.x;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public Color getColor() {return this.color;}
}
