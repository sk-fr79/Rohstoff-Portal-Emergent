package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;

public class B2_listCompRechnungGutschrift extends E2_UniversalListComponent {

	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM();

	public B2_listCompRechnungGutschrift(EnBgFieldList p_EnBgFieldList) throws myException {
		super();

		if( ! (p_EnBgFieldList == EnBgFieldList.A1_ID_BG_ATOM || p_EnBgFieldList == EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error: 092e684f-e4a8-4e77-8137-f852f6da54a1 : only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");
		}

		this.enBgFieldList = p_EnBgFieldList;
		
		if(this.enBgFieldList == EnBgFieldList.A1_ID_BG_ATOM) {
			compKey._setKeyAndName("fb834748-d78d-49cd-ba76-a2daa58a3eb0-R");
			this.EXT().set_oCompTitleInList(new RB_lab("Re-Nr."));
		}else {
			compKey._setKeyAndName("fb834748-d78d-49cd-ba76-a2daa58a3eb0-G");
			this.EXT().set_oCompTitleInList(new RB_lab("G-Nr."));
		}
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}
	

	

	@Override
	public String userText() throws myException {
		if(enBgFieldList == EnBgFieldList.A1_ID_BG_ATOM) {
			return "Rechnungsnummer";
		}
		return "Gutschriftnummer";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompRechnungGutschrift ret = new B2_listCompRechnungGutschrift(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
