package com.brs.idm.common.util;







import com.brs.idm.common.annotation.Logical;

import java.util.HashSet;
import java.util.List;


/**
 * @className StrUtil
 * @description 可以不用
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
public class StrUtil {


    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static boolean isNUll(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }


    /**
     * List 去除重复元素
     * @param list
     * @return
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


    public static Boolean matchPermission(String[]permmisionArr, String[]userPermissionArr, Logical logical){
        int count =0;
        for(int i=0;i<permmisionArr.length;i++){
            for(int j=0;j<userPermissionArr.length;j++){
                if(userPermissionArr[j].equals(permmisionArr[i])){
                    ++count;
                }
            }
        }
        System.out.println("权限匹配个数-------------------------------------->"+count);
        if(logical ==Logical.AND){
            return (count == permmisionArr.length)?true:false;
        }
        if(logical == Logical.OR){
            return(count>0)?true:false;
        }
        return false;
    }

}

