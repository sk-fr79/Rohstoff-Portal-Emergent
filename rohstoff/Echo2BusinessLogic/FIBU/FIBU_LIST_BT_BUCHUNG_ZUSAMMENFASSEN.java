package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_VERRECH_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_VERRECH_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import echopointng.Separator;


public class FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;

	public FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("buchungen_zusammen.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.oNaviList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BUCHUNG_ZUSAMMENFASSEN"));
		this.add_GlobalValidator(new ownValidatorCheckWaehrungsGleichheit());
		
		this.setToolTipText(new MyE2_String("Mehrere Buchungen zu Buchungsblock zusammenfassen").CTrans());
		this.add_IDValidator(new ownValidator());
		
		
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_FIBU)	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_FIBU  recFibu = new RECORD_FIBU(cID_FIBU);
			
			if (recFibu.is_BUCHUNG_GESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Geschlossene Buchung: Keine weitere Eingabe möglich !"));
				return oMV; 
			}
			
			if (recFibu.is_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Stornierte Buchung: Keine weitere Eingabe möglich !"));
				return oMV; 
			}
			
			
			return oMV;
		}
		
	}
	
	
	
	//2012-03-15: neuer validierer, der aufpasst, dass keine Buchungen zusammengefasst werden, die unterschiedliche
	//            waehrungssysmbole enthalten
	private class ownValidatorCheckWaehrungsGleichheit extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN oThis = FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size() >= 2)   //die anderen faelle werden sowieso abgefangen
			{
				RECLIST_FIBU  rlFibu = new RECLIST_FIBU();
				
				for (String cID_Fibu: vIDs)
				{
					RECORD_FIBU  oRec = new RECORD_FIBU(cID_Fibu);
					rlFibu.ADD(oRec, true);
				}
				
				//jetzt pruefen, ob mehrere waehrungen vorkommen
				VectorSingle  vS_Wahrungen = new VectorSingle();
				vS_Wahrungen.addAll(rlFibu.get_WAEHRUNG_FREMD_hmString_UnFormated("").values());
				
				if (vS_Wahrungen.size()>1)    //dann muss geprueft werden
				{
					if (vS_Wahrungen.size()==2)
					{
						RECLIST_FIBU_VERRECH_WAEHRUNG  recListVerrech = new RECLIST_FIBU_VERRECH_WAEHRUNG("SELECT * FROM "+bibE2.cTO()+".JT_FIBU_VERRECH_WAEHRUNG");
						
						boolean bAllowed = false;
						
						for (RECORD_FIBU_VERRECH_WAEHRUNG  recFibV: recListVerrech.values())
						{
							if (new ownRECORD_FIBU_VERRECH_WAEHRUNG(recFibV).CheckMatch(vS_Wahrungen.get(0), vS_Wahrungen.get(1)))   // dann eine kombination, die das zulaesst
							{
								bAllowed = true;
								break;
							}
						}
						
						if (!bAllowed)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es sollen zwei verschiedene Währungen zusammengefasst werden - da ist verboten !!")));
						}
					}
					else
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es sollen mehrere Währungen zusammengefasst werden - da ist verboten !!")));
					}
				}
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
		
		
		private class ownRECORD_FIBU_VERRECH_WAEHRUNG extends RECORD_FIBU_VERRECH_WAEHRUNG
		{

			public ownRECORD_FIBU_VERRECH_WAEHRUNG(RECORD_FIBU_VERRECH_WAEHRUNG recordOrig)
			{
				super(recordOrig);
			}

			public boolean CheckMatch(String cWaehrung1, String cWaehrung2) throws myException
			{
				if (       
					 (	this.get_WAEHRUNG1_cUF_NN("").trim().equals(S.NN(cWaehrung1.trim())) &&
					    this.get_WAEHRUNG2_cUF_NN("").trim().equals(S.NN(cWaehrung2.trim()))
					 )
					 ||
					 (	this.get_WAEHRUNG2_cUF_NN("").trim().equals(S.NN(cWaehrung1.trim())) &&
						this.get_WAEHRUNG1_cUF_NN("").trim().equals(S.NN(cWaehrung2.trim()))
					 )
				   )
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
	}
	
	
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN oThis = FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size() < 2)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens 2 Buchungen auswählen  !!"));
				return;
			}
			
			bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDs));
			
			if (bibMSG.get_bIsOK())
			{
				RECLIST_FIBU reclistFIBU = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_FIBU IN ("+bibALL.Concatenate(vIDs, ",", "-1")+") ORDER BY BUCHUNGSBLOCK_NR");
				
				//jetzt die reclist nach buchungsbloecken durchsuchen, die aus mehreren einheiten bestehen, wenn es einen solchen gibt, dann an diesen anhaengen
				VectorSingle vEinzelBloecke = new VectorSingle();
				VectorSingle vMehrfachBloecke = new VectorSingle();
				VectorSingle vID_ADRESSES = new VectorSingle();
				
				for (int i=0;i<reclistFIBU.get_vKeyValues().size();i++)
				{
					MyInteger intTest = new MyInteger(bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_FIBU WHERE BUCHUNGSBLOCK_NR="+reclistFIBU.get(i).get_BUCHUNGSBLOCK_NR_cUF()));
					
					if (intTest == null || intTest.get_oInteger()==null)
					{
						throw new myException(this,"System-Error: unknown status (1)!!");
					}
					
					vID_ADRESSES.add(reclistFIBU.get(i).get_ID_ADRESSE_cUF());
					
					if (intTest.get_iValue()==1)
					{
						vEinzelBloecke.add(reclistFIBU.get(i).get_BUCHUNGSBLOCK_NR_cUF());
					}
					else
					{
						vMehrfachBloecke.add(reclistFIBU.get(i).get_BUCHUNGSBLOCK_NR_cUF());
					}
				}
				
				//vorab: nur eine adresse in der auswahl, sonst macht das keinen sinn
				if (vID_ADRESSES.size()>1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es können nur Buchungen zu EINER Firma zusammengefasst werden !"));
					return;
				}
				
				String cBUCHUNGSBLOCK_ZIEL = null;
				if (vMehrfachBloecke.size()>1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("In der Auswahl sind mehrere zugeordnete Blöcke !!"));
					return;
				}
				else if (vMehrfachBloecke.size()==1)
				{
					cBUCHUNGSBLOCK_ZIEL=vMehrfachBloecke.get(0);
				}
				else
				{
					//dann wird die erste (kleinste) einfachbuchungsblock-nummer benutzt
					cBUCHUNGSBLOCK_ZIEL=vEinzelBloecke.get(0);
				}
				
				if (cBUCHUNGSBLOCK_ZIEL==null)
				{
					throw new myException(this,"System-Error: unknown status (2)!!");
				}

				
				//2012-02-10: weitere pruefung der forderungsverechnungs-stati: wird sowohl ein Beleg mit negativer als auch einer mit positiver endsumme gefunden 
				//            und ist keine Forderungsverrechnungsvereinbarung vom  FORDERUNGSVERRECHNUNG_ZUSTIMMUNG;
				// 																	FORDERUNGSVERRECHNUNG_ZUSTIMMUNG_KONTOKORRENT_ABREDE	
				//dann muss der Anwender gewarnt werden in einem Popup
				
				boolean bForderungenVorhanden = false;
				boolean bGuthabenVorhanden = false;
				
				for (RECORD_FIBU recFibu: reclistFIBU.values())
				{
					RECORD_VKOPF_RG recVKOPF_RG = recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();

					//2012-03-07: bugfix
					if (recVKOPF_RG != null)
					{
						//storno-gegen-belege werden behandelt wie zahlungsein- oder ausgaenge und nicht wie rechnungen/gutschriften
						if (recVKOPF_RG.get_ID_VKOPF_RG_STORNO_VORGAENGER_lValue(new Long(-1)).longValue()<=0)
						{
							if (recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
							{
								if 		(recFibu.get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0) 
								{
									bForderungenVorhanden = true;
								}
								else if (recFibu.get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)<0)
								{
									bGuthabenVorhanden = true;
								}
							}
							if (recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT))
							{
								if 		(recFibu.get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0)
								{
									bGuthabenVorhanden = true;
								}
								else if (recFibu.get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)<0)
								{
									bForderungenVorhanden = true;
								}
							}
						}
					}
				}
				
				boolean bOK = true;
				
				boolean bIstVerrechnung = (bForderungenVorhanden && bGuthabenVorhanden);
				
				if (bIstVerrechnung)   //warnung nur bei verrechnungen
				{
					bOK = false;
					String cForderungsVerrechnung = reclistFIBU.get(0).get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_FORDERUNGSVERRECHNUNG_cUF_NN("");
					if (cForderungsVerrechnung.equals(myCONST.FORDERUNGSVERRECHNUNG_ZUSTIMMUNG) ||cForderungsVerrechnung.equals(myCONST.FORDERUNGSVERRECHNUNG_ZUSTIMMUNG_KONTOKORRENT_ABREDE))
					{
						bOK = true;
					}
				}
				
				if (!bOK)
				{
					new ownWarnPopup(cBUCHUNGSBLOCK_ZIEL, vIDs);
				}
				else
				{
					this.starteBuchung(cBUCHUNGSBLOCK_ZIEL, vIDs);
				}
			}
		}
		
		
		
		private void starteBuchung(String cBUCHUNGSBLOCK_ZIEL, Vector<String> vIDs ) throws myException
		{
			FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN oThis = FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN.this;

			String cSQL_Statement = "UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNGSBLOCK_NR="+cBUCHUNGSBLOCK_ZIEL+" WHERE  ID_FIBU IN ("+bibALL.Concatenate(vIDs, ",", "-1")+")";
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(cSQL_Statement), true));
			
			if (bibMSG.get_bIsOK())
			{
				new FIBU__BUCHUNGS_CONTAINER(vIDs.get(0), oThis.oNaviList,false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Selektierte Buchungen wurde im Block ",true,cBUCHUNGSBLOCK_ZIEL,false," zusammengefasst !",true)));
			}
			
			oThis.oNaviList._REBUILD_ACTUAL_SITE("");
			oThis.oNaviList.Check_ID_IF_IN_Page(vIDs);
		}

		
		

		private class ownWarnPopup extends E2_BasicModuleContainer
		{
			private String 				cBUCHUNGSBLOCK_ZIEL = null;
			private Vector<String> 		vIDs = null; 
			
			public ownWarnPopup(String BUCHUNGSBLOCK_ZIEL, Vector<String> v_IDs ) throws myException
			{
				super();
				
				cBUCHUNGSBLOCK_ZIEL = BUCHUNGSBLOCK_ZIEL;
				vIDs = v_IDs;
				
				MyE2_Button oButtonOK =  		new MyE2_Button(new MyE2_String("Trotzdem ausführen"));
				MyE2_Button oButtonAbbruch =  	new MyE2_Button(new MyE2_String("Abbrechen"));

				MyE2_Grid oGridInnen = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				GridLayoutData  oGLAlarmBackground1 = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_10_5_10, new E2_ColorAlarm(), 1);
				GridLayoutData  oGLAlarmBackground2 = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_10_5_10);
				
				oGridInnen.add(new MyE2_Label(new MyE2_String("Warnung !!"),new E2_FontBold(6)), oGLAlarmBackground1);
				oGridInnen.add(new MyE2_Label(new MyE2_String("Diese Buchung beinhaltet eine Verrechnung"),new E2_FontBold(2)), oGLAlarmBackground2);
				oGridInnen.add(new MyE2_Label(new MyE2_String("ohne Vereinbarung mit dem Kunden !"),new E2_FontBold(2)),oGLAlarmBackground2);
				
				oGridInnen.add(new Separator(), E2_INSETS.I_5_10_5_10);
				
				oGridInnen.add(new E2_ComponentGroupHorizontal(0, oButtonOK, oButtonAbbruch, E2_INSETS.I_0_0_10_0), oGLAlarmBackground2);
				this.add(oGridInnen, E2_INSETS.I_5_5_5_5);
				
				oButtonOK.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						ownWarnPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						ownActionAgent.this.starteBuchung(ownWarnPopup.this.cBUCHUNGSBLOCK_ZIEL, ownWarnPopup.this.vIDs);
					}
				});				
				
				oButtonAbbruch.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						ownWarnPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				});				
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(400), new MyE2_String("Warnung! evtl. unberechtigte Verrechnung !"));
				
			}
			
		}
	}
	
	
	
	
	
}
