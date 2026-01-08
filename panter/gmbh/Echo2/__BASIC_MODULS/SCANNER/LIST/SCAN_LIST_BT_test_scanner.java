package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.RECORD_SCANNER_SETTINGS_SPECIAL;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_SETTINGS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SCAN_LIST_BT_test_scanner extends MyE2_ButtonInLIST  implements DS_IF_components4decision{

	public String 				cLastBaseFileName = "";

	private ArrayList<String>   v_CollectAllScannerFilesWithPath = new ArrayList<String>();   

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();

	public SCAN_LIST_BT_test_scanner() {
		super("Test");
		this.setToolTipText("Scanner test");
		this.cLastBaseFileName="";
		this._aaa(new confirm_scan_confirmation_agent(this));
		this._aaa(new ownActionStartScan());
	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy{
		SCAN_LIST_BT_test_scanner oDruckerTest_bt =  null;
		oDruckerTest_bt = new SCAN_LIST_BT_test_scanner();
		return oDruckerTest_bt;
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

			MyE2_MessageVector  			oMV = new MyE2_MessageVector();
			SCAN_LIST_BT_test_scanner  	oThis = SCAN_LIST_BT_test_scanner.this;

			E2_ComponentMAP oMAP = SCAN_LIST_BT_test_scanner.this.EXT().get_oComponentMAP();
			String scanner_id = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(SCANNER_SETTINGS.id_scanner_settings.fieldName());

			this.grid4progress.removeAll();

			//zuerst feststellen, ob der user einen scanner hat
			this.recScanner = new RECORD_SCANNER_SETTINGS_SPECIAL(new RECORD_SCANNER_SETTINGS(scanner_id)); //oThis.get_recScan4ThisButton();

			oThis.v_CollectAllScannerFilesWithPath.clear();

			if (this.recScanner!=null) {

				//dateiname festlegen, der im output-ordner landet
				SCAN_LIST_BT_test_scanner.this.cLastBaseFileName = "SCANNER_TEST_"+UUID.randomUUID().toString();

				if (oMV.get_bIsOK()) {


					this.cSYSCALL1 = recScanner.get_SCANNER_AUFRUF1_PARAMETERFREE(SCAN_LIST_BT_test_scanner.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
					this.cSYSCALL2 = recScanner.get_SCANNER_AUFRUF2_PARAMETERFREE(SCAN_LIST_BT_test_scanner.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
					this.cSYSCALL3 = recScanner.get_SCANNER_AUFRUF3_PARAMETERFREE(SCAN_LIST_BT_test_scanner.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));
					this.cSYSCALL4 = recScanner.get_SCANNER_AUFRUF4_PARAMETERFREE(SCAN_LIST_BT_test_scanner.this.cLastBaseFileName, recScanner.get_DPI_cUF_NN("150"));

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
								SCAN_LIST_BT_test_scanner   ooThis = SCAN_LIST_BT_test_scanner.this;
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
										//											ooThis.collect_vReturned_Files();
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

								//									bibMSG.add_MESSAGE(SCANNER_LIST_BT_test_scanner.this.do_Process_Scan_ResultFiles(ooThis.v_CollectAllScannerFilesWithPath));

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

							DEBUG.System_println(cSYSCALL1);
							DEBUG.System_println(cSYSCALL2);
							DEBUG.System_println(cSYSCALL3);
							DEBUG.System_println(cSYSCALL4);
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
							//								oThis.collect_vReturned_Files();
							//								bibMSG.add_MESSAGE(SCAN_LIST_BT_test_scanner.this.do_Process_Scan_ResultFiles(oThis.v_CollectAllScannerFilesWithPath));

						} catch (IOException e) {
							e.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("IO-Exception:"+e.getLocalizedMessage(),false)));
						} 
						catch (InterruptedException e) {
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


	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return storage_vector_4_all_agents;
	}


	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return storage_vector_4_standard_agents;
	}


	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return storage_vector_4_decision_agents;
	}


	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return hm_descision_containers;
	}

	private class confirm_scan_confirmation_agent extends DS_ActionAgent{


		public confirm_scan_confirmation_agent(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)
				throws myException {
			return new ownPopupContainer(this.get_actionComponent());
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()
					._add(new MyE2_Label(new MyE2_String("Wollen sie eine Test Seite scannen?"),MyE2_Label.STYLE_TITEL_NORMAL()), new RB_gld()._span(2)._al(E2_ALIGN.CENTER_MID))
					._add(container.get_bt_OK()._t("Ja"), 	new RB_gld()._ins(E2_INSETS.I(2,10,5,2))._al(E2_ALIGN.CENTER_MID))
					._add(container.get_bt_NO()._t("Nein"), new RB_gld()._ins(E2_INSETS.I(2,10,5,2))._al(E2_ALIGN.CENTER_MID))
					._setSize(200,200);

			container.add(gm, E2_INSETS.I(2,3,2,3));

		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {return null;}

	}

	private class ownPopupContainer extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 400;
		}

		@Override
		public int get_height_in_pixel() {
			return 200;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Test seite scannen");
		}

	}
}
