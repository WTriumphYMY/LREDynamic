package cn.edu.nwpu.domain.components;

import cn.edu.nwpu.domain.GasData.GasData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReducingValve
 * @Author: wkx
 * @Date: 2019/3/1 09:31
 * @Version: v1.0
 * @Description: 减压阀计算 输入进口流量压强温度，输出高压室流出流体压强，温度，流量；低压室流出流体压强，温度，流量；
 * 依据：《逆向卸荷式气体减压阀的动态特性仿真》
 */
public class ReducingValve {
    private GasData gasData;
    private double C;//阻尼系数5000
    private double stiffness;//弹簧刚度，N/m 390700
    private double F;//减压阀运动组建上合力
    private double M;//运动组件质量0.166kg
    private double Rg;//气体常数
    private double k;//气体比热比
    private double Amb;//膜片面积0.00090792
    private double Avc;//减压阀阀芯面积1.9635e-5
    private double V1;//高压腔体积6e-6
    private double V2;//低压腔体积6e-6
    private double xstop;//阀芯最大位移0.0002

    public List<Double> x = new ArrayList<>(); //阀芯位移
    public List<Double> u = new ArrayList<>(); //阀芯速度
    public List<Double> p_hign = new ArrayList<>();
    public List<Double> p_low = new ArrayList<>();
    public List<Double> temp_hign = new ArrayList<>();
    public List<Double> temp_low = new ArrayList<>();
    public List<Double> q_hign = new ArrayList<>();
    public List<Double> q_low = new ArrayList<>();

    public double[] dx = new double[4];
    public double[] du = new double[4];
    public double[] dp1 = new double[4];
    public double[] dp2 = new double[4];
    public double[] dtemp1 = new double[4];
    public double[] dtemp2 = new double[4];


    public ReducingValve(GasData gasData) {
        this.gasData = gasData;
        Rg = gasData.getR();
        k = gasData.getKappa();
    }

    public GasData getGasData() {
        return gasData;
    }

    public void setGasData(GasData gasData) {
        this.gasData = gasData;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }

    public double getAmb() {
        return Amb;
    }

    public void setAmb(double amb) {
        Amb = amb;
    }

    public double getAvc() {
        return Avc;
    }

    public void setAvc(double avc) {
        Avc = avc;
    }

    public double getV1() {
        return V1;
    }

    public void setV1(double v1) {
        V1 = v1;
    }

    public double getV2() {
        return V2;
    }

    public void setV2(double v2) {
        V2 = v2;
    }

    public double getXstop() {
        return xstop;
    }

    public void setXstop(double xstop) {
        this.xstop = xstop;
    }

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public List<Double> getU() {
        return u;
    }

    public void setU(List<Double> u) {
        this.u = u;
    }

    public List<Double> getP_hign() {
        return p_hign;
    }

    public void setP_hign(List<Double> p_hign) {
        this.p_hign = p_hign;
    }

    public List<Double> getP_low() {
        return p_low;
    }

    public void setP_low(List<Double> p_low) {
        this.p_low = p_low;
    }

    public List<Double> getTemp_hign() {
        return temp_hign;
    }

    public void setTemp_hign(List<Double> temp_hign) {
        this.temp_hign = temp_hign;
    }

    public List<Double> getTemp_low() {
        return temp_low;
    }

    public void setTemp_low(List<Double> temp_low) {
        this.temp_low = temp_low;
    }

    public List<Double> getQ_hign() {
        return q_hign;
    }

    public void setQ_hign(List<Double> q_hign) {
        this.q_hign = q_hign;
    }

    public List<Double> getQ_low() {
        return q_low;
    }

    public void setQ_low(List<Double> q_low) {
        this.q_low = q_low;
    }

    public double[] getDx() {
        return dx;
    }

    public void setDx(double[] dx) {
        this.dx = dx;
    }

    public double[] getDu() {
        return du;
    }

    public void setDu(double[] du) {
        this.du = du;
    }

    public double[] getDp1() {
        return dp1;
    }

    public void setDp1(double[] dp1) {
        this.dp1 = dp1;
    }

    public double[] getDp2() {
        return dp2;
    }

    public void setDp2(double[] dp2) {
        this.dp2 = dp2;
    }

    public double[] getDtemp1() {
        return dtemp1;
    }

    public void setDtemp1(double[] dtemp1) {
        this.dtemp1 = dtemp1;
    }

    public double[] getDtemp2() {
        return dtemp2;
    }

    public void setDtemp2(double[] dtemp2) {
        this.dtemp2 = dtemp2;
    }

    public double getF() {
        return F;
    }

    public void setF(double f) {
        F = f;
    }

    public double getM() {
        return M;
    }

    public void setM(double m) {
        M = m;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getRg() {
        return Rg;
    }

    public void setRg(double rg) {
        Rg = rg;
    }
}
