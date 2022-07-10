package Test.biyelunwen.search;

import Test.biyelunwen.entity.SearchInfoEntity;
import Test.biyelunwen.entity.UserEntity;
import Test.biyelunwen.entity.UserProfileEntity;

import java.util.*;

public class SearchWordPredictController {

    public static SearchWordPredictServiceImpl searchWordPredictService;

    public static SearchInfoEntity getSearchInfo(){
        return null;
    }

    public static UserEntity getUserInfoById(String id){
        return null;
    }

    public static List getPredictLabelList(String id, String searchWord){
        SearchInfoEntity searchInfo = getSearchInfo();
        String userID = searchInfo.getUserID();
        List<String> simLabels = getSimLabelByUserProfile(searchInfo.getSearchWord(), userID);


        return null;

    }

    public static List<String> getSimLabelByUserProfile(String searchWord, String id){
        UserProfileEntity userProfile;
        if(searchWordPredictService.isActiveUser(id)){
            userProfile = searchWordPredictService.readUserProfileById(id);
        }else{
            userProfile = searchWordPredictService.calculateUserProfileById(id);
        }
        Set<String> labelSet = userProfile.getLabelSet();
        Iterator<String> labelIterator = labelSet.iterator();
        String label;
        int score;
        HashMap<String, Integer> labelScore = new HashMap<String, Integer>();
        while(labelIterator.hasNext()){
            label = labelIterator.next();
            score = searchWordPredictService.calculateSimDegree(searchWord, label);
            labelScore.put(label, labelScore.getOrDefault(label, 0) + score);
        }
        List<String> labelList = new ArrayList<>();
        List<Integer> scoreList = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> labelScoreIterator = labelScore.entrySet().iterator();
        while(labelScoreIterator.hasNext()){
            Map.Entry<String, Integer> entry = labelScoreIterator.next();
            labelList.add(entry.getKey());
            scoreList.add(entry.getValue());
        }


        return null;
    }

    public static List getSimLabelByUserRecentBehavior(){
        return null;
    }

    public static List getSimLabelByVideoHotRank(){
        return null;
    }

}
