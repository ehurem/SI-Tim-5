package org.eclipse.wb.swing.Operater;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.jdbc.PreparedStatement;

import controller.UnosZahtjeva;
import controller.UnosZalbe;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainOperater {

	private JFrame frmInterfejsZaOperatera;
	private JTextField textField_2;
	
	//zaposlenik koji je logovan na sistem
	private static Long _zaposlenik;
	
	//the client we make if there is no one in the database already
	private static Klijent _noviKlijent;
	
	//kontroler
	private static UnosZahtjeva _kontroler;
	
	
	
	/**
	 * Launch the application.
	 *  Long zaposlenik je id u bazi logovanog zaposlenika
	 */
	public static void main(String[] args, Long zaposlenik) {
		
		_zaposlenik = zaposlenik;
		_kontroler = new UnosZahtjeva();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainOperater window = new MainOperater();
					window.frmInterfejsZaOperatera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainOperater() {
		initialize();
	}

	/**
	 * Message Box form.
	 */
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private static Boolean validirajPrazno(JTextArea t1) {
		
		if(t1.getText().equals(""))
		{
			t1.setBackground(new Color(216,210,139));
			return false;
		}
		else
		{
			t1.setBackground(new Color(255,255,255));
		}
		
	return true;
	
	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmInterfejsZaOperatera = new JFrame();
		
		final JComboBox comboBox_1 = new JComboBox();
		
		frmInterfejsZaOperatera.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Session sesija = HibernateUtil.getSessionFactory().openSession();
				Transaction t = sesija.beginTransaction();
				
				try
				{
					Query queryKlijent = sesija.createQuery("from Klijent");
					List listKlijent = queryKlijent.list();
				
					for(int i=0;i<listKlijent.size();i++){
						comboBox_1.addItem(listKlijent.get(i));
					}
					t.commit();
				}
				catch(Exception izuzetak)
				{
					infoBox(izuzetak.getMessage(), "Greska u čitanju iz baze !");
				}
				finally
				{
					sesija.close();
				}			
			}
		});
		frmInterfejsZaOperatera.setResizable(false);
		frmInterfejsZaOperatera.setTitle("Interfejs za operatera");
		frmInterfejsZaOperatera.setBounds(100, 100, 364, 475);
		//frmInterfejsZaOperatera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmInterfejsZaOperatera.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Unos zahtjeva", null, panel, null);
		
		
		comboBox_1.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				
				if(_noviKlijent != null){
					comboBox_1.addItem(_noviKlijent);
					comboBox_1.setSelectedItem(_noviKlijent);
					_noviKlijent = null;
				}
				
			}
		});		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNoviKlijent = new JButton("Novi klijent");
		
		btnNoviKlijent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_noviKlijent(new Klijent());
				DodajKlijenta zaDodavanje = new DodajKlijenta();
				zaDodavanje.main(null, get_noviKlijent());
			}
		});
		
		JLabel label_1 = new JLabel("Ime i prezime klijenta:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_2 = new JLabel("Komentar:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_3 = new JLabel("Tip ure\u0111aja:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_4 = new JLabel("Garancija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		final JTextArea textArea = new JTextArea();
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		final JRadioButton radioButton = new JRadioButton("Da");
		radioButton.setSelected(true);
		
		JRadioButton radioButton_1 = new JRadioButton("Ne");
		
		//we need to group JRadioButtons in order to get only one of them selected at the time
		final ButtonGroup grupaGarancija = new ButtonGroup();
		grupaGarancija.add(radioButton);
		grupaGarancija.add(radioButton_1);
		
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		
		
		JButton button = new JButton("Unesi");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Enumeration elementiGrupe = grupaGarancija.getElements();
				Boolean garancija_DA = false;
				while(elementiGrupe.hasMoreElements()){
					JRadioButton dugme = (JRadioButton)elementiGrupe.nextElement();
					if(dugme.isSelected()){
						if(dugme == radioButton) garancija_DA = true;
						break;
					}
				}
				
				//int i = (Integer)(comboBox_3.getSelectedItem());
				
				try {
					Long vratio = UnosZahtjeva.unesiZahtjevUBazu(_zaposlenik, ((Klijent)comboBox_1.getSelectedItem()).getId(), 
						comboBox_3.getSelectedIndex()+1, textField_2, garancija_DA, textArea);
					if(vratio != 0)
					{
						infoBox("Zahtjev"+Long.toString(vratio)+"unesen", "Unesen zahtjev");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					infoBox("Sva polja moraju biti popunjena!", "Neispravan unos");
				}
				
				
				
			}
		});
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(15)
					.addComponent(radioButton)
					.addGap(36)
					.addComponent(radioButton_1)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton)
						.addComponent(radioButton_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblPrioritet = new JLabel("Prioritet:");
		lblPrioritet.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(lblPrioritet, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNoviKlijent)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_3, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_2))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_3, 0, 0, Short.MAX_VALUE))
							.addComponent(comboBox_1, Alignment.LEADING, 0, 160, Short.MAX_VALUE))
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(11)
					.addComponent(btnNoviKlijent)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrioritet))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
							.addComponent(label_2)))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(245)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addGap(16))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Unos \u017Ealbe", null, panel_1, null);
		
		final JTextArea textArea_1 = new JTextArea();
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        
		        comboBox.removeAllItems();
		        comboBox_2.removeAllItems();
		        Session session = HibernateUtil.getSessionFactory().openSession();
		        try {
					
					Transaction t = session.beginTransaction();
					
					Query queryZaposlenik = session.createQuery("from Zaposlenik");
					List listZaposlenik = queryZaposlenik.list();
					
					Query queryKlijent = session.createQuery("from Klijent");
					List listKlijent = queryKlijent.list();
					
					for(int i=0;i<listZaposlenik.size();i++){
					comboBox.addItem(listZaposlenik.get(i));;
					}
					
					for(int i=0;i<listKlijent.size();i++){
						comboBox_2.addItem(listKlijent.get(i));;
						}
					
					t.commit();
					
					
					
				}
				catch (Exception ex) {
					infoBox(ex.toString(), "UZBUNA");
				}
		        finally{
		        	session.close();
		        }
		     
		        
		      }
		    };
		tabbedPane.addChangeListener(changeListener);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnZabiljeialbu = new JButton("Zabilje\u017Ei \u017Ealbu");
		btnZabiljeialbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Long id = UnosZalbe.unesiZalbuUBazu(textArea_1, comboBox, comboBox_2);
					if (id == 0) throw new Exception("Nešto je krenulo po zlu!!!");
					else infoBox("Uspješno dodana žalba", "Poruka");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(201)
							.addComponent(btnZabiljeialbu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addGap(29))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnZabiljeialbu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(24))
		);
		
		JLabel lblZaposlenik = new JLabel("Zaposlenik:");
		lblZaposlenik.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		JLabel lblKomentar = new JLabel("Komentar:");
		lblKomentar.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		JLabel lblKlijent = new JLabel("Klijent:");
		lblKlijent.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblZaposlenik, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
						.addComponent(lblKlijent, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
						.addComponent(lblKomentar, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_2, 0, 186, Short.MAX_VALUE)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(23))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZaposlenik)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKlijent))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKomentar))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		panel_1.setLayout(gl_panel_1);
		frmInterfejsZaOperatera.getContentPane().setLayout(groupLayout);
	}


	public static Klijent get_noviKlijent() {
		return _noviKlijent;
	}

	public static void set_noviKlijent(Klijent _noviKlijent) {
		MainOperater._noviKlijent = _noviKlijent;
	}
	
	
	
	
	


}
