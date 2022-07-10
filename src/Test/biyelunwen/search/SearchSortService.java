package Test.biyelunwen.search;

import Test.biyelunwen.entity.VideoEntity;

import java.util.List;

public abstract class SearchSortService {

    public abstract List<VideoEntity> getReverseByWord(String word);

    public abstract void setSearchConf(String key, String value);

    public abstract VideoEntity getVideoById(String id);

    public abstract void recordLabel(VideoEntity video);

    public abstract void setMax(int max);

    public abstract List getReverseByWords(List data);

    public abstract int matchingScoreCalculate(List list);

    public abstract void clickSort(List<VideoEntity> list);

    public abstract void releaseSort(List<VideoEntity> list);




}
