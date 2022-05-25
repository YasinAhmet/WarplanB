import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditorPanel extends JPanel {
    Editor editor;
    JButton addUnit;
    JButton editHexType;
    JButton addRiver;
    JButton removeUnit;
    public FirstPanel firstPanel;

    public void launchPanelStuff(Editor editor) {
        this.editor = editor;
        this.setBounds(1000,35,366,720);

        this.setBackground(new Color(182, 141, 64));
        Color buttonColor = new Color(188, 38, 32);

        addUnit = new JButton("Add unit to the selected hex");
        removeUnit = new JButton("Remove all the units of the selected hex");
        editHexType = new JButton("Change the selected hex");
        addRiver = new JButton("Add river to the selected hex");

        addUnit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        removeUnit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        editHexType.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        addRiver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));

        addUnit.setBackground(buttonColor);
        removeUnit.setBackground(buttonColor);
        editHexType.setBackground(buttonColor);
        addRiver.setBackground(buttonColor);

        addUnit.addActionListener(this::UnitEvent);
        removeUnit.addActionListener(this::removeUnitEvent);
        editHexType.addActionListener(this::HexEvent);
        addRiver.addActionListener(this::EdgeEvent);

        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        add(addUnit);
        add(removeUnit);
        add(editHexType);
        add(addRiver);

        this.setBounds(1000,35,366,720);

        setVisible(true);
    }

    public void removeUnitEvent(ActionEvent e) {
        ArrayList<Divisions> divs = firstPanel.mouselistener.hex.getDivisions();

        for(Divisions div : divs) {
            divs.remove(div);
        }
    }

    public void UnitEvent(ActionEvent a) {
        AddUnit(editor.firstPanel.getDivisions());
    }

    public void HexEvent(ActionEvent a) {
        firstPanel.mouselistener.hex.setHextype(JOptionPane.showInputDialog("Insert Hex Type"));
        //AddHex(new File(firstPanel.maplocation));
    }

    public void EdgeEvent(ActionEvent e) {
        AddRiver(editor.mouselistener);
    }

    public void AddUnit (ArrayList<Divisions> divisions) {
        Divisions division = new Divisions();

        division = firstPanel.divisionReader.tmp.FindTemplate(JOptionPane.showInputDialog("Insert Template Name"));

        if(division == null) {
            return;
        }

        division.setColumn(firstPanel.mouselistener.hex.getColumn());
        division.setRow(firstPanel.mouselistener.hex.getRow());

        firstPanel.mouselistener.hex.addDivision(division);
        divisions.add(division);
    }

    /* OLD HEX THING

    public void AddHex(File maploc) {
        try {
            RandomAccessFile scanfile = new RandomAccessFile(maploc, "rw");
            Hexagon hex = new Hexagon();

            hex.setRow(Integer.parseInt(JOptionPane.showInputDialog("Insert Hexagon Row")));
            hex.setColumn(Integer.parseInt(JOptionPane.showInputDialog("Insert Hexagon Column")));
            hex.setHextype(JOptionPane.showInputDialog("Insert Hexagon Type"));

            for(int z = 0; scanfile.length() > z; z++) {

                if(z == hex.getColumn()) {
                    for(int i = 0; i < hex.getRow(); i++) {
                        scanfile.readByte();
                        scanfile.write(String.valueOf(hex.hexGetTypeInChar()).getBytes());
                    }

                    if(scanfile.length() == hex.getColumn()-1) {
                    scanfile.write("\n".getBytes());
                    }

                    //setPastHexes(hex, scanfile);
                    scanfile.write(String.valueOf(hex.hexGetTypeInChar()).getBytes());
                }

                if(scanfile.readLine() == null) {
                    break;
                }
            }
            scanfile.close();

            editor.refreshPanel();

            editor.addKeyListener(firstPanel.returnCameraKey());
            editor.requestFocusInWindow();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found sir");
        } catch (IOException e) {
            System.out.println("Io exp");
            e.printStackTrace();
        }
    }

    public void setPastHexes(Hexagon hex, RandomAccessFile scanfile) throws IOException {

        boolean there_is_a_hex = false;
        for(Hexagon hexagon : firstPanel.getHexagons()) {
            if(hexagon.getRow() == hex.getRow()-1 && hexagon.getColumn() == hex.getColumn()) {
                there_is_a_hex = true;
            } else if (hex.getRow()-1 == -1) {
                there_is_a_hex = true;
            }

            if(!there_is_a_hex) {
                setPastHexes(hexagon, scanfile);
            }
        }

        if(!there_is_a_hex) {
            scanfile.write("e".getBytes());
        }


    }

     */

    public void AddRiver(Mouselistener mouselistener) {
        Hexagon hex = firstPanel.mouselistener.hex;

        String side = JOptionPane.showInputDialog("Insert Hexagon Edge (north, northeast etc)");
        String type = JOptionPane.showInputDialog("Insert Hexagon Edge Type (river, normal etc)");

        int sides = 0;
        switch (side) {
            case "northeast": sides = 1;
            break;

            case "southeast": sides = 2;
            break;

            case "south": sides = 3;
            break;

            case "southwest": sides = 4;
            break;

            case "northwest": sides = 5;
        }

        hex.edit(sides, type);
    }
}
