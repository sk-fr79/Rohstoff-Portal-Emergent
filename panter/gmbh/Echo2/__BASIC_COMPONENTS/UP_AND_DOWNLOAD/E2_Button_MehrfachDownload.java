package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import panter.gmbh.indep.pdf.PDF_GeneratePdf_With_Images;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class E2_Button_MehrfachDownload extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;
	
	
	//elemente, die von aussen uebergeben werden koennen, um die erstellung der pdfs zu kontrollieren
	private MyE2_SelectField	oSelectBildGroesse = new MyE2_SelectField();
	private Vector<String> 		vKeys = bibVECTOR.get_Vector("CHECKBOX_LEERZEILE_NACH_BILD", "SELECT_GROESSE");
	
	
	private PDF_GeneratePdf_With_Images oPDF = null;
	
	public E2_Button_MehrfachDownload(E2_NavigationList  NaviList, String cBasicModuleKenner) throws myException
	{
		super(E2_ResourceIcon.get_RI("down_multi.png"), true);
		
		//die einstell-elemente auf speichern setzen
		this.oSelectBildGroesse.add_oActionAgent(new ownActionAgentSaveSettings());
		
		this.oSelectBildGroesse.setToolTipText(new MyE2_String("Wie groß (im Verhältnis A4-Seite) sollen die Bilder sein ?").CTrans());
		
		
		this.oNaviList = NaviList;
		
		String[][] cWerteBildGroesse = {{"20%","95"},{"30%","140"},{"40%","190"},{"50%","240"},{"60%","285"},{"80%","380"},{"100%","480"},};
		
		this.oSelectBildGroesse.set_ListenInhalt(cWerteBildGroesse, false);

		
		//hier die einsteller aus eine usersave-setting einlesen
		@SuppressWarnings("unchecked")
		HashMap<String, String> hmWerte = (HashMap<String, String> )new E2_UserSetting_HashMap(
				ENUM_USER_SAVEKEY.SESSION_HASH_USER_SIZE_MULTI_IMAGE_PDF.name(), vKeys).get_Settings(
						ENUM_USER_SAVEKEY.SESSION_HASH_USER_SIZE_MULTI_IMAGE_PDF.name());
		if (hmWerte!= null && hmWerte.containsKey("SELECT_GROESSE"))
		{
			this.oSelectBildGroesse.set_ActiveValue_OR_FirstValue(hmWerte.get("SELECT_GROESSE"));
		}
		//ende einsteller
		
		
//		if (S.isFull(cBasicModuleKenner))
//		{
//			this.add_GlobalAUTHValidator(cBasicModuleKenner,AM_BasicContainer.BUTTON_MULTI_DOWNLOAD);
//		}
		
		this.add_oActionAgent(new ownActionAgentStartDownload());
		
		this.setToolTipText(new MyE2_String("Mehrere Bilddateien in einem PDF zusammenfassen und herunterladen ...").CTrans());
		
		
	}

	
	
	private class ownActionAgentStartDownload extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_Button_MehrfachDownload oThis = E2_Button_MehrfachDownload.this;
			
			Vector<String>  vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens ein Upload-Dokument aus der Liste aus")));
				return;
			}
			
			//PDF_GeneratePdf_With_Images oPDF = new PDF_GeneratePdf_With_Images(NamesOfImageFiles, Filename_without_ending)
			int iFalscheTypen = 0;
			
			
			Vector<String>  vFileNames = new Vector<String>();
			for (String cID: vIDs)
			{
				RECORD_ARCHIVMEDIEN_ext  recArchiv = new RECORD_ARCHIVMEDIEN_ext(cID);
				if (recArchiv.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES())
				{
					vFileNames.add(recArchiv.get__cCompletePathAndFileName());
				}
				else
				{
					iFalscheTypen++;
				}
			}
			
			if (vFileNames.size()>0)
			{
				float fScale = new Float(oThis.oSelectBildGroesse.get_ActualWert());
				oThis.oPDF = new PDF_GeneratePdf_With_Images(		vFileNames, 
																	"sammelpdf",
																	true,
																	fScale);
				oPDF.starte_DownLoad();
			}
			
			if (iFalscheTypen>0)
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es wurden ",true," "+iFalscheTypen,false," Dateien selektiert, die nicht verarbeitet werden können!",true)));
			}
			
		}
		
	}
	
	
	
	private class ownActionAgentSaveSettings extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_Button_MehrfachDownload oThis = E2_Button_MehrfachDownload.this; 
			
			HashMap<String, String> hmSettings = new HashMap<String, String>();
			
			hmSettings.put("SELECT_GROESSE", oThis.oSelectBildGroesse.get_ActualWert());
			
			new E2_UserSetting_HashMap(
							ENUM_USER_SAVEKEY.SESSION_HASH_USER_SIZE_MULTI_IMAGE_PDF.name(), vKeys).STORE(
							ENUM_USER_SAVEKEY.SESSION_HASH_USER_SIZE_MULTI_IMAGE_PDF.name(), hmSettings);
		}
	}


	public MyE2_SelectField get_oSelectBildGroesse()
	{
		return oSelectBildGroesse;
	}
	
	
	
	
}
