
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateNewProduct {
    public static Product createProduct(Hashtable<Long,Product> table) throws IllegalArgumentException, InputMismatchException {
        Scanner in = new Scanner(System.in);
        Coordinates coordinates = new Coordinates();
        String name = null,unit = null,partNumber = null;
        boolean checker=true;
        Long price = null;
        Integer x;
        int y;
        Organization organization=new Organization();
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
                if(price==null || x<=0) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите единицу измерения. Варианты: \n" +
                        "SQUARE_METERS,\n" +
                        "    PCS,\n" +
                        "    LITERS,\n" +
                        "    MILLILITERS,\n" +
                        "    GRAMS");
                unit = in.nextLine();
                System.out.println("Введите номер части");
                partNumber = in.nextLine();
                checker=true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Введенное значение неверно! Повторите ввод");
                CreateNewProduct.createProduct(table);
            }



       Product product = new Product(Product.checkId(table),name,coordinates,price,partNumber,unit,organization);
       return product;
    }
}
