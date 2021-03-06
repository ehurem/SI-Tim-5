package org.eclipse.wb.swing.Serviser;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import Models.Klijent;
import Models.Zahtjev;
import Models.Zaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class serviser {

	private JFrame frmInterfejsZaServisera;
	private JTable table;
	private static ArrayList<Zahtjev> _zahtjevi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serviser window = new serviser();
					window.frmInterfejsZaServisera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Show()
	{
		frmInterfejsZaServisera.setVisible(true);
	}
	
	/**
	 * Create the application.
	 */
	public serviser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Hard coded list of Zahtjev for prototype functionality purpose 
		ArrayList<Zahtjev> lz = new ArrayList<Zahtjev>();
		
		Zahtjev z1 = new Zahtjev();
		
		Date dat = new Date(System.currentTimeMillis());
		
		Klijent _klijent = new Klijent();
		_klijent.set_imeIPrezime("Imenko");
		_klijent.setEmail("imenko@test.ba");
		_klijent.setBrojTelefona("061 123-456");
		_klijent.set_adresa("Ulica klijenta prvog");
		Zaposlenik _zaposlenik = new Zaposlenik();
		_zaposlenik.setAdresa("Adresa Stanovanja 5");
		_zaposlenik.setBrojTelefona("061 321-654");
		_zaposlenik.setEmail("zaposlenik@test.ba");
		_zaposlenik.setIme("Nadimko");
		_zaposlenik.setPrezime("Nadimkovic");
		
		z1.setID(1);
		z1.setGarancija(true);
		z1.setTipUredaja("Samsunka");
		z1.setDatumOtvaranja(dat);
		z1.setKlijent(_klijent);
		z1.setZaposlenik(_zaposlenik);
		z1.setKomentar("komentar");
		z1.setStatus("Otvoren");
		z1.setPrioritet(1);
		lz.add(z1);
		
		Zahtjev z2 = new Zahtjev();
		
		Date dat2 = new Date(System.currentTimeMillis());
		
		Klijent _klijent2 = new Klijent();
		_klijent2.set_imeIPrezime("Imenkovev");
		_klijent2.set_adresa("Ulica klijenta drugog");
		_klijent2.setEmail("imenkovev@test.ba");
		_klijent2.setBrojTelefona("061 098-345");
		
		Zaposlenik _zaposlenik2 = new Zaposlenik();
		_zaposlenik2.setAdresa("Bulevar Nestanovanja 5");
		_zaposlenik2.setBrojTelefona("065 987-679");
		_zaposlenik2.setEmail("zaposlenik2@test.ba");
		_zaposlenik2.setIme("Nadimkovec");
		_zaposlenik2.setPrezime("Nadimkovicevic");
		
		z2.setID(2);
		z2.setGarancija(true);
		z2.setTipUredaja("Sonijevka");
		z2.setDatumOtvaranja(dat2);
		z2.setKlijent(_klijent2);
		//z2.setZaposlenik(_zaposlenik2);
		z2.setKomentar("komentar");
		z2.setStatus("Otvoren");
		z2.setPrioritet(0);
		lz.add(z2);
		
		Zahtjev z3 = new Zahtjev();
		
		Date dat3 = new Date(System.currentTimeMillis());
		
		Klijent _klijent3 = new Klijent();
		_klijent3.set_imeIPrezime("Nemenko");
		_klijent3.set_adresa("Ulica klijenta treceg");
		_klijent3.setEmail("neimenko@test.ba");
		_klijent3.setBrojTelefona("062 112-223");
		
		Zaposlenik _zaposlenik3 = new Zaposlenik();
		_zaposlenik3.setAdresa("Trg Stanovanja 25");
		_zaposlenik3.setBrojTelefona("063 322-090");
		_zaposlenik3.setEmail("nezaposlenik@test.ba");
		_zaposlenik3.setIme("Nenadimko");
		_zaposlenik3.setPrezime("Nenadimkovic");
		
		z3.setID(3);
		z3.setGarancija(false);
		z3.setTipUredaja("Panasonka");
		z3.setDatumOtvaranja(dat3);
		z3.setKlijent(_klijent3);
		//z3.setZaposlenik(_zaposlenik3);
		z3.setKomentar("komentar");
		z3.setStatus("Otvoren");
		z3.setPrioritet(1);
		lz.add(z3);
		set_zahtjevi(lz);
		frmInterfejsZaServisera = new JFrame();
		frmInterfejsZaServisera.setTitle("Interfejs za servisera");
		frmInterfejsZaServisera.setBounds(100, 100, 332, 357);
		frmInterfejsZaServisera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmInterfejsZaServisera.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Pregled otvorenih zahtjeva", null, panel, null);
		// dugme za odabir otvorenih zahtjeva i stavljanje u status izvrsavanja
		JButton btnOdaberi = new JButton("Odaberi");
		btnOdaberi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=-1;
			 i=table.getSelectedRow();  
			 if(i>=0) {
			//JOptionPane.showMessageDialog(table, "odabran je red "+(i+1));
				 String s = table.getValueAt(i, 0).toString();
		        for (int j=0; j<get_zahtjevi().size(); j++) {
		        	 if (get_zahtjevi().get(j).getID()==Integer.parseInt(s)) {
		        		get_zahtjevi().get(j).setStatus("U Izvrsavanju");
		        		((DefaultTableModel)table.getModel()).removeRow(i);
		        		JOptionPane.showMessageDialog(table, "Uspje�no ste uzeli zahtjev na izvr�avanje!");
		        		 break;
		        	 } 
		         }
			 }
			 else JOptionPane.showMessageDialog(table, "niste odabrali nijedan red");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOdaberi)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOdaberi)
					.addContainerGap(42, Short.MAX_VALUE))
		);
	    DefaultTableModel t = new DefaultTableModel() {
	   	    // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){return false;}
	   	    
	    };
		table = new JTable();
		table.setModel(t);
		t.addColumn("ID Zahtjeva");
		t.addColumn("Prioritet");
		for (int i=0; i<get_zahtjevi().size(); i++) {
			if(get_zahtjevi().get(i).getStatus()=="Otvoren")
		t.addRow(new Object[] {get_zahtjevi().get(i).getID(), get_zahtjevi().get(i).getPrioritet()});
		}
	    // dozvoliti selekciju samo jednog reda u tabeli
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);
		// klasa za event za klik na zaglavlje tabele
		class TableHeaderMouseListener extends MouseAdapter {
		     
		    private JTable table;
		     
		    public TableHeaderMouseListener(JTable table) {
		        this.table = table;
		    }
		     
		    public void mouseClicked(MouseEvent event) {
		        Point point = event.getPoint();
		        int column = table.columnAtPoint(point);
		        if (column==0) 
		        JOptionPane.showMessageDialog(table, "Sortira po IDu zahtjeva (nije implementirano)");
		        else 
		        	JOptionPane.showMessageDialog(table, "Sortira po prioritetu zahtjeva (nije implementirano)");
		    }
		}
		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new TableHeaderMouseListener(table));
		// event za dvostruki klik na red tabele
		 table.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
				   JTable target = (JTable)e.getSource();
			      if (e.getClickCount() == 2) {
			         int row = target.getSelectedRow();
			         int column = target.getSelectedColumn();
			         String s = target.getValueAt(row, 0).toString();
			         int index = 0;
			        for (int i=0; i<get_zahtjevi().size(); i++) {
			        	 if (get_zahtjevi().get(i).getID()==Integer.parseInt(s)) {
			        		 index=i;
			        		 break;
			        	 } 
			         }
			         PregledOdabranogZahtjeva forma = new PregledOdabranogZahtjeva();
			         forma.main(null, get_zahtjevi().get(index));
			         
			         }
			      
			   }
			});
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Zatvaranje zahtjeva", null, panel_1, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		final JList list = new JList();
		final DefaultListModel lm = new DefaultListModel ();
		list.setModel(lm);
		JButton btnNewButton = new JButton("Odaberi");
		/* Launches ZatvaranjeZahtjeva.java with selected Zahtjev */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Code is here
			     String s = list.getSelectedValue().toString();
			     int index=0;
		        for (int i=0; i<get_zahtjevi().size(); i++) {
		        	 if (get_zahtjevi().get(i).getID()==Integer.parseInt(s)) {
		        		 index=i;
		        		 break;
		        	 } 
		         }
				ZatvaranjeZahtjeva jf = new ZatvaranjeZahtjeva();
				jf.main(null, get_zahtjevi().get(index));
				//frmInterfejsZaServisera.dispose();
				//jf.Show();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		
		JLabel lblZahtjeviUIzvavanju = new JLabel("Zahtjevi u izvr\u0161avanju");
		
	

		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		       if(index==1) {
		    	   lm.removeAllElements();
		    	   for (int i=0; i<get_zahtjevi().size(); i++) {
		   			if(get_zahtjevi().get(i).getStatus()=="U Izvrsavanju")
		   		lm.addElement(get_zahtjevi().get(i).getID());
		   		}
		       }
		      }
		    };
		    tabbedPane.addChangeListener(changeListener);
		
	/*	list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Zahtjev 1", "Zahtjev 2", "Zahtjev 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(lblZahtjeviUIzvavanju, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblZahtjeviUIzvavanju)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
		frmInterfejsZaServisera.getContentPane().setLayout(groupLayout);
	}

	public static ArrayList<Zahtjev> get_zahtjevi() {
		return _zahtjevi;
	}

	public static void set_zahtjevi(ArrayList<Zahtjev> _zahtjevi) {
		serviser._zahtjevi = _zahtjevi;
	}
}
