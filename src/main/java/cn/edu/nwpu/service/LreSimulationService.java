package cn.edu.nwpu.service;

import cn.edu.nwpu.dto.*;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ConstDualSystemService
 * @Author: wkx
 * @Date: 2019/7/20 10:04
 * @Version: v1.0
 * @Description: 液发仿真接口
 */
public interface LreSimulationService {


    /**
     * 双组元挤压式系统仿真
     * @param constantSystemDTO 系统参数数据传输类
     * @return
     */
    Map<String, List<Double>> constDualSystemSim(ConstantSystemDTO constantSystemDTO);

    /**
     *
     * @param constantSystemDTO
     * @return 气瓶仿真
     */
    Map<String, List<Double>> gasBottleSim(ConstantSystemDTO constantSystemDTO);

    /**
     *
     * @param constantSystemDTO
     * @return 减压阀仿真
     */
    Map<String, List<Double>> reduceValveSim(ConstantSystemDTO constantSystemDTO);

    /**
     *
     * @param constantSystemDTO
     * @return 液体贮箱仿真
     */
    Map<String, List<Double>> liquidTankSim(ConstantSystemDTO constantSystemDTO);

    /**
     *
     * @param constantSystemDTO
     * @return 电磁阀仿真
     */
    Map<String, List<Double>> solenoidSim(ConstantSystemDTO constantSystemDTO);

    /**
     *
     * @param constantSystemDTO
     * @return 推力室仿真
     */
    Map<String, List<Double>> thrustChamberSim(ConstantSystemDTO constantSystemDTO);
}
