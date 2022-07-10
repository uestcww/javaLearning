package Test.biyelunwen.recommend.offline;

import Test.biyelunwen.util.ALSUtil;
import Test.biyelunwen.entity.ActiveUserEntity;
import Test.biyelunwen.entity.UserEntity;
import Test.biyelunwen.entity.VideoEntity;
import Test.biyelunwen.util.ElasticSearchUtil;
import Test.biyelunwen.util.RedisUtil;

import java.util.*;

public class OffLineComputingController {


    public static OffLineComputingServiceImpl offLineComputingService;
    public static final int RECESSIVE_FEATURE_NUMBER = 12;
    public static final String ALL_USER_ES_SQL = "123";
    public static final int NEAREST_K = 12;

    public static void func(){
        pearsonPredictScoreCalculate();
        updateActiveUser();
    }

    //第一条是，最近一周内有效登录频率超过三次，有效登录需要登录后停留5分钟以上。
    //第二条是在最近半小时内登录过系统。

    public static void updateActiveUser(){
        List<UserEntity> users = ElasticSearchUtil.query(ALL_USER_ES_SQL);
        for(UserEntity user : users){
            if(offLineComputingService.isActive(user.getUserId())){
                ActiveUserEntity activeUser = (ActiveUserEntity) RedisUtil.readHashByKey("ActiveUser:" + user.getUserId());
                Calendar now = Calendar.getInstance();
                Calendar lastTime = activeUser.getLastTime();
                if(now.getTimeInMillis() - lastTime.getTimeInMillis() <= 30 * 60 * 1000){
                    continue;
                }else{
                    Calendar first = activeUser.getFirstDate();
                    Calendar second = activeUser.getSecondDate();
                    Calendar third = activeUser.getThirdDate();
                    now = Calendar.getInstance();
                    long firstRes = now.getTimeInMillis() - first.getTimeInMillis();
                    long secondRes = now.getTimeInMillis() - second.getTimeInMillis();
                    long thirdRes = now.getTimeInMillis() - third.getTimeInMillis();
                    long max = 7 * 24 * 60 * 60 * 1000;
                    if(firstRes <= max && secondRes <= max && thirdRes <= max){
                        continue;
                    }else{
                        RedisUtil.deleteKey("ActiveUser:" + user.getUserId());
                    }
                }
            }else{



            }
        }
    }

    public static void pearsonPredictScoreCalculate(){
        List<List<Integer>> userScoreTable = offLineComputingService.getUserScoreTable();
        ALSUtil.createModel();
        ALSUtil.input(userScoreTable, RECESSIVE_FEATURE_NUMBER);
        ALSUtil.calculate();
        List<List<Integer>> userTable = ALSUtil.getUserTable();
        List<List<Integer>> itemTable = ALSUtil.getItemTable();
        List<UserEntity> users = ElasticSearchUtil.query(ALL_USER_ES_SQL);
        for(UserEntity user : users){
            List<VideoEntity> userLike = (List<VideoEntity>) RedisUtil.readHashByKey("recentUserPreference:" + user.getUserId());
            Set<VideoEntity> nears = new HashSet<>();
            for(VideoEntity v : userLike){
                nears.addAll(offLineComputingService.nearestK(v.getVideoId(), NEAREST_K));
            }
            userTable.size();
            itemTable.size();




        }


    }

    public static List generateUserProfile(){



        return null;
    }



}
