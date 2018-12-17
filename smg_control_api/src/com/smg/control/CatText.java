package com.smg.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CatText {
	File file;
	public CatText(String filePath) {
		this.file=new File(filePath);
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean write(byte[] status) {	
//		System.out.println("Write:"+new String(status));
		try {
			FileOutputStream fos=new FileOutputStream(file);
			try {
				fos.write(status);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public byte[] read() {	
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(file);
			byte b[]=new byte[1];
			int flag;
			try {
				flag = fis.read(b);
				if(flag!=-1){
					fis.close();
					return b;
			}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return null;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		
		return null;
	}

}
