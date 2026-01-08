package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE extends MyE2_PopUpMenue
{


	public FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE() throws myException
	{
		//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
		super(E2_ResourceIcon.get_RI("kompass_popup.png"),E2_ResourceIcon.get_RI("kompass_popup__.png"),false);

		this.setToolTipText(new MyE2_String("Verschiedene Sprungfunktionen aus der Adresse in andere Module").CTrans());

		this.addButton(new ownSprungButtonZuFreiePositionen()			, true);

		this.addButton(new ownButtonSprungIn_EK()						, true);
		this.addButton(new ownButtonSprungIn_VK()						, true);
		this.addButton(new ownButtonSprungIn_RECH()						, true);
		this.addButton(new ownButtonSprungIn_GUT()						, true);
		this.addButton(new ownSprungButtonZuWiegeschein()				, true);
		this.addButton(new ownSprungButtonZuAllenFuhrenDieserAdresse()	, true);
		this.addButton(new ownButtonSprungIn_Freigabe()					, true);
	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE oPopRueck = null;
		try
		{
			oPopRueck = new FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE();
			oPopRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oPopRueck));
			return oPopRueck;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getMessage());
		}
	}


	/*
	 * enabled wird nur nach der vailiderung entschieden, normalerweise ist der button immer enabled
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		boolean enabled = this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		this.setEnabled(enabled);
		if (enabled)
			this.setToggleIcon(this.get_oIconAktiv());
		else
			this.setToggleIcon(this.get_oIconInactiv());

	}




	private String get_cID_ADRESSE_of_this_line()
	{
		return this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
	}


	private class ownButtonSprungIn_EK extends MyE2_Button
	{
		public ownButtonSprungIn_EK() throws myException
		{
			super(new MyE2_String("Sprung: Einkaufs-Kontrakte"));
			this.setToolTipText(new MyE2_String("Zeigt alle Einkaufskontrakte zu dieser Firma an").CTrans());
			this.add_oActionAgent(new ownActionJumpToKontrakte("EK"));
		}
	}


	private class ownButtonSprungIn_VK extends MyE2_Button
	{
		public ownButtonSprungIn_VK() throws myException
		{
			super(new MyE2_String("Sprung: Verkaufs-Kontrakte"));
			this.setToolTipText(new MyE2_String("Zeigt alle Verkaufskontrakte zu dieser Firma an").CTrans());
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
			RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());
			Vector<String> vID_VKOPF_KON = new Vector<String>();
			if (recAdresse.get_DOWN_RECORD_LIST_VKOPF_KON_id_adresse("JT_VKOPF_KON.VORGANG_TYP='"+this.cDEF_QUELLE_ZIEL+"_KONTRAKT'",null,true)!=null)
			{
				vID_VKOPF_KON.addAll(
						recAdresse.get_DOWN_RECORD_LIST_VKOPF_KON_id_adresse(
								"JT_VKOPF_KON.VORGANG_TYP='"+this.cDEF_QUELLE_ZIEL+"_KONTRAKT'",null,true).get_ID_VKOPF_KON_hmString_UnFormated("").values());
			}

			return vID_VKOPF_KON;
		}
	}






	private class ownButtonSprungIn_RECH extends MyE2_Button
	{
		public ownButtonSprungIn_RECH() throws myException
		{
			super(new MyE2_String("Sprung: Rechnungen"));
			this.setToolTipText(new MyE2_String("Zeigt alle Rechnungen zu dieser Firma an").CTrans());
			this.add_oActionAgent(new ownActionJumpTo_RECH_GUT("RECHNUNG"));
		}
	}


	private class ownButtonSprungIn_GUT extends MyE2_Button
	{
		public ownButtonSprungIn_GUT() throws myException
		{
			super(new MyE2_String("Sprung: Gutschriften"));
			this.setToolTipText(new MyE2_String("Zeigt alle Gutschriften zu dieser Firma an").CTrans());
			this.add_oActionAgent(new ownActionJumpTo_RECH_GUT("GUTSCHRIFT"));
		}
	}



	private class ownActionJumpTo_RECH_GUT extends XX_ActionAgentJumpToTargetList
	{
		private String 			cVORGANGSART = null;

		public ownActionJumpTo_RECH_GUT(String VORGANGSART) throws myException 
		{
			super((VORGANGSART.equals("RECHNUNG")
					?
							E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST
							:
								E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST
					)
					, 
					(VORGANGSART.equals("RECHNUNG")
							?
									"Rechnungen"
									:
										"Gutschriften"
							)
					);

			this.cVORGANGSART=VORGANGSART;
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
			RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());
			Vector<String> vID_VKOPF_RG = new Vector<String>();
			if (recAdresse.get_DOWN_RECORD_LIST_VKOPF_RG_id_adresse("JT_VKOPF_RG.VORGANG_TYP='"+this.cVORGANGSART+"'",null,true)!=null)
			{
				vID_VKOPF_RG.addAll(
						recAdresse.get_DOWN_RECORD_LIST_VKOPF_RG_id_adresse("JT_VKOPF_RG.VORGANG_TYP='"+this.cVORGANGSART+"'",null,false).get_ID_VKOPF_RG_hmString_UnFormated("").values());
			}

			return vID_VKOPF_RG;
		}
	}







	private class ownSprungButtonZuFreiePositionen extends MyE2_Button
	{
		public ownSprungButtonZuFreiePositionen() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Freie Positionen"));
			this.setToolTipText(new MyE2_String("Zeigt alle Rechnungsposition zu der ausgewählten Adresse").CTrans());

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
				VectorSingle  vPOS = new VectorSingle();

				//zuerst alle rechnungen/gutschriften
				RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());
				RECLIST_VKOPF_RG rlRG = recAdresse.get_DOWN_RECORD_LIST_VKOPF_RG_id_adresse();
				for (RECORD_VKOPF_RG recRG: rlRG.values())
				{
					vPOS.addAllOnlyNotEmpty(recRG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().get_ID_VPOS_RG_hmString_UnFormated("").values());
				}

				//dann die freien positionen
				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_RG_id_adresse().get_ID_VPOS_RG_hmString_UnFormated("").values());

				return vPOS;
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





	//	private class ownSprungButtonZuTPA extends MyE2_Button
	//	{
	//		public ownSprungButtonZuTPA() throws myException
	//		{
	//			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
	//			super(new MyE2_String("Sprung: Transportauftrag"));
	//			this.setToolTipText(new MyE2_String("Sprung zu zugehörigen Transportaufträgen").CTrans());
	//	
	//			this.add_oActionAgent(new ownActionJumpToTPA());
	//		}
	//
	//		private class ownActionJumpToTPA extends XX_ActionAgentJumpToTargetList
	//		{
	//			
	//			public ownActionJumpToTPA() throws myException 
	//			{
	//				super(E2_MODULNAMES.NAME_MODUL_TPA_LIST, "Transportauftrag");
	//			}
	//			
	//			@Override
	//			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
	//			{
	//				VectorSingle  vPOS = new VectorSingle();
	//				
	//				//zuerst alle rechnungen/gutschriften
	//				RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());
	//				
	//				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_start().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
	//				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_ziel().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
	//				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
	//				
	//				return vPOS;
	//			}
	//			
	//			@Override
	//			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
	//			{
	//				MyE2_MessageVector  oMV = new MyE2_MessageVector();
	//				
	//				if (vTargetList.size()==0)
	//				{
	//					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
	//				}
	//				return oMV;
	//			}
	//		}
	//	}





	private class ownSprungButtonZuAllenFuhrenDieserAdresse extends MyE2_Button
	{
		public ownSprungButtonZuAllenFuhrenDieserAdresse() throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(new MyE2_String("Sprung: Alle Fuhren dieser Adresse"));
			this.setToolTipText(new MyE2_String("Sprung zu allen Fuhren dieser Adresse").CTrans());

			this.add_oActionAgent(new ownActionZuAllenFuhrenDieserEK_POS());
		}

		private class ownActionZuAllenFuhrenDieserEK_POS extends XX_ActionAgentJumpToTargetList
		{

			public ownActionZuAllenFuhrenDieserEK_POS() throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Zeige Alle Fuhren an denen diese Adresse beteiligt ist");
			}

			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				VectorSingle  vPOS = new VectorSingle();

				//zuerst alle rechnungen/gutschriften
				RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());

				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_fremdauftrag().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_spedition().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_start().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_ziel().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());
				vPOS.addAllOnlyNotEmpty(recAdresse.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_adresse().get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("").values());

				return vPOS;
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
				RECLIST_WIEGEKARTE  oRecListWiegeKarte = 
						new RECLIST_WIEGEKARTE("SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE " +
								RECORD_WIEGEKARTE.FIELD__ID_ADRESSE_LIEFERANT+"="+FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line()+" OR "+
								RECORD_WIEGEKARTE.FIELD__ID_ADRESSE_SPEDITION+"="+FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line());

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
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine Wiegekarten zu dieser Adresse !",true)));
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

	/** 
	 * @since 06.04.2018
	 * @author sebastien
	 * @param freigabe sprung
	 * @return
	 * @throws myException
	 */
	private class ownButtonSprungIn_Freigabe extends MyE2_Button
	{
		public ownButtonSprungIn_Freigabe() throws myException {
			super(S.ms("Sprung: Adresse Freigabe"));
			this.setToolTipText(S.ms("Zeigt Freigabe").CTrans());
			this._aaa(new ownSprungFreigabeAction());

		}

		private class ownSprungFreigabeAction extends XX_ActionAgentJumpToTargetList{

			public ownSprungFreigabeAction() throws myException {
				super(
						E2_MODULNAME_ENUM.MODUL.SANKTION_PRUEFUNG_LIST.get_callKey(), 
						E2_MODULNAME_ENUM.MODUL.SANKTION_PRUEFUNG_LIST.get_userInfo().toString()
						);
			}

			@Override
			public MyE2_MessageVector OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException {
				MyE2_MessageVector  oMV = new MyE2_MessageVector();

				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine Freigabe Bedingung zu dieser Adresse !",true)));
				}
				return oMV;
			}

			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException {
				VEK<String> vFreigabe = new VEK<String>();

				String id_hauptadresse = FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE.this.get_cID_ADRESSE_of_this_line();

				RecList21 recList_all_adresse =new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(id_hauptadresse)).getMainEmployeeAndStoreAdresses();

				for(Rec21 rec_adresse: recList_all_adresse) {
					RecList21 rec_ = rec_adresse.get_down_reclist21(SANKTION_PRUEFUNG.id_adresse, "", "");
					
					
					//2018-10-10: fehlerkorrektur fuer Sebastien: Fehler: falls die adresse bereits einmal freigegeben wurde, sind mehr als ein zieleintrag vorhanden !!
					//alt, falsch:
//					if(rec_.size()==1) {
//						vFreigabe._a(rec_.get(0).get_ufs_dbVal(SANKTION_PRUEFUNG.id_sanktion_pruefung));
//					}
					if(rec_.size()>0) {
						for (Rec21 r: rec_) {
							vFreigabe._a(r.get_ufs_dbVal(SANKTION_PRUEFUNG.id_sanktion_pruefung));
						}
					}

					
				}

				return vFreigabe;
			}
		}
	}
}
