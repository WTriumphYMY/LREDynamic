package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.SimpleLiquidOrifice;
import cn.edu.nwpu.domain.components.Solenoidvalve;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolenoidValveCalcTest {

    @Test
    public void execute() {
        SimpleLiquidOrifice simpleLiquidOrifice = new SimpleLiquidOrifice(new LO2Data());
        simpleLiquidOrifice.setD(0.005);
        simpleLiquidOrifice.setCq(0.997);
        Solenoidvalve solenoidvalve = new Solenoidvalve(new LO2Data(), simpleLiquidOrifice);
        solenoidvalve.setR(23.3);
        solenoidvalve.setN(1700);
        solenoidvalve.setU(28.0);
        solenoidvalve.setSigma(1.3);
        solenoidvalve.setSm(0.0002);
        solenoidvalve.setK(2800);
        solenoidvalve.setM(0.021);
        solenoidvalve.setFf0(8.68);
        solenoidvalve.setD(0.0035);
        solenoidvalve.setXstop(0.0004);

        SolenoidValveCalc solenoidValveCalc = new SolenoidValveCalc(solenoidvalve);

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 0.5){
            solenoidValveCalc.execute(time,  2.2e6, 2);
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("phi", solenoidValveCalc.getPhi());
        resultMap.put("psi", solenoidValveCalc.getPsi());
        resultMap.put("q", solenoidValveCalc.getQ());
        resultMap.put("v", solenoidValveCalc.getV());
        resultMap.put("x", solenoidValveCalc.getX());
        resultMap.put("p", solenoidValveCalc.getP());
        ResultShowUtil.showChart(resultMap);
    }
}