/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.corana.executor;

import main.corana.enums.Variation;

import java.io.File;

public class Testing {

    private static void exeFiles(File inpFile) throws Exception {
        if (inpFile.isDirectory()) {
            System.out.println("Directory: " + inpFile.getName());
            File[] files = inpFile.listFiles();
            for (File file : files) {
                exeFiles(file);
            }
        } else {
            String f = inpFile.getPath();
            String name = inpFile.getName();
            //Logs.initLog("./results/" + name + ".log");
            Corana.inpFile = name;
            //Corana.outFile = "./results/" + name;
            Executor.execute(Variation.M0, f);
            //Logs.endLog();
        }
    }

    public static void main(String[] args) {
        File dir = new File("/home/va/git/corana-x/binary-samples/samples/348b61d57cccdb7f71af3849e0b24eb1$");
        long startTime = System.nanoTime();
        try {
            exeFiles(dir);
        } catch (Exception e) {
            //System.out.println(e);
        }
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
