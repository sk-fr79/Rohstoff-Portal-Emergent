package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSRG_K_MASK_ButtonSelect_USTID extends MyE2_Button 
{

	private boolean b_Mandant = false;
	
	/**
	 * 
	 * @param bMandant (wenn false, dann wird die adresse des Abnehmers der Maske genommen, sonst die adresse des mandanten)
	 * @throws myException 
	 */
	public BSRG_K_MASK_ButtonSelect_USTID(boolean Mandant) throws myException 
	{
		super(new MyE2_String("UST.ID "+(Mandant?"("+bibALL.get_RECORD_MANDANT().get_KURZNAME_cUF_NN("<Mandant>")+")":" (Adressat)")));
		this.setToolTipText(Mandant? new MyE2_String("Auswahl der für diesen Beleg gueltigen UST-ID von "+bibALL.get_RECORD_MANDANT().get_NAME1_cUF_NN("<Mandant>")).CTrans():
				new MyE2_String("Auswahl der für diesen Beleg gueltigen UST-ID des Kunden").CTrans());
		
		this.b_Mandant=Mandant;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setWidth(new Extent(130));
		
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_Button oButtonThis = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			E2_ComponentMAP oMap = oButtonThis.EXT().get_oComponentMAP();
			
			String cActualAdressID = BSRG_K_MASK_ButtonSelect_USTID.this.b_Mandant
					? 	bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0")
					:   oMap.get_cActualDBValueFormated("ID_ADRESSE");
			
			if (S.isFull(cActualAdressID) && bibALL.isLong(bibALL.ReplaceTeilString(cActualAdressID, ".", "")))
			{
				RECORD_ADRESSE  		recAdresse = 	new RECORD_ADRESSE(""+bibALL.ReplaceTeilString(cActualAdressID, ".", ""));
				Vector<MyE2_Button>  	vSelekt_USTID = new Vector<MyE2_Button>();

				RECORD_FIRMENINFO       recFirmenInfo = recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
				
				//einen Eintrag definieren, der die Felder wieder leermacht
				MyE2_Button oButtonLeer = new MyE2_Button(new MyE2_String("<--Felder löschen-->"));
				oButtonLeer.EXT().set_C_MERKMAL("");
				oButtonLeer.EXT().set_C_MERKMAL2("");
				oButtonLeer.EXT().set_C_MERKMAL3("");
				vSelekt_USTID.add(oButtonLeer);
				
				if (S.isFull(recFirmenInfo.get_UMSATZSTEUERLKZ_cUF_NN("")) || S.isFull(recFirmenInfo.get_UMSATZSTEUERID_cUF_NN("")))
				{
					MyE2_Button oButton = new MyE2_Button(recFirmenInfo.get_UMSATZSTEUERLKZ_cUF_NN("")+"    "+recFirmenInfo.get_UMSATZSTEUERID_cUF_NN("")+" (*)");
					oButton.EXT().set_C_MERKMAL(recFirmenInfo.get_UMSATZSTEUERLKZ_cUF_NN(""));
					oButton.EXT().set_C_MERKMAL2(recFirmenInfo.get_UMSATZSTEUERID_cUF_NN(""));
					oButton.EXT().set_C_MERKMAL3("");
					vSelekt_USTID.add(oButton);
				}

				RECLIST_ADRESSE_UST_ID recIDs =recAdresse.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();
				
				for (int i=0;i<recIDs.get_vKeyValues().size();i++)
				{
					MyE2_Button oButton = new MyE2_Button(recIDs.get(i).get_UMSATZSTEUERLKZ_cUF_NN("")+"    "+recIDs.get(i).get_UMSATZSTEUERID_cUF_NN(""));
					oButton.EXT().set_C_MERKMAL(recIDs.get(i).get_UMSATZSTEUERLKZ_cUF_NN(""));
					oButton.EXT().set_C_MERKMAL2(recIDs.get(i).get_UMSATZSTEUERID_cUF_NN(""));
					oButton.EXT().set_C_MERKMAL3(recIDs.get(i).get_TEXT_AUSLANDSVERTRETUNG_cUF_NN(""));
					vSelekt_USTID.add(oButton);
				}
				if (BSRG_K_MASK_ButtonSelect_USTID.this.b_Mandant)
				{
					new ownPopup(vSelekt_USTID, oMap.get__DBComp("UMSATZSTEUERLKZ_MANDANT"), 
												oMap.get__DBComp("UMSATZSTEUERID_MANDANT"), 
												oMap.get__DBComp("TEXT_AUSLANDSVERTRETUNG"));
				}
				else
				{
					new ownPopup(vSelekt_USTID, oMap.get__DBComp("UMSATZSTEUERLKZ"), 
												oMap.get__DBComp("UMSATZSTEUERID"),
												null);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Adresse auswählen !!"));
			}
			
		}
	}

	private class ownPopup extends E2_BasicModuleContainer
	{
		private Vector<MyE2_Button> vButtons = null;
		private MyE2IF__DB_Component      tfUST_LKZ = null;
		private MyE2IF__DB_Component      tfUST_ID = null;
		private MyE2IF__DB_Component       tfUST_AUSLANDSTEXT = null;
		
		public ownPopup(Vector<MyE2_Button> Buttons, MyE2IF__DB_Component UST_LKZ, MyE2IF__DB_Component UST_ID,MyE2IF__DB_Component    UST_AUSLANDSTEXT) throws myException 
		{
			super();
			this.vButtons = 			Buttons;
			this.tfUST_LKZ = 			UST_LKZ;
			this.tfUST_ID = 			UST_ID;
			this.tfUST_AUSLANDSTEXT = 	UST_AUSLANDSTEXT;
			
			MyE2_Grid  oGridHelp = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER());
			
			for (MyE2_Button oButt: this.vButtons)
			{
				oGridHelp.add(oButt, E2_INSETS.I_2_2_2_2);
				oButt.add_oActionAgent(new ownActionAgentZuweisung());
			}
			
			this.add(oGridHelp, E2_INSETS.I_5_5_5_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(200), new Extent(500), new MyE2_String("Bitte wählen .."));
		}

		private class ownActionAgentZuweisung extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				MyE2_Button  oButt = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
				ownPopup.this.tfUST_LKZ.set_cActualMaskValue(oButt.EXT().get_C_MERKMAL());
				ownPopup.this.tfUST_ID.set_cActualMaskValue(oButt.EXT().get_C_MERKMAL2());
				if (ownPopup.this.tfUST_AUSLANDSTEXT!=null)
				{
					ownPopup.this.tfUST_AUSLANDSTEXT.set_cActualMaskValue(oButt.EXT().get_C_MERKMAL3());
				}
				
				ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
		
	}
	
}
