package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	/**
	 * aenderung Manfred 14.12.09
	 */
	private static final long serialVersionUID = 3722259148720652383L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	private LAG_LagerMengenErmittlung m_VertragsmengenErmittlung = null;
	private LAG_LagerSaldoErmittlung  m_SaldoErmittlung = null;
	
	private LAG_LIST_BedienPanel oPanel = null;
	public LAG_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGERLISTE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		// übergeordnetes Objekt zum lesen von Lagerdaten
		m_VertragsmengenErmittlung = new LAG_LagerMengenErmittlung();
		m_SaldoErmittlung =  new LAG_LagerSaldoErmittlung();
		
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new LAG_LIST_ComponentMap(m_VertragsmengenErmittlung,m_SaldoErmittlung),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		
		this.oPanel = new LAG_LIST_BedienPanel(oNaviList,new LAG_MASK_BasicModuleContainer());
		
		this.add(oPanel);
		
		
		
		// jetzt noch an die Navigation-List eine Listener anhängen, der immer wenn geblättert wird, die Daten der Lagermengen-Ermittlung aktualisiert.
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		oNaviList.add_actionBeforeListStarted(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
//					ExecINFO_OnlyCodeNavigationList o = (ExecINFO_OnlyCodeNavigationList) oExecInfo;
					LAG_LIST_BasicModuleContainer oThis = LAG_LIST_BasicModuleContainer.this;
					String sIDLager = null;
					String sIDHauptsorte = null;
					String sIDSorte = null;
					
					// aktuelle Auswahl ermitteln, damit die Selektion der 
					sIDLager = oThis.oPanel.get_oLAG_LIST_Selector().get_oSelLager().get_ActualWert();
					sIDHauptsorte = oThis.oPanel.get_oLAG_LIST_Selector().get_oSelHauptartikel().get_ActualWert();
					sIDSorte = oThis.oPanel.get_oLAG_LIST_Selector().get_oSelArtikel().get_ActualWert();
					
					// Die Vertragsdaten nur lesen, wenn auch der Schalter gesetzt ist.
					if( oPanel.get_oLAG_LIST_Selector().get_VertragsdatenAnzeigen()){
						m_VertragsmengenErmittlung.ReadVertragsLagerDaten(sIDLager, sIDHauptsorte, sIDSorte);
					} else {
						m_VertragsmengenErmittlung.ClearData();
					}
					
					// Die Saldodaten auf jeden Fall lesen
					m_SaldoErmittlung.readLagerSaldoDaten();
				}
			}
		});
		
		// das SaldoSummenfeld muss nach dem Eventhandler der Navigationlist gesetzt werden, da sonst die Saldo-Ermittlung
		// zu spaet für die Summenbildung laeuft 
		LAG_SALDO_LIST_Summay 	 oPanelSummary = new LAG_SALDO_LIST_Summay(oNaviList,m_SaldoErmittlung);
		this.add(oPanelSummary);
		
		
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oLAG_LIST_Selector().get_oSelVector().doActionPassiv();
		
	}

		
}
