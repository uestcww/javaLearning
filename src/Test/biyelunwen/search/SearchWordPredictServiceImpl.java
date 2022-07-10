package Test.biyelunwen.search;

import Test.biyelunwen.entity.UserProfileEntity;

import java.util.List;

public class SearchWordPredictServiceImpl extends SearchWordPredictService {


    @Override
    public boolean isActiveUser(String id) {
        return false;
    }

    @Override
    public UserProfileEntity readUserProfileById(String id) {
        return null;
    }

    @Override
    public List readUserRecentBehaviorById(String id) {
        return null;
    }

    @Override
    public List readVideoHotRank() {
        return null;
    }

    @Override
    public UserProfileEntity calculateUserProfileById(String id) {
        return null;
    }

    @Override
    public int calculateSimDegree(String searchWord, String label) {
        return 0;
    }

    @Override
    public List calculateUserRecentPreference() {
        return null;
    }
}
