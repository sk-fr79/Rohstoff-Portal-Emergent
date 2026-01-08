/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindAVVCodeV2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Artbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_BasicHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_InteractiveSettings;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_Search_ArtikelBezHand extends RB_HL_Search_V2_Artbez implements IF_HasChangeListeners<WK_RB_Search_ArtikelBezHand>{
	
	private boolean     _bLinked = true;
	
	private String		_id_artikel_bez = null;
	private String		_id_artikel		= null;
	
	private String 		_avv_code 		= "";
	private RB_lab		_labelResult   	= null;
	
	
	private RB_TransportHashMap _tpHashMap = null;
	
	public WK_RB_Search_ArtikelBezHand(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		_tpHashMap = p_tpHashMap;
		
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.anr1), COMP.LIKE, new TermSimple("'%#WERT#%'")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL_BEZ.anr2), COMP.LIKE, new TermSimple("'%#WERT#%'")));
		
		this._labelResult =  new RB_lab("")._fsa(_mFontSizeResultAdd);
//		this._setSearchIconAutomatikSearch();
//		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Bei leerem Suchfeld werden die Artikelbezeichner der Hauptsorte angezeigt !"));

		this.getButtonStartSearch()._addValidator(()-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();

//			if (getLongLiveVal(WIEGEKARTE.id_artikel_bez)==null) {
//				mv._addAlarm(S.ms("Bitte zuerst eine Sorte auswählen !"));
//			}
			
			return mv;
		});
		
		
		this._registerAfterFieldEraseEvents(()-> {
			_id_artikel = null;
			_id_artikel_bez = null;
			WK_RB_MC_InteractiveSettings mc = new WK_RB_MC_InteractiveSettings(this, bibMSG.MV());
			mc._setzeSortenInfo();
			_avv_code = "";
			try {
				refreshArtbez();
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				_setAVVCode(null);
		});

		
		this._registerAfterValueChangeEvents(()->{
			WK_RB_MC_InteractiveSettings mc = new WK_RB_MC_InteractiveSettings(this, bibMSG.MV());
			mc._setzeSortenInfo();
			try {
				Rec21_artikel_bez  artBez = new Rec21_artikel_bez()._fill_id(_id_artikel_bez);
				refreshArtbez();
//				_setAVVCode(mc._getAVVCodeSorteHand(artBez));
			} catch (myException e) {
				e.printStackTrace();
			}
		});
		
		
		
//		this._registerBeforeStartSearchEvent(()-> {
//			try {
//				_setAllowEmptySearchfield(false);
//				if (S.isEmpty(getTextFieldSearchInput().getText())) {
//					
//					Long idArtikel = getLongLiveVal(WIEGEKARTE.id_artikel_bez);
//					if (idArtikel!=null) {
//						getAndStatementCollectorOneTimeCondition().add(new vgl(ARTIKEL_BEZ.id_artikel_bez, idArtikel.toString()));
//						_setAllowEmptySearchfield(true);
//					}
//				}
//			} catch (myException e) {
//				e.printStackTrace();
//				bibMSG.MV()._addAlarm(S.ms("Bitte zuerst eine Sorte auswählen !"));
//			}
//		});
		
		
		
	}
	
	public WK_RB_Search_ArtikelBezHand _setOwnShape() {

		RB_gld gl1 = new RB_gld()._ins(2,2,4,2)._left_mid();
		this._clear()
		._a(this.getGridForResults()._bo_dd(), 		new RB_gld()._ins(0,2,4,2)._left_mid())
		._a(this.getTextFieldSearchInput(), new RB_gld()._ins(2,2,4,2)._left_mid())
		._a(this.getButtonErase(), 			new RB_gld()._ins(2,2,4,2)._left_mid())
		._a(this.getButtonStartSearch(), 	new RB_gld()._ins(2,2,4,2)._left_mid())
		._setSize(400,100,20,20)
		;
		
		return this;
	}
	
	
	
	@Override
	public void renderResultOnMask(E2_Grid resultContainer, Long id) throws myException {
		resultContainer._clear()._setSize(400);
		if (id!=null) {
			// volle Ergebniszeile
//			Rec21_artikel_bez  artBez = new Rec21_artikel_bez()._fill_id(id);
//			String sArtBez = artBez.__get_ANR1_ANR2() + "  " + artBez.getUfs(ARTIKEL_BEZ.artbez1,"");
//			
//			this._labelResult.setText(getCurrentArtbez(id.toString()));
			
			this.refreshArtbez();
			
			// AVV ermitteln
//			WK_RB_MC_InteractiveSettings mcSettings = new WK_RB_MC_InteractiveSettings(this, bibMSG.MV());
//			setAVVCode(mcSettings.getAVVCodeSorteHand(artBez));
			
			resultContainer	._a(this._labelResult, new RB_gld()._left_mid()._ins(0,0,0,0));
			
		}  else {
			// leere Ergebniszeile
			TextField emptyField = new TextField();
			emptyField.setEnabled(false);
			
			this._avv_code = "";
			
			
			MutableStyle oStyle = new MutableStyle();
			oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase(0));
			oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(0, new E2_ColorDDark(), Border.STYLE_NONE)); 

			emptyField.setStyle(oStyle);
			resultContainer	._a(emptyField, new RB_gld()._left_mid()._ins(0,0,0,0));
			
		}
	}
	
	
	
	private String getCurrentArtbez(String id) throws myException {
		Rec21_artikel_bez  artBez = new Rec21_artikel_bez()._fill_id(id);
		String sArtBez = artBez.__get_ANR1_ANR2() + "  " + artBez.getUfs(ARTIKEL_BEZ.artbez1,"");
		
		WK_RB_MC_InteractiveSettings mc = new WK_RB_MC_InteractiveSettings(this, bibMSG.MV());
		this._avv_code = mc._getAVVCodeSorteHand(artBez);
		
		sArtBez += " AVV:" + this._avv_code;
		
		return sArtBez;
	}
	
	
	public WK_RB_Search_ArtikelBezHand refreshArtbez() throws myException {
		String s = "";
		if (this._id_artikel_bez != null) {
			s = getCurrentArtbez(this._id_artikel_bez);// + this._avv_code;
		}
		
		this._labelResult.setText(s); 
		return this;
	}
	

	
	/**
	 * setzt den zusätzlich anzuzeigenden AVV-Code in der form '01-01-01'
	 * @author manfred
	 * @date 23.02.2021
	 *
	 * @param avvCode
	 * @return
	 * @throws myException
	 */
