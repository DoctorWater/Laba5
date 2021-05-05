package com.company;
import java.util.*;

public class CompareProducts {
    public static List<Product> compare(Hashtable<String, Product> table) {
        List<Product> tableValues= new ArrayList<>(table.values());
        Comparator<Product> pcomp = new ProductComporators.ProductNameComparator().thenComparing(new ProductComporators.ProductPriceComparator());
        Collections.sort(tableValues,pcomp);
        for(Product  p : tableValues){
            System.out.println(p.getName() + " " + p.getPrice());
        }
        return tableValues;
    }
}
