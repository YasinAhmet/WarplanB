public class Camera {
    private int x = 0;
    private int y = 0;
    private int zoom = 1;

    public void setCamera(int x, int y, int zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getY() {
        return this.y;
    }
    public int getX() {
        return this.x;
    }

    public int getZoom() {
        return zoom;
    }
}
