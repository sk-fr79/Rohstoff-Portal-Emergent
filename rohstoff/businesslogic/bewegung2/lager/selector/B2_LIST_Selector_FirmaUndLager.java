
package rohstoff.businesslogic.bewegung2.lager.selector;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.IF_Executer;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.list.listSelector.IF_CanBePopulated_NG;

public class B2_LIST_Selector_FirmaUndLager extends  XX_ListSelektor implements IF_CanBeDeactivate{

	private IF_CanBePopulated_NG angeschlossene = null;
	
	private RB_lab lbl_firma = new RB_lab()._t("Firma:");

	private RB_lab lbl_lager = new RB_lab()._t("Lager:");

	private B2_LIST_Selector_Firma_NG firmaSelektor;

	private B2_LIST_Selector_Lager_NG lagerSelektor;

	private E2_Grid grid = new E2_Grid();

	private XX_ActionAgent oAgent4Select;

	public B2_LIST_Selector_FirmaUndLager(String firmaBedingung, String lagerBedingung) throws myException {
		super();

		this.lagerSelektor 	= new B2_LIST_Selector_Lager_NG(lagerBedingung,470);

		this.angeschlossene = (IF_CanBePopulated_NG) lagerSelektor;

		this.firmaSelektor 	= new B2_LIST_Selector_Firma_NG(this.angeschlossene, firmaBedingung);
		
		this.grid._clear()._setSize(100,500)._bo_no()
		._a(lbl_firma, 										new RB_gld()._left_top())
		._a(this.firmaSelektor.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_top())
		._a(lbl_lager, 										new RB_gld()._left_top())
		._a(this.lagerSelektor.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_top())
		;
	}

	public B2_LIST_Selector_FirmaUndLager _setFirmaLabelText(String cText) throws myException{
		lbl_firma._t(cText);
		return this;
	}

	public B2_LIST_Selector_FirmaUndLager _setLagerLabelText(String cText) throws myException{
		lbl_lager._t(cText);
		return this;
	}

	public String getSelectedFirmaId() throws myException
	{
		return firmaSelektor.get_hauptadresse_id();
	}
	
	public void resetSearch() throws myException{
		firmaSelektor.loadAngleschlosseneSelector();
	}


	public E2_Grid getSelektorGrid() {
		return this.grid;
	}

	@Override
	public String get_WhereBlock() throws myException {	
		String whereBlock = "";
		if(lagerSelektor.get_WhereBlock().equals("(1=1)")) {
			whereBlock= firmaSelektor.get_WhereBlock();
		}else {
			whereBlock =lagerSelektor.get_WhereBlock();
		}
		return whereBlock;
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return getSelektorGrid();
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return getSelektorGrid();
	}

	public void addExecuterAfterFirmaSearch(IF_Executer oExecuter) throws myException {
			this.firmaSelektor.getSearchField()._registerAfterValueChangeEvents(oExecuter);
			this.firmaSelektor.getSearchField()._registerAfterFieldEraseEvents(oExecuter);
	}
	
	public void addExecuterAfterHomeJump(IF_Executer oExecuter) throws myException {
		this.firmaSelektor.getHomeLoadButton()._aaa(()->oExecuter.execute());
	}
	
	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.oAgent4Select=oAgent;	
		
		if(oAgent4Select != null) {
			this.firmaSelektor.add_ActionAgentToComponent(oAgent4Select);
			this.lagerSelektor.add_ActionAgentToComponent(oAgent4Select);
		}
	}

	@Override
	public void doInternalCheck() {}

	
	public void setEnabled(boolean isEnabled) throws myException{
		((B2_LIST_Selector_Firma_NG)this.firmaSelektor).setEnabled(isEnabled);
		((B2_LIST_Selector_Lager_NG)this.lagerSelektor).setEnabled(isEnabled);
	}

	public B2_LIST_Selector_FirmaUndLager setDefaultValue(String default_id_adresse) throws myException{
		this.firmaSelektor.getSearchField().rb_set_db_value_manual(default_id_adresse);
		
		this.lagerSelektor.populate(new VEK<String>()._a(default_id_adresse));
		return this;
	}
	
}

