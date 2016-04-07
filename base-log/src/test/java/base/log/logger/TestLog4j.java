package base.log.logger;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by base on 2016/4/5.
 */
public class TestLog4j {

    private static final Logger logger = Logger.getLogger(TestLog4j.class);

    @Test
    public void testLog4j(){
        logger.debug("debug");
        logger.info("------ info");
        logger.warn("------ ------ warn");
        logger.error("------ ------ ------ error");
    }
}
