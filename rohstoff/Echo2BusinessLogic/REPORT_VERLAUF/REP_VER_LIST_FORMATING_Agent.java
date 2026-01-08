package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
   
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
  
public class REP_VER_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public REP_VER_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
    	translate_user(oMAP, oUsedResultMAP);
        translate_weg(oMAP, oUsedResultMAP);
        translate_datum(oMAP, oUsedResultMAP);
    }
    
    private void translate_datum(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)throws myException {
    	Object o = oMAP.get__Comp(REPORT_LOG.report_druck_am);
    	
    	String[] fDateTimeTab = oUsedResultMAP.get_oResultField(REPORT_LOG.report_druck_am.fn()).get_cDateTimeValueFormated().split(":");
    	String fDateTime = fDateTimeTab[0]+":"+fDateTimeTab[1];
    	if(!(o == null) && (o instanceof MyE2_DB_Label_INGRID)) {
    		((MyE2_DB_Label_INGRID)o).set_Text(fDateTime);
    	}
    }
    
    private void translate_weg(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
    	Object o = oMAP.get__Comp(REPORT_LOG.report_weg);

    	String dbWegen = oUsedResultMAP.get_UnFormatedValue(REPORT_LOG.report_weg.fieldName(), ENUM_REPORT_WEG.UND.name());

		if(!(o == null) && (o instanceof MyE2_DB_Label_INGRID)) {
			((MyE2_DB_Label_INGRID)o).set_Text(ENUM_REPORT_WEG.valueOf(dbWegen).user_text());
		}
	}

	public void translate_user(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
    	Object o = null;

		o = oMAP.get__Comp(REPORT_LOG.report_druck_von);
		long id_user = oUsedResultMAP.get_LActualDBValue(REPORT_LOG.report_druck_von.fieldName(), true);

		Rec21 usr_rec = new Rec21(_TAB.user)._fill_id(id_user);

		if(!(o == null) && (o instanceof MyE2_DB_Label_INGRID)) {
			((MyE2_DB_Label_INGRID)o).set_Text(usr_rec.getFs(USER.kuerzel,""));
		}
    }
}
 
 
