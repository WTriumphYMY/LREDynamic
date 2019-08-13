//package cn.edu.nwpu.domain.components;
//
//
//import cn.edu.nwpu.domain.components.GasBottle;
//import cn.edu.nwpu.domain.GasData.N2GasData;
//import cn.edu.nwpu.domain.component.GasBottleParas;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GasBottleTest {
//
//    @Test
//    public void testGasBottle() throws Exception{
//        double time = 0.0;
//        double h = 0.001;
//        List<Double> p = new ArrayList<>();//压强，Pa
//        List<Double> rho = new ArrayList<>();//密度
//        List<Double> mdot = new ArrayList<>();//质量流率，kg/s
//        List<Double> temp = new ArrayList<>();//质量流率，kg/s
//        GasBottle gasBottle = new GasBottle(new N2GasData());
//        GasBottleParas gasBottleParas = new GasBottleParas();
//        gasBottleParas.setVc(0.05);
//        gasBottleParas.setCA(3.5e-4);
//        gasBottle.setAttributes(gasBottleParas);
//        p.add(2.2e7);
//        rho.add(0.1785);
//        mdot.add(0.0);
//        temp.add(300.0);
//        double[] dp = new double[4];
//        double[] drho = new double[4];
//        double[] dtemp = new double[4];
//        int j = 0;
//        while (time < 10){
//            dp[0] = gasBottle.getDp(rho.get(j), p.get(j), mdot.get(j));
//            drho[0] = gasBottle.getDrho(mdot.get(j));
//            dtemp[0] = gasBottle.getDtemp(p.get(j), temp.get(j), dp[0]);
//            for (int i = 1; i < 4; i++){
//                if (i < 3){
//                    dp[i] = gasBottle.getDp(rho.get(j) + 0.5*h*drho[i-1],
//                            p.get(j) + 0.5*h*dp[i-1],
//                            gasBottle.getQ(p.get(j) + 0.5*h*dp[i-1], rho.get(j) + 0.5*h*drho[i-1],1e5));
//                    drho[i] = gasBottle.getDrho(gasBottle.getQ(p.get(j) + 0.5*h*dp[i-1], rho.get(j) + 0.5*h*drho[i-1],1e5));
//                    dtemp[i] = gasBottle.getDtemp(p.get(j) + 0.5*h*dp[i-1], temp.get(j) + 0.5*h*dtemp[i-1], dp[i-1]);
//                }else {
//                    dp[i] = gasBottle.getDp(rho.get(j) + h*drho[i-1],
//                            p.get(j) + h*dp[i-1],
//                            gasBottle.getQ(p.get(j) + h*dp[i-1], rho.get(j) + h*drho[i-1],1e5));
//                    drho[i] = gasBottle.getDrho(gasBottle.getQ(p.get(j) + h*dp[i-1], rho.get(j) + h*drho[i-1],1e5));
//                    dtemp[i] = gasBottle.getDtemp(p.get(j) + h*dp[i-1], temp.get(j) + h*dtemp[i-1], dp[i-1]);
//                }
//            }
//            p.add(p.get(j) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
//            rho.add(rho.get(j) + h*(drho[0] + 2*drho[1] + 2*drho[2] + drho[3])/6);
//            mdot.add(gasBottle.getQ(p.get(j+1), rho.get(j+1), 1e5));
//            temp.add(temp.get(j) + h*(dtemp[0] + 2*dtemp[1] + 2*dtemp[2] + dtemp[3])/6);
//            if (p.get(j+1) <= 1.05e5) break;
//            time += h;
//            j++;
//        }
//        Path resultPath = Paths.get("E:\\ProgramTest\\gasbottle.txt");
//        Files.deleteIfExists(resultPath);
//        BufferedWriter writer = Files.newBufferedWriter(resultPath);
//        writer.write("t"+ "\t" +"p" + "\t" +"q" + "\t" +"rho" + "\t" +"temp");
//        writer.newLine();
//        for (int i = 0; i < p.size(); i++){
//            writer.write(i*h + "\t" +p.get(i) + "\t" +mdot.get(i) + "\t" +rho.get(i) + "\t" +temp.get(i));
//            writer.newLine();
//        }
//        writer.close();
//    }
//}