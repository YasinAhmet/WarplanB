import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HexagonReader  {
    private File hexFile;
    private Scanner scanner;
    private ArrayList<String> lines;

    public void setHexReader(String location) {
        try {

            hexFile = new File(location);
            scanner = new Scanner(hexFile);
            lines = new ArrayList<>();

        } catch (FileNotFoundException e) {
            System.out.println("Sir, File is not found!");
        }
    }

    public void ReadFile() {
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
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

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }
}
