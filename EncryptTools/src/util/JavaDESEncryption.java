package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class JavaDESEncryption {

	private static Cipher encrypt;
	private static Cipher decrypt;

	private static final byte[] initialization_vector = { 22, 33, 11, 44, 55,
			99, 66, 77 };

	public JavaDESEncryption() {

		try {
			SecretKey secret_key = KeyGenerator.getInstance("DES")
					.generateKey();
			AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(
					initialization_vector);
			encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, secret_key, alogrithm_specs);
			decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, secret_key, alogrithm_specs);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void encryptFile(String clearFile, String encryptedFile) {
		try {
			encrypt(new FileInputStream(clearFile), new FileOutputStream(
					encryptedFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decrytpFile(String encryptedFile, String decryptedFile) {
		try {
			decrypt(new FileInputStream(encryptedFile), new FileOutputStream(
					decryptedFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void encrypt(InputStream input, OutputStream output)
			throws IOException {

		try {
			    byte[] buf = new byte[1024];
			// bytes at this stream are first encoded
			    output = new CipherOutputStream(output, encrypt);
			 
			// read in the clear text and write to out to encrypt
			int numRead = 0;
			 
			while ((numRead = input.read(buf)) >= 0) {
			 
				output.write(buf, 0, numRead);
			 
			}
			 
			 
			output.close();
			 
			  }
			  catch (IOException e) {
			    System.out.println("I/O Error:" + e.getMessage());
			 
			  }

	}

	private static void decrypt(InputStream input, OutputStream output)
			throws IOException {

		try {
			    byte[] buf = new byte[1024];
			// bytes read from stream will be decrypted
			    CipherInputStream cis = new CipherInputStream(input, encrypt);
			 
			// read in the decrypted bytes and write the clear text to out
			 
			int numRead = 0;
			 
			while ((numRead = cis.read(buf)) >= 0) {
				output.write(buf, 0, numRead);
			 
			}
			 
			// close all streams
			 
			cis.close();
			 
			input.close();
			output.close();
			 
			  }
			  catch (IOException e) {
			    System.out.println("I/O Error:" + e.getMessage());
			 
			  }

	}

}