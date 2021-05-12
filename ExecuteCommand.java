import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class ExecuteCommand {
    private static final ArrayList<String> s = new ArrayList<>();
    public static void execute(String filename, ArrayList<String> previousFilenames, Hashtable<String, Product> table, String saveFilename, ArrayList<String> commands, Date initializationDate) throws IOException, RecursionExeption {

        try {
            Path filenamePath= Paths.get(filename);
            if(filenamePath.toRealPath().toString().length()>3 && filenamePath.toRealPath().toString().trim().startsWith("/dev"))
                throw new InvalidPathException("","Строка не может быть преобразована в путь!");
            for (String object : previousFilenames) {
                if (filename.equals(object)) {
                    throw new RecursionExeption("Эта команда создаст рекурсию и вызовет поломку программы. Устраните ее для корректного исполнения скрипта: execute " + object,object);
                }
            }
            previousFilenames.add(filename);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        while ((line = reader.readLine()) != null) {
            s.add(line);
            DetermineCommand.command(line,table,saveFilename, commands, previousFilenames, initializationDate);
        }
    } catch (RecursionExeption recursionExeption) {
            System.out.println(recursionExeption.getMessage());
        } catch (IOException|OutOfMemoryError e) {
            System.out.println("Неверное имя файла!");
        }
        catch (NoSuchElementException e) {
            System.out.println("Нажато Ctrl+D, программа завершена!");
            System.exit(0);
        }
        catch (InvalidPathException e){
        System.out.println("Имя файла неверно!");
    }
    }
}
