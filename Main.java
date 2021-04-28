import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
            try {
                String buffer = "", bufferIfer;
                Scanner scanner = new Scanner(buffer);
                System.out.println("Пожалуйста, введите имя файла (желательно, полное)");
                buffer = in.nextLine(); //"C:\\Users\\malck\\OneDrive\\Документы\\Лабы\\Laba5\\src\\main\\java\\AllProducts.json"
                if (scanner.findInLine("^\\/dev\\S*") != null)
                    throw new IllegalVarValue("Адрес файла неверен!");
                String filename = buffer;
                Hashtable<String, Product> products = Parce.parse(filename);
                ArrayList<String> commands = new ArrayList<>();
                while (true) {
                    System.out.println("ВВЕДИТЕ КОМАНДУ (ВВЕДИТЕ help ДЛЯ ПОЛУЧЕНИЯ СПИСКА ИСПОЛНЯЕМЫХ КОМАНД): ");
                    buffer = in.nextLine();
                    scanner = new Scanner(buffer);
                    bufferIfer = scanner.findInLine("^\\S+");
                    if (bufferIfer.equals("help") | bufferIfer.equals("info") | bufferIfer.equals("show") | bufferIfer.equals("insert") | bufferIfer.equals("update") | bufferIfer.equals("remove_key") | bufferIfer.equals("clear") | bufferIfer.equals("save") | bufferIfer.equals("execute_script") | bufferIfer.equals("remove_greater") | bufferIfer.equals("remove_lower") | bufferIfer.equals("history") | bufferIfer.equals("count_greater_than_part_number") | bufferIfer.equals("filter_greater_than_unit_of_measure") | bufferIfer.equals("print_field_descending_price")) {
                        commands.add(bufferIfer);
                    }
                    products = DetermineCommand.command(buffer, products, filename, commands, new ArrayList<>());
                }
            } catch (IllegalVarValue | IOException | RecursionExeption e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Нажато Ctrl+D, программа завершена!");
                System.exit(0);
            }
    }
}