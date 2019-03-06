package com.brs.idm.api;


import java.util.Random;

/**
 * @author tiny lin
 * @date 2019/2/21
 */

public class GenerateId {

    public static String generate(){
        return "njbrs"+new Random().nextInt(1000000)+1;
    }
    public static void main(String[]args){
       String id = GenerateId.generate();
       System.out.println(id);
    }
}
