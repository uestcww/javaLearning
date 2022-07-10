package Test.biyelunwen.recommend.realtime;

import Test.biyelunwen.entity.UserBehaviorEntity;
import Test.biyelunwen.entity.UserEntity;
import Test.biyelunwen.entity.VideoEntity;
import Test.biyelunwen.util.ElasticSearchUtil;
import Test.biyelunwen.util.RedisUtil;

import java.util.*;

public class RealTimeComputingController {

    public static RealTimeComputingServiceImpl realTimeComputingService;

    private static final String ALL_USER_ES_SQL = "123";
    private static final String ALL_VIDEO_ES_SQL = "123";
    private static final String ONE_VIDEO_ES_SQL = "123";
    public static final int NEAREST_K = 12;
    public static final double PREDICT_SCORE_THRESHOLD =  123;

    public static void func(){
        generateVideoHotList(1);
        calculateItremCFScore();
        mergeRecallList();
    }

    public static void generateVideoHotList(int topN){
        int userBehaviorlength = Integer.valueOf(RedisUtil.readStringByKey("recentUserBehaviorList"));
        List<UserBehaviorEntity> recentUserBehavior = new ArrayList<>();
        UserBehaviorEntity userBehavior;
        Calendar now = Calendar.getInstance();
        for(int i=0;i<userBehaviorlength;i++){
            userBehavior = (UserBehaviorEntity) RedisUtil.readHashByKey("recentUserBehavior:" + i);
            if(now.getTimeInMillis() - userBehavior.getDate().getTimeInMillis() <= 10 * 60 * 1000){
                recentUserBehavior.add(userBehavior);
            }
        }
        int videoHotLength = Integer.valueOf(RedisUtil.readStringByKey("videopHotList"));
        List<VideoEntity> hotVideoList = new ArrayList<>();
        VideoEntity video;
        for(int i=0;i<videoHotLength;i++){
            video = (VideoEntity) RedisUtil.readHashByKey("videoHot:" + i);
            hotVideoList.add(video);
        }





        topN = 10;
        int len = topN;
    }

    public static void calculateItremCFScore(){
        List<UserEntity> users = ElasticSearchUtil.query(ALL_USER_ES_SQL);
        for(UserEntity user : users){
            Set<VideoEntity> neighborSet = new HashSet<>();
            List<VideoEntity> userRecentLike = (List<VideoEntity>) RedisUtil.readHashByKey("recentUserPreference:" + user.getUserId());
            List<VideoEntity> userLike = (List<VideoEntity>) RedisUtil.readHashByKey("userProfile:" + user.getUserId());
            for(VideoEntity v : userRecentLike){
                neighborSet.addAll(realTimeComputingService.nearestK(v.getVideoId(), NEAREST_K));
            }
            for(VideoEntity v : userLike){
                neighborSet.addAll(realTimeComputingService.nearestK(v.getVideoId(), NEAREST_K));
            }
            HashMap<VideoEntity, Double> videoPredictScore = new HashMap<>();
            double score;
            for(VideoEntity v : neighborSet){
                score = 0;
                score = realTimeComputingService.calculateItremCFScore(user.getUserId(), v.getVideoId());
                if(score > PREDICT_SCORE_THRESHOLD){
                    videoPredictScore.put(v, score);
                }
            }




        }



    }

    public static void mergeRecallList(){

    }


}
