package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;

public class ADI_LIST_BedienPanel extends MyE2_Column {

	private ADI_LIST_Selector  		adresse_info_LIST_Selector = null;
	private RECORD_ADRESSE 			rec_adresse = null;

	public ADI_LIST_BedienPanel(E2_NavigationList oNaviList, RECORD_ADRESSE  p_rec_adresse) throws myException {

		super(MyE2_Column.STYLE_NO_BORDER());
		this.rec_adresse = p_rec_adresse;

		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());

		this.adresse_info_LIST_Selector = new ADI_LIST_Selector(oNaviList);

		Insets oInsets = new Insets(0,0,0,5);

		this.add(adresse_info_LIST_Selector, oInsets);
		this.add(oRowForComponents, oInsets);

		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new ADI_LIST_bt_ListToMask(true,oNaviList,this.rec_adresse),  new Insets(2,2,20,2));
		oRowForComponents.add(new ADI_LIST_bt_ListToMask(false,oNaviList,this.rec_adresse),  new Insets(2,2,20,2));
		oRowForComponents.add(new ADI_LIST_bt_New(oNaviList,this.rec_adresse), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new ADI_LIST_bt_multiDelete(oNaviList), E2_INSETS.I_2_2_2_2);

		oRowForComponents.add(new ADI_LIST_DATASEARCH(oNaviList, p_rec_adresse), new Insets(20,2,2,2));
	}
	public ADI_LIST_Selector get_list_Selector() 
	{
		return adresse_info_LIST_Selector;
	}
}

