package cn.edu.nwpu.algorithm.calculate;


import cn.edu.nwpu.domain.components.GasBottle;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GasBottleCalcTest {

    @Test
    public void gasBottleExecuteTest() throws Exception{
        GasBottle gasBottle = new GasBottle(new N2GasData());
        gasBottle.setCA(3.5e-4);
        gasBottle.setVc(0.05);
        gasBottle.setP0(1.8e7);
        gasBottle.setTemp0(300.0);

        GasBottleCalc gasBottleCalc = new GasBottleCalc(gasBottle);
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 1){
            gasBottleCalc.execute(101325.0);
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", gasBottleCalc.getP());
        resultMap.put("q", gasBottleCalc.getQ());
        resultMap.put("rho", gasBottleCalc.getRho());
        resultMap.put("temp", gasBottleCalc.getTemp());
        BufferedWriter bw = Files.newBufferedWriter(new File("E:\\ProgramTest\\gasbottle_p1.8.txt").toPath());
        bw.write("t\tp\tq\trho\ttemp\t");
        bw.newLine();
        for (int i = 0; i < resultMap.get("t").size(); i++) {
            bw.write(resultMap.get("t").get(i)+"\t"+resultMap.get("p").get(i)+"\t"+
                    resultMap.get("q").get(i)+"\t"+resultMap.get("rho").get(i)+"\t"+
                    resultMap.get("temp").get(i));
            bw.newLine();
        }
        bw.close();
        ResultShowUtil.showChart(resultMap);

    }
}