package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.LiquidTank;
import cn.edu.nwpu.algorithm.GlobleParas;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LiquidTankCalc
 * @Author: wkx
 * @Date: 2019/3/30 10:18
 * @Version: v1.0
 * @Description: 液体贮箱计算类
 *  依据： 《002-2014200285-白晶晶》与《液体火箭增压输送系统动态特性仿真与分析_王文斌》推进剂贮箱
 */
public class LiquidTankCalc {
    private LiquidTank liquidTank;
    private double k;//挤压气体比热比
    private double rho_l;//推进剂密度
    private double CA;//流量系数
    private List<Double> p = new ArrayList<>();//压强，Pa
    private List<Double> rho = new ArrayList<>();//密度
    private List<Double> q = new ArrayList<>();//质量流率，kg/s
    private List<Double> v = new ArrayList<>();//气腔体积，kg/s
    private List<Double> qin = new ArrayList<>();//流入质量

    private double[] dp = new double[4];
    private double[] drho = new double[4];
    private double[] dv = new double[4];
    private double h;

    public LiquidTankCalc(LiquidTank liquidTank) {
        this.liquidTank = liquidTank;
        this.k = liquidTank.getK();
        this.rho_l = liquidTank.getRho_l();
        this.CA = liquidTank.getCA();
        p.add(liquidTank.getP0());
        rho.add(liquidTank.getRho0());
        q.add(liquidTank.getQ0());
        v.add(liquidTank.getV0());
        qin.add(0.0);
        h = GlobleParas.TIME_STEP;
    }
    
    public void execute(double pin, double rhoin, double pe){
        int index = p.size()-1;
        double qin = getQmi(pin, p.get(index),rhoin);
        dv[0] = getDv(getQ(p.get(index), pe));
        dp[0] = getDp(p.get(index), v.get(index), rho.get(index), dv[0], qin);
        drho[0] = getDrho(rho.get(index), v.get(index), dv[0], qin);
        for (int i = 1; i < 4; i++){
            if (i < 3){
                dv[i] = getDv(getQ(p.get(index) + 0.5*h*dp[i-1], pe));
                dp[i] = getDp(p.get(index) + 0.5*h*dp[i-1], v.get(index) + 0.5*h*dv[i-1],
                        rho.get(index) + 0.5*h*drho[i-1], dv[i-1], qin);
                drho[i] = getDrho(rho.get(index) + 0.5*h*drho[i-1], v.get(index) + 0.5*h*dv[i-1], dv[i-1], qin);
            }else {
                dv[i] = getDv(getQ(p.get(index) + h*dp[i-1], pe));
                dp[i] = getDp(p.get(index) + h*dp[i-1], v.get(index) + h*dv[i-1], rho.get(index) + h*drho[i-1],
                        dv[i-1], qin);
                drho[i] = getDrho(rho.get(index) + h*drho[i-1], v.get(index) + h*dv[i-1], dv[i-1], qin);
            }
        }
        p.add(p.get(index) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
        rho.add(rho.get(index) + h*(drho[0] + 2*drho[1] + 2*drho[2] + drho[3])/6);
        q.add(getQ(p.get(index+1), pe));
        v.add(v.get(index) + h*(dv[0] + 2*dv[1] + 2*dv[2] + dv[3])/6);
        this.qin.add(qin);
    }

    /**
     *
     * @param p 压强Pa
     * @param v 气腔体积m3
     * @param rho 气腔密度kg/m3
     * @param dv 气腔体积导数m3
     * @param qin 入口流量
     * @return 压强关于时间导数
     */
    public double getDp(double p, double v, double rho, double dv, double qin){
        double dp;
        dp = k*p*qin/rho/v - k*p*dv/v;
        return dp;
    }

    /**
     *
     * @param rho 气腔密度kg/m3
     * @param v 气腔体积m3
     * @param dv 气腔体积导数m3
     * @param qin 入口流量
     * @return 密度关于时间导数
     */
    public double getDrho(double rho, double v, double dv, double qin){
        double drho;
        drho = (qin-rho*dv)/v;
        return drho;
    }

    /**
     *
     * @param qme 推进剂出口流量
     * @return 体积关于时间导数
     */
    public double getDv(double qme){
        double dv;
        dv = qme / rho_l;
        return dv;
    }

    /**
     *
     * @param p 气腔压强
     * @param pe 出口外界压强
     * @return 出口推进剂流量
     */
    public double getQ(double p, double pe){
        double q;
        q = CA*Math.sqrt(2*rho_l*(p-pe));
        return q;
    }

    public double getQmi(double pin, double p, double rhoin){
        double qmi;
        double Cdin = ((((-1.6827 * p/pin + 4.6) * p/pin - 3.9) * p/pin + 0.8415) * p/pin - 0.1) * p/pin + 0.8414;
        double Ain = 0.005*0.005*Math.PI*0.25;//节流孔面积
        qmi = Cdin*Ain*Math.sqrt(2*k*pin*rhoin*(Math.pow(p/pin, 2/k)-Math.pow(p/pin, (k+1)/k))/(k-1));
        qmi = Double.valueOf(qmi).isNaN() ? 0.0 : qmi;
        return qmi;
    }

    public List<Double> getP() {
        return p;
    }

    public List<Double> getRho() {
        return rho;
    }

    public List<Double> getQ() {
        return q;
    }

    public List<Double> getV() {
        return v;
    }

    public List<Double> getQin() {
        return qin;
    }
}
