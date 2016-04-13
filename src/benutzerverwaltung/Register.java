package benutzerverwaltung;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Register implements ActionListener{
	
	private boolean istRichtig; //Variable welche in allen Validierungsfunktionen gebraucht wird.
	private boolean istVorhanden; //Variable von Funktion "askDatabase()"
	private static final String SEPARATOR = ":::"; //Allgemein definierter Separator
	private String Username; //Sessionspeiche f�r den Username
	private String Email; //Sessionspeicher f�r Email
	private String Password; //Sessionspeicher f�r Password
	
	private boolean istEmailRichtig; //Wird gebraucht f�r Validierung von Email
	private boolean istUsernameRichtig; //Wird gebraucht f�r Validierung von Username 
	private boolean istPasswordRichtig; //Wird gebraucht f�r Validierung von Password
	
	private String Userdata; //Speichert alle Userdaten um Datenbank zu �berpr�fen
	
	//Alle swas es f�rs GUI braucht
	JFrame RegisterFrame = new JFrame();
	
	JPanel pnPanel0;

	JPanel All;
	JButton Register;
	JCheckBox Lehrer;
	JTextField TUsername;
	JTextField TEmail;
	JTextField TPassword;
	
	JLabel Fehler;
	
	public Register() {
		System.out.println("Constructor Register start");
		generateGUI();
		System.out.println("Constructor Register end");
	}
	
	public void addLernender() {
		//Wenn bei RegisterGUI "als Lehrer registrieren" nicht aktiviert ist wird diese Funktion aufgerufen 
		//Diese Funktion legt einen Lernenden an
		System.out.println("Erstellt Lernender");
		if (validateLernender()) {
			askDatabase(Userdata);
		} else {
			System.out.println("Die Daten erf�llen die Vorgaben nicht");
		}
	}
	
	public void addLehrer() {
		//Wennn bei RegisterGUI "als Lehrer registrieren" aktiviert ist wird diese Funktion aufgerufen
		//Diese Funktion legt einen Lehrer an
		System.out.println("Erstellt Lehrer");
		if (validateLehrer()) {
			askDatabase(Userdata);
		} else {
			System.out.println("Die Daten erf�llen die Vorgaben nicht");
		}
	}
	
	public boolean validateLernender() {
		istRichtig = false;
		getUsername();
		getEmail();
		getPassword();
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		generateUserdata();
		validateEmail();
		validateUsername();
		validatePassword();
		askDatabase(Userdata);
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig && istVorhanden) {
			istRichtig = true;
		} else {
			istRichtig = false;
		}
		return istRichtig;
	}
	
	public boolean validateLehrer() {
		istRichtig = false;
		getUsername();
		getEmail();
		getPassword();
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		generateUserdata();
		validateEmail();
		validateUsername();
		validatePassword();
		askDatabase(Userdata);
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig && istVorhanden) {
			istRichtig = true;
		} else {
			istRichtig = false;
		}
		return istRichtig;
	}
	
	private boolean validateEmail() {
		//TODO
		//Validierung Email
		final String R_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pEmail = Pattern.compile(R_EMAIL); 
		Matcher mEmail = pEmail.matcher(Email);
		if(mEmail.matches()) {
			System.out.println("Email: True");
			return istEmailRichtig = true;
		} else {
			System.out.println("Email: false");
			return istEmailRichtig = false;
		}
	}
	
	private boolean validateUsername() {
		//TODO
		//Validierung Username
		final String R_USERNAME = "^[A-Za-z0-9]{4,30}$";
		Pattern pUsername = Pattern.compile(R_USERNAME);
		Matcher mUsername = pUsername.matcher(Username);
		if (mUsername.matches()) {
			System.out.println("Username: True");
			return istUsernameRichtig = true;
		} else {
			System.out.println("Username: false");
			return istUsernameRichtig = false;
		}
	}
	
	private boolean validatePassword() {
		//TODO
		//Validierung Password
		final String R_PASSWORD = "^[A-Za-z0-9!?+*,�%&=]{8,50}$";
		Pattern pPassword = Pattern.compile(R_PASSWORD);
		Matcher mPassword = pPassword.matcher(Password);
		if (mPassword.matches()) {
			System.out.println("Password: True");
			return istPasswordRichtig = true;
		} else {
			System.out.println("Password: false");
			return istPasswordRichtig = false;
		}
	}
	
	private void getUsername() {
		//TODO
		//Eingabe "Username" aus RegisterGUI holen
		Username = TUsername.getText();
	}
	
	private void getPassword() {
		//TODO
		//Eingabe "Password" aus RegisterGUI holen
		Password = TPassword.getText();
	}
	
	private void getEmail() {
		//TODO
		//Eingabe "Email" aus RegisterGUI holen
		Email = TEmail.getText();
	}
	
	public void generateUserdata() {
		Userdata = Username + SEPARATOR + Email + SEPARATOR + Password;
	}
	
	public void askDatabase(String registerData) {
		istVorhanden = false;
		//TODO 
		//Datenbank �berpr�fung programmieren
		if (istVorhanden == false) {
			setData(registerData);
		} else {
			System.out.println("Fehler beim Registrieren");
		}
	}
	
	private void setData(String registerData) {
		//TODO 
		//Abspeicherung in Datenbank programmieren -> mit Roger absprechen
	}
	
	private void generateGUI() {
		RegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RegisterFrame.setTitle("Registrieren");

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

		TUsername = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 3;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(TUsername, gbcAll);
		All.add(TUsername);

		TEmail = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 5;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(TEmail, gbcAll);
		All.add(TEmail);

		TPassword = new JTextField();
		gbcAll.gridx = 7;
		gbcAll.gridy = 7;
		gbcAll.gridwidth = 1;
		gbcAll.gridheight = 1;
		gbcAll.fill = GridBagConstraints.BOTH;
		gbcAll.weightx = 1;
		gbcAll.weighty = 0;
		gbcAll.anchor = GridBagConstraints.NORTH;
		gbAll.setConstraints(TPassword, gbcAll);
		All.add(TPassword);
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

		RegisterFrame.setContentPane(pnPanel0);
		RegisterFrame.pack();
		RegisterFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Listener f�r den Registrierungsbutton
		if (arg0.getSource() == Register) {
			if (Lehrer.isSelected()) {
				addLehrer();
				System.out.println("Registrieren: Lernender");
			} else {
				addLernender();
				System.out.println("Registrieren: Lehrer");
			}
		}
	}
}
