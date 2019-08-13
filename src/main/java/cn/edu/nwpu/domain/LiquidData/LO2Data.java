package cn.edu.nwpu.domain.LiquidData;

/**
 * @ClassName LO2Data
 * @Author: Trium
 * @Date: 2019/2/27 16:55
 * @Version: v1.0
 * @Description: 液氧物性参数
 */
public class LO2Data extends LiquidData {
    public LO2Data() {
        rho = 1135;//密度，-182.7℃
        mu = 0.0020889;//动力粘度
        nu = 1.84e-6;//运动粘度
        beta = 9.296e8;//体积模量
        Cp = 4570;//定压比热容
        Cv = 0;//定容比热容
        R = 0;//气体常数，未提供
        kappa = 0;//比热比，未提供
        p_vapour = 101325;//饱和蒸汽压，未提供
    }
}
