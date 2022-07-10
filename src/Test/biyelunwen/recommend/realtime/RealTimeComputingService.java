package Test.biyelunwen.recommend.realtime;

import Test.biyelunwen.entity.VideoEntity;

import java.util.List;

public abstract class RealTimeComputingService {

    public abstract double hotScoreCalculate(String id, List<VideoEntity> data);

    public abstract List<VideoEntity> sortByHotScore(List<VideoEntity> list);

    public abstract void moveWindow(int start, int length);

    public abstract List<VideoEntity> nearestK(String id, int k);

    public abstract double calculateItremCFScore(String userId, String itemId);

}
