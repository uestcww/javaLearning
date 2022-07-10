package Test.biyelunwen.search;

import Test.biyelunwen.entity.UserProfileEntity;

import java.util.List;

public abstract class SearchWordPredictService {

    public static final double PREFERENCE_SIM_THRESHOLD = 0.75;
    public static final String USER_RECENT_BEHAVIOR_TABLE = "123";
    public static final String USER_RECENT_PREFERENCE_TABLE = "456";
    public static final String VIDEO_HOT_RANK_TABLE = "789";

    public abstract boolean isActiveUser(String id);
    public abstract UserProfileEntity readUserProfileById(String id);
    public abstract List readUserRecentBehaviorById(String id);
    public abstract List readVideoHotRank();
    public abstract UserProfileEntity calculateUserProfileById(String id);
    public abstract int calculateSimDegree(String searchWord, String label);
    public abstract List calculateUserRecentPreference();

}
