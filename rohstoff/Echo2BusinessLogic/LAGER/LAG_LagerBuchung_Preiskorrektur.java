package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Float;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


/**
 * Dialog zum korrigieren eines schon getätigten Lagerpreises
 * @author manfred
 * @date   07.02.2012
 */
public class LAG_LagerBuchung_Preiskorrektur extends Project_BasicModuleContainer
{
	
	
	private static final long serialVersionUID = -4029048585112361906L;

	
	// Parameter-Werte
	// wenn aufgerufen aus MaskeLager-Bestand
	private E2_NavigationList				m_NavListRecordLagerKonto 	= null;

	
	// Wenn aufgerufen aus Lager-Inventur
	private String 							m_IDLagerKonto 			= null;
	private RECORD_LAGER_KONTO				m_RecQuelle		= null;
	private RECORD_LAGER_KONTO				m_RecZiel		= null;
	
	private boolean 						m_bInitOk		= false;					
	
	Vector<String> 							m_vSqlStatements = null;
	VECTOR_UB_FIELDS						m_vUBFields = new VECTOR_UB_FIELDS();
	
	// Variablen
	
//	private MyE2_Label		  			tfLager				= null;
	private MyE2_Label 					lblLager		 	= null;
	
	private MyE2_Label 		  			tfMengeAltQuelle	= null;
	private MyE2_Label 					lblMengeAltQuelle 	= null;
	
	private MyE2_Label					tfMengeAltZiel		= null;
	private MyE2_Label 					lblMengeAltZiel		= null;

	private MyE2_Label 		  			tfPreisAltQuelle	= null;
	private MyE2_Label 					lblPreisAltQuelle 	= null;
	
	private MyE2_Label					tfPreisAltZiel		= null;
	private MyE2_Label 					lblPreisAltZiel		= null;

	
	private UB_TextField				tfPreisNeu			= null;
	private MyE2_Label 					lblPreisNeu			= null;
	
	private MyE2_Label					tfDatum 		  	= null;
	private MyE2_Label 					lblDatum			= null;
	
	private MyE2_Label					tfSorteQuelle		= null;
	private MyE2_Label 					lblSorteQuelle		= null;
	private MyE2_LabelWrap				tfBemerkungQuelle 	= null;
	
	private MyE2_Label					tfSorteZiel			= null;
	private MyE2_Label 					lblSorteZiel		= null;
	private MyE2_LabelWrap				tfBemerkungZiel 	= null;
	
	private MyE2_Button					oButtonRefreshLagerPreis = null;

	private MyE2_Button  				oButtonSaveBuchung 	= null;
	private MyE2_Button  				oButtonCancel 		= null;
	
	MyE2_Grid 							oGrid		 		= null;

	
	
	


