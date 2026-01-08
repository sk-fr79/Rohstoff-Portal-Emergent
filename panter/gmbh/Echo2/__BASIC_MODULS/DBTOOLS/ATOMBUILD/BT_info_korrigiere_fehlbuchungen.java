package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import panter.gmbh.Echo2.Container.E2_BasicContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_InfoButton_NG;
import panter.gmbh.indep.exceptions.myException;

class BT_info_korrigiere_fehlbuchungen extends E2_InfoButton_NG {

	@Override
	public E2_BasicContainer generate_popup_container() throws myException {
		return new ownContainer();
	}

	@Override
	public E2_InfoButton_NG fill_popup_container_and_show(E2_BasicContainer own_container, E2_Grid title_grid,	E2_Grid info_grid) throws myException {
		E2_Grid  g_aussen = new E2_Grid()	._setSize(450)
											._a(title_grid, new RB_gld()._left_top()._ins(4, 4, 4, 10))
											._a(info_grid, new RB_gld()._left_top()._ins(4, 4, 4, 4))
											._bc(new E2_ColorHelpBackground());

		title_grid	._w100()._s(1)
					._a(new RB_lab()._tr("Fehlbuchungen aus der Vergangenheit korrigieren")._b()._fsa(2), new RB_gld()._ins(2, 2,2,2));
		
		info_grid	._w100()._s(1)
					._a(new RB_lab()._tr("Es werden alle Abzüge der Sorten, die in der Tabelle "
										+ " JT_KORRE_ABZUEGE hinterlegt sind (zusammen mit dem Datum, bis zu "
										+ " welchem Datum die Korrektur erfolgt) dahingehende korrigiert, daß Mengenabzüge, die die"), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
					._a(new RB_lab()._tr("Lagermenge reduzieren")._b(), new RB_gld()._center_mid()._ins(2, 10, 2, 10))
					._a(new RB_lab()._tr("so behandelt werden, als wären es"), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
					._a(new RB_lab()._tr("anhaftende Abzüge")._b(), new RB_gld()._center_mid()._ins(2, 10, 2, 10))
					._a(new RB_lab()._tr("Dabei ändern sich sowohl die auf dem Lager befindlich Menge, als auch der resultierende Einzelpreis auf die Nettomenge."), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
					
					;
		
		own_container._setColor4PopupBackground(new E2_ColorHelpBackground())._add(g_aussen, 4, 4, 4, 4)._setTitleTr("Erklärung..")._doPopup(500, 350);
			
		return this;
	}
	
	private class ownContainer extends E2_BasicContainer {
		
	}
	
}