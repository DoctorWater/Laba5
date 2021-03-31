import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Product implements Jsonable, StructureInterface {
    Product(Long id, String name, Coordinates coordinates, Long price, String partNumber, String unitOfMeasure, Organization manufacturer) {
        this.id=id;
        this.name=name;
        this.coordinates=coordinates;
        this.price=price;
        this.partNumber=partNumber;
        this.unitOfMeasure = UnitOfMeasure.valueOf(unitOfMeasure);
        this.manufacturer=manufacturer;
    }

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long price; //Поле может быть null, Значение поля должно быть больше 0
    private String partNumber; //Строка не может быть пустой, Длина строки не должна быть больше 86, Значение этого поля должно быть уникальным, Поле может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле не может быть null

    public Long getId() { return id; }


    public static Long checkId(Hashtable<Long, Product> table) {
        final long[] id = {0};
        id[0] = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        table.forEach((k, v)->{
            if (v.getId()== id[0])
                id[0] =ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        });
        return id[0];
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public Long getPrice() {
        return price;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public Organization getManufacturer() {
        return manufacturer;
    }
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }


    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {

        final JsonObject json = new JsonObject();
        json.put("id", this.getId());
        json.put("name", this.name);
        json.put("coordinates", this.getCoordinates());
        json.put("date", this.getCreationDate());
        json.put("price", this.getPrice());
        json.put("PartNumber", this.partNumber);
        json.put("Manufacturer", this.manufacturer);
        json.put("UnitOfMeasure",this.unitOfMeasure);
        json.toJson(writer);

    }

    @Override
    public void stringToEnum(String s) {
        this.unitOfMeasure=unitOfMeasure.valueOf(s);
    }
}
