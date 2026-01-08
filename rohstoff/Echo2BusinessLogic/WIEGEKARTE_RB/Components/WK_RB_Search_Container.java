package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue.TYPE_OF_INPUT;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;
import panter.gmbh.indep.pdf.myPDF.BenannteTabelle;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ContainerHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_container;

public class WK_RB_Search_Container extends RB_SearchFieldV2 {

	// Tara Editieren
	private cBtEditTara  		_btEditTara 	= new cBtEditTara();
	
	private RB_TransportHashMap _tpHashMap 		= null;
	
	public WK_RB_Search_Container(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		this._tpHashMap = p_tpHashMap;
		
		this._setSaveSortSettingsKey(this.getClass().getCanonicalName());
		
		this._setOwnShape();
		
		this._setTable(_TAB.container);
		
		// nach dem löschen und ändern der ID muss auch der Status des EditContainer-Button gesetzt werden
		this._registerAfterFieldEraseEvents(()->{
			_setStatusButtonEditContainer();
		});
		
		this._registerAfterValueChangeEvents(()->{
			_setStatusButtonEditContainer();
		});
		
		
		
//		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Suchbutton bei leerer Eingabe gedrückt, dann wird eine Vorauswahl aus Sorgengruppen angezeigt"));
		
//		this._setHandlerStartInsteadOfSearch(new PopupPreSelectArtikel());
		
		
		// basis-selektion
//		this.getAndStatementCollectorForBasic().and(new vgl_YN(ARTIKEL.aktiv, true));

		// Suchparameter 
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, CONTAINER.container_nr), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
//		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD, CONTAINER.uvv_datum), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
//		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, CONTAINER.bemerkung), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
//		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, CONTAINERTYP.kurzbezeichnung),COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
//		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, CONTAINERTYP.beschreibung),COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
//		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.TO_CHAR, CONTAINER.id_container), COMP.EQ, new TermSimple("'#WERT#'")));
		
		this.set_iMaxResults(200);
		
		this.getSortButtonTextsHeaders()._a("ContainerNR")._a("UVV-Datum")._a("Bemerkung")._a("Containtertyp")._a("Typ-Beschreibung")._a("Id");
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException {
		super.set_bEnabled_For_Edit(Enabled);
		_setStatusButtonEditContainer();
		
		if (S.isFull(this._get_IDContainer())) {
			_btEditTara.set_bEnabled_For_Edit(Enabled);
		} else {
			_btEditTara.set_bEnabled_For_Edit(false);
		}
	}


	@Override
	public void renderResultOnMask(E2_Grid resultContainer, Long id) throws myException {
		resultContainer._clear()._setSize(150,120,130)._bo_ddd();

		if (id!=null) {
			Rec_container rec = new Rec_container()._fill_id(id);
			
			MyBigDecimal bdTaraKorr	=	rec.get_myBigDecimal_dbVal(CONTAINER.tara_korrektur	, new MyBigDecimal(BigDecimal.ZERO));
			MyBigDecimal bdTara 	=	rec.get_myBigDecimal_dbVal(CONTAINER.tara			, new MyBigDecimal(BigDecimal.ZERO));
			
			MyBigDecimal bdValue 	=	bdTaraKorr.get_bdWert().equals(BigDecimal.ZERO) ? bdTara : bdTaraKorr; 
			
			
			String sTara = String.format(new MyE2_String("Tara: %s Kg").CTrans(), bdValue.get_FormatedRoundedNumber(0));
			String sUVV = String.format(new MyE2_String("UVV: %s").CTrans(), rec.getFs(CONTAINER.uvv_datum,"-"));
			
			MyDate dtUVV = rec.get_myDate_dbVal(CONTAINER.uvv_datum);
			Color col = new E2_ColorBase();
			if (dtUVV == null) {
				col = Color.RED;
			} else {
				if (dtUVV.isOK()) {
					Long diff = MyDate.get_DayDifference_Date2_MINUS_Date1(dtUVV,new MyDate(new Date()));
					
					if (diff <= 30 && diff > 0){
						col = Color.ORANGE;
					} else if (diff <= 0){
						col = Color.RED;
					}
				}
			}
			
			resultContainer	
							._a(new RB_lab(rec.getUfs(CONTAINER.container_nr))._ttt(rec.getUfs(CONTAINER.bemerkung))._fsa(0), new RB_gld()._left_mid()._ins(2,0,10,0))
							._a(new RB_lab(sUVV)._fsa(0) , new RB_gld()._left_mid()._ins(2,0,0,0)._col_back(col))
							._a(new RB_lab(sTara)._fsa(0), new RB_gld()._right_mid()._ins(2,0,0,0));
 		} else {
			// leere Ergebniszeile
			TextField emptyField = new TextField();
			emptyField.setEnabled(false);
			
			MutableStyle oStyle = new MutableStyle();
			oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase(0));
			oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(0, new E2_ColorDDark(), Border.STYLE_NONE)); 
			emptyField.setStyle(oStyle);

