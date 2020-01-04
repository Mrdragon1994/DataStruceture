package com.cql.sparearray

class ArrAndSprseArr {
    public static void main(String[] args) {
        //ArrTOSpareArr()
        SpareArrtoArr()
    }

     static void ArrTOSpareArr() {
          //创建一个二维数组，0表示没有值，1表示黑子，2表示白子
         def arr = new int[11][11];
         arr[1][3] = 1
         arr[2][4] = 2
         //输出原始的二维数组
//         println("原始二维数组：")
//         for (int[] row : arr) {
//             for (int item : row) {
//                 print(item + "\t")
//             }
//             println()
//         }

         //1、先遍历二维数组，得到非0数据的个数
         int sum = 0;
         for (int i = 0; i < arr.length; i++) {
             for (int j = 0; j < arr[i].length; j++) {
                 if (arr[i][j] != 0) {
                     sum++
                 }
             }
         }
        //2、创建稀疏数组
         def spareArr = new int[sum+1][3]
        //3、给稀疏数组赋值
          //00 是原始数据的行数
         spareArr[0][0] = arr.length
         //01 是原始数据的列数
         spareArr[0][1] = arr[0].length
         //02 是非0元素的个数
         spareArr[0][2] = sum

         //遍历二维数组，将非0的值存放到稀疏数组中
         int count = 0
         for (int i = 0; i < arr.length; i++) {
             for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    spareArr[count][0] = i
                    spareArr[count][1] = j
                    spareArr[count][2] = arr[i][j]
                }
             }
         }

         //输出的稀疏数组
        println("得到的稀疏数组为：")
        for (int i = 0; i < spareArr.length; i++) {
            for (int j = 0; j < spareArr[i].length; j++) {
                print(spareArr[i][j] + "\t")
            }
            println()
        }
     }

    static void SpareArrtoArr() {
        //初始化一个稀疏数组
        def spareArr = new int[6][3]
        //稀疏数组第一行信息，表明原始数组有11行，11列，有5个非0数据
        spareArr[0][0] = 11
        spareArr[0][1] = 11
        spareArr[0][2] = 5

        spareArr[1][0] = 1
        spareArr[1][1] = 2
        spareArr[1][2] = 2

        spareArr[2][0] = 2
        spareArr[2][1] = 3
        spareArr[2][2] = 1

        spareArr[3][0] = 5
        spareArr[3][1] = 4
        spareArr[3][2] = 2

        spareArr[4][0] = 6
        spareArr[4][1] = 9
        spareArr[4][2] = 2

        spareArr[5][0] = 4
        spareArr[5][1] = 2
        spareArr[5][2] = 2

        //将稀疏数组恢复为原数组
        //先利用稀疏数组的第一行信息初始化一个稀疏数组
        def arr = new int[spareArr[0][0]][spareArr[0][1]]
        for (int i = 1; i < spareArr.length; i++) {
               arr[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2]
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                print(arr[i][j] + "\t")
            }
            println()
        }
    }
}
