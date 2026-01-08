package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK extends MyE2_Column  implements IF_BaseComponent4Mask
{
	private E2_ComponentMAP  oMapMask = null;
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FU_MASK(FU_MASK_ComponentMAP oComponentMAP_mask)  throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.oMapMask = oComponentMAP_mask;
		
		MyE2_TabbedPane oTabbedPane =  new MyE2_TabbedPane(new Integer(700));
		
		oTabbedPane.set_bAutoHeight(true);
		
		FU_MASK_GRID oFU0= null;
		FU_MASK_GRID oFU1= null;
		FU_MASK_GRID oFU2= null;
		FU_MASK_GRID oFU3= null;
		FU_MASK_GRID oFU4= null;
		FU_MASK_GRID oFU5= null;
		FU_MASK_GRID oFU6= null;
		FU_MASK_GRID oFU7= null;
		
		
		
		oTabbedPane.add_Tabb(new MyE2_String("Fuhre"),								oFU0 =	new FU_MASK_GRID(0,oComponentMAP_mask), new ownActionAgent());
		oTabbedPane.add_Tabb(new MyE2_String("Adressen/Bemerk./Artikelbez."),		oFU1 =	new FU_MASK_GRID(1,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Nummerncodes/Routing"),				oFU2 =	new FU_MASK_GRID(2,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Kost./Prot./Sonderpos."),				oFU3 =	new FU_MASK_GRID(3,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Abzüge Ladeort"),						oFU4 =	new FU_MASK_GRID(4,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Abzüge Abladeort"),					oFU5 =	new FU_MASK_GRID(5,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Zusatzorte"),							oFU6 =	new FU_MASK_GRID(6,oComponentMAP_mask));
		oTabbedPane.add_Tabb(new MyE2_String("Fahrplan"),							oFU7 =	new FU_MASK_GRID(7,oComponentMAP_mask));
		
		this.add(oTabbedPane);
		
		this.vMaskGrids.add(oFU0);
		this.vMaskGrids.add(oFU1);
		this.vMaskGrids.add(oFU2);
		this.vMaskGrids.add(oFU3);
		this.vMaskGrids.add(oFU4);
		this.vMaskGrids.add(oFU5);
		this.vMaskGrids.add(oFU6);
		this.vMaskGrids.add(oFU7);
		
		
	}
		
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP  oMap = FU_MASK.this.oMapMask;
			
			//jetzt die Sortenbezeichnungslabels aufbauen
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeEK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK);
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeVK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK);
		    oArtbezAnzeigeEK.baue_anzeige("","","EK");
			oArtbezAnzeigeVK.baue_anzeige("","","VK");

			oArtbezAnzeigeEK.baue_anzeige(true);
			oArtbezAnzeigeVK.baue_anzeige(false);
			
		}
	}


	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
}
