import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;



public class passwords {

	
	
	public static void main(String [] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String password = "none found";
		String[] dictionary = new String[500000];
		String[] PINlist = new String[10000];
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update("abcd".getBytes("utf8"));

		String salt = "DX";
		PINlist = generatePINList();
		String saltedPIN = "111111";
		String hash = "DB15AD9F2BF372C150FDB9400C1EABAA68D39F04";
		hash = hash.toLowerCase();

		
		for(int i = 0; i < 10000; i++){
			saltedPIN = salt+PINlist[i];
			md.reset();
			md.update(saltedPIN.getBytes("utf8"));
			password = new String(Hex.encodeHex(md.digest()));
			if(hash.equals(password)){
				System.out.println("PIN found: " + PINlist[i]);
				break;
			}
		}
		


	}
	
	
	public static String[] generatePINList(){
		char[] characters = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		char[] PIN = new char[4];
		String[] PINlist = new String[10000];
		int index = 0;
	
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				for(int k = 0; k < 10; k++){
					for(int l = 0; l < 10; l++){
						PIN[0] = Character.forDigit(i, 10);
						PIN[1] = Character.forDigit(j, 10);
						PIN[2] = Character.forDigit(k, 10);
						PIN[3] = Character.forDigit(l, 10);
						PINlist[index] = new String(PIN);
						index++;
					}
				}
			}
		}
		
		return PINlist;
	}
	
	
	public static String generateSalt(){
		String characters = "abcdefghijklmnopqarstuvqxyzABCDEFGHIJKLMNOPQRSTUVWXWZ1234567890!@#$%^&*()_+-=";
		char[] salt = new char[2];
		Random randomizer = new Random();
		int index;
		for(int i = 0; i<2; i++){
			index = randomizer.nextInt(78);
			salt[i] = characters.charAt(index);
		}
		
		String salt_string = new String(salt);
		return salt_string;
	}
	
}
