package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_ZYKLUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RecList_ContainerZyklus extends RecList22 {

	
	public RecList_ContainerZyklus() throws myException
	{
		this((String)null);
	}

	
	
	public RecList_ContainerZyklus(String IdContainer) throws myException {
		this(getSqlExt_Default( IdContainer	));
	}

	

	public RecList_ContainerZyklus( SqlStringExtended sql ) throws myException{
		super (_TAB.container_zyklus);
		this._fill(sql);
	}

	
	
	
	
	/**
	 * default SQL, zum Listen aller Container-Zyklen des Containers 
	 * @author manfred
	 * @date 10.03.2021
	 *
	 * @param IdContainer
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Default( String IdContainer ) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.container_zyklus)
				.WHERE(new TermSimple(" 1 = 1 "));

		// Standort
		if (S.isFull(IdContainer)) {
			
			And and_Basis = new And(new TermSimple(CONTAINER_ZYKLUS.id_container.fn()  + " =  ? ")) ;
			sel.getAnd().add( and_Basis );
			vecParam._a(new Param_Long(Long.parseLong(IdContainer)));

		}
		
		// order by
		sel.ORDERDOWN(CONTAINER_ZYKLUS.datum_zeit_buchung);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	
	/**
	 * default SQL, zum Listen aller Container-Zyklen des Containers 
	 * @author manfred
	 * @date 10.03.2021
	 *
	 * @param IdContainer
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_LastActive( String IdContainer ) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.container_zyklus)
				.WHERE(new TermSimple(" 1 = 1 "));

		// Standort
		if (S.isFull(IdContainer)) {
			
			And and_Basis = new And(new TermSimple(CONTAINER_ZYKLUS.id_container.fn()  + " =  ? ")) ;
			sel.getAnd().add( and_Basis );
			vecParam._a(new Param_Long(Long.parseLong(IdContainer)));
			
			And and_Aktiv = new And( new TermSimple("NVL(" +  CONTAINER_ZYKLUS.abgeschlossen.fn() + ",'N') = ?"));
			sel.getAnd().add (and_Aktiv);
			vecParam._a(new Param_String("", "N"));
			

		}
		
		// order by
		sel.ORDERDOWN(CONTAINER_ZYKLUS.datum_zeit_buchung);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	
	
	

}
