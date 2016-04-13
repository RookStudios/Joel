package benutzerverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterGUI_OLD extends JFrame implements ActionListener {
	
	/**
	 * @author Joel Häberli
	 * @version 0.1
	 */
	private static final long serialVersionUID = 1L;

	private JPanel All = new JPanel();
	
	private JTextField Username = new JTextField();
	private JTextField Email = new JTextField();
	private JTextField Password = new JTextField();
	
	private JButton Register = new JButton();
	private JCheckBox Lehrer = new JCheckBox("Sind Sie ein Lehrer?", false);
	
	private JLabel Fehler = new JLabel();
	
	private Register thisRegister;

	public RegisterGUI_OLD() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public RegisterGUI_OLD(String Title) {
		this.setTitle(Title);
		loadRegisterUI();
		this.setVisible(true);
	}

	private void loadRegisterUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		
		Username.setSize(50, 25);
		Email.setSize(50, 25);
		Password.setSize(50, 25);
		Register.setSize(50, 25);
		Register.setText("Registrieren");
		Register.addActionListener(this);
		Lehrer.setSize(50, 250);
		
		All.setLayout(new BorderLayout());
		
		All.add(Username, BorderLayout.EAST);
		All.add(Email, BorderLayout.EAST);
		All.add(Password, BorderLayout.EAST);
		All.add(Register, BorderLayout.CENTER);
		All.add(Lehrer, BorderLayout.WEST);
		All.add(Fehler, BorderLayout.SOUTH);
		
		this.add(All);
		
		All.setVisible(true);
		this.setVisible(true);
	}
	
	public JLabel getFehlermeldung(String Text){
		Fehler.setText(Text);
		Fehler.setVisible(true);
		return Fehler;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Listener für den Registrierungsbutton 
		if (arg0.getSource() == Register) {
			thisRegister = new Register();
			if (Lehrer.isSelected() == false) {
				thisRegister.addLernender();
			} else {
				thisRegister.addLehrer();
			}
		}
		
	}
	
	public String getUsernameField(){
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
