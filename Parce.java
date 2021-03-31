

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
public class Parce {
    private static ArrayList<String> s = new ArrayList<>();
    private static Hashtable hashtable = new Hashtable();
    private static String buffer;
    public static Hashtable parse(String filename) {
            s.clear();
        try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    s.add(line);
            }
                for (int j=1; j<s.size();j+=16)
                {
                    System.out.println(s.size());

                        Scanner scanner = new Scanner(s.get(j));
                        buffer = scanner.findInLine("\\d{1,}");
                        long id = Long.parseLong(buffer);

                        scanner = new Scanner(s.get(j+1));
                        String name = scanner.findInLine("\\:\\s*\\\"\\w*\\\"");
                        scanner = new Scanner(name);
                        name=scanner.findInLine("\\w+");

                        scanner = new Scanner(s.get(j+3));
                        buffer = scanner.findInLine("\\d{1,}");
                        int x = Integer.parseInt(buffer);

                        scanner = new Scanner(s.get(j+4));
                        buffer = scanner.findInLine("\\d{1,}");
                        int y = Integer.parseInt(buffer);

                        scanner = new Scanner(s.get(j+6));
                        buffer = scanner.findInLine("\\d{1,}");
                        long price = Long.parseLong(buffer);


                        scanner = new Scanner(s.get(j+7));
                        String partNumber = scanner.findInLine("\\:\\s*\\\"\\w*\\\"");
                        scanner = new Scanner(partNumber);
                        partNumber=scanner.findInLine("\\w+");
                        scanner = new Scanner(s.get(j+8));
                        String unitOfMeasure = scanner.findInLine("\\:\\s*\\\"\\w*\\\"");
                        scanner = new Scanner(unitOfMeasure);
                        unitOfMeasure=scanner.findInLine("\\w+");
                        Organization organization = new Organization();

                        scanner = new Scanner(s.get(j+10));
                        buffer = scanner.findInLine("\\d{1,}");
                        long organId = Integer.parseInt(buffer);

                        scanner = new Scanner(s.get(j+11));
                        String nameOfOrganization =scanner.findInLine("\\w+");

                        scanner = new Scanner(s.get(j+12));
                        buffer = scanner.findInLine("\\d{1,}\\.\\d{1,}");
                        float income = Float.parseFloat(buffer);

                        scanner = new Scanner(s.get(j+13));
                        String type = scanner.findInLine("\\w+");


                        organization.stringToEnum(type);
                        organization.setAnnualTurnover(income);
                        organization.setName(nameOfOrganization);
                        organization.setId(organId);
                        Coordinates coordinates = new Coordinates();
                        coordinates.setX(x);
                        coordinates.setY(y);
                        Product product = new Product(id,name,coordinates,price,partNumber,unitOfMeasure,organization);
                        System.out.println(id);
                        hashtable.put(id,product);

        }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashtable;
    }
}
//Перевести файл в массив строк ArrayList и потом циклом парсить их поодиночке
/*scanner.useDelimiter("\\\"\\w*\\\"\\:\\s*");
        Long id = scanner.nextLong();
        String name = scanner.next();
        Coordinates coord = new Coordinates();
        coord.setX(scanner.nextInt());
        coord.setY(scanner.nextInt());
        Long price = scanner.nextLong();
        String partNumber = scanner.next();
        String unitOfMeasure = scanner.next();
        Organization manufacturer = new Organization();
        manufacturer.setId(scanner.nextLong());
        manufacturer.setName(scanner.next());
        manufacturer.setAnnualTurnover(scanner.nextFloat());
        manufacturer.setTypeString(scanner.next());
        Product product = new Product(id,name, coord, price, partNumber, unitOfMeasure, manufacturer);
        return product;*/