package com.company;
import java.text.*;
import java.util.*;

public class ParseDate {
    public static Date parseDate (String str){
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        try {
            date = ft.parse(str);
        }catch (ParseException e) {
            System.out.println("Ошибка парсинга даты!");
        }
        return date;
    }
}
