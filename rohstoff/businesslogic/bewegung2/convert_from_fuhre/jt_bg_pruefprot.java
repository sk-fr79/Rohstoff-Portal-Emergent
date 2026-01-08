/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;
import java.util.Date;

import com.sun.tools.javac.v8.tree.Tree.Case;

import panter.gmbh.basics4project.EnPruefungTyp;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;


/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_pruefprot extends REC_Base{


	


	private DOString 	ID_BG_PRUEFPROT = new DOString(BG_PRUEFPROT.id_bg_pruefprot,null,"").setRaw(true);
	private DOLong 		ID_MANDANT = new DOLong(BG_PRUEFPROT.id_mandant);
	
	private DODate 		LETZTE_AENDERUNG = new DODate(BG_PRUEFPROT.letzte_aenderung);
	private DOString 	GEAENDERT_VON 	= new DOString(BG_PRUEFPROT.geaendert_von);
	private DOString 	ERZEUGT_VON 	= new DOString(BG_PRUEFPROT.erzeugt_von);
	private DODate 		ERZEUGT_AM 		= new DODate(BG_PRUEFPROT.erzeugt_am);
	
	private DOString	EN_PRUEFUNG_TYP = new DOString(BG_PRUEFPROT.en_pruefung_typ);
	private DODate		PRUEFUNG_AM 	= new DODate(BG_PRUEFPROT.pruefung_am);
	private DOLong 		ID_USER 		= new DOLong(BG_PRUEFPROT.id_user);
	private DOString 	BASE_TABLENAME 	= new DOString(BG_PRUEFPROT.base_tablename);
	private DOString 	ID_BASE_TABLE 	= new DOString(BG_PRUEFPROT.id_base_table,"","");


	public jt_bg_pruefprot( conv_helper helper) {
		super(_TAB.bg_pruefprot);
		this._helper = helper;
		this.initFieldList();
	}
	
	


	
	public jt_bg_pruefprot(Rec21_VPOS_TPA_FUHRE_ext fuhre, EnPruefungTyp typ, conv_helper helper) throws myException{
		this(helper);
		_helper = helper;
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));
		setID_BG_PRUEFPROT(BG_PRUEFPROT._tab().seq_nextval());

		
		setEN_PRUEFUNG_TYP(typ.dbVal());
		
		if 	(typ.equals(EnPruefungTyp.BG_ATOM_ABSCHLUSS)){
			// abschluss ?
		} else if 	(typ.equals(EnPruefungTyp.BG_ATOM_MENGENKONTROLLE)){
			setPRUEFUNG_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.pruefung_lademenge));
			setID_USER( _helper.getIDUserFromUser ( fuhre.getUfs(VPOS_TPA_FUHRE.pruefung_lademenge_von)));
		
		} else if 	(typ.equals(EnPruefungTyp.BG_ATOM_PREISKONTROLLE)){
			setPRUEFUNG_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.pruefung_ek_preis_am));
			setID_USER( _helper.getIDUserFromUser ( fuhre.getUfs(VPOS_TPA_FUHRE.pruefung_ek_preis_von)));
		
		} else if 	(typ.equals(EnPruefungTyp.BG_ATOM_ABSCHLUSS)){
			// abschluss ?
		} else if 	(typ.equals(EnPruefungTyp.BG_ATOM_MENGENKONTROLLE)){
			setPRUEFUNG_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.pruefung_ablademenge));
			setID_USER( _helper.getIDUserFromUser ( fuhre.getUfs(VPOS_TPA_FUHRE.pruefung_ablademenge_von)));
		
		} else if 	(typ.equals(EnPruefungTyp.BG_ATOM_PREISKONTROLLE)){
			setPRUEFUNG_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.pruefung_vk_preis_am));
			setID_USER( _helper.getIDUserFromUser ( fuhre.getUfs(VPOS_TPA_FUHRE.pruefung_vk_preis_von)));
			
		}
			
	}


	
	
	public DOString getID_BG_PRUEFPROT() {
	    return ID_BG_PRUEFPROT;
	}

	public jt_bg_pruefprot setID_BG_PRUEFPROT(String pID_BG_PRUEFPROT) {
	    ID_BG_PRUEFPROT.setValue(pID_BG_PRUEFPROT);
	    return this;
	}
	
	
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_pruefprot setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_pruefprot setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}

	public DOString getEN_PRUEFUNG_TYP() {
	    return EN_PRUEFUNG_TYP;
	}

	public jt_bg_pruefprot setEN_PRUEFUNG_TYP(String pEN_PRUEFUNG_TYP) {
		EN_PRUEFUNG_TYP.setValue(pEN_PRUEFUNG_TYP);
	    return this;
	}


	public DODate getPRUEFUNG_AM() {
	    return PRUEFUNG_AM;
	}

	public jt_bg_pruefprot setPRUEFUNG_AM(Date pPRUEFUNG_AM) {
		PRUEFUNG_AM.setValue(pPRUEFUNG_AM);
	    return this;
	}


	public DOString getBASE_TABLENAME() {
	    return BASE_TABLENAME;
	}

	public jt_bg_pruefprot setBASE_TABLENAME(String pBASE_TABLENAME) {
		BASE_TABLENAME.setValue(pBASE_TABLENAME);
	    return this;
	}
	


	public DOString getID_BASE_TABLE() {
	    return ID_BASE_TABLE;
	}

	public jt_bg_pruefprot setID_BASE_TABLE(String pID_BASE_TABLE) {
		ID_BASE_TABLE.setValue(pID_BASE_TABLE);
	    return this;
	}


	public DOLong getID_USER() {
	    return ID_USER;
	}

	public jt_bg_pruefprot setID_USER(Long pID_USER) {
	    ID_USER.setValue(pID_USER);
	    return this;
	}






	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_pruefprot setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}




	
	

	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_pruefprot setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_pruefprot setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}










	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {
		
		m_vDataObjects.addElement ( ID_BG_PRUEFPROT );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( EN_PRUEFUNG_TYP );
		m_vDataObjects.addElement ( PRUEFUNG_AM );
		m_vDataObjects.addElement ( ID_USER );
		m_vDataObjects.addElement ( BASE_TABLENAME );
	}




	
}
