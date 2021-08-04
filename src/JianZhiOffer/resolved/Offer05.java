package JianZhiOffer.resolved;

public class Offer05 {

    // 居然结果很不好 用时5.27% 内存5.01%
    public String replaceSpace0(String s) {
        char[] charArr = s.toCharArray();
        String result = "";
        for(char ch : charArr){
            if(ch == ' '){
                result += "%20";
            }else{
                result += ch;
            }
        }
        return result;
    }

    // 好家伙，我直接好家伙 用时100.00% 内存59.78%
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public static void main(String[] args) {
        System.out.println(new Offer05().replaceSpace("We are happy."));
    }

}