			resultContainer ._setSize(400)._bo_ddd();
			resultContainer	._a(emptyField, new RB_gld()._left_mid()._ins(0))
			;
			
		} 
		
		// Status des Edit-Container-Buttons setzen:
//		_setStatusButtonEditContainer();
		
		
	}
	
	
	/**
	 * prüft, ob der Suchbutton enabled ist und ob ein Wert im Feld ist, dann wird Suchbutton gesetzt
	 * @author manfred
	 * @date 21.01.2021
	 *
	 */
	private void _setStatusButtonEditContainer() {
		try {
			if (S.isFull(this._get_IDContainer())) {
					_btEditTara.set_bEnabled_For_Edit(this.getButtonStartSearch().isEnabled());
			} else {
				_btEditTara.set_bEnabled_For_Edit(false);
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * gibt die der Auswahl zurück
	 * @author manfred
	 * @date 10.08.2020
	 *
	 * @return
	 * @throws myException
	 */
	public String _get_IDContainer () throws myException {
		return this.rb_readValue_4_dataobject();
	}
	
	

	@Override
	public E2_BasicModuleContainer generatePopupContainer() throws myException {
		return new OwnPopupContainer();
	}

	private class OwnPopupContainer extends E2_BasicModuleContainer {

		public OwnPopupContainer() {
			super();
			this.set_oExtWidth(new Extent(800));
			this.set_oExtHeight(new Extent(900));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#createResultButtons(panter.gmbh.indep.dataTools.RECORD2.RecList21, panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2)
	 */
	@Override
	public ResultButtons createResultButtons(RecList21 rlSearchResult, RB_SearchFieldV2 searchField) {
		ResultButtons results = new ResultButtons(this.getSaveSortSettingsKey());
		
		try {
			for (Rec21 r: rlSearchResult) {
				ResultButton[] b = new   ResultButton[6];
				
				String sort_id = StringUtils.leftPad(r.getIdLong().toString(), 20, '0');
				int i = 0;
				
				b[i++]= (ResultButton)new ResultButton(r.getUfs(CONTAINER.container_nr), r, this)._t(r.getUfs(CONTAINER.container_nr))._style(E2_Button.baseStyle());
				b[i++]= (ResultButton)new ResultButton(r.getFs(CONTAINER.uvv_datum), r, this)._t(r.getFs(CONTAINER.uvv_datum))._style(E2_Button.baseStyle());
				b[i++]= (ResultButton)new ResultButton(r.getUfs(CONTAINER.bemerkung), r, this)._t(r.getUfs(CONTAINER.bemerkung))._style(E2_Button.baseStyle());
				b[i++]= (ResultButton)new ResultButton(r.getOverHeadValue(CONTAINERTYP.kurzbezeichnung.fn()), r, this)._t(r.getOverHeadValue(CONTAINERTYP.kurzbezeichnung.fn()))._style(E2_Button.baseStyle());
				b[i++]= (ResultButton)new ResultButton(r.getOverHeadValue(CONTAINERTYP.beschreibung.fn()), r, this)._t(r.getOverHeadValue(CONTAINERTYP.beschreibung.fn()))._style(E2_Button.baseStyle());
				b[i++]= (ResultButton)new ResultButton(sort_id, r, this)._t(r.getFs(CONTAINER.id_container))._style(E2_Button.baseStyle()	._setProp(AbstractButton.PROPERTY_ALIGNMENT, Alignment.ALIGN_RIGHT, Alignment.ALIGN_RIGHT));
				results._a(b);
				
			}
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._add(e.get_ErrorMessage());
			results.clear();
		}
		return results;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#generateSELWithoutWhere()
	 */
	@Override
	public SEL generateSELWithoutWhere() throws myException {
		return new SEL(_TAB.container)
					.ADDFIELD(CONTAINERTYP.beschreibung)
					.ADDFIELD(CONTAINERTYP.kurzbezeichnung)
					.ADDFIELD(CONTAINERTYP.containerinhalt)
					.FROM(_TAB.container)
					.INNERJOIN(_TAB.containertyp, CONTAINER.id_containertyp, CONTAINERTYP.id_containertyp)
					;
	}

	
	
	public WK_RB_Search_Container _setOwnShape() {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(this.getGridForResults(),  	new RB_gld()._ins(2,2,4,2)._left_mid())
			._a(getTextFieldSearchInput(),  new RB_gld()._ins(2,2,4,2)._left_mid())
			._a(getButtonErase(),  			new RB_gld()._ins(2,2,4,2)._left_mid())
			._a(getButtonStartSearch(),  	new RB_gld()._ins(2,2,4,2)._left_mid())
			._a(_btEditTara,  				new RB_gld()._ins(2,2,4,2)._left_mid())
			._setSize(400,100,20,20,20)

			;

		return this;
	}

	/**
	 * Neuen Tara-Korrekturwert schreiben und die Werte neu lesen
	 * @author manfred
	 * @date 21.01.2021
	 *
	 * @param bdNewValue
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public WK_RB_Search_Container _setNewTara(BigDecimal bdNewValue, MyE2_MessageVector mv) throws myException {
		
		if (this._get_IDContainer() == null || S.isEmpty(this._get_IDContainer())) {
			return this;
		}
		
		WK_RB_MC_ContainerHandling mcContainer = new WK_RB_MC_ContainerHandling(this);
		MyLong myIdContainer = new MyLong(this._get_IDContainer());

		mcContainer.setNewTaraValue(myIdContainer.get_cUF_LongString(), bdNewValue, mv);
		
		this.renderResultOnMask(this.getGridForResults(), myIdContainer.get_lValue());
		
		return this;
	}

	
	
	/** 
	 * Button-Klasse zum editieren vom Tara-Gewicht
	 * @author manfred
	 * @date 21.01.2021
	 *
	 */
	private class cBtEditTara extends E2_Button{
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		/**
		 * @author manfred
		 * @date 21.01.2021
		 *
		 */
		public cBtEditTara() {
			super();
			
			_image("edit.png", true);
			_ttt("Tara des Containers korrigieren");
			
			_aaa(()-> {
				new E2_MessageBoxGetValue(
						"Containergewicht korrigieren", 
						"OK", 
						"Abbrechen", 
						true, 
						true, 
						"Neues Containergewicht:",
						null, //new VectorE2String().ut("Hello World"), 
						null, 
						500, 
						200)._addChangeListener((o)->{
							// changelistener wird nur durch OK aufgerufen
							String value = o.getValue();
							if (value != null) {
								System.out.println(value) ;
								MyBigDecimal mybd = new MyBigDecimal(value);
								if (mybd.isOK()) {
									try {
										_setNewTara(mybd.get_bdWert(), mv);
									} catch (myException e) {
										e.printStackTrace();
									}
									
								} else {
									mv._addAlarm("Fehler beim erfassen des neuen Wertes!");
								}
							}
							return mv;
						})
				._setTypeOfInput(TYPE_OF_INPUT.NUMBER)
				._show();

			});
		}
		
		
		
	}
	
	
	
	
	
//	private class PopupPreSelectArtikel implements IfHandlerStartInsteadOfSearch {
//
//		@Override
//		public void executeInsteadOfSearch(RB_SearchFieldV2 searchfield, And andStatementCollectorHandlerStartInsteadOfSearch) throws myException {
//
//			SEL selHptArtikel = new SEL("SUBSTR("+ARTIKEL.anr1.fn()+",1,2)").ADD_Distinct().FROM(_TAB.artikel).ORDER("1");
//			VEK<Object[]> results = bibDB.getResultLines(new SqlStringExtended(selHptArtikel.s()), true);
//			
//			SEL selArtikelGruppen = new SEL(_TAB.artikel_gruppe).FROM(_TAB.artikel_gruppe).ADD_Distinct().
//							INNERJOIN(_TAB.artikel, ARTIKEL.id_artikel_gruppe, ARTIKEL_GRUPPE.id_artikel_gruppe).ORDERUP(ARTIKEL_GRUPPE.gruppe);
//			
//			RecList21 reclistArtikelGruppe = new RecList21(_TAB.artikel_gruppe)._fill(new SqlStringExtended(selArtikelGruppen.s())); 
//			
//			E2_Grid gContainer = new E2_Grid()._setSize(100,330);
//			
//			
//			E2_Grid gridHauptArtikel = new E2_Grid()._setSize(35,35,35,35,35,35,35,35);
//			E2_Grid gridArtikelGruppe = new E2_Grid()._setSize(100);
//			
//			
//			andStatementCollectorHandlerStartInsteadOfSearch.clear();
//			
//			PreselectContainer popup = new PreselectContainer();
//
//			for (Object[] o: results) {
//				gridHauptArtikel._a(new E2_Button()._t((String)o[0])._style(E2_Button.baseStyle())._setAddOnObject(o[0])
//						._aaa((source)->{
//							popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//							String anr1 = (String)((E2_Button)source).getAddOnObject();
//							
//							WK_RB_Search_V2_Container oThis = WK_RB_Search_V2_Container.this;
//							
//							andStatementCollectorHandlerStartInsteadOfSearch.and(new TermSimple(ARTIKEL.anr1.fn()+" LIKE '"+anr1+"%'"));
//							
//							oThis.getButtonStartSearch().doAction();
//							
//						})
//					, new RB_gld()._ins(2, 2, 2, 2));
//			}
//			
//			for (Rec22 rArtGrp: reclistArtikelGruppe) {
//				gridArtikelGruppe._a(
//						new E2_Button()	._t(S.NN(rArtGrp.getFs(ARTIKEL_GRUPPE.gruppe),"???"))
//										._style(E2_Button.baseStyle())
//										._setAddOnObject(rArtGrp)
//										._aaa((source)-> {
//											popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//											Long artGrp = ((Rec22) ((E2_Button)source).getAddOnObject()).getActualID();
//											
//											WK_RB_Search_V2_Container oThis = WK_RB_Search_V2_Container.this;
//											
//											andStatementCollectorHandlerStartInsteadOfSearch.and(new vgl(ARTIKEL.id_artikel_gruppe,artGrp.toString()));
//							
//											oThis.getButtonStartSearch().doAction();
//
//										})
//						
//										, new RB_gld()._ins(2, 2, 2, 2)
//										);
//			}
//			
//			
//			gContainer._a(new RB_lab()._tr("Artikelgruppe"))._a(new RB_lab()._tr("Hauptsorte-Nr"));
//			gContainer._a(gridArtikelGruppe)._a(gridHauptArtikel);
//			
//			popup.add(gContainer, E2_INSETS.I(5));
//			popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(450),new Extent(320),S.ms("Sorten-Vorauswahl ..."));
//		}
//		
//		private class PreselectContainer extends E2_BasicModuleContainer {
//		}
//
//		/* (non-Javadoc)
//		 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.IfHandlerStartInsteadOfSearch#checkIfNeeded(panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2)
//		 */
//		@Override
//		public boolean checkIfNeeded(RB_SearchFieldV2 searchfield) throws myException {
//			return S.isEmpty(searchfield.getTextFieldSearchInput().getText());
//		}
//	}




}
