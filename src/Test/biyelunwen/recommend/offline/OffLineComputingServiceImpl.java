package Test.biyelunwen.recommend.offline;

import Test.biyelunwen.entity.VideoEntity;

import java.util.List;

public class OffLineComputingServiceImpl extends OffLineComputingService {


    @Override
    public List<VideoEntity> nearestK(String id, int k) {
        return null;
    }

    @Override
    public int createUserScoreTable() {
        return 0;
    }

    @Override
    public boolean isActive(String id) {
        return false;
    }

    @Override
    public void updateUserActive() {

    }

    @Override
    public void calculateUserProfile(String id) {

    }

    @Override
    public void ALSCalculate() {

    }

    @Override
    public List<List<Integer>> getUserScoreTable() {
        return null;
    }
}
