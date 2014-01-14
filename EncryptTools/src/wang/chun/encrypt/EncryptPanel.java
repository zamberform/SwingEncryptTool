package wang.chun.encrypt;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class EncryptPanel extends JPanel {

	private static final long serialVersionUID = 34378756535995132L;
	private Image backgroundImage;
	
	private JButton jcomp1;
    private JPanel contentPane;
    private Color backgroundColour;

    public EncryptPanel() 
    {   
        System.out.println("I am in encrypt panel");
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(500, 500));
    }
/*
	  public EncryptPanel(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    g.drawImage(backgroundImage, 0, 0, this);
	  }
*/
}
