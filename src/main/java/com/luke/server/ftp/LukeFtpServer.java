package com.luke.server.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LukeFtpServer {

    private static Logger logger = LoggerFactory.getLogger(LukeFtpServer.class) ;
    private static FtpServer ftpServer = null;

    String userName = "anonymous" ;
    String passWord = null ;
    int port = 21 ;
    Boolean bWtire = true ;

    String homePath = "c:/" ;

    private Boolean  setParams(String[] args){
        System.out.println("args is "+args);
        for(int i = 0 ;i<args.length ;i++){
            switch (args[i]){
                case "-u":
                    this.userName = args[i+1] ;
                    break ;
                case "-p" :
                    this.passWord = args[i+1] ;
                    break ;
                case "-port" :
                    this.port = Integer.parseInt(args[i+1]) ;
                    break ;
                case "-w" :
                    this.bWtire = Boolean.parseBoolean(args[i+1]) ;
                    break ;
                case "-homePath" :
                    this.homePath = args[i+1] ;
                    break ;
                default:
                    System.out.println("java -jar lukeftp.jar -u [UserName] -p [PassWord] -port [port] -w [write] -homePath [ftpHomePath] -help [show help] ");
                    return false ;
            }
        }
        return true ;
    }


    public FtpServer ftpStart(String[] args){
        if(!this.setParams(args)) {
            return null  ;
        }
        try {
            // 用于创建server
            FtpServerFactory serverFactory = new FtpServerFactory();
            // 配置信息，如设监听的端口，设置IP过滤器
            ListenerFactory listenerFactory = new ListenerFactory();
            // 设置端口
            listenerFactory.setPort(this.port);
            // 如果五分钟还没任何操作，关闭连接
            // listenerFactory.setIdleTimeout(5*60*1000);

            // 设置匿名用户 但是对于稳健权限是只读

            // 设置用户名密码
            BaseUser user = new BaseUser();
            user.setName(this.userName);
            if(this.passWord!=null){
                user.setPassword(this.passWord);
            }
            // 设置PC端登录后可访问的根目录
            user.setHomeDirectory(this.homePath);
            // 授予用户写权限
            List<Authority> authorities = new ArrayList<Authority>();
            if(this.bWtire){
                authorities.add(new WritePermission());
                user.setAuthorities(authorities);
            }
            serverFactory.getUserManager().save(user);
            // 创建并监听网络
            serverFactory.addListener("default", listenerFactory.createListener());
            // 创建服务
            ftpServer = serverFactory.createServer();

            // 开启服务
            ftpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftpServer ;
    }

}
