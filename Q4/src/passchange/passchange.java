package passchange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
 

public class passchange {

	
	public static void main(String [ ] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
		
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();

		Scanner sc = new Scanner(System.in);
		
		//Prompt User For New Password
		System.out.println("Enter the New Password: ");
		String newpw = sc.next();
		
		//Get the sha-1 hash of the new password
		md.update(newpw.getBytes("utf8"));
		byte[] hash = md.digest();
		
		
		FileInputStream in = null;
		FileOutputStream out = null;
		byte[] before_pw = new byte[75801];		//this is the part of the program that precedes the stored password that will be changed
		byte[] pw = new byte[20];		//75801 is  the offset of the stored password in the program (found through decompiling with IDA Pro)
		byte[] after_pw = new byte[32723];		//the remainder of the program bytes
		File f = new File("50014109.program2.exe");		
		
		try{
			in = new FileInputStream("50014109.program2.exe");
			
			//we're gonna go through the whole program, but it will be segmented into 3 parts so we can access the location of the stored password
			in.read(before_pw);			
			in.read(pw);
			in.read(after_pw);
			//delete the program
			f.delete();
			
			//reconstruct the program, but with the new password in place of the old one
			out = new FileOutputStream("50014109.program2.exe");
			out.write(before_pw);
			out.write(hash);
			out.write(after_pw);
			

		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
		System.out.println("the password is now: " + newpw);

	}
	
			
	
	
}
