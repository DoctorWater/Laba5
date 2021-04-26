
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, RecursionExeption {
        String buffer;
        Scanner in = new Scanner(System.in);
        String filename = "C:\\Users\\malck\\OneDrive\\Документы\\Лабы\\Laba5\\src\\main\\java\\AllProducts.json";
        Hashtable<String, Product> products= Parce.parse(filename);
        System.out.println(products.isEmpty());
        ArrayList<String> commands = new ArrayList<>();
        while (true)
        {
            System.out.println("ВВЕДИТЕ КОМАНДУ (ВВЕДИТЕ help ДЛЯ ПОЛУЧЕНИЯ СПИСКА ИСПОЛНЯЕМЫХ КОМАНД): ");
            buffer=in.nextLine();
            Scanner scanner = new Scanner(buffer);
            products = DetermineCommand.command(buffer,products,filename,commands);
            buffer=scanner.findInLine("^\\w+");
            commands.add(buffer);
        }

        
    }
}