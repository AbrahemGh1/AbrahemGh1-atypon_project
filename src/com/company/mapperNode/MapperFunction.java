package com.company.mapperNode;

import com.company.input.InputBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperFunction extends Mapper<Integer, String, String, Integer> {


    public MapperFunction(InputBlock s) {
        super(s);
    }

    @Override
    protected void map(Integer key, String value) {
//        String[] words = value.split(" ");
//        for (String word : words) {
//            //do something
//            if (!MapperOut.containsKey(word))
//                MapperOut.put(word ,1);
//            else
//                MapperOut.put(word,(Integer) MapperOut.get(word) + 1 );
//        }

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher;
        String str = value;
        if (str != null && !str.equals("")) {
            matcher = pattern.matcher(str);
                while (matcher.find()) {
                    String word = matcher.group();
                    if (!MapperOut.containsKey(word))
                        MapperOut.put(word, 1);
                    else
                        MapperOut.put(word, (Integer) MapperOut.get(word) + 1);
                }
        }
    }
}