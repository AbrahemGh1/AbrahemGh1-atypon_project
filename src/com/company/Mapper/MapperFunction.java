package com.company.Mapper;

import com.company.input.InputBlock;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperFunction extends Mapper<Integer, String, String, Integer> {
    public static int all = 0;

    public MapperFunction(InputBlock s) {
        super(s);
    }

    @Override
    protected void map(Integer integer, String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        TreeMap<String, Integer> wordCount = new TreeMap<String, Integer>();
        Matcher matcher;
        String str = value;
        if (str != null && !str.equals("")) {
            matcher = pattern.matcher(str);
            while (matcher.find()) {
                String word = matcher.group();
                if (!wordCount.containsKey(word))
                    wordCount.put(word, 1);
                else
                    wordCount.put(word, wordCount.get(word) + 1);
            }
        }
        wordCount.keySet().forEach(k -> {
            out.put(k, wordCount.get(k));
        });
        all += out.size();
    }
}