package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
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


public class B2_listCompLkwFahrer extends E2_UniversalListComponent {

	private EnBgFieldList  enBgFieldList = null;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("b9c6875a-444f-40e3-a911-15d2d4f99182");

	public B2_listCompLkwFahrer(EnBgFieldList p_enBgFieldList) throws myException {
		super();

		if (! (p_enBgFieldList== EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 6dbb1433-d50a-4faa-bd8f-baeefb41a7ba : only ID_BG_TRANSPORT are allowed !");
		}

		this.enBgFieldList =  p_enBgFieldList;

		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList, BG_VEKTOR.transportkennzeichen)._setButtonText(S.ms("LKW")), 		new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList, BG_VEKTOR.anhaengerkennzeichen)._setButtonText(S.ms("Anhänger")), 	new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("Fahrer")._lwn(), 																new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Kennzeichen/Fahrer";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(20,200)._w100();

		Rec21 rVektor 	= null;

		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());
		

		if (enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT) {
			rVektor = 		map.getRec21(_TAB.bg_vektor,	BG_VEKTOR.id_bg_vektor.fn());
		} 
		
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		

		if (rVektor != null ) {
			this._a()._a(rVektor.get_fs_dbVal(BG_VEKTOR.transportkennzeichen, 	"<??>")	, gld);
			this._a()._a(rVektor.get_fs_dbVal(BG_VEKTOR.anhaengerkennzeichen, 	"<??>")	, gld);
			this._a()._a("-"															, gld);
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompLkwFahrer ret = new B2_listCompLkwFahrer(this.enBgFieldList);
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
			return B2_listCompLkwFahrer.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
