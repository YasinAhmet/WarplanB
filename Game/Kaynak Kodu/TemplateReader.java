import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TemplateReader {
    private File hexFile;
    private Scanner scanner;
    private File loc;

    public void setDivReader(String location) {
        try {

            loc = new File(new File(location).getParentFile().getParent());
            hexFile = new File(new File(location).getParentFile().getPath() + "\\templates.txt");
            scanner = new Scanner(hexFile);

        } catch (FileNotFoundException e) {
            System.out.println("Sir, File is not found!");
        }
    }

    public Divisions FindTemplate(String tempSearch) {
        try {
            scanner = new Scanner(hexFile);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
        Divisions division = new Divisions();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String curentline = "";

            goout:
            for (int i = 0; i < line.length(); i++) {

                switch (curentline) {
                    case "template:" -> {
                        curentline = "";
                        if(!linecheck(i, line).equals(tempSearch)) {
                            i = checkI(i, line);
                            break goout;
                        } else {
                            i = checkI(i, line);
                            continue;
                        }
                    }
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
                        return division;
                    }
                    case "photo:" -> {
                        curentline = "";
                        ImageIcon img = new ImageIcon(loc.getPath() + "\\" + linecheck(i, line));
                        division.setDivisionImage(img);
                        division.setDivisionBackground(img);
                        division.setImgpath(linecheck(i, line));
                        i = checkI(i, line);
                    }
                }


                if (line.charAt(i) != ',' && line.charAt(i) != ';') {
                    curentline = curentline + line.charAt(i);
                }

            }

        }
        scanner.close();
        return division;
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
}

