import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author ChangQilong
 * @Date 2020/4/13 21:22
 */
public class PolandNotationDemo2 {
    /**
     * 将中缀表达式换后缀表达式的方法
     * 1)、先将中缀表达式转为中缀表达式的List
     */
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        System.out.println(toInfixExpressionList(expression));
        System.out.println(parseSuffixExpression(toInfixExpressionList(expression)));
        System.out.println(Operation.calculate(parseSuffixExpression(toInfixExpressionList(expression))));
    }

    /**
     * 先将中缀表达式转为中缀表达式的List
     * @param string 中缀表达式字符串
     * @return 中缀表达式分割开的String集合
     */
    public static List<String> toInfixExpressionList(String string) {
        List<String> ls = new ArrayList<String>();
        int index = 0; //用来遍历字符串的指针,用于遍历中缀表达式字符串string
        StringBuilder str; //对多位数进行拼接
        char c; //遍历一个字符
        do {
            //如果c是一个非数字,就需要加入到ls中
            if(((c = string.charAt(index)) < 48) || (c = string.charAt(index)) > 57) {
                 ls.add("" + c);
                 index++;
            } else { //如果是一个数组,就要考虑是否要拼接，避免是个多位数
                str = new StringBuilder(); //现将保存多位数的String置""
                while (index < string.length() && (c = string.charAt(index)) >= 48 && (c = string.charAt(index)) <= 57) {
                    str.append(c);
                    index++;
                }
                ls.add(str.toString());
            }
        } while (index < string.length());
        return ls;
    }

    /**
     * 将中缀表达式集合元素转为后缀表达式
     */
    public static List<String> parseSuffixExpression(List<String> list) {
         //初始化两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //因为S2的栈在真个转化过程中,没有POP操作,而且后面还需要逆序输出,因此我们用List来代替Stack
        List<String> s2 = new ArrayList<String>(); //存储中间结果的list
        for(String item : list) {
            //如果是一个数，就入S2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item); //如果是左括号，直接入符号栈
            } else if (item.equals(")")) { //如果是右括号")",则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号位置，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //消除左括号
            } else { //步骤4的问题
                //当item的优先级小于等于s1栈顶运算符的优先级,将S1栈顶的运算符弹出并压入S2中，再次转到4.1与S1中心的栈顶运算符做比较；
                //判断优先级的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item); //将item压入s1中
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //因为是个List,因此不需要逆序输出了,List不存在先进后出的
    }


}

/**
 * 返回一个运算符对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    /**
     * 用后缀表达式进行计算
     * @param list
     * @return
     */
    static int calculate(List<String> list) {
        //创建栈,这里使用Java原生自带的栈
        Stack<String> stack = new Stack<String>();
        for (String item : list) {
            if (item.matches("\\d+")) { //是数字的话，就先入栈,一旦有符号就从栈中弹出两个元素进行计算
                stack.push(item);
            } else {
                Integer num1 = Integer.parseInt(stack.pop()); //先取一个
                Integer num2 = Integer.parseInt(stack.pop()); //再取第二个
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num2 - num1;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("符号不正确");
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}