/**
 * panter.gmbh.indep.dataTools.TERM
 * @author martin
 * @date 19.03.2019
 * 
 */
package panter.gmbh.indep.dataTools.TERM;

import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSQL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 19.03.2019
 *
 */
public class VglDbFieldsContainsDate implements Term {

	private String sqlBlock = null;
	
	public VglDbFieldsContainsDate(IF_Field dateBottom ,Date betWeen,  IF_Field dateTop) throws myException {
		this("",  dateBottom ,betWeen, dateTop);
	}

	
	public VglDbFieldsContainsDate(String alias, IF_Field dateBottom ,Date betWeen,  IF_Field dateTop) throws myException {
		super();
		
		if (O.isOneNull(dateBottom,betWeen,dateTop)) {
			throw new myException("68a13b3a-4a37-11e9-8646-d663bd873d93: Null value not allowed!");
		}
		
		String dateString = "NULL";
		if (betWeen!=null) {
			dateString = bibSQL.includeInTicks(new SimpleDateFormat("yyyy-MM-dd").format(betWeen),false);
		}
	
		if (S.isFull(alias)) {
			sqlBlock = "TO_CHAR("+dateBottom.fn(alias)+",'YYYY-MM-DD')<="+dateString +" AND "+"TO_CHAR("+dateTop.fn(alias)+",'YYYY-MM-DD')>="+dateString;
		} else {
			sqlBlock = "TO_CHAR("+dateBottom.tnfn()+",'YYYY-MM-DD')<="+dateString +" AND "+"TO_CHAR("+dateTop.tnfn()+",'YYYY-MM-DD')>="+dateString;
		}
		
	}

	public VglDbFieldsContainsDate( Date dateBottom, IF_Field dateField ,Date dateTop) throws myException {
		this("",dateBottom,dateField ,dateTop);
	}
	
	public VglDbFieldsContainsDate(String alias, Date dateBottom, IF_Field dateField ,Date dateTop) throws myException {
		super();
		
		if (O.isOneNull(dateBottom,dateField,dateTop)) {
			throw new myException("68a13b3a-4a37-11e9-8646-d663bd873d93: Null value not allowed!");
		}
		
		String s_dateBottom = 	bibSQL.includeInTicks(new SimpleDateFormat("yyyy-MM-dd").format(dateBottom),false);
		String s_dateTop = 		bibSQL.includeInTicks(new SimpleDateFormat("yyyy-MM-dd").format(dateTop),false);
		
		
		if (S.isFull(alias)) {
			sqlBlock = "TO_CHAR("+dateField.fn(alias)+",'YYYY-MM-DD')>="+s_dateBottom +" AND "+ "TO_CHAR("+dateField.fn(alias)+",'YYYY-MM-DD')<="+s_dateTop;
		} else {
			sqlBlock = "TO_CHAR("+dateField.tnfn()+",'YYYY-MM-DD')>="+s_dateBottom +" AND "+ "TO_CHAR("+dateField.tnfn()+",'YYYY-MM-DD')<="+s_dateTop;
		}
		
	}

	
	@Override
	public String s() throws myException {
		return sqlBlock;
	}

}
