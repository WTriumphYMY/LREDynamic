package cn.edu.nwpu.algorithm;

import java.util.Random;

/**
 * @ClassName RandomAlgorithm
 * @Author: wkx
 * @Date: 2019/8/10 11:14
 * @Version: v1.0
 * @Description: 生成正态分布，均匀分布，指数分布随机数算法
 */
public class RandomAlgorithm {
    private static Random random = new Random();

    /**
     *
     * @param ave 均值
     * @param sigma 标准差
     * @return 任意分布的正态分布随机数
     */
    public static double generateGaussianRandom(double ave, double sigma){
        return sigma*random.nextGaussian()+ave;
    }

    /**
     *
     * @param up 区间上限
     * @param down 区间下限
     * @return
     */
    public static double generateUniformRandom(double up, double down){
        return (up-down)*random.nextDouble()+down;
    }

    /**
     * 生成指数分布随机数
     * @param lamda
     * @return
     */
    public static double generateExponentialRandom(double lamda){
        return -(1/lamda) * Math.log(random.nextDouble());
    }
}
