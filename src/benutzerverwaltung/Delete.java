package benutzerverwaltung;

//Isch ds iz ufä pusht?

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * @author Administrator
 * @created April 13, 2016
 */
public class Delete 
{
	/**
	 * @author Joel Häberli
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	JFrame deleteFrame;
	
	JPanel deletePanel;
	JButton DeleteUser;
	JTextField DeleteUsername;
	JTextField DeleteEmail;
	JTextField DeletePassword;
	JCheckBox DeleteLehrer;
	JLabel DeleteLabel;
	
	public Delete(){
		System.out.println("Constructor Delete Start");
		loadDelete();
		System.out.println("Constructor Delete End");
	}

	public void loadDelete()
	{
		deleteFrame = new JFrame();

		deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		deletePanel = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		deletePanel.setLayout(gbPanel0);

		DeleteUser = new JButton("Löschen");
		gbcPanel0.gridx = 7;
		gbcPanel0.gridy = 16;
		gbcPanel0.gridwidth = 6;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeleteUser, gbcPanel0);
		deletePanel.add(DeleteUser);

		DeleteUsername = new JTextField();
		gbcPanel0.gridx = 4;
		gbcPanel0.gridy = 3;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeleteUsername, gbcPanel0);
		deletePanel.add(DeleteUsername);

		DeleteEmail = new JTextField();
		gbcPanel0.gridx = 4;
		gbcPanel0.gridy = 6;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeleteEmail, gbcPanel0);
		deletePanel.add(DeleteEmail);

		DeletePassword = new JTextField();
		gbcPanel0.gridx = 4;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 2;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeletePassword, gbcPanel0);
		deletePanel.add(DeletePassword);

		DeleteLehrer = new JCheckBox("Sind Sie ein Lehrer?");
		DeleteLehrer.setSelected(true);
		gbcPanel0.gridx = 4;
		gbcPanel0.gridy = 13;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeleteLehrer, gbcPanel0);
		deletePanel.add(DeleteLehrer);

		DeleteLabel = new JLabel("Bitte geben Sie Username, E-Mail und PAsswort ein");
		gbcPanel0.gridx = 4;
		gbcPanel0.gridy = 1;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(DeleteLabel, gbcPanel0);
		deletePanel.add(DeleteLabel);

		deleteFrame.setContentPane(deletePanel);
		deleteFrame.pack();
		deleteFrame.setVisible(true);
	}
}
