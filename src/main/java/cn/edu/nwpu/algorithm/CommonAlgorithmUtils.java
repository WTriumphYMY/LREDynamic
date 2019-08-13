package cn.edu.nwpu.algorithm;

import static java.lang.Math.*;

/**
 * @ClassName CommonAlgorithmUtils
 * @Author: wkx
 * @Date: 2019/3/5 10:44
 * @Version: v1.0
 * @Description: 基础算法工具类
 */
public class CommonAlgorithmUtils {


    //-----------------------------------------------------------------------------
    //   	  计算理论推力系数,考虑气流分离的冻结流理论推力系数计算
    //-----------------------------------------------------------------------------
    /**
     *
     * @param k 燃气比热比
     * @param AeAt 喷管面积比
     * @param PaPc 背压与燃烧室压强之比
     * @return
     */
    public static double CoeForce(double k,double  AeAt,double  PaPc)
    {
        double PePc=PeOverPc(k,AeAt);

        double PePa=PePc/PaPc;
        double PiPa=0.6666666667*pow(PaPc,0.2);
        double CF0,t;
        if(PePa>=PiPa){
            CF0 = sqrt(k)*pow(2.0/(k+1.0),(k+1.0)/(2.0*k-2.0))*
                    sqrt(2.0*k/(k-1)*(1.0-pow(PePc,(k-1)/k)));
            t=CF0+AeAt*(PePc-PaPc);
            return t;
        }
        //出现气流分离
        double PiPc=PiPa*PaPc;

        double AiAt=pow(2.0/(k+1.0),1.0/(k-1.0))*pow((k-1.0)/(k+1.0),0.5)
                /(pow(PiPc,1/k)*sqrt(1.0-pow(PiPc,(k-1)/k)));

        double a;
        if(AiAt<=(AeAt/1.604+0.377))
            a=(AiAt-1)/2.4;
        else
            a=(AeAt-AiAt)/1.45;

        double A095At=AiAt+a;

        double dCFs=0.55*(PiPc+0.95*PaPc)*a+0.975*PaPc*(AeAt-A095At);
        CF0 = sqrt(k)*pow(2.0/(k+1.0),(k+1.0)/(2.0*k-2.0))*
                sqrt(2.0*k/(k-1)*(1.0-pow(PiPc,(k-1)/k)))+AiAt*(PiPc-PaPc);
        t=CF0-dCFs;
        return t;
    }

    /**
     * 由面积比计算压强比
     * @param k
     * @param AeAt
     * @return
     */
    public static double PeOverPc(double  k,double  AeAt)
    {
        double  gma=sqrt(k)*pow(2.0/(k+1.0),(k+1.0)/(2.0*k-2.0));
        double  pepc0=0.008;
        double  pepc;
        double  eps;
        do{
            double parameter=gma/AeAt/sqrt(2.0*k/(k-1)*(1.0-pow((double)pepc0,(double)((k-1)/k))));
            pepc = pow(parameter,(double)k);
            eps=abs(pepc-pepc0)/pepc;
            pepc0=pepc;
        }while(eps>1.0E-4);
        return pepc;
    }

}