	/**
	 * Erfassungsmaske wird initialisiert für die verwendung einer direkten Buchung, ohne NavigationList
	 * Es ist immer eine einfache Korrekturbuchung
	 * @param IdLager
	 * @param IdSorte
	 * @param bdMenge
	 * @param dtBuchung
	 * @throws myException
	 */
	public LAG_LagerBuchung_Preiskorrektur(String IdLagerKonto, E2_NavigationList navListToRefresh ) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_ERFASSUNG);
		
		m_vSqlStatements = new Vector<String>();
		this.m_NavListRecordLagerKonto = navListToRefresh;
		this.m_IDLagerKonto = IdLagerKonto;
		
		this.initMask();

		this.initData();
		
	}

	

	private void initMask() throws myException {
		
		E2_MutableStyle oStyleCheckboxNoBorder = new E2_MutableStyle();
		oStyleCheckboxNoBorder.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		// initialisieren der einzelnen Objekte
		this.lblLager			= new MyE2_Label(new MyE2_String("Lager"),MyE2_Label.STYLE_TITEL_NORMAL());
		this.lblDatum 			= new MyE2_Label(new MyE2_String("Buchungsdatum:"));
		
		this.lblSorteQuelle 	= new MyE2_Label(new MyE2_String("Ursprungs-Sorte:"));
		this.lblMengeAltQuelle 	= new MyE2_Label(new MyE2_String("Menge:"));
		this.lblSorteZiel  		= new MyE2_Label(new MyE2_String("Ziel-Sorte:"));
		this.lblMengeAltZiel 	= new MyE2_Label(new MyE2_String("Menge:"));
		this.lblPreisAltQuelle	= new MyE2_Label(new MyE2_String("Preis:"));
		this.lblPreisAltZiel	= new MyE2_Label(new MyE2_String("Preis:"));
		this.lblPreisNeu 		= new MyE2_Label(new MyE2_String("Neuer Preis:"));
		
		this.tfDatum 			= new MyE2_Label("-");

		this.tfSorteQuelle		= new MyE2_Label(new MyE2_String("-"),MyE2_Label.STYLE_TITEL_NORMAL());
		this.tfMengeAltQuelle	= new MyE2_Label("-");
		this.tfPreisAltQuelle	= new MyE2_Label("-");
		this.tfBemerkungQuelle 	= new MyE2_LabelWrap("-");

		this.tfSorteZiel		= new MyE2_Label(new MyE2_String("-"),MyE2_Label.STYLE_TITEL_NORMAL());
		this.tfMengeAltZiel		= new MyE2_Label("-");
		this.tfPreisAltZiel		= new MyE2_Label("-");
		this.tfBemerkungZiel	= new MyE2_LabelWrap("-");
		
		this.tfPreisNeu			= new UB_TextField("PREIS_NEU",false,"",80,15);
		this.m_vUBFields.add(tfPreisNeu);
		
		oButtonSaveBuchung 		= new MyE2_Button("Preiskorrektur ausführen");
		oButtonSaveBuchung.setFont(new E2_FontBold(1));
		oButtonSaveBuchung.add_oActionAgent(new actionSaveBuchung());

		
		oButtonCancel 			= new MyE2_Button("Abbrechen");
		oButtonCancel.setFont(new E2_FontBold(1));
		oButtonCancel.add_oActionAgent(new actionCancelBuchung());
		
		MyE2_String oToolTipText = null;

			
		this.oButtonRefreshLagerPreis = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),E2_ResourceIcon.get_RI("reload__.png"));
		this.oButtonRefreshLagerPreis.add_oActionAgent(new actionRefreshLagerPreis());
		oToolTipText = new MyE2_String("Durchschnittlicher Lagerpreis ermitteln.");
		this.oButtonRefreshLagerPreis.setToolTipText(oToolTipText.toString());
			
		// input-Feld-Validierer
		this.tfPreisNeu.add_InputValidator(new VALIDATE_INPUT_Float());
		this.tfPreisNeu.set_bEmptyAllowd(false);
		this.tfPreisNeu.set_bEnabled_For_Edit(true);
		this.tfPreisNeu.set_StyleForInput(true);
		

		MyE2_Row oRowQuelle = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());


		MyE2_Row oRowButtonBuchung = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowButtonBuchung.add(oButtonSaveBuchung);
		oRowButtonBuchung.add(oButtonCancel,E2_INSETS.I_10_0_0_0);
		
		
		Alignment al = new Alignment(Alignment.RIGHT,Alignment.CENTER);
		
		Insets oIn = new Insets(4,1,2,1);
		Insets oInSep = new Insets(0,5,0,0);

		
		
		//
		// Layout
		//
		MyE2_Grid oGridBase = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
