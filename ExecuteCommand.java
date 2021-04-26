import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ExecuteCommand {
    private static final ArrayList<String> s = new ArrayList<>();
    public static void execute(String filename, ArrayList<String> previousFilenames, Hashtable<String, Product> table, String saveFilename, ArrayList<String> commands) throws IOException, RecursionExeption {
        previousFilenames.add(filename);
        try {
            for (String object : previousFilenames) {
                if (filename.equals(object)) {
                    throw new RecursionExeption("Этот файл создаст рекурсию!", object);
                }
            }
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        while ((line = reader.readLine()) != null) {
            s.add(line);
            DetermineCommand.command(line,table,saveFilename, commands);
        }
    } catch (RecursionExeption recursionExeption) {
            System.out.println(recursionExeption.getMessage());
        } catch (IOException e) {
            System.out.println("Неверное имя файла!");
        }
    }
}
