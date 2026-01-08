 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Fuhre;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_ArtikelBezHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Container;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_ArtikelBezKunde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_SorteHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_container;
 
 
 
public class WK_RB_MASK_DataObjectCollector extends RB_DataobjectsCollector_V22 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    
    /** bei NEW
     * 
     * @author manfred
     * @date 17.09.2020
     *
     * @param p_tpHashMap
     * @throws myException
     */
    public WK_RB_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
    	RecDOWiegekarte wk = new RecDOWiegekarte(MASK_STATUS.NEW);
    				  wk._setTransportHashMap(m_tpHashMap);
    	
    	RecDOWiegekarteBefund wkBefund = new RecDOWiegekarteBefund(MASK_STATUS.NEW);
    						wkBefund._setTransportHashMap(m_tpHashMap);
        
    						
    	if (wkBefund.is_newRecordSet()) {
    	    wkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
    	} 
    	
        registerComponent(RecDOWiegekarte.key,		wk);
        registerComponent(RecDOWiegekarteBefund.key,	wkBefund);
                
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
    
    
    /** bei EDIT / VIEW
     *
     * @param p_tpHashMap
     * @param id_WIEGEKARTE
     * @param status
     * @throws myException
     */
    public WK_RB_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_WIEGEKARTE, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
//        this._setUseRec21(true);
 
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);

        
        MyLong lIdWiegekarte = new MyLong(id_WIEGEKARTE);
 
        RecDOWiegekarte 	wk = null;
        RecDOWiegekarteBefund wkBefund = null;
        
        String _idAdresseLager = null;
        String _idStandort     = null;
       
 
    	if (lIdWiegekarte.isOK()) {
    		if (status.equals(MASK_STATUS.NEW_COPY)) {
    			
    			wk = new RecDOWiegekarte(status)._fill_id(lIdWiegekarte.get_lValue());
        		wk._setTransportHashMap(m_tpHashMap);
        		
        		// Lager und Standort vom originalen Record lesen
    			_idStandort = wk.get_ufs_lastVal(WIEGEKARTE.id_waage_standort);
    			_idAdresseLager = wk.get_ufs_lastVal(WIEGEKARTE.id_adresse_lager);
        		
        		wkBefund = new RecDOWiegekarteBefund(MASK_STATUS.NEW);
        		wkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
				wkBefund._setTransportHashMap(m_tpHashMap);
				
    		} else {

    			wk = new RecDOWiegekarte(status)._fill_id(lIdWiegekarte.get_lValue());
    			wk._setTransportHashMap(m_tpHashMap);
    			
    			wkBefund = new RecDOWiegekarteBefund(status)._fill_from_wiegekarte(wk);
    			wkBefund._setTransportHashMap(m_tpHashMap);
    			
    			if (wkBefund.is_newRecordSet()) {
    				wkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, wk.get_key_value());
    			} 
    			
    			// Lager und Standort in der Transporthashmap setzen
    			_idStandort = wk.get_ufs_lastVal(WIEGEKARTE.id_waage_standort);
    			_idAdresseLager = wk.get_ufs_lastVal(WIEGEKARTE.id_adresse_lager);
    		}
	    					
       		registerComponent(RecDOWiegekarte.key,	wk);
       		registerComponent(RecDOWiegekarteBefund.key,	wkBefund);
       		

    		m_tpHashMap._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_LAGER,_idAdresseLager );
    		m_tpHashMap._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_STANDORT,_idStandort );
       		
        } else {
        	throw new myException("ID-Wiegekarte konnte nicht gelesen werden !");
        } 
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
    }



      
    @Override
	public void database_to_dataobject(Object startPoint) throws myException {
    	
    	
	}


    


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21#manipulate_filled_records_before_save(panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21, panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V22 do_collector, MyE2_MessageVector mv)	throws myException {
	
		if (mv.hasAlarms()) {
			return;
		}
		
		RecDOWiegekarte  recWK = (RecDOWiegekarte) do_collector.get_v22(RecDOWiegekarte.key);
		RecDOWiegekarteBefund  recWkBefundung = (RecDOWiegekarteBefund) do_collector.get_v22(RecDOWiegekarteBefund.key);

		if( new RB_MaskController(this).getMaskStatus().equals(MASK_STATUS.NEW_COPY)){
			recWkBefundung._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
		}
		
		
		WK_RB_MASK_ComponentMap_Wiegekarte compMapWK = (WK_RB_MASK_ComponentMap_Wiegekarte) m_tpHashMap.getMaskComponentMapCollector().get(WK_RB_CONST.getLeadingMaskKey());
		WK_RB_Comp_Fuhre compFuhre = (WK_RB_Comp_Fuhre) compMapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key());
		
		// die Werte von der Fuhre hier übernehmen, da 2 Werte auf einem Key liegen:
		Long idFuhre = new MyLong(compFuhre.getIDFuhre()).get_oLong();
		Long idFuhreOrt = new MyLong(compFuhre.getIDFuhreOrt()).get_oLong();
		
		recWK._setNewVal(WIEGEKARTE.id_vpos_tpa_fuhre, idFuhre, bibMSG.MV());
		recWK._setNewVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort, idFuhreOrt, bibMSG.MV());
		
		// Sorten entweder aus dem Selectfield für Kundensorte oder dem Suchfeld Sorte-Hand
		WK_RB_Search_ArtikelBezHand 	oSearchArtikel 		= (WK_RB_Search_ArtikelBezHand) compMapWK.getComp(WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key());
		WK_RB_SelField_ArtikelBezKunde 	oSelArtikelKunde 	= (WK_RB_SelField_ArtikelBezKunde) compMapWK.getComp(WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key());
		WK_RB_cb_SorteHand 				cbSorteHand 		= (WK_RB_cb_SorteHand) compMapWK.getComp(WIEGEKARTE.sorte_hand.fk());
		

		Long idArtikelBez = null;
		Long idArtikel = null;
		if (cbSorteHand.isSelected()) {
			idArtikelBez 	= new MyLong(oSearchArtikel._get_idArtikelBez()).get_oLong();
			idArtikel 		= new MyLong(oSearchArtikel._get_idArtikel()).get_oLong();
		}	else {
			idArtikelBez 	= new MyLong(oSelArtikelKunde._get_idArtikelBez()).get_oLong();
			idArtikel 		= new MyLong(oSelArtikelKunde._get_idArtikel()).get_oLong();
		}
		
		recWK._setNewVal(WIEGEKARTE.id_artikel_sorte, idArtikel, mv);
		recWK._setNewVal(WIEGEKARTE.id_artikel_bez, idArtikelBez, mv);

		
		// bei einem neuen Record die LFD-Nr setzen
		if (recWK.is_newRecordSet()) {
			recWK._setNewValueInDatabaseTerminus(WIEGEKARTE.lfd_nr, "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL");

			recWK._setNewVal(WIEGEKARTE.id_adresse_lager, new MyLong((String) m_tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_LAGER)).get_lValue(), mv);
			recWK._setNewVal(WIEGEKARTE.id_waage_standort,new MyLong((String) m_tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_STANDORT)).get_lValue() , mv);
		} 
		
		// Nettogewicht setzen, wenn die Waegungen vorhanden sind
		RecDOWaegung1 waeg1 = recWK._get_Waegung1();
		RecDOWaegung2 waeg2 = recWK._get_Waegung2();
		BigDecimal bdGewichtNetto = null;
		if (waeg1 != null && 
			waeg2 != null && 
			waeg1.get_myBigDecimal_lastVal(WAEGUNG.gewicht) != null &&
			waeg2.get_myBigDecimal_lastVal(WAEGUNG.gewicht) != null )  {
			BigDecimal bd1 = waeg1.get_myBigDecimal_lastVal(WAEGUNG.gewicht).get_bdWert();
			BigDecimal bd2 = waeg2.get_myBigDecimal_lastVal(WAEGUNG.gewicht,new MyBigDecimal(BigDecimal.ZERO)).get_bdWert();
			
			bdGewichtNetto = bd1.subtract(bd2).abs();
		} 
		recWK._setNewVal(WIEGEKARTE.nettogewicht, bdGewichtNetto, mv);

		// kennzeichen normalisieren
		String kennzeichen = recWK.get_fs_lastVal(WIEGEKARTE.kennzeichen);
		String trailer = recWK.get_fs_lastVal(WIEGEKARTE.trailer);
		recWK._setNewVal(WIEGEKARTE.kennzeichen, normalisiereKennzeichen(kennzeichen), mv);
		recWK._setNewVal(WIEGEKARTE.trailer, normalisiereKennzeichen(trailer), mv);
		
		
		// Gesamtverwiegung setzen
		WK_RB_SelField_WiegekartenTyp wkTyp = (WK_RB_SelField_WiegekartenTyp) compMapWK.getComp(WIEGEKARTE.typ_wiegekarte);
		if (wkTyp._getCurrentSelectedTyp() != null && wkTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.GESAMTVERWIEGUNG)) {
			recWK._setNewVal(WIEGEKARTE.ist_gesamtverwiegung, "Y", mv);
		} else {
			recWK._setNewVal(WIEGEKARTE.ist_gesamtverwiegung, "N", mv);
		}
		
		
		// Container-Werte in WK eintragen
		// wenn id_container vorhanden ist, dann prüfen, wie das Gewicht und die UVV ist:
		String idContainer = null; //recWK.get_fs_lastVal(WIEGEKARTE.id_container_eigen);
		
		WK_RB_Search_Container cCont = (WK_RB_Search_Container) compMapWK.getComp(WIEGEKARTE.id_container_eigen);
		idContainer = cCont._get_IDContainer();
		
		if (S.isFull(idContainer)) {
			Rec_container recCont = new Rec_container()._fill_id(idContainer);
			Date dtUVV = recCont.getDateResultValueNative(CONTAINER.uvv_datum);
			BigDecimal bdTaraKorr = recCont.getBigDecimalDbValue(CONTAINER.tara_korrektur);
			recWK._setNewVal(WIEGEKARTE.uvv_datum_container, dtUVV, mv);
			recWK._setNewVal(WIEGEKARTE.tara_korr_container, bdTaraKorr, mv);
			
		}
		
		
	}

	
	
	private String normalisiereKennzeichen(String kennzeichen_ori){
		if (kennzeichen_ori!= null){
			kennzeichen_ori = kennzeichen_ori.replaceAll("-", " ");
			kennzeichen_ori = kennzeichen_ori.toUpperCase();
			// alle mehrfachen Leerzeichen rauslöschen
			kennzeichen_ori = kennzeichen_ori.replaceAll("\\s{2,}", " ");
		} 
		return kennzeichen_ori;
	}
	
	
	
	
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21#execute_final_statements_in_open_transaction(panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21, panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V22 do_collector,	MyE2_MessageVector mv) throws myException {
		
		
		Long idWK = null;
		RecDOWiegekarte  recWK = (RecDOWiegekarte) do_collector.get_v22(RecDOWiegekarte.key);
		
		if (recWK.is_newRecordSet()) {
			String id =  this.get_LastWrittenNewID(_TAB.wiegekarte);
			MyLong lID = new MyLong(id);
			idWK = lID.getLong();
		} else {
			idWK = recWK.getId();
		}
		
		RecDOWaegung1 waeg1 = recWK._get_Waegung1();
		if (waeg1 != null && waeg1.is_newRecordSet()) {
			if(recWK.is_ExistingRecord() ){
				waeg1._setNewValue(WAEGUNG.id_wiegekarte, recWK.getId(),mv);
			}else {
				waeg1._setNewValueInDatabaseTerminus(WAEGUNG.id_wiegekarte,_TAB.wiegekarte.seq_currval());
			}
			waeg1._SAVE(false, mv)._rebuildRecord()	;
		
		}
		
		RecDOWaegung2 waeg2 = recWK._get_Waegung2();
		if (waeg2 != null && waeg2.is_newRecordSet()) {
			if(recWK.is_ExistingRecord() ){
				waeg2._setNewValue(WAEGUNG.id_wiegekarte, recWK.getId(),mv);
			}else {
				waeg2._setNewValueInDatabaseTerminus(WAEGUNG.id_wiegekarte,_TAB.wiegekarte.seq_currval());
			}
			waeg2._SAVE(false, mv)._rebuildRecord();

		}
		

		
		
		
		// Abzüge und gesamtgewicht berechnen und eintragen
		String sSqlAbzugsgewicht = 	" UPDATE  " + bibE2.cTO() + ".jt_wiegekarte W  " +
								" SET W.GEWICHT_ABZUG = ( SELECT sum(A.GEWICHT_EINZEL * A.MENGE)    FROM  " + bibE2.cTO() + ".JT_WIEGEKARTE_ABZUG_GEB A    WHERE A.ID_WIEGEKARTE = W.ID_WIEGEKARTE ) ," +
								" W.GEWICHT_NACH_ABZUG = (CASE WHEN W.NETTOGEWICHT is not null " +
								" THEN W.NETTOGEWICHT - NVL( ( SELECT sum(A.GEWICHT_EINZEL * A.MENGE)    FROM  " + bibE2.cTO() + ".JT_WIEGEKARTE_ABZUG_GEB A    WHERE A.ID_WIEGEKARTE = W.ID_WIEGEKARTE),0) " +
								" ELSE null END)" +
								" where W.ID_WIEGEKARTE = ?";
	
		String sSqlAbzugsgewicht_Fuhre = " UPDATE  " + bibE2.cTO() + ".JT_WIEGEKARTE W  " +
				" SET W.GEWICHT_ABZUG_FUHRE = ( SELECT SUM( CASE  WHEN A.ABZUG_MENGE IS NOT NULL  THEN A.ABZUG_MENGE   ELSE nvl(W.GEWICHT_NACH_ABZUG,0) * A.ABZUG_PROZENT / 100   END ) " +
				" 	 FROM   " + bibE2.cTO() + ".JT_WIEGEKARTE_MGE_ABZ A " +
				"    WHERE   A.ID_WIEGEKARTE = W.ID_WIEGEKARTE  )" +
				"   " +
				", W.GEWICHT_NACH_ABZUG_FUHRE =  (CASE WHEN W.GEWICHT_NACH_ABZUG is not null " +
				"  THEN( W.GEWICHT_NACH_ABZUG - NVL(( SELECT SUM( CASE  WHEN A.ABZUG_MENGE IS NOT NULL  THEN A.ABZUG_MENGE   ELSE nvl(W.GEWICHT_NACH_ABZUG,0) * A.ABZUG_PROZENT / 100   END ) " +
				" 	 FROM   " + bibE2.cTO() + ".JT_WIEGEKARTE_MGE_ABZ A " +
				"    WHERE   A.ID_WIEGEKARTE = W.ID_WIEGEKARTE  ),0) ) ELSE null END)" +
				" WHERE W.ID_WIEGEKARTE = ? " ;
		
		SqlStringExtended sqlAbzugsgewicht = new SqlStringExtended(sSqlAbzugsgewicht);
		sqlAbzugsgewicht._addParameter(new Param_Long(idWK));
		
		SqlStringExtended sqlAbzugsgewichtFuhre = new SqlStringExtended(sSqlAbzugsgewicht_Fuhre);
		sqlAbzugsgewichtFuhre._addParameter(new Param_Long(idWK));
		
		boolean bOK = true;
		bOK &= bibDB.ExecSQL(sqlAbzugsgewicht, false);
		bOK &= bibDB.ExecSQL(sqlAbzugsgewichtFuhre, false);
		
		if (!bOK) {
			mv._addAlarm(new MyString("Fehler bei der Berechnung der Netto-Gewichts-Werte"));
		}
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21#get_copy()
	 */
	@Override
	public RB_DataobjectsCollector_V22 get_copy() throws myException {
		return null;
	}
}
 
 
