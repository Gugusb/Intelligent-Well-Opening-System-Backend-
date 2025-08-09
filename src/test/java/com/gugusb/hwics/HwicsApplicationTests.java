package com.gugusb.hwics;

import com.gugusb.hwics.conn.ClientConnectManagerRunner;
import com.gugusb.hwics.service.OPCUAReaderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HwicsApplicationTests {

    @Test
    void contextLoads() throws Exception {
        OPCUAReaderService example = new OPCUAReaderService();

        new ClientConnectManagerRunner(example, true).run();
    }

}
