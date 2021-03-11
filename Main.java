import com.github.cliftonlabs.json_simple.JsonException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, JsonException {
        Integer i=0;
        boolean exit=false;
        String buffer = null, filename=null;
        Scanner in = new Scanner(System.in);
        Hashtable products=LoadFromFile.loadFromFile(filename,i);
        ArrayList<String> commands = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[insert ]+\\d+");
        Matcher matcher = pattern.matcher(buffer);
        while (exit==false)
        {
            System.out.println("ВВЕДИТЕ КОМАНДУ (ВВЕДИТЕ help ДЛЯ ПОЛУЧЕНИЯ СПИСКА КОМАНД): ");
            buffer=in.nextLine();
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
                case matcher.find():
                    products.put(,null);
                    break;
                case 5:
                case 6:
                    break;
                case 7:
                case 8:
                    break;
                default:
                    break;
            }
        }

        
    }
}