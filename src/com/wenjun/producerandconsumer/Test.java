package com.wenjun.producerandconsumer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author RUANWENJUN
 * @Creat 2018-09-25 12:36
 */

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\RUANWENJUN\\Desktop\\User.txt"));
        String line;
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            try {
                int i = Integer.parseInt(split[0]);
                treeMap.put(i,split[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\RUANWENJUN\\Desktop\\User1.txt"));
        for (Map.Entry<Integer,String> entry:treeMap.entrySet()){
            writer.write(entry.getKey()+","+entry.getValue()+"\r\n");
        }
        writer.close();
    }
}
