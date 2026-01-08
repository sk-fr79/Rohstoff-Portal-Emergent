package rohstoff.businesslogic.bewegung2.lager_saldo;

import java.util.ArrayList;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_Ermittlung;
import rohstoff.businesslogic.bewegung2.lager.vertragsmengen.B2_LAG_KontraktmengenErmittlung;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_CONST.TRANSLATOR;


public class B2_LAG_SALDO_LIST_BasicModuleContainer extends Project_BasicModuleContainer {

	private RB_TransportHashMap   tpHashMap = null;

	private B2_LAG_KontraktmengenErmittlung			m_VertragsmengenErmittlung 		= null;
	private B2_LAG_SALDO_Ermittlung  				m_SaldoErmittlung 				= null;
	private ArrayList<B2_LAG_SALDO_Ermittlung> 		m_alSaldoErmittlungAdditional 	= null;

	private B2_LAG_SALDO_LIST_Summary 				oSummary						= null;
	
	public B2_LAG_SALDO_LIST_BasicModuleContainer() throws myException    {

		super(TRANSLATOR.LIST.get_modul().get_callKey());

		this.tpHashMap = new RB_TransportHashMap();

		this.set_bVisible_Row_For_Messages(true);

		E2_NavigationList oNaviList = new E2_NavigationList();

		this.tpHashMap._setModulContainerList(this);
		this.tpHashMap._setNavigationList(oNaviList);
		this.tpHashMap._setLeadingMaskKey(B2_LAG_SALDO_CONST.getLeadingMaskKey());
		this.tpHashMap._setLeadingTableOnMask(B2_LAG_SALDO_CONST.getLeadingTable());

		this.m_SaldoErmittlung 			= new B2_LAG_SALDO_Ermittlung();
		this.m_VertragsmengenErmittlung = new B2_LAG_KontraktmengenErmittlung();
		this.m_alSaldoErmittlungAdditional = new ArrayList<>();
		
		oNaviList.INIT_WITH_ComponentMAP(new B2_LAG_SALDO_LIST_ComponentMap(
				this.tpHashMap
				,this.m_VertragsmengenErmittlung
				,this.m_SaldoErmittlung        		
				,this.m_alSaldoErmittlungAdditional
				),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());

		B2_LAG_SALDO_LIST_BedienPanel oPanel = new B2_LAG_SALDO_LIST_BedienPanel(this.tpHashMap);

		oNaviList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){

					E2_ComponentMAP oCompMapRef =  oNaviList.get_oComponentMAP__REF();
					
					// Die Vertragsdaten nur lesen, wenn auch der Schalter gesetzt ist.
					if( oPanel.get_list_Selector().get_VertragsdatenAnzeigen() ){
						if (!m_VertragsmengenErmittlung.IsInitialized() ){
							m_VertragsmengenErmittlung.ReadVertragsLagerDaten();
						}
					} else {
						m_VertragsmengenErmittlung.ClearData();
					}
					boolean bShow_vertragsdaten = oPanel.get_list_Selector().get_VertragsdatenAnzeigen();
					
		  			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.name()).EXT().set_bIsVisibleInList(bShow_vertragsdaten);
		  			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.name()).EXT().set_bIsVisibleInList(bShow_vertragsdaten);
		  			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.name()).EXT().set_bIsVisibleInList(bShow_vertragsdaten);
		  			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.name()).EXT().set_bIsVisibleInList(bShow_vertragsdaten);
		  			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.name()).EXT().set_bIsVisibleInList(bShow_vertragsdaten);
		  			

					
					
					m_SaldoErmittlung.readSaldoDaten();

					// weitere Prüfung: zusätzliches Datum für die Saldo-Anzeige
					String Date1ISO = 		oPanel.get_list_Selector().get_AdditionalDateForSaldo1(true);
					String Date1Formatted = oPanel.get_list_Selector().get_AdditionalDateForSaldo1(false);

					String Date2ISO = 		oPanel.get_list_Selector().get_AdditionalDateForSaldo2(true);
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


					
        		

        		m_alSaldoErmittlungAdditional.clear();
        		oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_1.name()).EXT().set_bIsVisibleInList(false);
        		oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_2.name()).EXT().set_bIsVisibleInList(false);
        		oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DELTA.name()).EXT().set_bIsVisibleInList(false);

        		if (!bibALL.isEmpty( additionalDate1) ){
        			B2_LAG_SALDO_Ermittlung oSaldoErmittlungAdditional1 = new B2_LAG_SALDO_Ermittlung();
        			oSaldoErmittlungAdditional1.readSaldoDaten(null, null, additionalDate1);
        			m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional1);

        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_1.name()).EXT().set_bIsVisibleInList(true);

        			// formatiertes Datum holen
        			String sHeading = "Bestand am " + sDate1Description;
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_1.name()).EXT().set_oCompTitleInList(new RB_lab(sHeading));
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_1.name()).EXT().set_cList_or_Mask_Titel(S.ms(sHeading));

        		}


        		if (!bibALL.isEmpty( additionalDate2) ){
        			B2_LAG_SALDO_Ermittlung oSaldoErmittlungAdditional2 = new B2_LAG_SALDO_Ermittlung();
        			oSaldoErmittlungAdditional2.readSaldoDaten(null, null, additionalDate2);
        			m_alSaldoErmittlungAdditional.add(oSaldoErmittlungAdditional2);

        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_2.name()).EXT().set_bIsVisibleInList(true);

        			// formatiertes Datum holen
        			String sHeading = "Bestand am "+ sDate2Description;
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_2.name()).EXT().set_oCompTitleInList(new RB_lab()._t(sHeading));
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_2.name()).EXT().set_cList_or_Mask_Titel(S.ms(sHeading));
        		}


        		// Lagerbestandsänderung einblenden
        		if (!bibALL.isEmpty(additionalDate1) || !bibALL.isEmpty(additionalDate2)){
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DELTA.name()).EXT().set_bIsVisibleInList(true);

        			// formatiertes Datum holen
        			String sHeading = new MyString("Bestandsänderung ").CTrans() + sDeltaDescription;
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DELTA.name()).EXT().set_oCompTitleInList(new MyE2_Label(sHeading));
        			oCompMapRef.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DELTA.name()).EXT().set_cList_or_Mask_Titel(new MyString(sHeading));
        		} 
        		
        		
      
					 
			}
		}
		});

		this.tpHashMap._setListBedienPanel(oPanel);

		this.add(oPanel, E2_INSETS.I_2_2_2_2);

		this.oSummary = new B2_LAG_SALDO_LIST_Summary(oNaviList, m_SaldoErmittlung);

		this.add(oSummary);

		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}

}


