package LeetCode.resolved.string;

import java.util.ArrayList;
import java.util.List;

public class No6 {

    public String convert(String s, int numRows) {
        // 如果不大于numRows，那么就是一列，按原样输出就好
        // 如果numRows等于一，那就是一行，还是原样输出
        if(s.length() <= numRows || numRows == 1){
            return s;
        }
        int length = s.length();
        // 模数
        int m = 2*numRows-2;
        int position, i, column = 0;
        String result = "";
        // 一行一行的扫描（变化后的一行）
        while(column < numRows){
            i=0;
            // position的值为推导出来的公式
            // 此时position对应的是每个整列中的字符
            while((position = i*m+column) < length){
                result += s.charAt(position);
                // 这里是整行间的字符，根据其与position的相对偏移量来定位
                if(column != 0 && column !=numRows-1 && position+(numRows-column)*2-2 < length){
                    result+=s.charAt(position+(numRows-column)*2-2);
                }
                i++;
            }
            column++;
        }
        return result;
    }

    // 直接有几行构造几个字符串，遍历原字符串然后一个一个append好
    public String convertLeetCode(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public static void main(String[] args) {
        No6 obj = new No6();
        String str = "AB";
        String res = "AB";
        int column = 1;
        System.out.println(obj.convert(str, column));
        System.out.println(res);
        System.out.println(obj.convert(str, column).equals(res));


    }

}
