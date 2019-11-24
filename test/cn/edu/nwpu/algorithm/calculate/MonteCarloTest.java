package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.algorithm.StatisticsCal;
import cn.edu.nwpu.dto.ConstantSystemDTO;
import cn.edu.nwpu.dto.MonteCarloDTO;
import cn.edu.nwpu.service.MonteCarloService;
import cn.edu.nwpu.service.impl.MonteCarloServiceImpl;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MonteCarloTest
 * @Author: wkx
 * @Date: 2019/11/8 18:43
 * @Version: v1.0
 * @Description:
 */
public class MonteCarloTest {

    @Test
    public void testMonteCarlo() throws Exception{
        ConstantSystemDTO constantSystemDTO = new ConstantSystemDTO();
        constantSystemDTO.setBottleCA("3.5e-4");
        constantSystemDTO.setBottleVol("5.0");
        constantSystemDTO.setBottlePressure("2.2e7");
        constantSystemDTO.setBottleTemperature("300.0");
        constantSystemDTO.setBottleGas("N2");

        constantSystemDTO.setReduceC("5010");
        constantSystemDTO.setReduceK("390700");
        constantSystemDTO.setReduceF("3516.3");
        constantSystemDTO.setReduceM("0.166");
        constantSystemDTO.setReduceAmb("0.00090792");
        constantSystemDTO.setReduceAvc("1.9635e-5");
        constantSystemDTO.setReduceV1("6e-5");
        constantSystemDTO.setReduceV2("6e-5");
        constantSystemDTO.setReduceXstop("0.0002");

        constantSystemDTO.setOxidTankCA("2.663e-5");
        constantSystemDTO.setOxidTankV0("0.00011");
        constantSystemDTO.setOxidTankPressure("101325.0");
        constantSystemDTO.setOxidTankFuel("LO2");

        constantSystemDTO.setFuelTankCA("2.663e-5");
        constantSystemDTO.setFuelTankV0("0.00011");
        constantSystemDTO.setFuelTankPressure("101325.0");
        constantSystemDTO.setFuelTankFuel("MMH");

        constantSystemDTO.setOxidLiquidOrificeD("0.005");
        constantSystemDTO.setOxidLiquidOrificePc("0.997");

        constantSystemDTO.setFuelLiquidOrificeD("0.005");
        constantSystemDTO.setFuelLiquidOrificePc("0.997");

        constantSystemDTO.setOxidSolenoidR("23.3");
        constantSystemDTO.setOxidSolenoidN("1700");
        constantSystemDTO.setOxidSolenoidU("28");
        constantSystemDTO.setOxidSolenoidSigma("1.3");
        constantSystemDTO.setOxidSolenoidSm("0.0002");
        constantSystemDTO.setOxidSolenoidK("2800");
        constantSystemDTO.setOxidSolenoidM("0.021");
        constantSystemDTO.setOxidSolenoidF("8.68");
        constantSystemDTO.setOxidSolenoidD("0.0035");
        constantSystemDTO.setOxidSolenoidXstop("0.0004");

        constantSystemDTO.setFuelSolenoidR("23.3");
        constantSystemDTO.setFuelSolenoidN("1700");
        constantSystemDTO.setFuelSolenoidU("28");
        constantSystemDTO.setFuelSolenoidSigma("1.3");
        constantSystemDTO.setFuelSolenoidSm("0.0002");
        constantSystemDTO.setFuelSolenoidK("2800");
        constantSystemDTO.setFuelSolenoidM("0.021");
        constantSystemDTO.setFuelSolenoidF("8.68");
        constantSystemDTO.setFuelSolenoidD("0.0035");
        constantSystemDTO.setFuelSolenoidXstop("0.0004");

        constantSystemDTO.setThrustChamberV("7.8289e-5");
        constantSystemDTO.setThrustChamberTauc("0.0");
        constantSystemDTO.setThrustChamberK("1.25");
        constantSystemDTO.setThrustChamberD("0.007");
        constantSystemDTO.setThrustChamberEps("5");
        constantSystemDTO.setThrustChamberPa("101325.0");

        constantSystemDTO.setGlobalParasStep("0.0001");
        constantSystemDTO.setGlobalParasTime("2");

        MonteCarloDTO monteCarloDTO = new MonteCarloDTO();
        monteCarloDTO.setConstantSystemDTO(constantSystemDTO);
        monteCarloDTO.setCalculateTimes("100");

        monteCarloDTO.setThrustChamberPaup("0");
        monteCarloDTO.setThrustChamberPadown("101325.0");
        monteCarloDTO.setBottlePressuremiu("2.2e7");
        monteCarloDTO.setBottlePressuresigma("2e6");
//        monteCarloDTO.setThrustChamberEpsmiu("5");
//        monteCarloDTO.setThrustChamberEpssigma("0.05");
        monteCarloDTO.setThrustChamberDmiu("0.007");
        monteCarloDTO.setThrustChamberDsigma("0.0001");
//        monteCarloDTO.setBottleVolmiu("5");
//        monteCarloDTO.setBottleVolsigma("0.5");

        MonteCarloService monteCarloService = new MonteCarloServiceImpl();
        Map<String, List<Double>> resultMap =  monteCarloService.monteCalculate(monteCarloDTO);

        File file = new File("E:\\ProgramTest\\montecarloSystem_3change.txt");
        Path path = file.toPath();
        Files.deleteIfExists(path);
        BufferedWriter bw = Files.newBufferedWriter(path);
        bw.write("PAve\t"+ StatisticsCal.average(resultMap.get("stablePCombustionList"))+"\t");
        bw.write("PSigma2\t"+StatisticsCal.variance(resultMap.get("stablePCombustionList"))+"\t");
        bw.write("PdownCo\t"+StatisticsCal.confidenceIntervalDown(resultMap.get("stablePCombustionList"))+"\t");
        bw.write("PupCo\t"+StatisticsCal.confidenceIntervalUp(resultMap.get("stablePCombustionList"))+"\t");
        bw.write("FAve\t"+ StatisticsCal.average(resultMap.get("stableFCombustionList"))+"\t");
        bw.write("FSigma2\t"+StatisticsCal.variance(resultMap.get("stableFCombustionList"))+"\t");
        bw.write("FdownCo\t"+StatisticsCal.confidenceIntervalDown(resultMap.get("stableFCombustionList"))+"\t");
        bw.write("FupCo\t"+StatisticsCal.confidenceIntervalUp(resultMap.get("stableFCombustionList"))+"\t");
        bw.write("QAve\t"+ StatisticsCal.average(resultMap.get("stableQCombustionList"))+"\t");
        bw.write("QSigma2\t"+StatisticsCal.variance(resultMap.get("stableQCombustionList"))+"\t");
        bw.write("QdownCo\t"+StatisticsCal.confidenceIntervalDown(resultMap.get("stableQCombustionList"))+"\t");
        bw.write("QupCo\t"+StatisticsCal.confidenceIntervalUp(resultMap.get("stableQCombustionList"))+"\t");
        bw.write("IspAve\t"+ StatisticsCal.average(resultMap.get("stableIspCombustionList"))+"\t");
        bw.write("IspSigma2\t"+StatisticsCal.variance(resultMap.get("stableIspCombustionList"))+"\t");
        bw.write("IspdownCo\t"+StatisticsCal.confidenceIntervalDown(resultMap.get("stableIspCombustionList"))+"\t");
        bw.write("IspupCo\t"+StatisticsCal.confidenceIntervalUp(resultMap.get("stableIspCombustionList"))+"\t");
        bw.newLine();
        for (int i = 0; i < 5; i++) {
            bw.write(resultMap.get("boxFCombustionList").get(i)+"\t");
        }
        bw.newLine();
        for (int i = 0; i < 5; i++) {
            bw.write(resultMap.get("boxIspCombustionList").get(i)+"\t");
        }
        bw.newLine();
        bw.write("t\tpup\tpdown\tFup\tFdown\tqup\tqdown\tIspup\tIspdown");
        bw.newLine();
        for (int i = 0; i < resultMap.get("time").size(); i++) {
            bw.write(resultMap.get("time").get(i)+"\t"+resultMap.get("upPCombustionList").get(i)/1e6+"\t"+
                    resultMap.get("downPCombustionList").get(i)/1e6+"\t"+resultMap.get("upFCombustionList").get(i)/1e3+"\t"+
                    resultMap.get("downFCombustionList").get(i)/1e3+"\t"+resultMap.get("upQCombustionList").get(i)+"\t"+
                    resultMap.get("downQCombustionList").get(i)+"\t"+resultMap.get("upIspList").get(i)+"\t"+
                    resultMap.get("downIspList").get(i));
            bw.newLine();
        }
        bw.close();
    }
}


