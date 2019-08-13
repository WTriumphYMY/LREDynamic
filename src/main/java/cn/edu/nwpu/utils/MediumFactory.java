package cn.edu.nwpu.utils;

import cn.edu.nwpu.domain.GasData.GasData;
import cn.edu.nwpu.domain.GasData.HeGasData;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
import cn.edu.nwpu.domain.LiquidData.LiquidData;
import cn.edu.nwpu.domain.LiquidData.MMHData;

/**
 * @ClassName MediumFactory
 * @Author: wkx
 * @Date: 2019/7/20 20:14
 * @Version: v1.0
 * @Description: 气体与液体介质工具类
 */
public class MediumFactory {
    private static HeGasData heGasData = new HeGasData();
    private static N2GasData n2GasData = new N2GasData();
    private static LO2Data lo2Data = new LO2Data();
    private static MMHData mmhData = new MMHData();

    public static GasData getGasData(String gasStr){
        switch (gasStr){
            case "N2": return n2GasData;
            case "He": return heGasData;
            default: return null;
        }
    }

    public static LiquidData getLiquidData(String liquidStr){
        switch (liquidStr){
            case "LO2": return lo2Data;
            case "MMH": return mmhData;
            default: return null;
        }
    }
}
