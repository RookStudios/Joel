package benutzerverwaltung;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterGUI extends JFrame implements ActionListener {
	
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

	public RegisterGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public RegisterGUI(String Title) {
		this.setTitle(Title);
		loadRegisterUI();
		this.setVisible(true);
	}

	private void loadRegisterUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		
		Username.setSize(350, 250);
		Email.setSize(350, 250);
		Password.setSize(350, 250);
		Register.setSize(350, 250);
		Register.setText("Registrieren");
		Register.addActionListener(this);
		Lehrer.setSize(350, 250);
		
		All.setLayout(new FlowLayout());
		
		All.add(Username);
		All.add(Email);
		All.add(Password);
		All.add(Register);
		All.add(Lehrer);
		All.add(Fehler);
		
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
	
	public JTextField getUsernameField(){
		return Username;
	}
	
	public JTextField getPasswordField() {
		return Password;
	}
	
	public JTextField getEmailField() {
		return Email;
	}

}
