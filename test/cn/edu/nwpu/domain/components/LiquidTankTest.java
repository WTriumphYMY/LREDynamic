//package cn.edu.nwpu.domain.components;
//
//
//import cn.edu.nwpu.domain.components.LiquidTank;
//import cn.edu.nwpu.domain.GasData.GasData;
//import cn.edu.nwpu.domain.GasData.N2GasData;
//import cn.edu.nwpu.domain.LiquidData.LO2Data;
//import cn.edu.nwpu.domain.LiquidData.LiquidData;
//import cn.edu.nwpu.domain.component.LiquidTankParas;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LiquidTankTest {
//
//    @Test
//    public void testLiquidTank() throws Exception{
//        LiquidData liquidData = new LO2Data();
//        GasData gasData = new N2GasData();
//        LiquidTank liquidTank = new LiquidTank(liquidData, gasData);
//        LiquidTankParas liquidTankParas = new LiquidTankParas();
//        liquidTankParas.setCA(2.663*1e-5);
//        liquidTankParas.setV0(0.00011);
//        liquidTank.setAttributes(liquidTankParas);
//        double time = 0.0;
//        double h = 0.001;
//        List<Double> p = new ArrayList<>();//压强，Pa
//        List<Double> rho = new ArrayList<>();//密度
//        List<Double> mdot = new ArrayList<>();//质量流率，kg/s
//        List<Double> v = new ArrayList<>();//质量流率，kg/s
//        p.add(3864500.0);
//        rho.add(gasData.getRho());
//        mdot.add(0.16);
//        v.add(0.00011);
//        double[] dp = new double[4];
//        double[] drho = new double[4];
//        double[] dv = new double[4];
//        int j = 0;
//        while (time < 10){
//            dv[0] = liquidTank.getDv(liquidTank.getQ(p.get(j), 3848150.0));
//            dp[0] = liquidTank.getDp(p.get(j), v.get(j), rho.get(j), dv[0], 0.005);
//            drho[0] = liquidTank.getDrho(rho.get(j), v.get(j), dv[0], 0.005);
//            for (int i = 1; i < 4; i++){
//                if (i < 3){
//                    dv[i] = liquidTank.getDv(liquidTank.getQ(p.get(j) + 0.5*h*dp[i-1], 3848150.0));
//                    dp[i] = liquidTank.getDp(p.get(j) + 0.5*h*dp[i-1], v.get(j) + 0.5*h*dv[i-1], rho.get(j) + 0.5*h*drho[i-1], dv[i-1], 0.005);
//                    drho[i] = liquidTank.getDrho(rho.get(j) + 0.5*h*drho[i-1], v.get(j) + 0.5*h*dv[i-1], dv[i-1], 0.005);
//                }else {
//                    dv[i] = liquidTank.getDv(liquidTank.getQ(p.get(j) + h*dp[i-1], 3848150.0));
//                    dp[i] = liquidTank.getDp(p.get(j) + h*dp[i-1], v.get(j) + h*dv[i-1], rho.get(j) + h*drho[i-1], dv[i-1], 0.005);
//                    drho[i] = liquidTank.getDrho(rho.get(j) + h*drho[i-1], v.get(j) + h*dv[i-1], dv[i-1], 0.005);
//                }
//            }
//            p.add(p.get(j) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
//            rho.add(rho.get(j) + h*(drho[0] + 2*drho[1] + 2*drho[2] + drho[3])/6);
//            mdot.add(liquidTank.getQ(p.get(j+1), 3848150.0));
//            v.add(v.get(j) + h*(dv[0] + 2*dv[1] + 2*dv[2] + dv[3])/6);
//            if (p.get(j+1) <= 1.05e5) break;
//            time += h;
//            j++;
//        }
//        Path resultPath = Paths.get("E:\\ProgramTest\\LiquidTank.txt");
//        Files.deleteIfExists(resultPath);
//        BufferedWriter writer = Files.newBufferedWriter(resultPath);
//        writer.write("t"+ "\t" +"p" + "\t" +"q" + "\t" +"rho" + "\t" +"V");
//        writer.newLine();
//        for (int i = 0; i < p.size(); i++){
//            writer.write(i*h + "\t" +p.get(i) + "\t" +mdot.get(i) + "\t" +rho.get(i) + "\t" +v.get(i));
//            writer.newLine();
//        }
//        writer.close();
//    }
//
//}