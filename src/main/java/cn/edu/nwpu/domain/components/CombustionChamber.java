package cn.edu.nwpu.domain.components;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CombustionChamber
 * @Author: wkx
 * @Date: 2019/3/8 15:55
 * @Version: v1.0
 * @Description: 燃烧室模型
 * 依据：《002-2014200285-白晶晶》
 */
public class CombustionChamber {

    private double vc; //燃烧室容腔体积7.8389e-6m3
    private double tauc; //燃烧时滞0.01s
    private double k; //燃气比热比1.25
    private double gamma;//燃气热力系数
    private double area_throat; //喷喉面积d=0.007m
    private double pa; //环境压强101325.0
    private double eps;//喷管膨胀比25.72

    public List<Double> p = new ArrayList<>();//压强，Pa
    public List<Double> r = new ArrayList<>();//混合比
    public List<Double> q = new ArrayList<>();//质量流率，kg/s
    public List<Double> f = new ArrayList<>();//推力
    //四届龙格库塔数组
    public double[] dp = new double[4];
    public double[] dr = new double[4];
    public double[] dRT = new double[4];


    public double getVc() {
        return vc;
    }

    public void setVc(double vc) {
        this.vc = vc;
    }

    public double getTauc() {
        return tauc;
    }

    public void setTauc(double tauc) {
        this.tauc = tauc;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getArea_throat() {
        return area_throat;
    }

    public void setArea_throat(double area_throat) {
        this.area_throat = area_throat;
    }

    public double getPa() {
        return pa;
    }

    public void setPa(double pa) {
        this.pa = pa;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }
}
