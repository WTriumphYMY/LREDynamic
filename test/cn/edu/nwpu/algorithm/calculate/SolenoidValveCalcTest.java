package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.SimpleLiquidOrifice;
import cn.edu.nwpu.domain.components.Solenoidvalve;
import cn.edu.nwpu.algorithm.GlobleParas;
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

public class SolenoidValveCalcTest {

    @Test
    public void execute() throws Exception{
        SimpleLiquidOrifice simpleLiquidOrifice = new SimpleLiquidOrifice(new LO2Data());
        simpleLiquidOrifice.setD(0.005);
        simpleLiquidOrifice.setCq(0.997);
        Solenoidvalve solenoidvalve = new Solenoidvalve(new LO2Data(), simpleLiquidOrifice);
        solenoidvalve.setR(23.3);
        solenoidvalve.setN(1700);//1700
        solenoidvalve.setU(28.0);
        solenoidvalve.setSigma(1.3);
        solenoidvalve.setSm(0.0002);
        solenoidvalve.setK(2800);
        solenoidvalve.setM(0.021);
        solenoidvalve.setFf0(15);//8.68
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

        File file = new File("E:\\ProgramTest\\solenoid_F15.txt");
        Path path = file.toPath();
        Files.deleteIfExists(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
        bw.write("t\tphi\tpsi\tq\tv\tx\tp\t");
        bw.newLine();
        for (int i = 0; i < resultMap.get("t").size(); i++) {
            bw.write(resultMap.get("t").get(i)+"\t"+resultMap.get("phi").get(i)+"\t"+
                    resultMap.get("psi").get(i)+"\t"+resultMap.get("q").get(i)+"\t"+
                    resultMap.get("v").get(i)+"\t"+resultMap.get("x").get(i)+"\t"+
                    resultMap.get("p").get(i));
            bw.newLine();
        }
        bw.close();
        ResultShowUtil.showChart(resultMap);
    }
}