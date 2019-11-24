package cn.edu.nwpu.service;

import cn.edu.nwpu.dto.MonteCarloDTO;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName MonteCarloService
 * @Author: wkx
 * @Date: 2019/8/11 10:16
 * @Version: v1.0
 * @Description: 蒙特卡洛计算服务类接口
 */
public interface MonteCarloService {

    Map<String, List<Double>> monteCalculate(MonteCarloDTO monteCarloDTO);
}
