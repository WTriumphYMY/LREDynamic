package cn.edu.nwpu.domain.LiquidData;

/**
 * @ClassName LiquidData
 * @Author: Trium
 * @Date: 2019/2/27 16:47
 * @Version: v1.0
 * @Description:
 */
abstract public class LiquidData {
    double rho;//"密度，-182.7℃",
    double mu;//"动力粘度",
    double nu;//"运动粘度",
    double beta;//"体积模量",
    double Cp;//"定压比热容",
    double Cv;//"定容比热容",
    double R;//"气体常数，未提供",
    double kappa;//"比热比，未提供",
    double p_vapour;//"饱和蒸汽压，未提供",

    public double getRho() {
        return rho;
    }

    public double getMu() {
        return mu;
    }

    public double getNu() {
        return nu;
    }

    public double getBeta() {
        return beta;
    }

    public double getCp() {
        return Cp;
    }

    public double getCv() {
        return Cv;
    }

    public double getR() {
        return R;
    }

    public double getKappa() {
        return kappa;
    }

    public double getP_vapour() {
        return p_vapour;
    }
}
