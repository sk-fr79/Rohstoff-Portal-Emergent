package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7_SteuerTabelle;

public class FU__ListButton_SPRUNGBUTTONS_EX_FUHRE extends MyE2_PopUpMenue
{

	private String    			cID_VPOS_TPA_FUHRE = null;
	
	public FU__ListButton_SPRUNGBUTTONS_EX_FUHRE(String ID_VPOS_TPA_FUHRE) throws myException
	{
		//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
		super(E2_ResourceIcon.get_RI("kompass_popup.png"),E2_ResourceIcon.get_RI("kompass_popup__.png"),false);

		this.cID_VPOS_TPA_FUHRE = ID_VPOS_TPA_FUHRE;
		
		this.setToolTipText(new MyE2_String("Verschiedene Sprungmethoden aus der Fuhre in andere Module").CTrans());

		this.addButton(new ownSprungButtonZuFreiePositionen(), true);

		this.addButton(new ownButtonSprungIn_EK(), true);
		this.addButton(new ownButtonSprungIn_VK(), true);

		//2012-05-22: sprung zu angeboten
		this.addButton(new ownButtonSprungIn_AAngebot(), true);
		this.addButton(new ownButtonSprungIn_VAngebot(), true);

		this.addButton(new ownSprungButtonZuTPA(), true);
		this.addButton(new ownSprungButtonZuWiegeschein(), true);
		
		this.addButton(new ownSprungButtonZuAllenFuhrenDieserEK_POS(),true);
		this.addButton(new ownSprungButtonZuAllenFuhrenDieserVK_POS(),true);
		
		//2012-08-07: sprung in beanstandungsmeldungen
		this.addButton(new ownButtonSprungIn_FuhrenBAM(),true);
		
		if (ENUM_MANDANT_DECISION.AH7_USE_STEUERTABELLE.is_YES()) {
			//2017-11-21: sprung zu den steuertabellen-eintraegen
			this.addButton(new ownSprungButtonZuAH7_SteuerListe(),true);
		}
		
	}
	

	private class ownButtonSprungIn_EK extends MyE2_Button
	{
		public ownButtonSprungIn_EK() throws myException
		{
			super(new MyE2_String("Sprung: Einkaufs-Kontrakte"));
			
			this.setToolTipText(new MyE2_String("Zeigt alle Einkaufskontrakte zur Fuhre an").CTrans());
			
			this.add_oActionAgent(new ownActionJumpToKontrakte("EK"));
		}
	}
	
	
	private class ownButtonSprungIn_VK extends MyE2_Button
	{
		public ownButtonSprungIn_VK() throws myException
		{
			super(new MyE2_String("Sprung: Verkaufs-Kontrakte"));
			this.setToolTipText(new MyE2_String("Zeigt alle Verkaufskontrakte zur Fuhre an").CTrans());
			this.add_oActionAgent(new ownActionJumpToKontrakte("VK"));
		}
	}
	
	
	
