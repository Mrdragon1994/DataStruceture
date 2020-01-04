package com.cql.sparearray

/**
 * 将数组和稀疏数组进行转换的方法，默认数组中有0和非0数字，将0去=全部去掉，且通过磁盘IO进行原始数组数据读取和稀疏数据的保存
 */
class ComplicatedArrAndSpareArr {

    public static void main(String[] args) {
       // arrToSpareArr()
        spareArroArr()
    }

    /**
     * 将数组转变为稀疏数组并进行保存，数据保存到resources下，命名为sparearr.data
     */
    static void arrToSpareArr() {
        //先来读取文件，并创建原始数据
        InputStream fileInputStream  = ComplicatedArrAndSpareArr.class.getClassLoader().getResourceAsStream("map.data")
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"))
        String str = null
        int col = 0
        List<String> list = new ArrayList<>()
        while ((str = bufferedReader.readLine()) != null) {
            col = str.split("\t").length
            list.add(str)
        }
        bufferedReader.close()
        fileInputStream.close()

        def arr = new int[list.size()][col]

        //统计共有多少个非0数据
        int sum = 0
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = list[i].split("\t")[j].toInteger()
                if (arr[i][j] != 0) {
                    sum++
                }
            }
        }

        //初始化稀疏数组
        def spareArr = new int[sum + 1][3]

        //向稀疏数组中填充值
        spareArr[0][0] = arr.length
        spareArr[0][1] = arr[0].length
        spareArr[0][2] = sum

        //用来判断该向稀疏数组的第几行存入数据
        int count = 0
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++
                    spareArr[count][0] = i
                    spareArr[count][1] = j
                    spareArr[count][2] = arr[i][j]
                }
            }
        }

        //将稀疏数据存入到文件中
        File file = new File("src/main/resources/sparearr.data")
        if (!file.exists()) {
            file.createNewFile()
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file)
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"))
        for (int i = 0; i < spareArr.length; i++) {
            for (int j = 0; j < spareArr[i].length; j++) {
                bufferedWriter.write(spareArr[i][j] + "\t")
            }
            bufferedWriter.write("\r\n")
        }
        bufferedWriter.close()
        fileOutputStream.close()
    }


    /**
     * 将稀疏数组转换为数组
     */
    static void spareArroArr() {
        File file = new File("src/main/resources/sparearr.data")
        if (!file.exists()) {
            println("文件不存在")
        }
        InputStream fileInputStream = ComplicatedArrAndSpareArr.class.getClassLoader().getResourceAsStream("sparearr.data")
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"))
        //用以记录获取到的每行数据
        List<String> list = new ArrayList<>()
        String str = null
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str)
        }

        //根据读到的数据创建稀疏数组
        def spareArr = new int[list.size()][3]
        for (int i = 0; i < spareArr.length; i++) {
            spareArr[i][0] = list[i].split("\t")[0].toInteger()
            spareArr[i][1] = list[i].split("\t")[1].toInteger()
            spareArr[i][2] = list[i].split("\t")[2].toInteger()
        }

        //初始化一个原始数组
        def arr = new int[spareArr[0][0]][spareArr[0][1]]
        for (int i = 1; i < spareArr.length; i++) {
            arr[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2]
        }

        File file1 = new File("src/main/resources/arrresult.data")
        if (!file1.exists()) file1.createNewFile()

        OutputStream fileOutputStream = new FileOutputStream(file1)
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"))
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                bufferedWriter.write(arr[i][j] + "\t")
            }
            bufferedWriter.write("\r\n")
        }
        bufferedWriter.close()
        fileOutputStream.close()
    }
}
