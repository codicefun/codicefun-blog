package com.codicefun.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

public class MyBeanUtils {

    /**
     * 获取对象属性值为 null 的属性名
     *
     * @param source 目标对象
     * @return 属性名数组
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        ArrayList<String> nullPropertyNames = new ArrayList<>();

        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }

        return nullPropertyNames.toArray(new String[0]);
    }

}
