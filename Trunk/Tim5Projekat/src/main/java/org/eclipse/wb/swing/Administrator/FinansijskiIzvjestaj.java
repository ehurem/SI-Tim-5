package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;





import controller.IzvjestajiKontroler;

import java.util.*;

import Models.Zahtjev;

import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FinansijskiIzvjestaj {

	private JDialog frmFinansijskiIzvjestaj;
	private JTable table;
	private JTextField textField;
	//kreiranje liste zahtjeva
	private static  java.util.List<Zahtjev> zahtjevi;
	private static int broj;
	private static double zarada;
	private static IzvjestajiKontroler kontroler;


	/**
	 * Launch the application.
	 */
	public static void main(final int broj_sedmice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					broj=broj_sedmice+1;
					zarada=0;
					FinansijskiIzvjestaj window = new FinansijskiIzvjestaj();
					window.frmFinansijskiIzvjestaj.setVisible(true);
					//dodavanje zahtjeva u listu iz baze
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinansijskiIzvjestaj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private void initialize() {
		//dodavanje zahtjeva u listu iz baze
		try {
		zahtjevi = kontroler.iscitajListuZahtjevaIzBaze();
		//org.eclipse.wb.swing.Administrator.Main.infoBox(zahtjevi.size()+ "", "Broj zahtjeva");
		}
		catch(Exception ex)
		{
			infoBox(ex.getLocalizedMessage(), "Greška");
		}
		//izracunavanje ukupne zarade za prikazane zahtjeve
		try {
	    zarada= IzvjestajiKontroler.sabiranjeCijenaZahtjevaZaOdabranuSedmicu(zahtjevi, broj);
		}
		catch(Exception ex)
		{
			infoBox(ex.getLocalizedMessage(), "Greška");
		}
		frmFinansijskiIzvjestaj = new JDialog();
		frmFinansijskiIzvjestaj.setResizable(false);
		frmFinansijskiIzvjestaj.setTitle("Finansijski izvjestaj");
		frmFinansijskiIzvjestaj.setBounds(100, 100, 367, 346);
		frmFinansijskiIzvjestaj.setModalityType(ModalityType.APPLICATION_MODAL);
		//frmFinansijskiIzvjestaj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblUkupnaZaradaOdabrane = new JLabel("Ukupna zarada odabrane sedmice:");

		textField = new JTextField(String.valueOf(zarada));
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmFinansijskiIzvjestaj.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblUkupnaZaradaOdabrane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUkupnaZaradaOdabrane)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		 final DefaultTableModel tmodel = new DefaultTableModel() {
	    	 // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){
	    		return false;
	    		}
	   	    
	    };
	    table = new JTable();
		table.setModel(tmodel);
		tmodel.addColumn("ID Zahtjeva");
		tmodel.addColumn("Datum zatvaranja zahtjeva");
		tmodel.addColumn("Iznos naplate");
		table.getColumnModel().getColumn(1).setPreferredWidth(151);
		scrollPane.setViewportView(table);
		frmFinansijskiIzvjestaj.getContentPane().setLayout(groupLayout);
		//ispis zahtjeva u tabelu
		try {
		for (int i=0;i<zahtjevi.size();i++){
			if (zahtjevi.get(i).getDatumZatvaranja()!=null){
			Calendar c = kontroler.dateToCalendar(zahtjevi.get(i).getDatumZatvaranja());
			if (c.get(Calendar.WEEK_OF_YEAR)==broj) {
				tmodel.addRow(new Object[] {(zahtjevi.get(i).getID()), (zahtjevi.get(i).getDatumZatvaranja()), (zahtjevi.get(i).get_cijena())} );
				}
		}	
		}
		}
		catch(Exception ex)
		{
			infoBox("Nema zahtjeva u odabranoj sedmici", "Greška");
			//JOptionPane.showMessageDialog(null, "Nema zahtjeva u odabranoj sedmici", "InfoBox: " + ex.toString(), JOptionPane.INFORMATION_MESSAGE);
		}
	
	}

	private java.util.List<Zahtjev> getZahtjevi() {
		return zahtjevi;
	}

	private void setZahtjevi(java.util.List<Zahtjev> zahtjevi) {
		this.zahtjevi = zahtjevi;
	}
}
