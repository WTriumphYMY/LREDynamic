//package cn.edu.nwpu.domain.components;
//
//import cn.edu.nwpu.algorithm.SplineInterpolation;
//import cn.edu.nwpu.domain.component.CombustionChamberParas;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CombustionChamberTest {
//
//    @Test
//    public void testCombustionChamber() throws Exception{
//        double time = 0.0;
//        double h = 0.0001;
//        CombustionChamber combustionChamber = new CombustionChamber();
////        combustionChamber.setFittingParas(-1.872e4, 2.395e5, -1.057e6, 1.698e6, 5.148e5);
//        CombustionChamberParas combustionChamberParas = new CombustionChamberParas();
//        combustionChamberParas.setVc(7.8289e-6);
//        combustionChamberParas.setTauc(0.0);
//        combustionChamberParas.setK(1.25);
//        combustionChamberParas.setArea_throat(0.007*0.007*0.25*Math.PI);
//        combustionChamberParas.setEps(25.72);
//        combustionChamberParas.setPa(101325.0);
//        combustionChamber.setAttributes(combustionChamberParas);
//        List<Double> p = new ArrayList<>();
//        List<Double> r = new ArrayList<>();
//        List<Double> f = new ArrayList<>();
//        List<Double> qout = new ArrayList<>();
//        p.add(101325.0);
//        r.add(1.0);
//        f.add(0.0);
//        qout.add(0.0);
//        double[] dp = new double[4];
//        double[] dr = new double[4];
//        int j = 0;
//        while (time < 5){
//            dr[0] = combustionChamber.getDr(r.get(j), p.get(j), 0.0388625, 0.0189286,
//                    combustionChamber.getRT(r.get(j)));
//            dp[0] = combustionChamber.getDp(0.0388625, 0.0189286, p.get(j), r.get(j), combustionChamber.getRT(r.get(j)),
//                    combustionChamber.getDRT(r.get(j)));;
//            for (int i = 1; i < 4; i++){
//                if (i < 3){
//                    dr[i] = combustionChamber.getDr(r.get(j)+0.5*h*dr[i-1], p.get(j)+0.5*h*dp[i-1],
//                            0.0388625, 0.0189286, combustionChamber.getRT(r.get(j)+0.5*h*dr[i-1]));
//                    dp[i] = combustionChamber.getDp(0.0388625, 0.0189286, p.get(j)+0.5*h*dp[i-1], r.get(j)+0.5*h*dr[i-1],
//                            combustionChamber.getRT(r.get(j)+0.5*h*dr[i-1]), combustionChamber.getDRT(r.get(j)+0.5*h*dr[i-1]));
//                }else {
//                    dr[i] = combustionChamber.getDr(r.get(j)+h*dr[i-1], p.get(j)+h*dp[i-1],
//                            0.0388625, 0.0189286, combustionChamber.getRT(r.get(j)+h*dr[i-1]));
//                    dp[i] = combustionChamber.getDp(0.0388625, 0.0189286, p.get(j)+h*dp[i-1],r.get(j)+h*dr[i-1],
//                            combustionChamber.getRT(r.get(j)+h*dr[i-1]), combustionChamber.getDRT(r.get(j)+h*dr[i-1]));
//                }
//            }
//            p.add(p.get(j) + h*(dp[0] + 2*dp[1] + 2*dp[2] + dp[3])/6);
//            r.add(r.get(j) + h*(dr[0] + 2*dr[1] + 2*dr[2] + dr[3])/6);
//            f.add(combustionChamber.getForce(p.get(j+1)));
//            qout.add(combustionChamber.getQout(p.get(j+1), combustionChamber.getRT(r.get(j+1))));
//            time += h;
//            j++;
//        }
//        Path resultPath = Paths.get("E:\\ProgramTest\\CombustionChamber.txt");
//        Files.deleteIfExists(resultPath);
//        BufferedWriter writer = Files.newBufferedWriter(resultPath);
//        writer.write("t" + "\t" +"p" + "\t" +"r" + "\t" +"f" + "\t" +"q");
//        writer.newLine();
//        for (int i = 0; i < p.size(); i++){
//            writer.write(i*h + "\t" +p.get(i) + "\t" +r.get(i) + "\t" +f.get(i) + "\t" +qout.get(i));
//            writer.newLine();
//        }
//        writer.close();
//    }
//
//    @Test
//    public void testSpline(){
//        Double[] x = {0.1,0.2,0.3,0.4,0.5,0.7,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,7.0,10.0,15.0,20.0,30.0,40.0,50.0};
//        Double[] y = {751803.742485807,810774.283143972,865959.449191469,954842.465448960,1110824.45243604,1328184.49584619,1452299.88106342,1383505.27127263,1256818.52609915,1150992.16700430,1065248.61504909,993945.511064594,932723.066566264,878867.067304603,830685.936220580,674908.396124423,516017.422317614,363527.145546757,277428.753712470,183132.244825323,131808.457865899,103297.217111947};
//        SplineInterpolation splineInterpolation = new SplineInterpolation(x, y);
//        System.out.println(splineInterpolation.spline(7.91));
//    }
//}