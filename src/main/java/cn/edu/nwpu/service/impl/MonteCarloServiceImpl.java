package cn.edu.nwpu.service.impl;

import cn.edu.nwpu.algorithm.RandomAlgorithm;
import cn.edu.nwpu.algorithm.StatisticsCal;
import cn.edu.nwpu.dto.ConstantSystemDTO;
import cn.edu.nwpu.dto.MonteCarloDTO;
import cn.edu.nwpu.service.LreSimulationService;
import cn.edu.nwpu.service.MonteCarloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MonteCarloServiceImpl
 * @Author: wkx
 * @Date: 2019/8/11 10:32
 * @Version: v1.0
 * @Description: 蒙特卡洛计算服务实现类
 */
@Service
public class MonteCarloServiceImpl implements MonteCarloService {

    @Autowired
    private LreSimulationService lreSimulationService = new LreSimulationServiceImpl();

    @Override
    public Map<String, List<Double>> monteCalculate(MonteCarloDTO monteCarloDTO) {
        Integer times = Integer.parseInt(monteCarloDTO.getCalculateTimes());
        List<Double> upPGasList = new ArrayList<>();
        List<Double> downPGasList = new ArrayList<>();
        List<Double> upQGasList = new ArrayList<>();
        List<Double> downQGasList = new ArrayList<>();
        List<Double> upPhighReduceList = new ArrayList<>();
        List<Double> downPhighReduceList = new ArrayList<>();
        List<Double> upPlowReduceList = new ArrayList<>();
        List<Double> downPlowReduceList = new ArrayList<>();
        List<Double> upQhighReduceList = new ArrayList<>();
        List<Double> downQhighReduceList = new ArrayList<>();

        List<Double> upQlowReduceList = new ArrayList<>();
        List<Double> downQlowReduceList = new ArrayList<>();
        List<Double> upPLO2TankList = new ArrayList<>();
        List<Double> downPLO2TankList = new ArrayList<>();
        List<Double> upQLO2TankList = new ArrayList<>();
        List<Double> downQLO2TankList = new ArrayList<>();
        List<Double> upPMMHTankList = new ArrayList<>();
        List<Double> downPMMHTankList = new ArrayList<>();
        List<Double> upQMMHTankList = new ArrayList<>();
        List<Double> downQMMHTankList = new ArrayList<>();
        List<Double> upPLO2SoleList = new ArrayList<>();
        List<Double> downPLO2SoleList = new ArrayList<>();
        List<Double> upQLO2SoleList = new ArrayList<>();
        List<Double> downQLO2SoleList = new ArrayList<>();
        List<Double> upPMMHSoleList = new ArrayList<>();
        List<Double> downPMMHSoleList = new ArrayList<>();
        List<Double> upQMMHSoleList = new ArrayList<>();
        List<Double> downQMMHSoleList = new ArrayList<>();
        List<Double> upPCombustionList = new ArrayList<>();
        List<Double> downPCombustionList = new ArrayList<>();
        List<Double> stablePCombustionList = new ArrayList<>();
        List<Double> upQCombustionList = new ArrayList<>();
        List<Double> downQCombustionList = new ArrayList<>();
        List<Double> stableQCombustionList = new ArrayList<>();
        List<Double> upRCombustionList = new ArrayList<>();
        List<Double> downRCombustionList = new ArrayList<>();
        List<Double> upFCombustionList = new ArrayList<>();
        List<Double> downFCombustionList = new ArrayList<>();
        List<Double> upIspList = new ArrayList<>();
        List<Double> downIspList = new ArrayList<>();

        List<Double> stableFCombustionList = new ArrayList<>();
        List<Double> stableIspCombustionList = new ArrayList<>();

        List<Double> statisticsFCombustionList = new ArrayList<>();
        List<Double> statisticsIspCombustionList = new ArrayList<>();

        List<Double> boxFCombustionList = new ArrayList<>();
        List<Double> boxIspCombustionList = new ArrayList<>();
        List<Double> timeList = new ArrayList<>();
        for (int i = 0; i < times; i++){
            ConstantSystemDTO constantSystemDTO = setMonteParas(monteCarloDTO);
            Map<String, List<Double>> resultMap = lreSimulationService.constDualSystemSim(constantSystemDTO);
            stablePCombustionList.add(resultMap.get("p_com").get(resultMap.get("p_com").size()-1));
            stableFCombustionList.add(resultMap.get("f_com").get(resultMap.get("f_com").size()-1));
            stableQCombustionList.add(resultMap.get("q_com").get(resultMap.get("q_com").size()-1));
            stableIspCombustionList.add(resultMap.get("isp").get(resultMap.get("isp").size()-1));

            if (upPGasList.isEmpty()) {
                upPGasList.addAll(resultMap.get("p_g"));
                downPGasList.addAll(resultMap.get("p_g"));
                upQGasList.addAll(resultMap.get("q_g"));
                downQGasList.addAll(resultMap.get("q_g"));
                upPhighReduceList.addAll(resultMap.get("phigh"));
                downPhighReduceList.addAll(resultMap.get("phigh"));
                upPlowReduceList.addAll(resultMap.get("plow"));
                downPlowReduceList.addAll(resultMap.get("plow"));
                upQhighReduceList.addAll(resultMap.get("qhigh"));
                downQhighReduceList.addAll(resultMap.get("qhigh"));
                upQlowReduceList.addAll(resultMap.get("qlow"));
                downQlowReduceList.addAll(resultMap.get("qlow"));
                upPLO2TankList.addAll(resultMap.get("p_lo2"));
                downPLO2TankList.addAll(resultMap.get("p_lo2"));
                upQLO2TankList.addAll(resultMap.get("q_lo2"));
                downQLO2TankList.addAll(resultMap.get("q_lo2"));
                upPMMHTankList.addAll(resultMap.get("p_lmmh"));
                downPMMHTankList.addAll(resultMap.get("p_lmmh"));
                upQMMHTankList.addAll(resultMap.get("q_lmmh"));
                downQMMHTankList.addAll(resultMap.get("q_lmmh"));
                upPLO2SoleList.addAll(resultMap.get("p_so2"));
                downPLO2SoleList.addAll(resultMap.get("p_so2"));
                upQLO2SoleList.addAll(resultMap.get("q_so2"));
                downQLO2SoleList.addAll(resultMap.get("q_so2"));
                upPMMHSoleList.addAll(resultMap.get("p_smmh"));
                downPMMHSoleList.addAll(resultMap.get("p_smmh"));
                upQMMHSoleList.addAll(resultMap.get("q_smmh"));
                downQMMHSoleList.addAll(resultMap.get("q_smmh"));
                upPCombustionList.addAll(resultMap.get("p_com"));
                downPCombustionList.addAll(resultMap.get("p_com"));
                upQCombustionList.addAll(resultMap.get("q_com"));
                downQCombustionList.addAll(resultMap.get("q_com"));
                upRCombustionList.addAll(resultMap.get("r_com"));
                downRCombustionList.addAll(resultMap.get("r_com"));
                upFCombustionList.addAll(resultMap.get("f_com"));
                downFCombustionList.addAll(resultMap.get("f_com"));
                upIspList.addAll(resultMap.get("isp"));
                downIspList.addAll(resultMap.get("isp"));
                timeList.addAll(resultMap.get("t"));
            }else{
                for (int j = 0; j < resultMap.get("p_g").size(); j++) {
                    if (j < upPGasList.size()) {
                        if (resultMap.get("p_g").get(j) > upPGasList.get(j)) {
                            upPGasList.set(j, resultMap.get("p_g").get(j));
                        }
                        if (resultMap.get("p_g").get(j) < downPGasList.get(j)) {
                            downPGasList.set(j, resultMap.get("p_g").get(j));
                        }
                    }else{
                        upPGasList.add(resultMap.get("p_g").get(j));
                        downPGasList.add(resultMap.get("p_g").get(j));
                    }

                    if (j < upQGasList.size()) {
                        if (resultMap.get("q_g").get(j) > upQGasList.get(j)) {
                            upQGasList.set(j, resultMap.get("q_g").get(j));
                        }
                        if (resultMap.get("q_g").get(j) < downQGasList.get(j)) {
                            downQGasList.set(j, resultMap.get("q_g").get(j));
                        }
                    }else{
                        upQGasList.add(resultMap.get("q_g").get(j));
                        downQGasList.add(resultMap.get("q_g").get(j));
                    }

                    if (j < upPhighReduceList.size()) {
                        if (resultMap.get("phigh").get(j) > upPhighReduceList.get(j)) {
                            upPhighReduceList.set(j, resultMap.get("phigh").get(j));
                        }
                        if (resultMap.get("phigh").get(j) < downPhighReduceList.get(j)) {
                            downPhighReduceList.set(j, resultMap.get("phigh").get(j));
                        }
                    }else{
                        upPhighReduceList.add(resultMap.get("phigh").get(j));
                        downPhighReduceList.add(resultMap.get("phigh").get(j));
                    }

                    if (j < upPlowReduceList.size()) {
                        if (resultMap.get("plow").get(j) > upPlowReduceList.get(j)) {
                            upPlowReduceList.set(j, resultMap.get("plow").get(j));
                        }
                        if (resultMap.get("plow").get(j) < downPlowReduceList.get(j)) {
                            downPlowReduceList.set(j, resultMap.get("plow").get(j));
                        }
                    }else{
                        upPlowReduceList.add(resultMap.get("plow").get(j));
                        downPlowReduceList.add(resultMap.get("plow").get(j));
                    }

                    if (j < upQhighReduceList.size()) {
                        if (resultMap.get("qhigh").get(j) > upQhighReduceList.get(j)) {
                            upQhighReduceList.set(j, resultMap.get("qhigh").get(j));
                        }
                        if (resultMap.get("qhigh").get(j) < downQhighReduceList.get(j)) {
                            downQhighReduceList.set(j, resultMap.get("qhigh").get(j));
                        }
                    }else{
                        upQhighReduceList.add(resultMap.get("qhigh").get(j));
                        downQhighReduceList.add(resultMap.get("qhigh").get(j));
                    }

                    if (j < upQlowReduceList.size()) {
                        if (resultMap.get("qlow").get(j) > upQlowReduceList.get(j)) {
                            upQlowReduceList.set(j, resultMap.get("qlow").get(j));
                        }
                        if (resultMap.get("qlow").get(j) < downQlowReduceList.get(j)) {
                            downQlowReduceList.set(j, resultMap.get("qlow").get(j));
                        }
                    }else{
                        upQlowReduceList.add(resultMap.get("qlow").get(j));
                        downQlowReduceList.add(resultMap.get("qlow").get(j));
                    }

                    if (j < upPLO2TankList.size()) {
                        if (resultMap.get("p_lo2").get(j) > upPLO2TankList.get(j)) {
                            upPLO2TankList.set(j, resultMap.get("p_lo2").get(j));
                        }
                        if (resultMap.get("p_lo2").get(j) < downPLO2TankList.get(j)) {
                            downPLO2TankList.set(j, resultMap.get("p_lo2").get(j));
                        }
                    }else{
                        upPLO2TankList.add(resultMap.get("p_lo2").get(j));
                        downPLO2TankList.add(resultMap.get("p_lo2").get(j));
                    }

                    if (j < upQLO2TankList.size()) {
                        if (resultMap.get("q_lo2").get(j) > upQLO2TankList.get(j)) {
                            upQLO2TankList.set(j, resultMap.get("q_lo2").get(j));
                        }
                        if (resultMap.get("q_lo2").get(j) < downQLO2TankList.get(j)) {
                            downQLO2TankList.set(j, resultMap.get("q_lo2").get(j));
                        }
                    }else{
                        upQLO2TankList.add(resultMap.get("q_lo2").get(j));
                        downQLO2TankList.add(resultMap.get("q_lo2").get(j));
                    }

                    if (j < upPMMHTankList.size()) {
                        if (resultMap.get("p_lmmh").get(j) > upPMMHTankList.get(j)) {
                            upPMMHTankList.set(j, resultMap.get("p_lmmh").get(j));
                        }
                        if (resultMap.get("p_lmmh").get(j) < downPMMHTankList.get(j)) {
                            downPMMHTankList.set(j, resultMap.get("p_lmmh").get(j));
                        }
                    }else{
                        upPMMHTankList.add(resultMap.get("p_lmmh").get(j));
                        downPMMHTankList.add(resultMap.get("p_lmmh").get(j));
                    }

                    if (j < upQMMHTankList.size()) {
                        if (resultMap.get("q_lmmh").get(j) > upQMMHTankList.get(j)) {
                            upQMMHTankList.set(j, resultMap.get("q_lmmh").get(j));
                        }
                        if (resultMap.get("q_lmmh").get(j) < downQMMHTankList.get(j)) {
                            downQMMHTankList.set(j, resultMap.get("q_lmmh").get(j));
                        }
                    }else{
                        upQMMHTankList.add(resultMap.get("q_lmmh").get(j));
                        downQMMHTankList.add(resultMap.get("q_lmmh").get(j));
                    }

                    if (j < upPLO2SoleList.size()) {
                        if (resultMap.get("p_so2").get(j) > upPLO2SoleList.get(j)) {
                            upPLO2SoleList.set(j, resultMap.get("p_so2").get(j));
                        }
                        if (resultMap.get("p_so2").get(j) < downPLO2SoleList.get(j)) {
                            downPLO2SoleList.set(j, resultMap.get("p_so2").get(j));
                        }
                    }else{
                        upPLO2SoleList.add(resultMap.get("p_so2").get(j));
                        downPLO2SoleList.add(resultMap.get("p_so2").get(j));
                    }

                    if (j < upQLO2SoleList.size()) {
                        if (resultMap.get("q_so2").get(j) > upQLO2SoleList.get(j)) {
                            upQLO2SoleList.set(j, resultMap.get("q_so2").get(j));
                        }
                        if (resultMap.get("q_so2").get(j) < downQLO2SoleList.get(j)) {
                            downQLO2SoleList.set(j, resultMap.get("q_so2").get(j));
                        }
                    }else{
                        upQLO2SoleList.add(resultMap.get("q_so2").get(j));
                        downQLO2SoleList.add(resultMap.get("q_so2").get(j));
                    }

                    if (j < upPMMHSoleList.size()) {
                        if (resultMap.get("p_smmh").get(j) > upPMMHSoleList.get(j)) {
                            upPMMHSoleList.set(j, resultMap.get("p_smmh").get(j));
                        }
                        if (resultMap.get("p_smmh").get(j) < downPMMHSoleList.get(j)) {
                            downPMMHSoleList.set(j, resultMap.get("p_smmh").get(j));
                        }
                    }else{
                        upPMMHSoleList.add(resultMap.get("p_smmh").get(j));
                        downPMMHSoleList.add(resultMap.get("p_smmh").get(j));
                    }

                    if (j < upQMMHSoleList.size()) {
                        if (resultMap.get("q_smmh").get(j) > upQMMHSoleList.get(j)) {
                            upQMMHSoleList.set(j, resultMap.get("q_smmh").get(j));
                        }
                        if (resultMap.get("q_smmh").get(j) < downQMMHSoleList.get(j)) {
                            downQMMHSoleList.set(j, resultMap.get("q_smmh").get(j));
                        }
                    }else{
                        upQMMHSoleList.add(resultMap.get("q_smmh").get(j));
                        downQMMHSoleList.add(resultMap.get("q_smmh").get(j));
                    }

                    if (j < upPCombustionList.size()) {
                        if (resultMap.get("p_com").get(j) > upPCombustionList.get(j)) {
                            upPCombustionList.set(j, resultMap.get("p_com").get(j));
                        }
                        if (resultMap.get("p_com").get(j) < downPCombustionList.get(j)) {
                            downPCombustionList.set(j, resultMap.get("p_com").get(j));
                        }
                    }else{
                        upPCombustionList.add(resultMap.get("p_com").get(j));
                        downPCombustionList.add(resultMap.get("p_com").get(j));
                    }

                    if (j < upQCombustionList.size()) {
                        if (resultMap.get("q_com").get(j) > upQCombustionList.get(j)) {
                            upQCombustionList.set(j, resultMap.get("q_com").get(j));
                        }
                        if (resultMap.get("q_com").get(j) < downQCombustionList.get(j)) {
                            downQCombustionList.set(j, resultMap.get("q_com").get(j));
                        }
                    }else{
                        upQCombustionList.add(resultMap.get("q_com").get(j));
                        downQCombustionList.add(resultMap.get("q_com").get(j));
                    }

                    if (j < upRCombustionList.size()) {
                        if (resultMap.get("r_com").get(j) > upRCombustionList.get(j)) {
                            upRCombustionList.set(j, resultMap.get("r_com").get(j));
                        }
                        if (resultMap.get("r_com").get(j) < downRCombustionList.get(j)) {
                            downRCombustionList.set(j, resultMap.get("r_com").get(j));
                        }
                    }else{
                        upRCombustionList.add(resultMap.get("r_com").get(j));
                        downRCombustionList.add(resultMap.get("r_com").get(j));
                    }

                    if (j < upFCombustionList.size()) {
                        if (resultMap.get("f_com").get(j) > upFCombustionList.get(j)) {
                            upFCombustionList.set(j, resultMap.get("f_com").get(j));
                        }
                        if (resultMap.get("f_com").get(j) < downFCombustionList.get(j)) {
                            downFCombustionList.set(j, resultMap.get("f_com").get(j));
                        }
                    }else{
                        upFCombustionList.add(resultMap.get("f_com").get(j));
                        downFCombustionList.add(resultMap.get("f_com").get(j));
                    }

                    if (j < upIspList.size()) {
                        if (resultMap.get("isp").get(j) > upIspList.get(j)) {
                            upIspList.set(j, resultMap.get("isp").get(j));
                        }
                        if (resultMap.get("isp").get(j) < downIspList.get(j)) {
                            downIspList.set(j, resultMap.get("isp").get(j));
                        }
                    }else{
                        upIspList.add(resultMap.get("isp").get(j));
                        downIspList.add(resultMap.get("isp").get(j));
                    }

                }
            }
        }
        statisticsFCombustionList.add(StatisticsCal.average(stableFCombustionList));
        statisticsFCombustionList.add(StatisticsCal.variance(stableFCombustionList));
        statisticsFCombustionList.add(StatisticsCal.confidenceIntervalDown(stableFCombustionList));
        statisticsFCombustionList.add(StatisticsCal.confidenceIntervalUp(stableFCombustionList));
        statisticsIspCombustionList.add(StatisticsCal.average(stableIspCombustionList));
        statisticsIspCombustionList.add(StatisticsCal.variance(stableIspCombustionList));
        statisticsIspCombustionList.add(StatisticsCal.confidenceIntervalDown(stableIspCombustionList));
        statisticsIspCombustionList.add(StatisticsCal.confidenceIntervalUp(stableIspCombustionList));

        boxFCombustionList.add(StatisticsCal.min(stableFCombustionList));
        boxFCombustionList.add(StatisticsCal.lowerQuartile(stableFCombustionList));
        boxFCombustionList.add(StatisticsCal.median(stableFCombustionList));
        boxFCombustionList.add(StatisticsCal.upperQuartile(stableFCombustionList));
        boxFCombustionList.add(StatisticsCal.max(stableFCombustionList));
        boxIspCombustionList.add(StatisticsCal.min(stableIspCombustionList));
        boxIspCombustionList.add(StatisticsCal.lowerQuartile(stableIspCombustionList));
        boxIspCombustionList.add(StatisticsCal.median(stableIspCombustionList));
        boxIspCombustionList.add(StatisticsCal.upperQuartile(stableIspCombustionList));
        boxIspCombustionList.add(StatisticsCal.max(stableIspCombustionList));

        Map<String, List<Double>> showMap = new HashMap<>();
        showMap.put("upPGasList",    upPGasList);
        showMap.put("downPGasList",  downPGasList);
        showMap.put("upQGasList",    upQGasList);
        showMap.put("downQGasList",  downQGasList);
        showMap.put("upPhighReduceList",   upPhighReduceList);
        showMap.put("downPhighReduceList", downPhighReduceList);
        showMap.put("upQhighReduceList",   upQhighReduceList);
        showMap.put("downQhighReduceList", downQhighReduceList);
        showMap.put("upPlowReduceList",   upPlowReduceList);
        showMap.put("downPlowReduceList", downPlowReduceList);
        showMap.put("upQlowReduceList" ,upQlowReduceList);
        showMap.put("downQlowReduceList" ,downQlowReduceList);
        showMap.put("upPLO2TankList" ,upPLO2TankList);
        showMap.put("downPLO2TankList" ,downPLO2TankList);
        showMap.put("upQLO2TankList" ,upQLO2TankList);
        showMap.put("downQLO2TankList" ,downQLO2TankList);
        showMap.put("upPMMHTankList" ,upPMMHTankList);
        showMap.put("downPMMHTankList" ,downPMMHTankList);
        showMap.put("upQMMHTankList" ,upQMMHTankList);
        showMap.put("downQMMHTankList" ,downQMMHTankList);
        showMap.put("upPLO2SoleList" ,upPLO2SoleList);
        showMap.put("downPLO2SoleList" ,downPLO2SoleList);
        showMap.put("upQLO2SoleList" ,upQLO2SoleList);
        showMap.put("downQLO2SoleList" ,downQLO2SoleList);
        showMap.put("upPMMHSoleList" ,upPMMHSoleList);
        showMap.put("downPMMHSoleList" ,downPMMHSoleList);
        showMap.put("upQMMHSoleList" ,upQMMHSoleList);
        showMap.put("downQMMHSoleList" ,downQMMHSoleList);
        showMap.put("upPCombustionList" ,upPCombustionList);
        showMap.put("downPCombustionList" ,downPCombustionList);
        showMap.put("upQCombustionList" ,upQCombustionList);
        showMap.put("downQCombustionList" ,downQCombustionList);
        showMap.put("upRCombustionList" ,upRCombustionList);
        showMap.put("downRCombustionList" ,downRCombustionList);
        showMap.put("upFCombustionList" ,upFCombustionList);
        showMap.put("downFCombustionList" ,downFCombustionList);
        showMap.put("upIspList" ,upFCombustionList);
        showMap.put("downIspList" ,downFCombustionList);
        showMap.put("stablePCombustionList", stablePCombustionList);
        showMap.put("stableFCombustionList", stableFCombustionList);
        showMap.put("stableQCombustionList", stableQCombustionList);
        showMap.put("stableIspCombustionList", stableIspCombustionList);
        showMap.put("statisticsFCombustionList", statisticsFCombustionList);
        showMap.put("statisticsIspCombustionList", statisticsIspCombustionList);
        showMap.put("boxFCombustionList", boxFCombustionList);
        showMap.put("boxIspCombustionList", boxIspCombustionList);
        showMap.put("time", timeList);
        return showMap;
    }


