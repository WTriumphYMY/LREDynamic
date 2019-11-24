package cn.edu.nwpu.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName StatisticsCal
 * @Author: wkx
 * @Date: 2019/11/8 23:11
 * @Version: v1.0
 * @Description: 统计学参数计算
 */
public class StatisticsCal {
    /**
     *
     * @param doubleList
     * @return 返回均值
     */
    public static double average(List<Double> doubleList){
        double sum = 0.0;
        for (Double value : doubleList) {
            sum += value;
        }
        return sum/doubleList.size();
    }

    /**
     *
     * @param doubleList
     * @return 返回方差
     */
    public static double variance(List<Double> doubleList) {
        double average = average(doubleList);
        double varianceSum = 0.0;
        for (Double value : doubleList) {
            varianceSum += (value - average) * (value - average);
        }
        return varianceSum/(doubleList.size()-1);
    }

    /**
     *
     * @param doubleList
     * @return 返回标准差
     */
    public static double stDeviation(List<Double> doubleList) {
        return Math.sqrt(variance(doubleList));
    }

    /**
     *
     * @param doubleList
     * @return 返回置信区间上限
     */
    public static double confidenceIntervalUp(List<Double> doubleList) {
        return average(doubleList)+1.96*stDeviation(doubleList)/Math.sqrt(doubleList.size());
    }

    /**
     *
     * @param doubleList
     * @return 返回置信区间下限
     */
    public static double confidenceIntervalDown(List<Double> doubleList) {
        return average(doubleList)-1.96*stDeviation(doubleList)/Math.sqrt(doubleList.size());
    }

    /**
     *
     * @param doubleList
     * @return 返回最大值
     */
    public static double max(List<Double> doubleList){
        return Collections.max(doubleList);
    }

    public static double min(List<Double> doubleList){
        return Collections.min(doubleList);
    }

    /**
     *
     * @param doubleList
     * @return 返回中位数
     */
    public static double median(List<Double> doubleList){
        int length = doubleList.size();
        Collections.sort(doubleList);
        if (length % 2 ==0)
            return (doubleList.get(length/2)+doubleList.get(length/2-1))/2;
        else
            return doubleList.get((length-1)/2);

    }

    /**
     *
     * @param doubleList
     * @return 返回上四分位数
     */
    public static double upperQuartile(List<Double> doubleList){
        int tail = doubleList.size()-1;
        int middle = tail/2;
        Collections.sort(doubleList);
        List<Double> halfList =  new ArrayList<>();
        for (int i = middle+1; i <= tail; i++){
            halfList.add(doubleList.get(i));
        }
        return median(halfList);
    }

    /**
     *
     * @param doubleList
     * @return 返回下四分位数
     */
    public static double lowerQuartile(List<Double> doubleList){
        double middle = doubleList.size()/2;
        Collections.sort(doubleList);
        List<Double> halfList =  new ArrayList<>();
        for (int i = 0; i < middle; i++){
            halfList.add(doubleList.get(i));
        }
        return median(halfList);
    }
}
