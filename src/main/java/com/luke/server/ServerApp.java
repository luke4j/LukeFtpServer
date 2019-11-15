package com.luke.server;

import com.luke.server.ftp.LukeFtpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp {

    private static Logger logger = LoggerFactory.getLogger(ServerApp.class) ;

    public static void main(String[] args) {
        logger.info("===============start=================");
        SpringApplication.run(ServerApp.class,args) ;
        logger.info("===============start=================");
        new LukeFtpServer().ftpStart(args);
    }


}
