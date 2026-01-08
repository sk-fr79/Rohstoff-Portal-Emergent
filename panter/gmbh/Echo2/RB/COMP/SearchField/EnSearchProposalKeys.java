/**
 * panter.gmbh.Echo2.RB.COMP.SearchField
 * @author martin
 * @date 11.01.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.basics4project.DB_ENUMS.SUCHVORSCHLAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 11.01.2019
 *
 */
public enum EnSearchProposalKeys implements IF_enum_4_db_specified<EnSearchProposalKeys> {
	
	SEARCH_LAGER_IN_FUHREN("Adress-Suche")
	,SEARCH_SORTE_IN_FUHREN("Sorten-Suche")
	
	;

	
	private String m_userText = null;
	
	private EnSearchProposalKeys(String userText) {
		this.m_userText = userText;
		
	}
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return S.NN(this.m_userText,this.name());
	}

	@Override
	public EnSearchProposalKeys[] get_Values() {
		return EnSearchProposalKeys.values();
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.01.2019
	 *
	 * @return HMAP<Long, Rec21> with values for user or empty hashmap
	 */
	public HMAP<Long, Rec21> getProposals() {
		HMAP<Long, Rec21> hm = new HMAP<>();
		
		try {
			SEL sel = new SEL(_TAB.suchvorschlag)	.FROM(_TAB.suchvorschlag)
													.WHERE(new vglParam(SUCHVORSCHLAG.such_enum))
													.AND(new vglParam(SUCHVORSCHLAG.id_user))
													.ORDERUP(SUCHVORSCHLAG.beschriftung)
													;
			SqlStringExtended sql = new SqlStringExtended(sel.s())._addParameters(new VEK<ParamDataObject>()
																			._a(new Param_String("", this.db_val()))
																			._a(new Param_Long(bibALL.get_RECORD_USER().get_ID_USER_lValue(-1l)))
																			);
			
			RecList21 rl = new RecList21(_TAB.suchvorschlag)._fill(sql);
			
			for (Rec21 user: rl) {
				hm.put(user.getIdLong(), user);
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return hm;
	}
	
	
}
