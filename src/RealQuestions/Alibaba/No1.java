package RealQuestions.Alibaba;

/*
字符串反转功能，要求编码实现如下3点：
1. 实现一个函数 reverse, 输入参数为一个字符串, 返回结果为反转之后的字符串。
2. 特殊处理, 字符串中出现单词 ali (连续字符) 时, 该单词需要保留顺序。大小写取反。
3. 代码尽量健壮、清晰易懂，包含适当的注释，注释可使用中文。
4. 编写完整可运行的程序。
例如：
"welcome to alibaba!"  输出   "!ABABali OT EMOCLEW"
"ali all in, Ali ilA"   输出 "ALI ILA ,NI LLA ali"
"keep ali"           输出 "ali PEEK"

*/
public class No1 {

    public static String reverse(String str){
        char[] charArr = str.toCharArray();
        int length = charArr.length;
        char temp;
        for(int i=0;i<length;){
            temp = charArr[i];
            if(temp == 'a'){
                if(i+1 < length && i+2 < length && charArr[i+1] == 'l' && charArr[i+2] == 'i'){
                    charArr[i] = 'i';
                    charArr[i+1] = 'l';
                    charArr[i+2] = 'a';
                    i = i+2;
                }else{
                    charArr[i] = 'A';
                }
            }else{
                if(temp >= 'a' && temp <= 'z'){
                    charArr[i] = Character.toUpperCase(temp);
                }else if(temp >= 'A' && temp <= 'Z'){
                    charArr[i] = Character.toLowerCase(temp);
                }
            }
            ++i;
        }
        String result = "";
        for(int i=length-1;i>=0;--i){
            result += charArr[i];
        }
        return result;
    }

    public static void main(String[] args) {

        String str1 = "welcome to alibaba!";
        System.out.println(reverse(str1));

    }

}
