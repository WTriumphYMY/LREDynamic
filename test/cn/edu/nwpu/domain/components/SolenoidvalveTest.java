//package cn.edu.nwpu.domain.components;
//
//
//import cn.edu.nwpu.domain.LiquidData.LO2Data;
//import cn.edu.nwpu.domain.component.SolenoidValveParas;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SolenoidvalveTest {
//
//    @Test
//    public void testSolenoidvalve() throws Exception{
//        double time = 0.0;
//        double h = 0.001;
//        Solenoidvalve solenoidvalve = new Solenoidvalve(new LO2Data());
//        SolenoidValveParas solenoidValveParas = new SolenoidValveParas();
//        solenoidValveParas.setR(23.3);
//        solenoidValveParas.setN(1700);
//        solenoidValveParas.setU(28.0);
//        solenoidValveParas.setSigma(1.3);
//        solenoidValveParas.setSm(0.0002);
//        solenoidValveParas.setK(2800);
//        solenoidValveParas.setM(0.021);
//        solenoidValveParas.setFf0(8.68);
//        solenoidValveParas.setD(0.0035);
//        solenoidValveParas.setXstop(0.0004);
//        solenoidvalve.setAttributes(solenoidValveParas);
//        List<Double> phi = new ArrayList<>();
//        List<Double> psi = new ArrayList<>();
//        List<Double> v = new ArrayList<>();
//        List<Double> x = new ArrayList<>();
//        List<Double> p = new ArrayList<>();
//        phi.add(0.0);
//        psi.add(0.0);
//        v.add(0.0);
//        x.add(0.0);
//        p.add(101325.0);
//
//        double[] dphi = new double[4];
//        double[] dpsi = new double[4];
//        double[] dv = new double[4];
//        double[] dx = new double[4];
//        int j = 0;
//        boolean on = false;
//        while (time < 5){
//            if (time >= 0.5){
//                on = true;
//            }
//            dphi[0] = solenoidvalve.getDphi(phi.get(j), solenoidvalve.getRtotal(x.get(j), on));
//            dpsi[0] = solenoidvalve.getDpsi(dphi[0]);
//            dv[0] = solenoidvalve.getDv(psi.get(j), x.get(j), 4.83e6);
//            dx[0] = solenoidvalve.getDx(v.get(j), x.get(j));
//            for (int i = 1; i < 4; i++){
//                if (i < 3){
//                    dphi[i] = solenoidvalve.getDphi(phi.get(j)+0.5*h*dphi[i-1], solenoidvalve.getRtotal(x.get(j)+0.5*h*dx[i-1], on));
//                    dpsi[i] = solenoidvalve.getDpsi(dphi[i-1]);
//                    dv[i] = solenoidvalve.getDv(psi.get(j)+0.5*h*dpsi[i-1], x.get(j)+0.5*h*dx[i-1], 4.83e6);
//                    dx[i] = solenoidvalve.getDx(v.get(j)+0.5*h*dv[i-1], x.get(j)+0.5*h*dx[i-1]);
//                }else {
//                    dphi[i] = solenoidvalve.getDphi(phi.get(j)+h*dphi[i-1], solenoidvalve.getRtotal(x.get(j)+h*dx[i-1], on));
//                    dpsi[i] = solenoidvalve.getDpsi(dphi[i-1]);
//                    dv[i] = solenoidvalve.getDv(psi.get(j)+h*dpsi[i-1], x.get(j)+h*dx[i-1], 4.83e6);
//                    dx[i] = solenoidvalve.getDx(v.get(j)+h*dv[i-1], x.get(j)+h*dx[i-1]);
//                }
//            }
//            phi.add(phi.get(j) + h*(dphi[0] + 2*dphi[1] + 2*dphi[2] + dphi[3])/6);
//            psi.add(psi.get(j) + h*(dpsi[0] + 2*dpsi[1] + 2*dpsi[2] + dpsi[3])/6);
//            x.add(x.get(j) + h*(dx[0] + 2*dx[1] + 2*dx[2] + dx[3])/6);
//            v.add(solenoidvalve.getDx(v.get(j) + h*(dv[0] + 2*dv[1] + 2*dv[2] + dv[3])/6, x.get(j+1)));
//            p.add(solenoidvalve.getP(4.83e6));
//            time += h;
//            j++;
//        }
//        Path resultPath = Paths.get("E:\\ProgramTest\\Solenoidvalve.txt");
//        Files.deleteIfExists(resultPath);
//        BufferedWriter writer = Files.newBufferedWriter(resultPath);
//        writer.write("t"+ "\t" +"phi" + "\t" +"psi" + "\t" +"v" + "\t" +"x");
//        writer.newLine();
//        for (int i = 0; i < phi.size(); i++){
//            writer.write(i*h + "\t" +phi.get(i) + "\t" +psi.get(i) + "\t" +v.get(i) + "\t" +x.get(i));
//            writer.newLine();
//        }
//        writer.close();
//
//    }
//}