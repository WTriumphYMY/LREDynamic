//package cn.edu.nwpu.domain.components;
//
//import cn.edu.nwpu.domain.components.ReducingValve;
//import cn.edu.nwpu.domain.GasData.N2GasData;
//import cn.edu.nwpu.domain.component.ReduceValveParas;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReducingValveTest {
//    @Test
//    public void testReducingValve() throws Exception{
//        ReducingValve reducingValve = new ReducingValve(new N2GasData());
//        ReduceValveParas reduceValveParas = new ReduceValveParas();
//        reduceValveParas.setC(5010);
//        reduceValveParas.setK(390700);
//        reduceValveParas.setF(3516.3);
//        reduceValveParas.setM(0.166);
//        reduceValveParas.setAmb(0.00090792);
//        reduceValveParas.setAvc(1.9635e-5);
//        reduceValveParas.setV1(6.15e-6);
//        reduceValveParas.setV2(6.15e-6);
//        reduceValveParas.setXstop(0.0002);
//        reducingValve.setAttributes(reduceValveParas);
//        double time = 0.0;
//        double h = 0.001;
//        List<Double> x = new ArrayList<>();
//        List<Double> u = new ArrayList<>();
//        List<Double> p1 = new ArrayList<>();
//        List<Double> p2 = new ArrayList<>();
//        List<Double> temp1 = new ArrayList<>();
//        List<Double> temp2 = new ArrayList<>();
//        List<Double> q1 = new ArrayList<>();
//        List<Double> q2 = new ArrayList<>();
//        x.add(0.0);
//        u.add(0.0);
//        p1.add(101325.0);
//        p2.add(101325.0);
//        temp1.add(300.0);
//        temp2.add(300.0);
//        q1.add(0.0);
//        q2.add(0.0);
//        double[] dx = new double[4];
//        double[] du = new double[4];
//        double[] dp1 = new double[4];
//        double[] dp2 = new double[4];
//        double[] dtemp1 = new double[4];
//        double[] dtemp2 = new double[4];
//        int j = 0;
//        while (time < 5){
//            dx[0] = reducingValve.getDx(u.get(j), x.get(j));
//            du[0] = reducingValve.getDu(p1.get(j), p2.get(j), x.get(j), dx[0]);
//            dp1[0] = reducingValve.getDp1(temp1.get(j), q1.get(j), p1.get(j), dx[0], 0.01, 300);
//            dp2[0] = reducingValve.getDp2(q1.get(j), temp1.get(j), q2.get(j), temp2.get(j), p2.get(j), dx[0]);
//            dtemp1[0] = reducingValve.getDt1(temp1.get(j), q1.get(j), p1.get(j), dp1[0], 0.01, dx[0]);
//            dtemp2[0] = reducingValve.getDt2(q1.get(j), q2.get(j), temp2.get(j), p2.get(j), dp2[0], dx[0]);
//            for (int i = 1; i < 4; i++){
//                if (i < 3){
//                    dx[i] = reducingValve.getDx(u.get(j)+0.5*h*du[i-1], x.get(j)+0.5*h*dx[i-1]);
//                    du[i] = reducingValve.getDu(p1.get(j)+0.5*h*dp1[i-1], p2.get(j)+0.5*h*dp2[i-1], x.get(j)+0.5*h*dx[i-1],dx[i-1]);
//                    dp1[i] = reducingValve.getDp1(temp1.get(j)+0.5*h*dtemp1[i-1],
//                            reducingValve.getQ(p1.get(j)+0.5*h*dp1[i-1], p2.get(j)+0.5*h*dp2[i-1], temp1.get(j)+0.5*h*dtemp1[i-1]),
//                            p1.get(j)+0.5*h*dp1[i-1], dx[i-1],0.01, 300);
//                    dp2[i] = reducingValve.getDp2(reducingValve.getQ(p1.get(j)+0.5*h*dp1[i-1], p2.get(j)+0.5*h*dp2[i-1], temp1.get(j)+0.5*h*dtemp1[i-1]),
//                            temp1.get(j)+0.5*h*dtemp1[i-1],
//                            reducingValve.getQ(p2.get(j)+0.5*h*dp1[i-1], 101325.0, temp2.get(j)+0.5*h*dtemp2[i-1]),
//                            temp2.get(j)+0.5*h*dtemp2[i-1], p2.get(j)+0.5*h*dp2[i-1], dx[i-1]);
//                    dtemp1[i] = reducingValve.getDt1(temp1.get(j)+0.5*h*dtemp1[i-1],
//                            reducingValve.getQ(p1.get(j)+0.5*h*dp1[i-1], p2.get(j)+0.5*h*dp2[i-1], temp1.get(j)+0.5*h*dtemp1[i-1]),
//                            p1.get(j)+0.5*h*dp1[i-1], dp1[i-1], 0.01, dx[0]);
//                    dtemp2[i] = reducingValve.getDt2(reducingValve.getQ(p1.get(j)+0.5*h*dp1[i-1], p2.get(j)+0.5*h*dp2[i-1], temp1.get(j)+0.5*h*dtemp1[i-1]),
//                            reducingValve.getQ(p2.get(j)+0.5*h*dp2[i-1], 101325.0, temp2.get(j)+0.5*h*dtemp2[i-1]),
//                            temp2.get(j)+0.5*h*dtemp2[i-1], p2.get(j)+0.5*h*dp2[i-1], dp2[i-1], dx[i-1]);
//                }else {
//                    dx[i] = reducingValve.getDx(u.get(j)+h*du[i-1],x.get(j)+h*dx[i-1]);
//                    du[i] = reducingValve.getDu(p1.get(j)+h*dp1[i-1], p2.get(j)+h*dp2[i-1], x.get(j)+h*dx[i-1], dx[i-1]);
//                    dp1[i] = reducingValve.getDp1(temp1.get(j)+h*dtemp1[i-1],
//                            reducingValve.getQ(p1.get(j)+h*dp1[i-1], p2.get(j)+h*dp2[i-1], temp1.get(j)+h*dtemp1[i-1]),
//                            p1.get(j)+h*dp1[i-1], dx[i-1],0.01, 300);
//                    dp2[i] = reducingValve.getDp2(reducingValve.getQ(p1.get(j)+h*dp1[i-1], p2.get(j)+h*dp2[i-1], temp1.get(j)+h*dtemp1[i-1]),
//                            temp1.get(j)+h*dtemp1[i-1],
//                            reducingValve.getQ(p2.get(j)+h*dp2[i-1], 101325.0, temp2.get(j)+h*dtemp2[i-1]),
//                            temp2.get(j)+h*dtemp2[i-1], p2.get(j)+h*dp2[i-1], dx[i-1]);
//                    dtemp1[i] = reducingValve.getDt1(temp1.get(j)+h*dtemp1[i-1],
//                            reducingValve.getQ(p1.get(j)+h*dp1[i-1], p2.get(j)+h*dp2[i-1], temp1.get(j)+h*dtemp1[i-1]),
//                            p1.get(j)+h*dp1[i-1], dp1[i-1], 0.01, dx[0]);
//                    dtemp2[i] = reducingValve.getDt2(reducingValve.getQ(p1.get(j)+h*dp1[i-1], p2.get(j)+h*dp2[i-1], temp1.get(j)+h*dtemp1[i-1]),
//                            reducingValve.getQ(p2.get(j)+h*dp2[i-1], 101325.0, temp2.get(j)+h*dtemp2[i-1]),
//                            temp2.get(j)+h*dtemp2[i-1], p2.get(j)+h*dp2[i-1], dp2[i-1], dx[i-1]);
//                }
//            }
//            x.add(x.get(j) + h*(dx[0] + 2*dx[1] + 2*dx[2] + dx[3])/6);
//            u.add(u.get(j) + h*(du[0] + 2*du[1] + 2*du[2] + du[3])/6);
//            p1.add(p1.get(j) + h*(dp1[0] + 2*dp1[1] + 2*dp1[2] + dp1[3])/6);
//            p2.add(p2.get(j) + h*(dp2[0] + 2*dp2[1] + 2*dp2[2] + dp2[3])/6);
//            temp1.add(temp1.get(j) + h*(dtemp1[0] + 2*dtemp1[1] + 2*dtemp1[2] + dtemp1[3])/6);
//            temp2.add(temp2.get(j) + h*(dtemp2[0] + 2*dtemp2[1] + 2*dtemp2[2] + dtemp2[3])/6);
//            q1.add(reducingValve.getQ(p1.get(j+1), p2.get(j+1), temp1.get(j+1)));
//            q2.add(reducingValve.getQ(p2.get(j+1), 101325.0, temp2.get(j+1)));
//            time += h;
//            j++;
//        }
//        Path resultPath = Paths.get("E:\\ProgramTest\\reducevalve.txt");
//        Files.deleteIfExists(resultPath);
//        BufferedWriter writer = Files.newBufferedWriter(resultPath);
//        writer.write("t"+ "\t" +"p1" + "\t" +"p2" + "\t" +"temp1" + "\t" +"temp2" + "\t" +"q1" + "\t" +"q2" + "\t" +"x" + "\t" +"u");
//        writer.newLine();
//        for (int i = 0; i < p1.size(); i++){
//            writer.write(i*h + "\t" +p1.get(i) + "\t" +p2.get(i) + "\t" +temp1.get(i) + "\t" +temp2.get(i)
//                    + "\t" +q1.get(i) + "\t" +q2.get(i) + "\t" +x.get(i) + "\t" +u.get(i));
//            writer.newLine();
//        }
//        writer.close();
//    }
//}