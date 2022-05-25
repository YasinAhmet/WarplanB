import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HexagonWriter {
    public FileWriter writer;
    public File file;

    public void Write(ArrayList<Hexagon> hexagons, String maploc) {
        try {
            file = new File(maploc);
            writer = new FileWriter(file);

            int line = 0;
            for (int i = 0; i < hexagons.size(); i++) {
                if(hexagons.get(i).getColumn() > line) {
                    line++;
                    writer.write("\n");
                }

                writer.write(hexagons.get(i).hexGetTypeInChar());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(file.getPath());
            System.out.println(new File(DivisionWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        }
    }
}
