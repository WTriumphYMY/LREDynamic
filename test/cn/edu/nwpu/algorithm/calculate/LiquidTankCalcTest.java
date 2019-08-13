package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.LiquidTank;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LiquidTankCalcTest {

    @Test
    public void execute() {
        LiquidTank liquidTank = new LiquidTank(new LO2Data(), new N2GasData());
        liquidTank.setCA(2.663*1e-5);
        liquidTank.setV0(0.00011);
        liquidTank.setP0(101325.0);
        liquidTank.setQ0(0.0);
        liquidTank.setRho0(new N2GasData().getRho());

        LiquidTankCalc liquidTankCalc = new LiquidTankCalc(liquidTank);
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 1.0){
            liquidTankCalc.execute(1e7, new N2GasData().getRho(),101325.0);
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", liquidTankCalc.getP());
        resultMap.put("q", liquidTankCalc.getQ());
        resultMap.put("rho", liquidTankCalc.getRho());
        resultMap.put("v", liquidTankCalc.getV());
        ResultShowUtil.showChart(resultMap);
    }
}