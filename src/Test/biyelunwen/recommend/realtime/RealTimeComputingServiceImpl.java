package Test.biyelunwen.recommend.realtime;

import Test.biyelunwen.entity.VideoEntity;

import java.util.List;

public class RealTimeComputingServiceImpl extends RealTimeComputingService {
    @Override
    public double hotScoreCalculate(String id, List<VideoEntity> data) {
        return 0;
    }

    @Override
    public List<VideoEntity> sortByHotScore(List<VideoEntity> list) {
        return null;
    }

    @Override
    public void moveWindow(int start, int length) {

    }

    @Override
    public List<VideoEntity> nearestK(String id, int k) {
        return null;
    }

    @Override
    public double calculateItremCFScore(String userId, String itemId) {
        return 0;
    }
}
