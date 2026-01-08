package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompPosten extends E2_UniversalListComponent {

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("8fac69af-1af0-46b8-9236-30cb59339784\n" + 
			"");

	private EnBgFieldList enBgFieldList = null;

	public B2_listCompPosten(EnBgFieldList p_enBgFieldList) throws myException {
		super();
		if(! (p_enBgFieldList == EnBgFieldList.A1_ID_BG_ATOM || p_enBgFieldList == EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error : 4cd8af5c-d5af-457f-9435-820dd736a89f : only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");
		}

		this.enBgFieldList = p_enBgFieldList;

		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList, 	"A1." + BG_ATOM.postennummer.fn())._setButtonText(S.ms("Posten (Lade)")), 	new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList,	"A2." + BG_ATOM.postennummer.fn())._setButtonText(S.ms("Posten (Ablade)")), 	new RB_gld()._ins(1)._left_top())
				._a("Gelangengens-Best.", 		new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Posten";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
			B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

			this._clear()._setSize(150);

			if (enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM || enBgFieldList == EnBgFieldList.A2_ID_BG_ATOM) {
				String posten_lade = map.getRec21(_TAB.bg_atom,EnBgFieldList.A1_ID_BG_ATOM.dbVal()).get_ufs_dbVal(BG_ATOM.postennummer,"--");
				String posten_ablade = map.getRec21(_TAB.bg_atom,EnBgFieldList.A1_ID_BG_ATOM.dbVal()).get_ufs_dbVal(BG_ATOM.postennummer,"--");
				
				this
				._a(posten_lade, new RB_gld()._ins(1)._left_top())
				._a(posten_ablade, new RB_gld()._ins(1)._left_top())
				;
			}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompPosten ret = new B2_listCompPosten(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter(EnBgFieldList enBgFieldList, String p_field_2_sort) {
			super();
			this._w100();
			String sortUp = "NVL("+p_field_2_sort+",'') ";
			String sortDwn = "NVL("+p_field_2_sort+",'') DESC";
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompPosten.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
	}
}
