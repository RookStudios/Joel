package benutzerverwaltung;

//Isch ds iz pusht?

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
import bdatabase.*;

public class Register implements ActionListener
{

	private boolean istRichtig; // Variable welche in allen Validierungsfunktionen gebraucht wird.
	private boolean istVorhanden; // Variable von Funktion "askDatabase()"
	private static final String SEPARATOR = ":::"; // Allgemein definierter Separator
	private String Username; // Sessionspeiche f�r den Username
	private String Email; // Sessionspeicher f�r Email
	private String Password; // Sessionspeicher f�r Password

	private boolean istEmailRichtig; // Wird gebraucht f�r Validierung von Email
	private boolean istUsernameRichtig; // Wird gebraucht f�r Validierung von Username
	private boolean istPasswordRichtig; // Wird gebraucht f�r Validierung Password

	private String Userdata; // Speichert alle Userdaten um Datenbank zu �berpr�fen

	// Datenbank f�r Benutzerverwaltung deklarieren
	BDatabase BenutzerDB;

	// Alles was es f�rs GUI braucht --> Das Gui wird von Nina �berarbeitet aber
	// ich brauceh eines f�r testzwecke
	JFrame RegisterFrame = new JFrame(); // das Frame

	JPanel pnPanel0; // Panel 1 --> eigentlich unn�tig aber w�re viel arbeit es
						// zu entfernen und weil nur testzweck egal

	JPanel All; // Panel 2 --> Hier kleben s�mtliche komponenten drauf
	JButton Register; // Button welche alle daten submittet
	JCheckBox Lehrer; // CheckBox ob man Lehrer sein will oder nicht
	JTextField TUsername; // Textfeld f�r Username
	JTextField TEmail; // Textfeld f�r Email
	JTextField TPassword; // Textfeld f�r Password

	JLabel Fehler; // Wird sp�ter dazu verwendet, fehler auf GUI auszugeben

	// Konstruktor f�r diese Klasse -> generiert nur das GUI und somit auch den
	// ActionListener, welcher das Programm startet
	public Register()
	{
		System.out.println("Constructor Register start");
		generateGUI();
		// Instanzierung Datenbank
		BenutzerDB = new BDatabase();
		System.out.println("Constructor Register end");
	}

	// Wird aufgerufen, wenn der Lehrer im GUI nicht angekreuzt ist
	public void addLernender()
	{
		// Wenn bei RegisterGUI "als Lehrer registrieren" nicht aktiviert ist
		// wird diese Funktion aufgerufen
		// Diese Funktion legt einen Lernenden an
		System.out.println("Erstellt Lernender");
		if (validateLernender())
		{
			askDatabase();
			if (!istVorhanden)
			{
				setData();
			} else
			{
				System.out.println("Fehler beim Registrieren");
			}
		} else
		{
			System.out.println("Die Daten erf�llen die Vorgaben nicht");
		}
	}

	// Wird aufgerufen, wenn Lehrer im GUI angekreuzt ist
	public void addLehrer()
	{
		// Wennn bei RegisterGUI "als Lehrer registrieren" aktiviert ist wird
		// diese Funktion aufgerufen
		// Diese Funktion legt einen Lehrer an
		System.out.println("Erstellt Lehrer");
		if (validateLernender())
		{
			askDatabase();
			if (!istVorhanden)
			{
				setData();
			} else
			{
				System.out.println("Fehler beim Registrieren");
			}
		} else
		{
			System.out.println("Die Daten erf�llen die Vorgaben nicht");
		}
	}

