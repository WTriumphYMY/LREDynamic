package cn.edu.nwpu.domain.components;

import cn.edu.nwpu.algorithm.CommonAlgorithmUtils;
import cn.edu.nwpu.domain.LiquidData.LiquidData;

/**
 * @ClassName SimpleLiquidOrifice
 * @Author: wkx
 * @Date: 2019/3/5 14:48
 * @Version: v1.0
 * @Description: 简单液体节流孔（无汽蚀状态）
 */
public class SimpleLiquidOrifice {
    private LiquidData liquidData;
    private double pressureCoefficience; //压降系数（一般0.5-0.7，香港提供，这里根据casc选择0.997）
    private double d; //节流孔直径
    private double cq; //流量系数
    private double rho;//流体密度

    public SimpleLiquidOrifice(LiquidData liquidData) {
        this.liquidData = liquidData;
        this.cq = 0.707;//流量系数
        this.rho = liquidData.getRho();
    }


    /**
     *
     * @param pin 入口压强Pa
     * @return 出口压强
     */
    public double getP(double pin){
        return  pressureCoefficience*pin;
    }

    /**
     *
     * @param qin 入口流量kg/s
     * @param pin 入口压强Pa
     * @return 出口流量kg/s
     */
    public double getQ(double qin, double pin){
        double area = 0.25*d*d*Math.PI;
        double q = area*cq*Math.sqrt(2*(1-pressureCoefficience)*pin*rho);
        return q;
    }

    public double getPressureCoefficience() {
        return pressureCoefficience;
    }

    public void setPressureCoefficience(double pressureCoefficience) {
        this.pressureCoefficience = pressureCoefficience;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getCq() {
        return cq;
    }

    public void setCq(double cq) {
        this.cq = cq;
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
    }
}
