//package cn.edu.nwpu.domain.components;
//
//import cn.edu.nwpu.algorithm.CommonAlgorithmUtils;
//import cn.edu.nwpu.domain.LiquidData.LiquidData;
//
///**
// * @ClassName LiquidPipe
// * @Author: wkx
// * @Date: 2019/3/5 10:30
// * @Version: v1.0
// * @Description: 输入流量，压强，输出流量，出口压强
// */
//public class LiquidPipe {
//    private LiquidData liquidData;
//    private double cq; //流量系数
//    private double rho;//液体密度
//    private double d;//管径m
//    private double area;//管路流通面积
//
//    public LiquidPipe(LiquidData liquidData) {
//        this.liquidData = liquidData;
//        cq = CommonAlgorithmUtils.getCq();
//        rho = liquidData.getRho();
//
//    }
//
//    public void setAttributes(LiquidPipeParas liquidPipeParas){
//        this.d = liquidPipeParas.getD();
//        area = d*d*Math.PI*0.25;
//    }
//
//    /**
//     *
//     * @param qin 入口流量kg/s
//     * @return 出口流量kg/s
//     */
//    public double getQ(double qin){
//        return qin;
//    }
//
//    /**
//     *
//     * @param pin 入口压强
//     * @param qin 入口流量
//     * @return 出口压强
//     */
//    public double getP(double pin, double qin){
//        double dp;
//        dp = Math.pow((qin/cq/area), 2) / rho;
//        return pin-dp;
//    }
//}