	private class ownActionJumpToKontrakte extends XX_ActionAgentJumpToTargetList
	{
		private String 			cDEF_QUELLE_ZIEL = null;
		
		
		public ownActionJumpToKontrakte(String DEF_QUELLE_ZIEL) throws myException 
		{
			super(
					get_kontrakt_modul(DEF_QUELLE_ZIEL)
				  , 
				  (DEF_QUELLE_ZIEL.equals("EK")
					?
					"EK-Kontrakte"
					 :
				    "VK-Kontrakte"
				   )
			);
	
			this.cDEF_QUELLE_ZIEL=DEF_QUELLE_ZIEL;
		}

		
		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vTargetList.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}

		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
			Vector<String> vID_VKOPF_KON = new Vector<String>();
			if (this.cDEF_QUELLE_ZIEL.equals("EK"))
			{
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek()!=null)
				{
					vID_VKOPF_KON.add(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_VKOPF_KON_cUF());
				}
			}
			else
			{
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk()!=null)
				{
					vID_VKOPF_KON.add(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_VKOPF_KON_cUF());
				}
			}
			
			RECLIST_VPOS_TPA_FUHRE_ORT  recListFO = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			if (recListFO.get_vKeyValues().size()>0)
			{
				for (int i=0;i<recListFO.get_vKeyValues().size();i++)
				{
					if (recListFO.get(i).get_DEF_QUELLE_ZIEL_cUF_NN("").equals(this.cDEF_QUELLE_ZIEL))
					{
						if (recListFO.get(i).get_UP_RECORD_VPOS_KON_id_vpos_kon()!=null)
						{
							vID_VKOPF_KON.add(recListFO.get(i).get_UP_RECORD_VPOS_KON_id_vpos_kon().get_ID_VKOPF_KON_cUF());
						}
					}
				}
			}
			
			return vID_VKOPF_KON;
		}
	}
	
	
	
	
	private class ownSprungButtonZuFreiePositionen extends MyE2_Button
	{
		public ownSprungButtonZuFreiePositionen() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Freie Positionen"));
			this.setToolTipText(new MyE2_String("Zeigt alle Rechnungsposition zu der ausgewählten Fuhre").CTrans());
	
			this.add_oActionAgent(new ownActionJumpToFreiePos());
		}

		private class ownActionJumpToFreiePos extends XX_ActionAgentJumpToTargetList
		{
			
			public ownActionJumpToFreiePos() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN, "Freie Positionen");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				HashMap<String, String> hmIDVPOS_RG = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(null,"ID_ADRESSE",true).get_ID_VPOS_RG_hmString_UnFormated("");
				return bibALL.get_vBuildKeyVectorFromHashmap(hmIDVPOS_RG);
			}
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
			
		}
	}



	
	
	private class ownSprungButtonZuTPA extends MyE2_Button
	{
		public ownSprungButtonZuTPA() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Transportauftrag"));
			this.setToolTipText(new MyE2_String("Sprung zum zugehörigen Transportauftrag").CTrans());
	
			this.add_oActionAgent(new ownActionJumpToTPA());
		}

		private class ownActionJumpToTPA extends XX_ActionAgentJumpToTargetList
		{
			
			public ownActionJumpToTPA() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_TPA_LIST, "Transportauftrag");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				
				Vector<String> vRueck = new Vector<String>();
				
				if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
				{
					vRueck.add(recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ID_VKOPF_TPA_cUF_NN("-1"));
				}
				return vRueck;
			}
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
		}
	}



	
	
	private class ownSprungButtonZuAllenFuhrenDieserEK_POS extends MyE2_Button
	{
		public ownSprungButtonZuAllenFuhrenDieserEK_POS() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Alle Fuhren gleicher EK-Kontrakt-Pos"));
			this.setToolTipText(new MyE2_String("Sprung zu allen Fuhren mit der gleichen EK-Kontrakt-Pos").CTrans());
	
			this.add_oActionAgent(new ownActionZuAllenFuhrenDieserEK_POS());
		}

		private class ownActionZuAllenFuhrenDieserEK_POS extends XX_ActionAgentJumpToTargetList
		{
			
			public ownActionZuAllenFuhrenDieserEK_POS() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Zeige Alle Fuhren gleicher EK-Kontrakt-Position");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				
				Vector<String> vRueck = new Vector<String>();
				
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek()!=null)
				{
					vRueck.addAll(__pruefe_kontrakt(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek()));
				}
				
				
				//jetzt noch die orte untersuchen
				RECLIST_VPOS_TPA_FUHRE_ORT  reclist_Ort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK'", null, true);
				
				for (int i=0;i<reclist_Ort.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT  recOrt = reclist_Ort.get(i);
					
					if (recOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon()!=null)
					{
						vRueck.addAll(__pruefe_kontrakt(recOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon()));
					}
				}
				
				return vRueck;
			}
			
			
			
			private Vector<String> __pruefe_kontrakt(RECORD_VPOS_KON recKonEK) throws myException
			{
				Vector<String> vRueck = new Vector<String>();
				
				RECLIST_VPOS_TPA_FUHRE  	reclistVPosTPA_FUHRE = 		
					recKonEK.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' ",null,true);
				
				vRueck.addAll(reclistVPosTPA_FUHRE.get_vKeyValues());
				
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistVPosTPA_FUHRE_ORT = 	
					recKonEK.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'", null, true);
				
				for (int i=0;i<reclistVPosTPA_FUHRE_ORT.get_vKeyValues().size();i++)
				{
					vRueck.add(reclistVPosTPA_FUHRE_ORT.get(i).get_ID_VPOS_TPA_FUHRE_cUF());
				}
				
				return vRueck;
				
			}
			
			
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
			
		}
	}


	
	private class ownSprungButtonZuAllenFuhrenDieserVK_POS extends MyE2_Button
	{
		public ownSprungButtonZuAllenFuhrenDieserVK_POS() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Alle Fuhren gleicher VK-Kontrakt-Pos"));
			this.setToolTipText(new MyE2_String("Sprung zu allen Fuhren mit der gleichen VK-Kontrakt-Pos").CTrans());
	
			this.add_oActionAgent(new ownActionZuAllenFuhrenDieserVK_POS());
		}

		private class ownActionZuAllenFuhrenDieserVK_POS extends XX_ActionAgentJumpToTargetList
		{
			
			public ownActionZuAllenFuhrenDieserVK_POS() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Zeige Alle Fuhren gleicher VK-Kontrakt-Position");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				
				Vector<String> vRueck = new Vector<String>();
				
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk()!=null)
				{
					vRueck.addAll(__pruefe_kontrakt(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk()));
				}
				
				
				//jetzt noch die orte untersuchen
				RECLIST_VPOS_TPA_FUHRE_ORT  reclist_Ort = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK'", null, true);
				
				for (int i=0;i<reclist_Ort.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT  recOrt = reclist_Ort.get(i);
					
					if (recOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon()!=null)
					{
						vRueck.addAll(__pruefe_kontrakt(recOrt.get_UP_RECORD_VPOS_KON_id_vpos_kon()));
					}
				}
				
				return vRueck;
			}
			
			
			
			private Vector<String> __pruefe_kontrakt(RECORD_VPOS_KON recKonVK) throws myException
			{
				Vector<String> vRueck = new Vector<String>();
				
				RECLIST_VPOS_TPA_FUHRE  	reclistVPosTPA_FUHRE = 		
					recKonVK.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' ",null,true);
				
				vRueck.addAll(reclistVPosTPA_FUHRE.get_vKeyValues());
				
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistVPosTPA_FUHRE_ORT = 	
					recKonVK.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'", null, true);
				
				for (int i=0;i<reclistVPosTPA_FUHRE_ORT.get_vKeyValues().size();i++)
				{
					vRueck.add(reclistVPosTPA_FUHRE_ORT.get(i).get_ID_VPOS_TPA_FUHRE_cUF());
				}
				
				return vRueck;
				
			}
			
			
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
			
		}
	}


	/**
	 * 
	 * @author martin
	 *
	 */
	private class ownSprungButtonZuWiegeschein extends MyE2_Button
	{
		public ownSprungButtonZuWiegeschein() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Wiegekarten"));
			this.setToolTipText(new MyE2_String("Sprung zu zugehörigen Wiegekarten").CTrans());
	
			this.add_oActionAgent(new ownActionJumpToWiegescheinen());
		}

		private class ownActionJumpToWiegescheinen extends XX_ActionAgentJumpToTargetList
		{
			
			public ownActionJumpToWiegescheinen() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE, "Wiegekarten");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				RECLIST_WIEGEKARTE  oRecListWiegeKarte = new RECLIST_WIEGEKARTE("SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE="+FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				Vector<String> vRueck = new Vector<String>();
				vRueck.addAll(oRecListWiegeKarte.get_ID_WIEGEKARTE_hmString_UnFormated("").values());
				return vRueck;
			}
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine Wiegekarten zu dieser Fuhre !",true)));
				}
				return oMV;
			}
		}
	}


	
	
	//2012-05-22: weitere sprungfunktion in ek- und vk-angebote, die in der fuhre erwaehnt werden
	private class ownButtonSprungIn_AAngebot extends MyE2_Button
	{
		public ownButtonSprungIn_AAngebot() throws myException
		{
			super(new MyE2_String("Sprung: Abnahme-Angebote"));
			
			this.setToolTipText(new MyE2_String("Zeigt alle Abnahmeangebote zur Fuhre an").CTrans());
			
			this.add_oActionAgent(new ownActionJumpToAngebot("EK"));
		}
	}
	
	
	private class ownButtonSprungIn_VAngebot extends MyE2_Button
	{
		public ownButtonSprungIn_VAngebot() throws myException
		{
			super(new MyE2_String("Sprung: Verkaufs-Angebote"));
			this.setToolTipText(new MyE2_String("Zeigt alle Verkaufsangebote zur Fuhre an").CTrans());
			this.add_oActionAgent(new ownActionJumpToAngebot("VK"));
		}
	}
	
	
	
	private class ownActionJumpToAngebot extends XX_ActionAgentJumpToTargetList
	{
		private String 			cDEF_QUELLE_ZIEL = null;
		
		
		public ownActionJumpToAngebot(String DEF_QUELLE_ZIEL) throws myException 
		{
			super((DEF_QUELLE_ZIEL.equals("EK")
    				?
				    E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_LIST
				    :
				    E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_LIST
				    )
				  , 
				  (DEF_QUELLE_ZIEL.equals("EK")
					?
					"Abnahme-Angebote"
					 :
				    "Verkaufs-Angebote"
				   )
			);
	
			this.cDEF_QUELLE_ZIEL=DEF_QUELLE_ZIEL;
		}

		
		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vTargetList.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}

		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
			Vector<String> vID_VKOPF_STD = new Vector<String>();
			if (this.cDEF_QUELLE_ZIEL.equals("EK"))
			{
				if (recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_ek()!=null)
				{
					vID_VKOPF_STD.add(recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_ek().get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_ID_VKOPF_STD_cUF());
				}
			}
			else
			{
				if (recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_vk()!=null)
				{
					vID_VKOPF_STD.add(recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_vk().get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_ID_VKOPF_STD_cUF());
				}
			}
			
			RECLIST_VPOS_TPA_FUHRE_ORT  recListFO = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			if (recListFO.get_vKeyValues().size()>0)
			{
				for (int i=0;i<recListFO.get_vKeyValues().size();i++)
				{
					if (recListFO.get(i).get_DEF_QUELLE_ZIEL_cUF_NN("").equals(this.cDEF_QUELLE_ZIEL))
					{
						if (recListFO.get(i).get_UP_RECORD_VPOS_STD_id_vpos_std()!=null)
						{
							vID_VKOPF_STD.add(recListFO.get(i).get_UP_RECORD_VPOS_STD_id_vpos_std().get_ID_VKOPF_STD_cUF());
						}
					}
				}
			}
			
			return vID_VKOPF_STD;
		}
	}
	

	
	//2012-08-07: sprung aus fuhre in die bams einer fuhre
	private class ownButtonSprungIn_FuhrenBAM extends MyE2_Button
	{
		public ownButtonSprungIn_FuhrenBAM() throws myException
		{
			super(new MyE2_String("Sprung: Fuhren-Beanstandungen"));
			this.setToolTipText(new MyE2_String("Zeigt alle Fuhren-Beanstandungen zur Fuhre an").CTrans());
			this.add_oActionAgent(new ownActionJumpToBAM());
		}
	}

	private class ownActionJumpToBAM extends XX_ActionAgentJumpToTargetList
	{
		public ownActionJumpToBAM() throws myException 
		{
			super(E2_MODULNAMES.NAME_MODUL_FBAM_LIST ,"Fuhren-Beanstandungen");
		}

		
		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vTargetList.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}

		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
			Vector<String> vID_FBAM = new Vector<String>();

			if (recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre()!=null)
			{
				vID_FBAM.addAll(recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre().get_vKeyValues());
			}
			
			RECLIST_VPOS_TPA_FUHRE_ORT  recListFO = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			if (recListFO.get_vKeyValues().size()>0)
			{
				for (int i=0;i<recListFO.get_vKeyValues().size();i++)
				{
					if (recListFO.get(i).get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort()!=null)
					{
						vID_FBAM.addAll(recListFO.get(i).get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort().get_vKeyValues());
					}
				}
			}
			
			return vID_FBAM;
		}
	}
	
	
	
	//20171121: sprung auf ah7-varianten
	private class ownSprungButtonZuAH7_SteuerListe extends MyE2_Button
	{
		public ownSprungButtonZuAH7_SteuerListe() throws myException 		{
			super(new MyE2_String("Sprung: AH7-Druck-Steuertabelle"));
			this.setToolTipText(new MyE2_String("Springt zu den Anhang7-Steuerdatensätzen").CTrans());
	
			this.add_oActionAgent(new ownActionJumpToAH7_Steuerliste());
		}

		private class ownActionJumpToAH7_Steuerliste extends XX_ActionAgentJumpToTargetList {
			
			public ownActionJumpToAH7_Steuerliste() throws myException 	{
				super(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE.get_callKey(), "Anhang-7-Druck-Steuertabelle");
			}
			
			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException {
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU__ListButton_SPRUNGBUTTONS_EX_FUHRE.this.cID_VPOS_TPA_FUHRE);
				
				Reclist21_AH7_SteuerTabelle rlSt = new Reclist21_AH7_SteuerTabelle()._fillWithFuhre(recFuhre);
				
				VEK<String> ids = new VEK<String>()._a(rlSt.getVEK_uf(AH7_STEUERDATEI.id_ah7_steuerdatei));
				
				return ids;
			}
			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException {
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				
				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}
			
		}
	}

	
	
	
	
	/** 
	 * @since 06.02.2016
	 * @author sebastien
	 * @param def_quelle_ziel__ek_or_vk
	 * @return
	 * @throws myException
	 */
	private static String get_kontrakt_modul(String def_quelle_ziel__ek_or_vk) throws myException{
		String modulName = "";
		if(ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES()){
			modulName =	def_quelle_ziel__ek_or_vk.equals("EK")? E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG:E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG;
		}else{
			modulName = def_quelle_ziel__ek_or_vk.equals("EK")? E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST:E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST;
		}
		
		return modulName;
	}
	
}
