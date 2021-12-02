import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Mouselistener implements MouseListener {
    Camera camera;
    ArrayList<Hexagon> hexagonArrayList;
    JPanel panel;
    InfoPanel infoPanel;

    public void ms (JPanel panel, ArrayList<Hexagon> hexagons, Camera camera) {
        this.camera = camera;
        this.hexagonArrayList = hexagons;
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int egetX = e.getX();
        int egetY = e.getY();

        if (e.getButton() == MouseEvent.BUTTON2 && camera.getZoom() == 1) {
            camera.setZoom(2);
        } else if (e.getButton() == MouseEvent.BUTTON2 && camera.getZoom() == 2) {
            camera.setZoom(1);
        }

        here:
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (int i = 0; i < hexagonArrayList.size(); i++) {
                Hexagon hex = hexagonArrayList.get(i);

                int hexgetx = (hex.getHexagonX() - camera.getX()) / camera.getZoom();
                int hexgety = (hex.getHexagonY() - camera.getY()) / camera.getZoom();

                if (hex.getHexagonX() - camera.getX() < -100 / camera.getZoom()) {
                    continue;
                } else if (hex.getHexagonX() - camera.getX() > panel.getSize().getWidth() + 100) {
                    continue;
                }

                if (hex.getHexagonY() - camera.getY() < -100 / camera.getZoom()) {
                    continue;
                } else if (hex.getHexagonY() - camera.getY() > panel.getSize().getHeight() + 100) {
                    continue;
                }


                if (egetX >= hexgetx - (50/camera.getZoom()) && egetX <= hexgetx + (10/camera.getZoom()) &&
                        egetY >= hexgety - (50/camera.getZoom()) && egetY <= (hexgety + (10/camera.getZoom()))) {

                    for (int d = 0; d < infoPanel.getHexDivisions().size(); d++) {
                        infoPanel.getHexDivisions().get(d).setSelected(false);
                    }

                    infoPanel.reformPanel(hex.getDivisions());
                    infoPanel.setHexX(hex.getRow());
                    infoPanel.setHexY(hex.getColumn());

                    return;
                }

            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            for (int he = 0; he < hexagonArrayList.size(); he++) {
                Hexagon hex = hexagonArrayList.get(he);

                int hexgetx = (hex.getHexagonX() - camera.getX()) / camera.getZoom();
                int hexgety = (hex.getHexagonY() - camera.getY()) / camera.getZoom();

                if (hex.getHexagonX() - camera.getX() < -100 / camera.getZoom()) {
                    continue;
                } else if (hex.getHexagonX() - camera.getX() > panel.getSize().getWidth() + 100) {
                    continue;
                }

                if (hex.getHexagonY() - camera.getY() < -100 / camera.getZoom()) {
                    continue;
                } else if (hex.getHexagonY() - camera.getY() > panel.getSize().getHeight() + 100) {
                    continue;
                }

                if (egetX >= hexgetx - 50 && egetX <= hexgetx + 20 &&
                        egetY >= hexgety - 50 && egetY <= hexgety + 20) {


                    if (hex.getDivisions().size() <= 0 || hex.getDivisions().get(0).getSide().equals(infoPanel.getHexDivisions().get(0).getSide())) {

                        if (!isTrueColRow(infoPanel.getHexY(), infoPanel.getHexX(),hex.getRow(), hex.getColumn()) || hex.getHextype().equals("sea")) {
                            break here;
                        }

                        for (int i = infoPanel.getHexDivisions().size() - 1; i >= 0; i--) {

                            if (infoPanel.getHexDivisions().get(i).isSelected() && infoPanel.getHexDivisions().get(i).getMovementPoint() >= 5) {

                                infoPanel.getHexDivisions().get(i).setMovementPoint(infoPanel.getHexDivisions().get(i).getMovementPoint() - 5);
                                hex.addDivision(infoPanel.getHexDivisions().get(i));
                                infoPanel.getHexDivisions().remove(i);

                            }

                        }

                        infoPanel.reformPanel(hex.getDivisions());
                        infoPanel.setHexX(hex.getRow());
                        infoPanel.setHexY(hex.getColumn());

                    }

                    // Saldırı
                    if (infoPanel.getHexDivisions().size() > 0 && !infoPanel.getHexDivisions().get(0).getSide().equals(hex.getDivisions().get(0).getSide())) {
                        if (!isTrueColRow(infoPanel.getHexY(), infoPanel.getHexX(),hex.getRow(), hex.getColumn())) {
                            break here;
                        }

                        int damage = 0;

                        for (int i = 0; i < infoPanel.getHexDivisions().size(); i++) {
                            Divisions getI = infoPanel.getHexDivisions().get(i);


                            if (getI.isSelected() && getI.getMovementPoint() >= 10) {

                                damage = damage + getI.getSaldırı()+(getI.getMen()/10);
                                getI.setSelected(false);
                                getI.setMovementPoint(getI.getMovementPoint() - 10);

                                infoPanel.getHexDivisions().get(i).Division(getI.getDivisionImage(), getI.getMen(), getI.getMahiyet(), getI.getSaldırı(), getI.getSavunma(), getI.getDivisionBackground(), getI.getSide(), getI.getMovementPoint());

                            }

                        }

                        int hexDefence = 0;
                        if (hex.getHextype().equals("forrest")) {
                            hexDefence = 4;
                        } else if (hex.getHextype().equals("grassland")) {
                            hexDefence = 2;
                        } else if (hex.getHextype().equals("city")) {
                            hexDefence = 6;
                        }

                            damage = damage / hex.getDivisions().size();
                            for (int i = 0; i < hex.getDivisions().size(); i++) {

                                hex.getDivisions().get(i).setMen(hex.getDivisions().get(i).getMen() - (damage - (hex.getDivisions().get(i).getSavunma() + hexDefence)));

                                if (hex.getDivisions().get(i).getMen() <= 0) {
                                    hex.getDivisions().remove(i);
                                    i--;
                                }
                            }


                        infoPanel.reformPanel(infoPanel.getHexDivisions());
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

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public boolean isTrueColRow(int col, int row, int row2, int col2) {

        if ((row+1 == row2 || row == row2 || row-1 == row2) && (col+1 == col2 || col == col2 || col-1 == col2)) {
         return true;
        }

        return false;
    }

}
