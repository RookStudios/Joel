package benutzerverwaltung;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class Register {
	
	private boolean istRichtig; //Variable welche in allen Validierungsfunktionen gebraucht wird.
	private boolean istVorhanden; //Variable von Funktion "askDatabase()"
	private static final String SEPARATOR = ":::"; //Allgemein definierter Separator
	private String Username; //Sessionspeiche für den Username
	private String Email; //Sessionspeicher für Email
	private String Password; //Sessionspeicher für Password
	
	private boolean istEmailRichtig; //Wird gebraucht für Validierung von Email
	private boolean istUsernameRichtig; //Wird gebraucht für Validierung von Username 
	private boolean istPasswordRichtig; //Wird gebraucht für Validierung von Password
	
	///Regexe///////////////////////////////////////////////////////////////////////
	
	//Regexausdrücke für Email, Username und Password
	
	//Pattern erstellen für alle drei Angaben
	
	//Matcher für alle drei angaben erstellen
	
	///Regexe Ende///////////////////////////////////////////////////////////////////
	
	private String Userdata; //Speichert alle Userdaten um Datenbank zu überprüfen
	
	private RegisterGUI getData = new RegisterGUI();
	
	public Register() {
		getUsername();
		getEmail();
		getPassword();
		generateUserdata();
	}
	
	public void addLernender() {
		//Wenn bei RegisterGUI "als Lehrer registrieren" nicht aktiviert ist wird diese Funktion aufgerufen 
		//Diese Funktion legt einen Lernenden an
		System.out.println("Erstellt Lernender");
		System.out.println(Userdata);
		if (validateLernender() == true) {
			askDatabase(Userdata);
		} else {
			getData.getFehlermeldung("Die Daten erfüllen die Vorgaben nicht");
		}
	}
	
	public void addLehrer() {
		//Wennn bei RegisterGUI "als Lehrer registrieren" aktiviert ist wird diese Funktion aufgerufen
		//Diese Funktion legt einen Lehrer an
		System.out.println("Erstellt Lehrer");
		System.out.println(Userdata);
		if (validateLehrer() == true) {
			askDatabase(Userdata);
		} else {
			getData.getFehlermeldung("Die Daten erfüllen die Vorgaben nicht");
		}
	}
	
	public boolean validateLernender() {
		istRichtig = false;
		validateEmail();
		validateUsername();
		validatePassword();
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig) {
			istRichtig = true;
		} else {
			istRichtig = false;
		}
		return istRichtig;
	}
	
	public boolean validateLehrer() {
		istRichtig = false;
		validateEmail();
		validateUsername();
		validatePassword();
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig) {
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
			istEmailRichtig = true;
		} else {
			istEmailRichtig = false;
		}
		return istEmailRichtig;
	}
	
	private boolean validateUsername() {
		//TODO
		//Validierung Username
		final String R_USERNAME = "^[_A-Za-z0-9-\\+]$";
		Pattern pUsername = Pattern.compile(R_USERNAME);
		Matcher mUsername = pUsername.matcher(Username);
		if (mUsername.matches()) {
			return istUsernameRichtig = true;
		} else {
			return istUsernameRichtig = false;
		}
	}
	
	private boolean validatePassword() {
		//TODO
		//Validierung Password
		final String R_PASSWORD = "^[\\w]{8,50}$";
		Pattern pPassword = Pattern.compile(R_PASSWORD);
		Matcher mPassword = pPassword.matcher(Password);
		if (mPassword.matches()) {
			return istPasswordRichtig = true;
		} else {
			return istPasswordRichtig = false;
		}
	}
	
	private void getUsername() {
		//TODO
		//Eingabe "Username" aus RegisterGUI holen
		JTextField myText = getData.getUsernameField();
		Username = myText.getText();
	}
	
	private void getPassword() {
		//TODO
		//Eingabe "Password" aus RegisterGUI holen
		JTextField myText = getData.getPasswordField();
		Password = myText.getText();
	}
	
	private void getEmail() {
		//TODO
		//Eingabe "Email" aus RegisterGUI holen
		JTextField myText = getData.getEmailField();
		Email = myText.getText();
	}
	
	public void generateUserdata() {
		Userdata = Username + SEPARATOR + Email + SEPARATOR + Password;
	}
	
	public void askDatabase(String registerData) {
		istVorhanden = false;
		//TODO 
		//Datenbank überprüfung programmieren
		if (istVorhanden == false) {
			setData(registerData);
		} else {
			getData.getFehlermeldung("Dieser Benutzer ist leider schon vorhanden.");
		}
	}
	
	private void setData(String registerData) {
		//TODO 
		//Abspeicherung in Datenbank programmieren -> mit Roger absprechen
	}
}
