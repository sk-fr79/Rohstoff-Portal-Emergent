/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD
 * @author manfred
 * @date 07.10.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import panter.gmbh.Echo2.Container.E2_BasicContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_InfoButton_NG;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 07.10.2016
 *
 */
public class BT_info_ermittle_kosten extends E2_InfoButton_NG {
	@Override
	public E2_BasicContainer generate_popup_container() throws myException {
		return new ownContainer();
	}

	@Override
	public BT_info_ermittle_kosten fill_popup_container_and_show(E2_BasicContainer own_container, E2_Grid title_grid,	E2_Grid info_grid) throws myException {
		E2_Grid  g_aussen = new E2_Grid()	._setSize(450)
											._a(title_grid, new RB_gld()._left_top()._ins(4, 4, 4, 10))
											._a(info_grid, new RB_gld()._left_top()._ins(4, 4, 4, 4))
											._bc(new E2_ColorHelpBackground());

		title_grid	._setSize(150,150,150)
					._a(new RB_lab()._tr("Neuermittlung der Kosten für die auf Fuhren basierenden Atome")._b()._fsa(2), new RB_gld()._span(3)._ins(2, 2,2,2));
		
		info_grid	._setSize(150,150,150)
					._a(new RB_lab()._tr("Es werden die Kosten für alle Fuhrenbasierenden Atome "), new RB_gld()._span(3)._left_mid()._ins(2, 2, 2, 10))
					._a(new RB_lab()._tr("ermittelt."), new RB_gld()._span(3)._left_mid()._ins(2, 10, 2, 2))
					;
		own_container._setColor4PopupBackground(new E2_ColorHelpBackground())._add(g_aussen, 4, 4, 4, 4)._setTitleTr("Erklärung..")._doPopup(500, 350);
		
		return this;
	}
	
	private class ownContainer extends E2_BasicContainer {
		
	}

}
