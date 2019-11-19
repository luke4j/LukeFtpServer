package com.luke.server.ip.port;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Component
public class IpPortCheck {

//    https://blog.csdn.net/qq_41247433/article/details/79434202

    private static Logger logger = LoggerFactory.getLogger(IpPortCheck.class) ;

    /**
     * 检查IP或IP段的端口是否开放
     * @param ip
     * @return
     */
    public String check(String ip){
        int pointNumber = 0 ;
        StringBuffer info = new StringBuffer( );
        for(int i = 0 ; i<ip.length();i++){
            if(ip.charAt(i)=='.')
                pointNumber++ ;
        }
        if(pointNumber==2||pointNumber==3){
            //指定IP
            if(pointNumber==3){
            }else{
                //指定IP段
            }
        }else{
            info.append("您输入的IP["+ip+"]异常") ;
            logger.info(info.toString());
            return info.toString() ;
        }
        return info.toString() ;
    }

    public String scanAllPort(String ip){
        StringBuffer sb = new StringBuffer( );
        Map<String ,String> run_map = new HashMap<>(10) ;
        for(int i = 100 ;i<500;i++){
            Thread t = new Thread(new ThreadScan(ip,i));
            logger.debug(t.getName()+"=="+t.getId()+"====="+t.getState());
            t.run() ;
            logger.debug(t.getName()+"=="+t.getId()+"====="+t.getState());


        }
        return sb.toString() ;
    }


    class ThreadScan implements Runnable{
        String ip ;
        int port ;

        public ThreadScan(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {
            String info = scanByPort(ip,port) ;
            if(info!=null)
            logger.info("thread info ==>> "+info);
        }
    }


    public String scanByPort(String ip,int port){
        Socket socket = null ;
        try{
            socket = new Socket() ;
            socket.connect(new InetSocketAddress(ip,port));
        }catch (Exception e){
            try{
                if(socket!=null&&socket.isConnected()){
                    socket.close();
                }
            }catch (Exception e1){

            }
        }
        if(socket.isConnected()){
            logger.info("====================>>"+ip+":"+port+" is open");
            return ip+":"+port+" is open \n" ;
        }
        return null;
    }

}
