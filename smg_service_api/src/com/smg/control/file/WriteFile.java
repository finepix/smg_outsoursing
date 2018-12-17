package com.smg.control.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	private File file;
	private String path = "/home/zhuxin/smg/status.txt";
	private boolean sign;
	
	public WriteFile(Boolean sign){
		this.sign = sign;
	}
	
	
	public void write() throws IOException{
		file = new File(path);
		FileWriter fileWriter = new FileWriter(file);
		if(sign==false)
			fileWriter.write("0");
		else if(sign==true)
			fileWriter.write("1");
		
		fileWriter.close();
	}

}
