package cn.edu.nwpu.controller;

import cn.edu.nwpu.dto.ConstantSystemDTO;
import cn.edu.nwpu.dto.MonteCarloDTO;
import cn.edu.nwpu.service.LreSimulationService;
import cn.edu.nwpu.service.MonteCarloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientController
 * @Author: wkx
 * @Date: 2019/7/20 09:44
 * @Version: v1.0
 * @Description: 客户端Controller对外暴露接口
 */
@RestController
public class ClientController {

    @Autowired
    private LreSimulationService lreSimulationService;
    @Autowired
    private MonteCarloService monteCarloService;

    @PostMapping("constDualSystemSim")
    public Map<String, List<Double>> constDualSystemSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.constDualSystemSim(constantSystemDTO);
    }

    @PostMapping("gasBottleSim")
    public Map<String, List<Double>> gasBottleSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.gasBottleSim(constantSystemDTO);
    }

    @PostMapping("reduceValveSim")
    public Map<String, List<Double>> reduceValveSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.reduceValveSim(constantSystemDTO);
    }

    @PostMapping("liquidTankSim")
    public Map<String, List<Double>> liquidTankSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.liquidTankSim(constantSystemDTO);
    }

    @PostMapping("solenoidSim")
    public Map<String, List<Double>> solenoidSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.solenoidSim(constantSystemDTO);
    }

    @PostMapping("thrustChamberSim")
    public Map<String, List<Double>> thrustChamberSim(@RequestBody ConstantSystemDTO constantSystemDTO){
        return lreSimulationService.thrustChamberSim(constantSystemDTO);
    }

    @PostMapping("monteCarloSim")
    public Map<String, List<Double>> monteCarloSim(@RequestBody MonteCarloDTO monteCarloDTO){
        return monteCarloService.monteCalculate(monteCarloDTO);
    }
}
