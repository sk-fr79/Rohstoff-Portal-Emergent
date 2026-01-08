package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class KFIX__BT_Lock_unlock_position extends MyE2_Button
	{
		private String cID_VPOS_KON = null;
		private boolean bActiv = false;

		private GridLayoutData   GL_Green = null;
		private GridLayoutData   GL_Red = null;
		
		
		public KFIX__BT_Lock_unlock_position(String 				ID_VPOS_KON,
											boolean 				gesperrtAmStart,
											boolean                 Activ,
											GridLayoutData          oGL_Open,
											GridLayoutData          oGL_Closed,
											String 					p_modul_identifier) throws myException
		{
			super(E2_ResourceIcon.get_RI("unlocked.png"));
						
			this.GL_Green = oGL_Open;
			this.GL_Red = oGL_Closed;
			
			this.cID_VPOS_KON = ID_VPOS_KON;
			this.bActiv = Activ;
		
			this.setLayoutData(oGL_Open);
			
			if (gesperrtAmStart)
			{
				this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
				this.setLayoutData(oGL_Closed);
			}

			//hier folgt die ausfuehrung nicht auf die tabelle jt_vpos_kon sondern auf jt_vpos_kon_trakt, deshalb ist die validierung auch dort durchzufuehren
			this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
															" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
															" ANZAHL IS NOT NULL AND EINZELPREIS IS NOT NULL AND "+
															" ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON_TRAKT=#WERT#)",
															bibALL.get_Array("1"),
															true, new MyE2_String("Nur bei komplett ausgefüllten Mengenpositionen erlaubt !")));

			this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
															" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
															" NVL(DELETED,'N')='N' AND "+
															" ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON_TRAKT=#WERT#)",
															bibALL.get_Array("1"),
															true, new MyE2_String("Die Position wurde bereits gelöscht !")));

			this.add_oActionAgent(new ownActionAgentToggleOeffnenSchliessen());
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(p_modul_identifier,"LOCK_UNLOCK_KONTRAKTPOS"));
			this.setToolTipText(new MyE2_String("Öffnen/Schliessen einer Kontraktposition").CTrans());
		}
		
		
		public Component get_Comp()
		{
			return this.bActiv?this:new MyE2_Label(KFIX_K_L_EXPANDER_4_ComponentMAP_NG.oIconLeer);
		}

		
		private class ownActionAgentToggleOeffnenSchliessen extends ButtonActionAgent_TOGGLE_Y_N
		{
			public ownActionAgentToggleOeffnenSchliessen() throws myException
			{
				super(new MyE2_String("Öffnen/Schliessen Kontrakt-Position"),
										null,
										"ABGESCHLOSSEN",
										"JT_VPOS_KON_TRAKT",
										"ID_VPOS_KON_TRAKT");
				
				this.set_oAddonDialogBuilder(new KFIX__ADD_ON_DIALOG_BUILDER_Benachrichtige_NG()
				{

					@Override
					public void fill_v_ID_VPOS_KON_ToToggle() throws myException
					{
						this.get_vIDsToToggle().removeAllElements();
						this.get_vIDsToToggle().add(KFIX__BT_Lock_unlock_position.this.cID_VPOS_KON);
					}
					
				});
				
			}
			
			
			/*
			 * methode, um die id aus der liste mit der id, die geaendert wird,
			 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
			 */
			public Vector<String> get_IDS_FOR_Toggle() throws myException
			{
				String cID_VPOS_KON_TRAKT = bibDB.EinzelAbfrage("SELECT ID_VPOS_KON_TRAKT FROM "+
									bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+KFIX__BT_Lock_unlock_position.this.cID_VPOS_KON);
				if (!bibALL.isEmpty(cID_VPOS_KON_TRAKT) && bibALL.isLong(cID_VPOS_KON_TRAKT))
				{
					return bibALL.get_Vector(cID_VPOS_KON_TRAKT);	
				}
				else
				{
					throw new myException("Error finding ID_VPOS_KON_TRAKT ");
				}
			}


			public MyE2_MessageVector CheckIdToToggle(Vector<String> vID_UnformatedToDelete){return null;}

			public void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException 
			{
				// knopf richtig stellen
				String Abgeschlossen = bibDB.EinzelAbfrage("SELECT NVL(ABGESCHLOSSEN,'N') FROM "+
						bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+KFIX__BT_Lock_unlock_position.this.cID_VPOS_KON);
				if (Abgeschlossen.equals("Y"))
				{
					KFIX__BT_Lock_unlock_position.this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
					KFIX__BT_Lock_unlock_position.this.setLayoutData(KFIX__BT_Lock_unlock_position.this.GL_Red);
				}
				else if (Abgeschlossen.equals("N"))
				{
					KFIX__BT_Lock_unlock_position.this.setIcon(E2_ResourceIcon.get_RI("unlocked.png"));
					KFIX__BT_Lock_unlock_position.this.setLayoutData(KFIX__BT_Lock_unlock_position.this.GL_Green);
				}
				else
				{
					throw new myException("Error querying the correct status after toggle !");
				}
			}
			
			public void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException {}
			public Vector<String> get_vSQL_After_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}
			public Vector<String> get_vSQL_Before_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}

		}	

	}