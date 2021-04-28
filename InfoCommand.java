import java.util.Hashtable;

public class InfoCommand {
    public static void info(Hashtable<String, Product> hash){
        System.out.println("Размер коллекции: "+hash.size()+"\n");
        System.out.println("Тип коллекции: HashTable");
        System.out.println("");
    }


}
