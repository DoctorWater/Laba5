import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class DetermineCommand {
    public static Hashtable<String, Product> command(String c, Hashtable<String, Product> products, String filename, ArrayList<String> commands) throws IOException, RecursionExeption {
        Scanner scanner = new Scanner(c);
        switch (c) {
            case "help":
                HelpCommand.help();
                break;
            case "info":
                InfoCommand.info(products);
                break;
            case "show":
                System.out.println(products.toString());
                break;
            case "clear":
                products.clear();
                break;
            case "refactor":
                CompareProducts.compare(products);
                break;
            case "exit":
                System.exit(0);
                break;
            case "print_field_descending_price":
                PrintFieldDescendingPrice.print(products);
                break;
            case "history":
                for (int i = 0; i < commands.size(); i++)
                    System.out.println(commands.get(i));
                break;
            case "save":
                SaveToFile.save(filename, products);
                break;
            default:
                if (scanner.findInLine("^insert+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+\\s*");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    try {

                        products = CreateNewProduct.createProduct(products, c);
                        System.out.println(products.toString());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ключ!");
                    }
                }
                if (scanner.findInLine("^remove+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+\\s*");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    try {
                        products.remove(c);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ключ!");
                    }
                }
                if (scanner.findInLine("^update+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+\\s*");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    try {
                        String key = SearchId.search(products, c);
                        products.remove(key);
                        products = CreateNewProduct.createProduct(products, key);
                    } catch (NumberFormatException e1) {
                        System.out.println("Неверный ID!");
                    }
                }
                if (scanner.findInLine("^execute+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\S+");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\S+");
                    ExecuteCommand.execute(c, new ArrayList<String>(), products, filename, commands);
                }
                if (scanner.findInLine("^filter_greater_than_unit_of_measure+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    FilterGreaterUoM.print(products,c);
                }
                if (scanner.findInLine("^remove_greater+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    RemoveCompare.removeGreater(products,c);
                }
                if (scanner.findInLine("^remove_lower+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    RemoveCompare.removeSmaller(products,c);
                }
                if (scanner.findInLine("^count_greater_than_part_number+\\s+") != null) {
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\s+\\w+");
                    scanner = new Scanner(c);
                    c = scanner.findInLine("\\w+");
                    try {
                        CountGreaterPN.count(products, Integer.parseInt(c));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Неверное сравнительное значение!");
                    }
                }
                    break;
                }

        return products;
        }

    }


