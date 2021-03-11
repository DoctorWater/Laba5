import com.github.cliftonlabs.json_simple.JsonException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class LoadFromFile {
    public static Hashtable loadFromFile(String filename, Integer i) throws IOException, JsonException {
        Hashtable hash=new Hashtable();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine())
        {
            hash.put(i, JsonToJava.jsonToJava(filename));
            i++;
        }
        return hash;
    }
}