//	public WK_RB_Search_ArtikelBezHand _setAVVCode(String avvCode) throws myException {
//		this._avv_code = avvCode != null ? " AVV:" + avvCode : " *Kein AVV*";
//		this.refreshArtbez();
//		return this;
//	}
//	


	
	
	
	private int _mFontSizeResultAdd = 0;
	/**
	 * die Größenänderung in Pixel des Fonts des Ergebnis in der Maske
	 * @author manfred
	 * @date 14.04
	 *
	 * @param additionalFontSize
	 * @return
	 */
	public WK_RB_Search_ArtikelBezHand _setFontSizeAdd(int additionalFontSize) {
		_mFontSizeResultAdd = additionalFontSize;
		return this;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (_bLinked) {
			if (dataObject.get_RecORD() != null) {
				Rec22 r = (Rec22)dataObject;
				_id_artikel_bez 	= r.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez);
				_id_artikel  		= r.get_ufs_dbVal(WIEGEKARTE.id_artikel_sorte) ;
				
				if (r==null||r.is_newRecordSet()) {
					this.rb_set_db_value_manual("");
				} else {
					this.rb_set_db_value_manual(_id_artikel_bez);
				}
			}
		}
	}

	
	/**
	 * Nur wenn aktiv verlinkt, das setzen des Wertes zulassen
	 */
	@Override
	public void rb_set_db_value_manual(String dbValFormated) throws myException {
			if (_bLinked) {
				if (S.isFull(dbValFormated) ) {	
				_id_artikel_bez = dbValFormated;
				super.rb_set_db_value_manual(_get_idArtikelBez());
			} else {
				super.rb_set_db_value_manual("");
			}
		}
	}
	
	
	/**
	 * Wert muss von Hand mit den Funktionen _get_idArtikel() und _get_idArtikelBez() geholt werden..
	 */
	@Override
	public String rb_readValue_4_dataobject() {
		return null;
	}
	
	/**
	 * Löschen der hinterlegten Artikelnummern
	 * @author manfred
	 * @date 10.06.2020
	 *
	 */
	public void clearData() {
		_id_artikel = null;
		_id_artikel_bez = null;
	}

	
	/**
	 * Selektfield deaktivieren 
	 * @author manfred
	 * @date 28.05.2020
	 *
	 * @param _bActive
	 * @return
	 * @throws myException 
	 */
	public WK_RB_Search_ArtikelBezHand _setActive(boolean _bActive) throws myException {
		if (!_bActive) {
			// feld leeren
			this.rb_set_db_value_manual("");
		} 

		this._bLinked = _bActive;
		return this;
	}
	

	
	public String _get_idArtikelBez() {
		if (_bLinked) {
			return _id_artikel_bez;
		} else {
			return null;
		}
	}
	
	
	
	public String _get_idArtikel() {
		if (_bLinked) {
			readIdArtikel_From_IdArtbez();
			return _id_artikel;
		} else {
			return null;
		}
	}	
	
	
	private void readIdArtikel_From_IdArtbez() {
		
		if(_id_artikel_bez != null) {
			
			Rec20_artikel_bez a;
			try {
				a = new Rec20_artikel_bez()._fill_id(_id_artikel_bez);
				_id_artikel = a.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel);
			} catch (myException e) {
				// kein Artikel gefunden
			}
		}
		
	}


	private VEK<IF_ExecuterOnComponentV2<WK_RB_Search_ArtikelBezHand>>    changeListeners = new   VEK<>();

	@Override
	public WK_RB_Search_ArtikelBezHand _clearChangeListener() {
		changeListeners._clear();
		return this;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_Search_ArtikelBezHand _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Search_ArtikelBezHand> changeListener) {
		changeListeners._a(changeListener);
		return this;
	}
	
	
	
	private class ActionOnChangeListeners extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_Search_ArtikelBezHand> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_Search_ArtikelBezHand.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}
	
	
}
