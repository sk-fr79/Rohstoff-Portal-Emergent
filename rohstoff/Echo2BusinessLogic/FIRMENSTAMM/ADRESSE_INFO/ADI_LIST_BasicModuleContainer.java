
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO.ADI_CONST.TRANSLATOR;



public class ADI_LIST_BasicModuleContainer extends Project_BasicModuleContainer {

	public static final String NAME_OF_CHECKBOX_IN_LIST = ADI_CONST.ADI_NAMES.CHECKBOX_LISTE.db_val();

	public static final String NAME_OF_LISTMARKER_IN_LIST = ADI_CONST.ADI_NAMES.MARKER_LISTE.db_val();

	private RECORD_ADRESSE  rec_adresse = null;
	
	public ADI_LIST_BasicModuleContainer(RECORD_ADRESSE  p_rec_adresse) throws myException {
		super(TRANSLATOR.LIST.get_modul().get_callKey());

		this.rec_adresse = p_rec_adresse;
		
		this.set_bVisible_Row_For_Messages(true);

		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new ADI_LIST_ComponentMap(this.rec_adresse,oNaviList), E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		ADI_LIST_BedienPanel oPanel = new ADI_LIST_BedienPanel(oNaviList, this.rec_adresse);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}

	
	public RECORD_ADRESSE get_rec_adresse() {
		return rec_adresse;
	}

}
