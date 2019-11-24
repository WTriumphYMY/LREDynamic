package cn.edu.nwpu.service.impl;

import cn.edu.nwpu.algorithm.calculate.*;
import cn.edu.nwpu.domain.GasData.GasData;
import cn.edu.nwpu.domain.GasData.N2GasData;
import cn.edu.nwpu.domain.LiquidData.LO2Data;
import cn.edu.nwpu.domain.LiquidData.LiquidData;
import cn.edu.nwpu.domain.LiquidData.MMHData;
import cn.edu.nwpu.domain.components.*;
import cn.edu.nwpu.dto.*;
import cn.edu.nwpu.service.LreSimulationService;
import cn.edu.nwpu.utils.MediumFactory;
import cn.edu.nwpu.utils.ResultShowUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ConstDualSystemServiceImpl
 * @Author: wkx
 * @Date: 2019/7/20 10:04
 * @Version: v1.0
 * @Description: 液发仿真实现
 */
@Service
public class LreSimulationServiceImpl implements LreSimulationService {

    @Override
    public Map<String, List<Double>> constDualSystemSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        GasBottle gasBottle = getGasBottle(constantSystemDTO);
        GasBottleCalc gasBottleCalc = new GasBottleCalc(gasBottle);

        ReducingValve reducingValve = getReduceValve(constantSystemDTO);
        ReducingValveCalc reducingValveCalc = new ReducingValveCalc(reducingValve);

        LiquidTank liquidTank_LO2 = getOxidTank(constantSystemDTO);
        LiquidTankCalc liquidTankCalc_LO2 = new LiquidTankCalc(liquidTank_LO2);

        LiquidTank liquidTank_MMH = getFuelTank(constantSystemDTO);
        LiquidTankCalc liquidTankCalc_MMH = new LiquidTankCalc(liquidTank_MMH);

        SimpleLiquidOrifice simpleLiquidOrifice_LO2 = getOxidOrifice(constantSystemDTO);

        Solenoidvalve solenoidvalve_LO2 = getOxidSolenoidValve(constantSystemDTO, simpleLiquidOrifice_LO2);
        SolenoidValveCalc solenoidValveCalc_LO2 = new SolenoidValveCalc(solenoidvalve_LO2);

        SimpleLiquidOrifice simpleLiquidOrifice_MMH = getFuelOrifice(constantSystemDTO);
        Solenoidvalve solenoidvalve_MMH = getFuelSolenoidValve(constantSystemDTO, simpleLiquidOrifice_MMH);
        SolenoidValveCalc solenoidValveCalc_MMH = new SolenoidValveCalc(solenoidvalve_MMH);

        CombustionChamber combustionChamber = getCombustionChamber(constantSystemDTO);
        CombustionChamberCalc combustionChamberCalc = new CombustionChamberCalc(combustionChamber);

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            int index = gasBottleCalc.getP().size()-1;
            gasBottleCalc.execute(reducingValveCalc.getP_high().get(index));
            reducingValveCalc.execute(gasBottleCalc.getP().get(index), gasBottleCalc.getTemp().get(index),
                    liquidTankCalc_LO2.getP().get(index));
            //液氧贮箱与电磁阀
            double rhoin = reducingValveCalc.getP_low().get(index)/reducingValveCalc.getTemp_low().get(index)/reducingValve.getRg();
            liquidTankCalc_LO2.execute(reducingValveCalc.getP_low().get(index), rhoin, solenoidValveCalc_LO2.getP().get(index));
            solenoidValveCalc_LO2.execute(time, liquidTankCalc_LO2.getP().get(index), liquidTankCalc_LO2.getQ().get(index));
            //甲基肼贮箱与电磁阀
            liquidTankCalc_MMH.execute(reducingValveCalc.getP_low().get(index), rhoin,solenoidValveCalc_MMH.getP().get(index));
            solenoidValveCalc_MMH.execute(time, liquidTankCalc_MMH.getP().get(index), liquidTankCalc_MMH.getQ().get(index));

