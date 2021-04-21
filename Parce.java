

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;
public class Parce {
    private static final ArrayList<String> s = new ArrayList<>();
    private static final Hashtable<String, Product> hashtable = new Hashtable<>();
    private static final Date date = new Date();

    public static Hashtable<String,Product> parse(String filename) {
            s.clear();
        try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    s.add(line);
            }

                s.remove(0);
                s.remove(s.size()-1);
                for (int j=0; j<s.size();j+=17)
                {
                        Scanner scanner = new Scanner(s.get(j));
                        System.out.println(s.get(j));
                        String buffer = scanner.findInLine("\\d+");
                        Long id = Long.parseLong(buffer);

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
                        buffer = scanner.findInLine("\\d{2}\\-\\d{2}\\-\\d{4}");
                        Date date = ParseDate.parseDate(buffer);

                        scanner = new Scanner(s.get(j+7));
                        buffer = scanner.findInLine("\\d{1,}");
                        long price = Long.parseLong(buffer);

                        scanner = new Scanner(s.get(j+8));
                        String partNumber = scanner.findInLine("\\:\\s*\\\"\\w*\\\"");
                        scanner = new Scanner(partNumber);
                        partNumber=scanner.findInLine("\\w+");
                        scanner = new Scanner(s.get(j+9));
                        String unitOfMeasure = scanner.findInLine("\\:\\s*\\\"\\w*\\\"");
                        scanner = new Scanner(unitOfMeasure);
                        unitOfMeasure=scanner.findInLine("\\w+");
                        Organization organization = new Organization();

                        scanner = new Scanner(s.get(j+11));
                        buffer = scanner.findInLine("\\d{1,}");
                        long organId = Long.parseLong(buffer);

                        scanner = new Scanner(s.get(j+12));
                        String nameOfOrganization =scanner.findInLine("\\w+");

                        scanner = new Scanner(s.get(j+13));
                        buffer = scanner.findInLine("\\d{1,}\\.\\d{1,}");
                        float income = Float.parseFloat(buffer);

                        scanner = new Scanner(s.get(j+14));
                        String type = scanner.findInLine("\\w+");

                        scanner = new Scanner(s.get(j+16));
                        buffer = scanner.findInLine("\\:\\s+\\\"\\w+\\\"");
                        scanner=new Scanner(buffer);
                        String key = scanner.findInLine("\\w+");

                        organization.stringToEnum(type);
                        organization.setAnnualTurnover(income);
                        organization.setName(nameOfOrganization);
                        organization.setId(organId);
                        Coordinates coordinates = new Coordinates();
                        coordinates.setX(x);
                        coordinates.setY(y);
                        Product product = new Product(id,name,coordinates,price,date,partNumber,unitOfMeasure,organization,key);
                        hashtable.put(key,product);

        }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashtable;
    }
}