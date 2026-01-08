/**
 * panter.gmbh.Echo2.ListAndMask.List.OneToManyTools
 * @author martin
 * @date 16.08.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.OneToManyTools;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 16.08.2019
 * 
 * Klasse zur definition eine 1-zu-n-relation in der fuhrenzentrale,
 * damit kann definiert werden, wie die tabellen-zuordnungs-struktur ist 
 */
public class OneToMany {

	private _TAB tabOne = null;   			  //tabelle der ONE-Seite
	private _TAB tabMany = null;              //tabelle der MANY-Seite
	private _TAB tabRelationTable = null;     //Relationstablelle mit fremdschluesseln von beiden seiten  
	
	private PAIR<IF_Field, IF_Field>  relationOneSideToRelation = null;
	private PAIR<IF_Field, IF_Field>  relationManySideToRelation = null;
	
	//falls es relationsbeschreibungen innerhalb der gleichen tabellen sind,
	//kann es nuetzlich sein, eine weitere tabelle, die oberhalb des 1-zu-n-block steht, falls er existiert, muss sein primaery-key in det tabRelationTable vorhanden sein 
	private _TAB tabRelationBlockExtender = null;
	
	
	
	
	public OneToMany() {
	}


	public boolean checkBasicStructure() {
		boolean ret = true;
		
		if (tabOne==null) {
			ret = false;
		}
		
		if (tabMany==null) {
			ret = false;
		}
		if (tabRelationTable==null) {
			ret = false;
		}
		if (relationOneSideToRelation==null) {
			ret = false;
		}
		if (relationManySideToRelation==null) {
			ret = false;
		}
		
		if (ret) {
			if (O.isOneNull(relationOneSideToRelation , relationOneSideToRelation.getVal1(), relationOneSideToRelation.getVal2())) {
				ret = false;
			}
			
			if (O.isOneNull(relationManySideToRelation , relationManySideToRelation.getVal1(), relationManySideToRelation.getVal2())) {
				ret = false;
			}
			
		}
			
		return ret;
	}
	

	/**
	 * 
	 * @author martin
	 * @date 16.08.2019
	 *
	 * @param oneId
	 * @return RecList21 with all relationTableRecords, whitch are matching to the one-Value
	 * @throws myException
	 */
	public RecList21  getRelationsForOneId(Long oneId) throws myException {
		
		RecList21 rlMany = new RecList21(this.tabRelationTable);
		SEL s =  new SEL(this.tabRelationTable).FROM(this.tabRelationTable).WHERE(new vglParam(this.relationOneSideToRelation.getVal2()));
		rlMany._fill(new SqlStringExtended(s.s())._addParameter(new Param_Long(oneId)));

		return rlMany;
		
	}
	
	
	
	public VEK<Long> getManyIds(Long oneId) throws myException {
		return getRelationsForOneId(oneId).getVEKLong((r)-> { return r.getLongDbValue(relationManySideToRelation.getVal2()); });
	}
	
	
	

	public _TAB getTabOne() {
		return tabOne;
	}



	public OneToMany _setTabOne(_TAB tabOne) {
		this.tabOne = tabOne;
		return this;
	}


	
	public _TAB getTabRelationBlockExtender() {
		return tabRelationBlockExtender;
	}



	public OneToMany _setTabRelationBlockExtender(_TAB tabRelationBlockExtender) {
		this.tabRelationBlockExtender = tabRelationBlockExtender;
		return this;
	}

	

	public _TAB getTabMany() {
		return tabMany;
	}



	public OneToMany _setTabMany(_TAB tabMany) {
		this.tabMany = tabMany;
		return this;
	}



	public _TAB getTabRelationTable() {
		return tabRelationTable;
	}



	public OneToMany _setTabRelationTable(_TAB tabRelationTable) {
		this.tabRelationTable = tabRelationTable;
		return this;
	}



	public PAIR<IF_Field, IF_Field> getRelationOneSideToRelation() {
		return relationOneSideToRelation;
	}



	public OneToMany _setRelationOneSideToRelation(PAIR<IF_Field, IF_Field> relationOneSideToRelation) {
		this.relationOneSideToRelation = relationOneSideToRelation;
		return this;
	}



	public PAIR<IF_Field, IF_Field> getRelationManySideToRelation() {
		return relationManySideToRelation;
	}



	public OneToMany _setRelationManySideToRelation(PAIR<IF_Field, IF_Field> relationManySideToRelation) {
		this.relationManySideToRelation = relationManySideToRelation;
		return this;
	}

	

	public OneToMany _setRelationBlockExtender(_TAB tabRelationBlockExtender) {
		this.tabRelationBlockExtender = tabRelationBlockExtender;
		return this;
	}

	
	
	
	
	
}
