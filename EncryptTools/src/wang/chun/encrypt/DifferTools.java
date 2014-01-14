package wang.chun.encrypt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DifferTools extends JPanel {

	private static final long serialVersionUID = 34378756535995132L;
	private Image backgroundImage;
	
	private JButton jcomp1;
    private JPanel contentPane;
    private Color backgroundColour;

    public DifferTools() 
    {   
        System.out.println("I am in differ panel");
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(500, 500));
    }

}
