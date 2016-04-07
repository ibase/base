package base.log.other;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by base on 2016/4/5.
 */
public class TestLogback_ {

    private static final Logger logger = LoggerFactory.getLogger(TestLogback_.class);

    @Test
    public void testLogback(){
        logger.debug("debug");
        logger.info("------ info");
        logger.warn("------ ------ warn");
        logger.error("------ ------ ------ error");
    }
}
