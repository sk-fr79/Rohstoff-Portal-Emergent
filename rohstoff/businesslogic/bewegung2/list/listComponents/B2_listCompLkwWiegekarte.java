/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author sebastien
 * @date 18.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author sebastien
 * @date 18.03.2019
 *
 */
public class B2_listCompLkwWiegekarte extends E2_UniversalListComponent {

	private EnBgFieldList  enBgFieldList = null;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("444b1e1b-6531-4e34-8cee-666c628440db");

	public B2_listCompLkwWiegekarte(EnBgFieldList p_enBgFieldList) throws myException {
		super();

		if (! (p_enBgFieldList== EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: b7c57b3e-216f-4107-8436-b26105b751f6 : only ID_BG_TRANSPORT are allowed !");
		}

		this.enBgFieldList =  p_enBgFieldList;

		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList, BG_VEKTOR.transportkennzeichen)._setButtonText(S.ms("LKW-Kennz.")), 		new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList, BG_VEKTOR.anhaengerkennzeichen)._setButtonText(S.ms("Anhänger-Kennz.")), 	new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("Wiegek.(Laden)")._lwn(), 										new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("Wiegek.(Abladen)")._lwn(), 									new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Kennzeichen/Wiegekarte";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(20,200)._w100();

		Rec21 rVektor 	= null;
		Rec21 rStation1 = null;
		Rec21 rStation3 = null;

		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());
		

		if (enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT) {
			rVektor = 		map.getRec21(_TAB.bg_vektor,	BG_VEKTOR.id_bg_vektor.fn());
			rStation1 =  	map.getRec21(_TAB.bg_station,	EnBgFieldList.S1_ID_BG_STATION.dbVal());
			rStation3 =  	map.getRec21(_TAB.bg_station,	EnBgFieldList.S3_ID_BG_STATION.dbVal());
		} 
		
		RB_gld gld = new RB_gld()._ins(1)._left_top();

		if (rVektor != null ) {
			this._a()._a(rVektor.get_fs_dbVal(BG_VEKTOR.transportkennzeichen, 	"<??>"),gld);
			this._a()._a(rVektor.get_fs_dbVal(BG_VEKTOR.anhaengerkennzeichen, 	"<??>"),gld);
			this._a()._a(rStation1.get_fs_dbVal(BG_STATION.wiegekartenkenner,	"--"),	gld);
			this._a()._a(rStation3.get_fs_dbVal(BG_STATION.wiegekartenkenner,	"--"),	gld);
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompLkwWiegekarte ret = new B2_listCompLkwWiegekarte(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	protected class OwnSorter extends E2_ButtonListSorterNG {
		public OwnSorter(EnBgFieldList p_enBgFieldList, IF_Field p_field_2_sort) {
			super();
			this._w100();
			String sortUp = "NVL("+p_field_2_sort+",'') ";
			String sortDwn = "NVL("+p_field_2_sort+",'') DESC";
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompLkwWiegekarte.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
