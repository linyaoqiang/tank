package com.giant.main;

import com.giant.model.GameInformation;
import com.giant.model.LevelInformation;
import com.giant.model.map.LevelGameMap;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*String str = "[{507,745,0}{567,745,0}{627,745,0}{507,805,0}{567,805,6}{627,805,0}{210,105,0}{210,165,0}{210,225,0}{210,285,0}{210,345,0}{210,405,0}{210,525,0}{210,585,0}{870,115,0}{870,175,0}{870,235,0}{870,295,0}{870,355,0}{870,415,0}{870,525,0}{870,585,0}{690,115,1}{690,175,1}{690,235,1}{690,295,1}{690,355,1}{690,415,1}{690,525,0}{690,585,0}{420,115,1}{420,175,1}{420,235,1}{420,295,1}{420,355,1}{420,415,1}{420,525,0}{420,585,0}{480,205,0}{630,205,0}{480,355,0}{630,355,0}{480,285,1}{540,285,1}{600,285,1}{660,285,5}{660,315,5}{550,525,0}{550,585,0}]";
        String regex = "\\{\\d*\\,\\d*\\,\\d\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        while (matcher.find()){
            String[] array=matcher.group().replace("{","").replace("}","").split(",");
            System.out.println(Arrays.toString(array));
        }*/
        
        
        /*boolean flag=Pattern.matches("\\[("+regex+")*\\]",str);
        System.out.println(flag);*/

        //testLoadLV();
        //testLoadTmp();
        printAbout();

    }

    public static void testLoadLV() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("level/lv_1.lv"));
        System.out.println(properties.getProperty("map"));
    }

    public static void testLoadTmp() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("info/game.tmp"));
        Object object = in.readObject();
        System.out.println(object);
        object = in.readObject();
        System.out.println(object);
        object = in.readObject();
        LevelGameMap levelGameMap = (LevelGameMap) object;
        System.out.println(levelGameMap.getTankHome().getTankHomeBlock().getImage());
        System.out.println(object);
        int x = in.read();

        System.out.println(x);
    }

    public static void printAbout(){
        String[] array=GameInformation.getInstance().getAbouts();
        System.out.println(Arrays.toString(array));
    }
}
