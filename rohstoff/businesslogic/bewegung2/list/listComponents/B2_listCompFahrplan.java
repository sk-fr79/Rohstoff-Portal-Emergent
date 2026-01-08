package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;

public class B2_listCompFahrplan extends E2_UniversalListComponent {

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("82e5aa89-4169-4fb3-8416-20ab3927e9b9");

	private EnBgFieldList  enBgFieldList = null;

	public B2_listCompFahrplan(EnBgFieldList p_EnBgFieldList) throws myException {
		super();

		if(! (p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 5657388b-994e-4335-956e-e1ca634e9b6f : only ID_BG_TRANSPORT is allowed !" );

		}

		this.enBgFieldList = p_EnBgFieldList;

		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(null, null)._setButtonText(S.ms("Datum Fahrplan")), 	new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(null, null)._setButtonText(S.ms("Anfang")), 		new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(null, null)._setButtonText(S.ms("Ende")), 		new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Datum Fahrplan";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
//		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		this._clear()._setSize(150);

			this._a("--");
			this._a("--");
			this._a("--");

	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompFahrplan ret = new B2_listCompFahrplan(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter(EnBgFieldList enBgFieldList, IF_Field p_field_2_sort) {
			super();
			this._w100();

			//			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompFahrplan.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}


}
