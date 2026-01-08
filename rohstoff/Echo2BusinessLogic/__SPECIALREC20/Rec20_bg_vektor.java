/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.EN_ATOM_POS_IN_BG;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.EN_ATOM_TYP;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;

/**
 * @author martin
 *
 */
public class Rec20_bg_vektor extends Rec20 {

	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20_bg_vektor() throws myException {
		super(_TAB.bg_vektor);
	}


	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_bg_vektor(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	@Override
	public Rec20_bg_vektor _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec20_bg_vektor _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	@Override
	public Rec20_bg_vektor _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	
//	public RecList20  get_rlAtome() throws myException {
//		SEL sel = new SEL(_TAB.bg_atom).FROM(_TAB.bg_atom).WHERE(new In(BG_ATOM.en_atom_typ,
//																		EN_ATOM_TYP.HAUPTATOM.dbVal(),
//																		EN_ATOM_TYP.HAUPTATOM_STRECKE_START.dbVal(),
//																		EN_ATOM_TYP.HAUPTATOM_STRECKE_ZIEL.dbVal()))
//														  				;		
//		
//		return new RecList20(_TAB.bg_atom)._fill(sel.s());
//		
//	}
	
//	public RecList20  get_rlAtome(EN_ATOM_TYP typ, EN_ATOM_POS_IN_BG leftMidRight) throws myException {
//		return this.get_down_reclist20(BG_ATOM.id_bg_vektor, 
//						new  And(new vgl(BG_ATOM.en_atom_typ,typ.dbVal()))
//							.and(new vgl(BG_ATOM.en_atom_pos_in_bg,leftMidRight.dbVal())).s(), null, true);
//	}

	
//	public Rec20_bg_atom getAtomSingleMain() throws myException{
//		RecList20 rl20Atome = this.get_rlAtome(EN_ATOM_TYP.HAUPTATOM, EN_ATOM_POS_IN_BG.MID);
//		
//		//hier darf nur ein atom gefunden werden
//		if (rl20Atome.size()!=1) {
//			throw new myException("Singulärer Vektor MUSS exakt einen HAUPTATOM, Untertyp MID besitzen !");
//		} else {
//			return new Rec20_bg_atom(rl20Atome.get(0));
//		}
//	}
//	
//
//	public Rec20_bg_atom getAtomLeftMain() throws myException{
//		RecList20 rl20Atome = this.get_rlAtome(EN_ATOM_TYP.HAUPTATOM,  EN_ATOM_POS_IN_BG.LEFT);
//		//hier darf nur ein atom gefunden werden
//		if (rl20Atome.size()!=1) {
//			throw new myException("Dualer Vektor MUSS ein Atom vom Typ HAUPTATOM, Untertyp LEFT besitzen!");
//		} else {
//			return new Rec20_bg_atom(rl20Atome.get(0));
//		}
//	}
//	
//	public Rec20_bg_atom getAtomRightMain() throws myException{
//		RecList20 rl20Atome = this.get_rlAtome(EN_ATOM_TYP.HAUPTATOM, EN_ATOM_POS_IN_BG.RIGHT);
//		//hier darf nur ein atom gefunden werden
//		if (rl20Atome.size()!=1) {
//			throw new myException("Dualer Vektor MUSS ein Atom vom Typ HAUPTATOM, Untertyp RIGHT besitzen!");
//		} else {
//			return new Rec20_bg_atom(rl20Atome.get(0));
//		}
//	}
//	

	public boolean isArchiveDataset()  throws myException{
		if (this.getUfs(BG_VEKTOR.en_vektor_quelle)==null) {
			throw new myException(this,"Field en_vektor_quelle CANNOT be null !!");
		} else {
			return (this.getUfs(BG_VEKTOR.en_vektor_quelle).equals(EN_VEKTOR_QUELLE.FUHRE.name()));
		}
	}


	
	
	
}
