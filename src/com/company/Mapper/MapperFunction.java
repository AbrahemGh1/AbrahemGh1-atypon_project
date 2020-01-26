package com.company.Mapper;

import com.company.input.InputBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperFunction extends Mapper<Integer, String, String, Integer> {

    public MapperFunction(InputBlock s) {
        super(s);
    }

    Pattern pattern = Pattern.compile("[a-zA-Z]+");
    Matcher matcher;

    @Override
    protected void map(Integer integer, String value) {
        String str = value;
        if (str != null && !str.equals("")) {
            matcher = pattern.matcher(str);
            while (matcher.find()) {
                String word = matcher.group();
                if (!out.containsKey(word))
                    out.put(word, 1);
                else
                    out.put(word, (Integer) out.get(word) + 1);
            }
        }
        //  System.out.println("Line number:"+integer);
    }
}