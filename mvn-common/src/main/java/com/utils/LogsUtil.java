package com.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-11-09 14:24
 **/
public class LogsUtil {

        private static final Logger log= LoggerFactory.getLogger("paperLog");

        public static  Logger getInstance() {
            return log;
        }



}
