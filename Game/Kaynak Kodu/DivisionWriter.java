import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DivisionWriter {
    private FileWriter writer;
    File file;

    public void Write(ArrayList<Divisions> divs, String fileloc){
        try {
            file = new File(fileloc);
            writer = new FileWriter(file);

            for (int d = 0; d < divs.size(); d++) {
                Divisions div = divs.get(d);

                writer.write("side:" + div.getSide() + ",photo:" + div.getImgpath() + ",men:" + div.getMen() + ",attack:" +
                        div.getSaldırı() + ",defence:" + div.getSavunma() + ",morale:" +
                        div.getMahiyet() + ",row:" + div.getRow() + ",col:" + div.getColumn() + ";\n");

            }
            writer.write("saveside:first;");
            writer.close();


        } catch (IOException e) {
            System.out.println(file.getPath());
            System.out.println(new File(DivisionWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        }
    }

    public void writeItAll(ArrayList<Divisions> divs, String side) {
        try {
            writer = new FileWriter(file);

                for (int d = 0; d < divs.size(); d++) {
                    Divisions div = divs.get(d);

                    writer.write("side:" + div.getSide() + ",photo:" + div.getImgpath() + ",men:" + div.getMen() + ",attack:" +
                            div.getSaldırı() + ",defence:" + div.getSavunma() + ",morale:" +
                            div.getMahiyet() + ",row:" + div.getRow() + ",col:" + div.getColumn() + ";\n");

                }
            writer.write("saveside:"+side+";");
            writer.close();


        } catch (IOException e) {
            System.out.println(file.getPath());
            System.out.println(new File(DivisionWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        }
    }

    public void setWriter(File file) {
        try {


            this.file = file;
            this.writer = new FileWriter(file);

        } catch (IOException e) {
            System.out.println("IO");
        }
    }



}