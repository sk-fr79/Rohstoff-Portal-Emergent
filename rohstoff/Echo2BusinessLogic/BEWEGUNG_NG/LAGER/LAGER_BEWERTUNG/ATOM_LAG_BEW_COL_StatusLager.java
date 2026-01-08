package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler_KALKULATORISCH;

public class ATOM_LAG_BEW_COL_StatusLager extends MyE2_Column {

	private MyE2_Grid m_oGridInnen = null;
	
	private MyE2_Label m_lblDescMengeGesamt = null;
	private MyE2_Label m_lblDescMengePreiseUngleich0 = null;
	private MyE2_Label m_lblDescMengeNur0Preise = null;
	private MyE2_Label m_lblDescSumRestwert = null;
	private MyE2_Label m_lblDescAVGRestwertGesamt = null;
	private MyE2_Label m_lblDescAVGWertOhne0Preise = null;
	
	private MyE2_Label m_lblMengeGesamt = null;
	private MyE2_Label m_lblMengePreiseUngleich0 = null;
	private MyE2_Label m_lblMengeNur0Preise = null;
	private MyE2_Label m_lblSumRestwert = null;
	private MyE2_Label m_lblAVGRestwertGesamt = null;
	private MyE2_Label m_lblAVGWertOhne0Preise = null;

	// Kontrollsaldo
	private MyE2_Label m_lblDescSaldo = null;
	private MyE2_Label m_lblSaldo = null;
	
	private MyE2_Button m_btnSetzkasten = null;	
	
	private String    	m_IDAdresse = null;
	private String 	  	m_IDSorte = null;
	private String 	  	m_Hauptsorte = null;
	
	private Vector<String> m_vIDAdressen = null;
	private Vector<String> m_vIDHauptsorten = null;
	private Vector<String> m_vIDSorten = null;
	
	private boolean 	m_bKostenberuecksichtigung = false;

	
	public ATOM_LAG_BEW_COL_StatusLager(boolean bKostenberuecksichtigung) {
		super();
		m_bKostenberuecksichtigung = bKostenberuecksichtigung;
		this.initComponent();
	}

	
	public ATOM_LAG_BEW_COL_StatusLager(MutableStyle oStyle) {
		super(oStyle);
		this.initComponent();
	}

	
	
	public void set_IDAdresse (String IDAdresse){
		this.m_IDAdresse = IDAdresse;
	}
	
	public void set_vIDAdressen (Vector<String> vIDAdressen){
		this.m_vIDAdressen = vIDAdressen;
	}
	
	public void set_IDSorte(String m_IDSorte) {
		this.m_IDSorte = m_IDSorte;
	}
	
	public void set_vIDSorten( Vector<String> vIDSorten){
		m_vIDSorten = vIDSorten;
	}

	public void set_Hauptsorte(String m_Hauptsorte) {
		this.m_Hauptsorte = m_Hauptsorte;
	}

	public void set_vIDHauptSorten( Vector<String> vIDHauptSorten){
		m_vIDHauptsorten = vIDHauptSorten;
	}
	
	
	/** 
	 * Löscht Adress und Sorteninfos und refresht die Komponente
	 * @throws myException
	 */
	public void clear() throws myException{
		m_IDAdresse = null;
		m_IDSorte = null;
		m_Hauptsorte = null;
		m_vIDAdressen = null;
		m_vIDSorten = null;
		m_vIDHauptsorten = null;
		
		this.refresh();
	}
	
