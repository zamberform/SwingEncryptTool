package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class JavaDESEncryption {

	private static Cipher encrypt;
	private static Cipher decrypt;

	private static Key key;
	private static final byte[] initialization_vector = { (byte) 0x8E, 0x12,
			0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };

	public JavaDESEncryption() {

		try {
			KeyGenerator secret_key = KeyGenerator.getInstance("DES");
			secret_key.init(new SecureRandom("12345678".getBytes()));
			
			key = secret_key.generateKey();
			secret_key = null;
			AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(
					initialization_vector);
			encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, key, alogrithm_specs);
			decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, key, alogrithm_specs);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decrytpFile(String encryptedFile, String decryptedFile) {
		try {
			decrypt(new FileInputStream(encryptedFile), new FileOutputStream(
					decryptedFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void encrypt(InputStream input, OutputStream output)
			throws Exception {
	    CipherInputStream cis = new CipherInputStream(input, encrypt);
	    byte[] buffer = new byte[1024];
	    int r;
	    while ((r = cis.read(buffer)) > 0) {
	    	output.write(buffer, 0, r);
	    }
	    cis.close();
	    input.close();
	    output.close();

	}

	private static void decrypt(InputStream input, OutputStream output)
			throws Exception {
	    CipherOutputStream cos = new CipherOutputStream(output, decrypt);
	    byte[] buffer = new byte[1024];
	    int r;
	    while ((r = input.read(buffer)) >= 0) {
	        cos.write(buffer, 0, r);
	    }
	    cos.close();
	    output.close();
	    input.close();
	}

}