package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_Const.ListBuildType;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_dataPrepare;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.Fill_Bewegungs_Vektor_Temp_IDS;

public class FZ_NavigationList extends E2_NavigationList  implements E2_NavigationList_dataPrepare {

	private FZ_NavigationList_SelectFieldDetailStatus  statusSelector = null; 
	
	public FZ_NavigationList() throws myException {
		super();
		this.statusSelector = new FZ_NavigationList_SelectFieldDetailStatus(this);
		this.add_ComponentAfterNaviElements(this.statusSelector);
		RowLayoutData rl = new RowLayoutData();
		rl.setInsets(E2_INSETS.I(20,0,0,0));
		this.set_oLayout_Components_after_NaviElements(rl);
	}
	
	@Override
	public MyE2_MessageVector prepareData(ListBuildType type) throws myException {
		return new Fill_Bewegungs_Vektor_Temp_IDS().do_update();
	}


}