            combustionChamberCalc.execute(solenoidValveCalc_LO2.getQ().get(index), solenoidValveCalc_MMH.getQ().get(index));
            time += timeStep;
            t.add(time);
        }

        Map<String, List<Double>> resultMap = new TreeMap<>();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0000");
        resultMap.put("t", t.stream().map(tt -> Double.parseDouble(df.format(tt))).collect(Collectors.toList()));
        resultMap.put("p_g", gasBottleCalc.getP().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("q_g", gasBottleCalc.getQ());
        resultMap.put("rho_g", gasBottleCalc.getRho());
        resultMap.put("temp_g", gasBottleCalc.getTemp());

        resultMap.put("phigh", reducingValveCalc.getP_high().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("plow", reducingValveCalc.getP_low().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("temphigh", reducingValveCalc.getTemp_high());
        resultMap.put("templow", reducingValveCalc.getTemp_low());
        resultMap.put("qhigh", reducingValveCalc.getQ_high());
        resultMap.put("qlow", reducingValveCalc.getQ_low());
        resultMap.put("x", reducingValveCalc.getX());
        resultMap.put("u", reducingValveCalc.getU());

        resultMap.put("p_lo2", liquidTankCalc_LO2.getP().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("q_lo2", liquidTankCalc_LO2.getQ().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("rho_lo2", liquidTankCalc_LO2.getRho());
        resultMap.put("v_lo2", liquidTankCalc_LO2.getV());
        resultMap.put("qin_lo2", liquidTankCalc_LO2.getQin());

        resultMap.put("phi_o2", solenoidValveCalc_LO2.getPhi());
        resultMap.put("psi_o2", solenoidValveCalc_LO2.getPsi());
        resultMap.put("q_so2", solenoidValveCalc_LO2.getQ());
        resultMap.put("v_so2", solenoidValveCalc_LO2.getV());
        resultMap.put("x_so2", solenoidValveCalc_LO2.getX());
        resultMap.put("p_so2", solenoidValveCalc_LO2.getP().stream().map(p -> p/1e6).collect(Collectors.toList()));

        resultMap.put("p_lmmh", liquidTankCalc_MMH.getP());
        resultMap.put("q_lmmh", liquidTankCalc_MMH.getQ());
        resultMap.put("rho_lmmh", liquidTankCalc_MMH.getRho());
        resultMap.put("v_lmmh", liquidTankCalc_MMH.getV());

        resultMap.put("phi_mmh", solenoidValveCalc_MMH.getPhi());
        resultMap.put("psi_mmh", solenoidValveCalc_MMH.getPsi());
        resultMap.put("q_smmh", solenoidValveCalc_MMH.getQ());
        resultMap.put("v_smmh", solenoidValveCalc_MMH.getV());
        resultMap.put("x_smmh", solenoidValveCalc_MMH.getX());
        resultMap.put("p_smmh", solenoidValveCalc_MMH.getP().stream().map(p -> p/1e6).collect(Collectors.toList()));

        resultMap.put("p_com", combustionChamberCalc.getP().stream().map(p -> p/1e6).collect(Collectors.toList()));
        resultMap.put("r_com", combustionChamberCalc.getR());
        resultMap.put("f_com", combustionChamberCalc.getF().stream().map(f -> f/1e3).collect(Collectors.toList()));
        resultMap.put("q_com", combustionChamberCalc.getQ());
        resultMap.put("isp", combustionChamberCalc.getIsp());
//        ResultShowUtil.showChart(resultMap);
        return resultMap;
    }

    @Override
    public Map<String, List<Double>> gasBottleSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        GasBottle gasBottle = getGasBottle(constantSystemDTO);
        GasBottleCalc gasBottleCalc = new GasBottleCalc(gasBottle);

        double pe = Double.parseDouble(constantSystemDTO.getPe());
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            gasBottleCalc.execute(pe);
            time += timeStep;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", gasBottleCalc.getP());
        resultMap.put("q", gasBottleCalc.getQ());
        resultMap.put("rho", gasBottleCalc.getRho());
        resultMap.put("temp", gasBottleCalc.getTemp());
        return resultMap;
    }

    @Override
    public Map<String, List<Double>> reduceValveSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        ReducingValve reducingValve = getReduceValve(constantSystemDTO);
        ReducingValveCalc reducingValveCalc = new ReducingValveCalc(reducingValve);

        double qin = Double.parseDouble(constantSystemDTO.getQin());
        double tin = Double.parseDouble(constantSystemDTO.getTin());
        double pe = Double.parseDouble(constantSystemDTO.getPe());

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            reducingValveCalc.execute(qin,tin,pe);
            time += timeStep;
            t.add(time);
        }

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
        return resultMap;
    }

    @Override
    public Map<String, List<Double>> liquidTankSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        LiquidTank liquidTank = getFuelTank(constantSystemDTO);
        LiquidTankCalc liquidTankCalc = new LiquidTankCalc(liquidTank);

        double pin = Double.parseDouble(constantSystemDTO.getPin());
        double pe = Double.parseDouble(constantSystemDTO.getPe());

        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            liquidTankCalc.execute(pin, new N2GasData().getRho(),pe);
            time += timeStep;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", liquidTankCalc.getP());
        resultMap.put("q", liquidTankCalc.getQ());
        resultMap.put("rho", liquidTankCalc.getRho());
        resultMap.put("v", liquidTankCalc.getV());
        return resultMap;
    }

    @Override
    public Map<String, List<Double>> solenoidSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        Solenoidvalve solenoidvalve = getFuelSolenoidValve(constantSystemDTO, getFuelOrifice(constantSystemDTO));
        SolenoidValveCalc solenoidValveCalc = new SolenoidValveCalc(solenoidvalve);

        double pin = Double.parseDouble(constantSystemDTO.getPin());
        double qin = Double.parseDouble(constantSystemDTO.getQin());
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            solenoidValveCalc.execute(time,  pin, qin);
            time += timeStep;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", solenoidValveCalc.getP());
        resultMap.put("phi", solenoidValveCalc.getPhi());
        resultMap.put("psi", solenoidValveCalc.getPsi());
        resultMap.put("q", solenoidValveCalc.getQ());
        resultMap.put("v", solenoidValveCalc.getV());
        resultMap.put("x", solenoidValveCalc.getX());
        return resultMap;
    }

    @Override
    public Map<String, List<Double>> thrustChamberSim(ConstantSystemDTO constantSystemDTO) {
        double calTime = Double.parseDouble(constantSystemDTO.getGlobalParasTime());
        double timeStep = Double.parseDouble(constantSystemDTO.getGlobalParasStep());

        CombustionChamber combustionChamber = getCombustionChamber(constantSystemDTO);
        CombustionChamberCalc combustionChamberCalc = new CombustionChamberCalc(combustionChamber);

        double qo = Double.parseDouble(constantSystemDTO.getQo());
        double qf = Double.parseDouble(constantSystemDTO.getQf());
        double time = 0.0;
        List<Double> t = new ArrayList<>();
        t.add(time);
        while (time < calTime){
            combustionChamberCalc.execute(qo, qf);//0.18, 0.15 0.0388625, 0.0189286
            time += timeStep;
            t.add(time);
        }
        Map<String, List<Double>> resultMap = new HashMap<>();
        resultMap.put("t", t);
        resultMap.put("p", combustionChamberCalc.getP());
        resultMap.put("r", combustionChamberCalc.getR());
        resultMap.put("f", combustionChamberCalc.getF());
        resultMap.put("q", combustionChamberCalc.getQ());
        return resultMap;
    }

    private GasBottle getGasBottle(ConstantSystemDTO constantSystemDTO){
        GasData pushGas = MediumFactory.getGasData(constantSystemDTO.getBottleGas());
        GasBottle gasBottle = new GasBottle(pushGas);
        gasBottle.setCA(Double.parseDouble(constantSystemDTO.getBottleCA()));
        gasBottle.setVc(Double.parseDouble(constantSystemDTO.getBottleVol()));//0.05
        gasBottle.setP0(Double.parseDouble(constantSystemDTO.getBottlePressure()));
        gasBottle.setTemp0(Double.parseDouble(constantSystemDTO.getBottleTemperature()));
        return gasBottle;
    }

    private ReducingValve getReduceValve(ConstantSystemDTO constantSystemDTO){
        GasData pushGas = MediumFactory.getGasData(constantSystemDTO.getBottleGas());
        ReducingValve reducingValve = new ReducingValve(pushGas);
        reducingValve.setC(Double.parseDouble(constantSystemDTO.getReduceC()));
        reducingValve.setStiffness(Double.parseDouble(constantSystemDTO.getReduceK()));
        reducingValve.setF(Double.parseDouble(constantSystemDTO.getReduceF()));
        reducingValve.setM(Double.parseDouble(constantSystemDTO.getReduceM()));
        reducingValve.setAmb(Double.parseDouble(constantSystemDTO.getReduceAmb()));
        reducingValve.setAvc(Double.parseDouble(constantSystemDTO.getReduceAvc()));
        reducingValve.setV1(Double.parseDouble(constantSystemDTO.getReduceV1()));
        reducingValve.setV2(Double.parseDouble(constantSystemDTO.getReduceV2()));
        reducingValve.setXstop(Double.parseDouble(constantSystemDTO.getReduceXstop()));
        return reducingValve;
    }

    private LiquidTank getOxidTank(ConstantSystemDTO constantSystemDTO){
        GasData pushGas = MediumFactory.getGasData(constantSystemDTO.getBottleGas());
        LiquidData oxid = MediumFactory.getLiquidData(constantSystemDTO.getOxidTankFuel());
        LiquidTank liquidTank = new LiquidTank(oxid, pushGas);
        liquidTank.setCA(Double.parseDouble(constantSystemDTO.getOxidTankCA()));
        liquidTank.setV0(Double.parseDouble(constantSystemDTO.getOxidTankV0()));
        liquidTank.setP0(Double.parseDouble(constantSystemDTO.getOxidTankPressure()));
        liquidTank.setQ0(0.0);
        liquidTank.setRho0(pushGas.getRho());
        return liquidTank;
    }

    private LiquidTank getFuelTank(ConstantSystemDTO constantSystemDTO){
        GasData pushGas = MediumFactory.getGasData(constantSystemDTO.getBottleGas());
        LiquidData fuel = MediumFactory.getLiquidData(constantSystemDTO.getFuelTankFuel());
        LiquidTank liquidTank = new LiquidTank(fuel, pushGas);
        liquidTank.setCA(Double.parseDouble(constantSystemDTO.getFuelTankCA()));
        liquidTank.setV0(Double.parseDouble(constantSystemDTO.getFuelTankV0()));
        liquidTank.setP0(Double.parseDouble(constantSystemDTO.getFuelTankPressure()));
        liquidTank.setQ0(0.0);
        liquidTank.setRho0(pushGas.getRho());
        return liquidTank;
    }

    private SimpleLiquidOrifice getOxidOrifice(ConstantSystemDTO constantSystemDTO){
        LiquidData oxid = MediumFactory.getLiquidData(constantSystemDTO.getOxidTankFuel());
        SimpleLiquidOrifice simpleLiquidOrifice = new SimpleLiquidOrifice(oxid);
        simpleLiquidOrifice.setD(Double.parseDouble(constantSystemDTO.getOxidLiquidOrificeD()));
        simpleLiquidOrifice.setPressureCoefficience(Double.parseDouble(constantSystemDTO.getOxidLiquidOrificePc()));
        return simpleLiquidOrifice;
    }

    private SimpleLiquidOrifice getFuelOrifice(ConstantSystemDTO constantSystemDTO){
        LiquidData fuel = MediumFactory.getLiquidData(constantSystemDTO.getFuelTankFuel());
        SimpleLiquidOrifice simpleLiquidOrifice = new SimpleLiquidOrifice(fuel);
        simpleLiquidOrifice.setD(Double.parseDouble(constantSystemDTO.getFuelLiquidOrificeD()));
        simpleLiquidOrifice.setPressureCoefficience(Double.parseDouble(constantSystemDTO.getFuelLiquidOrificePc()));
        return simpleLiquidOrifice;
    }

    private Solenoidvalve getOxidSolenoidValve(ConstantSystemDTO constantSystemDTO, SimpleLiquidOrifice simpleLiquidOrifice){
        LiquidData oxid = MediumFactory.getLiquidData(constantSystemDTO.getOxidTankFuel());
        Solenoidvalve solenoidvalve = new Solenoidvalve(oxid, simpleLiquidOrifice);
        solenoidvalve.setR(Double.parseDouble(constantSystemDTO.getOxidSolenoidR()));
        solenoidvalve.setN(Double.parseDouble(constantSystemDTO.getOxidSolenoidN()));
        solenoidvalve.setU(Double.parseDouble(constantSystemDTO.getOxidSolenoidU()));
        solenoidvalve.setSigma(Double.parseDouble(constantSystemDTO.getOxidSolenoidSigma()));
        solenoidvalve.setSm(Double.parseDouble(constantSystemDTO.getOxidSolenoidSm()));
        solenoidvalve.setK(Double.parseDouble(constantSystemDTO.getOxidSolenoidK()));
        solenoidvalve.setM(Double.parseDouble(constantSystemDTO.getOxidSolenoidM()));
        solenoidvalve.setFf0(Double.parseDouble(constantSystemDTO.getOxidSolenoidF()));
        solenoidvalve.setD(Double.parseDouble(constantSystemDTO.getOxidSolenoidD()));
        solenoidvalve.setXstop(Double.parseDouble(constantSystemDTO.getOxidSolenoidXstop()));
        return solenoidvalve;
    }

    private Solenoidvalve getFuelSolenoidValve(ConstantSystemDTO constantSystemDTO, SimpleLiquidOrifice simpleLiquidOrifice){
        LiquidData fuel = MediumFactory.getLiquidData(constantSystemDTO.getFuelTankFuel());
        Solenoidvalve solenoidvalve = new Solenoidvalve(fuel, simpleLiquidOrifice);
        solenoidvalve.setR(Double.parseDouble(constantSystemDTO.getFuelSolenoidR()));
        solenoidvalve.setN(Double.parseDouble(constantSystemDTO.getFuelSolenoidN()));
        solenoidvalve.setU(Double.parseDouble(constantSystemDTO.getFuelSolenoidU()));
        solenoidvalve.setSigma(Double.parseDouble(constantSystemDTO.getFuelSolenoidSigma()));
        solenoidvalve.setSm(Double.parseDouble(constantSystemDTO.getFuelSolenoidSm()));
        solenoidvalve.setK(Double.parseDouble(constantSystemDTO.getFuelSolenoidK()));
        solenoidvalve.setM(Double.parseDouble(constantSystemDTO.getFuelSolenoidM()));
        solenoidvalve.setFf0(Double.parseDouble(constantSystemDTO.getFuelSolenoidF()));
        solenoidvalve.setD(Double.parseDouble(constantSystemDTO.getFuelSolenoidD()));
        solenoidvalve.setXstop(Double.parseDouble(constantSystemDTO.getFuelSolenoidXstop()));
        return solenoidvalve;
    }

    private CombustionChamber getCombustionChamber(ConstantSystemDTO constantSystemDTO){
        CombustionChamber combustionChamber = new CombustionChamber();
        combustionChamber.setVc(Double.parseDouble(constantSystemDTO.getThrustChamberV()));
        combustionChamber.setTauc(Double.parseDouble(constantSystemDTO.getThrustChamberTauc()));
        combustionChamber.setK(Double.parseDouble(constantSystemDTO.getThrustChamberK()));
        double d = Double.parseDouble(constantSystemDTO.getThrustChamberD());
        combustionChamber.setArea_throat(d*d*0.25*Math.PI);
        combustionChamber.setEps(Double.parseDouble(constantSystemDTO.getThrustChamberEps()));
        combustionChamber.setPa(Double.parseDouble(constantSystemDTO.getThrustChamberPa()));
        return combustionChamber;
    }
}
