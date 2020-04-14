import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @Author ChangQilong
 * @Date 2020/4/13 22:56
 */
public class ReversePolishMultiCalc {
    static final String SYMBOL = "[+\\-*/()]";


    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    //加减的优先级
    static final int LEVEL_01 = 1;

    //乘除的优先级
    static final int LEVEL_02 = 2;

    //括号
    static final int LEVEL_HIGH = Integer.MAX_VALUE;

    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<>());

    /**
     * 去除空白字符
     */
    public static String replaceAllSPACE(String s) {
        return s.replaceAll("\\s*", "");
    }

    /**
     * 判断是不是数字，支持int，double, long, float
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    /**
     * 判断是不是运算符
     */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL);
    }

    /**
     * 匹配运算符等级
     */
    public static int CalcLevel(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return LEVEL_01;
        } else if ("*".equals(s) || "/".equals(s)) {
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }


}
