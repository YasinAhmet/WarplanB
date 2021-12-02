import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InfoPanel extends JPanel {
    private ArrayList<Divisions> hexDivisions = new ArrayList<>();
    private int width = 366, height = 720;
    private ImageIcon background;
    private final InfoListener infoListener = new InfoListener();
    private final Timer timer = new Timer(34,this::detectClick);
    private int hexX = 0, hexY = 0;

    public void detectClick(ActionEvent e) {

        if(infoListener.isGotclick()) {
            infoListener.setGotclick(false);
            infoListener.clickHappened(infoListener.getX(), infoListener.getY());
            setHexDivisions(infoListener.getHexDivisions());
            repaint();
        }

    }

    public void setPanel(String side) {
        infoListener.setSide(side);
        setBounds(1000,35,366,680);
        setVisible(true);


    }

    public ArrayList<Divisions> getHexDivisions() {
        return hexDivisions;
    }

    public void setHexDivisions(ArrayList<Divisions> hexDivisions) {
        this.hexDivisions = hexDivisions;
    }

    public void setHexX(int hexX) {
        this.hexX = hexX;
    }

    public void setHexY(int hexY) {
        this.hexY = hexY;
    }

    public int getHexX() {
        return hexX;
    }

    public int getHexY() {
        return hexY;
    }

    public void reformPanel(ArrayList<Divisions> divisions) {
        timer.start();
        hexDivisions = divisions;
        infoListener.setHexDivisions(divisions);
        addMouseListener(infoListener);

        removeAll();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.paintIcon(this,g,0,0);

        int h = 0;
        for (int i = 0; i < hexDivisions.size(); i++) {
            g.setColor(Color.red);
            if (hexDivisions.get(i).isSelected()) {
                g.setColor(Color.green);
            }

            g.drawRect(29, 29+h, 307, 81);
            hexDivisions.get(i).getDivisionBackground().paintIcon(this,g,30,30+h);
            hexDivisions.get(i).getDivisionImage().paintIcon(this,g,30,30+h);


            g.setColor(new Color(155,60,70));
            g.drawString("Savunma: " + hexDivisions.get(i).getSavunma() + " Savunmasında",35, 80+h);
            g.drawString("Saldırı: " + hexDivisions.get(i).getSaldırı() + " Gücünde",35, 95+h);
            g.drawString("Mahiyet: " + hexDivisions.get(i).getMahiyet() + " Kalitesinde",200, 80+h);
            g.drawString("Sayı: " + hexDivisions.get(i).getMen() + " Asker",200, 95+h);
            g.drawString("Hareket Puanı: " + hexDivisions.get(i).getMovementPoint() + " Hareket Puanı",80, 40+h);


            h = h + 100;
        }
    }

    public InfoListener getInfoListener() {
        return infoListener;
    }

    public void setBackground(String bg) {
        background = new ImageIcon(bg);
    }
}


