package cn.edu.nwpu.algorithm.calculate;


import cn.edu.nwpu.domain.components.ReducingValve;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReducingValveCalcTest {

    @Test
    public void reduceValveCalcExecuteTest(){
        ReducingValve reducingValve = new ReducingValve(new N2GasData());
        reducingValve.setC(5010);
        reducingValve.setStiffness(390700);
        reducingValve.setF(3516.3);
        reducingValve.setM(0.166);
        reducingValve.setAmb(0.00090792);
        reducingValve.setAvc(1.9635e-5);
        reducingValve.setV1(6.15e-6);
        reducingValve.setV2(6.15e-6);
        reducingValve.setXstop(0.0002);

        ReducingValveCalc reducingValveCalc = new ReducingValveCalc(reducingValve);
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 2.0){
            double pe = 101325.0;
            if (time < 1){
                pe += 101325.0*0.5;
            }else {
                pe -= 101325.0;
            }
            reducingValveCalc.execute(0.035,300,pe);
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }
//        gasBottleCalc.outPutToFile("E:\\ProgramTest\\newGasBottle.txt");
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("phigh", reducingValveCalc.getP_high());
        resultMap.put("plow", reducingValveCalc.getP_low());
        resultMap.put("temphigh", reducingValveCalc.getTemp_high());
        resultMap.put("templow", reducingValveCalc.getTemp_low());
        resultMap.put("qhigh", reducingValveCalc.getQ_high());
        resultMap.put("qlow", reducingValveCalc.getQ_low());
        resultMap.put("x", reducingValveCalc.getX());
        resultMap.put("u", reducingValveCalc.getU());
        ResultShowUtil.showChart(resultMap);
    }
}