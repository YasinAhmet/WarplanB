import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mouselistener implements MouseListener {
    private Camera camera;
    private ArrayList<Hexagon> hexagonArrayList;
    private JPanel panel;
    private InfoPanel infoPanel;
    private Hexagon selectedHexagon, selectedHexagon2;
    private static int result = 0;

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
        } else if (e.getButton() == MouseEvent.BUTTON2 && camera.getZoom() == 4) {
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

                    if(selectedHexagon2 != null && selectedHexagon != null) {
                        selectedHexagon2 = null; selectedHexagon = null;
                    }
                    if(selectedHexagon == null) {
                        selectedHexagon = hex;
                    } else if (selectedHexagon2 == null) {
                        selectedHexagon2 = hex;
                    }

                    infoPanel.currentHex = hex;
                    infoPanel.reformPanel(hex.getDivisions());
                    infoPanel.setHexX(hex.getRow());
                    infoPanel.setHexY(hex.getColumn());

                    if(selectedHexagon == selectedHexagon2 && selectedHexagon != null && selectedHexagon.getDivisions().size() > 0) {
                        if(Objects.equals(selectedHexagon.getDivisions().get(0).getSide(), infoPanel.getInfoListener().getSide())) {
                            for (int g = 0; g < infoPanel.getHexDivisions().size(); g++) {
                                infoPanel.getHexDivisions().get(g).setSelected(true);
                            }

                            selectedHexagon2 = null;
                            selectedHexagon = null;
                        }
                    } else if (selectedHexagon != null && selectedHexagon2 != null){
                        selectedHexagon = null; selectedHexagon2 = null;
                    }

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

                    Attack(hex);

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

    public int riverDetect(Hexagon targetHex, Hexagon currentHex) {
        int targetRow = targetHex.getRow(), targetCol = targetHex.getColumn();
        int currentRow = currentHex.getRow(), currentCol = currentHex.getColumn();

        int row = 0, col = 0;

        if(currentRow > targetRow) {
            row = 1;
        } else if (currentRow == targetRow) {
            row = 2;
        }

        if(currentCol > targetCol) {
            col = 1;
        } else if (currentCol == targetCol) {
            col = 2;
        }

        if(col == 1 && row == 2) {
            result = 0;
        }

        else if (col == 1 && row == 0) {
            result = 1;
        }

        else if (col == 2 && row == 0) {
            result = 2;
        }

        else if (col == 0 && row == 2) {
            result = 3;
        }

        else if (col == 0 && row == 1) {
            result = 4;
        }

        else if (col == 2 && row == 1) {
            result = 5;
        }

        if(currentHex.getEdges().get(result).equals("river")) {
            return 1;
        }
         else {
             return 0;
        }

    }

    public int randomDamage(int damage) {
        double dmg = (((Math.random()%(damage))+(damage/3))*20);
        damage = (int)dmg;

        return damage;
    }

    public void Attack(Hexagon hex) {
        try {
            // ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK
            if (infoPanel.getHexDivisions().size() > 0 && !infoPanel.getHexDivisions().get(0).getSide().equals(hex.getDivisions().get(0).getSide())) {
                if (!isTrueColRow(infoPanel.getHexY(), infoPanel.getHexX(), hex.getRow(), hex.getColumn())) {
                    System.out.println(2);
                    return;
                }

                int damage = 0;

                for (int i = 0; i < infoPanel.getHexDivisions().size(); i++) {
                    Divisions getI = infoPanel.getHexDivisions().get(i);

                    if (getI.isSelected() && getI.getMovementPoint() >= 10) {
                        damage = randomDamage(getI.getSaldırı());
                        getI.setSelected(false);
                        getI.setMovementPoint(getI.getMovementPoint() - 10);

                        infoPanel.getHexDivisions().get(i).Division(getI.getDivisionImage(), getI.getMen(), getI.getMahiyet(), getI.getSaldırı(), getI.getSavunma(), getI.getDivisionBackground(), getI.getSide(), getI.getMovementPoint());
                    }

                }

                int hexDefence = 0;
                if (hex.getHextype().equals("forrest")) {
                    hexDefence += 1;
                } else if (hex.getHextype().equals("city")) {
                    hexDefence += 2;
                }


                int isThereRiver = riverDetect(hex, infoPanel.currentHex);

                if (isThereRiver == 1) {
                    hexDefence += 1;
                }


                damage = damage / hex.getDivisions().size();
                for (int i = 0; i < hex.getDivisions().size(); i++) {
                    int depot = hex.getDivisions().get(i).getMen();

                    hex.getDivisions().get(i).setMen(hex.getDivisions().get(i).getMen() - ((damage+damage/3 - (randomDamage(hex.getDivisions().get(i).getSavunma() + hexDefence)))));

                    if (hex.getDivisions().get(i).getMen() > depot) {
                        hex.getDivisions().get(i).setMen(depot);
                    }

                    if (hex.getDivisions().get(i).getMen() <= 0) {
                        hex.getDivisions().remove(i);
                        i--;
                    }
                }


                infoPanel.reformPanel(infoPanel.getHexDivisions());
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

}
