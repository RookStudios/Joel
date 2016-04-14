package benutzerverwaltung;

//Isch ds iz uf� pusht?

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import bdatabase.*;

/**
 * @author Administrator
 * @created April 13, 2016
 */
public class Delete implements ActionListener
{
	/**
	 * @author Joel H�berli
	 * @version 1.0
	 */

	// Variablendeklarationen
	private String Username;
	private String Email;
	private String Password;

	private String delData;
	private String SEPARATOR = ":::";

	private boolean existiert;

	// Datenbank
	BDatabase BenutzerDB;

	// GUIdeklarationen
	JFrame deleteFrame;

	JPanel deletePanel;
	JButton DeleteUser;
	JTextField DeleteUsername;
	JTextField DeleteEmail;
	JTextField DeletePassword;
	JCheckBox DeleteLehrer;
	JLabel DeleteLabel;

	public Delete()
	{
		System.out.println("Constructor Delete Start");
		loadDelete();
		BenutzerDB = new BDatabase();
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

		DeleteUser = new JButton("L�schen");
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

		DeleteUser.addActionListener(this);

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

		DeleteLabel = new JLabel("Bitte geben Sie Username, E-Mail und Passwort ein");
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

	private void getdelData()
	{
		delData = Username + SEPARATOR + Email + SEPARATOR + Password;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == DeleteUser)
		{
			Username = DeleteUsername.getText();
			Email = DeleteEmail.getText();
			Password = DeletePassword.getText();
			getdelData();
			existiert = BenutzerDB.checkPossible(delData);
			if (existiert)
			{
				JFrame sicher = new JFrame();
				JLabel frage = new JLabel("M�chten Sie ihr Profil wirklich l�schen?");
				JButton bestatigung = new JButton();
				bestatigung.setText("Ja, L�schen!");
				bestatigung.addActionListener(this);
				JButton nein = new JButton();
				nein.setText("Nein, auf keinen Fall!");
				nein.addActionListener(this);
				sicher.add(frage);
				sicher.add(nein);
				sicher.add(bestatigung);
				sicher.setVisible(true);
				if (arg0.getSource() == bestatigung)
				{
					BenutzerDB.delUser(delData, DeleteLehrer.isSelected());
				}
				if (arg0.getSource() == nein) {
					sicher.setVisible(false);
				}
			} else
			{
				System.out.println("Es scheint, dass dieser User bereits gel�scht wurde oder nie existiert hat");
			}
		}
	}
}
