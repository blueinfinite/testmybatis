package com.blueinfinite;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanTest implements InitializingBean {

    public BeanTest() {
        System.out.println("BeanTest");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanTest.afterPropertiesSet");
    }
}
