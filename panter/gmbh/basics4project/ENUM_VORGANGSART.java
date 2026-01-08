/**
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public enum ENUM_VORGANGSART implements IF_enum_4_db {
	
     ANGEBOT(		_TAB.vkopf_std, _TAB.vpos_std, 	VPOS_STD.id_vkopf_std, 	VKOPF_STD.id_vkopf_std, VPOS_STD.id_vpos_std)
    ,ABNAHMEANGEBOT(_TAB.vkopf_std, _TAB.vpos_std, 	VPOS_STD.id_vkopf_std, 	VKOPF_STD.id_vkopf_std, VPOS_STD.id_vpos_std)
    ,RECHNUNG(		_TAB.vkopf_rg, 	_TAB.vpos_rg,  	VPOS_RG.id_vkopf_rg, 	VKOPF_RG.id_vkopf_rg,   VPOS_RG.id_vpos_rg)
    ,GUTSCHRIFT(	_TAB.vkopf_rg, 	_TAB.vpos_rg, 	VPOS_RG.id_vkopf_rg, 	VKOPF_RG.id_vkopf_rg,   VPOS_RG.id_vpos_rg)
    ,TRANSPORT(		_TAB.vkopf_tpa, _TAB.vpos_tpa, 	VPOS_TPA.id_vkopf_tpa, 	VKOPF_TPA.id_vkopf_tpa, VPOS_TPA.id_vpos_tpa)
    ,EK_KONTRAKT(	_TAB.vkopf_kon, _TAB.vpos_kon, 	VPOS_KON.id_vkopf_kon, 	VKOPF_KON.id_vkopf_kon, VPOS_KON.id_vpos_kon)
    ,VK_KONTRAKT(	_TAB.vkopf_kon, _TAB.vpos_kon, 	VPOS_KON.id_vkopf_kon,  VKOPF_KON.id_vkopf_kon, VPOS_KON.id_vpos_kon)
    ;
	
	private _TAB 		m_tabKopf=null;
	private _TAB 		m_tabPos=null;
	private IF_Field 	m_fieldPosToHead = null;
	private IF_Field 	m_fieldIndexHead = null;
	private IF_Field 	m_fieldIndexPosition = null;
	
	private ENUM_VORGANGSART(_TAB tabKopf, _TAB tabPos, IF_Field fieldPosToHead, IF_Field fieldIndexHead, IF_Field fieldIndexPosition) {
		this.m_tabKopf = tabKopf;
		this.m_tabPos = tabPos;
		this.m_fieldPosToHead = fieldPosToHead;
		this.m_fieldIndexHead = fieldIndexHead;
		this.m_fieldIndexPosition = fieldIndexPosition;
	}

	public _TAB getTabKopf() {
		return m_tabKopf;
	}

	public _TAB getTabPos() {
		return m_tabPos;
	}
    
	

	/**
	 * 
	 * @param vorgangTyp or null when not found
	 * @return
	 */
	public static ENUM_VORGANGSART findENUM_VORGANGSART(String vorgangTyp) {
		ENUM_VORGANGSART enumArt = null;
		
		for (ENUM_VORGANGSART art: ENUM_VORGANGSART.values()) {
			if (art.name().equals(vorgangTyp)) {
				enumArt = art;
			}
		}
		return enumArt;
	}

	
	/**
	 * findet die passende enum mit der _TAB der position
	 * @param tablePos
	 * @return
	 */
	public static ENUM_VORGANGSART findENUM_VORGANGSART_fromPosTable(_TAB tablePos) {
		ENUM_VORGANGSART enumArt = null;
		
		for (ENUM_VORGANGSART art: ENUM_VORGANGSART.values()) {
			if (art.m_tabPos==tablePos) {
				enumArt = art;
			}
		}
		return enumArt;
	}

	
	
	
	public Rec21 getRecKopf(Long id) throws myException {
		return new Rec21(this.m_tabKopf)._fill_id(id);
	}

	public RecList21 getRecListPos(Long idKopf, boolean addDeleted) throws myException {
		SEL s = new SEL("*").FROM(this.m_tabPos).WHERE(new vgl(this.m_fieldPosToHead, idKopf.toString()));
		if (!addDeleted) {
			s.AND(new TermSimple("NVL(DELETED,'N')='N'"));
		}
		
		return new RecList21(this.m_tabPos)._fill(s.s());
	}

	public IF_Field getFieldPosToHead() {
		return m_fieldPosToHead;
	}

	public IF_Field getFieldIndexHead() {
		return m_fieldIndexHead;
	}

	public IF_Field getFieldIndexPosition() {
		return m_fieldIndexPosition;
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		String ret = null;
		switch (this) {
		case ANGEBOT:
			ret = "Angebot";
			break;
		case ABNAHMEANGEBOT:
			ret = "Angebot";
			break;
		case EK_KONTRAKT:
			ret = "Angebot";
			break;
		case GUTSCHRIFT:
			ret = "Angebot";
			break;
		case RECHNUNG:
			ret = "Angebot";
			break;
		case TRANSPORT:
			ret = "Angebot";
			break;
		case VK_KONTRAKT:
			ret = "Angebot";
			break;
		default:
			ret = null;
			break;
		}
		return ret;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VORGANGSART.values(), emptyPairInFront);
	}
	
	

}
