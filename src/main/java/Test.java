import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author ChangQilong
 * @Date 2020/4/14 22:37
 */
public class Test {
    public static void main(String[] args) {
      //  System.out.println(getResult("  哈  哈哈!哈##3!哈2哈ss哈坎坎%^^咳咳哈哈"));
        System.out.println(getResultUseSet("  哈  哈哈!哈##3!哈2哈ss哈坎坎%^^咳咳哈哈"));
    }

    public static String getResult(String init) {
        //先删除所有的字母
        StringBuilder stringBuilder = new StringBuilder();
        String result1 = init.replaceAll("\\w", "");
        System.out.println(result1);
        char[] temp = result1.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < temp.length; i++) {
            if (!map.containsKey(temp[i])) {
                if (isChinese(temp[i])) {
                    map.put(temp[i], 1);
                    stringBuilder.append(String.valueOf(temp[i]));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isChinese(char c) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(String.valueOf(c));
        return m.find();
    }

    public static String getResultUseSet(String init) {
        String result1 = init.replaceAll("\\w", "'");
        char[] temp = result1.toCharArray();
        Set<Character> set = new TreeSet<>();
        for(char c : temp) {
            if (isChinese(c)) {
                set.add(c);
            }
        }
        return set.stream().map(i -> i.toString()).collect(Collectors.joining(""));
    }
}
