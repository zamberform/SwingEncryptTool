package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.JavaDESEncryption;
import model.MainModel;

public class MainController {

	private MainModel model;

	public MainController(MainModel model) {
		this.model = model;
		this.model.action();
	}

	public void quitter(JPanel main) {
		((JFrame) main.getTopLevelAncestor()).dispose();
		System.out.println("Quit !");
	}

	public void serachAllFilesInPath(String path) {
		File pathName = new File(path);
		String files;
		File[] listOfFiles = pathName.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				System.out.println(files);
			}
		}
	}
	
	public void setSelectionPathInText(JTextField textField)
	{
		JFileChooser openFile = new JFileChooser();
		openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFile.showOpenDialog(null);
        File test = openFile.getSelectedFile();
        
        if(test!=null)
        {
        	textField.setText(test.getAbsolutePath());
            System.out.println(test.getName());
        }
	}
	
	public void setOutputPathInText(JTextField textField)
	{
		JFileChooser openFile = new JFileChooser();
		openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFile.showOpenDialog(null);
        File test = openFile.getSelectedFile();
        
        if(test!=null)
        {
        	textField.setText(test.getAbsolutePath());
            System.out.println(test.getName());
        }
	}
	
	public void runDESEncrypt(JTextField textField1,JTextField textField2,JTextField textField3)
	{
		System.out.println("Encrypt:" + textField1.getText()+" "+ textField2.getText() + " "+ textField3.getText());
		
		JavaDESEncryption encrypt = new JavaDESEncryption();
		
		encrypt.encryptFile(textField1.getText(), textField3.getText());
	}
	
	public void runDESDecrypt(JTextField textField1,JTextField textField2,JTextField textField3)
	{
		System.out.println("Decrypt:" + textField1.getText()+" "+ textField2.getText() + " "+ textField3.getText());
		
		JavaDESEncryption encrypt = new JavaDESEncryption();
		
		encrypt.decrytpFile(textField1.getText(), textField3.getText());
	}
	
	public boolean checkKeyText(JTextField textField)
	{
		boolean flag = false;
		if(!"".equals(textField.getText()))
		{
			flag = true;
		}
		return flag;
	}
	
	public boolean checkInputPath(JTextField textField)
	{
		boolean flag = false;
		File test = new File(textField.getText());
		if(test!=null&&test.exists())
		{
			flag = true;
		}
		return flag;
	}
	
	public boolean checkOutputpath(JTextField textField)
	{
		boolean flag = false;
		File test = new File(textField.getText());
		if(test!=null&&test.exists())
		{
			flag = true;
		}
		return flag;
	}
}
