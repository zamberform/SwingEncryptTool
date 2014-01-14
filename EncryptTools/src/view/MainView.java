package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;

import controller.MainController;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer {

	private JPanel main = new JPanel();
	private MainController ctrlr = null;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	public MainView(final MainController ctrlr) {
		super();
		this.ctrlr = ctrlr;
		initialize();
		this.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {				
				if(quit())
					ctrlr.quitter(main);
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
	}

	private void initialize() {
		this.setSize(480, 320);
		this.setTitle("wang.chun DES tool");
		this.setResizable(false);
		this.setContentPane(getPane());
		this.getContentPane().setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		this.setJMenuBar(getMenu());
		this.bindAction();
	}

	private void bindAction() {
		final JMenuBar menu = this.getJMenuBar();

		menu.getMenu(0).getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(quit())
					ctrlr.quitter(main);
			}
		});
	}

	protected boolean quit() {
		int clicked = JOptionPane.showConfirmDialog(this, "Do you really want to quit ?");
		return (clicked == JOptionPane.YES_OPTION) ? true : false;
	}

	private JPanel getPane() {
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		main.add(desktopPane);
		SpringLayout sl_desktopPane = new SpringLayout();
		desktopPane.setLayout(sl_desktopPane);
		
		textField = new JTextField();
		sl_desktopPane.putConstraint(SpringLayout.WEST, textField, 93, SpringLayout.WEST, desktopPane);
		sl_desktopPane.putConstraint(SpringLayout.NORTH, textField, 33, SpringLayout.NORTH, desktopPane);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                ctrlr.setSelectionPathInText(textField);
			}
		});
		sl_desktopPane.putConstraint(SpringLayout.WEST, btnNewButton, 342, SpringLayout.WEST, desktopPane);
		sl_desktopPane.putConstraint(SpringLayout.EAST, textField, -11, SpringLayout.WEST, btnNewButton);
		sl_desktopPane.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, textField);
		desktopPane.add(btnNewButton);
		
		JLabel lblPath = new JLabel("Path");
		sl_desktopPane.putConstraint(SpringLayout.NORTH, lblPath, 3, SpringLayout.NORTH, textField);
		sl_desktopPane.putConstraint(SpringLayout.EAST, lblPath, -11, SpringLayout.WEST, textField);
		desktopPane.add(lblPath);
		
		JLabel lblKey = new JLabel("Key");
		sl_desktopPane.putConstraint(SpringLayout.NORTH, lblKey, 22, SpringLayout.SOUTH, lblPath);
		sl_desktopPane.putConstraint(SpringLayout.EAST, lblKey, 0, SpringLayout.EAST, lblPath);
		desktopPane.add(lblKey);
		
		textField_2 = new JTextField();
		sl_desktopPane.putConstraint(SpringLayout.NORTH, textField_2, -3, SpringLayout.NORTH, lblKey);
		sl_desktopPane.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		sl_desktopPane.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblString = new JLabel("Output Path");
		sl_desktopPane.putConstraint(SpringLayout.NORTH, lblString, 27, SpringLayout.SOUTH, lblKey);
		sl_desktopPane.putConstraint(SpringLayout.EAST, lblString, 0, SpringLayout.EAST, lblPath);
		desktopPane.add(lblString);
		
		textField_3 = new JTextField();
		sl_desktopPane.putConstraint(SpringLayout.NORTH, textField_3, -3, SpringLayout.NORTH, lblString);
		sl_desktopPane.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, textField);
		sl_desktopPane.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField);
		desktopPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("...");
		sl_desktopPane.putConstraint(SpringLayout.NORTH, btnNewButton_2, -4, SpringLayout.NORTH, lblString);
		sl_desktopPane.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, btnNewButton);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlr.setOutputPathInText(textField_3);
			}
		});
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Encrypt");
		sl_desktopPane.putConstraint(SpringLayout.WEST, btnNewButton_1, -180, SpringLayout.EAST, desktopPane);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlr.checkInputPath(textField)&&ctrlr.checkOutputpath(textField_3)&&ctrlr.checkKeyString(textField_2))
				{
					if(ctrlr.runDESEncrypt(textField, textField_2, textField_3))
					{
						JOptionPane.showMessageDialog(null, "Encrypt Complete");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "I am Sorry,there are some wrong with Input");
				}
			}
		});
		sl_desktopPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 15, SpringLayout.SOUTH, btnNewButton_2);
		sl_desktopPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 65, SpringLayout.SOUTH, btnNewButton_2);
		sl_desktopPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -59, SpringLayout.EAST, desktopPane);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Decrypt");
		sl_desktopPane.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, btnNewButton_1);
		sl_desktopPane.putConstraint(SpringLayout.EAST, btnNewButton_3, -28, SpringLayout.WEST, btnNewButton_1);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlr.checkInputPath(textField)&&ctrlr.checkOutputpath(textField_3)&&ctrlr.checkKeyString(textField_2))
				{
					if(ctrlr.runDESDecrypt(textField, textField_2, textField_3))
					{
						JOptionPane.showMessageDialog(null, "Decrypt Complete");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "I am Sorry,there are some wrong with Input");
				}
			}
		});
		
		sl_desktopPane.putConstraint(SpringLayout.WEST, btnNewButton_3, -157, SpringLayout.WEST, btnNewButton_1);
		sl_desktopPane.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 0, SpringLayout.SOUTH, btnNewButton_1);
		desktopPane.add(btnNewButton_3);
		
		((JComponent) main.getComponent(0)).setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		main.getComponent(0).setSize(new Dimension(
				this.getSize().width, 
				this.getSize().height
		));

		JPanel panel = new JPanel();
		main.add(panel);
		
		((JPanel) main.getComponent(1)).setLayout(new FlowLayout());
		((FlowLayout) ((JPanel) main.getComponent(1)).getLayout()).setAlignment(FlowLayout.RIGHT);
		((JPanel) main.getComponent(1)).add(new JLabel("CopyRight @zamberform"));
		return main;
	}
	
	private JMenuBar getMenu() {
		JMenuBar menu = new JMenuBar();

		menu.add(new JMenu("Help")); // Menu 0
		menu.getMenu(0).addSeparator(); // item 0
		menu.getMenu(0).add(new JMenuItem("Quit")); // item 1
		menu.getMenu(0).getItem(1).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.VK_ALT));

		return menu;
	}

	protected void alert(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void update(Observable o, Object arg) {
		if(arg.equals("action")) 
			alert("One Action !");
		repaint();
	}
}