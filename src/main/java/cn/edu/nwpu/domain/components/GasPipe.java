//package cn.edu.nwpu.domain.components;
//
//import cn.edu.nwpu.domain.GasData.GasData;
//import cn.edu.nwpu.domain.component.GasPipeParas;
//
///**
// * @ClassName GasPipe
// * @Author: Trium
// * @Date: 2019/2/26 14:35
// * @Version: v1.0
// * @Description: 气体管路 输入入口流量，密度，温度， 输出压强与出口流量,温度
// */
//public class GasPipe {
//    private GasData gasData;
//    private double Rg;//气体常数J/kg`K
//    private double l;//管路长度m
//    private double d;//管路直径m
//    private double s;//管路面积m2
//    private double k;//气体比热比
//    private double f;//摩擦系数0.035
//    private double zeta;//流容损失系数（读泽塔ζ）
//    private double xi;//压力损失系数（读克西ξ）
//    private double temp;//出口温度
//    private double p;//出口压强
//    private double q;//出口流量
//
//    public GasPipe(GasData gasData) {
//        this.gasData = gasData;
//        Rg = gasData.getR();
//        k = gasData.getKappa();
//    }
//
//    public void setAttributes(GasPipeParas gasPipeParas){
//        this.l = gasPipeParas.getL();
//        this.d = gasPipeParas.getD();
//        this.s = Math.PI*d*d*0.25;
//        this.f = gasPipeParas.getF();
//        this.xi = f*l/2/d/s/s;
//    }
//
//    /**
//     * @param pin 入口压强，由上一个组件的出口压强得到
//     * @param qin 入口流量，由上一个组件的出口流量得到kg/s
//     * @param rhoin 入口介质的密度，也由上一组件得到kg/m3
//     * @return 返回出口压强
//     */
//    public double getP(double pin, double qin, double rhoin){
//        double dp;
//        dp = xi*qin*qin/rhoin;
//        return pin-dp;
//    }
//
//    /**
//     *
//     * @param qin 入口流量kg/s
//     * @param rhoin 入口密度kg/m3
//     * @return
//     */
//    public double getQ(double qin, double rhoin, double tempin){
//        double qout;
//        zeta = s*l/k/Rg/tempin;
//        qout = qin - zeta*xi*qin*qin/rhoin;
//        return qout;
//    }
//
//    /**
//     *
//     * @param tempin 入口温度
//     * @return 出口温度
//     */
//    public double getTemp(double tempin){
//        return tempin;
//    }
////    /**
////     * @param temp 温度，单位K
////     * @param p 压强，单位Pa
////     * @param dp 压强关于时间导数
////     * @param q 出口质量流率kg/s
////     * @param qi 入口质量流率kg/s
////     * @return 温度关于时间导数
////     */
////    public double getDt(double temp, double p, double dp, double q, double qi){
////        double dt;
////        dt = temp*dp/p + Rg*temp*temp*(q-qi)/s/l/p;
////        return dt;
////    }
////
////    /**
////     * @param temp 管路内温度
////     * @param p 管路内压强
////     * @param q 出口流量
////     * @param qi 入口流量
////     * @param ti 入口温度
////     * @param pi 入口压强
////     * @return 出口流量关于时间导数
////     */
////    public double getDq(double temp, double p, double q, double qi, double ti, double pi){
////        double dq;
////        dq = Rg*(qi*qi*ti/pi-q*q*temp/p)/s/l + s*(p-pi)/l - 2*f*Rg*q*q*temp/s/d/p;
////        return dq;
////    }
////
////    /**
////     * @param q 管路流量
////     * @param temp 管路温度
////     * @param qi 入口流量
////     * @param ti 入口温度
////     * @return
////     */
////    public double getDp(double q, double temp, double qi, double ti){
////        double dp;
////        dp = k*Rg*(-qi*ti+q*temp)/s/l;
////        return dp;
//
////    }
//
//}
