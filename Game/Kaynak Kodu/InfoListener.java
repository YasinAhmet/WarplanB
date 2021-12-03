import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InfoListener implements MouseListener {
    private ArrayList<Divisions> hexDivisions = new ArrayList<>();
    private boolean gotclick = false;
    private String side;
    private int x, y;


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        gotclick = true;
        x = e.getX();
        y = e.getY();

    }

    public void clickHappened(int x, int y) {
        
        if (side.equals(hexDivisions.get(0).getSide())) {
            for (int i = 0; i < hexDivisions.size(); i++) {

                if (y >= 29 + (i * 100) && y <= 109 + (i * 100)) {

                    if (hexDivisions.get(i).isSelected()) {
                        hexDivisions.get(i).setSelected(false);
                    } else if (!hexDivisions.get(i).isSelected()) {
                        hexDivisions.get(i).setSelected(true);
                    }

                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setHexDivisions(ArrayList<Divisions> hexDivisions) {
        this.hexDivisions = hexDivisions;
    }

    public ArrayList<Divisions> getHexDivisions() {
        return hexDivisions;
    }

    public boolean isGotclick() {
        return gotclick;
    }

    public void setGotclick(boolean gotclick) {
        this.gotclick = gotclick;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }
}
