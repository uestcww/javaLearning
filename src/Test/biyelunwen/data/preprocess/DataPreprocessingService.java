package Test.biyelunwen.data.preprocess;

import Test.biyelunwen.entity.UserBehaviorEntity;

import java.util.List;

public abstract class DataPreprocessingService {

    public abstract int invalidDataProcess(List<UserBehaviorEntity> dataList);
    public abstract void defaultDataProcess(UserBehaviorEntity data);
    public abstract void formatDataProcess(UserBehaviorEntity data);
    public abstract void validityFieldProcess(UserBehaviorEntity data);
    public abstract void featureSelect(UserBehaviorEntity data);
    public abstract void discreteFeaturesProcess(UserBehaviorEntity data);
    public abstract void medianCalculate(List<UserBehaviorEntity> dataList);
    public abstract void modeCalculate(List<UserBehaviorEntity> dataList);
    public abstract void averageCalculate(List<UserBehaviorEntity> dataList);
    public abstract int getMedian();
    public abstract int getMode();
    public abstract int getAverage();

}



