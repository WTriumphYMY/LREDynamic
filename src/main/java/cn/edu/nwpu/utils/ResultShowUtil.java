package cn.edu.nwpu.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ResultShowUtil
 * @Author: wkx
 * @Date: 2019/6/30 17:00
 * @Version: v1.0
 * @Description:
 */
public class ResultShowUtil {

    public static void showChart(Map<String, List<Double>> resultMap){
        JFrame jFrame = new JFrame("曲线窗口");
        jFrame.getContentPane().add(new BalsDisplayPanel(resultMap), BorderLayout.CENTER);
        jFrame.setSize(800, 900);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Thread.interrupted();
            }
        });
        jFrame.setVisible(true);
        try {
            Thread.sleep(900000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Map<String, List<Double>> resultMap){
        File resultFile = new File("E:\\ProgramTest\\systemtest.txt");
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(resultFile.toPath());
            String title = "";
            List<String> titleList = new ArrayList<>();
            for (String s : resultMap.keySet()) {
                title += s+"\t";
                titleList.add(s);
            }
            bufferedWriter.write(title);
            bufferedWriter.newLine();
            for (int i = 0; i < resultMap.get("t").size(); i++) {
                String line = "";
                for (String s : titleList) {
                    line += resultMap.get(s).get(i)+"\t";
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
