package Test.biyelunwen.search;

import Test.biyelunwen.entity.SearchSortResultEntity;
import Test.biyelunwen.entity.VideoEntity;

import java.util.*;

public class SearchSortController {

    public static final int MAX_NUMBER = 1000;

    public static SearchSortServiceImpl searchSortService;


    public static void func(){
        searchSort("123");
    }




    public static List<SearchSortResultEntity> searchSort(String words){
        List<String> splitWords = SearchWordProcessController.splitWords(words);
        searchSortService.setMax(MAX_NUMBER);
        List<List<VideoEntity>> lists = new ArrayList<>();
        for(String word : splitWords){
            lists.add(searchSortService.getReverseByWord(word));
        }
        int n = splitWords.size();
        HashSet<VideoEntity> set = new HashSet<>();
        List<VideoEntity> result = new ArrayList<>();
        VideoEntity video;
        for(int i=0;i<n;++i){
            for(int j=0;j<lists.get(i).size();++j){
                video = lists.get(i).get(j);
                if(set.contains(video)){
                    continue;
                }else{
                    set.add(video);
                    result.add(video);
                }
            }
        }
        Collections.sort(result, new Comparator<VideoEntity>() {
            @Override
            public int compare(VideoEntity o1, VideoEntity o2) {
                return (int) (o1.getSearchSortScore() - o2.getSearchSortScore());
            }
        });
        searchSortService.clickSort(result);
        searchSortService.releaseSort(result);









        return null;
    }





}
