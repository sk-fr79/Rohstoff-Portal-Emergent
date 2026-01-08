package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorter;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_CONST.TICKET_STATUS;
import panter.gmbh.Echo2.components.DB.E2_DB_PlaceHolder_V3;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class HELP2_LIST_CompInfoBlock extends E2_DB_PlaceHolder_V3 {

	public static String key = "8b03ef46-ab64-11e8-98d0-529269fb1459";
	public static int width1 = 60;
	public static int width2 = 100;
	public static int widthSpalte = width1+width2+10;

    private RB_TransportHashMap   m_tpHashMap = null;
	
	
	public HELP2_LIST_CompInfoBlock(RB_TransportHashMap tpHashMap) {
		super();
		this.EXT().set_oCompTitleInList( new E2_ButtonListSorter(	S.ms("Infos/Version"),
											VERSION.version_code.tnfn()+" ASC",
											VERSION.version_code.tnfn()+" DESC",
											tpHashMap.getNavigationList(), 
											true));
		this.m_tpHashMap = tpHashMap;
		
	}

	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP res) throws myException {
		
		this._setSize(width1,width2)._bo_d();
		
		RB_gld gl1 = new RB_gld()._ins(2,1,2,0);
		RB_gld gl2 = new RB_gld()._ins(2,1,2,0);
		
		String version = "<vers>";
		
		@SuppressWarnings({ "unchecked" })
		HashMap<Long, Rec21>  versions = (HashMap<Long, Rec21> ) ENUM_MANDANT_SESSION_STORE.HASHMAP_ALL_PROGRAMM_VERSIONS.getValueFromSession();
		Long idVers = res.get_LActualDBValue(HILFETEXT.id_version.fn(),true);
		if (idVers.longValue()>0) {
			Rec21 r = versions.get(idVers);
			if (r!=null) {
				version=r.get_fs_dbVal(VERSION.version_code, "<?>");
			}
		}
		
		MyE2_String labelTextModulKenner = E2_MODULNAME_ENUM.getUserText(res.getUfs(HILFETEXT.modulkenner));
		if (labelTextModulKenner.COrig().equals(HELP2_CONST.pairGlobalRange.getVal1())) {
			labelTextModulKenner=S.ms(HELP2_CONST.pairGlobalRange.getVal2());
		}
		
		//this._a(new RB_lab(S.ms("Modul:"))._fo_italic(), gl1)		._a(new RB_lab(E2_MODULNAME_ENUM.getUserText(res.getUfs(HILFETEXT.modulkenner)))._b(), gl2);
		this._a(new RB_lab(labelTextModulKenner)._b(), gl2._c()._span(2));
		this._a(new RB_lab(S.ms("Version:"))._fo_italic(), gl1)		._a(version, gl2);
		this._a(new RB_lab(S.ms("Typ:"))._fo_italic(), gl1)			._a(HELP2_CONST.getTicketTypUserText(res.getUfs(HILFETEXT.typ)), gl2);
		
		TICKET_STATUS status = HELP2_CONST.getTicketStatus(res.getUfs(HILFETEXT.status));
		if (status != null) {
			RB_gld glStatus = new RB_gld()._ins(2,1,2,0)._col(status.getBackCol());
			this._a(new RB_lab(S.ms("Status:")), gl1)		._a(status.user_text(), glStatus);
			if (status==TICKET_STATUS.CLOSED) {
				this._a(new RB_lab(S.ms("")), gl1)			._a(res.get_FormatedValue(HILFETEXT.abschlussdatum.fn()), glStatus);
			}
		}
	}
	
	
	public HELP2_LIST_CompInfoBlock get_Copy(Object objHelp) throws myExceptionCopy {
		return new HELP2_LIST_CompInfoBlock(m_tpHashMap);
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}
}
