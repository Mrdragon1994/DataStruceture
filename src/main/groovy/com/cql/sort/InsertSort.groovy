package com.cql.sort

/**
 * @Author ChangQilong* @Date 2020/6/29 21:19
 */
class InsertSort {
    public static void main(String[] args) {
        def arr = [101, 34, 119, 1] as int[]
        insertSort(arr)

    }
    public static void insertSort(int[] arr) {

        //第一轮
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i] //待插入的元素
            int insertIndex = i - 1; //arr[1]前面的数的下标
            //给insertVal找到一个插入的位置
            //insertIndex >= 0 保证给inserVal找插入位置时不越界
            //insertVal < arr[insertIndex] 待插入的数还没有找到适当的位置
            //需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex] //其实就是相当于将后面的数先覆盖掉成当前数相同的
                insertIndex-- //下标继续向前走
            }
            //当退出循环后,说明找到了插入位置,但此时下标是可插入位置的前一位,因此需要加1
            arr[insertIndex + 1] = insertVal
            println("第${i}轮后：" + arr) 
        }
    }
}
