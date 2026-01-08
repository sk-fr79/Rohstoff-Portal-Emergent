package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_SONDER;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

public class FUO_MASK_DB_SELECT_SonderDef extends MyE2_DB_SelectField
{

	public FUO_MASK_DB_SELECT_SonderDef(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_VPOS_TPA_FUHRE_SONDER","AUSNAHME","ID_VPOS_TPA_FUHRE_SONDER",null,null,true,"AUSNAHME");
		this.set_ListenInhalt(oDD.getDD(),false);

		this.add_oActionAgent(new ownAction());
		

	}

	private class ownAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FUO_MASK_DB_SELECT_SonderDef 	oThis = FUO_MASK_DB_SELECT_SonderDef.this;
			E2_ComponentMAP 				oMap = oThis.EXT().get_oComponentMAP();
			
			/*
			 * 	OHNE_ABRECHNUNG
				KEIN_KONTRAKT_NOETIG
				OHNE_AVV_VERTRAG_CHECK
			 */
			
			if (!bibALL.isEmpty(oThis.get_ActualWert()))
			{
				Long lWert= oThis.EXT_DB().get_LActualDBValue(true, true, new Long(0));
				
				RECORD_VPOS_TPA_FUHRE_SONDER oMapSonder = new RECORD_VPOS_TPA_FUHRE_SONDER(lWert.longValue());
				
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_ABRECHNUNG")).setSelected(oMapSonder.is_OHNE_ABRECHNUNG_YES());
				((MyE2_DB_CheckBox)oMap.get__Comp("KEIN_KONTRAKT_NOETIG")).setSelected(oMapSonder.is_KEIN_KONTRAKT_NOETIG_YES());
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_AVV_VERTRAG_CHECK")).setSelected(oMapSonder.is_OHNE_AVV_VERTRAG_CHECK_YES());


				
				// jetzt die sonderpositionen verarbeiten
				
				//  ----- das tochterelement raussuchen
				FUO_MASK_DAUGHTER_POS_VL oDaughterSonderPos = (FUO_MASK_DAUGHTER_POS_VL)oMap.get__Comp(FUO__CONST.HASHKEY_DAUGHTER_POS_VORLAGEN);
				
				// alle eintraege mit der QUELLEN-Angabe SONDER loeschmarkieren (wenn diese noch nicht erzeugt wurden)
				Vector<E2_ComponentMAP>  vAlleMaps = new Vector<E2_ComponentMAP>();
				vAlleMaps.addAll(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS());
				vAlleMaps.addAll(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW());
				
				for (E2_ComponentMAP oMapZeile: vAlleMaps)
				{
					MyE2_DB_SelectField oSelQuelle = (MyE2_DB_SelectField)oMapZeile.get__Comp("QUELLE");
					if (oSelQuelle.get_ActualWert().equals(FU___CONST.RG_VL_QUELLE_VARIANTE_SONDER))
					{
						if (!((MyE2_DB_CheckBox)oMapZeile.get__Comp("WURDE_ERZEUGT")).isSelected())
						{
							((MyE2_ButtonMarkForDelete)oMapZeile.get__Comp("DELETE_SELECTOR")).set_bMarkToDelete(true);
							oMapZeile.set_AllComponentsAsDeleted();
						}
					}
				}

				
				if (oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get_vKeyValues().size()>0)
				{
					
					//jetzt neue eintraege vornehmen (so wie sie in der MAP-Sonder vorliegen
					for (int i=0;i<oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get_vKeyValues().size();i++)
					{
						// String cID_VPOS_RG_VL = oMapSonder.get_DOWN_MAP_FUHRE_SONDER_VL().get_cF_ID_VPOS_RG_VL(cID_FUHRE_SONDER_VL);
						String cID_VPOS_RG_VL = oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get(i).get_ID_VPOS_RG_VL_cF();

						//neue zeile an NaviList
						oDaughterSonderPos.get_oNavigationList().add_Row_MAP_FOR_NEW_INPUT(true, true, true);
						E2_ComponentMAP oMapNEW = oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW().get(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW().size()-1);

						//fuellen
						((MyE2_DB_SelectField)  oMapNEW.get__Comp("ID_VPOS_RG_VL")).set_ActiveValue(cID_VPOS_RG_VL);
						((MyE2_DB_CheckBox) 	oMapNEW.get__Comp("WURDE_ERZEUGT")).setSelected(false);
						((MyE2_DB_SelectField) 	oMapNEW.get__Comp("QUELLE")).set_ActiveValue(FU___CONST.RG_VL_QUELLE_VARIANTE_SONDER);
						
						oDaughterSonderPos.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
						
					}
				}
			}
			else
			{
				//dann die standardvorgabe, alle ausnahmen ausschalten
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_ABRECHNUNG")).setSelected(false);
				((MyE2_DB_CheckBox)oMap.get__Comp("KEIN_KONTRAKT_NOETIG")).setSelected(false);
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_AVV_VERTRAG_CHECK")).setSelected(false);
			}
		}
	}
	
	
}
