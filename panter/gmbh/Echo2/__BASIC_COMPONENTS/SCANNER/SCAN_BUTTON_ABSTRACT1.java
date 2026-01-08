package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class SCAN_BUTTON_ABSTRACT1 extends SCAN_BUTTON_ABSTRACT0 {

	/**
	 * das ergebniss ist eine reihe von dateien im output-ordner, die mit dem namen cLastBaseFileName beginnen
	 * @throws myException
	 */
	
	private 			String 				cLastBaseFileName = null;
	
	public abstract 	MyE2_MessageVector do_Process_Scan_ResultFiles(Collection<String> vResultsFileNamesWithoutPath) throws myException;
	
	private 			ArrayList<String>   v_CollectAllScannerFilesWithPath = new ArrayList<String>();           
	
	public SCAN_BUTTON_ABSTRACT1() throws myException {
		super();
	}

	public SCAN_BUTTON_ABSTRACT1(MyString cButtonText) throws myException {
		super(cButtonText);
	}

	public SCAN_BUTTON_ABSTRACT1(RECORD_SCANNER_SETTINGS_SPECIAL  rec_Scan4ThisButton) throws myException {
		super(rec_Scan4ThisButton);
	}

	
	@Override
	public XX_ActionAgent get_ActionAgent() throws myException {
		return new ownActionStartScan();
	}
	
	
	private class ownActionStartScan extends XX_ActionAgent {
		
		private String 							cSYSCALL1 = null;
		private String 							cSYSCALL2 = null;;
		private String 							cSYSCALL3 = null;
		private String 							cSYSCALL4 = null;
		
		private RECORD_SCANNER_SETTINGS_SPECIAL recScanner = null;
		private int[]     gridBreite = {300,50};
		private MyE2_Grid grid4progress = 		new MyE2_Grid(gridBreite,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			MyE2_MessageVector  	oMV = new MyE2_MessageVector();
			SCAN_BUTTON_ABSTRACT1   oThis = SCAN_BUTTON_ABSTRACT1.this;
			this.grid4progress.removeAll();
			
			//zuerst feststellen, ob der user einen scanner hat
			this.recScanner = oThis.get_recScan4ThisButton();

			oThis.v_CollectAllScannerFilesWithPath.clear();
			
			if (this.recScanner!=null) {
			
				//dateiname festlegen, der im output-ordner landet
				SCAN_BUTTON_ABSTRACT1.this.cLastBaseFileName = UUID.randomUUID().toString();
				
				if (oMV.get_bIsOK()) {
				
						
						this.cSYSCALL1 = recScanner.get_SCANNER_AUFRUF1_PARAMETERFREE(SCAN_BUTTON_ABSTRACT1.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
						this.cSYSCALL2 = recScanner.get_SCANNER_AUFRUF2_PARAMETERFREE(SCAN_BUTTON_ABSTRACT1.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
						this.cSYSCALL3 = recScanner.get_SCANNER_AUFRUF3_PARAMETERFREE(SCAN_BUTTON_ABSTRACT1.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
						this.cSYSCALL4 = recScanner.get_SCANNER_AUFRUF4_PARAMETERFREE(SCAN_BUTTON_ABSTRACT1.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
						
						if (S.isFull(cSYSCALL1)) DEBUG.System_println(cSYSCALL1,DEBUG_FLAGS.MARTINS_EIGENER.name());
						if (S.isFull(cSYSCALL2)) DEBUG.System_println(cSYSCALL1,DEBUG_FLAGS.MARTINS_EIGENER.name());
						if (S.isFull(cSYSCALL3)) DEBUG.System_println(cSYSCALL1,DEBUG_FLAGS.MARTINS_EIGENER.name());
						if (S.isFull(cSYSCALL4)) DEBUG.System_println(cSYSCALL1,DEBUG_FLAGS.MARTINS_EIGENER.name());

						if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Call 1: <"+cSYSCALL1+">",false)));
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Call 2: <"+cSYSCALL2+">",false)));
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Call 3: <"+cSYSCALL3+">",false)));
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Call 4: <"+cSYSCALL4+">",false)));
						}
						
						if (recScanner.is_LOOP_SCAN_YES()) {
						
							new E2_ServerPushMessageContainer_STD (	new Extent(500), 	
																	new Extent(200), 
																	new MyE2_String("Scan läuft ..."),
																	true,true,
																	1000,
																	grid4progress,
																	E2_INSETS.I(10,10,10,10)) {

								@Override
								public void Run_Loop() throws myException {
									ownActionStartScan 		oThis = ownActionStartScan.this;
									SCAN_BUTTON_ABSTRACT1   ooThis = SCAN_BUTTON_ABSTRACT1.this;
									this.get_oButtonCloseWindow().setVisible(false);
									
									boolean bAbbruch = false;
									//hier in abhaengigkeit vom scanner-profil-eintrag eine schleife
									do  {
										try {

											Process p = Runtime.getRuntime().exec(oThis.cSYSCALL1);  p.waitFor();
											
											if (S.isFull(recScanner.get_SCANNER_AUFRUF2_cUF())) {
												Process p2 = Runtime.getRuntime().exec(oThis.cSYSCALL2); 	p2.waitFor();
											}
											
											if (S.isFull(recScanner.get_SCANNER_AUFRUF3_cUF())) {
												Process p3 = Runtime.getRuntime().exec(oThis.cSYSCALL3);  p3.waitFor();
											}
											
											if (S.isFull(recScanner.get_SCANNER_AUFRUF4_cUF())) {
												Process p4 = Runtime.getRuntime().exec(oThis.cSYSCALL4);	p4.waitFor();
											}
											
											int iVorher = ooThis.v_CollectAllScannerFilesWithPath.size();
											ooThis.collect_vReturned_Files();
											int iNachher = ooThis.v_CollectAllScannerFilesWithPath.size();
											
											oThis.grid4progress.removeAll();
											oThis.grid4progress.add(new MyE2_Label(new MyE2_String("Anzahl abgeschlossene Seiten ...")), E2_INSETS.I(3,3,3,3));
											oThis.grid4progress.add(new MyE2_Label(new MyE2_String(""+ooThis.v_CollectAllScannerFilesWithPath.size(),false)), E2_INSETS.I(3,3,3,3));
											
											if (iVorher==iNachher) {
												bAbbruch = true;
											} else {
												Thread.sleep(recScanner.get_LOOP_TIMEOUT_SECONDS_lValue(0l)*1000);
											}
										} catch (IOException e) {
											e.printStackTrace();
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("IO-Exception:"+e.getLocalizedMessage(),false)));
											bAbbruch=true;
										} catch (InterruptedException e) {
											e.printStackTrace();
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("InterruptedException-Exception:"+e.getLocalizedMessage(),false)));
											bAbbruch=true;
										}
										
										if (this.get_bIsInterupted() || bAbbruch) {
											break;
										}
										
									} while (true);
									
									bibMSG.add_MESSAGE(SCAN_BUTTON_ABSTRACT1.this.do_Process_Scan_ResultFiles(ooThis.v_CollectAllScannerFilesWithPath));

									if (bibMSG.get_bHasAlarms()) {
										this.set_bIsInterupted(true);
										this.get_oButtonCloseWindow().setVisible(true);
									}
									
								}

								@Override
								public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane)throws myException {
								}
								
							};

							
						
						} else {
							
							try {

								Process p = Runtime.getRuntime().exec(cSYSCALL1);  		p.waitFor();
								
								if (S.isFull(recScanner.get_SCANNER_AUFRUF2_cUF())) {
									Process p2 = Runtime.getRuntime().exec(cSYSCALL2); 	p2.waitFor();
								}
								
								if (S.isFull(recScanner.get_SCANNER_AUFRUF3_cUF())) {
									Process p3 = Runtime.getRuntime().exec(cSYSCALL3);  p3.waitFor();
								}
								
								if (S.isFull(recScanner.get_SCANNER_AUFRUF4_cUF())) {
									Process p4 = Runtime.getRuntime().exec(cSYSCALL4);	p4.waitFor();
								}
								oThis.collect_vReturned_Files();
								bibMSG.add_MESSAGE(SCAN_BUTTON_ABSTRACT1.this.do_Process_Scan_ResultFiles(oThis.v_CollectAllScannerFilesWithPath));
								
							} catch (IOException e) {
								e.printStackTrace();
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("IO-Exception:"+e.getLocalizedMessage(),false)));
								
							} catch (InterruptedException e) {
								e.printStackTrace();
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("InterruptedException-Exception:"+e.getLocalizedMessage(),false)));
							}

						}

				} else {
					bibMSG.add_MESSAGE(oMV);
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde für den KNOPF oder Benutzer kein SCANNERPROFIL hinterlegt !")));
			}
		}
	}
	
	
	/**
	 * 
	 * return true, when collection of filenames starting with this.cLastBaseFileName was found
	 */
	private boolean collect_vReturned_Files() throws myException {
		
		String cStartPath = 	File.separator+bibALL.get_WEBROOTPATH()+File.separator+bibALL.get_OUTPUTPATH();
		String cStartPath_WE = cStartPath+File.separator;
		
		File fOutput = new File(cStartPath);
		
		String[] cALLFilesStartWithTempName = fOutput.list();
		
		if (cALLFilesStartWithTempName!=null) {
			
			if (cALLFilesStartWithTempName.length>0) {
				for (int i=0; i<cALLFilesStartWithTempName.length;i++) {
					if (cALLFilesStartWithTempName[i].startsWith(this.cLastBaseFileName)) {
						
						DEBUG.System_println("Dateiname ALT:<sdffpepglfgsgp>"+cALLFilesStartWithTempName[i],DEBUG_FLAGS.MARTINS_EIGENER.name());
						
						//jede temp-datei bekommt einen neuen eindeutigen namen
						String c_new_temp_name = cStartPath_WE+UUID.randomUUID().toString()+".pdf";
						new File(cStartPath_WE+cALLFilesStartWithTempName[i]).renameTo(new File(c_new_temp_name));
						
						this.v_CollectAllScannerFilesWithPath.add(c_new_temp_name);
						
						DEBUG.System_println("Dateiname NEU: <sdffpepglfgsgp>"+c_new_temp_name,DEBUG_FLAGS.MARTINS_EIGENER.name());
					}
				}
				return true;
			} else {
				
			}
		} else { 
			throw new myException("System error: Path is not existing ..."+cStartPath);
		}
		return false;
	}

	
}
