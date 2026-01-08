/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAMB_SQLFieldMAP extends SQLFieldMAP
{

	/**
	 * @param bWithRestrict (restriction ist fuer die maskendarstellung noetig, in der liste unsinnig)
	 * @throws myException
	 */
	public BAMB_SQLFieldMAP() throws myException
	{
		super("JT_FBAM",bibE2.get_CurrSession());
		this.addCompleteTable_FIELDLIST("JT_FBAM",":ID_FBAM:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ID_VPOS_TPA_FUHRE:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_FBAM","ID_FBAM","ID_FBAM",new MyE2_String("ID"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FBAM.NEXTVAL FROM DUAL",true), false);
		
		/*
		 * ein infofield, wird nur in der liste gebraucht, um den status der BAM datzustellen
		 */
		this.add_SQLField(new SQLField(" NVL(JT_FBAM.ABGESCHLOSSEN_BEHEBUNG,'-')|| NVL(JT_FBAM.ABGESCHLOSSEN_KONTROLLE,'-')",
				BAMB_LIST_ModulContainer.NAME_OF_SQL_INFOFIELD,new MyE2_String("Info"),bibE2.get_CurrSession()), false);

		
		this.get_("ID_USER_AUSSTELLUNG").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		this.get_("DATUM_AUSSTELLUNG").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		/*
		 * fuhre MUSS LEER sein
		 */
		this.clear_BEDINGUNG_STATIC();
		this.add_BEDINGUNG_STATIC("(JT_FBAM.ID_VPOS_TPA_FUHRE IS NULL AND JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NULL )");

		this.initFields();
		
	}
	
}