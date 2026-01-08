package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_BT_BAUE_FUHREN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Mask_SHOW_SUM;

public class FUS_Grid_ErfassteFuhren extends MyE2_Grid
{
	
	private FUS_Vector_FuhreRepraesentantInListe   vFuhren = new FUS_Vector_FuhreRepraesentantInListe();
	
	

	public FUS_Grid_ErfassteFuhren()
	{
		super(9, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
	}

	
	
	public FUS_Vector_FuhreRepraesentantInListe get_vFuhren()
	{
		return vFuhren;
	}
	
	
	public void add_Fuhre_to_List(FUS_FuhreRepraesentantInListe  oFuhre) throws myException
	{
		this.vFuhren.add(oFuhre);
		this.baue_liste_auf();
	}
	
	
	public void baue_liste_auf() throws myException
	{
		this.removeAll();
		
		for (int i=0;i<this.vFuhren.size();i++)
		{
			FUS_FuhreRepraesentantInListe  oFuhre = this.vFuhren.get(i);
			this.add_to_list(oFuhre);
		}
		
		new _SEARCH_BT_BAUE_FUHREN().get_Found_FUS_BT_BAUE_FUHREN().set_bEnabled_For_Edit(this.vFuhren.size()>0);
		
		new _SEARCH_Mask_SHOW_SUM().get_Found_FUS_Mask_SHOW_SUM().set_Text(
				MyNumberFormater.formatDez(this.vFuhren.get_bdSummeAllerPositionen(), 3, true));
		
		
	}
	
	
	private void add_to_list(FUS_FuhreRepraesentantInListe  oFuhre) throws myException
	{
		this.add(oFuhre.get_Comp_Datum_und_oder_Datum2());

		this.add(oFuhre.get_Comp_LADESTELLE());
		this.add(oFuhre.get_Comp_LADESORTE());
		
		this.add(oFuhre.get_Comp_ABLADESTELLE());
		this.add(oFuhre.get_Comp_ABLADESORTE());
		
		this.add(oFuhre.get_Comp_WiegeMenge());
		
		if (new _Check_ob_fahrplan().get_bIsFahrPlan())
		{
			this.add(oFuhre.get_Comp_AnzahlContainer());
		}
		else
		{
			this.add(new MyE2_Label(""));
		}

		this.add(oFuhre.get_oButtonEditRow());
		
		GridLayoutData oGL_OK = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_0_5_0);
		GridLayoutData oGL_WARN = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_0_5_0);
		oGL_WARN.setBackground(new E2_ColorHelpBackground());
		
		
		if (new __FUS_Check_ob_VK_Mit_Kontrakt(oFuhre).get_bOK())
		{
			this.add(oFuhre.get_oButtonDeleteRow(),oGL_OK);
		}
		else
		{
			this.add(oFuhre.get_oButtonDeleteRow(),oGL_WARN);
		}
	}
	
}
