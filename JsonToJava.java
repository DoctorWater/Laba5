import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.io.FileReader;
import java.io.IOException;

public class JsonToJava {

    public static Product jsonToJava(String filename) throws IOException, JsonException {


        try (FileReader fileReader = new FileReader(filename)) {

            JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);
            Mapper mapper = new DozerBeanMapper();
            Product product = mapper.map(deserialize, Product.class);
            return product;
        }

    }

}