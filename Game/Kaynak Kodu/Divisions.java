import javax.swing.ImageIcon;
import java.awt.*;

public class Divisions {
    private ImageIcon divisionImage;
    private int Men = 100;
    private char Mahiyet = 'A';
    private int Saldırı = 10;
    private int Savunma = 5;
    private ImageIcon divisionBackground;
    private String side = "first";
    private int movementPoint = 20;
    private boolean isSelected = false;
    private int row = 0, column = 0;

    public void Division(ImageIcon divisionImage, int men, char mahiyet, int saldırı, int savunma, ImageIcon background, String side, int movementPoint) {
        this.divisionImage = divisionImage;
        this.Men = men;
        this.Mahiyet = mahiyet;
        this.Saldırı = saldırı;
        this.Savunma = savunma;
        this.divisionBackground = background;
        this.side = side;
        this.movementPoint = movementPoint;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setDivisionImage(ImageIcon divisionImage) {
        this.divisionImage = divisionImage;
    }

    public ImageIcon getDivisionImage() {
        return divisionImage;
    }

    public char getMahiyet() {
        return Mahiyet;
    }

    public int getMen() {
        return Men;
    }

    public int getSaldırı() {
        return Saldırı;
    }

    public int getMovementPoint() {
        return movementPoint;
    }

    public void setMovementPoint(int movementPoint) {
        this.movementPoint = movementPoint;
    }

    public int getSavunma() {
        return Savunma;
    }

    public void setMahiyet(char mahiyet) {
        Mahiyet = mahiyet;
    }

    public void setMen(int men) {
        Men = men;
    }

    public void setSaldırı(int saldırı) {
        Saldırı = saldırı;
    }

    public void setSavunma(int savunma) {
        Savunma = savunma;
    }

    public ImageIcon getDivisionBackground() {
        return divisionBackground;
    }

    public void setDivisionBackground(ImageIcon divisionBackground) {
        this.divisionBackground = divisionBackground;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
