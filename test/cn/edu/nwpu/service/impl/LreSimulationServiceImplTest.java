package cn.edu.nwpu.service.impl;

import cn.edu.nwpu.dto.ConstantSystemDTO;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

public class LreSimulationServiceImplTest {

    private LreSimulationServiceImpl lreSimulationService = new LreSimulationServiceImpl();
    private ConstantSystemDTO constantSystemDTO = new ConstantSystemDTO();
    private Map<String, List<Double>> resultMap;

    @Before
    public void init(){
        setConstantSystemDTOData();
    }

    @After
    public void showResult(){
        ResultShowUtil.showChart(resultMap);
    }

    @Test
    public void constDualSystemSim() {
        resultMap = lreSimulationService.constDualSystemSim(constantSystemDTO);
    }

    @Test
    public void gasBottleSim() {
        constantSystemDTO.setPe("101325.0");
        resultMap = lreSimulationService.gasBottleSim(constantSystemDTO);
    }

    @Test
    public void reduceValveSim() {
        constantSystemDTO.setQin("0.035");
        constantSystemDTO.setTin("300");
        constantSystemDTO.setPe("101325.0");
        resultMap = lreSimulationService.reduceValveSim(constantSystemDTO);
    }

    @Test
    public void liquidTankSim() {
        constantSystemDTO.setPin("1e7");
        constantSystemDTO.setPe("101325");
        resultMap = lreSimulationService.liquidTankSim(constantSystemDTO);
    }

    @Test
    public void solenoidSim() {
        constantSystemDTO.setPin("2.2e6");
        constantSystemDTO.setQin("2");
        resultMap = lreSimulationService.solenoidSim(constantSystemDTO);
    }

    @Test
    public void thrustChamberSim() {
        constantSystemDTO.setQo("0.18");
        constantSystemDTO.setQf("0.15");
        resultMap = lreSimulationService.thrustChamberSim(constantSystemDTO);
    }

    private void setConstantSystemDTOData(){
        constantSystemDTO.setGlobalParasTime("1");
        constantSystemDTO.setGlobalParasStep("0.0001");

        constantSystemDTO.setBottleCA("3.5e-4");
        constantSystemDTO.setBottleGas("N2");
        constantSystemDTO.setBottlePressure("2.2e7");
        constantSystemDTO.setBottleTemperature("300");
        constantSystemDTO.setBottleVol("0.05");

        constantSystemDTO.setReduceAmb("0.00090792");
        constantSystemDTO.setReduceAvc("1.9635e-5");
        constantSystemDTO.setReduceC("5010");
        constantSystemDTO.setReduceF("3516.3");
        constantSystemDTO.setReduceK("390700");
        constantSystemDTO.setReduceM("0.166");
        constantSystemDTO.setReduceV1("6.15e-6");
        constantSystemDTO.setReduceV2("6.15e-6");
        constantSystemDTO.setReduceXstop("0.0002");

        constantSystemDTO.setFuelTankCA("2.663e-5");
        constantSystemDTO.setFuelTankFuel("MMH");
        constantSystemDTO.setFuelTankPressure("101325.0");
        constantSystemDTO.setFuelTankV0("0.00011");

        constantSystemDTO.setOxidTankCA("2.663e-5");
        constantSystemDTO.setOxidTankFuel("LO2");
        constantSystemDTO.setOxidTankPressure("101325.0");
        constantSystemDTO.setOxidTankV0("0.00011");

        constantSystemDTO.setFuelLiquidOrificeD("0.005");
        constantSystemDTO.setFuelLiquidOrificePc("0.997");

        constantSystemDTO.setOxidLiquidOrificeD("0.005");
        constantSystemDTO.setOxidLiquidOrificePc("0.997");

        constantSystemDTO.setFuelSolenoidR("23.3");
        constantSystemDTO.setFuelSolenoidN("1700");
        constantSystemDTO.setFuelSolenoidU("28.0");
        constantSystemDTO.setFuelSolenoidSigma("1.3");
        constantSystemDTO.setFuelSolenoidSm("0.0002");
        constantSystemDTO.setFuelSolenoidK("2800");
        constantSystemDTO.setFuelSolenoidM("0.021");
        constantSystemDTO.setFuelSolenoidF("8.68");
        constantSystemDTO.setFuelSolenoidD("0.0035");
        constantSystemDTO.setFuelSolenoidXstop("0.0004");

        constantSystemDTO.setOxidSolenoidR("23.3");
        constantSystemDTO.setOxidSolenoidN("1700");
        constantSystemDTO.setOxidSolenoidU("28.0");
        constantSystemDTO.setOxidSolenoidSigma("1.3");
        constantSystemDTO.setOxidSolenoidSm("0.0002");
        constantSystemDTO.setOxidSolenoidK("2800");
        constantSystemDTO.setOxidSolenoidM("0.021");
        constantSystemDTO.setOxidSolenoidF("8.68");
        constantSystemDTO.setOxidSolenoidD("0.0035");
        constantSystemDTO.setOxidSolenoidXstop("0.0004");

        constantSystemDTO.setThrustChamberV("7.8289e-5");
        constantSystemDTO.setThrustChamberTauc("0.0");
        constantSystemDTO.setThrustChamberK("1.25");
        constantSystemDTO.setThrustChamberD("0.007");
        constantSystemDTO.setThrustChamberEps("25.72");
        constantSystemDTO.setThrustChamberPa("101325.0");


    }
}