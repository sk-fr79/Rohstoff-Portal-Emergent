package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;


import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE;

public class LAG_BEW_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	
	private Vector<String>  vAuswahlHaufen = null;
	
	private GridLayoutData  GreenLayout = new GridLayoutData();
	
	/**
	 * @param auswahlHaufen
	 */
	public LAG_BEW_LIST_FORMATING_Agent(Vector<String> auswahlHaufen)
	{
		super();
		vAuswahlHaufen = auswahlHaufen;
		this.GreenLayout.setBackground(Color.GREEN);
		this.GreenLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
	}

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		if (this.vAuswahlHaufen.contains(oUsedResultMAP.get_cUNFormatedROW_ID()))
		{
//			((Component)oMAP.get_Comp("PREIS"))..setLayoutData(this.GreenLayout);
			oMAP.get__Comp("PREIS").EXT().set_oLayout_ListElement(this.GreenLayout);
		}
		

		
		MyE2_Column_IMMER_ENABLED col = (MyE2_Column_IMMER_ENABLED)oMAP.get__Comp(LAG_BEW_CONST.HASH_BUTTON_SHOW_FUHRE);
		col.removeAll();

		String idFuhre = oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		if (!bibALL.isEmpty(idFuhre)){
			// button fürs Öffnen der fuhre einbauen
			ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE oBtn = new ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
					oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE"),
					null,
					null,
					(XX_ActionAgent)null,
					true,
					true
					);
			col.add(oBtn);
		}
	}

	
	private class ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE extends POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE
	{

		/**
		 * @param cid_vpos_tpa_fuhre_unformated
		 * @param iconAmStart
		 * @param beschriftung
		 * @param actionAgentAfterSaveMask
		 * @param ShowInViewModeWhenEditForbidden
		 * @param autoRefreshContainer
		 * @throws myException
		 */
		public ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
				String cid_vpos_tpa_fuhre_unformated,
				E2_ResourceIcon iconAmStart, MyString beschriftung,
				XX_ActionAgent actionAgentAfterSaveMask,
				boolean ShowInViewModeWhenEditForbidden,
				boolean autoRefreshContainer) throws myException
		{
			super(cid_vpos_tpa_fuhre_unformated, iconAmStart, beschriftung,
					actionAgentAfterSaveMask, ShowInViewModeWhenEditForbidden,
					autoRefreshContainer);
		}

		
		public void set_bEnabled_For_Edit(boolean enabled) throws myException
		{
		}
	}
	
}
