package com.luke.server.ip.port;

import org.junit.Test;

public class TestIpPortCheck {


    @Test
    public void testCheck(){
        IpPortCheck ipPortCheck = new IpPortCheck() ;
//        ipPortCheck.scanByPort("127.0.0.1",21) ;
        ipPortCheck.scanAllPort("192.168.0.60") ;
    }

}
