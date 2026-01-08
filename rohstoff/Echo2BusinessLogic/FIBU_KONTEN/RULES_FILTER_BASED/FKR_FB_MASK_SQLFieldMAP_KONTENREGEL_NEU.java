package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_MASK_SQLFieldMAP_KONTENREGEL_NEU extends Project_SQLFieldMAP 
{

	public FKR_FB_MASK_SQLFieldMAP_KONTENREGEL_NEU(FKR_FB_MASK_SQLFieldMAP_FILTER  oFM_Filter) throws myException 
	{
		super(_DB.FIBU_KONTENREGEL_NEU, _DB.FIBU_KONTENREGEL_NEU$ID_FILTER, false);
	
//		this.add_SQLField(new SQLField("(SELECT NVL(F.BESCHREIBUNG,'<beschreibung>') FROM "+bibE2.cTO()+"."+_DB.FILTER+" F WHERE F.ID_FILTER="+
//				_DB.FIBU_KONTENREGEL_NEU+"."+_DB.FIBU_KONTENREGEL_NEU$ID_FILTER+")",
//				FKR_FB__CONST.FELD_FILTER_BESCHREIBUNG, new MyE2_String("Filterbeschreibung"), bibE2.get_CurrSession()), false);


		this.add_SQLField(new SQLFieldJoinOutside(	_DB.FIBU_KONTENREGEL_NEU,
													_DB.FIBU_KONTENREGEL_NEU$ID_FILTER,
													_DB.FIBU_KONTENREGEL_NEU$ID_FILTER,
													new MyE2_String("ID-Filter"),
													false,
													bibE2.get_CurrSession(),
													oFM_Filter.get_(_DB.FILTER$ID_FILTER)), false);

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU).set_cDefaultValueFormated("");
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO).set_cDefaultValueFormated("");
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FILTER).set_cDefaultValueFormated("");
		this.get_(_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR).set_cDefaultValueFormated("");
		
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FILTER).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.FIBU_KONTENREGEL_NEU$AKTIV).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/

		this.initFields();
	}

}