//		MyE2_Grid oGridBase = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		// Zeile Lager
		oGridBase.add(lblLager,5,  E2_INSETS.I_2_2_2_2);
		
		// Zeile Datum
		oGridBase.add(lblDatum,1,1, E2_INSETS.I_0_2_5_2,al);
		oGridBase.add(tfDatum,1, E2_INSETS.I_2_2_2_2);
		oGridBase.add(new MyE2_Label(""),3,E2_INSETS.I_2_2_2_2);

		// Sep.
		oGridBase.add(new Separator(),5,oInSep);	
		
		// Zeile Quellsorte
		oGridBase.add(lblSorteQuelle,1,1, E2_INSETS.I_0_2_5_2,al);
		oGridBase.add(tfSorteQuelle,4, E2_INSETS.I_2_2_2_2);
		
		// Zeile Quellsorte Bemerkung
		oGridBase.add(new MyE2_Label(""),1, E2_INSETS.I_0_2_5_2);
		oGridBase.add(tfBemerkungQuelle ,4, E2_INSETS.I_2_2_2_2);
		
		
		// Zeile Quelle Menge Preis
		oGridBase.add(lblMengeAltQuelle,1,1, E2_INSETS.I_0_2_5_2,al);
		oGridBase.add(tfMengeAltQuelle,1, E2_INSETS.I_2_2_2_2);
		oGridBase.add(lblPreisAltQuelle,1,1, E2_INSETS.I_2_2_2_2,al);
		oGridBase.add(tfPreisAltQuelle,1, E2_INSETS.I_2_2_2_2);
		oGridBase.add(new MyE2_Label(""),3,E2_INSETS.I_2_2_2_2);

		// Sep.
		oGridBase.add(new Separator(),5,oInSep);	

		// Zeile Zielsorte
		oGridBase.add(lblSorteZiel,1,1, E2_INSETS.I_0_2_5_2,al);
		oGridBase.add(tfSorteZiel,4, E2_INSETS.I_2_2_2_2);

		// Zeile Quellsorte Bemerkung
		oGridBase.add(new MyE2_Label(""),1, E2_INSETS.I_0_2_5_2);
		oGridBase.add(tfBemerkungZiel ,4, E2_INSETS.I_2_2_2_2);

		
		// Zeile Ziel Menge Preis
		oGridBase.add(lblMengeAltZiel,1,1, E2_INSETS.I_0_2_5_2,al);
		oGridBase.add(tfMengeAltZiel,1, E2_INSETS.I_2_2_2_2);
		oGridBase.add(lblPreisAltZiel,1,1, E2_INSETS.I_2_2_2_2,al);
		oGridBase.add(tfPreisAltZiel,1, E2_INSETS.I_2_2_2_2);
		oGridBase.add(new MyE2_Label(""),3,E2_INSETS.I_2_2_2_2);

		// Sep.
		oGridBase.add(new Separator(),5,oInSep);	

		// Preise
		MyE2_Row oRowPreis = new MyE2_Row();
		oGridBase.add(lblPreisNeu,1,1,E2_INSETS.I_0_2_5_2,al);
		oRowPreis.add(tfPreisNeu,E2_INSETS.I_0_0_5_0);
		oRowPreis.add(oButtonRefreshLagerPreis,E2_INSETS.I_0_0_5_0);
		oGridBase.add(oRowPreis,4,E2_INSETS.I_2_2_2_2);
		
	
		// Sep.
		oGridBase.add(new Separator(),5,oInSep);	

		
		// Buttons
		oGridBase.add(oRowButtonBuchung,5,E2_INSETS.I_2_20_2_0);
		

				
		this.add(oGridBase,E2_INSETS.I_2_2_2_2);

		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (m_NavListRecordLagerKonto!= null){
					m_NavListRecordLagerKonto.RefreshList();
				}
			}
		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Korrektur eines Lagerpreises"));
	}
	
	
	
	
	
		
	
	/**
	 * setzen der Daten bei der Initialisierung der Maske
	 * @throws myException 
	 */
	private void initData() throws myException{
		
		if (m_IDLagerKonto == null){
			return;
		}
		
		
		// prüfen, ob der gewählte Satz der Ursprungssatz oder Zielsatz ist
		RECORD_LAGER_KONTO oRec = new RECORD_LAGER_KONTO(m_IDLagerKonto);
		
		// U- Umbuchung (2 Buchungen) K-Korrekturbuchung (1 Buchung)
		boolean bUmbuchung = oRec.get_BUCHUNG_HAND_cUF_NN("-").equalsIgnoreCase("U");
		
		if (bUmbuchung){
			
			// 1. Select um den Timestamp mit Sekunden zu ermitteln
			String sSQL = "SELECT to_char(ERZEUGT_AM, 'yyyy-mm-dd-HH24:MI:SS')  " +
			" FROM " + bibE2.cTO() +".jt_lager_konto WHERE jt_lager_konto.ID_LAGER_KONTO =  "  + m_IDLagerKonto + 
			" AND NVL(STORNO,'N') = 'N' ";
			
			String sTimestamp = bibDB.EinzelAbfrage(sSQL);
			
			
			// 2. die Datensätze selektieren
			String sIDLager = oRec.get_ID_ADRESSE_LAGER_cUF(); 

			String sSQL_get_IDs = "SELECT * from JT_LAGER_KONTO  " +
					" WHERE to_char(ERZEUGT_AM, 'yyyy-mm-dd-HH24:MI:SS') =  '" + sTimestamp + "' " +
					" AND ID_ADRESSE_LAGER = " + sIDLager + 
					" AND NVL(STORNO,'N') = 'N' " +
					" ORDER BY JT_LAGER_KONTO.MENGE " +
					"";  
			RECLIST_LAGER_KONTO listKTO = new RECLIST_LAGER_KONTO(	"to_char(ERZEUGT_AM, 'yyyy-mm-dd-HH24:MI:SS') =  '" + sTimestamp + "' " +
																	" AND NVL(STORNO,'N') = 'N' "  +
																	" AND ID_ADRESSE_LAGER = " + sIDLager, 
																	"MENGE");
			
			if (listKTO.size() < 1 ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Buchung konnte nicht initialisiert werden.")));
			}
			
			// Quell-Ziel initialisieren
			m_RecQuelle = listKTO.get(0);
			
			if (listKTO.size() > 1){
				m_RecZiel = listKTO.get(1);
			}
			
		} else {
			
			m_RecQuelle = oRec;
		}

		
		
		// füllen der Angaben
		if (m_RecQuelle != null){
			String sLager = 
			m_RecQuelle.get_UP_RECORD_ADRESSE_id_adresse_lager().get_NAME1_cUF() + " " +
			m_RecQuelle.get_UP_RECORD_ADRESSE_id_adresse_lager().get_NAME2_cUF_NN("") + ", " +
			m_RecQuelle.get_UP_RECORD_ADRESSE_id_adresse_lager().get_ORT_cUF() 
			;
			this.lblLager.setText(sLager);
			this.tfDatum.setText(m_RecQuelle.get_ERZEUGT_AM_cF());
			
			String sSorteQuell = m_RecQuelle.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ANR1_cF() + "-" +
							m_RecQuelle.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ARTBEZ1_cF() + " " +
							m_RecQuelle.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ARTBEZ2_cF() ;
			this.tfSorteQuelle.setText(sSorteQuell) ;
			this.tfBemerkungQuelle.setText(m_RecQuelle.get_BEMERKUNG_cUF());
			this.tfMengeAltQuelle.setText(m_RecQuelle.get_MENGE_cF());
			this.tfPreisAltQuelle .setText(m_RecQuelle.get_PREIS_cF());
			m_bInitOk = true;
		}
		
		if (m_RecZiel != null){
			String sSorteZiel = m_RecZiel.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ANR1_cF() + "-" +
			m_RecZiel.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ARTBEZ1_cF() + " " +
			m_RecZiel.get_UP_RECORD_ARTIKEL_id_artikel_sorte().get_ARTBEZ2_cF() ;
			
			this.tfSorteZiel.setText(sSorteZiel);
			this.tfBemerkungZiel.setText( m_RecZiel.get_BEMERKUNG_cUF());
			this.tfMengeAltZiel.setText(m_RecZiel.get_MENGE_cF());
			this.tfPreisAltZiel.setText(m_RecZiel.get_PREIS_cF());
			m_bInitOk = true;
		}
		
	}
	
	
	

	
	
	
	/**
	 * Ermittelt den Durchschnittslagerpreis 
	 * der auf dem lager liegenden Werte
	 * @author manfred
	 * @throws myException 
	 * @date 25.06.2009
	 */
	private void getLagerPreis() throws myException{
		String sIDLager = null;
		String sIDSorte = null;
		String sIDLagerKonto = null;
		
		sIDSorte = m_RecQuelle.get_ID_ARTIKEL_SORTE_cUF();
		sIDLager = m_RecQuelle.get_ID_ADRESSE_LAGER_cUF();
		sIDLagerKonto = m_RecQuelle.get_ID_LAGER_KONTO_cUF();
		
		BigDecimal bdMenge = m_RecQuelle.get_MENGE_bdValue(BigDecimal.ZERO).abs();
		
		
		// einen Tag vor dem eigentlichen Datum
		String sDatum = m_RecQuelle.get_BUCHUNGSDATUM_VALUE_FOR_SQLSTATEMENT();
		sDatum = sDatum.replaceAll("'", "");
		
		
		String sPreis = null;
		if (sIDSorte != null && sIDLager != null){
			if (bdMenge.equals(BigDecimal.ZERO)){
				bdMenge = null;
			}
			
			// ermitteln der Preise, der Waren, die noch auf dem Lager liegen
			BigDecimal dPreis = null;
			dPreis = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte, 1, 10, sDatum, sIDLagerKonto );
			
			if (dPreis == null){
				// zu dem angegebenen Zeitpunkt war keine Preisermittlung möglich
				BigDecimal dPreisAll = LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(sIDLager, sIDSorte,1, 1000 , null, sIDLagerKonto);
				if (dPreisAll != null){
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte kein Lagerpreis zum angegebenen Zeitpunkt ermittelt werden. " +
						"Der gesamte Durchschnittspreis beträgt: " + dPreisAll.toPlainString() ));
				}
			}
			
			
			if (dPreis != null){
				sPreis = bibALL.convertBigDecimalToString(dPreis,2);
				
			} else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte kein Lagerpreis ermittelt werden, da keine verbuchbare Mengen auf dem Lager vorhanden sind."));
			}
		} 

		this.tfPreisNeu.setText(sPreis);
	}

	
	
	
	/**
	 * Eventhandler-Klasse zum manuellen refreshen der Saldo-Stände
	 * @author manfred
	 *
	 */
	private class actionRefreshLagerPreis extends XX_ActionAgent
	{

		/***
		 * Ermitteln des aktuellen Saldos für die gewählte Lager/Sorte-Kombination	
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			LAG_LagerBuchung_Preiskorrektur oThis = LAG_LagerBuchung_Preiskorrektur.this;
			oThis.getLagerPreis();
		}
	}
	
	
	/**
	 * Cancel-Action
	 * @author manfred
	 * @date   07.02.2012
	 */
	private class actionCancelBuchung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung wurde abgebrochen.")));
			CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	/**
	 * Speichern-Action
	 * @author manfred
	 * @date   07.02.2012
	 */
	private class actionSaveBuchung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			if (m_bInitOk){
				
				m_vSqlStatements.clear();
				
				bibMSG.add_MESSAGE(m_vUBFields.get_MV_AllFieldsAreOK_ShowErrorInput());
				
				if (bibMSG.get_bIsOK()){
				// den Preis formatieren
				String sPreis = tfPreisNeu.getText();
				
				BigDecimal bdPreis = bibALL.convertTextToBigDecimal(sPreis);
				if (bdPreis == null){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist kein gültiger Preis eingegeben!")));
					return;
				}
				
				sPreis = MyNumberFormater.formatDez(bdPreis.toPlainString(), 2, true);
				
				
				Vector<String> vDoNotUpdate = DB_STATICS.get_AutomaticWrittenFields_STD();
				vDoNotUpdate.add("BUCHUNGSDATUM");
				vDoNotUpdate.add("BUCHUNGSZEIT");
				vDoNotUpdate.add("ID_MANDANT");
				
				
				if (m_RecQuelle != null){
					m_RecQuelle.set_NEW_VALUE_PREIS(sPreis);
					m_RecQuelle.set_NEW_VALUE_IST_GEAENDERT("Y");
					m_vSqlStatements.add(m_RecQuelle.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
				}
				
				if (m_RecZiel != null){
					m_RecZiel.set_NEW_VALUE_PREIS(sPreis);
					m_RecZiel.set_NEW_VALUE_IST_GEAENDERT("Y");
					m_vSqlStatements.add(m_RecZiel.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
				}
				
				boolean bRet = true;
				MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSqlStatements, true);
				bRet &= mv.get_bIsOK();
				
				if (bRet){
					bibDB.Commit();
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der neue Preis wurde gespeichert.")));
					
					CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
				} else {
					bibDB.Rollback();
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Buchung konnte nicht gespeichert werden")));
					if (mv.size() > 0){
						bibMSG.add_MESSAGE(mv.firstElement());
					}
				}
				
				}
				
			}
		
		}

	}



	
	
}
