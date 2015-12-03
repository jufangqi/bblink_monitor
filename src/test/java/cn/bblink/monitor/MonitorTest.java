package cn.bblink.monitor;

import cn.bblink.monitor.util.DaoTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-bblink-monitor-beans.xml" })
public class MonitorTest {
    @Resource
    private DaoTemplate daoTemplate;
    @Test
    public void testInsert(){

    }
}
