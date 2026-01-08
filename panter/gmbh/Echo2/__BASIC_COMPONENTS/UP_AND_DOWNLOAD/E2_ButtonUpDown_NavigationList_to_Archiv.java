package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonUpDown_NavigationList_to_Archiv extends MyE2_Button
{
	
	private E2_NavigationList	oNavigationList = null;
	private String 				cMODULKENNER = null;
	
	//2013-10-23: weitere Kenner fuer die Validierung, da es eingelagerte Module mit eigener Upload-funktion geben kann, die aber mit der kennung der muttermaske validiert werden
	private String   			cMODULKENNER_4_VALIDERUNG = null;
	
	
	private E2_ComponentMAP     m_oMap = null;
	
	// falls man einen Medienkenner direkt angeben will
	private String              m_cMedienkenner = null;

	
	
	//2015-01-30: plugin um die elemente in der liste, die die zugeordnete id_email_send - zugehoerigkeit zu erzeugen
	private UP_DOWN_AddOn_COL_ComponentFactory  id_EMAIL_COMPONENT_FACTORY = null;

	
	
	// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
	private Vector<XX_ActionAgentWhenCloseWindow> vCloseActions = new Vector<XX_ActionAgentWhenCloseWindow>();

	
//	//2015-03-26: attachmentseeker zum uebergeben
//	private ES__AttachementSeeker                  es_AttachementSeeker = null;
//	public ES__AttachementSeeker get_es_AttachementSeeker() {
//		return es_AttachementSeeker;
//	}


//	public void set_es_AttachementSeeker(ES__AttachementSeeker esAttachementSeeker) {
//		this.es_AttachementSeeker = esAttachementSeeker;
//	}
	

	
	
//	/**
//	 * Konstruktor für den Knopf der direkt auf die ID innerhalb des Moduls zeigt und nicht über die NavigationList geht.
//	 * Die NavigationList wird für die Ermittlung des Tabellennamen gebraucht 
//	 * Author: manfred
//	 * Date:   12.03.2013
//	 * @param cEntryID
//	 * @param onavigationList
//	 * @param cMODUL_KENNER
//	 */
//	public E2_ButtonUpDown_NavigationList_to_Archiv(E2_ComponentMAP oMap,
//													String 			cMODUL_KENNER ){
//		this(oMap,cMODUL_KENNER,null);
//		
//	}

	
	/**
	 * Konstruktor für den Knopf der direkt auf die ID innerhalb des Moduls zeigt und nicht über die NavigationList geht.
	 * Die NavigationList wird für die Ermittlung des Tabellennamen gebraucht
	 * Falls cMedienkenner angegeben ist, wird der Medienkenner selektiert
	 *  
	 * Author: manfred
	 * Date:   12.03.2013
	 * @param cEntryID
	 * @param onavigationList
	 * @param cMODUL_KENNER
	 * @param cMedienkenner
	 */
	public E2_ButtonUpDown_NavigationList_to_Archiv(	E2_ComponentMAP oMap,
														String 			cMODUL_KENNER ,
														String 			cMedienkenner )
	{
		this(oMap.get_oNavigationList_This_Belongs_to(),cMODUL_KENNER,cMODUL_KENNER, cMedienkenner,null,null);
		this.m_oMap = oMap;
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date   27.03.2013
	 * @param onavigationList
	 * @param cMODUL_KENNER
	 */
	public E2_ButtonUpDown_NavigationList_to_Archiv(	E2_NavigationList 	onavigationList,
														String 				cMODUL_KENNER){
		this(onavigationList,cMODUL_KENNER,cMODUL_KENNER,null,null,null);
	}


//	/**
//	 * 
//	 * @author martin
//	 * @date   30.01.2015
//	 * @param onavigationList
//	 * @param cMODUL_KENNER
//	 * @param idEMAIL_COMPONENT_FACTORY   //2015-01-30: moeglichkeit, auf die komponente ID_EMAIL_SEND einfluss zu nehmen
//	 */
//	public E2_ButtonUpDown_NavigationList_to_Archiv(	E2_NavigationList 	onavigationList,
//														String 				cMODUL_KENNER,
//														UP_DOWN_AddOn_COL_ComponentFactory  idEMAIL_COMPONENT_FACTORY){
//		this(onavigationList,cMODUL_KENNER,cMODUL_KENNER,null,null,idEMAIL_COMPONENT_FACTORY);
//	}


	
	
	/**
	 * 
	 * @author martin
	 * @date   2013-10-23
	 * @param onavigationList
	 * @param cMODUL_KENNER
	 * @param cMODUL_KENNER_4_VALID
	 */
	public E2_ButtonUpDown_NavigationList_to_Archiv(	E2_NavigationList 	onavigationList,
														String 				cMODUL_KENNER,
														String 				cMODUL_KENNER_4_VALID	){
		this(onavigationList,cMODUL_KENNER,cMODUL_KENNER_4_VALID,null,null,null);
	}


	
	
	
	
	/**
	 * Falls cMedienkenner angegeben ist, wird der Medienkenner selektiert
	 * 
	 * @author manfred
	 * @date   27.03.2013
	 * @param onavigationList
	 * @param cMODUL_KENNER
	 * @param cMODULKENNER_4_VALID
	 * @param cMedienkenner
	 * @param id_EMAIL_COMPONENT_FACTORY  //2015-01-30: moeglichkeit, auf die komponente ID_EMAIL_SEND einfluss zu nehmen
	 */
	public E2_ButtonUpDown_NavigationList_to_Archiv(	E2_NavigationList 	onavigationList,
														String 				cMODUL_KENNER,
														String    			cMODULKENNER_4_VALID,
														String              cMedienkenner,
														String   			cButtonKenner,
														UP_DOWN_AddOn_COL_ComponentFactory  idEMAIL_COMPONENT_FACTORY)
	{
		super(E2_ResourceIcon.get_RI("attach.png"), E2_ResourceIcon.get_RI("leer.png"));
		
		this.oNavigationList = onavigationList;
		this.cMODULKENNER = cMODUL_KENNER;
		this.cMODULKENNER_4_VALIDERUNG = cMODULKENNER_4_VALID;
		
		this.m_cMedienkenner = cMedienkenner;
		
		String cValidationKenner = S.isFull(this.cMODULKENNER_4_VALIDERUNG)?this.cMODULKENNER_4_VALIDERUNG:this.cMODULKENNER;
		

		this.id_EMAIL_COMPONENT_FACTORY = idEMAIL_COMPONENT_FACTORY;
		
		//2013-05-13: martin: neue moeglichkeit, den validierungskenner mitzugeben
		if (S.isFull(cValidationKenner)) {
			if (cButtonKenner!=null) {
				this.add_GlobalValidator(new E2_ButtonAUTHValidator(cValidationKenner,cButtonKenner));
			} else {
				this.add_GlobalValidator(new E2_ButtonAUTHValidator(cValidationKenner,"ZUSATZDATEIEN"));
			}
		}
		
		this.setToolTipText(new MyE2_String("Zusatzdateien für Dateiarchiv hoch/runterladen. Archiv-Bereich").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		
	}

	
	
	public UP_DOWN_AddOn_COL_ComponentFactory get_id_EMAIL_COMPONENT_FACTORY() {
		return id_EMAIL_COMPONENT_FACTORY;
	}




	public void set_id_EMAIL_COMPONENT_FACTORY(UP_DOWN_AddOn_COL_ComponentFactory idEMAIL_COMONENT_FACTORY) {
		this.id_EMAIL_COMPONENT_FACTORY = idEMAIL_COMONENT_FACTORY;
	}



	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		
		public ownActionAgent()
		{
			super();
		}



		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Vector<String> vIDs_Selected_Unformated = null;
			
			if (m_oMap != null && m_oMap.get_oInternalSQLResultMAP() != null){
				// anlegen Vektors
				vIDs_Selected_Unformated = new Vector<String>();
				
				// ermitteln der ID des Datensatzes und füllen des Vektors
				String sID = m_oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				if (sID != null){
					vIDs_Selected_Unformated.add(sID);
				}
				
			} else {
				// ermitteln des Vektors
				vIDs_Selected_Unformated = E2_ButtonUpDown_NavigationList_to_Archiv.this.oNavigationList.get_vSelectedIDs_Unformated();
			}
			
			
			if (vIDs_Selected_Unformated.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(new MyE2_String("Um Zusatzdateien zu bearbeiten MUSS GENAU EIN Datensatz ausgewählt werden !!").CTrans()));
			}
			else
			{
				try
				{
					String cID = 			vIDs_Selected_Unformated.get(0);
					String cTableName = 	E2_ButtonUpDown_NavigationList_to_Archiv.this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE();
					
					
					AM_BasicContainer oPopUp = new AM_BasicContainer(		cTableName,
																			cID,
																			E2_ButtonUpDown_NavigationList_to_Archiv.this.cMODULKENNER,
																			E2_ButtonUpDown_NavigationList_to_Archiv.this.cMODULKENNER_4_VALIDERUNG,
																			true,
																			E2_ButtonUpDown_NavigationList_to_Archiv.this.m_cMedienkenner,
																			E2_ButtonUpDown_NavigationList_to_Archiv.this.id_EMAIL_COMPONENT_FACTORY);
					
//					//2015-03-27: hier ein attachmentSeeker uebergeben (falls einer da ist) 
//					if (E2_ButtonUpDown_NavigationList_to_Archiv.this.es_AttachementSeeker!=null) {
//						E2_ButtonUpDown_NavigationList_to_Archiv.this.es_AttachementSeeker.init_with_archivInfos(
//								new Archiver_Normalized_Tablename(cTableName).get_TableBaseName(),
//								cID);
//						oPopUp.set_attachMentSeeker(E2_ButtonUpDown_NavigationList_to_Archiv.this.es_AttachementSeeker);
//					}
					
					
					// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
					if (E2_ButtonUpDown_NavigationList_to_Archiv.this.get_vActionAgentWhenCloseWindow().size()>0) {
						for (XX_ActionAgentWhenCloseWindow action: E2_ButtonUpDown_NavigationList_to_Archiv.this.get_vActionAgentWhenCloseWindow()) {
							oPopUp.add_CloseActions(action);
						}
					}
					
					/*
					 * 2015-01-30: falls in der liste die komponente der Spalte ID_EMAIL_SEND eine aktion bekommen soll, dann wird hier die
					 * factory-klasse, die die jeweilige komponente erzeugt, uebergeben
					 */
					if (E2_ButtonUpDown_NavigationList_to_Archiv.this.get_id_EMAIL_COMPONENT_FACTORY()!=null) {
						oPopUp.set_id_EMAIL_COMONENT_FACTORY(E2_ButtonUpDown_NavigationList_to_Archiv.this.get_id_EMAIL_COMPONENT_FACTORY());
					}
					
					oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Download-Window: "));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
				
			}
		}

	}






	// closeactions werden an die klasse E2_PopUp_For_UP_AND_DOWN_FILES weitergegeben, wenn hier welche sind
	public Vector<XX_ActionAgentWhenCloseWindow> get_vActionAgentWhenCloseWindow() 	{
		return vCloseActions;
	}


	
}
