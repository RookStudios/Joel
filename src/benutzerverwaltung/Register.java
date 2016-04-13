package benutzerverwaltung;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private String Userdata; //Speichert alle Userdaten um Datenbank zu überprüfen
	
	private RegisterGUI getData = new RegisterGUI();
	
	public Register() {
		System.out.println("Constructor Register start");
		getUsername();
		getEmail();
		getPassword();
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		generateUserdata();
		System.out.println("Constructor Register end");
	}
	
	public void addLernender() {
		//Wenn bei RegisterGUI "als Lehrer registrieren" nicht aktiviert ist wird diese Funktion aufgerufen 
		//Diese Funktion legt einen Lernenden an
		System.out.println("Erstellt Lernender");
		if (validateLernender()) {
			askDatabase(Userdata);
		} else {
			System.out.println("Die Daten erfüllen die Vorgaben nicht");
		}
	}
	
	public void addLehrer() {
		//Wennn bei RegisterGUI "als Lehrer registrieren" aktiviert ist wird diese Funktion aufgerufen
		//Diese Funktion legt einen Lehrer an
		System.out.println("Erstellt Lehrer");
		if (validateLehrer()) {
			askDatabase(Userdata);
		} else {
			System.out.println("Die Daten erfüllen die Vorgaben nicht");
		}
	}
	
	public boolean validateLernender() {
		istRichtig = false;
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
		final String R_USERNAME = "^[_A-Za-z0-9-\\+]$";
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
		final String R_PASSWORD = "^[\\w]{8,50}$";
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
		Username = getData.getUsernameField();
	}
	
	private void getPassword() {
		//TODO
		//Eingabe "Password" aus RegisterGUI holen
		Password = getData.getPasswordField(); 
	}
	
	private void getEmail() {
		//TODO
		//Eingabe "Email" aus RegisterGUI holen
		Email = getData.getEmailField();
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
			System.out.println("Fehler beim Registrieren");
		}
	}
	
	private void setData(String registerData) {
		//TODO 
		//Abspeicherung in Datenbank programmieren -> mit Roger absprechen
	}
}
