package base.log.other;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by Doing on 2016/4/5.
 */
public class TestLog4j_ {

    private static final Logger logger = Logger.getLogger(TestLog4j_.class);

    @Test
    public void testLog4j(){
        logger.debug("debug");
        logger.info("------ info");
        logger.warn("------ ------ warn");
        logger.error("------ ------ ------ error");
    }
}
