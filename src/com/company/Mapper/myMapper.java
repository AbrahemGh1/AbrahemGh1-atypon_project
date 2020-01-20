package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myMapper extends Mapper<Integer, String, String, Integer> {
    private static Pattern p = Pattern.compile("[a-zA-Z]+");

    public myMapper(SplitBlockInfo s) throws FileNotFoundException {
        super(s);
    }

    @Override
    protected void map(Integer integer, String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        TreeMap<String, Integer> wordCount = new TreeMap<String, Integer>();
        Matcher matcher;
        String str = value;
        if (!str.equals("")) {
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

    }
}