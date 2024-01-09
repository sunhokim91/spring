package com.study.oas.config;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

public class JsonConverter {
    private static final String CLASS_PATH = "classpath";
    public static final String MOCK_DATA_RESOURCE_PATH = CLASS_PATH + ":" + "mock";

    public static String fromJson(String path) throws Exception {

        File file = ResourceUtils.getFile(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.lines().collect(Collectors.joining());
        }
    }
}
