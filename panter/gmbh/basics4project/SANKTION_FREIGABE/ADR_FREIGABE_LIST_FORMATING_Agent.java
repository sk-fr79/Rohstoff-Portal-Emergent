
package panter.gmbh.basics4project.SANKTION_FREIGABE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class ADR_FREIGABE_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException 
	{
	}
	
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		translate_freigabe(oMAP, oUsedResultMAP);
		translate_user(oMAP, oUsedResultMAP);
//		translate_wegen(oMAP, oUsedResultMAP);
		translate_aktiv_inaktiv(oMAP, oUsedResultMAP);
	}

	private void translate_freigabe(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		Object	o = oMAP.get__Comp(SANKTION_PRUEFUNG.freigabe);
		if(!(o == null) && (o instanceof MyE2_Label)) {
			if(oUsedResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.freigabe.fieldName()).equals("Y")) {
				((MyE2_DB_Label_INGRID)o).set_Text("");
				((MyE2_DB_Label_INGRID)o).get_oErsatzButton().setToolTipText("Hat die Freigabe");
				((MyE2_DB_Label_INGRID)o).get_oErsatzButton().setIcon(E2_ResourceIcon.get_RI("listlabel_green_border.png"));
				
			}else {
				((MyE2_DB_Label_INGRID)o).set_Text("");
				((MyE2_DB_Label_INGRID)o).get_oErsatzButton().setToolTipText("Hat nicht die Freigabe");
				((MyE2_DB_Label_INGRID)o).get_oErsatzButton().setIcon(E2_ResourceIcon.get_RI("listlabel_red_border.png"));
			}
		}
	}

	private void translate_aktiv_inaktiv(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		Object	o = oMAP.get__Comp(SANKTION_PRUEFUNG.aktiv);
		if(!(o == null) && (o instanceof MyE2_Label)) {
			if(oUsedResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.aktiv.fieldName()).equals("Y")) {
				((MyE2_DB_Label_INGRID)o).set_Text("Aktiv");
				
			}else {
				((MyE2_DB_Label_INGRID)o).set_Text("Inaktiv");
			}
		}
	}

	private void translate_user(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
		Object o = null;

		o = oMAP.get__Comp(SANKTION_PRUEFUNG.freigabe_user);
		long id_user = oUsedResultMAP.get_LActualDBValue(SANKTION_PRUEFUNG.freigabe_user.fieldName(), true);

		Rec21 user_n1 = new Rec21(_TAB.user)._fill_id(id_user);

		if(!(o == null) && (o instanceof MyE2_DB_Label_INGRID)) {
			((MyE2_DB_Label_INGRID)o).set_Text(user_n1.getFs(USER.kuerzel,""));
		}
	}

	
	/*private void translate_wegen(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException{
		Object o = null;

		o = oMAP.get__Comp(SANKTION_PRUEFUNG.sanktion_wegen);
		
		String dbWegen = oUsedResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.sanktion_wegen.fieldName());
		
		if(!(o == null) && (o instanceof MyE2_DB_Label_INGRID) && S.isFull(dbWegen)) {
			((MyE2_DB_Label_INGRID)o).set_Text(ENUM_SANKTION_Error.valueOf(dbWegen).user_text());
		}
	}*/
	
	
}

