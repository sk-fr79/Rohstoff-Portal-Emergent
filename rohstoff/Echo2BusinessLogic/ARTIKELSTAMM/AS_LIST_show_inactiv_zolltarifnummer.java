package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class AS_LIST_show_inactiv_zolltarifnummer extends MyE2_DB_PlaceHolder_onlyWhenVisisble {

	public enum ZT_STAUS {
		EMPTY(new MyE2_String("Keine Zolltarifnummer"), E2_ResourceIcon.get_RI("empty10.png")) 
		,OK(new MyE2_String("Zolltarifnummer ist ok"), E2_ResourceIcon.get_RI("zt_ok.png"))
		,INAKTIV(new MyE2_String("Zolltarifnummer ist inaktiv, MUSS neu zugeordnet werden"), E2_ResourceIcon.get_RI("zt_inaktiv.png"))
		,BROKEN(new MyE2_String("ID der Zolltraifnummer stimmt nicht mit der erfaßten Nummer überein - MUSS korrigiert werden !"), E2_ResourceIcon.get_RI("zt_broken.png"))
		;
		
		
		private MyE2_String 	tooltips = null;
		private E2_ResourceIcon icon = null;

		private ZT_STAUS(MyE2_String p_tooltips, E2_ResourceIcon p_icon) {
			this.tooltips = p_tooltips;
			this.icon = p_icon;
		}
		
		
		
	}
	
	
	public AS_LIST_show_inactiv_zolltarifnummer(SQLField osqlField) throws myException {
		super(osqlField);
		this.EXT().set_oLayout_ListElement(new RB_gld()._center_top()._ins(1,3,1,0));
		this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._ins(2,1,2,0));
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.removeAll();
		
		ZT_STAUS status=null;
		try {
			String id = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			RECORD_ARTIKEL  		ra = new RECORD_ARTIKEL(id);
			RECORD_ZOLLTARIFNUMMER  rz = ra.get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer();
				
			status = null;
			if (rz==null && S.isFull(ra.ufs(ARTIKEL.zolltarifnr))) {
				status = ZT_STAUS.BROKEN;
			} else if (rz != null && S.isEmpty(ra.ufs(ARTIKEL.zolltarifnr))) {
				status = ZT_STAUS.BROKEN;
			} else if (rz==null && S.isEmpty(ra.ufs(ARTIKEL.zolltarifnr))) {
				status = ZT_STAUS.EMPTY;
			} else {
				if (rz.ufs(ZOLLTARIFNUMMER.nummer).equals(ra.ufs(ARTIKEL.zolltarifnr))) {
					if (rz.is_AKTIV_NO()) {
						status = ZT_STAUS.INAKTIV;
					} else {
						status = ZT_STAUS.OK;
					}
				} else {
					status = ZT_STAUS.BROKEN;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = ZT_STAUS.INAKTIV;
		}
		
		if (status==null) {
			status=ZT_STAUS.INAKTIV;
		}
		this.add(new RB_lab()._ri(status.icon)._ttt(status.tooltips));
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new AS_LIST_show_inactiv_zolltarifnummer(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getMessage());
		}
	}
}
