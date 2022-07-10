package Test.biyelunwen.data.preprocess;

import Test.biyelunwen.entity.UserBehaviorEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataPreprocessingController {

    private static long userActionDataCount;
    private static long userActionDataErrorCount;
    private static long userActionDataSucessCount;

    public static DataPreprocessingService dataPreprocessingService = new DataPreprocessingImpl();

    public static List<UserBehaviorEntity> dataReceive(){
        allDataPreprocess(new ArrayList<UserBehaviorEntity>());
        return null;
    }

    public boolean oneDataPreprocess(UserBehaviorEntity data){
        return false;
    }

    public static void dataSend(){

        List<UserBehaviorEntity> list = new ArrayList<>();

        UserBehaviorEntity e = new UserBehaviorEntity();

        dataPreprocessingService.featureSelect(e);
        dataPreprocessingService.discreteFeaturesProcess(e);
        dataPreprocessingService.medianCalculate(list);
        dataPreprocessingService.modeCalculate(list);
        dataPreprocessingService.averageCalculate(list);
        int a = dataPreprocessingService.getMedian();
        int b = dataPreprocessingService.getMode();
        int c = dataPreprocessingService.getAverage();

        int abc = dataPreprocessingService.invalidDataProcess(list);

    }

    public static void allDataPreprocess(List<UserBehaviorEntity> dataList){
        if(dataList.isEmpty()){
            return;
        }
        dataPreprocessingService.invalidDataProcess(dataList);
        Iterator<UserBehaviorEntity> userBehaviorEntityIterator = dataList.iterator();
        while(userBehaviorEntityIterator.hasNext()){
            UserBehaviorEntity e = userBehaviorEntityIterator.next();
            dataPreprocessingService.defaultDataProcess(e);
            dataPreprocessingService.formatDataProcess(e);
            dataPreprocessingService.validityFieldProcess(e);
        }
        dataSend();
    }

}


