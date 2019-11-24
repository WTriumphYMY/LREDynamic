package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.ReducingValve;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.utils.CalculateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReducingValveCalc
 * @Author: wkx
 * @Date: 2019/3/29 11:27
 * @Version: v1.0
 * @Description: 减压阀计算类
 */
public class ReducingValveCalc {
    private ReducingValve reducingValve;
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
    private double xs;//预压缩量0.009
    private double h;

    private List<Double> x = new ArrayList<>(); //阀芯位移
    private List<Double> u = new ArrayList<>(); //阀芯速度
    private List<Double> p_high = new ArrayList<>();
    private List<Double> p_low = new ArrayList<>();
    private List<Double> temp_high = new ArrayList<>();
    private List<Double> temp_low = new ArrayList<>();
    private List<Double> q_high = new ArrayList<>();
    private List<Double> q_low = new ArrayList<>();

    private double[] dx = new double[4];
    private double[] du = new double[4];
    private double[] dp1 = new double[4];
    private double[] dp2 = new double[4];
    private double[] dtemp1 = new double[4];
    private double[] dtemp2 = new double[4];

    public ReducingValveCalc(ReducingValve reducingValve) {
        this.reducingValve = reducingValve;
        C = reducingValve.getC();
        stiffness = reducingValve.getStiffness();
        F = reducingValve.getF();
        M = reducingValve.getM();
        Rg = reducingValve.getRg();
        k = reducingValve.getK();
        Amb = reducingValve.getAmb();
        Avc = reducingValve.getAvc();
        V1 = reducingValve.getV1();
        V2 = reducingValve.getV2();
        xstop = reducingValve.getXstop();
        xs = reducingValve.getXs();

        x.add(0.0);
        u.add(0.0);
        p_high.add(101325.0);
        p_low.add(101325.0);
        temp_high.add(300.0);
        temp_low.add(300.0);
        q_high.add(0.0);
        q_low.add(0.0);
        h = GlobleParas.TIME_STEP;
    }

    public void execute(double pin, double tin, double pe){
        int index = p_high.size()-1;
//        //减压阀组件
        dx[0] = getDx(u.get(index), x.get(index));
        du[0] = getDu(p_high.get(index), p_low.get(index), x.get(index), u.get(index));
//        dp1[0] = getDp1(temp_high.get(index), q_high.get(index), p_high.get(index), dx[0], pin, tin);
//        dp2[0] = getDp2(q_high.get(index), temp_high.get(index), q_low.get(index), temp_low.get(index), p_low.get(index), dx[0]);
//        dtemp1[0] = getDt1(temp_high.get(index), q_high.get(index), p_high.get(index), dp1[0], pin, dx[0], tin);
//        dtemp2[0] = getDt2(q_high.get(index), q_low.get(index), temp_low.get(index), p_low.get(index), dp2[0], dx[0]);
        dp1[0] = getNewDp1(p_high.get(index), pin, tin, q_high.get(index));
        dp2[0] = getNewDp2(q_high.get(index), q_low.get(index), tin);
        for (int i = 1; i < 4; i++){
            double ratio = (i<3) ? 0.5 : 1.0;
           //减压阀
            dx[i] = getDx(u.get(index)+ratio*h*du[i-1], x.get(index)+ratio*h*dx[i-1]);
            du[i] = getDu(p_high.get(index)+ratio*h*dp1[i-1], p_low.get(index)+ratio*h*dp2[i-1],
                    x.get(index)+ratio*h*dx[i-1],u.get(index)+ratio*h*du[i-1]);
//            dp1[i] = getDp1(temp_high.get(index)+ratio*h*dtemp1[i-1],
//                    getQ(p_high.get(index)+ratio*h*dp1[i-1], p_low.get(index)+ratio*h*dp2[i-1],
//                            temp_high.get(index)+ratio*h*dtemp1[i-1]),
//                    p_high.get(index)+ratio*h*dp1[i-1], dx[i-1],pin, tin);
//            dp2[i] = getDp2(getQ(p_high.get(index)+ratio*h*dp1[i-1], p_low.get(index)+ratio*h*dp2[i-1],
//                    temp_high.get(index)+ratio*h*dtemp1[i-1]), temp_high.get(index)+ratio*h*dtemp1[i-1],
//                    getQ(p_low.get(index)+ratio*h*dp1[i-1], pe, temp_low.get(index)+ratio*h*dtemp2[i-1]),
//                    temp_low.get(index)+ratio*h*dtemp2[i-1], p_low.get(index)+ratio*h*dp2[i-1], dx[i-1]);
//            dtemp1[i] = getDt1(temp_high.get(index)+ratio*h*dtemp1[i-1],
//                    getQ(p_high.get(index)+ratio*h*dp1[i-1], p_low.get(index)+ratio*h*dp2[i-1],
//                            temp_high.get(index)+ratio*h*dtemp1[i-1]),
//                    p_high.get(index)+ratio*h*dp1[i-1], dp1[i-1], pin, dx[i-1], tin);
//            dtemp2[i] = getDt2(getQ(p_high.get(index)+ratio*h*dp1[i-1], p_low.get(index)+ratio*h*dp2[i-1],
//                    temp_high.get(index)+ratio*h*dtemp1[i-1]),
//                    getQ(p_low.get(index)+ratio*h*dp2[i-1], pe, temp_low.get(index)+ratio*h*dtemp2[i-1]),
//                    temp_low.get(index)+ratio*h*dtemp2[i-1], p_low.get(index)+ratio*h*dp2[i-1], dp2[i-1], dx[i-1]);
            dp1[i] = getNewDp1(p_high.get(index), pin, tin, getQ(p_high.get(index)+ratio*h*dp1[i-1], pe, tin));
            dp2[i] = getNewDp2(getQ(p_low.get(index)+ratio*h*dp1[i-1], pe, tin), getQ(p_low.get(index)+ratio*h*dp2[i-1], pe, tin), tin);
        }
      //减压阀
        x.add(x.get(index) + h*(dx[0] + 2*dx[1] + 2*dx[2] + dx[3])/6);
        u.add(u.get(index) + h*(du[0] + 2*du[1] + 2*du[2] + du[3])/6);
        p_high.add(p_high.get(index) + h*(dp1[0] + 2*dp1[1] + 2*dp1[2] + dp1[3])/6);
        p_low.add(p_low.get(index) + h*(dp2[0] + 2*dp2[1] + 2*dp2[2] + dp2[3])/6);
//        temp_high.add(temp_high.get(index) + h*(dtemp1[0] + 2*dtemp1[1] + 2*dtemp1[2] + dtemp1[3])/6);
//        temp_low.add(temp_low.get(index) + h*(dtemp2[0] + 2*dtemp2[1] + 2*dtemp2[2] + dtemp2[3])/6);
        temp_high.add(tin);
        temp_low.add(tin);
        q_high.add(getQ(p_high.get(index+1), p_low.get(index+1), temp_high.get(index+1)));
        q_low.add(getQ(p_low.get(index+1), pe, temp_low.get(index+1)));

    }

