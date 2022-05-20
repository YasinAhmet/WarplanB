import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EdgeWriter {
    public FileWriter writer;
    public File file;

    public void Write(ArrayList<Hexagon> hexagons, String edgeloc) {
        try {
            file = new File(edgeloc);
            writer = new FileWriter(file);

            for (int i = 0; i < hexagons.size(); i++) {
                Hexagon hex = hexagons.get(i);

                if(hexagons.get(i).hasEdge()) {

                    writer.write("north:"+ hex.north()+","+"northeast:"+ hex.northeast()+","+"southeast:"+
                            hex.southeast()+","+"south:"+ hex.south()+","+"southwest:"+ hex.southwest()+","+"northwest:"+ hex.northwest()+","+
                            "col:"+hex.getColumn()+","+"row:"+hex.getRow()+";\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(file.getPath());
            System.out.println(new File(DivisionWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        }
    }
}
