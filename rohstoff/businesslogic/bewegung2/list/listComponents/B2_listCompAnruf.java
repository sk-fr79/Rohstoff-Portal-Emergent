package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;

public class B2_listCompAnruf extends E2_UniversalListComponent {

	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("76fe90cc-15f3-4586-b728-f4713beff92d");

	public B2_listCompAnruf(EnBgFieldList p_EnBgFieldList) throws myException {
		super();
		
		if(!(p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: ff9ed792-05c2-4815-bab2-fa814276957e : only ID_BG_TRANSPORT is allowed !");
		}
		
		this.enBgFieldList = p_EnBgFieldList;
		
		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a(new OwnSorter(null)._setButtonText(S.ms("Anrufer")))
				._a(new OwnSorter(null)._setButtonText(S.ms("Anrufdatum")))
				._a(new OwnSorter(null)._setButtonText(S.ms("Ende")))	
			);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Anrufer/Anrunfdatum";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		this._clear()._w100()._s(1)._a("-",gld)._a("-",gld)._a("-",gld);
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompAnruf ret = new B2_listCompAnruf(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter(EnBgFieldList p_enBgFieldList) {
			super();
	
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompAnruf.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
