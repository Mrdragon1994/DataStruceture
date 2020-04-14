package com.cql.stack

/**
 * 逆波兰表达式
 * 中缀表达式转后缀表达式
 * @Author ChangQilong
 * @Date 2020/3/22 18:22
 */
class PolandNotation {
    static void main(String[] args) {
        //逆波兰表达式每个数字和符号均用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -"
        String suffixExpression2 = "30 4 + 5 * 6 -"
        String suffixExpression3 = "4 5 * 8 - 60 + 8 2 / +"
        //先将suffixExpression先装入到一个List中
        //将List传递给一个方法,该方法中配合栈完成计算
        List<String> list = getListString(suffixExpression3)
        println(calculate(list))
    }

    //将逆波兰表达式切依次将数据和运算符放入到List中
    static List<String> getListString(String str) {
        return Arrays.asList(str.split(" "))
    }

    static int calculate(List<String> list) {
        //创建栈,这里使用Java原生自带的栈
        def stack = new Stack<String>()
        for (String item : list) {
            if (item.matches("\\d+")) { //是数字的话，就先入栈,一旦有符号就从栈中弹出两个元素进行计算
                stack.push(item)
            } else {
                Integer num1 = Integer.parseInt(stack.pop()) //先取一个
                Integer num2 = Integer.parseInt(stack.pop()) //再取第二个
                int result = 0
                if (item == "+") {
                    result = num1 + num2
                } else if (item == "-") {
                    result = num2 - num1
                } else if (item == "*") {
                    result = num1 * num2
                } else if (item == "/") {
                    result = num2 / num1
                } else {
                    throw new RuntimeException("符号不正确")
                }
                stack.push(result.toString())
            }
        }
        return Integer.parseInt(stack.pop())
    }

    /**
     * 将中缀表达式转为后缀表达式的方法
     */

}
