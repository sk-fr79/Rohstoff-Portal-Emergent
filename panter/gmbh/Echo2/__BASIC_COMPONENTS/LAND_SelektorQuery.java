package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.NvlTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class LAND_SelektorQuery {

	private SEL selLand = null;

	public LAND_SelektorQuery() throws myException{
		super();
		this.selLand=new SEL(new NvlTerm(LAND.laendername, LAND.laendercode),new FieldTerm(LAND.id_land)).FROM(_TAB.land).ORDERUP(LAND.laendername).ORDERUP(LAND.laendercode);
	}


	
	
	
	public SEL get_selLand() {
		return selLand;
	}
	
	public String[][] get_dropDownList4DataComponent(boolean empty_in_front) throws myException {
		String[][] arrayData = bibDB.EinzelAbfrageInArrayFormatiert(this.selLand.s(), "-");
		
		if (empty_in_front) {
			arrayData=bibARR.add_array_inFront(arrayData, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT) ;
		}

		return arrayData;
	}
	
}
