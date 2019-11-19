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
        for(int i = 0 ; i < args.length ; i++){
            if("-help".equals(args[i])){
                System.out.println("java -jar lukeftp.jar -u [UserName] -p [PassWord] -port [port] -w [write] -homePath [ftpHomePath] -help [show help] ");
                return ;
            }
        }
        SpringApplication.run(ServerApp.class,args) ;
        logger.info("===============start=================");
        new LukeFtpServer().ftpStart(args);
    }


}
