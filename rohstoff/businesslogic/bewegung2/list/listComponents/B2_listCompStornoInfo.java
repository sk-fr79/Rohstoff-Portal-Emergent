package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompStornoInfo extends E2_UniversalListComponent {

	private EnBgFieldList  enBgFieldList = null;

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("e370aa0c-c1fc-4b02-9d11-6e384c55d26c");

	public B2_listCompStornoInfo(EnBgFieldList p_EnBgFieldList) throws myException {
		super();
		
		if(! (p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT) ) {
			throw new myException("Error : 2a8f4402-18f3-4c53-8c36-fae81ac3cbe4 : only ID_BG_TRANSPORT is allowed");
		}
		
		this.enBgFieldList = p_EnBgFieldList;
	}



	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Storno info";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._w100()._setSize(150);

		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());		

		Rec21 rVektor = null;

		if (enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT) {
			rVektor = map.getRec21(_TAB.bg_vektor, BG_VEKTOR.id_bg_vektor.fn());

			if(rVektor != null ){

				RB_gld gld = new RB_gld()._ins(1)._left_top();
				
				if(S.isFull(rVektor.getUfs(BG_VEKTOR.id_bg_storno_info))) {
					Rec21 rStornoInfo = rVektor.get_up_Rec21(BG_STORNO_INFO.id_bg_storno_info);
					String storno_datum = rStornoInfo.get_ufs_dbVal(BG_STORNO_INFO.storno_datum);
					String storno_bemer = rStornoInfo.get_ufs_dbVal(BG_STORNO_INFO.storno_grund,"-");

					if(S.isFull(storno_bemer)) {
						this._a(new RB_lab()._t(storno_datum)._i()._fsa(-1), 		gld);
						this._a(new RB_lab()._t(storno_bemer)._i()._fsa(-1)._lw(), 	gld);
					}else {
						this._a(new RB_lab()._t("-")._i()._fsa(-1)._lw(), 	gld);
					}
				}else {
					this._a(new RB_lab()._t("-")._i()._fsa(-1)._lw(), 	gld);
				}
			}

		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompStornoInfo ret = new B2_listCompStornoInfo(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
