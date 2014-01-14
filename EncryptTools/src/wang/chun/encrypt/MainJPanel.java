package wang.chun.encrypt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainJPanel extends JPanel {

	private static final long serialVersionUID = 7483829961687822457L;
	private Image backgroundImage;

	  public MainJPanel(String fileName) throws IOException {
	  	
	  	JButton btnNewButton_3 = new JButton("Create New Project");
	  	btnNewButton_3.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  		
	  		}
	  	});
	  	
	  	JButton btnNewButton = new JButton("Encrypt Tools");
	  	btnNewButton.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			
	  		}
	  	});
	  	
	  	JButton btnNewButton_1 = new JButton("Different Tools");
	  	btnNewButton_1.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {

	  		}
	  	});
	  	GroupLayout groupLayout = new GroupLayout(this);
	  	groupLayout.setHorizontalGroup(
	  		groupLayout.createParallelGroup(Alignment.LEADING)
	  			.addGroup(groupLayout.createSequentialGroup()
	  				.addContainerGap(73, Short.MAX_VALUE)
	  				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	  					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
	  						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
	  						.addGap(61)
	  						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
	  						.addGap(65))
	  					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
	  						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
	  						.addGap(125))))
	  	);
	  	groupLayout.setVerticalGroup(
	  		groupLayout.createParallelGroup(Alignment.LEADING)
	  			.addGroup(groupLayout.createSequentialGroup()
	  				.addGap(97)
	  				.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	  				.addGap(37)
	  				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	  					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	  					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
	  				.addContainerGap(83, Short.MAX_VALUE))
	  	);
	  	setLayout(groupLayout);
	  	
		  backgroundImage = ImageIO.read(new File(fileName));
	  }

	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
		    g2d.translate(-backgroundImage.getWidth(null) / 2, -backgroundImage.getHeight(null) / 2);
		    g2d.drawImage(backgroundImage, 0, 0, this);
	  }

}
