package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__ALL.CopyTyp;


/*
 * drop-down element zum kopieren von Fahrplaneintraegen
 */
public class FP_DROPDOWN_COPYS extends MyE2_SelectField
{

	private E2_NavigationList oNavilist = null;
	
	private CopyTyp copyTyp = null; 

	
	
	public FP_DROPDOWN_COPYS(E2_NavigationList Navilist, CopyTyp  p_copyTyp) throws myException
	{
		super();
		this.oNavilist = Navilist;
		this.copyTyp = p_copyTyp;
		
		String[][] cWerte = {{"-",""},{"1 x","1"},{"2 x","2"},{"3 x","3"},{"4 x","4"},{"5 x","5"}};
		
		this.set_ListenInhalt(cWerte,false);
		this.add_oActionAgent(new ownActionAgent());
		this.add_IDValidator(new FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("FAHRT_KOPIEREN"));
		
		//aenderung 2010-12-22: keine kopie von inaktiven adressen
		this.add_IDValidator(new ownValidatior());
	}
	
	
	
	//aenderung 2010-12-22: keine kopie von inaktiven adressen
	private class ownValidatior extends valid_KopiereNurBelegeMitAktiveAdressen
	{

		@Override
		public VectorSingle SammleAdressIDs(String cID_BelegToCopy)		throws myException 
		{
			VectorSingle vRueck = new VectorSingle();
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_BelegToCopy);
					
			vRueck.add(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_SPEDITION_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_START_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("0"));

			RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = 
				recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			
			if (reclistOrte != null)
			{
				Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iterOrte = reclistOrte.values().iterator();
				
				while (iterOrte.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = iterOrte.next();
					
					vRueck.add(recOrt.get_ID_ADRESSE_cUF_NN("0"));
				}
			}
			return vRueck;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException 
		{
			return null;
		}
		
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			FP_DROPDOWN_COPYS oDD = FP_DROPDOWN_COPYS.this;
			
			
			try
			{
				Vector<String> vSelectedIDs = oDD.oNavilist.get_vSelectedIDs_Unformated();
				
				if (vSelectedIDs.size()!=1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau eine Fahrt auswählen !"));
					oDD.setSelectedIndex(0);    // wieder zurueckstellen
					return;
				}
				
				String cID_Fuhre_SelectedToCopy = (String)vSelectedIDs.get(0);
				
				bibMSG.add_MESSAGE(oDD.valid_IDValidation(bibALL.get_Vector(cID_Fuhre_SelectedToCopy)));

				if (bibMSG.get_bIsOK())
				{
					if (oDD.get_ActualWert().equals(""))
						return;
			
					int iMulti = new Integer(oDD.get_ActualWert()) .intValue();

					
					Vector<String> vSQLStack = new Vector<String>();
					
					Vector<String> v_ids_new = new Vector<String>();
					
					
					for (int i=0;i<iMulti;i++) 	{
						
						String id_next = bibDB.get_NextSequenceValueOfTable(_TAB.vpos_tpa_fuhre);

						v_ids_new.add(id_next);
						
						
						FP_SQLCopyFuhre      oCopyFuhre = new FP_SQLCopyFuhre(cID_Fuhre_SelectedToCopy,FP_DROPDOWN_COPYS.this.copyTyp,id_next);  
						String cInsert = oCopyFuhre.get_cINSERT_String();
						
						vSQLStack.add(cInsert);
						vSQLStack.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET EAN_CODE_FP='FU-'||TO_CHAR(ID_VPOS_TPA_FUHRE) " +
											" WHERE ID_VPOS_TPA_FUHRE="+id_next);
					}

					//die maximale id_vpos_tpa_fuhre feststellen vor der operation und die neuen nach der operation zusaetzlich einblende
//					String cID_VPOS_TPA_FUHRE_MAX = bibDB.EinzelAbfrage("SELECT MAX(ID_VPOS_TPA_FUHRE) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE");
					
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStack, true));
					if (bibMSG.get_bIsOK())
					{
						MyE2_String cHelp = new MyE2_String("Zugefügte Fuhren: ");
						cHelp.addUnTranslated(""+iMulti);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(cHelp), false);
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Fuhren-Kopieren !"));
					}
	
					/*
					 * dann feststellen, welche zu der gruppe gehören und alle auf der aktuellen seite einblenden
					 */
//					String[][] cGroupIDs = bibDB.EinzelAbfrageInArray("SELECT ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE>"+cID_VPOS_TPA_FUHRE_MAX);
////					for (int i=0;i<cGroupIDs.length;i++)
//					{
//						oDD.oNavilist.ADD_NEW_ID_TO_ALL_VECTORS(cGroupIDs[i][0]);
//					}
					oDD.oNavilist.ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(v_ids_new);
					
					oDD.oNavilist.BUILD_ComponentMAP_Vector_from_ActualSegment();
					oDD.oNavilist.FILL_GRID_From_InternalComponentMAPs(true, true);
					
					oDD.setSelectedIndex(0);    // wieder zurueckstellen
					
					//kopien und den alten satz anhaken, die neuen markieren 
					Vector<String>  v_id_to_mark = new Vector<String>();
					v_id_to_mark.add(cID_Fuhre_SelectedToCopy);
					v_id_to_mark.addAll(v_ids_new);
					
					oDD.oNavilist.set_CheckBox_To_AllIdsInVector(v_id_to_mark);
					oDD.oNavilist.Mark_ID_IF_IN_Page(v_ids_new);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die originalen und zugefügten Fuhren sind angehakt, die neuen rot markiert !")));

				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FP_DROPDOWN_COPYS:ownActionAgent:doAction: Error"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FP_DROPDOWN_COPYS:ownActionAgent:doAction: Unknown Error: "+ex.getLocalizedMessage()));
			}
			
		}
		
	}
	
	
}
