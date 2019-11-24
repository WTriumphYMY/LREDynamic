package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.LiquidTank;
import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
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


public class LiquidTankCalcTest {

    @Test
    public void execute() throws Exception{
        LiquidTank liquidTank = new LiquidTank(new LO2Data(), new N2GasData());
        liquidTank.setCA(2.663*1e-5);
        liquidTank.setV0(0.0002);//0.00011
        liquidTank.setP0(101325.0);
        liquidTank.setQ0(0.0);
        liquidTank.setRho0(new N2GasData().getRho());

        LiquidTankCalc liquidTankCalc = new LiquidTankCalc(liquidTank);
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 2){
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

        File file = new File("E:\\ProgramTest\\liquidtank_v0.0002.txt");
        Path path = file.toPath();
        Files.deleteIfExists(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
        bw.write("t\tp\tq\trho\tv\t");
        bw.newLine();
        for (int i = 0; i < resultMap.get("t").size(); i++) {
            bw.write(resultMap.get("t").get(i)+"\t"+resultMap.get("p").get(i)/1e6+"\t"+
                    resultMap.get("q").get(i)+"\t"+resultMap.get("rho").get(i)+"\t"+
                    resultMap.get("v").get(i));
            bw.newLine();
        }
        bw.close();

        ResultShowUtil.showChart(resultMap);
    }
}