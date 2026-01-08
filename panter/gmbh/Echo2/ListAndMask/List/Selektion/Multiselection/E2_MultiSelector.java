package panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_EXT;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_MultiSelector extends XX_ListSelektor_EXT {

	private Vector<XX_ListSelector_4Multiselect> 	    v_vectorOfSelectors 					= 	new Vector<XX_ListSelector_4Multiselect>();

	private E2_Grid 									grid_4_anzeige							= 	new E2_Grid();

	private ownButtonOpenMultiSelectPopup 				oButtonOpenMultiSelectPopup 			= 	new ownButtonOpenMultiSelectPopup();

	private XX_ActionAgent								oAgent_4_selection						= 	null;

	public abstract String 								fill_tool_tip() throws myException;

	public abstract E2_BasicModuleContainer  			get_PopupContainer()  throws myException;     

	/**
	 * erlaubt, die groesse und spaltenbreiten der anzeige im basisselector zu aendern
	 * @param grid_with_selector_part
	 * @throws myException 
	 */
	public abstract void                                format_selectorGridInBase(E2_Grid grid_with_selector_part) throws myException;

	/**
	 * erlaubt, die groesse und spaltenbreiten der anzeige im popup zu aendern
	 * @param complete_popup_grid_all_selectors_and_buttons
	 */
	public abstract void                                format_gridInPopup(E2_Grid complete_popup_grid_all_selectors_and_buttons);	

	/**
	 * 
	 * @param source can be null, if not null, source can be used for build
	 * @return
	 * @throws myException
	 */
	public abstract XX_ListSelector_4Multiselect      generate_selector_part(XX_ListSelector_4Multiselect source) throws myException;


	public E2_MultiSelector() throws myException{
		super();

		this.v_vectorOfSelectors.add(this.generate_selector_part(null));
		
		this.render_selector(false);

	}


	public Vector<XX_ListSelector_4Multiselect> get_vectorOfSelectors() {
		return v_vectorOfSelectors;
	}


	@Override
	public String get_WhereBlock() throws myException {

		Vector<String> v_where= new Vector<>();

		for (XX_ListSelector_4Multiselect selector :  v_vectorOfSelectors) {
			if (S.isFull(selector.get_WhereBlock())) {
				v_where.add("("+selector.get_WhereBlock()+")");
			}
		}

		String where = "";
		if (v_where.size()>0) {
			where = bibALL.Concatenate(v_where, " OR ", "");
		}

		return where;
	}


	@Override
	public Component get_oComponentForSelection() throws myException {
		return grid_4_anzeige;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return grid_4_anzeige;
	}



	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {

		//bei einordnen des selektors in den selectioncomponent-Vector ist ist der status des multiselectors immer 1 teilselektor
		this.oAgent_4_selection = oAgent;

		this.v_vectorOfSelectors.get(0).add_ActionAgentToComponent(oAgent_4_selection);
			
	}

	@Override
	public void doInternalCheck() {}

	public ownButtonOpenMultiSelectPopup get_button_4_multiSelect() {
		return oButtonOpenMultiSelectPopup;
	}

	public E2_Grid get_grid_4_anzeige() {
		return grid_4_anzeige;
	}


	/**
	 * baut repräsentation des selectors auf der oberflaeche
	 * @throws myException
	 */
	public void render_selector(boolean status_is_popup) throws myException {
		this.grid_4_anzeige._clear();

		if (!status_is_popup) {

			if (this.v_vectorOfSelectors.size()==1) {

				this.grid_4_anzeige._a(this.v_vectorOfSelectors.get(0).render_representation_4_base_selector(this.v_vectorOfSelectors))._a(this.get_button_4_multiSelect(), new RB_gld()._al(E2_ALIGN.CENTER_MID));

			} else if (this.v_vectorOfSelectors.size()>1) {

				this.grid_4_anzeige._a(this.v_vectorOfSelectors.get(0).render_representation_placeholder(this.v_vectorOfSelectors))._a(this.get_button_4_multiSelect(), new RB_gld()._al(E2_ALIGN.CENTER_MID));

			} else {

				throw new myException(this,"Error: IT MUST BE ONE SELECTOR..");

			}
		} else {

			this.grid_4_anzeige._a(this.v_vectorOfSelectors.get(0).render_representation_placeholder(this.v_vectorOfSelectors))._a(this.get_button_4_multiSelect(), new RB_gld()._al(E2_ALIGN.CENTER_MID));
		}

		this.format_selectorGridInBase(this.grid_4_anzeige);
	}


	public class ownButtonOpenMultiSelectPopup extends E2_Button
	{
		private int iAnzahlZusatzSelektionen = 0;


		public ownButtonOpenMultiSelectPopup() throws myException
		{
			super();

			this._t("0")._fo(new E2_FontBold())._width(new Extent(20));

			this._aaa(new E2_MultiSelector_ActionAgent_4_Popup(E2_MultiSelector.this));

			this.set_StatusHasMultiSelect(0);

		}

		public void set_StatusHasMultiSelect(int isize) throws myException   //anzahlZusatzWerte kann nur null oder >=2 werden
		{
			if(isize > 0){
				this.iAnzahlZusatzSelektionen = isize;

				if (this.iAnzahlZusatzSelektionen==0)
				{
					this.setForeground(Color.BLACK);
					this.setBackground(new E2_ColorDark());
				}
				else
				{
					this.setForeground(Color.BLACK);
					this.setBackground(new E2_ColorHelpBackground());
				}

				this.setText(""+this.iAnzahlZusatzSelektionen+"");
				this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));

				String cToolTipText = new MyE2_String("Die Auswahl auf weitere Bereiche erweitern").CTrans();

				if (iAnzahlZusatzSelektionen>=2)   //dann die selektionswahl im tooltip anzeigen
				{
					cToolTipText = new MyE2_String("Erweiterte Auswahl:").CTrans() +" \n\n" + fill_tool_tip();

				}

				this.setToolTipText(cToolTipText);

			}else{

				this.iAnzahlZusatzSelektionen = 0;
				this.setForeground(Color.BLACK);
				this.setBackground(new E2_ColorDark());
				this.setText(""+this.iAnzahlZusatzSelektionen+"");
				this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				
				this._ttt("Die Auswahl auf weitere Bereiche erweitern");
			}
		}

	}

	public void clear_selector() throws myException{
		this.get_grid_4_anzeige()._clear();
		
		this.v_vectorOfSelectors.removeAllElements();
		this.v_vectorOfSelectors.add(this.generate_selector_part(null));
		this.v_vectorOfSelectors.get(0).add_ActionAgentToComponent(oAgent_4_selection);
		
		this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
		
		this.render_selector(false);
	}
	
	public XX_ActionAgent get_agent_4_selection() throws myException{
		return this.oAgent_4_selection;
	}
}
