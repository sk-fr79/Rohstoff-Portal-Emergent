package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.TempFilter.FilterVariante;
import panter.gmbh.Echo2.ListAndMask.List.TempFilter.IfFilterExtForListComponents;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;


/**
 * button mit auswahlpopup fuer filterselektion in der spalte
 * @author martin
 *
 */
public class BSK_BtFilterExtender extends E2_Button {

	private IfFilterExtForListComponents ownFilterCol;


	/**
	 * @throws myException 
	 * 
	 */
	public BSK_BtFilterExtender(IfFilterExtForListComponents ownFilterCol, E2_NavigationList p_naviList, BSKC__CONST.FILTERCOLUMN p_column) throws myException {
		super();
		this.ownFilterCol = ownFilterCol;
		this._image(E2_ResourceIcon.get_RI("filter.png"));
		this.add_oActionAgent(new ownActionAgentOpenWindow());
		this._ttt("Temporärer Filter für diese Spalte aktivieren");
	}
	
	

	private class ownActionAgentOpenWindow extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			BSK_BtFilterExtender oThis = BSK_BtFilterExtender.this;

			//die liste scannen und dann den popup oeffnen
			if (!((BSK_GridWithNumber)oThis.ownFilterCol).isFilterprepared()) {
				((BSK_GridWithNumber)oThis.ownFilterCol).buildAndFillCategories();
			}
			new ownContainer(oThis.ownFilterCol.getVarianten());
		}
	}
	

	
	
	
	private class ownContainer extends E2_BasicModuleContainer {

		/**
		 * @param filterVarianten
		 * @throws myException 
		 */
		public ownContainer(Vector<FilterVariante> filterVarianten) throws myException {
			super();
			
			BSK_BtFilterExtender oThis = BSK_BtFilterExtender.this;
			
			
			E2_Grid grid = new E2_Grid()._setSize(360,40);
			
			grid._a(new RB_lab()._tr("Gesamte Zeilen der Liste vor Filter")._fo_italic()._fo_s(-2), new RB_gld()._left_mid()._col(new E2_ColorBase())._ins(3,3,3,10))
				._a(new RB_lab()._t("("+oThis.ownFilterCol.getvCopyOfVector4Segmentation().size()+")")._fo_italic()._fo_s(-2), new RB_gld()._right_mid()._col(new E2_ColorBase())._ins(3,3,3,10));
			
			grid._a(new RB_lab()._tr("Variante zur Auswahl")._fo_italic()._fo_s(-2), new RB_gld()._left_mid()._col(new E2_ColorDDark())._ins(3))
				._a(new RB_lab()._tr("Anteil")._fo_italic()._fo_s(-2), new RB_gld()._right_mid()._col(new E2_ColorDDark())._ins(3));
			
			for (FilterVariante f: filterVarianten) {
				grid._a(f.getCB(),new RB_gld()._left_mid()._ins(3))._a("("+f.getvIdMembers().size()+")",new RB_gld()._right_mid()._ins(3));
			}
			grid._a(new E2_Grid()._setSize(200,200)._a(new btStartSelection(this), new RB_gld()._ins(3, 10, 3, 3)._left_mid()).
													_a(new btClearSelection(this), new RB_gld()._ins(3, 10, 3, 3)._right_mid()), new RB_gld()._span(2));
			grid._a(new btClearAllFiltersInList(this), new RB_gld()._span(2)._ins(3, 10, 3, 3)._left_mid());
			
			this.add(grid,  E2_INSETS.I(5, 10, 5, 5));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Was soll angezeigt werden ?"));
		}
		
		
		
	}
	
	/*
	 * button, fuehrt die selektion aus ...
	 */
	private class btStartSelection extends E2_Button {
		private ownContainer container = null;
		
		public btStartSelection(ownContainer p_container) {
			super();
			this.container = p_container;
			this._image(E2_ResourceIcon.get_RI("ok.png"));
			this._tr("Filter anwenden")._s_BorderText();
			
			this._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					IfFilterExtForListComponents ownFilter = BSK_BtFilterExtender.this.ownFilterCol;
					
					for (FilterVariante filter: ownFilter.getVarianten()) {
						filter.setAktiv(filter.getCB().isSelected());
					}
					
					ownFilter.activateFilters();
					btStartSelection.this.container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}

	
	/*
	 * button, fuehrt die selektion aus ...
	 */
	private class btClearSelection extends E2_Button {
		private ownContainer container = null;
		
		public btClearSelection(ownContainer p_container) {
			super();
			this.container = p_container;
			this._image(E2_ResourceIcon.get_RI("clear.png"));
			this._tr("Filter entfernen")._s_BorderText();
			
			this._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					IfFilterExtForListComponents ownFilter = BSK_BtFilterExtender.this.ownFilterCol;
					ownFilter.clearFilter(false);
					ownFilter.activateFilters();
					btClearSelection.this.container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}

	
	/*
	 * button, fuehrt die selektion aus ...
	 */
	private class btClearAllFiltersInList extends E2_Button {
		private ownContainer container = null;
		
		public btClearAllFiltersInList(ownContainer p_container) {
			super();
			this.container = p_container;
			this._image(E2_ResourceIcon.get_RI("clear.png"));
			this._tr("Alle Listenfilter entfernen")._s_BorderText();
			
			this._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					IfFilterExtForListComponents ownFilter = BSK_BtFilterExtender.this.ownFilterCol;
					ownFilter.clearAllFilters(false);
					ownFilter.activateFilters();
					btClearAllFiltersInList.this.container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
	}

	
	
}
