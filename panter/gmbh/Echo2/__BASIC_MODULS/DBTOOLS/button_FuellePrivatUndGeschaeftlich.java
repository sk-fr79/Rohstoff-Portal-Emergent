package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class button_FuellePrivatUndGeschaeftlich extends MyE2_Button {

	public button_FuellePrivatUndGeschaeftlich() {
		super(new MyE2_String("Adressen automatisch einstufen, Steuer:Privat/Firma"), MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.add_oActionAgent(new ownAction());
	}


	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Durchsuchen der Adressen ..."),true,true,true,1000)
			{
				@Override
				public void Run_Loop() throws myException
				{

					RECLIST_ADRESSE  oRLA = new RECLIST_ADRESSE("SELECT * FROM JT_ADRESSE WHERE "+_DB.ADRESSE$ADRESSTYP+"=1 AND ID_LAND IS NOT NULL"); 
					
					int iBereitsGesetzt=	0;
					int iUndef=	0;
					int iFirmaD =			0;
					int iFirmaEU = 			0;
					int iFirmaEXEU = 		0;
					
					int iPrivatD =			0;
					int iPrivatEU = 		0;
					int iPrivatEXEU = 		0;
					
					String cID_HOMELAND = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF();
					
					int iCount = 0;
					
					for (RECORD_ADRESSE recA: oRLA.values()) {
						iCount++;

						RECORD_FIRMENINFO  recF = null;
						try {
							recF = recA.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
						} catch (myException ex) {
							System.out.println("Fehler bei Adresse ID: "+recA.get_ID_ADRESSE_cUF());
						}
						
						if (recF != null) {
						
							if (recF.is_FIRMA_YES() || recF.is_PRIVAT_YES()) {
								iBereitsGesetzt ++;
							} else {
								
								RECORD_LAND  recL = recA.get_UP_RECORD_LAND_id_land();
								
							
								if (recL.get_ID_LAND_cUF().equals(cID_HOMELAND)) {
									if (S.isFull(recF.get_UMSATZSTEUERID_cUF_NN("")) || S.isFull(recF.get_UMSATZSTEUERLKZ_cUF_NN(""))) {
										recF.set_NEW_VALUE_FIRMA("Y");
										recF.UPDATE(null,true);
										
										iFirmaD ++;
									} else if (S.isFull(recA.get_AUSWEIS_NUMMER_cUF_NN("")) && S.isEmpty(recF.get_STEUERNUMMER_cUF_NN(""))) {
										
										recF.set_NEW_VALUE_PRIVAT("Y");
										recF.UPDATE(null,true);
										
										iPrivatD ++;
										
									} else {
										iUndef ++;
									}
								} else if (recL.is_INTRASTAT_JN_YES()) {
									
									if (S.isFull(recF.get_UMSATZSTEUERID_cUF_NN("")) || S.isFull(recF.get_UMSATZSTEUERLKZ_cUF_NN(""))) {
										recF.set_NEW_VALUE_FIRMA("Y");
										recF.UPDATE(null,true);
										
										iFirmaEU ++;
									} else if (S.isFull(recA.get_AUSWEIS_NUMMER_cUF_NN(""))) {
										
										recF.set_NEW_VALUE_PRIVAT("Y");
										recF.UPDATE(null,true);
										
										iPrivatEU ++;
										
									} else {
										iUndef ++;
									}
								} else {
									
									recF.set_NEW_VALUE_FIRMA("Y");
									recF.UPDATE(null,true);
									
									iFirmaEXEU ++;
	
								}
								
							}
						}
						
						
						if (iCount % 100 == 0) {  
							//fortschrittsmeldungen
							MyE2_Grid oG = this.get_oGridBaseForMessages();
							oG.removeAll();
							oG.setSize(9);
							oG.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
							oG.add(new MyE2_Label("Gesamt"));
							oG.add(new MyE2_Label("Vorhanden"));
							oG.add(new MyE2_Label("Undef"));
							oG.add(new MyE2_Label("Firma D"));
							oG.add(new MyE2_Label("Firma EU"));
							oG.add(new MyE2_Label("Firma EXEU"));
							oG.add(new MyE2_Label("Privat D"));
							oG.add(new MyE2_Label("Privat EU"));
							oG.add(new MyE2_Label("Privat EXEU"));
							
							oG.add(new MyE2_Label(""+iCount),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iBereitsGesetzt),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iUndef),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iFirmaD),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iFirmaEU),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iFirmaEXEU),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iPrivatD),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iPrivatEU),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));
							oG.add(new MyE2_Label(""+iPrivatEXEU),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0));

						}
						
					}
					
				

					
					
				}	

				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane)	throws myException {
							
				}
				
			};
	}
	}
	
//	private class 
	
	
}
