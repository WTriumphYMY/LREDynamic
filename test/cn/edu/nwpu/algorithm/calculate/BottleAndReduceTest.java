package cn.edu.nwpu.algorithm.calculate;

import cn.edu.nwpu.algorithm.GlobleParas;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
import cn.edu.nwpu.domain.LiquidData.MMHData;
import cn.edu.nwpu.domain.components.*;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName BottleAndReduceTest
 * @Author: wkx
 * @Date: 2019/7/2 15:57
 * @Version: v1.0
 * @Description: 测试气瓶和减压阀联合
 */
public class BottleAndReduceTest {

    @Test
    public void testBottleAndReduce(){
        GasBottle gasBottle = new GasBottle(new N2GasData());
        gasBottle.setCA(3.5e-4);
        gasBottle.setVc(3.0);//0.05
        gasBottle.setP0(2.2e7);
//        gasBottle.setRho0(0.1785);
        gasBottle.setTemp0(300.0);

        GasBottleCalc gasBottleCalc = new GasBottleCalc(gasBottle);

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

        LiquidTank liquidTank_LO2 = new LiquidTank(new LO2Data(), new N2GasData());
        liquidTank_LO2.setCA(2.663*1e-5);
        liquidTank_LO2.setV0(0.00011);
        liquidTank_LO2.setP0(101325.0);
        liquidTank_LO2.setQ0(0.0);
        liquidTank_LO2.setRho0(new N2GasData().getRho());

        LiquidTankCalc liquidTankCalc_LO2 = new LiquidTankCalc(liquidTank_LO2);

        LiquidTank liquidTank_MMH = new LiquidTank(new MMHData(), new N2GasData());
        liquidTank_MMH.setCA(2.663*1e-5);
        liquidTank_MMH.setV0(0.00011);
        liquidTank_MMH.setP0(101325.0);
        liquidTank_MMH.setQ0(0.0);
        liquidTank_MMH.setRho0(new N2GasData().getRho());

        LiquidTankCalc liquidTankCalc_MMH = new LiquidTankCalc(liquidTank_MMH);

        SimpleLiquidOrifice simpleLiquidOrifice_LO2 = new SimpleLiquidOrifice(new LO2Data());
        simpleLiquidOrifice_LO2.setD(0.005);
        simpleLiquidOrifice_LO2.setPressureCoefficience(0.997);

        Solenoidvalve solenoidvalve_LO2 = new Solenoidvalve(new LO2Data(), simpleLiquidOrifice_LO2);
        solenoidvalve_LO2.setR(23.3);
        solenoidvalve_LO2.setN(1700);
        solenoidvalve_LO2.setU(28.0);
        solenoidvalve_LO2.setSigma(1.3);
        solenoidvalve_LO2.setSm(0.0002);
        solenoidvalve_LO2.setK(2800);
        solenoidvalve_LO2.setM(0.021);
        solenoidvalve_LO2.setFf0(8.68);
        solenoidvalve_LO2.setD(0.0035);
        solenoidvalve_LO2.setXstop(0.0004);

        SolenoidValveCalc solenoidValveCalc_LO2 = new SolenoidValveCalc(solenoidvalve_LO2);

        SimpleLiquidOrifice simpleLiquidOrifice_MMH = new SimpleLiquidOrifice(new LO2Data());
        simpleLiquidOrifice_MMH.setD(0.005);
        simpleLiquidOrifice_MMH.setPressureCoefficience(0.997);

        Solenoidvalve solenoidvalve_MMH = new Solenoidvalve(new MMHData(), simpleLiquidOrifice_MMH);
        solenoidvalve_MMH.setR(23.3);
        solenoidvalve_MMH.setN(1700);
        solenoidvalve_MMH.setU(28.0);
        solenoidvalve_MMH.setSigma(1.3);
        solenoidvalve_MMH.setSm(0.0002);
        solenoidvalve_MMH.setK(2800);
        solenoidvalve_MMH.setM(0.021);
        solenoidvalve_MMH.setFf0(8.68);
        solenoidvalve_MMH.setD(0.0035);
        solenoidvalve_MMH.setXstop(0.0004);

        SolenoidValveCalc solenoidValveCalc_MMH = new SolenoidValveCalc(solenoidvalve_MMH);

        CombustionChamber combustionChamber = new CombustionChamber();
        combustionChamber.setVc(7.8289e-5);
        combustionChamber.setTauc(0.0);
        combustionChamber.setK(1.25);
        combustionChamber.setArea_throat(0.007*0.007*0.25*Math.PI);
        combustionChamber.setEps(25.72);
        combustionChamber.setPa(101325.0);

        CombustionChamberCalc combustionChamberCalc = new CombustionChamberCalc(combustionChamber);

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < 2.0){
            int index = gasBottleCalc.getP().size()-1;
            gasBottleCalc.execute(reducingValveCalc.getP_high().get(index));
            reducingValveCalc.execute(gasBottleCalc.getQ().get(index), gasBottleCalc.getTemp().get(index),
                    liquidTankCalc_LO2.getP().get(index));
            //液氧贮箱与电磁阀
            double rhoin = reducingValveCalc.getP_low().get(index)/reducingValveCalc.getTemp_low().get(index)/reducingValve.getRg();
            liquidTankCalc_LO2.execute(reducingValveCalc.getP_low().get(index), rhoin, solenoidValveCalc_LO2.getP().get(index));
            solenoidValveCalc_LO2.execute(time, liquidTankCalc_LO2.getP().get(index), liquidTankCalc_LO2.getQ().get(index));
            //甲基肼贮箱与电磁阀
            liquidTankCalc_MMH.execute(reducingValveCalc.getP_low().get(index), rhoin,solenoidValveCalc_MMH.getP().get(index));
            solenoidValveCalc_MMH.execute(time, liquidTankCalc_MMH.getP().get(index), liquidTankCalc_MMH.getQ().get(index));

            combustionChamberCalc.execute(solenoidValveCalc_LO2.getQ().get(index), solenoidValveCalc_MMH.getQ().get(index));
            time += GlobleParas.TIME_STEP;
            t.add(time);
        }

