package cn.lkl.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static InetAddress getInetAddress(){  
    	  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            System.out.println("unknown host!");  
        }  
        return null;  
  
    } 
    
    public static String getHostIp(){  
    	InetAddress netAddress = getInetAddress();
    	if(null == netAddress){  
            return null;  
        }  
        String ip = netAddress.getHostAddress(); //get the ip address 
        netAddress.getAddress();
        return ip;  
    }  
    
    public static byte[] getIpByte(){  
    	InetAddress netAddress = getInetAddress();
    	if(null == netAddress){  
            return null;  
        }  
        return netAddress.getAddress();
    }  
}
