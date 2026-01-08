package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.Vector;

import com.sun.org.omg.CORBA._IDLTypeStub;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_MailHelper;


/***
 * Button zum Versenden von Mails 
 * @author manfred
 *
 */
public class WF_SIMPLE_BT_SendMail extends MyE2_Button {

		private String m_ID_Laufzettel = null;
		private String m_ID_Laufzettel_Eintrag = null;

		Rec21 	_recLaufzettel = null;
		Rec21 	_recLaufZettelEintrag = null;
		RecList21 _reclistLaufzettelEintrag = null;
		
		/*
		 * komponenten fuer die e-Mail-Sende-Abfrage
		 */
		MyE2_Column oColumn = null; 			
		MyE2_TextArea oTextArea= null;
		MyE2_CheckBox oSendToClosedEntries = null;

		
		/***
		 * Konstruktor,wenn man die Mails an alle bearbeier des Laufzettels schicken will
		 * z.B. aus dem Laufzettel selbst heraus
		 * 
		 * Sender ist immer der aktuell angemeldetet Benutzer
		 * 
		 * @param IDLaufzettel
		 * @param sUserBearbeiter
		 * 
		 */
		public WF_SIMPLE_BT_SendMail(String IDLaufzettel)
		{
			this(IDLaufzettel, null);
		}


		
		/**
		 * Button zum Mailversand von Laufzettel-Nachrichten.
		 * Es gibt 2 implizite Möglichkeiten: 
		 * 	1. Es sollen Mails an alle Laufzetteleinträge-Bearbeiter eines Laufzettels verschickt werden  (sIDLaufzettelEintrag == null)
		 * 	2. Es soll eine Mail für genau einen Laufzetteleintrag verschickt werden. (sIDLaufzettelEintrag != null)
		 * 
		 * @param sIDLaufzettel	
		 * @param sIDLaufzettelEintrag
		 * 	
		 */
		public WF_SIMPLE_BT_SendMail(String sIDLaufzettel, String sIDLaufzettelEintrag)
		{
			super ();
			m_ID_Laufzettel = sIDLaufzettel;
			m_ID_Laufzettel_Eintrag = sIDLaufzettelEintrag;
			
			getLaufzettel();
			getLaufzettelEintrag();
			

			E2_ResourceIcon ico_mail = null;
			if (sIDLaufzettelEintrag == null)
			{
				ico_mail = E2_ResourceIcon.get_RI("mass_email.png");
				this.setToolTipText(new MyE2_String("Alle Aufgaben per Mail verschicken").CTrans());
			}
			else
			{
				ico_mail = E2_ResourceIcon.get_RI("email_small.png");
				this.setToolTipText(new MyE2_String("Diese Aufgabe per Mail verschicken").CTrans());
			}
			
			this.__setImages(ico_mail, ico_mail);
			
			
			this.add_GlobalAUTHValidator(
					E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"SENDE_LAUFZETTEL_EINTRAG");

			this.add_oActionAgent(new MailButton_ActionAgent());
			
			// Laufzettel-Validierer
			this.add_IDValidator(new ValidateLaufzettelAbgeschlossen());
			
			this.add_IDValidator(new XX_ActionValidator(){

				@Override
				public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				protected MyE2_MessageVector isValid(String unformated)
						throws myException {
					
					// Besitzer und supervisor des Laufzettels holen
					String sOwner = S.NN(_recLaufzettel.getUfs(LAUFZETTEL.id_user_besitzer) );
					String sSupervisor = S.NN(_recLaufzettel.getUfs(LAUFZETTEL.id_user_supervisor) );
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
//					try
//					{
//						
//						if (getLaufzettelEintrag() != null){
//							if (sIDLaufzettel != null){
//								// ganzer Laufzettel
//								String s_id = getLaufzettel().getUfs(LAUFZETTEL.id_user_besitzer) ;
//								if (!S.NN(getLaufzettel().getUfs(LAUFZETTEL.id_user_besitzer) ).equals(bibALL.get_ID_USER()) ){
//									oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur der Besitzer des Laufzettels darf die Mail verschicken!"));
//								}
//							}
//						}
//							
//						
//						if (getLaufzettelEintrag() != null)
//						{// wenn es die Mail für einen Eintrag ist, dann das hier:
//							
//							if ( !(_recLaufZettelEintrag.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_user_besitzer,"-").equals(bibALL.get_ID_USER()) 
//									|| bibALL.get_bIST_SUPERVISOR()
//									|| sOwner.equals(bibALL.get_ID_USER())
//									|| sSupervisor.equals(bibALL.get_ID_USER()))
//								)
//							{
//								oMV.add_MESSAGE(new MyE2_Alarm_Message(
//										"Nur der Besitzer oder Supervisor dürfen das!"));
//							}
//						}
//						else
//						{// es ist ein Mailbutton für den gesamten Laufzettel
//							if (!(sOwner.equals(bibALL.get_ID_USER())	
//									|| sSupervisor.equals(bibALL.get_ID_USER()) 
//									|| bibALL.get_bIST_SUPERVISOR())
//								)
//								{
//									oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur der Besitzer des Laufzettels oder Supervisor dürfen das!"));
//								}
//						}
						
//					}catch (myException e)
//					{
//						e.printStackTrace();
//						oMV.add_MESSAGE(e.get_ErrorMessage());
//					}
					
					
					// gelöschte Einträge können nicht einzeln verschickt werden.
					try
					{
						if (getLaufzettelEintrag() != null)
						{// wenn es die Mail für einen Eintrag ist, dann das hier:
							//RECORD_LAUFZETTEL_EINTRAG oRec = m_oChilds.get(m_ID_Laufzettel_Eintrag);
							if ( !(_recLaufZettelEintrag.get_ufs_dbVal(LAUFZETTEL_EINTRAG.geloescht, "N").equalsIgnoreCase("N")	))
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message(
										"Gelöschte Einträge dürfen nicht gemailt werden."));
							}
						}

					} catch (myException e2)
					{
						e2.printStackTrace();
						oMV.add_MESSAGE(e2.get_ErrorMessage());
					}	
					
					return oMV;
				}
				
			});
		}

		
		
		private Rec21 getLaufzettel(){
			Rec21 _rec = null;
			if (m_ID_Laufzettel != null ){
				if (_recLaufzettel != null){
					_rec = _recLaufzettel;
				} else {
					// load
					try {
						_recLaufzettel = new Rec21(_TAB.laufzettel)._fill_id(m_ID_Laufzettel);
						_rec = _recLaufzettel;
					} catch (myException e) {
						// konnte laufzettel nicht laden						
					}
					
				}
					
			}
			return _rec;
		}
		
		
		
		private Rec21 getLaufzettelEintrag(){
			Rec21 _rec = null;
			if (m_ID_Laufzettel_Eintrag != null ){
				if (_recLaufZettelEintrag != null){
					_rec = _recLaufZettelEintrag;
				} else {
					// load
					try {
						_recLaufZettelEintrag = new Rec21(_TAB.laufzettel_eintrag)._fill_id(m_ID_Laufzettel_Eintrag);
						_rec = _recLaufZettelEintrag;
						if (_recLaufzettel == null){
							_recLaufzettel = _recLaufZettelEintrag.get_up_Rec21(LAUFZETTEL.id_laufzettel);
						}
					} catch (myException e) {
						// konnte laufzettel-Eintrag nicht laden						
					}
				}
			}
			return _rec;
		}
		
		
		
		/**
		 * gibt alle nicht gelöschten Laufzettel-Einträge zurück,
		 * null wenn keine gefunden.
		 * 
		 * @author manfred
		 * @date 25.04.2019
		 *
		 * @return
		 */
		private RecList21 getLaufzettelEintraege(){
			RecList21 _rl = null;
			if (m_ID_Laufzettel != null ){
				if (_reclistLaufzettelEintrag != null){
					_rl = _reclistLaufzettelEintrag;
				} else {
					// load
					Rec21 recLZ = getLaufzettel();
					if (recLZ != null ){
						try {
							Term t = new vgl(new Nvl(LAUFZETTEL_EINTRAG.geloescht.t(),"'N'"), COMP.EQ, new TermSimple("'N'"));
							String s = t.s();
							
							_rl = recLZ.get_down_reclist21(
									LAUFZETTEL_EINTRAG.id_laufzettel, 
									s,
									null );
							
							_reclistLaufzettelEintrag = _rl;
							
						} catch (myException e) {
							// konnte laufzettel nicht laden						
						}
					}
				}
			}
			
			return _rl;
		}
		
		
		/***
		 * Action Agent zeigt einen Dialog an, der den Kopftext der Mail eingeben lässt,
		 * und wenn es eine Multi-Mail ist für den ganzen Laufzettel, dann wird noch abgefragt, ob auch noch
		 * die abgeschlossenen Einträge gesendet werden sollen! 
		 * @author manfred
		 * @date 13.11.2008
		 */
		private class MailButton_ActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{

				execInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(WF_SIMPLE_BT_SendMail.this.m_ID_Laufzettel));
				if (!bibMSG.get_bHasAlarms())
				{
					int nrows = 10;
					
					oColumn =  new MyE2_Column();
					oTextArea= new MyE2_TextArea(new MyE2_String("Es gibt eine Laufzettel-Info für Sie...").toString(),300,2000,10);
					oSendToClosedEntries = new MyE2_CheckBox(new MyE2_String("Auch abgeschlossene Einträge berücksichtigen!"));
			
					// komponenten fuer eMail-senden 
					oColumn.setInsets(E2_INSETS.I_0_0_0_0);
					oColumn.add(new MyE2_Label(new MyE2_String("Text:")));
					oColumn.add(oTextArea);
					oTextArea.set_iRows(10);
					oTextArea.set_iWidthPixel(500);
					
					if(WF_SIMPLE_BT_SendMail.this.m_ID_Laufzettel_Eintrag == null)
					{	// Braucht man nur bei Mehrfachmails...
						oTextArea.set_iRows(nrows - 2);
						oColumn.add(oSendToClosedEntries);
					}
					
					ownConfirmContainer oMessageBox = new ownConfirmContainer(
							new MyE2_String("Laufzettel-Mails verschicken"),
							new MyE2_String("Mail-Text anpassen"),
							null,
							oColumn,
							null,
							new MyE2_String("Mailen..."), 
							new MyE2_String("Abbrechen"),
							new Extent(300),
							new Extent(300));
	
					
//					oMessageBox.get_oButtonOK().activate_KeyCode(-1);
					
					// den ActionAgent für die Mailverschickung dran hängen!!
					oMessageBox.set_ActionAgentForOK(new MailSendActionAgent(WF_SIMPLE_BT_SendMail.this.m_ID_Laufzettel,WF_SIMPLE_BT_SendMail.this.m_ID_Laufzettel_Eintrag,oMessageBox));
					
					oMessageBox.show_POPUP_BOX();
				}
			}
		}
		
		
		private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
		{

			public ownConfirmContainer(MyE2_String windowtitle,
					MyE2_String texttitle, Vector<MyString> innerTextblock,
					Component innerComponent,
					XX_ActionValidator validatorForOK_Button,
					MyE2_String labelOKButton, MyE2_String labelCancelButton,
					Extent width, Extent height)  throws myException
			{
				super(windowtitle, texttitle, innerTextblock, innerComponent,
						validatorForOK_Button, labelOKButton, labelCancelButton, width, height);

			}
			
		}
		
		/**
		 * Action Agent, den man dem Abfrage-Dialog mitgeben muss, damit dann die Mails verschickt werden können 
		 * @author manfred
		 * @date 13.11.2008
		 */
		private class MailSendActionAgent extends XX_ActionAgent
		{

			private String m_ID_Laufzettel = null;
			private String m_ID_Eintrag = null;
			@SuppressWarnings("unused")
			private E2_ConfirmBasicModuleContainer m_calling_Messagebox = null;
			
			/***
			 * Konstruktor zum übernehmen der IDs füs verschicken der Mails
			 * @param ID_Laufzettel_ID
			 * @param ID_Laufzettel_Eintrag_ID
			 */
			public MailSendActionAgent(String ID_Laufzettel_ID, String ID_Laufzettel_Eintrag_ID,E2_ConfirmBasicModuleContainer caller)
			{
				this.m_ID_Eintrag = ID_Laufzettel_Eintrag_ID;
				this.m_ID_Laufzettel = ID_Laufzettel_ID;
				this.m_calling_Messagebox = caller;
			}
			
			
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				
				
				String sEingabe = WF_SIMPLE_BT_SendMail.this.oTextArea.getText();
				boolean bErledigte = WF_SIMPLE_BT_SendMail.this.oSendToClosedEntries.isSelected();
				
				WF_MailHelper mailHelper = null;
				
				// TODO Auto-generated method stub
				if (this.m_ID_Eintrag == null){
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("Mail verschicken an alle Bearbeiter von Laufzettel: " + this.m_ID_Laufzettel));
					mailHelper = new WF_MailHelper(this.m_ID_Laufzettel);
						
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("Mail verschicken an Bearbeiter von Aufgabe: " +this.m_ID_Laufzettel + "/" + this.m_ID_Eintrag));
					mailHelper = new WF_MailHelper(this.m_ID_Laufzettel,this.m_ID_Eintrag);
				}
				
				if (mailHelper != null)
				{
					mailHelper.set_MailBetreff("Es gibt eine Laufzettel-Info für Sie...");
					mailHelper.set_MailBodyText(sEingabe);
					mailHelper.set_MailWhenClosed(bErledigte);
					
					// die Mail(s) werden verschickt!	
					mailHelper.SendMail();
				}
			}
		}
		
		


		
	/**
	 * Ein Validator der prüft, ob der Laufzettel schon abgeschlossen ist
	 * @author manfred
	 * @date 31.10.2008
	 */
	private class ValidateLaufzettelAbgeschlossen extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException {
			
			// Besitzer und supervisor des Laufzettels holen
			String sClosed = 		"" + (getLaufzettel() != null ? S.NN(WF_SIMPLE_BT_SendMail.this.getLaufzettel().getUfs(LAUFZETTEL.abgeschlossen_am, "")) : "");
			String sClosedEntry= 	"" + (getLaufzettelEintrag() != null ? S.NN(WF_SIMPLE_BT_SendMail.this.getLaufzettelEintrag().getUfs(LAUFZETTEL_EINTRAG.abgeschlossen_am, "")): "" ) ;
			boolean bClosed = sClosed.equalsIgnoreCase("Y") || sClosedEntry.equalsIgnoreCase("Y") ? true : false; 
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (bClosed )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Einen abgeschlossenen Laufzettel kann man nicht mehr erweitern/löschen/verschicken!"));
			}
			
			return oMV;
		}
	}
	
	
	
	
	/**
	 * Ein Validator der prüft, ob der Laufzettel schon gelöscht ist
	 * @author manfred
	 * @date 31.10.2008
	 */
	private class ValidateLaufzettelEintragGeloescht extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)	throws myException {
			
			// Besitzer und supervisor des Laufzettels holen
			String sLZ_deleted = 	"" + (getLaufzettel() != null 			? S.NN(WF_SIMPLE_BT_SendMail.this.getLaufzettel().getUfs(LAUFZETTEL.geloescht, "")) : "");
			String sLZE_deleted= 	"" + (getLaufzettelEintrag() != null 	? S.NN(WF_SIMPLE_BT_SendMail.this.getLaufzettelEintrag().getUfs(LAUFZETTEL_EINTRAG.geloescht, "")): "" ) ;

			boolean bDeleted= sLZ_deleted.equalsIgnoreCase("Y")||sLZE_deleted.equalsIgnoreCase("Y") ? true : false;
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (bDeleted )
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Einen gelöschten Laufzettel/Eintrag kann man nicht mehr erweitern/ändern/verschicken!"));
			}
			
			return oMV;
		}
	}

	


	
//	
//	/**
//	 * Ein Validator der prüft, ob der Laufzetteleintrag schon abgeschlossen ist
//	 * @author manfred
//	 * @date 6.11.2008  
//	 */
//	private class ValidateLaufzettelEintragAbgeschlossen extends XX_ActionValidator
//	{
//
//		@Override
//		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
//		{
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String unformated)	throws myException {
//			
//			// Besitzer und supervisor des Laufzettels holen
//			String sAbgeschlossen = S.NN(m_oLaufzettel.get_GELOESCHT_cF());
//			
//			MyE2_MessageVector oMV = new MyE2_MessageVector();
//			
//			if (sAbgeschlossen != null && sAbgeschlossen.equalsIgnoreCase("Y") )
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(	"Eine gelöschte Aufgabe kann man nicht mehr ändern!"));
//			}
//			
//			return oMV;
//		}
//	}
//		
		
		
		
	
}
