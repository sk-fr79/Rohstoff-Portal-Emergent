/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;

/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_del_info extends REC_Base{



	private DOString 	ID_BG_DEL_INFO = new DOString(BG_DEL_INFO.id_bg_del_info,null,"").setRaw(true);
	private DOLong 		ID_MANDANT = new DOLong(BG_DEL_INFO.id_mandant);
	
	private DODate 		LETZTE_AENDERUNG = new DODate(BG_DEL_INFO.letzte_aenderung);
	private DOString 	GEAENDERT_VON = new DOString(BG_DEL_INFO.geaendert_von);
	private DOString 	ERZEUGT_VON = new DOString(BG_DEL_INFO.erzeugt_von);
	private DODate 		ERZEUGT_AM = new DODate(BG_DEL_INFO.erzeugt_am);
	
	private DODate 		DELETE_DATUM = new DODate(BG_DEL_INFO.delete_datum);
	private DOString 	DELETE_GRUND = new DOString(BG_DEL_INFO.delete_grund);

	private DOLong 		ID_USER = new DOLong(BG_DEL_INFO.id_user);
	
	// Vektor
	private jt_bg_vektor jt_bg_vektor = null;

	

	public jt_bg_del_info( conv_helper helper) {
		super(_TAB.bg_del_info);
		this._helper = helper;
		this.initFieldList();
	}
	
	


	
	public jt_bg_del_info(Rec21_VPOS_TPA_FUHRE_ext fuhre, conv_helper helper) throws myException{
		this(helper);

		setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_nextval());
		setDELETE_DATUM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.del_date));
		setDELETE_GRUND(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.del_grund));
		
		setID_USER(helper.getIDUserFromUser(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.del_kuerzel)));
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

	}
	
	public jt_bg_del_info(Rec21_VPOS_TPA_FUHRE_ORT_ext fuhre, conv_helper helper) throws myException{
		this(helper);

		setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_nextval());
		setDELETE_DATUM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.del_date));
		setDELETE_GRUND(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.del_grund));
		setID_USER(helper.getIDUserFromUser(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.del_kuerzel)));
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));

	}
	
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_del_info setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_del_info setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}


	public DODate getDELETE_DATUM() {
	    return DELETE_DATUM;
	}

	public jt_bg_del_info setDELETE_DATUM(Date pDELETE_DATUM) {
	    DELETE_DATUM.setValue(pDELETE_DATUM);
	    return this;
	}


	public DOString getDELETE_GRUND() {
	    return DELETE_GRUND;
	}

	public jt_bg_del_info setDELETE_GRUND(String pDELETE_GRUND) {
	    DELETE_GRUND.setValue(pDELETE_GRUND);
	    return this;
	}


	public DOLong getID_USER() {
	    return ID_USER;
	}

	public jt_bg_del_info setID_USER(Long pID_USER) {
	    ID_USER.setValue(pID_USER);
	    return this;
	}




	public DOString getID_BG_DEL_INFO() {
	    return ID_BG_DEL_INFO;
	}

	public jt_bg_del_info setID_BG_DEL_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_DEL_INFO.setValue(pID_BG_DEL_INFO);
	    return this;
	}


	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_del_info setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}




	
	

	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_del_info setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_del_info setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}




	public jt_bg_vektor get_jt_bg_vektor() {
		return jt_bg_vektor;
	}


	public jt_bg_del_info set_jt_bg_vektor(jt_bg_vektor jt_bg_vektor) {
		this.jt_bg_vektor = jt_bg_vektor;
		return this;
	}
	
	

	
	





	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {

		m_vDataObjects.addElement ( ID_BG_DEL_INFO );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( DELETE_DATUM );
		m_vDataObjects.addElement ( DELETE_GRUND );
		m_vDataObjects.addElement ( ID_USER );
	}




	
}
