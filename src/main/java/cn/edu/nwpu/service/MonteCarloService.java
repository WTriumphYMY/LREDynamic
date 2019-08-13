package cn.edu.nwpu.service;

import cn.edu.nwpu.dto.MonteCarloDTO;

/**
 * @InterfaceName MonteCarloService
 * @Author: wkx
 * @Date: 2019/8/11 10:16
 * @Version: v1.0
 * @Description: 蒙特卡洛计算服务类接口
 */
public interface MonteCarloService {

    void monteCalculate(MonteCarloDTO monteCarloDTO);
}
