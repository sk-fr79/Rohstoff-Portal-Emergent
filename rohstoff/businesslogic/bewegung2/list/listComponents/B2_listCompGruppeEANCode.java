package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompGruppeEANCode extends E2_UniversalListComponent {
	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("55280285-1429-4ed3-9ab0-1432dbd86981");
	
	public B2_listCompGruppeEANCode(EnBgFieldList p_enBgFieldList) throws myException {
		super();
		
		if(! (p_enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error : 64336579-562c-453f-b83c-ae0ed29984bc : only ID_BG_TRANSPORT is allowed !");
		}
		
		this.enBgFieldList = p_enBgFieldList;
		
		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
				._a(new RB_lab()._t("ID Vektor"), 	new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("Fahrtgrp."), 	new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("Anzahl Grp."), new RB_gld()._ins(1)._left_top())
				._a(new RB_lab()._t("EAN"), 		new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Gruppe/EAN Code";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._w100()._s(1);
		
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());
		
		if(this.enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT) {
			Rec21 rVektor = map.getRec21(_TAB.bg_vektor, BG_VEKTOR.id_bg_vektor.fn());
			this._a(rVektor.get_fs_dbVal(BG_VEKTOR.id_bg_vektor,"-"), gld)._a("-",gld)._a("-",gld)._a("-",gld);
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompGruppeEANCode ret = new B2_listCompGruppeEANCode(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
}
