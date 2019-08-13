package cn.edu.nwpu.algorithm.calculate;


import cn.edu.nwpu.domain.components.GasBottle;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GasBottleCalcTest {

    @Test
    public void gasBottleExecuteTest(){
        GasBottle gasBottle = new GasBottle(new N2GasData());
        gasBottle.setCA(3.5e-4);
        gasBottle.setVc(0.05);
        gasBottle.setP0(2.2e7);
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
        ResultShowUtil.showChart(resultMap);
    }
}