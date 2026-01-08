package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.DATENBLATT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS_BasicModuleContainerMASK;

public class ART_DB_FullDaughter4Mask extends XX_FULL_DAUGHTER
{

	private ART_DB__LIST_BasicModule_Inlay   	oModulContainerList_Daughter = null;
	
	public ART_DB_FullDaughter4Mask(SQLFieldForPrimaryKey 	osqlField, AS_BasicModuleContainerMASK BasicModuleContainerMask) throws myException
	{
		super(osqlField);
		
		this.oModulContainerList_Daughter = new ART_DB__LIST_BasicModule_Inlay(BasicModuleContainerMask);
		
		this.set_bIsActive(true);
	}

	public Component build_content_for_Mask(String cValueFormated, String cValueUnformated, String cMASK_STATUS) throws myException
	{
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Zusätze können erst erfasst werden, wenn der Mandant gespeichert wurde!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oModulContainerList_Daughter.set_ID_From_Calling_Record(cValueUnformated);
			this.oModulContainerList_Daughter.get_oNaviList().Fill_NavigationList("");
			return this.oModulContainerList_Daughter;
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oModulContainerList_Daughter.set_ID_From_Calling_Record(cValueUnformated);
			this.oModulContainerList_Daughter.get_oNaviList().Fill_NavigationList("");
			return this.oModulContainerList_Daughter;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		
	}

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Steuervermerke können erst erfasst werden, wenn der Mandant gespeichert wurde!")));
	}

	
	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}


	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{

		this.oModulContainerList_Daughter.set_ListButtonsEnabled(false,true);
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oModulContainerList_Daughter.set_ListButtonsEnabled(true,true);
		}
	}

}
