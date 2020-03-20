package com.cql.test

/**
 * @Author ChangQilong* @Date 2020/3/20 22:11
 */
class test {
    public static void main(String[] args) {
        def map = new HashMap()
        map = [
                aa: "aa",
                bb:"bb"

        ]
        println(map.get("aa"))
    }
}
