package com.cjr.guava.utilities;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import java.util.concurrent.TimeUnit;

public class StopWatchExample {
    public static void main(String[] args) throws InterruptedException {
        process("17554382940");
    }
    private final static Logger LOGGER = Logger.getLogger(StopWatchExample.class);

    private static void process (String orderNo) throws InterruptedException {
        LOGGER.info("start process the order [{"+orderNo +"}]");
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info("The order [{}] process successful and elapsed [{}]"+orderNo+","+ stopwatch.stop().elapsed());
    }
}
