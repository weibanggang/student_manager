package com.bm.student_manager;

import org.springframework.context.ApplicationContext;

public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
    if(null== com.bm.student_manager.SpringContextUtil.applicationContext)
        com.bm.student_manager.SpringContextUtil.applicationContext=applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
