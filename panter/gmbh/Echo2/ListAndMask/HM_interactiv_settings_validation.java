package panter.gmbh.Echo2.ListAndMask;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/*
 * existiert in jeder E2_ComponentMAP, um beliebige XX_ComponentMAP_interactiv_settings_validation-Objekte zu bestimmten feldern zu uebernehmen
 */
public class HM_interactiv_settings_validation extends LinkedHashMap<String, Vector<XX_MAP_Set_And_Valid>> implements E2_IF_Copy
{
	
	/**
	 * fuegt dem 
	 * @param HASH
	 * @param oSettingAndValid
	 */
	public void put_(String HASH, XX_MAP_Set_And_Valid oSettingAndValid)
	{
		Vector<XX_MAP_Set_And_Valid> vValid = this.get(HASH);
		
		if (vValid==null)
		{
			vValid = new Vector<XX_MAP_Set_And_Valid>();
			this.put(HASH, vValid);
		}
		
		vValid.add(oSettingAndValid);
	}
	
	
	public  Vector<XX_MAP_Set_And_Valid> get_(String HASH)
	{
		return this.get(HASH);
	}
	
	
	
	//2012-12-13: anwenden der maskensetter-collection (globale), z.B. nach dem fuellen der maske
	//erfolgt automatisch in der E2_ComponentMAP/ E2_vCombinedComponentMAPs
	public void execute_ComponentMAP_interactiv_settings(E2_ComponentMAP oMAP) throws myException
	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//ein vector, der dafuer sorgt, dass mehrfach elementen uebergeben validierer nur einmal ausgefuehrt werden
		Vector<XX_MAP_Set_And_Valid> vDublettenCheck = new Vector<XX_MAP_Set_And_Valid>();
		
		
		for (Vector<XX_MAP_Set_And_Valid> vSetting: oMAP.get_hmInteractiv_settings_validation().values())
		{
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_EDIT))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_EMPTY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_COPY))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_COPY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_VIEW))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_VIEW(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			
			//2013-121-17: neuer status: behandlung von undefined
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_UNDEFINED))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_UNDEFINED(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null));
						vDublettenCheck.add(oSetting);
					}
				}
			}

			
			
		}
		
		bibMSG.add_MESSAGE(oMV);
		
	}
	

	
	//2012-12-13: anwenden der maskensetter-collection (pro komponente in einem actionagent der maskenelemente)
	public void execute_ComponentMAP_interactiv_settings(E2_ComponentMAP oMAP, ExecINFO oExecInfo) throws myException
	{

		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		String cHashKey = null;
		
		if (oExecInfo!=null)
		{
			cHashKey = oExecInfo.get_HASHKEY_of_KLICK_COMPONENT();
		}
		
		
		if (S.isFull(cHashKey))
		{
			for (Entry<String, Vector<XX_MAP_Set_And_Valid>> oEntry: oMAP.get_hmInteractiv_settings_validation().entrySet())
			{
				Vector<XX_MAP_Set_And_Valid> vSetting = oEntry.getValue();
				
				if (oEntry.getKey().equals(cHashKey))
				{
					if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_EDIT))
					{
						for (XX_MAP_Set_And_Valid oSetting: vSetting)
						{
							oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION, oExecInfo,null));
						}
					}
					if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
					{
						for (XX_MAP_Set_And_Valid oSetting: vSetting)
						{
							oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_EMPTY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION, oExecInfo,null));
						}
					}
					if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_COPY))
					{
						for (XX_MAP_Set_And_Valid oSetting: vSetting)
						{
							oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_COPY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION, oExecInfo,null));
						}
					}
					if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_VIEW))
					{
						for (XX_MAP_Set_And_Valid oSetting: vSetting)
						{
							oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_VIEW(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION, oExecInfo,null));
						}
					}
					
					//2013-121-17: neuer status: behandlung von undefined
					if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_UNDEFINED))
					{
						for (XX_MAP_Set_And_Valid oSetting: vSetting)
						{
							oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_UNDEFINED(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION, oExecInfo,null));
						}
					}
				}
			}
		}
		
		bibMSG.add_MESSAGE(oMV);
	}
	
	
	
	
	
	
	/**
	 * 2014-01-24: anwenden der maskensetter-collection als actionAgent-Methode von beliebiger, nicht registriertem Klickobjekt
	 * Wird zum Beispiel eine Maskenvalidierung faellig, die nicht an eine simples klickobjekt gehaengt werden kann, dann
	 * kann diese methode in einem ActionAgent hilfreich sein, da sie nicht auf ein registriertes Hash wartet, sondern die
	 * die ganze hashap durchgeht
	 * @param oMAP
	 * @param oExecInfo (kann null sein)
	 * @throws myException
	 */
	public void execute_ComponentMAP_interactiv_settings_SimpleClick_Without_Registered_Component(E2_ComponentMAP oMAP, ExecINFO oExecInfo) throws myException
	{

		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		
		for (Entry<String, Vector<XX_MAP_Set_And_Valid>> oEntry: oMAP.get_hmInteractiv_settings_validation().entrySet())
		{
			Vector<XX_MAP_Set_And_Valid> vSetting = oEntry.getValue();
			
				if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_EDIT))
				{
					for (XX_MAP_Set_And_Valid oSetting: vSetting)
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION, oExecInfo,null));
					}
				}
				if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
				{
					for (XX_MAP_Set_And_Valid oSetting: vSetting)
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_EMPTY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION, oExecInfo,null));
					}
				}
				if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_COPY))
				{
					for (XX_MAP_Set_And_Valid oSetting: vSetting)
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_COPY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION, oExecInfo,null));
					}
				}
				if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_VIEW))
				{
					for (XX_MAP_Set_And_Valid oSetting: vSetting)
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_VIEW(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION, oExecInfo,null));
					}
				}
				
				//2013-121-17: neuer status: behandlung von undefined
				if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_UNDEFINED))
				{
					for (XX_MAP_Set_And_Valid oSetting: vSetting)
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_UNDEFINED(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION, oExecInfo,null));
					}
				}
			}
		
		bibMSG.add_MESSAGE(oMV);
	}

	
	//2012-12-13: anwenden der maskensetter-collection (globale), z.B. nach dem fuellen der maske
	public MyE2_MessageVector execute_ComponentMAP_ValidBeforeSave_settings(E2_ComponentMAP oMAP,SQLMaskInputMAP oInputMap) throws myException
	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//ein vector, der dafuer sorgt, dass mehrfach elementen uebergeben validierer nur einmal ausgefuehrt werden
		Vector<XX_MAP_Set_And_Valid> vDublettenCheck = new Vector<XX_MAP_Set_And_Valid>();
		
		
		for (Vector<XX_MAP_Set_And_Valid> vSetting: oMAP.get_hmInteractiv_settings_validation().values())
		{
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_EDIT))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION, null, oInputMap));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_EMPTY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION, null, oInputMap));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_NEW_COPY))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_NEW_COPY(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION, null, oInputMap));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_VIEW))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_VIEW(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION, null, oInputMap));
						vDublettenCheck.add(oSetting);
					}
				}
			}
			
			//2013-121-17: neuer status: behandlung von undefined
			if (S.NN(oMAP.get_STATUS_LAST_FILL()).equals(E2_ComponentMAP.STATUS_UNDEFINED))
			{
				for (XX_MAP_Set_And_Valid oSetting: vSetting)
				{
					if (!vDublettenCheck.contains(oSetting))
					{
						oMV.add_MESSAGE(oSetting.make_InteractiveSettings_STATUS_UNDEFINED(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION, null, oInputMap));
						vDublettenCheck.add(oSetting);
					}
				}
			}

		}
		
		return oMV;
		
	}
	

	
	



	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		HM_interactiv_settings_validation oCopy = new HM_interactiv_settings_validation();
		
		for (String cKEY: this.keySet())
		{
			Vector<XX_MAP_Set_And_Valid> vValid = this.get(cKEY);
			
			for (int i=0;i<vValid.size();i++)
			{
				oCopy.put_(cKEY, vValid.get(i));
			}
		}
		
		return oCopy;
	}
	
	
	/**
	 * map_valid_before_save-objekt, kann direkt der E2_ComponentMap uebergeben werden
	 * @return
	 */
	public XX_MAP_ValidBeforeSAVE get_XX_MAP_ValidBeforeSave()
	{
		return new XX_MAP_ValidBeforeSAVE() {
			
			@Override
			public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException {
				return HM_interactiv_settings_validation.this.execute_ComponentMAP_ValidBeforeSave_settings(oMap,oInputMap);
			}
		};
	}
	
	
	
}
