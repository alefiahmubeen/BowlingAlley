import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddPartyView implements AddPartyViewInterface, ActionListener, ListSelectionListener {
	private final int maxSize;
	private final JFrame win;
	private final JButton addPatron;
	private final JButton newPatron;
	private final JButton remPatron;
	private final JButton finished;
	public final JList<String> partyList;
	public final JList<Object> allBowlers;
	public final Vector<String> party;
	public Vector<Object> bowlerdb;
	private final ControlDeskView controlDesk;
	private String selectedNick;
	private String selectedMember;

	public AddPartyView(ControlDeskView controlDesk, int max) {
		this.controlDesk = controlDesk;
		this.maxSize = max;
		this.win = ViewComponents.MakeWindow("Add Party");
		JPanel colPanel = ViewComponents.GridLayoutPanel(1, 3);
		JPanel partyPanel = ViewComponents.FlowLayoutPanel();
		partyPanel.setBorder(new TitledBorder("Your Party"));
		this.party = new Vector();
		Vector<String> empty = new Vector();
		empty.add("(Empty)");
		this.partyList = new JList(empty);
		this.partyList.setFixedCellWidth(120);
		this.partyList.setVisibleRowCount(6);
		this.partyList.addListSelectionListener(this);
		JScrollPane partyPane = new JScrollPane(this.partyList);
		partyPanel.add(partyPane);
		JPanel bowlerPanel = new JPanel();
		bowlerPanel.setLayout(new FlowLayout());
		bowlerPanel.setBorder(new TitledBorder("Bowler Database"));

		try {
			this.bowlerdb = new Vector(BowlerFile.getBowlers());
		} catch (Exception var10) {
			System.err.println("File Error");
			this.bowlerdb = new Vector();
		}

		this.allBowlers = new JList(this.bowlerdb);
		this.allBowlers.setVisibleRowCount(8);
		this.allBowlers.setFixedCellWidth(120);
		JScrollPane bowlerPane = new JScrollPane(this.allBowlers);
		bowlerPane.setVerticalScrollBarPolicy(22);
		this.allBowlers.addListSelectionListener(this);
		bowlerPanel.add(bowlerPane);
		JPanel buttonPanel = ViewComponents.GridLayoutPanel(4, 1);
		this.addPatron = ViewComponents.MakeButtons("Add to Party", buttonPanel);
		this.addPatron.addActionListener(this);
		this.remPatron = ViewComponents.MakeButtons("Remove Member", buttonPanel);
		this.remPatron.addActionListener(this);
		this.newPatron = ViewComponents.MakeButtons("New Patron", buttonPanel);
		this.newPatron.addActionListener(this);
		this.finished = ViewComponents.MakeButtons("Finished", buttonPanel);
		this.finished.addActionListener(this);
		colPanel.add(partyPanel);
		colPanel.add(bowlerPanel);
		colPanel.add(buttonPanel);
		ViewComponents.AddContentsToWindow(this.win, colPanel);
		ViewComponents.SetWindowPosition(this.win);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.addPatron)) {
			this.funAddPatron();
		}

		if (e.getSource().equals(this.remPatron) && this.selectedMember != null) {
			this.party.removeElement(this.selectedMember);
			this.partyList.setListData(this.party);
		}

		if (e.getSource().equals(this.newPatron)) {
			new NewPatronView(this);
		}

		if (e.getSource().equals(this.finished)) {
			if (this.party != null && this.party.size() > 0) {
				this.controlDesk.updateAddParty(this);
			}

			this.win.setVisible(false);
		}

	}

	public void funAddPatron() {
		if (this.selectedNick != null && this.party.size() < this.maxSize) {
			if (this.party.contains(this.selectedNick)) {
				System.err.println("Member already in Party");
			} else {
				this.party.add(this.selectedNick);
				this.partyList.setListData(this.party);
			}
		}

	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(this.allBowlers)) {
			this.selectedNick = (String)((JList)e.getSource()).getSelectedValue();
		}

		if (e.getSource().equals(this.partyList)) {
			this.selectedMember = (String)((JList)e.getSource()).getSelectedValue();
		}

	}

	public Vector<String> getParty() {
		return this.party;
	}
}
