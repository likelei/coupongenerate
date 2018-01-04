package cn.lkl.coupon.generate;

import cn.lkl.util.IpUtil;
/**
 * ÓÅ»ÝÈ¯Éú³É
 * @author lkl
 *
 */
public class Coupon {
	final static char[] AVAILABLECHAR = {'2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	private static SnowflakeIdWorker idWorker ;
	
	static {
		int  dataindex = 0;
		String indexStr  = System.getProperty("coupon.data.index");
		System.out.println("aaaa"+indexStr);
		if(indexStr!=null && !indexStr.trim().equals("")){
			dataindex = Integer.valueOf(indexStr);
			if(dataindex>3 || dataindex<0){
				dataindex = 0 ;
			}
		}
		byte[] ipByte = IpUtil.getIpByte();
		idWorker = new SnowflakeIdWorker(ipByte[3], dataindex);
	}
	
	public static String generate(){
		int step = 5 ;
		String result = "";
		long id = idWorker.nextId();
		String valStr  = Long.toBinaryString(id);
		if(valStr.length()<64){
			for(int i=64-valStr.length();i>0;i--){
				valStr = "0" + valStr ;
			}
		}
		int index = 0;
		for(int i=0;i<12;i++){
			String subStr = valStr.substring(i*step, (i+1)*step);
			index = Integer.valueOf(subStr,2);
			result += AVAILABLECHAR[index];
		}
		
		String subStr = valStr.substring(60, 64);
		index = Integer.valueOf(subStr,2);
		result += AVAILABLECHAR[index];
		
		return result;
	}
	
	public static void main(String[] args) {
		int size  = 10000;
		 long start = System.currentTimeMillis();
		 for(int i=0;i<size;i++){
			 System.out.println( generate());
		}
		long use  = System.currentTimeMillis()-start;
        System.out.println(use);
        System.out.println(size/use);
		
	}
}
