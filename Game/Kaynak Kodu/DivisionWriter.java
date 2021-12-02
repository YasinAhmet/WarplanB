import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DivisionWriter {
    private FileWriter writer;


    public void writeItAll(ArrayList<Hexagon> hexagons, String side) {
        try {
            String txt = "";
            for (int i = 0; i < hexagons.size(); i++) {

                for (int d = 0; d < hexagons.get(i).getDivisions().size(); d++) {
                    Divisions div = hexagons.get(i).getDivisions().get(d);

                     txt = txt + ("side:" + div.getSide() + ",men:" + div.getMen() + ",attack:" +
                            div.getSaldırı() + ",defence:" + div.getSavunma() + ",morale:" +
                            div.getMahiyet() + ",row:" + hexagons.get(i).getRow() + ",col:" + hexagons.get(i).getColumn() + ";\n");




                }
            }
            txt = txt + "saveside:"+side+";";
            System.out.println(txt);
            writer.write(txt);
            writer.close();

        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    public void setWriter(File file) {
        try {
            this.writer = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("IO");
        }
    }



}