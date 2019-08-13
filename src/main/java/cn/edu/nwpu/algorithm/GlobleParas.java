package cn.edu.nwpu.algorithm;

/**
 * @ClassName GlobleParas
 * @Author: wkx
 * @Date: 2019/3/29 10:50
 * @Version: v1.0
 * @Description: 计算全局变量,使用前必须初始化
 */
public class GlobleParas {
    public static double TIME = 10; //计算时间
    public static double TIME_STEP = 0.0001; //计算时间步长

    public static void initCalcParas(double time, double h){
        TIME =time;
        TIME_STEP = h;
    }
}
