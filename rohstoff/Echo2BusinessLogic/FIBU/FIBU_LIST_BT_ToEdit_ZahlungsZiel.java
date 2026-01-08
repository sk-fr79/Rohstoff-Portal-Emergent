package rohstoff.Echo2BusinessLogic.FIBU;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button_To_FastEdit;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FIBU_LIST_BT_ToEdit_ZahlungsZiel extends MyE2_DB_Button_To_FastEdit
{

	public FIBU_LIST_BT_ToEdit_ZahlungsZiel(SQLField osqlField) throws myException
	{
		super(osqlField, new MyE2_String("Zahlungsziel verändern"), MyE2_Button.StyleTextButtonSTD(new E2_FontPlain()),
									"BEARBEITE_ZAHLUNGSZIEL_AUS_LISTE", new Extent(400), new Extent(150));
		this.set_cSetTextWhenEmpty(new MyE2_String("-"));
		this.add_GlobalValidator(new ownValidator());
		
	}

	
	private class ownValidator  extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException 
		{
			//die aktuellen werte beschaffen
			E2_ComponentMAP oMAP = FIBU_LIST_BT_ToEdit_ZahlungsZiel.this.EXT().get_oComponentMAP();
			SQLResultMAP     oResultMap = oMAP.get_oInternalSQLResultMAP();

			MyE2_MessageVector oMV = new MyE2_MessageVector();
			

			if (!(oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_GUTSCHRIFT")||  oResultMap.get_UnFormatedValue("BUCHUNGSTYP").equals("DRUCK_RECHNUNG")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur für Einträge vom Typ RECHNUNG/GUTSCHRIFT bearbeitbar !"));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)		throws myException 
		{
			return null;
		}
		
	}
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FIBU_LIST_BT_ToEdit_ZahlungsZiel oButtCopy = null;
		
		try
		{
			oButtCopy = new FIBU_LIST_BT_ToEdit_ZahlungsZiel(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Button:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());

		oButtCopy.setFont(this.getFont());
		oButtCopy.setAlignment(this.getAlignment());
		
		oButtCopy.set_cSetTextWhenEmpty(this.get_cSetTextWhenEmpty());
		
		oButtCopy.set_bNoTextOnButton(this.get_bNoTextOnButton());
		
		return oButtCopy;
	}

	
	
	
	@Override
	public E2_BasicModuleContainer create_BasicContainer4Popup() throws myException
	{
		return new ownBasicContainer();
	}

	
	
	private class ownBasicContainer extends E2_BasicModuleContainer
	{
		private UB_TextField_With_DatePOPUP_OWN oDateZahlungszielKorr = null;
		private E2_ComponentMAP  				oMap = null;
		
		private String    						cID_FIBU = null;
		
		public ownBasicContainer() throws myException 
		{
			super();
			
			//die aktuellen werte beschaffen
			this.oMap = FIBU_LIST_BT_ToEdit_ZahlungsZiel.this.EXT().get_oComponentMAP();
			SQLResultMAP     oResultMap = oMap.get_oInternalSQLResultMAP();
			
			this.cID_FIBU = ""+oResultMap.get_LActualDBValue("ID_FIBU", false);
			
			
			this.oDateZahlungszielKorr = new UB_TextField_With_DatePOPUP_OWN("ZAHLUNGSZIEL", false, oResultMap.get_FormatedValue("ZAHLUNGSZIEL"), 100);

			MyE2_Button   oButtonSave = 	new MyE2_Button("Speichern");
			MyE2_Button   oButtonCancel = 	new MyE2_Button("Abbruch");
			
			oButtonSave.add_oActionAgent(new ownActionSave());
			oButtonCancel.add_oActionAgent(new ownActionCancel());
			
			MyE2_Grid oGridHelp = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			oGridHelp.add_raw(this.oDateZahlungszielKorr, LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2));
			oGridHelp.add_raw(oButtonSave, LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2));
			oGridHelp.add_raw(oButtonCancel, LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2));
			
			this.add(oGridHelp, E2_INSETS.I_5_5_5_5);
			
		}
		
		private class ownActionSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				RECORD_FIBU  						recFibu = 	new RECORD_FIBU(ownBasicContainer.this.cID_FIBU);
				UB_TextField_With_DatePOPUP_OWN 	DateZZ = 	ownBasicContainer.this.oDateZahlungszielKorr;
				
				bibMSG.add_MESSAGE(DateZZ.get_MV_InputOK());
				
				if (bibMSG.get_bIsOK())
				{
					recFibu.set_NEW_VALUE_ZAHLUNGSZIEL(ownBasicContainer.this.oDateZahlungszielKorr.get_cString4Database());
					
					bibMSG.add_MESSAGE(recFibu.UPDATE(null, true));
					
					if (bibMSG.get_bIsOK())
					{
						ownBasicContainer.this.oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
						ownBasicContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}				
				}
			}
		}
		
		private class ownActionCancel extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				ownBasicContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
		
	}
	
}
