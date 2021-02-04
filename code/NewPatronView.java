
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class NewPatronView implements ActionListener {
	private JFrame win;
	private JButton abort;
	private JButton finished;
	private JLabel fullLabel;
	private JLabel emailLabel;
	private JTextField nickField;
	private JTextField fullField;
	private JTextField emailField;
	private String nick;
	private String full;
	private String email;
	private boolean done;
	private String selectedNick;
	private String selectedMember;
	private AddPartyView addParty;

	public NewPatronView(AddPartyView v) {
		this.addParty = v;
		this.done = false;
		this.win = new JFrame("Add Patron");
		this.win.getContentPane().setLayout(new BorderLayout());
		((JPanel)this.win.getContentPane()).setOpaque(false);
		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());
		JPanel patronPanel = new JPanel();
		patronPanel.setLayout(new GridLayout(3, 1));
		patronPanel.setBorder(new TitledBorder("Your Info"));
		JPanel nickPanel = new JPanel();
		nickPanel.setLayout(new FlowLayout());
		JLabel nickLabel = new JLabel("Nick Name");
		this.nickField = new JTextField("", 15);
		nickPanel.add(nickLabel);
		nickPanel.add(this.nickField);
		JPanel fullPanel = new JPanel();
		fullPanel.setLayout(new FlowLayout());
		this.fullLabel = new JLabel("Full Name");
		this.fullField = new JTextField("", 15);
		fullPanel.add(this.fullLabel);
		fullPanel.add(this.fullField);
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		this.emailLabel = new JLabel("E-Mail");
		this.emailField = new JTextField("", 15);
		emailPanel.add(this.emailLabel);
		emailPanel.add(this.emailField);
		patronPanel.add(nickPanel);
		patronPanel.add(fullPanel);
		patronPanel.add(emailPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));
		this.finished = new JButton("Add Patron");
		JPanel finishedPanel = new JPanel();
		finishedPanel.setLayout(new FlowLayout());
		this.finished.addActionListener(this);
		finishedPanel.add(this.finished);
		this.abort = new JButton("Abort");
		JPanel abortPanel = new JPanel();
		abortPanel.setLayout(new FlowLayout());
		this.abort.addActionListener(this);
		abortPanel.add(this.abort);
		buttonPanel.add(abortPanel);
		buttonPanel.add(finishedPanel);
		colPanel.add(patronPanel, "Center");
		colPanel.add(buttonPanel, "East");
		this.win.getContentPane().add("Center", colPanel);
		this.win.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.win.setLocation(screenSize.width / 2 - this.win.getSize().width / 2, screenSize.height / 2 - this.win.getSize().height / 2);
		this.win.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.abort)) {
			this.done = true;
			this.win.dispose();
		}

		if (e.getSource().equals(this.finished)) {
			this.nick = this.nickField.getText();
			this.full = this.fullField.getText();
			this.email = this.emailField.getText();
			this.done = true;
		}

	}

	public String getNick() {
		return this.nick;
	}

	public String getFull() {
		return this.full;
	}

	public String getEmail() {
		return this.email;
	}
}
