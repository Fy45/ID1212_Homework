/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author user
 */
import java.rmi.*;
import java.util.Vector;
/**
 *
 * @author user
 */
public interface RMIinter extends Remote{

  
//定义为public类型，保证其他类能够调用，扩展成为remote类型，使对象成为远程对象
    
        Vector<String> getFileList() throws RemoteException;
        String  print() throws RemoteException;
	String operationSystem(String input);
	//获取文件长度
	int getFileLength(String fileName) throws RemoteException;	
	String login(String loginname) throws RemoteException;
        String register(String registername) throws RemoteException;
	//获取文件内容
	//fileName: 文件�?
	//start：起始位�?
	//lenghg：字节长�?
	byte[] getFile(String fileName, int start, int length) throws RemoteException;	
   
}