package com.xyz;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiShow {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        List<String> strings = Files.readLines(file, Charsets.UTF_8);
        Set<String> apiNames = new HashSet<>(100);
        for (String string : strings) {
            List<String> strings1 = Splitter.on("\"").splitToList(string);
            String api = strings1.get(1);
            List<String> strings2 = Splitter.on(" ").splitToList(api);
            String apiName = strings2.get(1);
            if(apiName.contains("[")) {
                List<String> strings3 = Splitter.on("?").splitToList(apiName);
                String s = strings3.get(0);
                if(s.contains("/gw/api")) {
                    apiNames.add(s);
                }
            }
        }

        for (String apiName : apiNames) {
            System.err.println(apiName);
        }
    }

}
