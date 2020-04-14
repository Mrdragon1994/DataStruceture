package com.cql.recusion

/**
 * 从(1,1)出发，如果小球能够走到(6,5)表示小球走到终点
 * 当map[i][j] = 0，表示该点还没有走过；当为1，表示墙体，是不能移动的；当为2，表示是一个通路可以移动
 * 若为3，表示该位置已经走过了，但是走不通
 * 在走迷宫时，需要确定一个策略方法，先走下，若下不同，则走右，否则上，否则左，如果该点走不通，再回溯
 * @Author ChangQilong
 * @Date 2020/4/14 22:24
 */
class Labyrinth {

    public static void main(String[] args) {
        //先创建二维数组模拟一个迷宫
        int[][] map = new int[8][7]
        //使用1表示墙体,无法移动
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1
            map[7][i] = 1
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1
            map[i][6] = 1
        }
        //补充额外两个位置为墙体
        map[3][1] = 1
        map[3][2] = 1
//        map[1][2] = 1
//        map[2][2] = 1

        getWay(map, 1, 1)

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ")
            }
            System.out.println()
        }
    }

    //使用递归回溯来给小球找路
    /**
     * 下右上左的策略
     * @param map  表示地图
     * @param i 从哪个位置开始找(表示行坐标)
     * @param j 从哪个位置开始找(表示列坐标)
     * @return 如果找到通路,返回true，否则就返回false
     */
    public static boolean getWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true
        } else {
            if (map[i][j] == 0) { //如果当前点还没有走过
                map[i][j] = 2 //假定该点可以走通
                if (getWay(map, i+1, j)) { //先向下走
                    return true
                } else if (getWay(map, i, j+1)) { //在向右走
                    return true
                } else if (getWay(map, i-1, j)) { //再向上走
                    return true
                } else if (getWay(map, i, j-1)){ //再向左走
                    return true
                } else {
                    map[i][j] = 3 //表明上下左右都走不通,将该点置为3
                    return false
                }
            } else { //1表明是墙，2表明已经走过，3表示不通，三种情况都不能再次走了
                return false
            }
        }
    }

    //改良方法,获得最短路径
    //目前可以使用枚举法，使用不同的策略去试，看哪种策略得到的2最少

}
