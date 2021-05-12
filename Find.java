import java.util.Hashtable;

public class Find {
    public static Product find(Hashtable<String, Product> table, Long id)
    {
        final Product[] product = {null};
        table.forEach((k, v)->{
            if (v.getId().equals(id))
            {
                product[0] =v;
            }
        });

        return product[0];

    }
}
