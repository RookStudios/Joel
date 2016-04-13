package benutzerverwaltung;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * @author Administrator
 * @created April 13, 2016
 */
public class RegisterGUI extends JFrame implements ActionListener {

	/**
	 * @author Joel Häberli
	 * @version 1.1
	 */
	private static final long serialVersionUID = 1L;

	JPanel pnPanel0;

	JPanel All;
	JButton Register;
	JCheckBox Lehrer;
	JTextField Username;
	JTextField Email;
	JTextField Password;
	
	JLabel Fehler;

	private Register thisRegister;
	
	public RegisterGUI() {
		loadGUI();
	}

	public void loadGUI() {
		this.setTitle("Registrieren");

		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		All = new JPanel();
		GridBagLayout gbAll = new GridBagLayout();
		GridBagConstraints gbcAll = new GridBagConstraints();
		All.setLayout(gbAll);

		Register = new JButton("Registrieren");
		gbcAll.gridx = 4;
		gbcAll.gridy = 12;
		gbcAll.gridwidth = 8;
		gbcAll.gridheight = 2;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(Register, gbcAll);
		All.add(Register);

		Lehrer = new JCheckBox("Sind Sie ein Lehrer?");
		Lehrer.setSelected(true);
		gbcAll.gridx = 7;
		gbcAll.gridy = 10;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(Lehrer, gbcAll);
		All.add(Lehrer);

		Username = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 3;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(Username, gbcAll);
		All.add(Username);

		Email = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 5;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(Email, gbcAll);
		All.add(Email);

		Password = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 7;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(Password, gbcAll);
		All.add(Password);
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 1;
		gbcPanel0.gridwidth = 16;
		gbcPanel0.gridheight = 19;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(All, gbcPanel0);
		pnPanel0.add(All);

		Register.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnPanel0);
		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Listener für den Registrierungsbutton
		if (arg0.getSource() == Register) {
			thisRegister = new Register();
			System.out.println("Registrieren:");
			if (Lehrer.isSelected() == false) {
				thisRegister.addLernender();
				System.out.println("Lernender");
			} else {
				thisRegister.addLehrer();
				System.out.println("Lehrer");
			}
		}
	}

	public String getUsernameField() {
		String strUsername = Username.getText();
		return strUsername;
	}

	public String getPasswordField() {
		String strPassword = Password.getText();
		return strPassword;
	}

	public String getEmailField() {
		String strEmail = Email.getText();
		return strEmail;
	}
}

