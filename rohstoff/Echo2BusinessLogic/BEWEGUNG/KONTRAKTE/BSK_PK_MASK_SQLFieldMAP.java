package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.exceptions.myException;


// sqlfieldmap fuer die angebundene componentmap JT_VPOS_KON_TRAKT
public class BSK_PK_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSK_PK_MASK_SQLFieldMAP(BSK_P_MASK_SQLFieldMAP oSQLFieldMap_VPOS_KON) throws myException 
	{
		super("JT_VPOS_KON_TRAKT", ":ID_VPOS_KON_TRAKT:ID_VPOS_KON:", false);
		
		// das connection-field
		this.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_KON_TRAKT","ID_VPOS_KON","ID_VPOS_KON",new MyE2_String("ID-Position"),false,bibE2.get_CurrSession(),oSQLFieldMap_VPOS_KON.get_("ID_VPOS_KON")), false);
		
		/*
		 * defaultwerte setzen
		 */
		this.get_("ABGESCHLOSSEN").set_cDefaultValueFormated("N");
		this.get_("UEBERLIEFER_OK").set_cDefaultValueFormated("N");
		
		
		//bibDEBUG.print_VectorToConsole(bibALL.get_vBuildKeyVectorFromHashmap(this));
		
		
		/*
		 * must-values
		 */
		this.get_("GUELTIG_VON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("GUELTIG_BIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.initFields();
	}

}
