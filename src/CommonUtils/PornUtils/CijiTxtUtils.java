package CommonUtils.PornUtils;

import java.io.*;
import java.util.HashSet;

// 将慈继阁的小说的广告插入行和垃圾行去掉
public class CijiTxtUtils {

    private static final String ENCODING_UTF8 = "utf8";
    private static final String ENCODING_GBK = "gbk";
    private static final String DIRECTORY_PATH = "D:\\Projects\\javaLearning\\src\\CommonUtils\\TXTUtils\\input\\";
    private static final String OUTPUT_PATH = "D:\\Projects\\javaLearning\\src\\CommonUtils\\TXTUtils\\output\\";

    private static HashSet<String> deleteSetFirst = new HashSet<String>(){{
        add("()");
        add("(）");
        add("（)");
        add("（）");
    }};
    private static HashSet<String> deleteSetSecond = new HashSet<String>(){{
        add("免费电子书下载");
        add("好看的txt电子书");
        add("好看的Txt电子书");
        add("好看的tXt电子书");
        add("好看的txT电子书");
        add("好看的TXt电子书");
        add("好看的TxT电子书");
        add("好看的tXT电子书");
        add("好看的TXT电子书");
        add("免费TXT小说下载");
        add("免费tXT小说下载");
        add("免费TxT小说下载");
        add("免费TXt小说下载");
        add("免费txT小说下载");
        add("免费tXt小说下载");
        add("免费Txt小说下载");
        add("免费txt小说下载");
    }};


    public static boolean isEmptyLine(String line){
        if(line.equals("") || line.trim().equals("")){
            return true;
        }
        return false;
    }

    public static boolean isDeleteLine(String line){
        if(line.charAt(0) == '慈'){
            return line.length() > 6 && line.startsWith("慈继阁小说网");
        }
        if(line.length() >= 2 && deleteSetFirst.contains(line.substring(0,2))){
            if(isEmptyLine(line.substring(2, line.length()))){
                return true;
            }else if(line.length() >= 9 && deleteSetSecond.contains(line.substring(2, 9))){
                return true;
            }else if(line.length() >= 11 && deleteSetSecond.contains(line.substring(2, 11))){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static void clearOneFile(File txt){
        try {
            String fileName = txt.getName();
            if(txt.exists() && txt.isFile()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(txt), ENCODING_GBK);
                BufferedReader bufferedReader = new BufferedReader(read);
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_PATH + "m" + fileName)));
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    if(isEmptyLine(line)){
                        out.println("");
                    }else if(line.charAt(0) == 12288){
                        out.println(line);
                    }else if(!isDeleteLine(line)){
                        out.println(line);
                    }
                }
                read.close();
                out.close();
            }else{
                System.out.println("找不到指定文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearAll(){
        File targetDir = new File(DIRECTORY_PATH);
        File[] txts = targetDir.listFiles();
        File target;
        for(int i=0;i<txts.length;++i){
            target = txts[i];
            if(target.isFile() && target.exists() && target.getName().endsWith(".txt")){
                clearOneFile(target);
            }
        }
    }

    public static void main(String[] args) {
        CijiTxtUtils.clearAll();
    }

}
