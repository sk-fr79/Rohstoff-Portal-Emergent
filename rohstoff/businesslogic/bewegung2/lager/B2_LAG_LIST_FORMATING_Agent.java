
package rohstoff.businesslogic.bewegung2.lager;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.B2_LAG_NAMES;

public class B2_LAG_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;

	public B2_LAG_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
		this.m_tpHashMap = p_tpHashMap;
	}


	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {

		String einheit_k = oUsedResultMAP.get_UnFormatedValue(B2_LAG_NAMES.EINHEIT_KURZ.db_val());

		if(S.isFull(einheit_k)) {	
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val());
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WE_MENGE_NETTO.db_val());
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WE_MENGE_ABZUG.db_val());		
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val());
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WA_MENGE_NETTO.db_val());
			add_menge_einheit(oMAP, oUsedResultMAP, einheit_k, B2_LAG_NAMES.WA_MENGE_ABZUG.db_val());		
		}

	}

	private void add_menge_einheit(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP, String einheit_k, String oHash) throws myException {
		String field_hash = oHash;
		MyE2_DB_Label_INGRID cmp = (MyE2_DB_Label_INGRID)oMAP.get__Comp(field_hash);
		if(cmp != null) {
			if(S.isFull(oUsedResultMAP.get_UnFormatedValue(field_hash))) {
				cmp.set_Text(new MyBigDecimal(oUsedResultMAP.get_bdActualValue(field_hash, false)).get_FormatedRoundedNumber(2) + " " + einheit_k);
			}
		}
	}
}


