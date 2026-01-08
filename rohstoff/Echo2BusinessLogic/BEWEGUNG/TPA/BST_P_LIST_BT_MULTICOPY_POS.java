package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMultiActionWithConfirmPOPUP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF_HashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_CopyVorgang;
import rohstoff.utils.VorgangTableNames;

public class BST_P_LIST_BT_MULTICOPY_POS extends MyE2_Row
{
	private E2_NavigationList 			oNavigationList = null;
	private BST_K_MASK__ModulContainer 	oKopfMaskContainer = null;
	
	private MyE2_TextField				oTextAnzahl   = new MyE2_TextField("1",50,10);
	private ownButtonStartCopy  		oButtonStartCopy = null;
	
	
	
	public BST_P_LIST_BT_MULTICOPY_POS(	E2_NavigationList 			navigationList,
									BST_K_MASK__ModulContainer 	kopfMaskContainer
									) throws myException
	{
		super(MyE2_Row.STYLE_THIN_BORDER());
		
		this.oKopfMaskContainer = kopfMaskContainer;
		this.oNavigationList = navigationList;
		
		this.oButtonStartCopy = new ownButtonStartCopy();
		this.oTextAnzahl.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		
		this.add(this.oTextAnzahl,E2_INSETS.I_2_0_2_0);
		this.add(this.oButtonStartCopy,E2_INSETS.I_2_0_2_0);
		
	}

	
	public class ownButtonStartCopy extends MyE2_Button
	{

		public ownButtonStartCopy()
		{
			super(new MyE2_String("x Kopie"));
			
			this.add_oActionAgent(new ownActionAgent(this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"MULTICOPY_TPA_POS"));
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die TPA-Position wurde bereits gelöscht !")));
		}

	
		private class ownActionAgent extends ButtonActionAgentMultiActionWithConfirmPOPUP
		{
	
			
			public ownActionAgent(MyE2_Button oownButton)
			{
				super(new MyE2_String("Kopie Transportposition"), BST_P_LIST_BT_MULTICOPY_POS.this.oNavigationList, oownButton, new MyE2_String("Starten ?"),
						null, null, null);
			}
	
			public void do_ChangeAction( Vector<String> vIDsSelected, E2_NavigationList oNaviList) throws myException
			{

				BST_P_LIST_BT_MULTICOPY_POS oThis = BST_P_LIST_BT_MULTICOPY_POS.this;
				
				
				/*
				 * jetzt die id-validierung
				 */
				for (int i=0;i<vIDsSelected.size();i++)
				{
					String cID_To_Copy = (String)vIDsSelected.get(i);
					bibMSG.add_MESSAGE(oThis.oButtonStartCopy.valid_IDValidation(bibALL.get_Vector(cID_To_Copy)));
				}
				
				if (bibMSG.get_bIsOK())
				{	

					
					
					String cAnzahl = BST_P_LIST_BT_MULTICOPY_POS.this.oTextAnzahl.getText();
					if (!bibALL.isInteger(cAnzahl))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie eine Zahl in das Multiplikatorfeld ein !"));
					}
					else
					{
						int iAnzahl = 0;
						try
						{
							Integer IAnzahl = new Integer(cAnzahl);
							iAnzahl = IAnzahl.intValue();
						}
						catch (Exception ex)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BST_P_BT_MULTICOPY_POS:ownButtonStartCopy:do_ChangeAction: ERROR "+ex.getLocalizedMessage()));
						}
						
		
						if (bibMSG.get_bIsOK())
						{
							try 
							{
								HashMap<String,String> hmBlock_VPOS_ZIEL = 			new MyMetaFieldDEF_HashMap("JT_VPOS_TPA").get_hmFields();
	
								HashMap<String,String> hmErsatzPOS = new HashMap<String, String>();
								BS_CopyVorgang._WRITE_ERSATZFIELDS_IN_POS(hmErsatzPOS,false,new VorgangTableNames(myCONST.VORGANGSART_TRANSPORT));
								hmBlock_VPOS_ZIEL.putAll(hmErsatzPOS);
	

								Vector<String> vFieldBlockPos = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VPOS_ZIEL);
								Vector<String> vValueBlockPos = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VPOS_ZIEL);

								// einen vector aufbauen aus n x kopiersql der vIDsSelected
								Vector<String> vSQL_STACK = new Vector<String>();

								for (int i=0;i<vIDsSelected.size();i++)
								{
									String cID_POS = (String)vIDsSelected.get(i);

									
									HashMap<String,String> hmBlock_VPOS_ZIEL_ZUSATZ = 	new MyMetaFieldDEF_HashMap("JT_VPOS_TPA_FUHRE").get_hmFields();
									HashMap<String,String> hmErsatzZusatz = new HashMap<String, String>();
									BS_CopyVorgang._WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(hmErsatzZusatz,new VorgangTableNames(myCONST.VORGANGSART_TRANSPORT), new RECORD_VPOS_TPA(cID_POS).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0));
									hmBlock_VPOS_ZIEL_ZUSATZ.putAll(hmErsatzZusatz);
									Vector<String> vFieldBlockPosZusatz = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);
									Vector<String> vValueBlockPosZusatz = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);

//									new DebugPrinter(hmErsatzZusatz);
									
									for (int k=0;k<iAnzahl;k++)
									{
										String 	cSQLPos = "INSERT INTO "+bibE2.cTO()+".JT_VPOS_TPA ("+bibALL.Concatenate(vFieldBlockPos,",", null)+")"+
															" SELECT "+bibALL.Concatenate(vValueBlockPos,",", null)+" FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE "+
															"ID_VPOS_TPA="+cID_POS;
	
										vSQL_STACK.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLPos);
										
										String cSQLPosZusatz = "INSERT INTO "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE ("+bibALL.Concatenate(vFieldBlockPosZusatz,",", null)+")"+
														" SELECT "+bibALL.Concatenate(vValueBlockPosZusatz,",", null)+" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE "+
														"ID_VPOS_TPA="+cID_POS;
										
										vSQL_STACK.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLPosZusatz);
									}
									
								}
								
								bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_STACK,true));
								if (bibMSG.get_bIsOK())
								{
									bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl kopierte Positionen: "+(int)(vSQL_STACK.size()/2)));
									oThis.oNavigationList._REBUILD_COMPLETE_LIST("");
								}
								else
								{
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kopieren !!"));
								}
							} 
							catch (SQLException e) 
							{
								e.printStackTrace();
								throw new myException(this,"SQLException:"+e.getLocalizedMessage());
							}
						}
						
					}
				}
			}

			public MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete) {return null;}

			@Override
			public E2_ConfirmBasicModuleContainer create_ConfirmBasicModuleContainer(
					MyE2_String windowtitle, MyE2_String texttitle,
					MyE2_String innerTextblock, MyE2_String labelOKButton,
					MyE2_String labelCancelButton, Extent width, Extent height)
					throws myException
			{
				return new ownConfirmBasicModuleContainer(windowtitle, texttitle,
						innerTextblock, labelOKButton,
						labelCancelButton, width, height);
			}
		}
	}

	public ownButtonStartCopy get_oButtonStartCopy()
	{
		return oButtonStartCopy;
	}


	public MyE2_TextField get_oTextAnzahl()
	{
		return oTextAnzahl;
	}
	

	
	private class ownConfirmBasicModuleContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmBasicModuleContainer(
				MyE2_String windowtitle, MyE2_String texttitle,
				MyE2_String innerTextblock, MyE2_String labelOKButton,
				MyE2_String labelCancelButton, Extent width, Extent height) throws myException
		{
			super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,
					width, height);
		}
		
	}

	
	
	
}