	/**
	 * Lesen der Daten und aktualisieren der Anzeige
	 */
	public void refresh() throws myException {

		m_lblMengeGesamt.setText("-") ;
		m_lblMengePreiseUngleich0.setText("-") ;
		m_lblMengeNur0Preise.setText("-") ;
		m_lblSumRestwert.setText("-") ;
		m_lblAVGRestwertGesamt.setText("-") ;
		m_lblAVGWertOhne0Preise.setText("-") ;
	
		m_lblSaldo.setText("-");
		
		if (bibALL.isEmpty(m_Hauptsorte) && bibALL.isEmpty(m_IDSorte)
				&& (m_vIDSorten == null || m_vIDSorten.size() == 0) 
				&& (m_vIDHauptsorten == null || m_vIDHauptsorten.size() == 0)){
			return;
		}
	
		// Lagerstatus
		ATOM_LAG_BEW_StatusErmittlung_Ext oStatus = new ATOM_LAG_BEW_StatusErmittlung_Ext(m_bKostenberuecksichtigung);
		ATOM_LAG_BEW_DataRowStatus oRow  = oStatus.ErmittleLagerstatus(m_IDAdresse, m_vIDAdressen, m_Hauptsorte , m_vIDHauptsorten, m_IDSorte, m_vIDSorten, null);
		if (oRow != null){
			m_lblMengeGesamt.setText(BD2String(oRow.getMenge_Gesamt())) ;
			m_lblMengePreiseUngleich0.setText(BD2String(oRow.getMenge_Preise_nicht_Null())) ;
			m_lblMengeNur0Preise.setText(BD2String(oRow.getMenge_Preise_nur_Null())) ;
			m_lblSumRestwert.setText(BD2String(oRow.getSumme_Restwert())) ;
			m_lblAVGRestwertGesamt.setText(BD2String(oRow.getAvg_Restwert_gesamt())) ;
			m_lblAVGWertOhne0Preise.setText(BD2String(oRow.getAvg_Restwert_Menge_Preise_nicht_Null())) ;
		}
		
		// Zur kontrolle den Saldo lesen und anzeigen. 
		ATOM_LagerSaldoErmittlung  oSaldo = new ATOM_LagerSaldoErmittlung();
		oSaldo.readSaldoDaten(m_IDAdresse, m_vIDAdressen, m_Hauptsorte, m_vIDHauptsorten, m_IDSorte,m_vIDSorten, null, null, false, true, true, false);
		BigDecimal bdSaldo = BigDecimal.ZERO;
		bdSaldo = oSaldo.getSumOfAllSaldoValuesFound();
//		bibMSG.add_MESSAGE(new MyE2_Info_Message(bdSaldo.toPlainString()));
		
		// Saldo
		if (bdSaldo != null){
			m_lblSaldo.setText(BD2String(bdSaldo));
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
		
		m_oGridInnen = new MyE2_Grid(6, 1);

		m_lblDescMengeGesamt 				= new MyE2_Label("Gesamt unverb. WE");
		m_lblDescMengeGesamt.setToolTipText(new MyString("Summe aller nicht verbuchten Wareneingänge, mit und ohne Preise. Negative Bestände werden nicht berücksichtigt.").CTrans());
		
		m_lblDescMengePreiseUngleich0 		= new MyE2_Label("Menge bepreist");
		m_lblDescMengePreiseUngleich0.setToolTipText(new MyString("Summe aller Mengen, die einen Preis haben.").CTrans());
		
		m_lblDescMengeNur0Preise 			= new MyE2_Label("Menge unbepreist");
		m_lblDescMengeNur0Preise.setToolTipText(new MyString("Summe aller Mengen, die keinen Preis haben.").CTrans());

		m_lblDescSumRestwert 				= new MyE2_Label("Wert bepreist");
		m_lblDescSumRestwert.setToolTipText(new MyString("Gesamtwert des Lagers. Unbepreiste Mengen werden mit 0 Euro betrachtet.").CTrans());
		
		m_lblDescAVGRestwertGesamt 			= new MyE2_Label("Ø Gesamt");
		m_lblDescAVGRestwertGesamt.setToolTipText(new MyString("Durchschnittspreis aller Mengen ( Unbepreiste Mengen werden mit 0 Euro betrachtet)").CTrans());
		
		m_lblDescAVGWertOhne0Preise 		= new MyE2_Label("Ø bepreiste Mengen");
		m_lblDescAVGWertOhne0Preise.setToolTipText(new MyString("Durchschnittspreis der Mengen, die einen Preis > 0 Euro haben.").CTrans());
		
		m_lblMengeGesamt 					= new MyE2_Label("-");
		m_lblMengePreiseUngleich0 			= new MyE2_Label("-");
		m_lblMengeNur0Preise 				= new MyE2_Label("-");
		
		m_lblSumRestwert 					= new MyE2_Label("-");
		
		m_lblAVGRestwertGesamt 				= new MyE2_Label("-");
		m_lblAVGWertOhne0Preise 			= new MyE2_Label("-");
		
		// Kontrollsaldo
		m_lblDescSaldo 						= new MyE2_Label("Saldo Gesamt:");
		m_lblSaldo 							= new MyE2_Label("-");
		
		m_btnSetzkasten 					= new MyE2_Button(new MyString("Setzkasten aktualisieren"));
		m_btnSetzkasten.add_oActionAgent(new cActionAgentButtonSetzkasten());
		
		
		// 1. Zeile Überschriften
		m_oGridInnen.add(m_lblDescMengeGesamt, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescMengePreiseUngleich0 , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescMengeNur0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescSumRestwert , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescAVGRestwertGesamt , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescAVGWertOhne0Preise , 1, 1, oInsets, oAlign);
		
		// 2. Zeile: WE-Daten
		m_oGridInnen.add(m_lblMengeGesamt, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblMengePreiseUngleich0 , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblMengeNur0Preise , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblSumRestwert , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblAVGRestwertGesamt , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblAVGWertOhne0Preise , 1, 1, oInsets, oAlign);
		
		
		// 2. Zeile: Saldo
		m_oGridInnen.add(m_lblDescSaldo,1,1,oInsets,oAlign);
		m_oGridInnen.add(m_lblSaldo,4,1,oInsets,oAlign);
		m_oGridInnen.add(m_btnSetzkasten,1,1,oInsets,oAlign);
		this.add(m_oGridInnen);
		
	}
	
	
	/**
	 * gibt das innere Grid zurück, um Properties zu setzen
	 * @return
	 */
	public MyE2_Grid  getInnerGrid(){
		return m_oGridInnen;
	}


	/**
	 * ActionAgent für die Setzkasten-Aktualisierung
	 * @author manfred
	 *
	 */
	private class cActionAgentButtonSetzkasten extends XX_ActionAgent{
		ATOM_SetzkastenHandler oHandler = null;
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_BEW_COL_StatusLager oThis = ATOM_LAG_BEW_COL_StatusLager.this;
			if (oThis.m_bKostenberuecksichtigung){
				oHandler = new ATOM_SetzkastenHandler_KALKULATORISCH(null);
			} else {
				oHandler = new ATOM_SetzkastenHandler(null);
			}
			
			String sDatum = myDateHelper.ChangeNormalString2DBFormatString(bibALL.get_cDateNOW());
			
			if (!bibALL.isEmpty(m_IDAdresse) &&	!bibALL.isEmpty(m_IDSorte) ){
				// wenn adresse und sorte angegeben ist, dann nur diese Sorte verarbeiten
				oHandler.ReorganiseLagerEntries(/*bibALL.get_ID_MANDANT(),*/ m_IDAdresse, m_IDSorte, sDatum, null);
			} else if (!bibALL.isEmpty(m_IDAdresse) ){
				// die ganze Adresse 
				oHandler.ReorganiseLagerEntries(/*bibALL.get_ID_MANDANT(),*/ m_IDAdresse, sDatum, false);
			} else {
				// sonst den ganzen Mandanten
				oHandler.ReorganiseLagerEntries(bibALL.get_ID_MANDANT(), sDatum, false);
			}
			
			// refresh der daten
			oThis.refresh();
		}
		
	}
	
	
	
}
