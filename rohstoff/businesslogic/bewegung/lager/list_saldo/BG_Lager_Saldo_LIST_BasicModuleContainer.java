 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import java.util.ArrayList;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO.ATOM_SALDO_LIST_Summay;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.TRANSLATOR;
import rohstoff.businesslogic.bewegung.lager.saldo.BG_LagerSaldoErmittlung;
import rohstoff.businesslogic.bewegung.lager.vertragsmengen.BG_Lager_KontraktmengenErmittlung;

@Deprecated
public class BG_Lager_Saldo_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES.MARKER_LISTE.db_val();
    
	private BG_Lager_KontraktmengenErmittlung		m_VertragsmengenErmittlung 		= null;
	private BG_LagerSaldoErmittlung  				m_SaldoErmittlung 				= null;
	private ArrayList<BG_LagerSaldoErmittlung> 		m_alSaldoErmittlungAdditional 	= null;
    
    
    public BG_Lager_Saldo_LIST_BasicModuleContainer() throws myException
    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
		// übergeordnetes Objekt zum lesen von Lagerdaten
		m_VertragsmengenErmittlung 		= new BG_Lager_KontraktmengenErmittlung();
		m_SaldoErmittlung 				= new BG_LagerSaldoErmittlung();
		m_alSaldoErmittlungAdditional = new ArrayList<>();

		E2_ComponentMAP oComponentMap = new BG_Lager_Saldo_LIST_ComponentMap(m_VertragsmengenErmittlung, m_SaldoErmittlung,m_alSaldoErmittlungAdditional);
        
        oNaviList.INIT_WITH_ComponentMAP(oComponentMap ,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        BG_Lager_Saldo_LIST_BedienPanel oPanel = new BG_Lager_Saldo_LIST_BedienPanel(oNaviList);
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        
        
		// jetzt noch an die Navigation-List eine Listener anhängen, der immer wenn geblättert wird, die Daten der Lagermengen-Ermittlung aktualisiert.
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		oNaviList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
					BG_Lager_Saldo_LIST_BasicModuleContainer oThis = BG_Lager_Saldo_LIST_BasicModuleContainer.this;
						
					// Die Vertragsdaten nur lesen, wenn auch der Schalter gesetzt ist.
					if(  oPanel.get_list_Selector().get_VertragsdatenAnzeigen() ){
						if (!m_VertragsmengenErmittlung.IsInitialized() ){
							m_VertragsmengenErmittlung.ReadVertragsLagerDaten();
						}
					} else {
						m_VertragsmengenErmittlung.ClearData();
					}
					
					m_SaldoErmittlung.readSaldoDaten();

				}

					
				// weitere Prüfung: zusätzliches Datum für die Saldo-Anzeige
				String Date1ISO = oPanel.get_list_Selector().get_AdditionalDateForSaldo1(true);
				String Date1Formatted = oPanel.get_list_Selector().get_AdditionalDateForSaldo1(false);
				
				String Date2ISO = oPanel.get_list_Selector().get_AdditionalDateForSaldo2(true);
				String Date2Formatted = oPanel.get_list_Selector().get_AdditionalDateForSaldo2(false);
				
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
				oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_bIsVisibleInList(false);
				oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_bIsVisibleInList(false);
				oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_bIsVisibleInList(false);
				
				if (!bibALL.isEmpty( additionalDate1) ){
					BG_LagerSaldoErmittlung oSaldoErmittlungAdditional1 = new BG_LagerSaldoErmittlung();
					oSaldoErmittlungAdditional1.readSaldoDaten(null, null, additionalDate1);
					m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional1);
					
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestand am ").CTrans() + sDate1Description;
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name()).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
					
				}

				
				if (!bibALL.isEmpty( additionalDate2) ){
					BG_LagerSaldoErmittlung oSaldoErmittlungAdditional2 = new BG_LagerSaldoErmittlung();
					oSaldoErmittlungAdditional2.readSaldoDaten(null, null, additionalDate2);
					m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional2);
					
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestand am ").CTrans() + sDate2Description;
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name()).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
				}
				
				
				// Lagerbestandsänderung einblenden
				if (!bibALL.isEmpty(additionalDate1) || !bibALL.isEmpty(additionalDate2)){
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_bIsVisibleInList(true);
					
					// formatiertes Datum holen
					String sHeading = new MyString("Bestandsänderung ").CTrans() + sDeltaDescription;
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
					oCompMapRef.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name()).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
				} 
				
			}
			
		}	);
        
		// das SaldoSummenfeld muss nach dem Eventhandler der Navigationlist gesetzt werden, da sonst die Saldo-Ermittlung
		// zu spaet für die Summenbildung laeuft 
		BG_SALDO_LIST_Summay 	 oPanelSummary = new BG_SALDO_LIST_Summay(oNaviList,m_SaldoErmittlung);
		this.add(oPanelSummary);

		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
