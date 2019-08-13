//package cn.edu.nwpu.domain.components;
//
//import cn.edu.nwpu.domain.GasData.N2GasData;
//import cn.edu.nwpu.domain.component.GasPipeParas;
//import org.junit.Test;
//
//
//public class GasPipeTest {
//
//    @Test
//    public void testGasPipe() throws Exception{
//        GasPipe gasPipe = new GasPipe(new N2GasData());
//        GasPipeParas gasPipeParas = new GasPipeParas();
//        gasPipeParas.setD(0.02);
//        gasPipeParas.setL(1);
//        gasPipeParas.setF(0.035);
//        gasPipe.setAttributes(gasPipeParas);
//        System.out.println(gasPipe.getP(20000000,0.8, 1.2507)/1e6);
//        System.out.println(gasPipe.getQ(0.8, 1.2507, 300.0));
////        double time = 0.0;
////        double h = 0.001;
////        List<Double> p = new ArrayList<>();//压强，Pa
////        List<Double> t = new ArrayList<>();//温度
////        List<Double> mdot = new ArrayList<>();//质量流率，kg/s
////        double[] dp = new double[4];
////        double[] dt = new double[4];
////        double[] dq = new double[4];
////        p.add(1e5);
////        t.add(300.0);
////        mdot.add(0.0);
////        GasPipe gasPipe = new GasPipe(new N2GasData());
////        gasPipe.setAttribute(1, 0.009, 0.035);
////        int j = 0;
////        while (time < 10){
////            dp[0] = gasPipe.getDp(mdot.get(j), t.get(j), 0.8, 325);
////            dq[0] = gasPipe.getDq(t.get(j),p.get(j), mdot.get(j), 0.8, 325, 2.2e7);
////            dt[0] = gasPipe.getDt(t.get(j), p.get(j), dp[0], mdot.get(j), 0.8);
////            for (int i = 1; i < 4; i++){
////                if (i < 3){
////                    dp[i] = gasPipe.getDp(mdot.get(j)+0.5*h*dp[i-1], t.get(j)+0.5*h*dt[i-1], 0.8, 325);
////                    dq[i] = gasPipe.getDq(t.get(j)+0.5*h*dt[i-1],p.get(j)+0.5*h*dp[i-1], mdot.get(j)+0.5*h*dp[i-1], 0.8, 325, 2.2e7);
////                    dt[i] = gasPipe.getDt(t.get(j)+0.5*h*dt[i-1], p.get(j)+0.5*h*dp[i-1], dp[i-1], mdot.get(j)+0.5*h*dp[i-1], 0.8);
////                }else {
////                    dp[i] = gasPipe.getDp(mdot.get(j)+h*dp[i-1], t.get(j)+h*dt[i-1], 0.8, 325);
////                    dq[i] = gasPipe.getDq(t.get(j)+h*dt[i-1],p.get(j)+h*dp[i-1], mdot.get(j)+h*dp[i-1], 0.8, 325, 2.2e7);
////                    dt[i] = gasPipe.getDt(t.get(j)+h*dt[i-1], p.get(j)+h*dp[i-1], dp[i-1], mdot.get(j)+h*dp[i-1], 0.8);
////                }
////            }
////            p.add(p.get(j) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
////            t.add(t.get(j) + h*(dt[0] + 2*dt[1] + 2*dt[2] + dt[3])/6);
////            mdot.add(mdot.get(j) + h*(dq[0] + 2*dq[1] + 2*dq[2] + dq[3])/6);
//////            if (p.get(j+1) <= 1.05e5) break;
////            time += h;
////            j++;
////        }
////        Path resultPath = Paths.get("E:\\ProgramTest\\gaspipe.txt");
////        Files.deleteIfExists(resultPath);
////        BufferedWriter writer = Files.newBufferedWriter(resultPath);
////        writer.write("t"+ "\t" +"p" + "\t" +"q" + "\t" +"T");
////        writer.newLine();
////        for (int i = 0; i < p.size(); i++){
////            writer.write(i*h + "\t" +p.get(i) + "\t" +mdot.get(i) + "\t" +t.get(i));
////            writer.newLine();
////        }
////        writer.close();
//
//    }
//}