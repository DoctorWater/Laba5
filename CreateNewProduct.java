
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
        try {
                System.out.println("Пожалуйста, введите название");
                name = in.nextLine();
                if(name==null) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите значение X");
                x = in.nextInt();
                if(x==null) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите значение Y");
                y = in.nextInt();
                coordinates.setX(x);
                coordinates.setY(y);
                System.out.println("Введите значение цены");
                price = in.nextLong();
                System.out.println(price);
                System.out.println("Вывести unit = " + unit);
                System.out.println("Введите единицу измерения. Варианты: \n" +
                        "SQUARE_METERS,\n" +
                        "    PCS,\n" +
                        "    LITERS,\n" +
                        "    MILLILITERS,\n" +
                        "    GRAMS");
                in.nextLine();
                unit=in.nextLine();
                if (!unit.equals("SQUARE_METERS") && !unit.equals("PCS") && !unit.equals("LITERS") && !unit.equals("MILLILITERS") && !unit.equals("GRAMS"))
                    throw new IllegalArgumentException();
                if(unit==null) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите номер части");
                partNumber = in.nextLine();
                Organization organization = CreateNewOrganization.createOrganization(table);
                Product product = new Product(Product.checkId(table),name,coordinates,price,dateNow,partNumber,unit,organization,key);
                table.put(key,product);
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Введенное значение неверно! Повторите ввод");
                CreateNewProduct.createProduct(table,key);
            }



       return table;
    }
}
