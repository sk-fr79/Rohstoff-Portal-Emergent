package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic._4_ALL.Generate_Concatenated_InvoiceArchiveLists;

public class FS_BT_HOLE_BELEGE_AUS_ARCHIV extends MyE2_Button {

	private E2_NavigationList o_NaviList = null;

	public FS_BT_HOLE_BELEGE_AUS_ARCHIV(E2_NavigationList oNaviList) {
		super(new MyE2_String("Hole Rechnungen/Gutschriften aus Archiv"));
		this.o_NaviList = oNaviList;

		this.add_GlobalAUTHValidator_AUTO("SAMMLE_ARCHIV_DATEIEN");

		this.add_oActionAgent(new ownActionAgent());

		this.setToolTipText(new MyE2_String("Kopiert eine Adresse mit allen Telefon/Mail-Einträgen, sowie allen Mitarbeitern und Lieferadressen ...").CTrans());
	}

	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList oNaviList = FS_BT_HOLE_BELEGE_AUS_ARCHIV.this.o_NaviList;

			Vector<String> vIDsAdressen = oNaviList.get_vSelectedIDs_Unformated();

			if (vIDsAdressen.size() != 1) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte selektieren Sie genau eine Adresse, deren Belege Sie herunterladen möchten!")));
			} else {
				new ownPopupContainer(vIDsAdressen.get(0));
			}
		}

	}

	private class ownPopupContainer extends E2_BasicModuleContainer {

		private String c_ID_Adresse = null;

		public ownPopupContainer(String cID_Adresse) throws myException {
			super();
			this.c_ID_Adresse = cID_Adresse;

//			RECORD_ADRESSE recAdresse = new RECORD_ADRESSE(cID_Adresse);

			String cFieldName1 = "TO_CHAR(" + _DB.VKOPF_RG$DRUCKDATUM + ",'MM.YYYY')";
			String cFieldName2 = "TO_CHAR(" + _DB.VKOPF_RG$DRUCKDATUM + ",'YYYY-MM')";

			String cQuery = "SELECT DISTINCT " + cFieldName1 + "," + cFieldName2 + " FROM " + bibE2.cTO() + "." + _DB.VKOPF_RG + 
						" WHERE " + _DB.VKOPF_RG$ID_ADRESSE + "=" + this.c_ID_Adresse +
						" AND "+_DB.VKOPF_RG$DRUCKDATUM +" IS NOT NULL "+
						" AND NVL("+_DB.VKOPF_RG$DELETED +",'N')='N' "+
						" AND NVL("+_DB.VKOPF_RG$ABGESCHLOSSEN +",'N')='Y' "+
						" ORDER BY 2 DESC";

			E2_DropDownSettingsNew oDD = new E2_DropDownSettingsNew(cQuery, true, false);

			MyE2_SelectField oSelectField = new MyE2_SelectField(oDD.getDD(),"",false);
			oSelectField.add_oActionAgent(new ownActionAgentSelector());

			MyE2_Grid  oGridIntern = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.add(oGridIntern);
			
			oGridIntern.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie den Monat für den Belegdownload ...")),2, E2_INSETS.I(5,5,5,10));
			oGridIntern.add(oSelectField,2, E2_INSETS.I(5,5,5,10));
			
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(300), new MyE2_String("Belege aus dem Archiv holen ..."));
		}

		private class ownActionAgentSelector extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				MyE2_SelectField  oSelField = (MyE2_SelectField) oExecInfo.get_MyActionEvent().getSource();
				
				String cDatum_YYYY_MM = oSelField.get_ActualWert();
				
				if (S.isFull(cDatum_YYYY_MM)) {
					String cFieldName = "TO_CHAR("+_DB.VKOPF_RG$DRUCKDATUM+",'YYYY-MM')";
					String cSQL = "SELECT "+_DB.VKOPF_RG$ID_VKOPF_RG+" FROM "+bibE2.cTO()+"."+_DB.VKOPF_RG+
											" WHERE "+_DB.VKOPF_RG$ID_ADRESSE+"="+ownPopupContainer.this.c_ID_Adresse+
											" AND "+cFieldName+"="+bibALL.MakeSql(cDatum_YYYY_MM)+
											" AND NVL(" +_DB.VKOPF_RG$DELETED+",'N')='N' "+
											" AND NVL(" +_DB.VKOPF_RG$ABGESCHLOSSEN+",'N')='Y'";

					
					String[][] arrRueck = bibDB.EinzelAbfrageInArray(cSQL);
					
					if (arrRueck != null && arrRueck.length>0) {
						Generate_Concatenated_InvoiceArchiveLists oRechnungKette = new Generate_Concatenated_InvoiceArchiveLists(bibVECTOR.get_VectorFromArray(arrRueck), true);
						MyE2_MessageVector oMV = oRechnungKette.DO_Concatenate();
						if (oMV.get_bIsOK()) {
							myTempFile oTempfile = oRechnungKette.get_oTempFile();
							if (oTempfile!=null) {
								oTempfile.starteDownLoad("BELEGARCHIV_KDID_"+ownPopupContainer.this.c_ID_Adresse+".pdf", "Application/pdf");
								
								if (oMV.get_bHasWarnings()) {
									bibMSG.add_MESSAGE(oMV);
								}
								
							} else {
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Archivdateien gefunden !")));
								bibMSG.add_MESSAGE(oMV);
							}
							
						} else {
							bibMSG.add_MESSAGE(oMV);
						}
						
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Rechnungen gefunden !")));
					}
					
					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde kein Monat angegeben !")));
				}
				
				
				
			}
		}

	}

}
