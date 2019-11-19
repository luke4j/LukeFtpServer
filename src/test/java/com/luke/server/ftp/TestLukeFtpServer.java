package com.luke.server.ftp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestLukeFtpServer {

    @Test
    public void run(){
        LukeFtpServer lukeFtpServer = new LukeFtpServer() ;
        List<String> ftpParam = new ArrayList<>() ;
        ftpParam.add("-homePath") ;
        ftpParam.add("\\") ;
        String[] args = new String[ftpParam.size()] ;
        ftpParam.toArray(args) ;
        lukeFtpServer.ftpStart(args) ;
    }
}
