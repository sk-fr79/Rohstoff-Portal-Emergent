/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class KV_PosNavigationList extends E2_NavigationList {

	/**
	 * @throws myException
	 */
	public KV_PosNavigationList() throws myException {
		super();
	}

	
	// 20171122: rowNavigationButtons aufbauen, kann ueberschrieben werden
	@Override
	public void buildRow4Navigation(	E2_ComponentGroupHorizontal _rowNavigationButtons
    									, MyE2_Button _buttStart
    									, MyE2_Button _buttBack
    									, MyE2_TextField _oTextPageNumber
    									, MyE2_Button _buttGoPos
    									, MyE2_Button _buttForeward
    									, MyE2_Button _buttEnd
    									, MyE2_Button _buttReload
    									, E2_Button   _buttRestore
    									, MyE2_Label  _labelPosInfo
    									, MyE2_Label  _labelZeilenInfo
    									, MyE2_SelectField selZeilen
    									) {
		
		_rowNavigationButtons.add(_buttStart, E2_INSETS.I(2, 1, 0, 1));
		_rowNavigationButtons.add(_buttBack, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_oTextPageNumber, E2_INSETS.I(5, 1, 0, 1));
		_rowNavigationButtons.add(_buttGoPos, E2_INSETS.I(0, 1, 5, 1));
		_rowNavigationButtons.add(_buttForeward, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_buttEnd, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_buttReload, E2_INSETS.I(10, 1, 0, 1));
		_rowNavigationButtons.add(_buttRestore, E2_INSETS.I(10, 1, 0, 1));    //20180208: restore-button
		// rowNavigationButtons.add(this.buttToggleReducedView);
		_rowNavigationButtons.add(new MyE2_Label("Seite: "), E2_INSETS.I(10, 1, 3, 1));
		_rowNavigationButtons.add(_labelPosInfo, E2_INSETS.I(10, 1, 0, 1));

		_rowNavigationButtons.add(new MyE2_Label("Anz. Positionen:"), E2_INSETS.I(25, 1, 3, 1));
		_rowNavigationButtons.add(_labelZeilenInfo, E2_INSETS.I(5, 1, 3, 1));

		/*
		 * das selektionsfeld fuer die Seitenlaenge
		 */
		_rowNavigationButtons.add(new MyE2_Label("Seitenlänge: "), E2_INSETS.I(140, 1, 0, 1));
		_rowNavigationButtons.add(selZeilen, E2_INSETS.I(5, 1, 0, 1));
		
		RowLayoutData oRowBig = new RowLayoutData();
		oRowBig.setWidth(new Extent(10));
		_labelPosInfo.setLayoutData(oRowBig);
		_labelZeilenInfo.setLayoutData(oRowBig);
    }
	
	
}
