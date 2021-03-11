import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileWriter;
import java.io.IOException;
public class JavaToJson {
    public static void javaToJson(String filename){
        Product product = createProduct();

        // Java objects to JSON String
        String json = Jsoner.serialize(product);

        // pretty print
        json= Jsoner.prettyPrint(json);

        System.out.println(json);

        // Java objects to JSON file
        try (FileWriter fileWriter = new FileWriter(filename)) {
            Jsoner.serialize(product, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

