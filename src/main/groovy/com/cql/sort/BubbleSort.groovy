package com.cql.sort

import java.util.stream.IntStream

/**
 * 优化版冒泡排序算法
 * @Author ChangQilong
 * @Date 2020/6/28 21:57
 */
class BubbleSort {
    public static void main(String[] args) {
        def origin = [3,9,-1,10,20] as int[]
        def array = [1,2,3,4] as int[]
        long time1 = System.currentTimeMillis()
        def rand = new Random(47).ints(0, 800000).limit(8000).toArray()
        //println("初始的数组：" + origin)
        bubbleSort(rand)
        //println("使用冒泡排序后的算法：" + origin)
        long time2 = System.currentTimeMillis()
        println("Total time: ${time2-time1}")

    }

    static void bubbleSort(int[] array) {
        //开始排序
        //先置一个标志flag,若在排序的某一趟中发现不需要交换元素,表明已经有序了,就直接退出当前方法
        boolean flag = false
        //外层循环表示趟数,趟数等于数组长度减1
        for (int i = 1; i < array.length; i++) {
            //用以计算在小循环中,前面元素是否比后面元素小
            int count = 0
            //内层循环表示每次比较的位置
            if (!flag) {
//                println("进行第${i}次")
                for (int j = 0; j < array.length - i; j++) {
                        if (array[j] > array[j+1]) {
                            int temp = array[j+1]
                            array[j+1] = array[j]
                            array[j] = temp
                        } else {
                            //如果前面元素比后面元素小,则加1
                            count++
                        }
                }
            } else {
                return
            }
            //通过比较叠加出的count值和当前小循环中的j的最终值
            if (count == array.length - i) {
                flag = true
            }
        }

    }
}
