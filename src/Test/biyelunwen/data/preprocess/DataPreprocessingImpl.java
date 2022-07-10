package Test.biyelunwen.data.preprocess;

import Test.biyelunwen.entity.UserBehaviorEntity;

import java.util.List;

public class DataPreprocessingImpl extends DataPreprocessingService {


    @Override
    public int invalidDataProcess(List<UserBehaviorEntity> dataList) {
        return 0;
    }

    @Override
    public void defaultDataProcess(UserBehaviorEntity data) {

    }

    @Override
    public void formatDataProcess(UserBehaviorEntity data) {

    }

    @Override
    public void validityFieldProcess(UserBehaviorEntity data) {

    }

    @Override
    public void featureSelect(UserBehaviorEntity data) {

    }

    @Override
    public void discreteFeaturesProcess(UserBehaviorEntity data) {

    }

    @Override
    public void medianCalculate(List<UserBehaviorEntity> dataList) {

    }

    @Override
    public void modeCalculate(List<UserBehaviorEntity> dataList) {

    }

    @Override
    public void averageCalculate(List<UserBehaviorEntity> dataList) {

    }

    @Override
    public int getMedian() {
        return 0;
    }

    @Override
    public int getMode() {
        return 0;
    }

    @Override
    public int getAverage() {
        return 0;
    }
}
