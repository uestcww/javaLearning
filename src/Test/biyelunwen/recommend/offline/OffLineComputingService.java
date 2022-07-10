package Test.biyelunwen.recommend.offline;

import Test.biyelunwen.entity.VideoEntity;

import java.util.List;

public abstract class OffLineComputingService {

    private List<List<Integer>> UserScoreTable;

    public abstract List<VideoEntity> nearestK(String id, int k);

    public abstract int createUserScoreTable();

    public abstract boolean isActive(String id);

    public abstract void updateUserActive();

    public abstract void calculateUserProfile(String id);

    public abstract void ALSCalculate();

    public abstract List<List<Integer>> getUserScoreTable();


}
