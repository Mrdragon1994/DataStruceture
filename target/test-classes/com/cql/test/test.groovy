package com.cql.test

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @Author ChangQilong* @Date 2020/3/20 22:11
 */
class test {
    public static void main(String[] args) {
        String str = "123456"
        println(str.substring(6,7))
    }

    static boolean parseStrToDate(String str) {
        boolean flag = true
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            LocalDate date = LocalDate.parse(str, dateTimeFormatter)
        } catch(Exception e) {
            flag = false
        }
        return flag
    }
}
