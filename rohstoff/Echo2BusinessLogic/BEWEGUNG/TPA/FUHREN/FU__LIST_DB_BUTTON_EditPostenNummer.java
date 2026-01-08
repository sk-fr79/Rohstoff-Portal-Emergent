package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button_To_FastEdit;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU__LIST_DB_BUTTON_EditPostenNummer extends MyE2_DB_Button_To_FastEdit
{
	
	private String cEK_VK = null;
	
	
	
	
	public FU__LIST_DB_BUTTON_EditPostenNummer(SQLField osqlField, String EK_VK) throws myException
	{
		super(osqlField, 
				new MyE2_String("Bearbeiten Postennummer "+(EK_VK.equals("EK")?"(Ladeseite)":"Abladeseite")), 
				MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder(), 
				"EDIT_POSTENNUMMER_FU_IN_LISTE_"+EK_VK, 
				new Extent(370), 
				new Extent(200));
		
		this.cEK_VK = EK_VK;
		this.setLineWrap(true);
		
		this.setToolTipText(new MyE2_String("Direkte Eingabe der Posten- / Externe Eingangs-/Ausgangsnummer (auch bei geschlossenen Fuhren)").CTrans());
		
	}




	@Override
	public E2_BasicModuleContainer create_BasicContainer4Popup() throws myException
	{
		return new ownBasicModuleContainer();
	}

	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		private UB_TextField  			oTF_Postennummer = 	null;
		private RECORD_VPOS_TPA_FUHRE  	recFuhre 		=	null;
		
		public ownBasicModuleContainer() throws myException
		{
			super();
			
			FU__LIST_DB_BUTTON_EditPostenNummer oThis = FU__LIST_DB_BUTTON_EditPostenNummer.this;
			//zuerst die fuhrenid besorgen
			
			SQLResultMAP oResMap = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			
			this.recFuhre = new RECORD_VPOS_TPA_FUHRE(oResMap.get_cUNFormatedROW_ID());
			
			oTF_Postennummer = new UB_TextField(
					(oThis.cEK_VK.equals("EK")?
					recFuhre.get_POSTENNUMMER_EK_cUF_NN(""):
					recFuhre.get_POSTENNUMMER_VK_cUF_NN(""))
					, 200, 100);
			oTF_Postennummer.set_bEmptyAllowd(true);
			oTF_Postennummer.set_StartValue(oTF_Postennummer.get_cText());
			
			
			MyE2_Grid oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			//zeile 1
			oGrid.add(new MyE2_Label(new MyE2_String("Postennummer / Externe Nummer "+(oThis.cEK_VK.equals("EK")?"Ladeseite":"Abladeseite"))), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_5_2_5_2,null,2,1));

			//zeile 2
			oGrid.add(new MyE2_Label(new MyE2_String("Nummer: ")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_5_2_5_2,null,1,1));
			oGrid.add(oTF_Postennummer, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_5_2_5_2,null,1,1));

			//zeile 3
			oGrid.add(new E2_ComponentGroupHorizontal(0, new ownButtonSave(),E2_INSETS.I_0_0_10_0), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_5_2_5_2,null,1,1));
			oGrid.add(new E2_ComponentGroupHorizontal(0, new ownButtonCancel(), E2_INSETS.I_0_0_10_0), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_5_2_5_2,null,1,1));
			
			this.add(oGrid);
			
		}
		
		private class ownButtonSave extends MyE2_Button
		{

			public ownButtonSave()
			{
				super(new MyE2_String("Speichern"));
				this.add_oActionAgent(new ownActionSave());
			}
		}

		
		private class ownActionSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownBasicModuleContainer oThis = ownBasicModuleContainer.this;
				
				if (FU__LIST_DB_BUTTON_EditPostenNummer.this.cEK_VK.equals("EK"))
				{
					oThis.recFuhre.set_NEW_VALUE_POSTENNUMMER_EK(oThis.oTF_Postennummer.get_cString4Database());
				}
				else
				{
					oThis.recFuhre.set_NEW_VALUE_POSTENNUMMER_VK(oThis.oTF_Postennummer.get_cString4Database());
				}
				
				if (oThis.oTF_Postennummer.get_bFieldHasChanged())
				{
					MyE2_MessageVector oMV= oThis.recFuhre.UPDATE(null, true);
					
					if (oMV.get_bIsOK())
					{
						FU__LIST_DB_BUTTON_EditPostenNummer.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
						ownBasicModuleContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Postennummer/Externe Eingangsnummer wurde gespeichert")));
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
					}
				}
				else
				{
					ownBasicModuleContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Postennummer/Externe Eingangsnummer wurde NICHT geändert !!")));
				}
			}
		}
		
		
		private class ownButtonCancel extends MyE2_Button
		{

			public ownButtonCancel()
			{
				super(new MyE2_String("Abbruch"));
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						ownBasicModuleContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Postennummer/Externe Eingangsnummer wurde NICHT gespeichert")));
					}
				});
			}
			
		}

	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU__LIST_DB_BUTTON_EditPostenNummer oButtCopy = null;
		
		try
		{
			oButtCopy = new FU__LIST_DB_BUTTON_EditPostenNummer(this.EXT_DB().get_oSQLField(),this.cEK_VK);
			oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Button:get_Copy:Copy-Error!");
		}
		
		return oButtCopy;
	}

	
	
}
