package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_GEBINDE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ValidateOnSave;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Abzug_Geb;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Gebinde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Mge_Abz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Wiegekarte;

/**
 * Beinhaltet die Logik für Wiegekarten-Verwaltung aus der Liste heraus
 * @author manfred
 *
 */
public class WK_RB_LIST_WiegekartenHandler {

	String 					_ID_Wiegekarte = null;
	Vector<String>  		_vSQLStatements = null;
	MyE2_MessageVector 		_mv = null;
	
	/** 
	 * Standardkonstruktur
	 */
	public WK_RB_LIST_WiegekartenHandler(String idWiegekarte, MyE2_MessageVector mv){
		_vSQLStatements = new Vector<String>();

		_ID_Wiegekarte = idWiegekarte;
		_mv = mv;
	}

	
	
	/**
	 * Methode zum externen Zuordnen von Fuhren
	 * @author manfred
	 * @date 04.10.2017
	 *
	 * @param IDFuhre
	 * @param IDWiegekarte
	 * @return
	 * @throws myException 
	 */
	public WK_RB_LIST_WiegekartenHandler setFuhreInWiegekarte( String IDFuhre, String IDFuhreOrt) throws myException{
		boolean bOK = false;
		// die ID Fuhre muss immer da sein, auch beim Fuhrenort...
		if (bibALL.isEmpty(IDFuhre)) {
			_mv._addAlarm("Keine Fuhren-ID vorhanden!");
			return this;
		}
		
		if (bibALL.isEmpty(_ID_Wiegekarte)) {
			_mv._addAlarm("Keine Wiegekarten-ID vorhanden!");
			return this;
		}
		
		RecDOWiegekarte	 recWK = new RecDOWiegekarte(MASK_STATUS.EDIT)._fill_id(_ID_Wiegekarte);
		
		if (recWK == null) {
			return this;
		}

		Long idWiegekarte = recWK.getActualID();
		
		
		// prüfen, ob die Wiegekarte schon eine Fuhre zugewiesen bekommen hat und ob es die gleiche ist...
		String IDFuhreWK = recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre,"");
		String IDFuhreOrtWK = recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort,"") ;	
		
		
		// Falls die IDs der Fuhre aus der Wiegekarte und die der Fuhre die gesetzt werden soll, übereinstimmen....
		if (IDFuhreWK.equals(IDFuhre) && IDFuhreOrtWK.equals(IDFuhreOrt) ){
			_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Fuhre ist schon mit dieser Wiegekarte verknüpft")));
			return this;
		}
		
		// die Wiegekarte wurde schon mit einer anderen Fuhre verknüpft
		if ( (  ! bibALL.isEmpty(IDFuhreWK) && 	
				! (IDFuhreWK+"-"+IDFuhreOrtWK).equals(IDFuhre+"-"+IDFuhreOrt))) {
			_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Wiegekarte ist schon mit einer anderen Fuhre verknüpft!")));
			return this;
		}
		
		
		
				
		// FuhrenortID normalisieren
		IDFuhreOrt = IDFuhreOrt == null ? "" : IDFuhreOrt ;  

		
		IDFuhreWK 		= (IDFuhreWK == null ? "" : IDFuhreWK);
		IDFuhreOrtWK 	= (IDFuhreOrtWK == null ? "" : IDFuhreOrtWK);
		
		SEL sel = new SEL(_TAB.wiegekarte).FROM(_TAB.wiegekarte)
				.WHERE(new TermSimple(WIEGEKARTE.id_wiegekarte.fn() + " != ?"))
				.AND(new vgl(new TermSimple(" nvl(STORNO,'N') "),COMP.EQ, new TermSimple("'N'") ))
				.AND(new vgl(new TermSimple(" nvl(ID_VPOS_TPA_FUHRE,'0') || '-' || nvl(ID_VPOS_TPA_FUHRE_ORT,'')"), COMP.EQ, new TermSimple("'" + IDFuhre + "-" + IDFuhreOrt + "'" ) ) );
		
		
		SqlStringExtended sql_ext = new SqlStringExtended(sel.s());
		sql_ext.getValuesList().add(new Param_Long(idWiegekarte));
		
		RecList_Wiegekarte rlWK = new RecList_Wiegekarte(sql_ext);
		if (rlWK != null && rlWK.size() > 0) {
			_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Fuhre wurde schon einmal mit einer Wiegekarte verknüpft. Bitte ändern Sie die Fuhre oder löschen Sie die Fuhre in der anderen Wiegekarte!")));
			return this;
		}


		// Fuhrenids in der Wiegekarte setzen
		recWK._setNewValue(WIEGEKARTE.id_vpos_tpa_fuhre, IDFuhre, _mv);
		recWK._setNewValue(WIEGEKARTE.id_vpos_tpa_fuhre_ort, IDFuhreOrt, _mv);
		
		try {
			Rec22 rec = recWK._SAVE(true);
			_mv.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Fuhre wurde der Wiegekarte zugeordnet.")));
		} catch (Exception e) {
			_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler: Die Wiegekarte wurde nicht gespeichert.")));
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
	

	
	/**
	 * @author manfred
	 * 
	 * @throws myException
	 */
	public Long  _createStornoKorrekturWiegekarte(  boolean bCommit) throws myException   {

		RecDOWiegekarte rec 			=  new RecDOWiegekarte(MASK_STATUS.NEW)._fill_id(this._ID_Wiegekarte);
		RecDOWiegekarteBefund recBefund = rec._get_Befundung();
		
		
		// Anlegen der Folgewiegekarte...		
		Long idBaseParent = rec.get_raw_resultValue_Long(WIEGEKARTE.id_wiegekarte);
		
		
		VEK<IF_Field> fieldsToClear = new VEK<IF_Field>();
		fieldsToClear._a(rec.getAutoFieldsStd());
		fieldsToClear
						._a(WIEGEKARTE.id_wiegekarte)
						._a(WIEGEKARTE.lfd_nr)
						._a(WIEGEKARTE.gedruckt_am)
						._a(WIEGEKARTE.druckzaehler)
						._a(WIEGEKARTE.druckzaehler_eingangsschein)
						._a(WIEGEKARTE.storno)
						._a(WIEGEKARTE.storno_grund)
						._a(WIEGEKARTE.storniert_am)
						._a(WIEGEKARTE.storniert_von)
						._a(WIEGEKARTE.nettogewicht)
						._a(WIEGEKARTE.gewicht_abzug)
						._a(WIEGEKARTE.gewicht_abzug_fuhre)
						._a(WIEGEKARTE.gewicht_nach_abzug_fuhre)
						._a(WIEGEKARTE.gewicht_nach_abzug)
//					  ._a(WIEGEKARTE.anz_behaelter)
//					  ._a(WIEGEKARTE.anz_bigbags)
//					  ._a(WIEGEKARTE.anz_gitterboxen)
//					  ._a(WIEGEKARTE.anz_paletten)
//					  ._a(WIEGEKARTE.befund)
//					  ._a(WIEGEKARTE.bemerkung1)
//					  ._a(WIEGEKARTE.bemerkung2)
//					  ._a(WIEGEKARTE.bemerkung_intern)
//					  ._a(WIEGEKARTE.container_nr)
//					  ._a(WIEGEKARTE.container_tara)
//					  ._a(WIEGEKARTE.id_container_eigen)
//					  ._a(WIEGEKARTE.befund)
//					  ._a(WIEGEKARTE.es_nr)
//					  ._a(WIEGEKARTE.id_artikel_bez)
//					  ._a(WIEGEKARTE.id_artikel_sorte)
//					  ._a(WIEGEKARTE.id_wiegekarte_parent)
//					  ._a(WIEGEKARTE.sorte_hand)
//					  ._a(WIEGEKARTE.ist_gesamtverwiegung)
//					  ._a(WIEGEKARTE.container_nr)
//					  ._a(WIEGEKARTE.container_tara)
//					  ._a(WIEGEKARTE.siegel_nr)
//					  ._a(WIEGEKARTE.gueterkategorie)
//					  ._a(WIEGEKARTE.id_lagerplatz_absetz)
//					  ._a(WIEGEKARTE.id_lagerplatz_schuett)
					  ;
					  
	
		RecDOWiegekarte _recCopy  = new RecDOWiegekarte(rec.getRecForCreateCopy(fieldsToClear,_mv),MASK_STATUS.NEW_COPY);
		
		
		_recCopy._setNewValueInDatabaseTerminus(WIEGEKARTE.lfd_nr, "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL");
		_recCopy._setNewValueInDatabaseTerminus(WIEGEKARTE.id_wiegekarte, _TAB.wiegekarte.seq_nextval());
		//_recCopy._setNewValue(WIEGEKARTE.id_wiegekarte_storno, idBaseParent, _mv);
		
		// Typ der Wiegekarte ist Wiegekarte_Korrektur
		_recCopy._setNewValue(WIEGEKARTE.typ_wiegekarte, WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR.db_val(), _mv);

		
		// WK_BEFUND erzeugen
		VEK<IF_Field> fieldsToClearBefund = new VEK<IF_Field>();
		fieldsToClearBefund._a(recBefund.getAutoFieldsStd());
		fieldsToClearBefund._a(WIEGEKARTE_BEFUND.id_wiegekarte_befund);
		
		RecDOWiegekarteBefund  _recwkBefund = new RecDOWiegekarteBefund(recBefund.getRecForCreateCopy(fieldsToClearBefund, _mv),MASK_STATUS.NEW_COPY);
		_recwkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
		_recwkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte_befund, _TAB.wiegekarte_befund.seq_nextval());
				
		
		
		
		_recCopy._SAVE(false, _mv);
		_recwkBefund._SAVE(false, _mv);
		
		Long id = null;
		
		if (_mv.isOK()) {
			id = _recCopy.getId();
			Long id_new = _recCopy.getActualID();
			
			// die Liste der Tara-Abzüge kopieren
			RecList_WK_Abzug_Geb rlGeb = new RecList_WK_Abzug_Geb(this._ID_Wiegekarte);
			for (Rec22 r : rlGeb) {
				Rec22 rNew = new Rec22(r.getRecForCreateCopyStdExclude(_mv));
				rNew._setNewValue(WIEGEKARTE_ABZUG_GEB.id_wiegekarte , id.toString(),_mv);
				rNew._setNewValueInDatabaseTerminus(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb, _TAB.wiegekarte_abzug_geb.seq_nextval());
				
				try {
					rNew._SAVE(false);
				} catch (Exception e) {
					// 	
				}
			}
			
			
			
			// die Liste der Fuhrnabzüge kopieren
			RecList_WK_Mge_Abz rlMge = new RecList_WK_Mge_Abz(this._ID_Wiegekarte);
			for (Rec22 r : rlMge) {
				Rec22 rNew = new Rec22(r.getRecForCreateCopyStdExclude(_mv));
				rNew._setNewValue(WIEGEKARTE_MGE_ABZ.id_wiegekarte , id.toString(),_mv);
				rNew._setNewValueInDatabaseTerminus(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz, _TAB.wiegekarte_mge_abz.seq_nextval());
				try {
					rNew._SAVE(false);
				} catch (Exception e) {
					// 	
				}
				
			}

			boolean bOK = true;
			if (bCommit) {
				bOK = bibDB.Commit();
			} 
			
			if(bOK && bCommit) {
				_mv.add_MESSAGE(new MyE2_Info_Message("StornoKorrektur-Wiegekarte angelegt: ID " + String.valueOf(id) + " ... " + String.valueOf(id_new)));
			}
			
		}
		
		return id;
	}

	
	
}
