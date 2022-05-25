import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mouselistener implements MouseListener {
    private Camera camera;
    private JPanel panel;
    private InfoPanel infoPanel; private FirstPanel firstPanel;
    private Hexagon selectedHexagon, selectedHexagon2;
    public Hexagon hex;
    private static int result = 0;

    public void ms (JPanel panel, Camera camera) {
        this.camera = camera;
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

            for (int i = 0; i < firstPanel.getHexagons().size(); i++) {
                Hexagon hex = firstPanel.getHexagons().get(i);

                Polygon Poli = new Polygon();
                Poli.xpoints = hex.getX();
                Poli.ypoints = hex.getY();
                Poli.npoints = 6;

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



                if (Poli.contains(e.getX(), e.getY())) {

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

                    this.hex = hex;
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
            for (int he = 0; he < firstPanel.getHexagons().size(); he++) {
                Hexagon hex = firstPanel.getHexagons().get(he);

                Polygon Poli = new Polygon();
                Poli.xpoints = hex.getX();
                Poli.ypoints = hex.getY();
                Poli.npoints = 6;

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

                if (Poli.contains(e.getX(), e.getY())) {


                    if (hex.getDivisions().size() <= 0 || hex.getDivisions().get(0).getSide().equals(infoPanel.getHexDivisions().get(0).getSide())) {

                        if (!isTrueColRow(infoPanel.getHexY(), infoPanel.getHexX(),hex.getRow(), hex.getColumn(), infoPanel.getHex()) || hex.getHextype().equals("sea")) {
                            break here;
                        }

                        for (int i = infoPanel.getHexDivisions().size() - 1; i >= 0; i--) {
                            if (infoPanel.getHexDivisions().get(i).isSelected() && infoPanel.getHexDivisions().get(i).getMovementPoint() >= 5) {

                                infoPanel.getHexDivisions().get(i).setMovementPoint(infoPanel.getHexDivisions().get(i).getMovementPoint() - 5);
                                infoPanel.getHexDivisions().get(i).setSelected(false);
                                hex.addDivision(infoPanel.getHexDivisions().get(i));
                                infoPanel.getHexDivisions().remove(i);

                                this.hex = hex;
                                infoPanel.currentHex = hex;
                                infoPanel.reformPanel(hex.getDivisions());
                                infoPanel.setHexX(hex.getRow());
                                infoPanel.setHexY(hex.getColumn());

                                for(Divisions div : infoPanel.getHexDivisions()) {
                                    div.setSelected(true);
                                }
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

    public void setFirstPanel(FirstPanel firstPanel) {
        this.firstPanel = firstPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public boolean isTrueColRow(int col, int row, int row2, int col2, Hexagon hex) {

        if(col == col2+1 && row+2 > row2) {
            //Top of the hex

            if(row == row2-1 && hex.hexthing.equals("notmiddle")) {
                return true;
            }

            else if (row == row2+1 && hex.hexthing.equals("notmiddle")) {
                return true;
            }

            else if (row == row2) {
                return true;
            }
        }

        if(col == col2) {
            //Same line of hex
            System.out.println("same line: " + col + " " + col2);

            if(row == row2+1) {
                return true;
            }

            else if(row == row2-1) {
                return true;
            }
        }

        if(col == col2-1 && row+2 > row2) {
            //Bottom of the hex

            if(row == row2-1 && hex.hexthing.equals("middle")) {
                return true;
            }

            else if (row == row2+1 && hex.hexthing.equals("middle")) {
                return true;
            }

            else if (row == row2) {
                return true;
            }
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

        if(currentHex.getEdges().get(result).equals("river") || currentHex.getEdges().get(result).equals("empty")) {
            return 1;
        }
         else {
             return 0;
        }

    }

    public int randomDamage(int damage, Divisions division) {
        double dmg = (((Math.random()%(damage))+(damage/useQuality(division.getMahiyet())))*10);
        damage = (int)dmg;

        return damage;
    }

    public int useQuality(char q) {
        if(q == 'A') {
            return 4;
        } else if (q == 'B') {
            return 3;
        } else if (q == 'C') {
            return 2;
        } else if (q == 'D') {
            return 1;
        }

        return 0;
    }

    public void Attack(Hexagon hex) {
        try {
            // ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK ATTACK
            if (infoPanel.getHexDivisions().size() > 0 && !infoPanel.getHexDivisions().get(0).getSide().equals(hex.getDivisions().get(0).getSide())) {
                if (!isTrueColRow(infoPanel.getHexY(), infoPanel.getHexX(), hex.getRow(), hex.getColumn(), infoPanel.getHex())) {
                    System.out.println(2);
                    return;
                }

                int damage = 0;

                for (int i = 0; i < infoPanel.getHexDivisions().size(); i++) {
                    Divisions getI = infoPanel.getHexDivisions().get(i);

                    if (getI.isSelected() && getI.getMovementPoint() >= 15) {
                        damage = damage + randomDamage(getI.getSaldırı(), getI);
                        getI.setMovementPoint(getI.getMovementPoint() - 15);

                        infoPanel.getHexDivisions().get(i).Division(getI.getDivisionImage(), getI.getMen(), getI.getMahiyet(), getI.getSaldırı(), getI.getSavunma(), getI.getDivisionBackground(), getI.getSide(), getI.getMovementPoint());
                    }

                }

                int hexDefence = 0;
                if (hex.getHextype().equals("forrest")) {
                    hexDefence = 1;
                } else if (hex.getHextype().equals("city")) {
                    hexDefence = 2;
                }


                int isThereRiver = riverDetect(hex, infoPanel.currentHex);

                if (isThereRiver == 1) {
                    hexDefence += 1;
                }

                damage = damage / hex.getDivisions().size();
                for (int i = 0; i < hex.getDivisions().size(); i++) {
                    int depot = hex.getDivisions().get(i).getMen();

                    hex.getDivisions().get(i).setMen(hex.getDivisions().get(i).getMen() - ((damage+damage/3 - (randomDamage(hex.getDivisions().get(i).getSavunma() + hexDefence, hex.getDivisions().get(i))))));

                    if (hex.getDivisions().get(i).getMen() > depot) {
                        hex.getDivisions().get(i).setMen(depot);

                        int seconddep = hex.getDivisions().get(0).getMen();
                        hex.getDivisions().get(0).setMen(hex.getDivisions().get(i).getMen() - (((damage*hex.getDivisions().size())+damage/3 - (randomDamage(hex.getDivisions().get(i).getSavunma() + hexDefence, hex.getDivisions().get(i))))));

                        if(hex.getDivisions().get(0).getMen() > seconddep) {
                            hex.getDivisions().get(0).setMen(seconddep);
                        }
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
