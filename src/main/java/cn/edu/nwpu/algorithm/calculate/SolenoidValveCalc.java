package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.components.SimpleLiquidOrifice;
import cn.edu.nwpu.domain.components.Solenoidvalve;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SolenoidValveCalc
 * @Author: wkx
 * @Date: 2019/4/1 09:14
 * @Version: v1.0
 * @Description:
 * 依据：《轨∕姿控发动机用电磁阀动态特性仿真》-电路方程与磁路方程
 *  * 《液体火箭发动机非线性静特性与响应特性》-电动气阀响应特性分析
 */
public class SolenoidValveCalc {
    private Solenoidvalve solenoidvalve;
    private SimpleLiquidOrifice simpleLiquidOrifice; //简单液体节流孔
    private double R; //线圈电阻23.3欧
    private double lambda; //气隙磁导率 1.25664e-6 H/m
    private double n; //线圈匝数1700
    private double hc; //磁场强度，由Bc得到
    private double lc; //磁路长度 0.08m
    private double u; //电压28V
    private double sigma; //漏磁系数 1.3(百度得到)
    private double mu0; //真空磁导率 4π×10-7 H/m
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
    private List<Double> p = new ArrayList<>(); //出口压强
    private double[] dphi = new double[4];
    private double[] dpsi = new double[4];
    private double[] dv = new double[4];
    private double[] dx = new double[4];
    private double h;

    public SolenoidValveCalc(Solenoidvalve solenoidvalve) {
        this.solenoidvalve = solenoidvalve;
        this.simpleLiquidOrifice = solenoidvalve.getSimpleLiquidOrifice();
        this.R = solenoidvalve.getR();
        this.lambda = solenoidvalve.getLambda();
        this.n = solenoidvalve.getN();
        this.hc = solenoidvalve.getHc();
        this.lc = solenoidvalve.getLc();
        this.u = solenoidvalve.getU();
        this.sigma = solenoidvalve.getSigma();
        this.mu0 = 4*Math.PI*1e-7;
        this.sm = solenoidvalve.getSm();
        this.K = solenoidvalve.getK();
        this.m = solenoidvalve.getM();
        this.Ff0 = solenoidvalve.getFf0();
        this.d = solenoidvalve.getD();
        this.area = solenoidvalve.getArea();
        this.xstop = solenoidvalve.getXstop();

        phi.add(0.0);
        psi.add(0.0);
        v.add(0.0);
        x.add(0.0);
        q.add(0.0);
        p.add(101325.0);
        this.h = GlobleParas.TIME_STEP;
    }
    
    public void execute(double time, double pin, double qin) {
        int index = q.size()-1;
        boolean on = false;

        if (time >= 0.1) {
            on = true;
        }
        dphi[0] = getDphi(phi.get(index), getRtotal(x.get(index), on));
        dpsi[0] = getDpsi(dphi[0]);
        dv[0] = getDv(psi.get(index), x.get(index), pin);
        dx[0] = getDx(v.get(index), x.get(index));
        for (int i = 1; i < 4; i++) {
            if (i < 3) {
                dphi[i] = getDphi(phi.get(index) + 0.5 * h * dphi[i - 1], getRtotal(x.get(index) + 0.5 * h * dx[i - 1], on));
                dpsi[i] = getDpsi(dphi[i - 1]);
                dv[i] = getDv(psi.get(index) + 0.5 * h * dpsi[i - 1], x.get(index) + 0.5 * h * dx[i - 1], pin);
                dx[i] = getDx(v.get(index) + 0.5 * h * dv[i - 1], x.get(index) + 0.5 * h * dx[i - 1]);
            } else {
                dphi[i] = getDphi(phi.get(index) + h * dphi[i - 1], getRtotal(x.get(index) + h * dx[i - 1], on));
                dpsi[i] = getDpsi(dphi[i - 1]);
                dv[i] = getDv(psi.get(index) + h * dpsi[i - 1], x.get(index) + h * dx[i - 1], pin);
                dx[i] = getDx(v.get(index) + h * dv[i - 1], x.get(index) + h * dx[i - 1]);
            }
        }
        phi.add(phi.get(index) + h * (dphi[0] + 2 * dphi[1] + 2 * dphi[2] + dphi[3]) / 6);
        psi.add(psi.get(index) + h * (dpsi[0] + 2 * dpsi[1] + 2 * dpsi[2] + dpsi[3]) / 6);
        x.add(x.get(index) + h * (dx[0] + 2 * dx[1] + 2 * dx[2] + dx[3]) / 6);
        v.add(getDx(v.get(index) + h * (dv[0] + 2 * dv[1] + 2 * dv[2] + dv[3]) / 6, x.get(index + 1)));
        q.add(getQ(qin, pin, x.get(x.size()-1)));
        p.add(getP(pin));
    }

    /**
     *
     * @param phi
     * @param rtotal 磁阻
     * @return
     */
    public double getDphi(double phi, double rtotal) {
        double dphi;
        dphi = u / n - phi * rtotal * R / n / n;
        return dphi;
    }

    /**
     *
     * @param dphi
     * @return dpsi
     */
    public double getDpsi(double dphi) {
        return n * dphi;
    }

    /**
     * @param x    阀芯位移
     * @param flag 电磁阀是否通电
     * @return 磁阻
     */
    public double getRtotal(double x, boolean flag) {
        double rtotal;
        double rm = flag ? 4.25103e6 : 2.06293e6; //磁性材料磁阻
        double rair = flag ? 0 : 1.90498e6; //工作气隙磁阻
        double rl = 1.5e6; //非工作气隙磁阻
        rtotal = rm + rair + rl;
        return rtotal;
    }

    public double getDv(double psi, double x, double pin) {
        double dv;
        dv = psi * psi / 2 / n / n / sigma / sigma / mu0 / sm / m - K * x / m - (Ff0 + pin * area) / m;
        if (x >= xstop || dv < 0){
            dv = 0;
        }
        return dv;
    }

    public double getDx(double v, double x) {
        double dx;
        dx = v;
        if (x >= xstop || x < 0){
            dx = 0;
        }
        return dx;
    }

    /**
     * @param pin 入口压强Pa
     * @return 出口压强
     */
    public double getP(double pin) {
        return simpleLiquidOrifice.getP(pin);
    }

    /**
     * @param qin 入口流量kg/s
     * @param pin 入口压强Pa
     * @param x  阀芯运动距离
     * @return 出口流量kg/s
     */
    public double getQ(double qin, double pin, double x) {
        double q = (x/xstop)*simpleLiquidOrifice.getQ(qin, pin);
        return q;
    }

    public List<Double> getPhi() {
        return phi;
    }

    public List<Double> getPsi() {
        return psi;
    }

    public List<Double> getV() {
        return v;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getQ() {
        return q;
    }

    public List<Double> getP() {
        return p;
    }
}
