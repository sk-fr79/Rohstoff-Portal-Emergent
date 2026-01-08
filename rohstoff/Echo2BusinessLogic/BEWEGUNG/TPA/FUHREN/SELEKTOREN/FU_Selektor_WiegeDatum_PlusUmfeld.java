package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_Date_plus_Umkreis;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selektor_WiegeDatum_PlusUmfeld extends 		E2_ListSelector_Date_plus_Umkreis
{
	public FU_Selektor_WiegeDatum_PlusUmfeld() throws myException
	{
		super("(" +
				"(JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN>=TO_DATE('#WERT#','DD.MM.YYYY')-#TAGE#  AND JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN<=TO_DATE('#WERT#','DD.MM.YYYY')+#TAGE# )"+
				 " OR "+
	  		 " (JT_VPOS_TPA_FUHRE.DATUM_ABLADEN>=TO_DATE('#WERT#','DD.MM.YYYY')-#TAGE#   AND JT_VPOS_TPA_FUHRE.DATUM_ABLADEN<=TO_DATE('#WERT#','DD.MM.YYYY')+#TAGE#  )" +
	  		 	 " OR "+
	  		 " (JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN (" +
	  		 "  SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO " +
	  		 		" WHERE NVL(FUO.DELETED,'N')='N' " +
	  		 		" AND FUO.DATUM_LADE_ABLADE>=TO_DATE('#WERT#','DD.MM.YYYY')-#TAGE#  " +
	  		 		" AND FUO.DATUM_LADE_ABLADE<=TO_DATE('#WERT#','DD.MM.YYYY')+#TAGE# )" +
	  		 	")" +
	  		 "  )","");
		
		this.get_oDatePopup().get_oTextField().set_iWidthPixel(80);
		this.get_oDatePopup().get_oTextField().set_bEnabled_For_Edit(false);
		
		String cToolTip = new MyE2_String("Gefunden werden Fuhren, deren Lade- oder Abladedatum in dem besagten Zeitraum enthalten ist").CTrans();
		
		this.setToolTipText(cToolTip);
		this.get_oSelFieldTage().set_ActiveInhalt_or_FirstInhalt("10");

	}

	@Override
	public Component get_oComponentForSelection()
	{
		MyE2_Grid oGrid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid.setSize(1);
		oGrid.setColumnWidth(0, new Extent(95));
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		oGrid.add(new MyE2_Label("Wiegedatum:"), E2_INSETS.I_0_0_5_0);
		oGrid.add(this.get_oDatePopup(), E2_INSETS.I_0_0_2_0);
		oGrid.add(new MyE2_Label("+/-"), E2_INSETS.I_0_0_2_0);
		oGrid.add(this.get_oSelFieldTage(), E2_INSETS.I_0_0_2_0);
		oGrid.add(new MyE2_Label("Tage"), E2_INSETS.I_0_0_2_0);
		return oGrid;
	}

	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection();
	}

}
