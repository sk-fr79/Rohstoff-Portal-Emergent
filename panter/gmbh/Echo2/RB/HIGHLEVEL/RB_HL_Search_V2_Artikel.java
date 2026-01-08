package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.IfHandlerStartInsteadOfSearch;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;

public class RB_HL_Search_V2_Artikel extends RB_SearchFieldV2 {

	
	public RB_HL_Search_V2_Artikel() throws myException {
		super();
		
		
		this._setSaveSortSettingsKey(this.getClass().getCanonicalName());
		
		this._setOwnShape();
		
		this._setTable(_TAB.artikel);
		
		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Suchbutton bei leerer Eingabe gedrückt, dann wird eine Vorauswahl aus Sorgengruppen angezeigt"));
		
		this._setHandlerStartInsteadOfSearch(new PopupPreSelectArtikel());
		
		
		// basis-selektion
		this.getAndStatementCollectorForBasic().and(new vgl_YN(ARTIKEL.aktiv, true));

		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.anr1), COMP.EQ, new TermSimple("'#WERT#'")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.artbez1),COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.artbez2),COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(
				new FieldTerm(ATTRIBUTES.TO_CHAR, ARTIKEL.id_artikel), COMP.EQ, new TermSimple("'#WERT#'")));

		this.set_iMaxResults(5000);
		
		this.getSortButtonTextsHeaders()._a("ANR1")._a("Artikelbezeichung")._a("Id");
		
	}



	@Override
	public void renderResultOnMask(E2_Grid resultContainer, Long id) throws myException {
		resultContainer._clear()._setSize(65,330)._bo_dd();
		if (id!=null) {
			Rec21_artikel recArt = new Rec21_artikel()._fill_id(id);
			resultContainer._a(new RB_lab(recArt.getUfs(ARTIKEL.anr1))._i()._fsa(-1), new RB_gld()._left_top()._ins(2,0,10,0))
							._a(new RB_lab(recArt.getUfs(ARTIKEL.artbez1))._i()._fsa(-1), new RB_gld()._left_top()._ins(2,0,0,0));
		}
	}

	@Override
	public E2_BasicModuleContainer generatePopupContainer() throws myException {
		return new OwnPopupContainer();
	}

	private class OwnPopupContainer extends E2_BasicModuleContainer {

		public OwnPopupContainer() {
			super();
			this.set_oExtWidth(new Extent(400));
			this.set_oExtHeight(new Extent(700));
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
				ResultButton[] b = new   ResultButton[3];
				String sort_id = ("00000000000000000000000000000000").substring(r.getIdLong().toString().length())
						+ r.getIdLong().toString();
				
				b[0]= (ResultButton)new ResultButton(r.getUfs(ARTIKEL.anr1), r, this)._t(r.getUfs(ARTIKEL.anr1))._style(E2_Button.baseStyle());
				b[1]= (ResultButton)new ResultButton(r.getUfs(ARTIKEL.artbez1), r, this)._t(r.getUfs(ARTIKEL.artbez1))._style(E2_Button.baseStyle());
				b[2]= (ResultButton)new ResultButton(sort_id, r, this)._t(r.getFs(ARTIKEL.id_artikel))._style(E2_Button.baseStyle()
																									._setProp(AbstractButton.PROPERTY_ALIGNMENT, Alignment.ALIGN_RIGHT, Alignment.ALIGN_RIGHT));
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
		return new SEL(_TAB.artikel).FROM(_TAB.artikel);
	}

	
	
	
	private class PopupPreSelectArtikel implements IfHandlerStartInsteadOfSearch {

		@Override
		public void executeInsteadOfSearch(RB_SearchFieldV2 searchfield, And andStatementCollectorHandlerStartInsteadOfSearch) throws myException {

			SEL selHptArtikel = new SEL("SUBSTR("+ARTIKEL.anr1.fn()+",1,2)").ADD_Distinct().FROM(_TAB.artikel).ORDER("1");
			VEK<Object[]> results = bibDB.getResultLines(new SqlStringExtended(selHptArtikel.s()), true);
			
			SEL selArtikelGruppen = new SEL(_TAB.artikel_gruppe).FROM(_TAB.artikel_gruppe).ADD_Distinct().
							INNERJOIN(_TAB.artikel, ARTIKEL.id_artikel_gruppe, ARTIKEL_GRUPPE.id_artikel_gruppe).ORDERUP(ARTIKEL_GRUPPE.gruppe);
			
			RecList21 reclistArtikelGruppe = new RecList21(_TAB.artikel_gruppe)._fill(new SqlStringExtended(selArtikelGruppen.s())); 
			
			E2_Grid gContainer = new E2_Grid()._setSize(100,330);
			
			
			E2_Grid gridHauptArtikel = new E2_Grid()._setSize(35,35,35,35,35,35,35,35);
			E2_Grid gridArtikelGruppe = new E2_Grid()._setSize(100);
			
			
			andStatementCollectorHandlerStartInsteadOfSearch.clear();
			
			PreselectContainer popup = new PreselectContainer();

			for (Object[] o: results) {
				gridHauptArtikel._a(new E2_Button()._t((String)o[0])._style(E2_Button.baseStyle())._setAddOnObject(o[0])
						._aaa((source)->{
							popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							String anr1 = (String)((E2_Button)source).getAddOnObject();
							
							RB_HL_Search_V2_Artikel oThis = RB_HL_Search_V2_Artikel.this;
							
							andStatementCollectorHandlerStartInsteadOfSearch.and(new TermSimple(ARTIKEL.anr1.fn()+" LIKE '"+anr1+"%'"));
							
							oThis.getButtonStartSearch().doAction();
							
						})
					, new RB_gld()._ins(2, 2, 2, 2));
			}
			
			for (Rec21 rArtGrp: reclistArtikelGruppe) {
				gridArtikelGruppe._a(
						new E2_Button()	._t(S.NN(rArtGrp.getFs(ARTIKEL_GRUPPE.gruppe),"???"))
										._style(E2_Button.baseStyle())
										._setAddOnObject(rArtGrp)
										._aaa((source)-> {
											popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
											Long artGrp = ((Rec21) ((E2_Button)source).getAddOnObject()).getActualID();
											
											RB_HL_Search_V2_Artikel oThis = RB_HL_Search_V2_Artikel.this;
											
											andStatementCollectorHandlerStartInsteadOfSearch.and(new vgl(ARTIKEL.id_artikel_gruppe,artGrp.toString()));
							
											oThis.getButtonStartSearch().doAction();

										})
						
										, new RB_gld()._ins(2, 2, 2, 2)
										);
			}
			
			
			gContainer._a(new RB_lab()._tr("Artikelgruppe"))._a(new RB_lab()._tr("Hauptsorte-Nr"));
			gContainer._a(gridArtikelGruppe)._a(gridHauptArtikel);
			
			popup.add(gContainer, E2_INSETS.I(5));
			popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(450),new Extent(320),S.ms("Sorten-Vorauswahl ..."));
		}
		
		private class PreselectContainer extends E2_BasicModuleContainer {
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.IfHandlerStartInsteadOfSearch#checkIfNeeded(panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2)
		 */
		@Override
		public boolean checkIfNeeded(RB_SearchFieldV2 searchfield) throws myException {
			return S.isEmpty(searchfield.getTextFieldSearchInput().getText());
		}
	}

	
	public RB_SearchFieldV2 _setOwnShape() {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(100,20,20,400);

		return this;
	}

}
