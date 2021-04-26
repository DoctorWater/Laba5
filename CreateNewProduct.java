
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateNewProduct {
    public static Hashtable<String,Product> createProduct(Hashtable<String, Product> table, String key) throws IllegalArgumentException, InputMismatchException {
        Scanner in = new Scanner(System.in);
        Coordinates coordinates = new Coordinates();
        String unit = null;
        String partNumber = null;
        String date=null;
        boolean checker=true;
        Long price = null;
        Integer x;
        int y;
        Date dateNow=new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String name = null;
        while (true) {
            try {
                System.out.println("Пожалуйста, введите название");
                name = in.nextLine();
                if (name == null | name=="") {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверное имя!");
            }
        }
        while (true) {
            try {
                System.out.println("Введите значение X");
                x = in.nextInt();
                if (x == null) {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch (IllegalArgumentException | InputMismatchException e){
                System.out.println("Неверное значение X!");
                in.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Введите значение Y");
                y = in.nextInt();
                if (y>775) {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch (IllegalArgumentException | InputMismatchException e){
                System.out.println("Неверное значение Y!");
                in.nextLine();
            }
        }
        coordinates.setX(x);
        coordinates.setY(y);
        while (true) {
            try {
                System.out.println("Введите значение цены");
                price = in.nextLong();
                if (price<=0) {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch (IllegalArgumentException | InputMismatchException e){
                System.out.println("Неверное значение цены!");
                in.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Введите единицу измерения. Варианты: \n" +
                        "SQUARE_METERS,\n" +
                        "    PCS,\n" +
                        "    LITERS,\n" +
                        "    MILLILITERS,\n" +
                        "    GRAMS");
                in.nextLine();
                unit = in.next();
                if (!unit.equals("SQUARE_METERS") && !unit.equals("PCS") && !unit.equals("LITERS") && !unit.equals("MILLILITERS") && !unit.equals("GRAMS"))
                    throw new IllegalArgumentException();
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println("Неверное значение единицы измерения!");
                in.nextLine();
            }
        }
        try {
            while(true) {
                System.out.println("Введите номер части");
                partNumber = in.nextLine();
                if (!Checkers.CheckPartNumber.check(table, partNumber))
                    throw new IllegalArgumentException("Номер части не уникален или null");
                else
                    break;
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        Organization organization = CreateNewOrganization.createOrganization(table);
        Product product = new Product(Product.checkId(table),name,coordinates,price,dateNow,partNumber,unit,organization,key);
        table.put(key,product);
       return table;
    }
}