        Map<String, List<Double>> resultMap = new TreeMap<>();
        resultMap.put("t", t);
        resultMap.put("p_g", gasBottleCalc.getP());
        resultMap.put("q_g", gasBottleCalc.getQ());
        resultMap.put("rho_g", gasBottleCalc.getRho());
        resultMap.put("temp_g", gasBottleCalc.getTemp());

        resultMap.put("phigh", reducingValveCalc.getP_high());
        resultMap.put("plow", reducingValveCalc.getP_low());
        resultMap.put("temphigh", reducingValveCalc.getTemp_high());
        resultMap.put("templow", reducingValveCalc.getTemp_low());
        resultMap.put("qhigh", reducingValveCalc.getQ_high());
        resultMap.put("qlow", reducingValveCalc.getQ_low());
        resultMap.put("x", reducingValveCalc.getX());
        resultMap.put("u", reducingValveCalc.getU());

        resultMap.put("p_lo2", liquidTankCalc_LO2.getP());
        resultMap.put("q_lo2", liquidTankCalc_LO2.getQ());
        resultMap.put("rho_lo2", liquidTankCalc_LO2.getRho());
        resultMap.put("v_lo2", liquidTankCalc_LO2.getV());
        resultMap.put("qin_lo2", liquidTankCalc_LO2.getQin());

        resultMap.put("phi_o2", solenoidValveCalc_LO2.getPhi());
        resultMap.put("psi_o2", solenoidValveCalc_LO2.getPsi());
        resultMap.put("q_so2", solenoidValveCalc_LO2.getQ());
        resultMap.put("v_so2", solenoidValveCalc_LO2.getV());
        resultMap.put("x_so2", solenoidValveCalc_LO2.getX());
        resultMap.put("p_so2", solenoidValveCalc_LO2.getP());

        resultMap.put("p_lmmh", liquidTankCalc_MMH.getP());
        resultMap.put("q_lmmh", liquidTankCalc_MMH.getQ());
        resultMap.put("rho_lmmh", liquidTankCalc_MMH.getRho());
        resultMap.put("v_lmmh", liquidTankCalc_MMH.getV());

        resultMap.put("phi_mmh", solenoidValveCalc_MMH.getPhi());
        resultMap.put("psi_mmh", solenoidValveCalc_MMH.getPsi());
        resultMap.put("q_smmh", solenoidValveCalc_MMH.getQ());
        resultMap.put("v_smmh", solenoidValveCalc_MMH.getV());
        resultMap.put("x_smmh", solenoidValveCalc_MMH.getX());
        resultMap.put("p_smmh", solenoidValveCalc_MMH.getP());

        resultMap.put("p_com", combustionChamberCalc.getP());
        resultMap.put("r_com", combustionChamberCalc.getR());
        resultMap.put("f_com", combustionChamberCalc.getF());
        resultMap.put("q_com", combustionChamberCalc.getQ());
        ResultShowUtil.showChart(resultMap);
//        ResultShowUtil.writeToFile(resultMap);
    }
}
