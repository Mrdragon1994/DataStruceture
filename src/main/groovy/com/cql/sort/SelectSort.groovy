package com.cql.sort

/**
 * 选择排序（选择排序会比冒泡排序用的时间短）
 * @Author ChangQilong
 * @Date 2020/6/29 20:35
 */
class SelectSort {
    public static void main(String[] args) {
        def arr = [101, 34, 119, 1] as int[]
        def rand = new Random(47).ints(0, 800000).limit(8000).toArray()
        println("排序前的数组：" + arr)
        long time1 = System.currentTimeMillis()
        selectSort(rand)
        long time2 = System.currentTimeMillis()
        println("Total time: ${time2-time1}")
    }

    public static void selectSort(int[] arr) {
        //优化标志位：
        boolean flag = false

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i
            int min = arr[i]
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j]
                    minIndex = j
                    flag = true
                }
            }
            if (flag) {
                arr[minIndex] = arr[i]
                arr[i] = min
                flag = false
                println("第${i+1}轮需要进行交换")
            }
            println("第${i+1}轮后的数组：" + arr)
        }
    }
}
