package com.xyz.config.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigUtil {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("file.conf");
        int value = config.getInt("foo.bar");
        System.out.println(value);
    }
}
