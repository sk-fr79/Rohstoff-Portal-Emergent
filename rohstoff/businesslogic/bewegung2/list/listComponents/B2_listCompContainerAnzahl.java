package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;

public class B2_listCompContainerAnzahl extends E2_UniversalListComponent {

	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("8fe18920-bc18-4ce5-8b5e-62de15489ad0");

	public B2_listCompContainerAnzahl(EnBgFieldList p_EnBgFieldList) throws myException {
		super();
		if(!(p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: edd7827b-107d-45d6-818c-60131b14165f : only ID_BG_TRANSPORT is allowed !");
		}
		
		this.enBgFieldList = p_EnBgFieldList;
		
		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a(new RB_lab()._t("Zahl"))
			);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Anzahl Container";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		this._clear()._w100()._s(1)._a("-",gld);
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompContainerAnzahl ret = new B2_listCompContainerAnzahl(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	

}
