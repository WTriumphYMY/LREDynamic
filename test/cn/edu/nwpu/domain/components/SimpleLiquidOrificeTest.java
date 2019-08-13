//package cn.edu.nwpu.domain.components;
//
//
//import cn.edu.nwpu.domain.LiquidData.LO2Data;
//import cn.edu.nwpu.domain.component.SimpleLiquidOrificeParas;
//import org.junit.Test;
//
//public class SimpleLiquidOrificeTest {
//
//    @Test
//    public void testSimpleLiquidOrifice(){
//        SimpleLiquidOrifice simpleLiquidOrifice = new SimpleLiquidOrifice(new LO2Data());
//        SimpleLiquidOrificeParas simpleLiquidOrificeParas = new SimpleLiquidOrificeParas();
//        simpleLiquidOrificeParas.setPressureCoefficience(0.9997);
//        simpleLiquidOrificeParas.setD(0.005);
//        simpleLiquidOrifice.setAttributes(simpleLiquidOrificeParas);
//        System.out.println(simpleLiquidOrifice.getP(3.78416e6)/1e6);
//        System.out.println(simpleLiquidOrifice.getQ(0.0553, 3.78416e6));
//    }
//}