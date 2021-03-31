import com.github.cliftonlabs.json_simple.JsonException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, JsonException {
        boolean exit=false;
        String buffer = null, filename=null;
        Scanner in = new Scanner(System.in);
        Hashtable<Long, Product> products= Parce.parse("C:\\Users\\malck\\OneDrive\\Рабочий стол\\Лабы\\Laba5\\src\\main\\java\\AllProducts.json");
        System.out.println(products.isEmpty());
        ArrayList<String> commands = new ArrayList<String>();
        while (exit==false)
        {
            System.out.println("ВВЕДИТЕ КОМАНДУ (ВВЕДИТЕ help ДЛЯ ПОЛУЧЕНИЯ СПИСКА КОМАНД): ");
            buffer=in.nextLine();
            Scanner scanner = new Scanner(buffer);
            switch (buffer) {
                case "help":
                    HelpCommand.help();
                    commands.add("help");
                    break;
                case "info":
                    InfoCommand.info(products);
                    commands.add("info");
                case "show":
                    System.out.println(products.toString());
                    commands.add("show");
                    break;
                case "print":
                    products.get(0);
                    commands.add("print");
                    break;
                case "clear":
                    products.clear();
                    break;
                case "refactor":
                    CreateNewProduct.createProduct(products);
                case "exit":
                    System.exit(0);
                    break;
                case "history":
                    for(int i= commands.size()-1; i>0;i--)
                    System.out.println(commands.get(i));
                default:
                    if(scanner.findInLine("^insert+\\s+")!=null) {
                        buffer = scanner.findInLine("\\d+");
                        try {
                            products.put(Long.parseLong(buffer), CreateNewProduct.createProduct(products));
                            commands.add("insert");
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ключ!");
                        }
                    }
                    if(scanner.findInLine("^remove+\\s+")!=null){
                        buffer = scanner.findInLine("\\d+");
                        try {
                            products.remove(Long.parseLong(buffer));
                            commands.add("remove");
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ключ!");
                        }
                    }

                    break;
            }
        }

        
    }
}