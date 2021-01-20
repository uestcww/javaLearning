package LeetCode.unsolved;

import java.util.ArrayList;
import java.util.List;

public class No127 {

    // 超出时间限制了，我服了，我是菜逼
    private List<String> words;
    private boolean[] visited;
    private int result = 0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        wordList.remove(beginWord);
        wordList.remove(endWord);
        words = wordList;
        visited = new boolean[words.size()];
        backtrack(beginWord, endWord);
        return result;
    }
    public void backtrack(String word, String targetWord){
        if(result == word.length()){
            return;
        }
        if(canTrans(word, targetWord)){
            int count = 0;
            for(boolean isSelected : visited){
                if(isSelected){
                    ++count;
                }
            }
            count += 2;
            if(result == 0 || count < result){
                result = count;
            }
            return;
        }
        for(int i=0;i< words.size();++i){
            if(!visited[i] && canTrans(word, words.get(i))){
                visited[i] = true;
                backtrack(words.get(i), targetWord);
                visited[i] = false;
            }
        }
    }
    public boolean canTrans(String word, String targetWord){
        char[] wordArr = word.toCharArray();
        char[] targetArr = targetWord.toCharArray();
        int dif = 0;
        for(int i=0;i<wordArr.length;++i){
            if(wordArr[i] != targetArr[i]){
                if(++dif > 1){
                    return false;
                }
            }
        }
        return dif == 1;
    }

    public static void main(String[] args) {
        No127 obj = new No127();
        System.out.println("5 ? " + obj.ladderLength("hit", "cog",
                new ArrayList<String>(){{add("hot");add("dot");add("dog");add("lot");add("log");add("cog");}}));
        System.out.println("0 ? " + obj.ladderLength("hit", "cog",
                new ArrayList<String>(){{add("hot");add("dot");add("dog");add("lot");add("log");}}));
        System.out.println("3 ? " + obj.ladderLength("hot", "dog",
                new ArrayList<String>(){{add("hot");add("cog");add("dog");add("tot");add("hog");add("hop");add("pot");add("dot");}}));
    }

}
