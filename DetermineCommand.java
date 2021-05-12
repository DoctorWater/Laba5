import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DetermineCommand {
    public static Hashtable<String, Product> command(String c, Hashtable<String, Product> products, String filename, ArrayList<String> commands, ArrayList<String> previousFilenames, Date initializationDate) throws IOException, RecursionExeption {
        Scanner scanner = new Scanner(c);

        try {
            switch (c) {
                case "help":
                    HelpCommand.help();
                    break;
                case "info":
                    InfoCommand.info(products, initializationDate);
                    System.out.println("Команда выполнена!");
                    break;
                case "show":
                    System.out.println(products.toString());
                    System.out.println("Команда выполнена!");
                    break;
                case "clear":
                    products.clear();
                    System.out.println("Команда выполнена!");
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "print_field_descending_price":
                    PrintFieldDescendingPrice.print(products);
                    System.out.println("Команда выполнена!");
                    break;
                case "history":
                    for (int i = 0; i < commands.size(); i++)
                        System.out.println(commands.get(i));
                    System.out.println("Команда выполнена!");
                    break;
                case "save":
                    SaveToFile.save(filename, products);
                    break;
                default:
                    if (scanner.findInLine("^insert+\\s+\\w+") != null) {
                        try {
                            scanner = new Scanner(c);
                            c = scanner.findInLine("\\s+\\w+\\s*");
                            scanner = new Scanner(c);
                            c = scanner.findInLine("\\w+");

                            products = CreateNewProduct.createProduct(products, c);
                            System.out.println(products.toString());
                            System.out.println("Команда выполнена!");
                        } catch (NumberFormatException | NullPointerException e) {
                            System.out.println("Неверный ключ!");
                        }
                    } else {
                        if (scanner.findInLine("^remove_key+\\s+") != null) {
                            scanner = new Scanner(c);
                            c = scanner.findInLine("\\s+\\w+\\s*");
                            scanner = new Scanner(c);
                            c = scanner.findInLine("\\w+");
                            try {
                                products.remove(c);
                                System.out.println("Команда выполнена!");
                            } catch (NumberFormatException e) {
                                System.out.println("Неверный ключ!");
                            }
                        } else {
                            if (scanner.findInLine("^update+\\s+\\w+") != null) {
                                try {
                                    scanner = new Scanner(c);
                                    c = scanner.findInLine("\\s+\\w+\\s*");
                                    scanner = new Scanner(c);
                                    c = scanner.findInLine("\\w+");
                                    String key = SearchId.search(products, c);
                                    products.remove(key);
                                    products = CreateNewProduct.createProduct(products, key);
                                    System.out.println("Команда выполнена!");

                                } catch (NumberFormatException e1) {
                                    System.out.println("Неверный ID!");
                                }
                            } else {
                                if (scanner.findInLine("^execute_script+\\s+") != null) {
                                    scanner = new Scanner(c);
                                    c = scanner.findInLine("\\s+\\S+");
                                    scanner = new Scanner(c);
                                    c = scanner.findInLine("\\S+");
                                    ExecuteCommand.execute(c, previousFilenames, products, filename, commands, initializationDate);
                                } else {
                                    if (scanner.findInLine("^filter_greater_than_unit_of_measure+\\s+") != null) {
                                        scanner = new Scanner(c);
                                        c = scanner.findInLine("\\s+\\w+");
                                        scanner = new Scanner(c);
                                        c = scanner.findInLine("\\w+");
                                        FilterGreaterUoM.print(products, c);
                                        System.out.println("Команда выполнена!");
                                    } else {
                                        if (scanner.findInLine("^remove_greater+\\s+") != null) {
                                            scanner = new Scanner(c);
                                            c = scanner.findInLine("\\s+\\w+");
                                            scanner = new Scanner(c);
                                            c = scanner.findInLine("\\w+");
                                            RemoveCompare.removeGreater(products, c);
                                            System.out.println("Команда выполнена!");
                                        } else {
                                            if (scanner.findInLine("^remove_lower+\\s+") != null) {
                                                scanner = new Scanner(c);
                                                c = scanner.findInLine("\\s+\\w+");
                                                scanner = new Scanner(c);
                                                c = scanner.findInLine("\\w+");
                                                RemoveCompare.removeSmaller(products, c);
                                                System.out.println("Команда выполнена!");
                                            } else {
                                                if (scanner.findInLine("^count_greater_than_part_number+\\s+") != null) {
                                                    scanner = new Scanner(c);
                                                    c = scanner.findInLine("\\s+\\w+");
                                                    scanner = new Scanner(c);
                                                    c = scanner.findInLine("\\w+");
                                                    try {
                                                        CountGreaterPN.count(products, Integer.parseInt(c));
                                                        System.out.println("Команда выполнена!");
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Неверное сравнительное значение!");
                                                    } catch (NoSuchElementException e) {
                                                        System.out.println("Нажато Ctrl+D, программа завершена!");
                                                        System.exit(0);
                                                    }
                                                } else {
                                                    System.out.println("Введена неверная команда!");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }

        } catch (NullPointerException e) {
            System.out.println("Аргумент пуст!");

        }
        return products;
    }

}
