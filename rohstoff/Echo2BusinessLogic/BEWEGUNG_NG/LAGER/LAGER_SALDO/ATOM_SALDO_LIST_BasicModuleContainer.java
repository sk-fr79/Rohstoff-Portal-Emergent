package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import java.util.ArrayList;
import java.util.Date;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.VERTRAGSMENGEN.ATOM_Lager_KontraktmengenErmittlung;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_MASK_BasicModuleContainer;

public class ATOM_SALDO_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	/**
	 * aenderung Manfred 14.12.09
	 */
	private static final long serialVersionUID = 3722259148720652383L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	private ATOM_Lager_KontraktmengenErmittlung 	m_VertragsmengenErmittlung 		= null;
	private ATOM_LagerSaldoErmittlung  				m_SaldoErmittlung 				= null;
	private ArrayList<ATOM_LagerSaldoErmittlung> 	m_alSaldoErmittlungAdditional 	= null;
	
//	private ATOM_LagerSaldoErmittlung  m_SaldoErmittlungAdditional1 		= null;
//	private ATOM_LagerSaldoErmittlung  m_SaldoErmittlungAdditional2 		= null;
	
	
	
	private E2_NavigationList 		   		oNaviList = null;
//	private ATOM_SALDO_LIST_ComponentMap 	oComponentMap = null;

	private ATOM_SALDO_LIST_BedienPanel oPanel = null;
	
	
	public ATOM_SALDO_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_SALDO);
		
		this.set_bVisible_Row_For_Messages(true);
		
		// übergeordnetes Objekt zum lesen von Lagerdaten
		m_VertragsmengenErmittlung 		= new ATOM_Lager_KontraktmengenErmittlung();
		m_SaldoErmittlung 				= new ATOM_LagerSaldoErmittlung();
		
		m_alSaldoErmittlungAdditional = new ArrayList<>();
		
		
		oNaviList = new E2_NavigationList();
		
		E2_ComponentMAP oComponentMap = new ATOM_SALDO_LIST_ComponentMap(m_VertragsmengenErmittlung,m_SaldoErmittlung,m_alSaldoErmittlungAdditional);
		
		oNaviList.INIT_WITH_ComponentMAP(oComponentMap,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		
//		AKTUELL KEINE MASKE NÖTIG
		this.oPanel = new ATOM_SALDO_LIST_BedienPanel(oNaviList,new LAG_MASK_BasicModuleContainer());
		this.add(oPanel);
		
		// jetzt noch an die Navigation-List eine Listener anhängen, der immer wenn geblättert wird, die Daten der Lagermengen-Ermittlung aktualisiert.
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		oNaviList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
					ATOM_SALDO_LIST_BasicModuleContainer oThis = ATOM_SALDO_LIST_BasicModuleContainer.this;
						
					// Die Vertragsdaten nur lesen, wenn auch der Schalter gesetzt ist.
					if( oPanel.get_oLAG_LIST_Selector().get_VertragsdatenAnzeigen()){
						if (!m_VertragsmengenErmittlung.IsInitialized() ){
							m_VertragsmengenErmittlung.ReadVertragsLagerDaten();
						}
					} else {
						m_VertragsmengenErmittlung.ClearData();
					}
					
					m_SaldoErmittlung.readSaldoDaten();

				}
				
				// weitere Prüfung: zusätzliches Datum für die Saldo-Anzeige
				String Date1ISO = oPanel.get_oLAG_LIST_Selector().get_AdditionalDateForSaldo1(true);
				String Date1Formatted = oPanel.get_oLAG_LIST_Selector().get_AdditionalDateForSaldo1(false);
				
				String Date2ISO = oPanel.get_oLAG_LIST_Selector().get_AdditionalDateForSaldo2(true);
				String Date2Formatted = oPanel.get_oLAG_LIST_Selector().get_AdditionalDateForSaldo2(false);
				
				// Datumswerte ordnen, dass immer das größere Datum zuerst kommt, sodass die Liste so aussieht: Saldo aktuell,Datum größer,Datum kleiner
				String additionalDate1 = null;
				String additionalDate2 = null;

				String sDeltaDescription = "";
				String sDate1Description = "";
				String sDate2Description = "";
				
				if (!bibALL.isEmpty( Date1ISO) && !bibALL.isEmpty(Date2ISO)){
					if (Date1ISO.compareTo(Date2ISO)>0){
						additionalDate1 = Date1ISO;
						additionalDate2 = Date2ISO;
						sDeltaDescription = "vom " + Date2Formatted + " bis " + Date1Formatted;
						sDate1Description = Date1Formatted;
						sDate2Description = Date2Formatted;
						
					} else {
						additionalDate1 = Date2ISO;
						additionalDate2 = Date1ISO;
						sDeltaDescription = "vom " + Date1Formatted + " bis " + Date2Formatted;
						sDate1Description = Date2Formatted;
						sDate2Description = Date1Formatted;
					}
					
				} else if (!bibALL.isEmpty( Date1ISO) ){
					additionalDate1 = Date1ISO;
					sDeltaDescription = "vom " + Date1Formatted + " bis aktuell";
					sDate1Description = Date1Formatted;

				} else if (!bibALL.isEmpty( Date2ISO) ){
					additionalDate1 = Date2ISO;
					sDeltaDescription = "vom " + Date2Formatted + " bis aktuell";
					sDate1Description = Date2Formatted;
				}

				
				
				E2_ComponentMAP oCompMapRef =  oNaviList.get_oComponentMAP__REF();
				
				m_alSaldoErmittlungAdditional.clear();
				oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_bIsVisibleInList(false);
				oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_bIsVisibleInList(false);
				oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_bIsVisibleInList(false);
				
				if (!bibALL.isEmpty( additionalDate1) ){
					ATOM_LagerSaldoErmittlung oSaldoErmittlungAdditional1 = new ATOM_LagerSaldoErmittlung();
					oSaldoErmittlungAdditional1.readSaldoDaten(null, null, additionalDate1);
					m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional1);
					
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestand am ").CTrans() + sDate1Description;
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
					
				}

				
				if (!bibALL.isEmpty( additionalDate2) ){
					ATOM_LagerSaldoErmittlung oSaldoErmittlungAdditional2 = new ATOM_LagerSaldoErmittlung();
					oSaldoErmittlungAdditional2.readSaldoDaten(null, null, additionalDate2);
					m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional2);
					
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestand am ").CTrans() + sDate2Description;
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
				}
				
				
				// Lagerbestandsänderung einblenden
				if (!bibALL.isEmpty(additionalDate1) || !bibALL.isEmpty(additionalDate2)){
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestandsänderung ").CTrans() + sDeltaDescription;
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
				} 
				
			}
			
		});

		
		// das SaldoSummenfeld muss nach dem Eventhandler der Navigationlist gesetzt werden, da sonst die Saldo-Ermittlung
		// zu spaet für die Summenbildung laeuft 
		ATOM_SALDO_LIST_Summay 	 oPanelSummary = new ATOM_SALDO_LIST_Summay(oNaviList,m_SaldoErmittlung);
		this.add(oPanelSummary);
		
		
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oLAG_LIST_Selector().get_oSelVector().doActionPassiv();
		
	}

		
}
