package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;

public class B2_listCompVormerkzeit extends E2_UniversalListComponent {

	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("ce02a9b1-976e-4e93-acd8-0fec0cc8022a");

	public B2_listCompVormerkzeit(EnBgFieldList p_EnBgFieldList) throws myException {
		super();
		if(!(p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 0be1236a-c042-41ac-8092-c54fd315632e : only ID_BG_TRANSPORT is allowed !");
		}
		
		this.enBgFieldList = p_EnBgFieldList;
		
		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a(new  RB_lab("Vormerkung"))
				._a(new RB_lab("Geplant"))
			);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Vormerkzeit";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		
		 RB_cb cb_ist_geplant = new RB_cb();
		 cb_ist_geplant.set_bEnabled_For_Edit(false);
		
		this._clear()._w100()._s(1)._a("-",gld)._a(cb_ist_geplant,gld);
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompVormerkzeit ret = new B2_listCompVormerkzeit(this.enBgFieldList);
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
			return B2_listCompVormerkzeit.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
