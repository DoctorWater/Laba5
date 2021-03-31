import java.util.Hashtable;

public class Find {
    public static Product find(Hashtable<Long, Product> table, Long id)
    {
        final Product[] product = {null};
        table.forEach((k, v)->{
            if (v.getId()==id)
            {
                product[0] =v;
            }
            else
            {
                product[0] = null;

            }
        });

        return product[0];

    }
}
