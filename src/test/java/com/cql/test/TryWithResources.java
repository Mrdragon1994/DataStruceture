package com.cql.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author ChangQilong
 * @Date 2020/6/29 14:37
 */
public class TryWithResources {
    public static void main(String[] args) throws IOException {
        try (
                Stream<String> lines = Files.lines(Paths.get("D:\\ideaworkspace\\lab8\\src\\main\\java\\oa\\iecas\\annotation\\LoggerOperator.java"));
                PrintWriter printWriter = new PrintWriter("D:/1.txt");

                ) {
            lines.forEach(printWriter::println);
//            new Random(47).ints(0, 1000).limit(10).toArray();
        }
    }
}
