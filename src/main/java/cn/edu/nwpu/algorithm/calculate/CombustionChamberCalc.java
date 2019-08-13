package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.algorithm.CommonAlgorithmUtils;
import cn.edu.nwpu.algorithm.SplineInterpolation;
import cn.edu.nwpu.domain.components.CombustionChamber;
import cn.edu.nwpu.algorithm.GlobleParas;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CombustionChamberCalc
 * @Author: wkx
 * @Date: 2019/4/1 09:44
 * @Version: v1.0
 * @Description: 燃烧室模型
 *  * 依据：《002-2014200285-白晶晶》《液体火箭发动机非线性特性与响应特性》-干扰因素对液体火箭发动机性能影响的过渡特性研究
 */
public class CombustionChamberCalc {
    private CombustionChamber combustionChamber;
    private double vc; //燃烧室容腔体积7.8389e-6m3
    private double tauc; //燃烧时滞0.01s
    private double k; //燃气比热比1.25
    private double gamma;//燃气热力系数
    private double area_throat; //喷喉面积d=0.007m
    private double pa; //环境压强101325.0
    private double eps;//喷管膨胀比25.72

    private List<Double> p = new ArrayList<>();//压强，Pa
    private List<Double> r = new ArrayList<>();//混合比
    private List<Double> q = new ArrayList<>();//质量流率，kg/s
    private List<Double> f = new ArrayList<>();//推力

    private double[] dp = new double[4];
    private double[] dr = new double[4];
    private double h;

    public CombustionChamberCalc(CombustionChamber combustionChamber) {
        this.combustionChamber = combustionChamber;
        this.vc = combustionChamber.getVc();
        this.tauc = combustionChamber.getTauc();
        this.k = combustionChamber.getK();
        this.gamma = combustionChamber.getGamma();
        this.area_throat = combustionChamber.getArea_throat();
        this.pa = combustionChamber.getPa();
        this.eps = combustionChamber.getEps();
        p.add(101325.0);
        r.add(1.0);
        q.add(0.0);
        f.add(0.0);
        this.h = GlobleParas.TIME_STEP;
    }

