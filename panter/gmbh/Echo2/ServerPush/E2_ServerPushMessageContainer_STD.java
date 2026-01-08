package panter.gmbh.Echo2.ServerPush;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.MyE2_Grid;

public abstract class E2_ServerPushMessageContainer_STD extends E2_ServerPushMessageContainer 
{

	private E2_FortsschrittsBalken  oBalken = null;
	private MyE2_Grid 				oGrid4AddonInfos = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	



	/**
	 * Variante 1 mit beliebiger komponentenuebergabe zur fortschrittsanzeige
	 * @param oWidth
	 * @param oHeight
	 * @param oTitle
	 * @param AutoCloseAfterLoop
	 * @param ShowCancelButton
	 * @param iMilliSecondsIntervall
	 * @param oComponentToShow
	 * @param oInsets
	 */
	public E2_ServerPushMessageContainer_STD(		Extent 				oWidth, 
													Extent 				oHeight,
													MyE2_String 		oTitle, 
													boolean 			AutoCloseAfterLoop, 
													boolean 			ShowCancelButton,
													int 				iMilliSecondsIntervall,
													Component 			oComponentToShow,
													Insets              oInsets) 
	{
		super(oWidth, oHeight, oTitle, true, AutoCloseAfterLoop,ShowCancelButton, iMilliSecondsIntervall);
		
		
		if (oComponentToShow != null)
		{
			this.get_oGridBaseForMessages().add(oComponentToShow,(oInsets==null?E2_INSETS.I_5_5_5_5:oInsets));
		}
		
	}


	

	/**
	 * 
	 * @param oWidth
	 * @param oHeight
	 * @param oTitle
	 * @param AutoCloseAfterLoop
	 * @param ShowCancelButton
	 * @param iMilliSecondsIntervall
	 * @param iAnzahlSteps
	 * @param iSegmente
	 * @param oInsets
	 */
	public E2_ServerPushMessageContainer_STD(		Extent 				oWidth, 
													Extent 				oHeight,
													MyE2_String 		oTitle, 
													boolean 			AutoCloseAfterLoop, 
													boolean 			ShowCancelButton,
													int 				iMilliSecondsIntervall,
													int   				iAnzahlSteps,
													int                 iSegmente,
													Insets              oInsets) 
	{
		super(oWidth, oHeight, oTitle, true, AutoCloseAfterLoop,ShowCancelButton, iMilliSecondsIntervall);

		this.oBalken = new E2_FortsschrittsBalken(iAnzahlSteps, iSegmente, Color.BLUE);
		this.get_oGridBaseForMessages().add(this.oBalken,(oInsets==null?E2_INSETS.I_5_5_5_5:oInsets));
		
	}



	/**
	 * 2014-04-11: weiterer konstruktor 
	 * @param oWidth
	 * @param oHeight
	 * @param oTitle
	 * @param AutoCloseAfterLoop
	 * @param ShowCancelButton
	 * @param iMilliSecondsIntervall
	 * @param iAnzahlSteps
	 * @param iSegmente
	 * @param oComponentUnderProgressBar
	 * @param oInsets
	 * 
	 */
	public E2_ServerPushMessageContainer_STD(		Extent 				oWidth, 
													Extent 				oHeight,
													MyE2_String 		oTitle, 
													boolean 			AutoCloseAfterLoop, 
													boolean 			ShowCancelButton,
													int 				iMilliSecondsIntervall,
													int   				iAnzahlSteps,
													int                 iSegmente,
													Component           oComponentUnderProgressBar,
													Insets              oInsets) 
	{
		super(oWidth, oHeight, oTitle, true, AutoCloseAfterLoop,ShowCancelButton, iMilliSecondsIntervall);

		this.oBalken = new E2_FortsschrittsBalken(iAnzahlSteps, iSegmente, Color.BLUE);
		this.get_oGridBaseForMessages().add(this.oBalken,(oInsets==null?E2_INSETS.I_5_5_5_5:oInsets));
		if (oComponentUnderProgressBar!=null) {
			this.get_oGridBaseForMessages().add(this.oGrid4AddonInfos,(oInsets==null?E2_INSETS.I_5_5_5_5:oInsets));
			this.oGrid4AddonInfos.add(oComponentUnderProgressBar,E2_INSETS.I(0,0,0,0));
		}
	}

	
	
	public E2_FortsschrittsBalken get_oBalken() 
	{
		return oBalken;
	}


	public MyE2_Grid get_oGrid4AddonInfos()
	{
		return oGrid4AddonInfos;
	}


	
	
}
