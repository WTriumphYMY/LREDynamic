package cn.edu.nwpu.domain.components;

import cn.edu.nwpu.domain.LiquidData.LiquidData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName Solenoidvalve
 * @Author: wkx
 * @Date: 2019/3/6 15:34
 * @Version: v1.0
 * @Description: 电磁阀组件
 * 依据：《轨∕姿控发动机用电磁阀动态特性仿真》-电路方程与磁路方程
 * 《液体火箭发动机非线性静特性与响应特性》-电动气阀响应特性分析
 */
public class Solenoidvalve {
    private LiquidData liquidData; //液体数据
    private SimpleLiquidOrifice simpleLiquidOrifice; //简单液体节流孔
    private double R; //线圈电阻23.3欧
    private double lambda; //气隙磁导率 1.25664e-6 H/m
    private double n; //线圈匝数1700
    private double hc; //磁场强度，由Bc得到
    private double lc; //磁路长度 0.08m
    private double u; //电压28V
    private double sigma; //漏磁系数 1.3(百度得到)
    private double mu0 = 4*Math.PI*1e-7; //真空磁导率 4π×10-7 H/m
    private double sm; //磁性材料截面积 0.0002m2
    private double K; //弹簧钢度 2800 N/m
    private double m; //阀芯质量 0.021kg
    private double Ff0; //弹簧预紧力 8.68N
    private double d; //阀芯直径 0.015m
    private double area;//阀芯面积
    private double xstop; //阀芯最大位移

    private List<Double> phi = new ArrayList<>();
    private List<Double> psi = new ArrayList<>();
    private List<Double> v = new ArrayList<>();
    private List<Double> x = new ArrayList<>();
    private List<Double> q = new ArrayList<>();


    public Solenoidvalve(LiquidData liquidData, SimpleLiquidOrifice simpleLiquidOrifice) {
        this.liquidData = liquidData;
        this.simpleLiquidOrifice = simpleLiquidOrifice;
    }

    public SimpleLiquidOrifice getSimpleLiquidOrifice() {
        return simpleLiquidOrifice;
    }

    public void setSimpleLiquidOrifice(SimpleLiquidOrifice simpleLiquidOrifice) {
        this.simpleLiquidOrifice = simpleLiquidOrifice;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getHc() {
        return hc;
    }

    public void setHc(double hc) {
        this.hc = hc;
    }

    public double getLc() {
        return lc;
    }

    public void setLc(double lc) {
        this.lc = lc;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public double getMu0() {
        return mu0;
    }

    public void setMu0(double mu0) {
        this.mu0 = mu0;
    }

    public double getSm() {
        return sm;
    }

    public void setSm(double sm) {
        this.sm = sm;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getFf0() {
        return Ff0;
    }

    public void setFf0(double ff0) {
        Ff0 = ff0;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getXstop() {
        return xstop;
    }

    public void setXstop(double xstop) {
        this.xstop = xstop;
    }
}