    /**
     *
     * @param u 阀芯运动速度
     * @return 阀芯位移导数
     */
    public double getDx(double u, double x){
        double dx;
        dx = u;
//        if (x >= xstop || x < 0){
//            dx = 0;
//        }
        return dx;
    }

    /**
     *
     * @param x 阀芯位移
     * @param dx 阀芯位移导数
     * @return 阀芯速度导数
     */
    public double getDu(double p1, double p2, double x, double dx){
        //TODO 阀芯运动方程还需研究
        double du;
        double kstop = 1e10;
        double cstop = 1e8;
        double deltaX = 0;
        if (x < 0){
            deltaX=x;
        }else if (x > xstop){
            deltaX=x-xstop;
        }else {
            x=0;
        }
        F = kstop*deltaX + cstop*dx;
        du = p1*Avc/M + p2*(0*Amb-Avc)/M - 0*202650.0*Amb/M - C*dx/M - stiffness*xs/M - stiffness*x/M - 0*F/M;
        return du;
    }

    /**
     *
     * @param t1 高压腔流体温度
     * @param q1 高压腔流出量
     * @param pin 入口压强
     * @param tin 流入高压腔流体温度
     * @return 高压腔压强导数
     */
    public double getDp1(double t1, double q1, double p1, double dx, double pin, double tin){
        double dp1;
        double qin = getQ(pin, p1, tin);
        dp1 = k*Rg*(qin*tin-q1*t1)/V1 - 0*k*p1*Avc*dx/V1;
        return dp1;
    }

    public double getNewDp1(double p1, double pin, double tin, double q1){
        double dp1;
        double qin = getQ(pin, p1, tin);
        dp1 = k*Rg*(qin*tin-q1*tin)/V1;
        return dp1;
    }

    /**
     *
     * @param t1 高压腔流体温度
     * @param p1 高压腔压强
     * @param dp1 高压腔压强导数
     * @param pin 入口压强
     * @param q1 高压腔流体流出流量
     * @return 高压腔温度导数
     */
    public double getDt1(double t1, double q1, double p1, double dp1, double pin, double dx, double tin){
        double dt1;
        double qin = getQ(pin, p1, tin);
        dt1 = t1*dp1/p1 + 0*t1*Avc*dx/V1 - Rg*t1*t1*(qin-q1)/p1/V1;
        return dt1;
    }

    /**
     *
     * @param q1 高压腔流体流出流量
     * @param t1 高压腔流体温度
     * @param q2 低压腔流体流出流量
     * @param t2 低压腔流体温度
     * @param p2 低压腔压强
     * @param dx 阀芯位移导数
     * @return 低压腔压强导数
     */
    public double getDp2(double q1, double t1, double q2, double t2, double p2, double dx){
        double dp2;
        dp2 = k*Rg*(q1*t1-q2*t2)/V2 - 0*k*p2*(Amb-Avc)*dx/V2;
        return dp2;
    }

    public double getNewDp2(double q1, double q2, double tin){
        double dp2;
        dp2 = k*Rg*(q1*tin-q2*tin)/V2;
        return dp2;
    }

    /**
     *
     * @param q1 高压腔流体流出流量
     * @param q2 低压腔流体流出流量
     * @param t2 低压腔流体温度
     * @param p2 低压腔压强
     * @param dp2 低压腔压强导数
     * @param dx 阀芯位移导数
     * @return
     */
    public double getDt2(double q1, double q2, double t2, double p2, double dp2, double dx){
        double dt2;
        dt2 = t2*dp2/p2 + 0*t2*(Amb-Avc)*dx/V2 - Rg*t2*t2*(q1-q2)/p2/V2;
        return dt2;
    }

    /**
     * 节流孔流量方程
     * @param p 孔前压强
     * @param pe 孔后压强
     * @param t 流体温度
     * @return 返回流量
     */
    public double getQ(double p, double pe, double t){
        return CalculateUtils.getQ(p,pe,t,k,Rg);
    }

    public List<Double> getP_high() {
        return p_high;
    }

    public List<Double> getP_low() {
        return p_low;
    }

    public List<Double> getTemp_high() {
        return temp_high;
    }

    public List<Double> getTemp_low() {
        return temp_low;
    }

    public List<Double> getQ_high() {
        return q_high;
    }

    public List<Double> getQ_low() {
        return q_low;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getU() {
        return u;
    }
}
