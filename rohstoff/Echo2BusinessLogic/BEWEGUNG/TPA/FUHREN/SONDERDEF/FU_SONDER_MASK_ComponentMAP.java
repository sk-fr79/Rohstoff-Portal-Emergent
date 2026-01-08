package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_SONDER_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public FU_SONDER_MASK_ComponentMAP() throws myException
	{
		super(new FU_SONDER_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(	oFM.get_("AUSNAHME"),true,400), 		new MyE2_String("Titel"));
		this.add_Component(new MyE2_DB_Label(		oFM.get_("ID_VPOS_TPA_FUHRE_SONDER")), 	new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_CheckBox(	oFM.get_("KEIN_KONTRAKT_NOETIG")), 		new MyE2_String("Ohne VK-Kontrakt"));
		this.add_Component(new MyE2_DB_CheckBox(	oFM.get_("OHNE_ABRECHNUNG")), 			new MyE2_String("Keine Mengenabrechung"));
		this.add_Component(new MyE2_DB_CheckBox(	oFM.get_("OHNE_AVV_VERTRAG_CHECK")), 	new MyE2_String("Keine AVV-Vertrags-Prüfung"));

		//tochter, um die pos-Vorlagen anzuhaengen
		FU_SONDER_MASK_TOCHTER_POS_VL oPos_VL_Daughter = new FU_SONDER_MASK_TOCHTER_POS_VL(oFM,this);
		this.add_Component(FU_SONDER__CONST.KEY_DAUGHTER_VPOS_VL, oPos_VL_Daughter, new MyE2_String("Positionen bei dieser Ausnahme"));
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new FU_SONDER_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FU_SONDER_LIST_FORMATING_Agent());
	}
	
}
