import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DivisionReader {
    private File hexFile;
    private Scanner scanner;
    private ArrayList<Divisions> divisions;
    private String side = "first";

    public void setDivReader(String location) {
        try {

            hexFile = new File(location+"\\data\\divisions.txt");
            scanner = new Scanner(hexFile);
            divisions = new ArrayList<>();

        } catch (FileNotFoundException e) {
            System.out.println("Sir, File is not found!");
        }
    }

    public ArrayList<Divisions> ReadFile() {

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Divisions division = new Divisions();
            String curentline = "";

            for (int i = 0; i < line.length(); i++) {


                switch (curentline) {
                    case "side:" -> {
                        curentline = "";
                        division.setSide(linecheck(i, line));
                        i = checkI(i, line);
                    }
                    case "men:" -> {
                        curentline = "";
                        division.setMen(Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                    }
                    case "attack:" -> {
                        curentline = "";
                        division.setSaldırı(Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                    }
                    case "defence:" -> {
                        curentline = "";
                        division.setSavunma(Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                    }
                    case "morale:" -> {
                        curentline = "";
                        division.setMahiyet(linecheck(i, line).charAt(0));
                        i = checkI(i, line);
                    }
                    case "row:" -> {
                        curentline = "";
                        division.setRow(Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                    }
                    case "col:" -> {
                        curentline = "";
                        division.setColumn(Integer.parseInt(linecheck(i, line)));
                        i = checkI(i, line);
                    }
                    case "saveside:" -> {
                        curentline = "";
                        side = linecheck(i, line);
                        i = checkI(i, line);
                    }
                }


                if (line.charAt(i) != ',' && line.charAt(i) != ';') {
                    curentline = curentline + line.charAt(i);
                }

            }

            divisions.add(division);
        }

        return divisions;
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

