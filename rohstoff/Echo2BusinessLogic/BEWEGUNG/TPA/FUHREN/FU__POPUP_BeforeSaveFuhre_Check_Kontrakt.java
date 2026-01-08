package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FU__POPUP_BeforeSaveFuhre_Check_Kontrakt extends E2_BasicModuleContainer_PopUp_BeforeExecute
{

	
	public FU__POPUP_BeforeSaveFuhre_Check_Kontrakt()
	{
		super();
		
		this.get_oButtonForOK().set_Text(new MyE2_String("Trotzdem Speichern"));
		this.get_oButtonForAbbruch().set_Text(new MyE2_String("Abbrechen und zurück"));
		this.set_bCloseAfterOK_With_Errors(true);
	}

	@Override
	public void doBuildContent(ExecINFO oExecInfo) throws myException
	{
		
		MyE2_Column  oColumnInhalt = 	new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		MyE2_Grid    oGridEK = 			new MyE2_Grid(3,0);
		MyE2_Grid    oGridVK = 			new MyE2_Grid(3,0);
		
		E2_vCombinedComponentMAPs 		vComponentMAPs = ((E2_BasicModuleContainer_MASK) (new E2_RecursiveSearchParent_BasicModuleContainer(oExecInfo.get_MyActionEvent()).get_First_FoundContainer())).get_vCombinedComponentMAPs();

		E2_ComponentMAP  oMapFuhre = null;
		
		for (E2_ComponentMAP oMap: vComponentMAPs)
		{
			if (oMap.get_oSQLFieldMAP().get_cMAIN_TABLE().toUpperCase().equals("JT_VPOS_TPA_FUHRE"))
			{
				oMapFuhre = oMap;
			}
		}
		
		if (oMapFuhre == null)
		{
			throw new myException("Error Checking Fuhren-Kontrakt-Situation (1)!");
		}
		
		//nachsehen, ob die sorte und der kunde als kombination in einem offenen kontrakt vorhanden sind: wenn in der fuhre kein kontrakt vorhanden ist, dann warnen
		long lID_ADRESSE_START = 	oMapFuhre.get_LActualDBValue("ID_ADRESSE_START", true,true, new Long(-1));
		long lID_ADRESSE_ZIEL = 	oMapFuhre.get_LActualDBValue("ID_ADRESSE_ZIEL", true,true, new Long(-1));
		long lID_ARTIKEL = 			oMapFuhre.get_LActualDBValue("ID_ARTIKEL", true,true, new Long(-1));
	
		if (lID_ADRESSE_START==-1 || lID_ADRESSE_ZIEL==-1 || lID_ARTIKEL == -1)
		{
			return;      //dann geht die maske sowieso nicht durch
		}
		
		long lID_VPOS_KON_EK = 		oMapFuhre.get_LActualDBValue("ID_VPOS_KON_EK", new Long(-1), new Long(-2));
		long lID_VPOS_KON_VK = 		oMapFuhre.get_LActualDBValue("ID_VPOS_KON_VK", new Long(-1), new Long(-2));
		
		/*
		 * pruefen dann, wenn es eine fremdadresse und ein leeres kontrakt-feld ist
		 */
		boolean bPruefungEK = 		(lID_ADRESSE_START!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)) && lID_VPOS_KON_EK==-1); 
		boolean bPruefungVK = 		(lID_ADRESSE_ZIEL!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)) && lID_VPOS_KON_VK==-1); 

		
		RECLIST_VPOS_KON  recVPOS_KON_EK = null;
		RECLIST_VPOS_KON  recVPOS_KON_VK = null;

		if (bPruefungEK)
		{
			 recVPOS_KON_EK = this.get_RECLIST("EK", lID_ARTIKEL, lID_ADRESSE_START);
		}

		if (bPruefungVK)
		{
			recVPOS_KON_VK = this.get_RECLIST("VK", lID_ARTIKEL, lID_ADRESSE_ZIEL);
		}

		
		oGridEK.setVisible(false);
		oGridVK.setVisible(false);
		if (recVPOS_KON_EK != null && recVPOS_KON_EK.get_vKeyValues().size()>0)
		{
			MyE2_String cInfo1 = 
					new MyE2_String("Achtung! Für ",true,
					recVPOS_KON_EK.get(0).get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT"),"","<",">"," "),false);

			MyE2_String cInfo2 = 
					new MyE2_String("mit der Sorte ",true,
					recVPOS_KON_EK.get(0).get___KETTE(bibALL.get_Vector("ANR1", "ANR2","ARTBEZ1"),"","<",">"," "),false," existiert ein/mehrere EK-Kontrakte!",true);

			oGridEK.add(new MyE2_Label(cInfo1),3,E2_INSETS.I_1_1_1_1);
			oGridEK.add(new MyE2_Label(cInfo2),3,E2_INSETS.I_1_1_1_1);

			oGridEK.setVisible(true);
		}
		if (recVPOS_KON_VK != null && recVPOS_KON_VK.get_vKeyValues().size()>0)
		{
			MyE2_String cInfo1 = 
				new MyE2_String("Achtung! Für ",true,
				recVPOS_KON_VK.get(0).get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get___KETTE(bibALL.get_Vector("NAME1", "NAME2")),false);

			MyE2_String cInfo2 = 
				new MyE2_String("mit der Sorte ",true,
				recVPOS_KON_VK.get(0).get___KETTE(bibALL.get_Vector("ANR1", "ANR2","ARTBEZ1")),false," existiert ein/mehrere VK-Kontrakte!",true);

			oGridVK.add(new MyE2_Label(cInfo1),3,E2_INSETS.I_1_1_1_1);
			oGridVK.add(new MyE2_Label(cInfo2),3,E2_INSETS.I_1_1_1_1);
			oGridVK.setVisible(true);
		}
	
		oColumnInhalt.add(oGridEK, E2_INSETS.I_0_5_0_5);
		oColumnInhalt.add(oGridVK, E2_INSETS.I_0_5_0_5);
		
		this.add(oColumnInhalt,E2_INSETS.I_5_5_5_5);
		this.add(new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_0_2_10_2),E2_INSETS.I_5_5_5_5);

	}

	@Override
	protected void doOwnCode_in_ok_button() throws myException
	{


	}

	@Override
	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException
	{
		
		E2_vCombinedComponentMAPs 		vComponentMAPs = ((E2_BasicModuleContainer_MASK) (new E2_RecursiveSearchParent_BasicModuleContainer(oExecInfo.get_MyActionEvent()).get_First_FoundContainer())).get_vCombinedComponentMAPs();

		E2_ComponentMAP  oMapFuhre = null;
		
		for (E2_ComponentMAP oMap: vComponentMAPs)
		{
			if (oMap.get_oSQLFieldMAP().get_cMAIN_TABLE().toUpperCase().equals("JT_VPOS_TPA_FUHRE"))
			{
				oMapFuhre = oMap;
			}
		}
		
		if (oMapFuhre == null)
		{
			throw new myException("Error Checking Fuhren-Kontrakt-Situation (1)!");
		}
		
		//nachsehen, ob die sorte und der kunde als kombination in einem offenen kontrakt vorhanden sind: wenn in der fuhre kein kontrakt vorhanden ist, dann warnen
		long lID_ADRESSE_START = 	oMapFuhre.get_LActualDBValue("ID_ADRESSE_START", true,true, new Long(-1));
		long lID_ADRESSE_ZIEL = 	oMapFuhre.get_LActualDBValue("ID_ADRESSE_ZIEL", true,true, new Long(-1));
		long lID_ARTIKEL = 			oMapFuhre.get_LActualDBValue("ID_ARTIKEL", true,true, new Long(-1));
	
		if (lID_ADRESSE_START==-1 || lID_ADRESSE_ZIEL==-1 || lID_ARTIKEL == -1)
		{
			return false;      //dann geht die maske sowieso nicht durch
		}
		
		long lID_VPOS_KON_EK = 		oMapFuhre.get_LActualDBValue("ID_VPOS_KON_EK", new Long(-1), new Long(-2));
		long lID_VPOS_KON_VK = 		oMapFuhre.get_LActualDBValue("ID_VPOS_KON_VK", new Long(-1), new Long(-2));
		
		/*
		 * pruefen dann, wenn es eine fremdadresse und ein leeres kontrakt-feld ist
		 */
		boolean bPruefungEK = 		(lID_ADRESSE_START!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)) && lID_VPOS_KON_EK==-1); 
		boolean bPruefungVK = 		(lID_ADRESSE_ZIEL!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)) && lID_VPOS_KON_VK==-1); 

		boolean bPopUpEK = 		false; 
		boolean bPopUpVK = 		false; 

		if (bPruefungEK)
		{
			if (this.get_RECLIST("EK", lID_ARTIKEL, lID_ADRESSE_START).get_vKeyValues().size()>0)
			{
				bPopUpEK = true;
			}
		}

		if (bPruefungVK)
		{
			if (this.get_RECLIST("VK", lID_ARTIKEL, lID_ADRESSE_ZIEL).get_vKeyValues().size()>0)
			{
				bPopUpVK = true;
			}
		}
		
		return (bPopUpEK || bPopUpVK);
	}

	@Override
	protected void doOwnCode_in_cancel_button() throws myException
	{
		
	}

	
	private RECLIST_VPOS_KON get_RECLIST(String cEK_VK, long lID_ARTIKEL, long lID_ADRESSE) throws myException
	{
		String cSQL = "SELECT "+
						"    JT_VPOS_KON.*"+
						" FROM "+
						bibE2.cTO()+".JT_VPOS_KON_TRAKT "+
						" INNER JOIN "+
						bibE2.cTO()+".JT_VPOS_KON "+
						"    ON "+
						"    ("+
						"        JT_VPOS_KON_TRAKT.ID_VPOS_KON = JT_VPOS_KON.ID_VPOS_KON "+
						"    ) "+
						" INNER JOIN "+
						bibE2.cTO()+".JT_VKOPF_KON "+
						"    ON "+
						"    ( "+
						"        JT_VPOS_KON.ID_VKOPF_KON = JT_VKOPF_KON.ID_VKOPF_KON "+
						"    ) "+
						" WHERE NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' " +
						" AND   NVL(JT_VPOS_KON.DELETED,'N')='N' " +
						" AND   JT_VPOS_KON.ID_ARTIKEL="+lID_ARTIKEL+
						" AND   JT_VKOPF_KON.ID_ADRESSE="+lID_ADRESSE+
						" AND   JT_VKOPF_KON.VORGANG_TYP='"+cEK_VK+"_KONTRAKT'";
		
		RECLIST_VPOS_KON reclistVPOS_KON = new RECLIST_VPOS_KON(cSQL);
		return reclistVPOS_KON;
	}
	
}