	// Validiert alle eingaben des Benutzers plus, ob der Benutz er bereits
	// existiert oder nicht (DB) (Wenn "addLernender")
	public boolean validateLernender()
	{
		getUsername();
		getEmail();
		getPassword();
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		generateUserdata();
		System.out.println(Userdata);
		validateEmail();
		validateUsername();
		validatePassword();
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig)
		{
			istRichtig = true;
		} else
		{
			istRichtig = false;
		}
		return istRichtig;
	}

	// Validiert alle eingaben des Benutzers plus, ob der Benutz er bereits
	// existiert oder nicht (DB) (Wenn "addLehrer")
	public boolean validateLehrer()
	{
		istRichtig = false;
		getUsername();
		getEmail();
		getPassword();
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		generateUserdata();
		System.out.println(Userdata);
		validateEmail();
		validateUsername();
		validatePassword();
		if (istEmailRichtig && istUsernameRichtig && istPasswordRichtig)
		{
			istRichtig = true;
		} else
		{
			istRichtig = false;
		}
		return istRichtig;
	}

	// validiert E-Mail mit Regex
	private boolean validateEmail()
	{
		// Validierung Email
		final String R_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pEmail = Pattern.compile(R_EMAIL);
		Matcher mEmail = pEmail.matcher(Email);
		if (mEmail.matches())
		{
			System.out.println("Email: True");
			return istEmailRichtig = true;
		} else
		{
			System.out.println("Email: false");
			return istEmailRichtig = false;
		}
	}

	// Validiert Username mit Regex
	private boolean validateUsername()
	{
		// Validierung Username
		final String R_USERNAME = "^[A-Za-z0-9]{4,30}$";
		Pattern pUsername = Pattern.compile(R_USERNAME);
		Matcher mUsername = pUsername.matcher(Username);
		if (mUsername.matches())
		{
			System.out.println("Username: True");
			return istUsernameRichtig = true;
		} else
		{
			System.out.println("Username: false");
			return istUsernameRichtig = false;
		}
	}

	// validiert Password mit Regex
	private boolean validatePassword()
	{
		// Validierung Password
		final String R_PASSWORD = "^[A-Za-z0-9!?+*,�%&=]{8,50}$";
		Pattern pPassword = Pattern.compile(R_PASSWORD);
		Matcher mPassword = pPassword.matcher(Password);
		if (mPassword.matches())
		{
			System.out.println("Password: True");
			return istPasswordRichtig = true;
		} else
		{
			System.out.println("Password: false");
			return istPasswordRichtig = false;
		}
	}

	// getter f�r den USername aus GUI
	private void getUsername()
	{
		// Eingabe "Username" aus RegisterGUI holen
		Username = TUsername.getText();
	}

	// getter f�r Password aus GUI
	private void getPassword()
	{
		// Eingabe "Password" aus RegisterGUI holen
		Password = TPassword.getText();
	}

	// getter f�r E-Mail aus Gui
	private void getEmail()
	{
		// Eingabe "Email" aus RegisterGUI holen
		Email = TEmail.getText();
	}

	// generiert einen String um mit unseren Datenbank KLassen kommunizieren zu
	// k�nnen (richtiges Format)
	public void generateUserdata()
	{
		Userdata = Username + SEPARATOR + Email + SEPARATOR + Password;
	}

	// Schnittstelle Datenbank zum schreiben und lesen (Neue Daten hinzuf�gen
	// udn auf Redundanzen �berpr�fen)
	public void askDatabase()
	{
		// Datenbank �berpr�fung
		istVorhanden = BenutzerDB.checkPossible(Userdata);
	}

	// Wird in Methode "askDatabase" gebraucht um Daten in DB zu speichern
	private void setData()
	{
		// Abspeicherung in Datenbank
		BenutzerDB.insert(Userdata, Lehrer.isSelected());
	}

	// Wird im Konstruktor aufgerufen und generiert das GUI
	private void generateGUI()
	{
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

	// ActionListener von Knopf "Register" (Registrieren)
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// Listener f�r den Registrierungsbutton
		if (arg0.getSource() == Register)
		{
			if (Lehrer.isSelected())
			{
				System.out.println("Registrieren: Lehrer");
				addLehrer();
			} else
			{
				System.out.println("Registrieren: Lernender");
				addLernender();
			}
		}
	}
}
