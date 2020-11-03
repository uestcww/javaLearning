package LeetCode.resolved.array;

import java.util.*;

public class No77 {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> arraylist = new ArrayList<Integer>();

    public List<List<Integer>> combine(int n, int k) {
        testFunc(1,n,k);
        return result;
    }

    public void testFunc(int pointer, int n, int k){
        if(k == 0){
            result.add(new ArrayList<Integer>(arraylist));
            return;
        }
        if(n-pointer+1<k){
            return;
        }
        arraylist.add(new Integer(pointer));
        testFunc(pointer+1,n,k-1);
        arraylist.remove(new Integer(pointer));
        testFunc(pointer+1,n,k);
    }

    public static void main(String[] args) {
        No77 obj = new No77();
        int n = 4;
        int k = 2;
        List<List<Integer>> listList = obj.combine(n,k);
        System.out.println(listList);
    }
    /*
    * 旧版，虽然对了，但是有很多可以优化的地方，上面是看过解析后的版本
    * */
//    List<List<Integer>> result = new ArrayList<List<Integer>>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        HashSet<Integer> numset = new HashSet<Integer>(n);
//        List<Integer> arraylist = new ArrayList<Integer>();
//        for(int i=1;i<=n;i++){
//            numset.add(i);
//        }
//        testFunc(numset,k,arraylist);
//        return result;
//    }
//
//    public void testFunc(HashSet<Integer> num, int k, List<Integer> list){
//        if(k == 0){
//            List<Integer> oneResult = new ArrayList<Integer>();
//            oneResult.addAll(list);
//            result.add(oneResult);
//        }
//        Integer temp;
//        Iterator<Integer> iter = num.iterator();
//        if(!iter.hasNext()){
//            return;
//        }
//        HashSet<Integer> newSet = new HashSet<Integer>();
//        newSet.addAll(num);
//        temp = iter.next();
//        newSet.remove(temp);
//        list.add(temp);
//        testFunc(newSet,k-1,list);
//        list.remove(temp);
//        while (iter.hasNext()){
//            temp = iter.next();
//            newSet.remove(temp);
//            list.add(temp);
//            testFunc(newSet,k-1,list);
//            list.remove(temp);
//        }
//    }
}
