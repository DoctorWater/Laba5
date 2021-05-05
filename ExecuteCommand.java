package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class ExecuteCommand {
    private static final ArrayList<String> s = new ArrayList<>();
    public static void execute(String filename, ArrayList<String> previousFilenames, Hashtable<String, Product> table, String saveFilename, ArrayList<String> commands) throws IOException, RecursionExeption {

        try {
            for (String object : previousFilenames) {
                if (filename.equals(object)) {
                    throw new RecursionExeption("Эта команда создаст рекурсию и вызовет поломку программы. Устраните ее для корректного исполнения скрипта: execute " + object,object);
                }
            }
            System.out.println("Добавленное имя файла: " + filename);
            previousFilenames.add(filename);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        while ((line = reader.readLine()) != null) {
            s.add(line);
            DetermineCommand.command(line,table,saveFilename, commands, previousFilenames);
        }
    } catch (RecursionExeption recursionExeption) {
            System.out.println(recursionExeption.getMessage());
        } catch (IOException e) {
            System.out.println("Неверное имя файла!");
        }
        catch (NoSuchElementException e) {
            System.out.println("Нажато Ctrl+D, программа завершена!");
            System.exit(0);
        }
    }
}
