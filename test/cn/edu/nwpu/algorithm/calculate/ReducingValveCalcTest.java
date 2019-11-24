package cn.edu.nwpu.algorithm.calculate;


import cn.edu.nwpu.domain.components.ReducingValve;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReducingValveCalcTest {

    @Test
    public void reduceValveCalcExecuteTest() throws Exception{
        ReducingValve reducingValve = new ReducingValve(new N2GasData());
        reducingValve.setC(5010);
        reducingValve.setStiffness(390700);//390700
        reducingValve.setF(3516.3);
        reducingValve.setM(0.166);//0.166
        reducingValve.setAmb(9.0792e-4);//9.0792e-4
        reducingValve.setAvc(1.9635e-5);//1.9635e-5  5.89049e-5
        reducingValve.setV1(6e-6);
        reducingValve.setV2(6e-6);//6.15e-6
        reducingValve.setXstop(0.0002);
        reducingValve.setXs(0.009);

        ReducingValveCalc reducingValveCalc = new ReducingValveCalc(reducingValve);
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 0.5){
            double pe = 101325.0;
            if (time < 1){
                pe += 101325.0*0.5;
            }else {
                pe -= 101325.0;
            }
            reducingValveCalc.execute(2e7,300,pe);//0.035
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

        File file = new File("E:\\ProgramTest\\reducevalve_amb9e-5.txt");
        Path path = file.toPath();
        Files.deleteIfExists(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
        bw.write("t\tphigh\tplow\tqhigh\tqlow\ttemphigh\ttemplow\tx\tu\t");
        bw.newLine();
        for (int i = 0; i < resultMap.get("t").size(); i++) {
            bw.write(resultMap.get("t").get(i)+"\t"+resultMap.get("phigh").get(i)/1e6+"\t"+
                    resultMap.get("plow").get(i)/1e6+"\t"+resultMap.get("qhigh").get(i)+"\t"+
                    resultMap.get("qlow").get(i)+"\t"+resultMap.get("temphigh").get(i)+"\t"+
                    resultMap.get("templow").get(i)+"\t"+resultMap.get("x").get(i)+"\t"+
                    resultMap.get("u").get(i));
            bw.newLine();
        }
        bw.close();

        ResultShowUtil.showChart(resultMap);
    }
}