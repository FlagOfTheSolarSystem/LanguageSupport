package LanguageSupport.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import LanguageSupport.api.*;

public class Panel extends JPanel {

	private static final long serialVersionUID = 3581394766130844367L;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public Panel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JScrollPane iLPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, iLPane, 10, SpringLayout.NORTH, this);
		iLPane.setBorder(
				new CompoundBorder(
						new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Disabled Languages",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
								new LineBorder(new Color(130, 135, 144))));
		springLayout.putConstraint(SpringLayout.WEST, iLPane, 31, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, iLPane, 112, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, iLPane, 187, SpringLayout.WEST, this);
		add(iLPane);

		final JList<String> inactiveLanguages = new JList<>();
		inactiveLanguages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inactiveLanguages.setFixedCellWidth(8);
		iLPane.setViewportView(inactiveLanguages);
		inactiveLanguages.setVisibleRowCount(5);
		inactiveLanguages.setModel(new ListModelExtended() {
			String[] values = new String[] { "German (Deutsch)", "French (Francais)",
					"Russian (\u0420\u0443\u0441\u0441\u043A\u0438\u0439)", "Croatian (Hrvatski)",
			"Italian (Italiano)" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}

			public void addValue(String string) {
				String[] newValues = new String[values.length + 1];
				for (int i = 0; i < values.length; i++) {
					newValues[i] = values[i];
					newValues[i + 1] = string;
				}
				this.values = newValues;
			}

			public void setValue(String[] string) {
				this.values = string;
			}

			// TODO: delValue 1
			public void delValue(int index) {
				if (values.length <= 1) {
					this.values = new String[0];
				} else {
					String[] newValues = new String[values.length - 1];
					for (int i = 0; i < values.length - 1; i++) {
						if (i == index) {
							newValues[i] = values[i + 1];
						} else if (i > index) {
							newValues[i] = values[i + 1];
						} else if (i < index) {
							newValues[i] = values[i];
						}
					}
					this.values = newValues;
				}
			}

			public boolean isEmpty() {
				return values.length == 0;
			}
		});

		JScrollPane aLPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, aLPane, 0, SpringLayout.NORTH, iLPane);
		aLPane.setBorder(
				new CompoundBorder(
						new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enabled Languages",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
								new LineBorder(new Color(130, 135, 144))));
		springLayout.putConstraint(SpringLayout.SOUTH, aLPane, 112, SpringLayout.NORTH, this);
		add(aLPane);

		final JList<String> activeLanguages = new JList<String>();
		activeLanguages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		activeLanguages.setFixedCellWidth(8);
		aLPane.setViewportView(activeLanguages);
		activeLanguages.setVisibleRowCount(5);
		activeLanguages.setModel(new ListModelExtended() {
			String[] values = new String[] { "Polish (Polski)" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}

			public void addValue(String string) {
				String[] newValues = new String[values.length + 1];
				for (int i = 0; i < values.length; i++) {
					newValues[i] = values[i];
					newValues[i + 1] = string;
				}
				this.values = newValues;
			}

			public void setValue(String[] string) {
				this.values = string;
			}

			// TODO: delValue 2
			public void delValue(int index) {
				if (values.length <= 1) {
					this.values = new String[0];
				} else {
					String[] newValues = new String[values.length - 1];
					for (int i = 0; i < values.length - 1; i++) {
						if (i == index) {
							newValues[i] = values[i + 1];
						} else if (i > index) {
							newValues[i] = values[i + 1];
						} else if (i < index) {
							newValues[i] = values[i];
						}
					}
					this.values = newValues;
				}
			}

			public boolean isEmpty() {
				return values.length == 0;
			}
		});

		JButton toActive = new JButton("");
		springLayout.putConstraint(SpringLayout.NORTH, toActive, 24, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, toActive, 6, SpringLayout.EAST, iLPane);
		springLayout.putConstraint(SpringLayout.EAST, toActive, 52, SpringLayout.EAST, iLPane);
		toActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inactiveLanguages.isSelectionEmpty()) {
					// Do nothing...
				} else {
					int i = inactiveLanguages.getSelectedIndex();
					ListModelExtended modelI = (ListModelExtended) inactiveLanguages.getModel();
					ListModelExtended modelA = (ListModelExtended) activeLanguages.getModel();

					if (!(modelI.isEmpty())) {
						if (modelA.isEmpty()){
							String selectedLang = (String) modelI.getElementAt(i);
							modelA.setValue(new String[]{selectedLang});
							modelI.delValue(i);
							inactiveLanguages.updateUI();
							activeLanguages.updateUI();
						} else {
							String selectedLang = (String) modelI.getElementAt(i); //FIXME: Error occur here
							modelA.addValue(selectedLang);
							modelI.delValue(i);
							inactiveLanguages.updateUI();
							activeLanguages.updateUI();
						}
					}
				}
			}
		});
		toActive.setIcon(new ImageIcon(Panel.class.getResource("/LanguageSupport/images/Arrow2.png")));
		add(toActive);

		JButton fromActive = new JButton("");
		springLayout.putConstraint(SpringLayout.WEST, aLPane, 8, SpringLayout.EAST, fromActive);
		springLayout.putConstraint(SpringLayout.EAST, aLPane, 162, SpringLayout.EAST, fromActive);
		springLayout.putConstraint(SpringLayout.SOUTH, fromActive, 112, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, toActive, -6, SpringLayout.NORTH, fromActive);
		springLayout.putConstraint(SpringLayout.WEST, fromActive, 6, SpringLayout.EAST, iLPane);
		springLayout.putConstraint(SpringLayout.NORTH, fromActive, 71, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, fromActive, 52, SpringLayout.EAST, iLPane);
		fromActive.setIcon(new ImageIcon(Panel.class.getResource("/LanguageSupport/images/Arrow.png")));
		fromActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activeLanguages.isSelectionEmpty()) {
					// Do nothing...
				} else {
					int i = activeLanguages.getSelectedIndex();
					ListModelExtended modelI = (ListModelExtended) inactiveLanguages.getModel();
					ListModelExtended modelA = (ListModelExtended) activeLanguages.getModel();

					if (!(modelA.isEmpty())) {
						if (modelI.isEmpty()) {
							String selectedLang = (String) modelA.getElementAt(i);
							modelI.setValue(new String[]{selectedLang});
							modelA.delValue(i);
							inactiveLanguages.updateUI();
							activeLanguages.updateUI();
						} else {
							String selectedLang = (String) modelA.getElementAt(i);
							modelI.addValue(selectedLang);
							modelA.delValue(i);
							inactiveLanguages.updateUI();
							activeLanguages.updateUI();
						}
					}
				}
			}
		});
		add(fromActive);

		JScrollPane cPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, cPane, 6, SpringLayout.SOUTH, iLPane);
		springLayout.putConstraint(SpringLayout.WEST, cPane, 31, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, cPane, 182, SpringLayout.SOUTH, iLPane);
		springLayout.putConstraint(SpringLayout.EAST, cPane, 187, SpringLayout.WEST, this);
		cPane.setAutoscrolls(true);
		cPane.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Console",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
				new LineBorder(new Color(130, 135, 144))));
		add(cPane);

		final JTextPane console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Consolas", Font.PLAIN, 12));
		console.setText("test\r\ntest\r\ntest");
		console.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				console.setText(console.getText() + "\n");
			}
		});
		console.setBorder(null);
		springLayout.putConstraint(SpringLayout.NORTH, console, 31, SpringLayout.SOUTH, iLPane);
		springLayout.putConstraint(SpringLayout.WEST, console, 0, SpringLayout.WEST, iLPane);
		cPane.setViewportView(console);

		JComboBox<String> selectChar = new JComboBox<>();
		springLayout.putConstraint(SpringLayout.NORTH, selectChar, 21, SpringLayout.SOUTH, aLPane);
		springLayout.putConstraint(SpringLayout.WEST, selectChar, 0, SpringLayout.WEST, aLPane);
		selectChar.setModel(new DefaultComboBoxModel<String>(new String[] { "\u00DF - Eszett (German (Deutsch))" }));
		add(selectChar);

		JButton editChar = new JButton("Edit character...");
		springLayout.putConstraint(SpringLayout.NORTH, editChar, 6, SpringLayout.SOUTH, selectChar);
		springLayout.putConstraint(SpringLayout.WEST, editChar, 0, SpringLayout.WEST, aLPane);
		add(editChar);

		JButton editName = new JButton("Edit character name...");
		springLayout.putConstraint(SpringLayout.NORTH, editName, 6, SpringLayout.SOUTH, editChar);
		springLayout.putConstraint(SpringLayout.WEST, editName, 0, SpringLayout.WEST, aLPane);
		add(editName);

		JButton btnNewButton = new JButton("Set assossiated language...");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, aLPane);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Set keyboard shortcut...\r\n");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, editName);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, aLPane);
		add(btnNewButton_1);
	}
}