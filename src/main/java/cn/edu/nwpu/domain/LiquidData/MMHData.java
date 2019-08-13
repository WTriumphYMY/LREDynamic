package cn.edu.nwpu.domain.LiquidData;

/**
 * @ClassName MMHData
 * @Author: wkx
 * @Date: 2019/3/11 10:29
 * @Version: v1.0
 * @Description: 甲基肼
 */
public class MMHData extends LiquidData {
    public MMHData() {
        rho = 847.2;//密度
        mu = 0.00056;//动力粘度
        nu = 7.125e-6;//运动粘度
        beta = 1.8397e9;//体积模量
        Cp = 2928;//定压比热容
        Cv = 0;//定容比热容
        R = 180.46;//气体常数
        kappa = 0;//比热比
        p_vapour = 0;//饱和蒸汽压
    }
}
