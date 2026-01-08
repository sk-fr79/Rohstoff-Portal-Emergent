/**
 * @author sebastien
 * @date 06.12.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.lager.selector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Adresse;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.list.listSelector.IF_CanBePopulated_NG;


public class B2_LIST_Selector_Firma_NG extends XX_ListSelektor implements IF_CanBeDeactivate{

	private IF_CanBePopulated_NG 	angeschlossene 			= null;

	private RB_HL_Search_V2_Adresse cSearch					= null;

	private E2_Button homeButton = new E2_Button()._image("firma.png");

	private String whereBlock = "";
	
	private String idAdresse = "";
	
	public B2_LIST_Selector_Firma_NG(IF_CanBePopulated_NG pAngeschlosseneSelector, String firmaBedingung) throws myException {
		super();

		RB_gld gld_lt = new RB_gld()._ins(0,0,4,2)._left_top();

		this.angeschlossene = pAngeschlosseneSelector;

		this.cSearch = new ownAdresseSearchField();
		
		this.homeButton._aaa(()->fill_default_value_4_search());
		
		this.cSearch._bo_no()
		._clear()
		._a(cSearch.getGridForResults(), 		gld_lt._c()._ins(0,1,2,1))
		._a(cSearch.getTextFieldSearchInput(),	gld_lt._c()._ins(5,1,2,1))
		._a(cSearch.getButtonErase(), 			gld_lt._c()._ins(5,1,2,1))
		._a(cSearch.getButtonStartSearch(), 	gld_lt._c()._ins(5,1,2,1))
		._a(homeButton, 						gld_lt._c()._ins(5,1,2,1))	
		._setSize(310,100,25,25,25);
		
		whereBlock = firmaBedingung;

		this.cSearch._setFindOnlyMainAdresses(false);

		this.cSearch._registerAfterValueChangeEvents(()->loadAngleschlosseneSelector());
		this.cSearch._registerAfterFieldEraseEvents(()->loadAngleschlosseneSelector());
		this.cSearch._registerAfterValueChangeEvents(()->updateId(false));
		this.cSearch._registerAfterFieldEraseEvents(()->updateId(true));

	}

	private void fill_default_value_4_search() throws myException {
		this.cSearch.rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());
		loadAngleschlosseneSelector();

	}

	private void updateId(boolean isErase){
		try {
				this.idAdresse = "";
			if(! isErase){
				this.idAdresse =bibALL.convertID2UnformattedID(this.cSearch.get__actual_maskstring_in_view());
			}
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	public void loadAngleschlosseneSelector() {
		try {
			
			VEK<String> vRueck = new VEK<String>();
			if(S.isFull(this.cSearch.get__actual_maskstring_in_view())){
				vRueck._a(bibALL.convertID2UnformattedID(this.cSearch.get__actual_maskstring_in_view()));
			}
			
			if(this.angeschlossene !=null) {
				this.angeschlossene.populate(vRueck);
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	public void setEnabled(boolean bEnabled) throws myException{
		this.cSearch.set_bEnabled_For_Edit(bEnabled);
	}
	
	public String get_hauptadresse_id () throws myException{
		return this.idAdresse;
	}
	
	@Override
	public String get_WhereBlock() throws myException {
		String uf_firma_id = bibALL.convertID2UnformattedID(this.cSearch.get__actual_maskstring_in_view() );
		if(S.isFull(whereBlock) && S.isFull(uf_firma_id)) {
			String cWhere = this.whereBlock;
			return cWhere.replaceAll("#WERT#", uf_firma_id);
		}
		return "1=1";
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.cSearch;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.cSearch;
	}

	public RB_HL_Search_V2_Adresse getSearchField() throws myException {
		return this.cSearch;
	}
	
	public E2_Button getHomeLoadButton() throws myException{
		return this.homeButton;
	}
	
	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.cSearch.getButtonStartSearch()	._aaa(oAgent);
		this.cSearch.getButtonErase()		._aaa(oAgent);
		this.homeButton						._aaa(oAgent);
	}

	@Override
	public void doInternalCheck() {}

	private class ownAdresseSearchField extends RB_HL_Search_V2_Adresse{

		public ownAdresseSearchField() throws myException {
			super();
		}

		@Override
		public void renderResultOnMask(E2_Grid gridForResults, Long id) throws myException {
			if(id != null && id>=1000) {
				RB_lab result_label = new RB_lab()._fsa(-1)._lw();

				Rec21_adresse recAdr = new Rec21_adresse()._fill_id(id);
				
				String firma_name_ort = recAdr.get__Name_flexible(" ") + " - " + recAdr.getUfs(ADRESSE.ort,"") ;

				if( (""+id).equals(bibALL.get_ID_ADRESS_MANDANT())) {
					firma_name_ort = "(*) " + firma_name_ort;
					result_label._b();
				}

				if( recAdr.is_no_db_val(ADRESSE.aktiv) ) {
					result_label._col_fore_lgrey();
				}

				gridForResults._bo_no()._w100()._clear()._a(result_label._t(firma_name_ort), new RB_gld()._ins(1)._left_mid());
			}
		}

	}
}
