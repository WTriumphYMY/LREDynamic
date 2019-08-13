package cn.edu.nwpu.service.impl;

import cn.edu.nwpu.dto.ConstantSystemDTO;
import cn.edu.nwpu.dto.MonteCarloDTO;
import cn.edu.nwpu.service.LreSimulationService;
import cn.edu.nwpu.service.MonteCarloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private LreSimulationService lreSimulationService;

    @Override
    public void monteCalculate(MonteCarloDTO monteCarloDTO) {
        ConstantSystemDTO constantSystemDTO = new ConstantSystemDTO();
        //TODO: 这里给根据仿真次数和1均值方差constantSystemDTO赋值
        lreSimulationService.constDualSystemSim(constantSystemDTO);
    }
}
