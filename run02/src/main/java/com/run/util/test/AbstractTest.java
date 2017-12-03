package com.run.util.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: AbstractTest 
 * @Description: TODO
 * @author: lizaibiao
 * @date: 2016年7月31日 下午8:39:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:context-servlet.xml" })
public abstract class AbstractTest {

}