    private ConstantSystemDTO setMonteParas(MonteCarloDTO monteCarloDTO){
        ConstantSystemDTO constantSystemDTO = monteCarloDTO.getConstantSystemDTO();
        if (monteCarloDTO.getBottleCAmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getBottleCAmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getBottleCAsigma());
            constantSystemDTO.setBottleCA(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getBottleCAup() != null){
            double up = Double.parseDouble(monteCarloDTO.getBottleCAup());
            double down = Double.parseDouble(monteCarloDTO.getBottleCAdown());
            constantSystemDTO.setBottleCA(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getBottleCAlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getBottleCAlamda());
            constantSystemDTO.setBottleCA(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getBottleVolmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getBottleVolmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getBottleVolsigma());
            constantSystemDTO.setBottleVol(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getBottleVolup() != null){
            double up = Double.parseDouble(monteCarloDTO.getBottleVolup());
            double down = Double.parseDouble(monteCarloDTO.getBottleVoldown());
            constantSystemDTO.setBottleVol(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getBottleVollamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getBottleVollamda());
            constantSystemDTO.setBottleVol(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getBottlePressuremiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getBottlePressuremiu());
            double sigma = Double.parseDouble(monteCarloDTO.getBottlePressuresigma());
            constantSystemDTO.setBottlePressure(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getBottlePressureup() != null){
            double up = Double.parseDouble(monteCarloDTO.getBottlePressureup());
            double down = Double.parseDouble(monteCarloDTO.getBottlePressuredown());
            constantSystemDTO.setBottlePressure(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getBottlePressurelamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getBottlePressurelamda());
            constantSystemDTO.setBottlePressure(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getBottleTemperaturemiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getBottleTemperaturemiu());
            double sigma = Double.parseDouble(monteCarloDTO.getBottleTemperaturesigma());
            constantSystemDTO.setBottleTemperature(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getBottleTemperatureup() != null){
            double up = Double.parseDouble(monteCarloDTO.getBottleTemperatureup());
            double down = Double.parseDouble(monteCarloDTO.getBottleTemperaturedown());
            constantSystemDTO.setBottleTemperature(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getBottleTemperaturelamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getBottleTemperaturelamda());
            constantSystemDTO.setBottleTemperature(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceCmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceCmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceCsigma());
            constantSystemDTO.setReduceC(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceCup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceCup());
            double down = Double.parseDouble(monteCarloDTO.getReduceCdown());
            constantSystemDTO.setReduceC(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceClamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceClamda());
            constantSystemDTO.setReduceC(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceKmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceKmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceKsigma());
            constantSystemDTO.setReduceK(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceKup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceKup());
            double down = Double.parseDouble(monteCarloDTO.getReduceKdown());
            constantSystemDTO.setReduceK(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceKlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceKlamda());
            constantSystemDTO.setReduceK(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceFmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceFmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceFsigma());
            constantSystemDTO.setReduceF(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceFup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceFup());
            double down = Double.parseDouble(monteCarloDTO.getReduceFdown());
            constantSystemDTO.setReduceF(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceFlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceFlamda());
            constantSystemDTO.setReduceF(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceMmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceMmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceMsigma());
            constantSystemDTO.setReduceM(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceMup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceMup());
            double down = Double.parseDouble(monteCarloDTO.getReduceMdown());
            constantSystemDTO.setReduceM(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceMlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceMlamda());
            constantSystemDTO.setReduceM(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceAmbmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceAmbmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceAmbsigma());
            constantSystemDTO.setReduceAmb(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceAmbup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceAmbup());
            double down = Double.parseDouble(monteCarloDTO.getReduceAmbdown());
            constantSystemDTO.setReduceAmb(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceAmblamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceAmblamda());
            constantSystemDTO.setReduceAmb(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceAvcmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceAvcmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceAvcsigma());
            constantSystemDTO.setReduceAvc(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceAvcup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceAvcup());
            double down = Double.parseDouble(monteCarloDTO.getReduceAvcdown());
            constantSystemDTO.setReduceAvc(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceAvclamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceAvclamda());
            constantSystemDTO.setReduceAvc(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceV1miu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceV1miu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceV1sigma());
            constantSystemDTO.setReduceV1(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceV1up() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceV1up());
            double down = Double.parseDouble(monteCarloDTO.getReduceV1down());
            constantSystemDTO.setReduceV1(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceV1lamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceV1lamda());
            constantSystemDTO.setReduceV1(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceV2miu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceV2miu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceV2sigma());
            constantSystemDTO.setReduceV2(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceV2up() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceV2up());
            double down = Double.parseDouble(monteCarloDTO.getReduceV2down());
            constantSystemDTO.setReduceV2(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceV2lamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceV2lamda());
            constantSystemDTO.setReduceV2(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getReduceXstopmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getReduceXstopmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getReduceXstopsigma());
            constantSystemDTO.setReduceXstop(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getReduceXstopup() != null){
            double up = Double.parseDouble(monteCarloDTO.getReduceXstopup());
            double down = Double.parseDouble(monteCarloDTO.getReduceXstopdown());
            constantSystemDTO.setReduceXstop(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getReduceXstoplamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getReduceXstoplamda());
            constantSystemDTO.setReduceXstop(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelTankCAmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelTankCAmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelTankCAsigma());
            constantSystemDTO.setFuelTankCA(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelTankCAup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelTankCAup());
            double down = Double.parseDouble(monteCarloDTO.getFuelTankCAdown());
            constantSystemDTO.setFuelTankCA(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelTankCAlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelTankCAlamda());
            constantSystemDTO.setFuelTankCA(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelTankV0miu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelTankV0miu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelTankV0sigma());
            constantSystemDTO.setFuelTankV0(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelTankV0up() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelTankV0up());
            double down = Double.parseDouble(monteCarloDTO.getFuelTankV0down());
            constantSystemDTO.setFuelTankV0(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelTankV0lamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelTankV0lamda());
            constantSystemDTO.setFuelTankV0(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelTankPressuremiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelTankPressuremiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelTankPressuresigma());
            constantSystemDTO.setFuelTankPressure(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelTankPressureup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelTankPressureup());
            double down = Double.parseDouble(monteCarloDTO.getFuelTankPressuredown());
            constantSystemDTO.setFuelTankPressure(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelTankPressurelamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelTankPressurelamda());
            constantSystemDTO.setFuelTankPressure(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidTankCAmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidTankCAmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidTankCAsigma());
            constantSystemDTO.setOxidTankCA(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidTankCAup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidTankCAup());
            double down = Double.parseDouble(monteCarloDTO.getOxidTankCAdown());
            constantSystemDTO.setOxidTankCA(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidTankCAlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidTankCAlamda());
            constantSystemDTO.setOxidTankCA(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidTankV0miu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidTankV0miu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidTankV0sigma());
            constantSystemDTO.setOxidTankV0(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidTankV0up() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidTankV0up());
            double down = Double.parseDouble(monteCarloDTO.getOxidTankV0down());
            constantSystemDTO.setOxidTankV0(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidTankV0lamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidTankV0lamda());
            constantSystemDTO.setOxidTankV0(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidTankPressuremiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidTankPressuremiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidTankPressuresigma());
            constantSystemDTO.setOxidTankPressure(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidTankPressureup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidTankPressureup());
            double down = Double.parseDouble(monteCarloDTO.getOxidTankPressuredown());
            constantSystemDTO.setOxidTankPressure(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidTankPressurelamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidTankPressurelamda());
            constantSystemDTO.setOxidTankPressure(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidRmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidRmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidRsigma());
            constantSystemDTO.setFuelSolenoidR(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidRup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidRup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidRdown());
            constantSystemDTO.setFuelSolenoidR(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidRlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidRlamda());
            constantSystemDTO.setFuelSolenoidR(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidNmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidNmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidNsigma());
            constantSystemDTO.setFuelSolenoidN(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidNup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidNup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidNdown());
            constantSystemDTO.setFuelSolenoidN(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidNlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidNlamda());
            constantSystemDTO.setFuelSolenoidN(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidUmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidUmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidUsigma());
            constantSystemDTO.setFuelSolenoidU(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidUup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidUup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidUdown());
            constantSystemDTO.setFuelSolenoidU(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidUlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidUlamda());
            constantSystemDTO.setFuelSolenoidU(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidSigmamiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidSigmamiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidSigmasigma());
            constantSystemDTO.setFuelSolenoidSigma(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidSigmaup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidSigmaup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidSigmadown());
            constantSystemDTO.setFuelSolenoidSigma(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidSigmalamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidSigmalamda());
            constantSystemDTO.setFuelSolenoidSigma(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidSmmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidSmmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidSmsigma());
            constantSystemDTO.setFuelSolenoidSm(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidSmup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidSmup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidSmdown());
            constantSystemDTO.setFuelSolenoidSm(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidSmlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidSmlamda());
            constantSystemDTO.setFuelSolenoidSm(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidKmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidKmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidKsigma());
            constantSystemDTO.setFuelSolenoidK(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidKup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidKup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidKdown());
            constantSystemDTO.setFuelSolenoidK(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidKlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidKlamda());
            constantSystemDTO.setFuelSolenoidK(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidMmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidMmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidMsigma());
            constantSystemDTO.setFuelSolenoidM(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidMup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidMup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidMdown());
            constantSystemDTO.setFuelSolenoidM(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidMlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidMlamda());
            constantSystemDTO.setFuelSolenoidM(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidFmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidFmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidFsigma());
            constantSystemDTO.setFuelSolenoidF(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidFup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidFup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidFdown());
            constantSystemDTO.setFuelSolenoidF(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidFlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidFlamda());
            constantSystemDTO.setFuelSolenoidF(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidDmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidDmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidDsigma());
            constantSystemDTO.setFuelSolenoidD(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidDup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidDup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidDdown());
            constantSystemDTO.setFuelSolenoidD(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidDlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidDlamda());
            constantSystemDTO.setFuelSolenoidD(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelSolenoidXstopmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelSolenoidXstopmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelSolenoidXstopsigma());
            constantSystemDTO.setFuelSolenoidXstop(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelSolenoidXstopup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelSolenoidXstopup());
            double down = Double.parseDouble(monteCarloDTO.getFuelSolenoidXstopdown());
            constantSystemDTO.setFuelSolenoidXstop(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelSolenoidXstoplamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelSolenoidXstoplamda());
            constantSystemDTO.setFuelSolenoidXstop(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidRmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidRmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidRsigma());
            constantSystemDTO.setOxidSolenoidR(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidRup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidRup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidRdown());
            constantSystemDTO.setOxidSolenoidR(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidRlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidRlamda());
            constantSystemDTO.setOxidSolenoidR(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidNmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidNmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidNsigma());
            constantSystemDTO.setOxidSolenoidN(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidNup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidNup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidNdown());
            constantSystemDTO.setOxidSolenoidN(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidNlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidNlamda());
            constantSystemDTO.setOxidSolenoidN(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidUmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidUmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidUsigma());
            constantSystemDTO.setOxidSolenoidU(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidUup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidUup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidUdown());
            constantSystemDTO.setOxidSolenoidU(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidUlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidUlamda());
            constantSystemDTO.setOxidSolenoidU(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidSigmamiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidSigmamiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidSigmasigma());
            constantSystemDTO.setOxidSolenoidSigma(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidSigmaup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidSigmaup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidSigmadown());
            constantSystemDTO.setOxidSolenoidSigma(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidSigmalamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidSigmalamda());
            constantSystemDTO.setOxidSolenoidSigma(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidSmmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidSmmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidSmsigma());
            constantSystemDTO.setOxidSolenoidSm(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidSmup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidSmup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidSmdown());
            constantSystemDTO.setOxidSolenoidSm(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidSmlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidSmlamda());
            constantSystemDTO.setOxidSolenoidSm(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidKmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidKmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidKsigma());
            constantSystemDTO.setOxidSolenoidK(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidKup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidKup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidKdown());
            constantSystemDTO.setOxidSolenoidK(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidKlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidKlamda());
            constantSystemDTO.setOxidSolenoidK(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidMmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidMmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidMsigma());
            constantSystemDTO.setOxidSolenoidM(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidMup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidMup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidMdown());
            constantSystemDTO.setOxidSolenoidM(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidMlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidMlamda());
            constantSystemDTO.setOxidSolenoidM(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidFmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidFmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidFsigma());
            constantSystemDTO.setOxidSolenoidF(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidFup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidFup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidFdown());
            constantSystemDTO.setOxidSolenoidF(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidFlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidFlamda());
            constantSystemDTO.setOxidSolenoidF(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidDmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidDmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidDsigma());
            constantSystemDTO.setOxidSolenoidD(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidDup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidDup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidDdown());
            constantSystemDTO.setOxidSolenoidD(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidDlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidDlamda());
            constantSystemDTO.setOxidSolenoidD(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidSolenoidXstopmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidSolenoidXstopmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidSolenoidXstopsigma());
            constantSystemDTO.setOxidSolenoidXstop(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidSolenoidXstopup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidSolenoidXstopup());
            double down = Double.parseDouble(monteCarloDTO.getOxidSolenoidXstopdown());
            constantSystemDTO.setOxidSolenoidXstop(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidSolenoidXstoplamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidSolenoidXstoplamda());
            constantSystemDTO.setOxidSolenoidXstop(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelLiquidOrificeDmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificeDmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificeDsigma());
            constantSystemDTO.setFuelLiquidOrificeD(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelLiquidOrificeDup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificeDup());
            double down = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificeDdown());
            constantSystemDTO.setFuelLiquidOrificeD(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelLiquidOrificeDlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificeDlamda());
            constantSystemDTO.setFuelLiquidOrificeD(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getFuelLiquidOrificePcmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificePcmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificePcsigma());
            constantSystemDTO.setFuelLiquidOrificePc(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getFuelLiquidOrificePcup() != null){
            double up = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificePcup());
            double down = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificePcdown());
            constantSystemDTO.setFuelLiquidOrificePc(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getFuelLiquidOrificePclamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getFuelLiquidOrificePclamda());
            constantSystemDTO.setFuelLiquidOrificePc(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidLiquidOrificeDmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificeDmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificeDsigma());
            constantSystemDTO.setOxidLiquidOrificeD(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidLiquidOrificeDup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificeDup());
            double down = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificeDdown());
            constantSystemDTO.setOxidLiquidOrificeD(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidLiquidOrificeDlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificeDlamda());
            constantSystemDTO.setOxidLiquidOrificeD(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getOxidLiquidOrificePcmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificePcmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificePcsigma());
            constantSystemDTO.setOxidLiquidOrificePc(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getOxidLiquidOrificePcup() != null){
            double up = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificePcup());
            double down = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificePcdown());
            constantSystemDTO.setOxidLiquidOrificePc(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getOxidLiquidOrificePclamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getOxidLiquidOrificePclamda());
            constantSystemDTO.setOxidLiquidOrificePc(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberVmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberVmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberVsigma());
            constantSystemDTO.setThrustChamberV(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberVup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberVup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberVdown());
            constantSystemDTO.setThrustChamberV(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberVlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberVlamda());
            constantSystemDTO.setThrustChamberV(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberTaucmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberTaucmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberTaucsigma());
            constantSystemDTO.setThrustChamberTauc(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberTaucup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberTaucup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberTaucdown());
            constantSystemDTO.setThrustChamberTauc(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberTauclamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberTauclamda());
            constantSystemDTO.setThrustChamberTauc(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberKmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberKmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberKsigma());
            constantSystemDTO.setThrustChamberK(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberKup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberKup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberKdown());
            constantSystemDTO.setThrustChamberK(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberKlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberKlamda());
            constantSystemDTO.setThrustChamberK(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberDmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberDmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberDsigma());
            constantSystemDTO.setThrustChamberD(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberDup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberDup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberDdown());
            constantSystemDTO.setThrustChamberD(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberDlamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberDlamda());
            constantSystemDTO.setThrustChamberD(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberPamiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberPamiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberPasigma());
            constantSystemDTO.setThrustChamberPa(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberPaup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberPaup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberPadown());
            constantSystemDTO.setThrustChamberPa(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberPalamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberPalamda());
            constantSystemDTO.setThrustChamberPa(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }

        if (monteCarloDTO.getThrustChamberEpsmiu() !=null){
            double miu = Double.parseDouble(monteCarloDTO.getThrustChamberEpsmiu());
            double sigma = Double.parseDouble(monteCarloDTO.getThrustChamberEpssigma());
            constantSystemDTO.setThrustChamberEps(String.valueOf(RandomAlgorithm.generateGaussianRandom(miu,sigma)));
        }
        if (monteCarloDTO.getThrustChamberEpsup() != null){
            double up = Double.parseDouble(monteCarloDTO.getThrustChamberEpsup());
            double down = Double.parseDouble(monteCarloDTO.getThrustChamberEpsdown());
            constantSystemDTO.setThrustChamberEps(String.valueOf(RandomAlgorithm.generateUniformRandom(up,down)));
        }
        if (monteCarloDTO.getThrustChamberEpslamda() != null){
            double lamda = Double.parseDouble(monteCarloDTO.getThrustChamberEpslamda());
            constantSystemDTO.setThrustChamberEps(String.valueOf(RandomAlgorithm.generateExponentialRandom(lamda)));
        }
        return constantSystemDTO;
    }
}
