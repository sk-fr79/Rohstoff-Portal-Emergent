package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import java.math.BigDecimal;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LAG_BEW_COL_StatusLager extends MyE2_Column {

	private MyE2_Grid m_oGridInnen = null;
	
	private MyE2_Label m_lblDescMengeGesamt = null;
	private MyE2_Label m_lblDescMengePreiseUngleich0 = null;
	private MyE2_Label m_lblDescMengeNur0Preise = null;
	private MyE2_Label m_lblDescMengeOhnePreise = null;
	private MyE2_Label m_lblDescSumRestwert = null;
	private MyE2_Label m_lblDescAVGRestwertGesamt = null;
	private MyE2_Label m_lblDescAVGWertOhne0Preise = null;
	private MyE2_Label m_lblDescAVGWertMit0Preise = null;
	
	private MyE2_Label m_lblMengeGesamt = null;
	private MyE2_Label m_lblMengePreiseUngleich0 = null;
	private MyE2_Label m_lblMengeNur0Preise = null;
	private MyE2_Label m_lblMengeOhnePreise = null;
	private MyE2_Label m_lblSumRestwert = null;
	private MyE2_Label m_lblAVGRestwertGesamt = null;
	private MyE2_Label m_lblAVGWertOhne0Preise = null;
	private MyE2_Label m_lblAVGWertMit0Preise = null;

		
	private String    m_IDAdresse = null;
	private String 	  m_IDSorte = null;
	private String 	  m_Hauptsorte = null;

	public LAG_BEW_COL_StatusLager() {
		super();
		this.initComponent();
	}

	public LAG_BEW_COL_StatusLager(MutableStyle oStyle) {
		super(oStyle);
		this.initComponent();
	}

	
	
	
	public void set_IDAdresse (String IDAdresse){
		this.m_IDAdresse = IDAdresse;
	}
	
	public void set_IDSorte(String m_IDSorte) {
		this.m_IDSorte = m_IDSorte;
	}

	public void set_Hauptsorte(String m_Hauptsorte) {
		this.m_Hauptsorte = m_Hauptsorte;
	}

	
	
	
	/**
	 * Lesen der Daten und aktualisieren der Anzeige
	 */
	public void refresh() throws myException {

		m_lblMengeGesamt.setText("-") ;
		m_lblMengePreiseUngleich0.setText("-") ;
		m_lblMengeNur0Preise.setText("-") ;
		m_lblMengeOhnePreise.setText("-") ;
		m_lblSumRestwert.setText("-") ;
		m_lblAVGRestwertGesamt.setText("-") ;
		m_lblAVGWertOhne0Preise.setText("-") ;
		m_lblAVGWertMit0Preise.setText("-") ;
		
		LAG_BEW_StatusErmittlung oStatus = new LAG_BEW_StatusErmittlung();
		LAG_BEW_DataRowStatus oRow = oStatus.ErmittleLagerstatus(m_IDAdresse, m_Hauptsorte, m_IDSorte);
		
		
		if (oRow != null){
			m_lblMengeGesamt.setText(BD2String(oRow.getMenge_Gesamt())) ;
			m_lblMengePreiseUngleich0.setText(BD2String(oRow.getMenge_Preise_nicht_Null())) ;
			m_lblMengeNur0Preise.setText(BD2String(oRow.getMenge_Preise_nur_Null())) ;
			m_lblMengeOhnePreise.setText(BD2String(oRow.getMenge_Preise_leer())) ;
			m_lblSumRestwert.setText(BD2String(oRow.getSumme_Restwert())) ;
			m_lblAVGRestwertGesamt.setText(BD2String(oRow.getAvg_Restwert_gesamt())) ;
			m_lblAVGWertMit0Preise.setText(BD2String(oRow.getAvg_Restwert_Menge_Preise_auch_Null())) ;
			m_lblAVGWertOhne0Preise.setText(BD2String(oRow.getAvg_Restwert_Menge_Preise_nicht_Null())) ;
		}
	}
	
	

	private String BD2String(BigDecimal bd ){
		String sRet = "";
		if (bd != null){
			sRet =  bibALL.makeDez(bd.doubleValue(), 2, true);
		}
		return sRet;
	}
	
	
	
	/**
	 * Aufbau der Col mit dem inneren Grid 
	 */
	private void initComponent(){
		Insets oInsets = E2_INSETS.I_4_4_4_4;
		Alignment oAlign = new Alignment(Alignment.RIGHT, Alignment.CENTER);
		
		m_oGridInnen = new MyE2_Grid(8, 1);

		m_lblDescMengeGesamt = new MyE2_Label("Gesamtmenge");
		m_lblDescMengeGesamt.setToolTipText(new MyString("Summe aller nicht verbuchten Wareneingänge, mit und ohne Preise.").CTrans());
		m_lblDescMengePreiseUngleich0 = new MyE2_Label("Menge Preise > 0");
		m_lblDescMengePreiseUngleich0.setToolTipText(new MyString("Summe aller Mengen, die einen Preis > 0 Euro haben.").CTrans());
		
		m_lblDescMengeNur0Preise = new MyE2_Label("Menge 0-Preise");
		m_lblDescMengeNur0Preise.setToolTipText(new MyString("Summe aller Mengen, die einen Preis mit 0 Euro haben.").CTrans());
		m_lblDescMengeOhnePreise = new MyE2_Label("Menge unbepreist");
		m_lblDescMengeOhnePreise.setToolTipText(new MyString("Summe aller Mengen, die noch keine Preise zugeordnet haben.").CTrans());

		m_lblDescSumRestwert = new MyE2_Label("Wert bepreist");
		m_lblDescSumRestwert.setToolTipText(new MyString("Gesamtwert des Lagers. Unbepreiste Mengen werden mit 0 Euro betrachtet.").CTrans());
		
		m_lblDescAVGRestwertGesamt = new MyE2_Label("Ø Gesamt");
		m_lblDescAVGRestwertGesamt.setToolTipText(new MyString("Durchschnittspreis aller Mengen ( Unbepreiste Mengen werden mit 0 Euro betrachtet)").CTrans());
		m_lblDescAVGWertOhne0Preise = new MyE2_Label("Ø ohne 0-Preise");
		m_lblDescAVGWertOhne0Preise.setToolTipText(new MyString("Durchschnittspreis der Mengen, die einen Preis > 0 Euro haben.").CTrans());
		m_lblDescAVGWertMit0Preise = new MyE2_Label("Ø mit 0-Preisen");
		m_lblDescAVGWertMit0Preise.setToolTipText(new MyString("Durchschnittspreis aller bepreisten Mengen. (Es werden auch Mengen berücksichtigt, die mit 0 Euro bepreist sind.)").CTrans());
		
		m_lblMengeGesamt = new MyE2_Label("-");
		m_lblMengePreiseUngleich0 = new MyE2_Label("-");
		m_lblMengeNur0Preise = new MyE2_Label("-");
		m_lblMengeOhnePreise = new MyE2_Label("-");
		
		m_lblSumRestwert = new MyE2_Label("-");
		
		m_lblAVGRestwertGesamt = new MyE2_Label("-");
		m_lblAVGWertOhne0Preise =new MyE2_Label("-");
		m_lblAVGWertMit0Preise = new MyE2_Label("-");
		
		
		
		
		// 1. Zeile Überschriften
		m_oGridInnen.add(m_lblDescMengeGesamt, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescMengePreiseUngleich0 , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescMengeNur0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescMengeOhnePreise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescSumRestwert , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescAVGRestwertGesamt , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescAVGWertOhne0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescAVGWertMit0Preise , 1, 1, oInsets, oAlign);
		
		// 2. Zeile: Forderungen
		m_oGridInnen.add(m_lblMengeGesamt, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblMengePreiseUngleich0 , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblMengeNur0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblMengeOhnePreise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblSumRestwert , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblAVGRestwertGesamt , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblAVGWertOhne0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblAVGWertMit0Preise , 1, 1, oInsets, oAlign);
		
		this.add(m_oGridInnen);
	}
	
	
	/**
	 * gibt das innere Grid zurück, um Properties zu setzen
	 * @return
	 */
	public MyE2_Grid  getInnerGrid(){
		return m_oGridInnen;
	}


	
	
	
}
