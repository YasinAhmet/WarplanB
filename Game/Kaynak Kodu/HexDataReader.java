import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HexDataReader {
    private File hexFile;
    private Scanner scanner;
    private String side = "first";

    public void setDivReader(String location) {
        try {

            hexFile = new File(location);
            scanner = new Scanner(hexFile);

        } catch (FileNotFoundException e) {
            System.out.println("Sir, File is not found!");
        }
    }

    public ArrayList<Hexagon> ReadFile(ArrayList<Hexagon> hexagons) {

        ArrayList<String> edges = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String curentline = "";
            int row = -1, col = -1;

            for (int i = 0; i < line.length(); i++) {



                switch (curentline) {
                    case "north:", "northeast:", "southeast:", "south:", "southwest:", "northwest:" -> {
                        edges.add(linecheck(i, line));
                        i = checkI(i, line);
                        curentline = "";
                    }
                    case "col:" -> {
                        col = (Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                        curentline = "";
                    }
                    case "row:" -> {
                        row = (Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                        curentline = "";
                    }

                }

                    if (line.charAt(i) != ',' && line.charAt(i) != ';') {
                        curentline = curentline + line.charAt(i);
                    }

            }

            if(row != -1 && col != -1) {
                for (int i = 0; i < hexagons.size(); i++) {
                    if(hexagons.get(i).getRow() == row && hexagons.get(i).getColumn() == col) {
                        hexagons.get(i).setEdges(edges);
                        edges = new ArrayList<>();
                    }

                }
            }
        }

        return hexagons;
    }

    public String linecheck(int i, String line) {
        String curentline = "";

        while(line.charAt(i) != ',' && line.charAt(i) != ';') {
            curentline = curentline + line.charAt(i);

            i++;
        }

        return curentline;
    }

    public int checkI(int i, String line) {

        while (line.charAt(i) != ',' && line.charAt(i) != ';') {
            i++;
        }

        return i;
    }

    public void setHexFile(File hexFile) {
        this.hexFile = hexFile;
    }

    public File getHexFile() {
        return hexFile;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getSide() {
        return side;
    }
}