    public void execute(double qo, double qf){
        int index = p.size()-1;

        dr[0] = getDr(r.get(index), p.get(index), qo, qf,
                getRT(r.get(index)));
        dp[0] = getDp(qo, qf, p.get(index), r.get(index), getRT(r.get(index)),
                getDRT(r.get(index)));
        for (int i = 1; i < 4; i++){
            if (i < 3){
                dr[i] = getDr(r.get(index)+0.5*h*dr[i-1], p.get(index)+0.5*h*dp[i-1],
                        qo, qf, getRT(r.get(index)+0.5*h*dr[i-1]));
                dp[i] = getDp(qo, qf, p.get(index)+0.5*h*dp[i-1], r.get(index)+0.5*h*dr[i-1],
                        getRT(r.get(index)+0.5*h*dr[i-1]), getDRT(r.get(index)+0.5*h*dr[i-1]));
            }else {
                dr[i] = getDr(r.get(index)+h*dr[i-1], p.get(index)+h*dp[i-1],
                        qo, qf, getRT(r.get(index)+h*dr[i-1]));
                dp[i] = getDp(qo, qf, p.get(index)+h*dp[i-1],r.get(index)+h*dr[i-1],
                        getRT(r.get(index)+h*dr[i-1]), getDRT(r.get(index)+h*dr[i-1]));
            }
        }
        p.add(p.get(index) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
        r.add(r.get(index) + h*(dr[0] + 2*dr[1] + 2*dr[2] + dr[3])/6);
        f.add(getForce(p.get(index+1)));
        q.add(getQout(p.get(index+1), getRT(r.get(index+1))));
    }

    /**
     *
     * @param qo 入口氧化剂流量
     * @param qf 入口燃料流量
     * @param p 燃烧室压强
     * @param r 燃烧室混合比
     * @param RT 燃气热值
     * @param dRT 燃气热值关于混合比导数
     * @return 燃烧室压强导数
     */
    public double getDp(double qo, double qf, double p, double r, double RT, double dRT){
        double dp;//(t-tauc)
        dp = ((RT+dRT*(1+r))*qo + (RT-dRT*r*(1+r))*qf - RT*getQout(p, RT))/vc;
//        dp = (getRT(qo/qf)*(qo+qf) - gamma*area_throat*p*Math.sqrt(getRT(qo/qf)))/vc;
//        dp = (RT*(qo+qf) - RT*getQout(p, RT) + p*vc*dRT*dr/RT) / vc;
        return dp;
    }

    /**
     *
     * @param r 燃烧室混合比
     * @param p 燃烧室压强
     * @param qo 入口氧化剂流量
     * @param qf 入口燃料流量
     * @param RT 燃气热值
     * @return
     */
    public double getDr(double r, double p, double qo, double qf, double RT){
        double dr;
        dr = (1+r)*RT/p/vc*(qo - r*qf);
        if (dr<0 && r<0){
            dr = 0;
        }
        return dr;
    }

    public double getRT(double r){
        double RT;
//        RT = p1*Math.pow(r,4) + p2*Math.pow(r,3) + p3*Math.pow(r,2) + p4*r + p5;
        Double[] x = {0.1,0.2,0.3,0.4,0.5,0.7,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,7.0,10.0,15.0,20.0,30.0,40.0,50.0};
        Double[] y = {751803.742485807,810774.283143972,865959.449191469,954842.465448960,1110824.45243604,1328184.49584619,1452299.88106342,1383505.27127263,1256818.52609915,1150992.16700430,1065248.61504909,993945.511064594,932723.066566264,878867.067304603,830685.936220580,674908.396124423,516017.422317614,363527.145546757,277428.753712470,183132.244825323,131808.457865899,103297.217111947};
        SplineInterpolation splineInterpolation = new SplineInterpolation(x, y);
        RT = splineInterpolation.spline(r);
        return RT;
    }

    public double getDRT(double r){
        double dRT;
//        dRT = 4*p1*Math.pow(r,3) + 3*p2*Math.pow(r,2) + 2*p3*r + p4;
        Double[] x = {0.1,0.2,0.3,0.4,0.5,0.7,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,7.0,10.0,15.0,20.0,30.0,40.0,50.0};
        Double[] y = {751803.742485807,810774.283143972,865959.449191469,954842.465448960,1110824.45243604,1328184.49584619,1452299.88106342,1383505.27127263,1256818.52609915,1150992.16700430,1065248.61504909,993945.511064594,932723.066566264,878867.067304603,830685.936220580,674908.396124423,516017.422317614,363527.145546757,277428.753712470,183132.244825323,131808.457865899,103297.217111947};
        SplineInterpolation splineInterpolation = new SplineInterpolation(x, y);
        dRT = (splineInterpolation.spline(r+0.001)-splineInterpolation.spline(r))/0.001;
        return dRT;
    }

    public double getQout(double pc, double RT){
        double qout;
//        gamma = (pa/pc<=Math.pow(2/(k+1), k/(k-1))) ? Math.sqrt(k) * Math.pow(2/(k+1), (k+1)/2/(k-1)) : Math.sqrt(2*k/(k-1)*(Math.pow(pa/pc, 2/k)-Math.pow(pa/pc, (k+1)/k)));
        gamma = Math.sqrt(k*Math.pow(2/(1+k), (k+1)/(k-1)));
        qout = pc*area_throat*gamma/Math.sqrt(RT);
        return qout;
    }

    public double getForce(double pc){
        double f;//推力
        double cf;//推力系数
        cf = CommonAlgorithmUtils.CoeForce(k, eps, pa/pc);
        f = cf*pc*area_throat;
        return f;
    }

    public List<Double> getP() {
        return p;
    }

    public List<Double> getR() {
        return r;
    }

    public List<Double> getQ() {
        return q;
    }

    public List<Double> getF() {
        return f;
    }
}
