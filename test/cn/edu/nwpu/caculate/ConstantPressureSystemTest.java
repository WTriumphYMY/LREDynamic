//package cn.edu.nwpu.caculate;
//
//import cn.edu.nwpu.domain.component.*;
//import org.junit.Test;
//
//
//public class ConstantPressureSystemTest {
//
//    @Test
//    public void testConstantPressureSystem(){
//        GasBottleParas gasBottleParas = new GasBottleParas(0.05,3.5e-4,0.1785,2.2e7,0.0,300.0);
//        GasPipeParas gasPipeParas = new GasPipeParas(1,0.02,0.035);
//        ReduceValveParas reduceValveParas = new ReduceValveParas(5010,390700,3516.3,0.166,0.00090792,1.9635e-5,6.15e-6,6.15e-6,0.0002,
//        0.0,0.0,101325.0,101325.0,300.0,300.0,0.0,0.0);
//        LiquidTankParas liquidTankParas = new LiquidTankParas(0.00011,2.663*1e-5,3864500.0,1.2507,0.16,0.00011);
//        LiquidPipeParas liquidPipeParas = new LiquidPipeParas(0.008);
//        SimpleLiquidOrificeParas simpleLiquidOrificeParas = new SimpleLiquidOrificeParas(0.9997,0.005);
//        SolenoidValveParas solenoidValveParas = new SolenoidValveParas(23.3,1700,28.0,1.3,0.0002,2800,0.021,8.68,0.0035,0.00040,0,0.0,0.0,0.0);
//        CombustionChamberParas combustionChamberParas = new CombustionChamberParas(7.8289e-6,0.0,1.25,0.007*0.007*0.25*Math.PI,101325.0,25.72,101325.0,1.0,0.0,0.0);
//
//        ConstantPressureSystem constantPressureSystem = new ConstantPressureSystem();
//        constantPressureSystem.setAttributes(gasBottleParas, gasPipeParas,
//                reduceValveParas, liquidTankParas,
//                liquidPipeParas, simpleLiquidOrificeParas,
//                solenoidValveParas, combustionChamberParas);
//        constantPressureSystem.execute();
//    }
//
//
//
//}