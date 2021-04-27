import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CountGreaterPN {
    public static void count (Hashtable<String,Product> productHashtable, int checker){
        int counter=0;
        List<Product> tableValues= new ArrayList<>(productHashtable.values());
        for(Product  p : tableValues){
            if (Integer.parseInt(p.getPartNumber())>checker){
                counter++;
            }
        }
        System.out.println("Количество элементов, значение поля partNumber которых больше заданного: " + counter);
    }
}
