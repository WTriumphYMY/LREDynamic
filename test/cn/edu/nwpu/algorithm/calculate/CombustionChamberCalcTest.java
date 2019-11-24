package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.domain.components.CombustionChamber;
import cn.edu.nwpu.algorithm.GlobleParas;
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

public class CombustionChamberCalcTest {

    @Test
    public void execute() throws Exception{
        CombustionChamber combustionChamber = new CombustionChamber();
        combustionChamber.setVc(7.8289e-5);
        combustionChamber.setTauc(0.0);
        combustionChamber.setK(1.25);
        combustionChamber.setArea_throat(0.007*0.007*0.25*Math.PI);//0.007
        combustionChamber.setEps(25.72);//25.72
        combustionChamber.setPa(50662.5);//101325.0

        CombustionChamberCalc combustionChamberCalc = new CombustionChamberCalc(combustionChamber);

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 0.3){
            combustionChamberCalc.execute(0.18, 0.15);//0.18, 0.15 0.0388625, 0.0189286
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p_co", combustionChamberCalc.getP());
        resultMap.put("r_co", combustionChamberCalc.getR());
        resultMap.put("f_co", combustionChamberCalc.getF());
        resultMap.put("q_co", combustionChamberCalc.getQ());

        File file = new File("E:\\ProgramTest\\thrustChamber_pa50662.5.txt");
        Path path = file.toPath();
        Files.deleteIfExists(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
        bw.write("t\tp\tr\tF\tq\t");
        bw.newLine();
        for (int i = 0; i < resultMap.get("t").size(); i++) {
            bw.write(resultMap.get("t").get(i)+"\t"+resultMap.get("p_co").get(i)/1e6+"\t"+
                    resultMap.get("r_co").get(i)+"\t"+resultMap.get("f_co").get(i)/1e3+"\t"+
                    resultMap.get("q_co").get(i));
            bw.newLine();
        }
        bw.close();

        ResultShowUtil.showChart(resultMap);
    }
}