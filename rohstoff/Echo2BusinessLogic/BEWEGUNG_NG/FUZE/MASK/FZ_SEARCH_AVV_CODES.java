package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_ListBox;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataIndexHashMAP;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.EAK_DataRecordHashMap_CODE;

public abstract class FZ_SEARCH_AVV_CODES extends E2_Grid  implements IF_RB_Component_Savable, E2_IF_Copy, MyE2IF_IsMarkable {

	/**
	 * abstracts
	 */
	public abstract RECORD_ARTIKEL get_actual_artikel() throws myException;

	/**
	 * end abstracts
	 */

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private MyE2_Button ButtonSelect = new MyE2_Button(E2_ResourceIcon.get_RI("suche_mini.png"), true);
	private MyE2_Button ButtonErase = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"), true);

	private RB_TextField tf_4_id = new RB_TextField(40, 10)._sBDD();
	private RB_TextField tf_4_anzeige = new RB_TextField(300, 100)._sBDD();

	private XX_ActionAgent oActionAfterFound = null;

	private MyE2_Button oButtonStdAVV = new MyE2_Button(E2_ResourceIcon.get_RI("wizard_mini.png"));

	/*
	 * hier steht der gefundene code drin
	 */
	private RECORD_EAK_CODE found_and_stored_code = null;

	public FZ_SEARCH_AVV_CODES() throws myException {
		super();
		this._bo(null);

		this._setSize(40, 300, 20, 20);
		this.setBackground(null);

		this.tf_4_id._f(get_selected_size().get_font());
		this.tf_4_anzeige._f(get_selected_size().get_font());
		
		this.tf_4_id.set_bEnabled_For_Edit(false);
		this.tf_4_anzeige.set_bEnabled_For_Edit(false);

		this.ButtonSelect.add_oActionAgent(new actionStartSearch());
		this.ButtonSelect.add_GlobalAUTHValidator_AUTO("AVV_CODE_DEF");
		this.ButtonSelect.setToolTipText(new MyE2_String("AVV-Code auswählen ..").CTrans());

		this.ButtonErase.add_oActionAgent(new ownActionErase());
		this.ButtonErase.add_GlobalAUTHValidator_AUTO("AVV_CODE_DEF");
		this.ButtonErase.setToolTipText(new MyE2_String("AVV-Code entfernen").CTrans());

		this._a(this.tf_4_id, new RB_gld());
		this._a(this.tf_4_anzeige, new RB_gld());
		this._a(this.ButtonErase, new RB_gld());
		this._a(this.ButtonSelect, new RB_gld());

	}

	public void set_layoutdata_color_to_components(Color col) throws myException {
		for (Component c : this.getComponents()) {
			LayoutDataFactory.change_gridLayoutData(c, col);
		}

	}

	private class ownActionErase extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_SEARCH_AVV_CODES.this.tf_4_anzeige.setText("");
			FZ_SEARCH_AVV_CODES.this.tf_4_id.setText("");
		}
	}

	public String hole_StandardAVV_Code_kleinanlieferer() throws myException {
		RECORD_ARTIKEL art = this.get_actual_artikel();
		if (art == null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Sortenbezeichung definieren !"));
			return null;
		} else {
			if (S.isFull(art.ufs(ARTIKEL.id_eak_code, ""))) {
				this.found_and_stored_code = new RECORD_EAK_CODE(art.ufs(ARTIKEL.id_eak_code, ""));
				this.tf_4_id.setText(art.fs(ARTIKEL.id_eak_code));
				this.FillLabelWithDBQuery(art.ufs(ARTIKEL.id_eak_code));
				return art.ufs(ARTIKEL.id_eak_code);
			} else {
				this.tf_4_id.setText("");
				this.FillLabelWithDBQuery("");
				return null;
			}
		}
	}

	public String hole_StandardAVV_Code_ex_mandant() throws myException {
		RECORD_ARTIKEL art = this.get_actual_artikel();
		if (art == null) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Sortenbezeichung definieren !"));
			return null;
		} else {
			if (S.isFull(art.ufs(ARTIKEL.id_eak_code_ex_mandant, ""))) {
				this.found_and_stored_code = new RECORD_EAK_CODE(art.ufs(ARTIKEL.id_eak_code_ex_mandant, ""));
				this.tf_4_id.setText(art.fs(ARTIKEL.id_eak_code_ex_mandant));
				this.FillLabelWithDBQuery(art.ufs(ARTIKEL.id_eak_code_ex_mandant));
				return art.ufs(ARTIKEL.id_eak_code_ex_mandant);
			} else {
				this.tf_4_id.setText("");
				this.FillLabelWithDBQuery("");
				return null;
			}
		}
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		throw new myExceptionCopy("FZ_SEARCH_AVV_CODES:get_Copy:copy-error!");
	}

	@Override
	public void set_bEnabled_For_Edit(boolean p_enabled) throws myException {
		boolean enabled = p_enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled();
		this.ButtonSelect.set_bEnabled_For_Edit(enabled);
		this.ButtonErase.set_bEnabled_For_Edit(enabled);
		this.oButtonStdAVV.set_bEnabled_For_Edit(enabled);
	}

	public void FillLabelWithDBQuery(String p_id_avv_code) throws myException {

		String id_avv_code = "";
		MyLong l_id = new MyLong(p_id_avv_code);
		if (l_id.isOK()) {
			id_avv_code = l_id.get_cUF_LongString();
		}

		// zuerst die uebergabe eines leeren wertes abfangen
		if (bibALL.isEmpty(p_id_avv_code)) {
			this.tf_4_anzeige.setText("");
			this.tf_4_anzeige.setToolTipText("");
			this.tf_4_id.setText("");
			return;
		}

		String cInfoText = "";

		String cSQL1 = "SELECT    JT_EAK_BRANCHE.KEY_BRANCHE, " + " JT_EAK_GRUPPE.KEY_GRUPPE,"
				+ " JT_EAK_CODE.KEY_CODE, " + " JT_EAK_CODE.CODE ," + " JT_EAK_CODE.ID_EAK_CODE, "
				+ " NVL(JT_EAK_CODE.GEFAEHRLICH,'N') " + " FROM " + bibE2.cTO() + ".JT_EAK_GRUPPE," + bibE2.cTO()
				+ ".JT_EAK_BRANCHE," + bibE2.cTO() + ".JT_EAK_CODE " + " WHERE "
				+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"
				+ " JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND " + " JT_EAK_CODE.ID_EAK_CODE="
				+ id_avv_code;

		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL1, "");

		if (cErgebnis == null) {
			cInfoText = "@@@ERROR ";
		} else if (cErgebnis.length == 0) {
			cInfoText = "";
		} else {
			String cIDSprache = bibALL.get_ID_SPRACHE_USER();
			String cUebersetzung = bibDB.EinzelAbfrage("SELECT CODE_UEBERSETZUNG FROM " + bibE2.cTO()
					+ ".JT_EAK_CODE_SP WHERE ID_EAK_CODE=" + cErgebnis[0][4] + " AND ID_SPRACHE=" + cIDSprache, "", "",
					"");

			String cGefaehrlich = "   ";
			if (cErgebnis[0][5].toUpperCase().equals("Y"))
				cGefaehrlich = "(*)";

			if (!bibALL.isEmpty(cUebersetzung))
				cErgebnis[0][3] = cUebersetzung;

			cInfoText = cErgebnis[0][0] + " " + cErgebnis[0][1] + " " + cErgebnis[0][2] + " " + cGefaehrlich + " "
					+ cErgebnis[0][3];
		}

		this.tf_4_anzeige.setText(cInfoText);
		this.tf_4_anzeige.setToolTipText(cInfoText);
	}

	/// ++++++++++++++++++++ die actionAgents fuer die buttons

	private class actionStartSearch extends XX_ActionAgent {

		public void executeAgentCode(ExecINFO oExecInfo) {
			FZ_SEARCH_AVV_CODES oThis = FZ_SEARCH_AVV_CODES.this;

			WindowToSearch oWindow = new WindowToSearch();
			oWindow.set_bVisible_Row_For_Messages(false);

			try {
				/*
				 * nachsehen, ob ein wert vorhanden war
				 */
				MyLong l_id_avv = new MyLong(oThis.tf_4_id.getText());
				if (l_id_avv.get_bOK()) {
					try {
						EAK_DataRecordHashMap_CODE hmCode = new EAK_DataRecordHashMap_CODE(
								l_id_avv.get_cUF_LongString());

						String cID_Gruppe = hmCode.get_hmGruppe().get_UnFormatedValue("ID_EAK_GRUPPE");
						String cID_Branche = hmCode.get_hmBranche().get_UnFormatedValue("ID_EAK_BRANCHE");

						// branche auswaehlen und gruppe anzeigen
						oWindow.fill_ColumnBranche();
						oWindow.get_oListBoxBranche().set_ActiveValue_OR_FirstValue(cID_Branche);

						oWindow.fill_ColumnGruppe(cID_Branche);
						oWindow.get_oListBoxGruppe().set_ActiveValue_OR_FirstValue(cID_Gruppe);

						oWindow.fill_ColumnCode(cID_Gruppe);
						oWindow.get_oListBoxCode().set_ActiveValue_OR_FirstValue(l_id_avv.get_cUF_LongString());
					} catch (Exception ex) {
					}
				}

				oWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(610),
						new MyE2_String("Suche Abfall-Code ..."));

			} catch (myException ex) {
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}

	private class WindowToSearch extends E2_BasicModuleContainer {
		// row mit den columns Branche/gruppe/code
		MyE2_Column oColForSelect = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());

		MyE2_Column oColumnBranche = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column oColumnGruppe = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column oColumnCode = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());

		MyE2_ListBox oListBoxBranche = null;
		MyE2_ListBox oListBoxGruppe = null;
		MyE2_ListBox oListBoxCode = null;

		public WindowToSearch() {
			super();

			this.add(this.oColForSelect);

			this.oColForSelect.add(oColumnBranche, E2_INSETS.I_1_1_1_1);
			this.oColForSelect.add(oColumnGruppe, E2_INSETS.I_1_1_1_1);
			this.oColForSelect.add(oColumnCode, E2_INSETS.I_1_1_1_1);

			this.fill_ColumnBranche();
		}

		public void fill_ColumnBranche() {
			this.oColumnBranche.removeAll();
			this.oColumnGruppe.removeAll();
			this.oColumnCode.removeAll();

			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '|| NVL(JT_EAK_BRANCHE.BRANCHE,'-'), JT_EAK_BRANCHE.ID_EAK_BRANCHE "
					+ " FROM " + bibE2.cTO() + ".JT_EAK_BRANCHE ORDER BY KEY_BRANCHE ";

			String[][] cBranche = bibDB.EinzelAbfrageInArray(cSQL1, "");
			if (cBranche == null)
				this.oColumnBranche.add(new MyE2_Label("@@@ ERROR"));
			else if (cBranche.length == 0)
				this.oColumnBranche.add(new MyE2_Label("@@@ NO RESULT"));
			else {
				try {
					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();

					String cSQL = "SELECT JT_EAK_BRANCHE.ID_EAK_BRANCHE,JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG "
							+ " FROM " + bibE2.cTO() + ".JT_EAK_BRANCHE_SP," + bibE2.cTO() + ".JT_EAK_BRANCHE "
							+ " WHERE " + " JT_EAK_BRANCHE_SP.ID_EAK_BRANCHE=JT_EAK_BRANCHE.ID_EAK_BRANCHE AND "
							+ " JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG IS NOT NULL AND "
							+ " JT_EAK_BRANCHE_SP.ID_SPRACHE=" + cIDSprache;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL, false);

					for (int i = 0; i < cBranche.length; i++) {
						String cNeuerWert = oDMI.get_Result_without_Exception(cBranche[i][1], 1);
						if (!bibALL.isEmpty(cNeuerWert))
							cBranche[i][0] = cNeuerWert;
					}

					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccBranche = new String[cBranche.length + 1][2];
					ccBranche[0][0] = "--";
					ccBranche[0][1] = "--";
					for (int i = 0; i < cBranche.length; i++) {
						ccBranche[i + 1][0] = cBranche[i][0];
						ccBranche[i + 1][1] = cBranche[i][1];
					}

					this.oListBoxBranche = new MyE2_ListBox(ccBranche, null, false);
					oListBoxBranche.setFont(new E2_FontItalic(-2));
					oListBoxBranche.add_oActionAgent(new actionAgentListBoxBranche());
					oListBoxBranche.setHeight(new Extent(170));
					this.oColumnBranche.add(oListBoxBranche);
				} catch (myException ex) {
					this.oColumnBranche.add(new MyE2_Label(ex.get_ErrorMessage()));
				}

			}
		}

		public void fill_ColumnGruppe(String cID_BRANCHE) {
			this.oColumnGruppe.removeAll();
			this.oColumnCode.removeAll();

			if (cID_BRANCHE.equals("--")) // leereintrag
				return;

			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_GRUPPE.KEY_GRUPPE||' - '||  NVL(JT_EAK_GRUPPE.GRUPPE,'-'), JT_EAK_GRUPPE.ID_EAK_GRUPPE "
					+ " FROM " + bibE2.cTO() + ".JT_EAK_BRANCHE," + bibE2.cTO() + ".JT_EAK_GRUPPE " + " WHERE "
					+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "
					+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE=" + cID_BRANCHE;

			String[][] cGruppe = bibDB.EinzelAbfrageInArray(cSQL1, "");
			if (cGruppe == null)
				this.oColumnGruppe.add(new MyE2_Label("@@@ ERROR"));
			else if (cGruppe.length == 0)
				this.oColumnGruppe.add(new MyE2_Label("@@@ NO RESULT"));
			else {
				try {

					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();

					String cSQL = "SELECT JT_EAK_GRUPPE.ID_EAK_GRUPPE,JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_GRUPPE.KEY_GRUPPE||' - '||JT_EAK_GRUPPE_SP.GRUPPE_UEBERSETZUNG "
							+ " FROM " + bibE2.cTO() + ".JT_EAK_BRANCHE, " + bibE2.cTO() + ".JT_EAK_GRUPPE, "
							+ bibE2.cTO() + ".JT_EAK_GRUPPE_SP " + " WHERE "
							+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE=JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "
							+ " JT_EAK_GRUPPE_SP.ID_EAK_GRUPPE=JT_EAK_GRUPPE.ID_EAK_GRUPPE AND "
							+ " JT_EAK_GRUPPE_SP.GRUPPE_UEBERSETZUNG IS NOT NULL AND " + " JT_EAK_GRUPPE_SP.ID_SPRACHE="
							+ cIDSprache + " AND  " + " JT_EAK_BRANCHE.ID_EAK_BRANCHE=" + cID_BRANCHE;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL, false);

					for (int i = 0; i < cGruppe.length; i++) {
						String cNeuerWert = oDMI.get_Result_without_Exception(cGruppe[i][1], 1);
						if (!bibALL.isEmpty(cNeuerWert))
							cGruppe[i][0] = cNeuerWert;
					}

					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccGruppe = new String[cGruppe.length + 1][2];
					ccGruppe[0][0] = "--";
					ccGruppe[0][1] = "--";
					for (int i = 0; i < cGruppe.length; i++) {
						ccGruppe[i + 1][0] = cGruppe[i][0];
						ccGruppe[i + 1][1] = cGruppe[i][1];
					}

					this.oListBoxGruppe = new MyE2_ListBox(ccGruppe, null, false);
					oListBoxGruppe.setFont(new E2_FontItalic(-2));
					oListBoxGruppe.add_oActionAgent(new actionAgentListBoxGruppe());
					oListBoxGruppe.setHeight(new Extent(170));
					this.oColumnGruppe.add(oListBoxGruppe);
				} catch (myException ex) {
					this.oColumnGruppe.add(new MyE2_Label(ex.get_ErrorMessage()));
				}

			}

		}

		public void fill_ColumnCode(String cID_GRUPPE) {
			this.oColumnCode.removeAll();

			if (cID_GRUPPE.equals("--")) // leereintrag
				return;

			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '||" + "JT_EAK_GRUPPE.KEY_GRUPPE||' - '||"
					+ "JT_EAK_CODE.KEY_CODE||' '||" + "TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||' - '||"
					+ "  NVL(JT_EAK_CODE.CODE,'-')," + " JT_EAK_CODE.ID_EAK_CODE " + " FROM " + bibE2.cTO()
					+ ".JT_EAK_BRANCHE," + bibE2.cTO() + ".JT_EAK_GRUPPE," + bibE2.cTO() + ".JT_EAK_CODE " + " WHERE "
					+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "
					+ " JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE AND " + " JT_EAK_GRUPPE.ID_EAK_GRUPPE="
					+ cID_GRUPPE;

			String[][] cCodes = bibDB.EinzelAbfrageInArray(cSQL1, "");
			if (cCodes == null)
				this.oColumnCode.add(new MyE2_Label("@@@ ERROR"));
			else if (cCodes.length == 0)
				this.oColumnCode.add(new MyE2_Label("@@@ NO RESULT"));
			else {
				try {

					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();

					String cSQL = "SELECT JT_EAK_CODE.ID_EAK_CODE," + "JT_EAK_BRANCHE.KEY_BRANCHE||' - '||"
							+ "JT_EAK_GRUPPE.KEY_GRUPPE||' - '||" + "JT_EAK_CODE.KEY_CODE||' '||"
							+ "TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||' - '||"
							+ "  NVL(JT_EAK_CODE_SP.CODE_UEBERSETZUNG,'-')" + " FROM " + bibE2.cTO()
							+ ".JT_EAK_BRANCHE, " + bibE2.cTO() + ".JT_EAK_GRUPPE, " + bibE2.cTO() + ".JT_EAK_CODE, "
							+ bibE2.cTO() + ".JT_EAK_CODE_SP " + " WHERE "
							+ " JT_EAK_BRANCHE.ID_EAK_BRANCHE=JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "
							+ " JT_EAK_GRUPPE.ID_EAK_GRUPPE=JT_EAK_CODE.ID_EAK_GRUPPE AND "
							+ " JT_EAK_CODE_SP.ID_EAK_CODE=JT_EAK_CODE.ID_EAK_CODE AND "
							+ " JT_EAK_CODE_SP.CODE_UEBERSETZUNG IS NOT NULL AND " + " JT_EAK_CODE_SP.ID_SPRACHE="
							+ cIDSprache + " AND " + " JT_EAK_GRUPPE.ID_EAK_GRUPPE=" + cID_GRUPPE;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL, false);

					for (int i = 0; i < cCodes.length; i++) {
						String cNeuerWert = oDMI.get_Result_without_Exception(cCodes[i][1], 1);
						if (!bibALL.isEmpty(cNeuerWert))
							cCodes[i][0] = cNeuerWert;
					}

					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccCodes = new String[cCodes.length + 1][2];
					ccCodes[0][0] = "--";
					ccCodes[0][1] = "--";
					for (int i = 0; i < cCodes.length; i++) {
						ccCodes[i + 1][0] = cCodes[i][0];
						ccCodes[i + 1][1] = cCodes[i][1];
					}
					this.oListBoxCode = new MyE2_ListBox(ccCodes, null, false);
					oListBoxCode.setFont(new E2_FontItalic(-2));
					oListBoxCode.add_oActionAgent(new actionAgentListBoxCode());
					oListBoxCode.setHeight(new Extent(170));
					this.oColumnCode.add(oListBoxCode);
				} catch (myException ex) {
					this.oColumnCode.add(new MyE2_Label(ex.get_ErrorMessage()));
				}

			}
		}

		private class actionAgentListBoxBranche extends XX_ActionAgent {
			public void executeAgentCode(ExecINFO oExecInfo) {
				MyE2_ListBox oListBranche = (MyE2_ListBox) bibE2.get_LAST_ACTIONEVENT().getSource();
				try {
					WindowToSearch.this.fill_ColumnGruppe(oListBranche.get_ActualWert());
				} catch (myException ex) {
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		private class actionAgentListBoxGruppe extends XX_ActionAgent {
			public void executeAgentCode(ExecINFO oExecInfo) {
				MyE2_ListBox oListGruppe = (MyE2_ListBox) bibE2.get_LAST_ACTIONEVENT().getSource();
				try {
					WindowToSearch.this.fill_ColumnCode(oListGruppe.get_ActualWert());
				} catch (myException ex) {
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		private class actionAgentListBoxCode extends XX_ActionAgent {
			public void executeAgentCode(ExecINFO oExecInfo) {
				MyE2_ListBox oListCode = (MyE2_ListBox) bibE2.get_LAST_ACTIONEVENT().getSource();
				try {
					FZ_SEARCH_AVV_CODES oThis = FZ_SEARCH_AVV_CODES.this;
					oThis.tf_4_id.setText(oListCode.get_ActualWert());
					oThis.FillLabelWithDBQuery(oListCode.get_ActualWert());

					if (FZ_SEARCH_AVV_CODES.this.oActionAfterFound != null) {
						FZ_SEARCH_AVV_CODES.this.oActionAfterFound.executeAgentCode(oExecInfo);
					}

					oThis.found_and_stored_code = new RECORD_EAK_CODE(oListCode.get_ActualWert());

					WindowToSearch.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				} catch (myException ex) {
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		public MyE2_ListBox get_oListBoxBranche() {
			return oListBoxBranche;
		}

		public MyE2_ListBox get_oListBoxCode() {
			return oListBoxCode;
		}

		public MyE2_ListBox get_oListBoxGruppe() {
			return oListBoxGruppe;
		}

	}

	@Override
	public void rb_set_db_value_manual(String id_avv_code) throws myException {
		this.tf_4_id.setText(id_avv_code);
		this.FillLabelWithDBQuery(id_avv_code);
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
	}

	@Override
	public void setFontActive(Font Font) {
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		return Color.BLACK;
	}

	@Override
	public Font get_Unmarked_Font() {
		return new E2_FontPlain();
	}

	//////////// kopien
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD() == null) {
			this.tf_4_id.setText("");
			this.tf_4_anzeige.setText("");
		} else {
			this.tf_4_id.setText(((MyRECORD) dataObject.get_RecORD()).ufs(this.rb_KF().FIELDNAME(), ""));
		}
		this.FillLabelWithDBQuery(this.tf_4_id.getText());
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.EXT().get_RB_K();
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.EXT().set_RB_K(key);
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public MyE2IF__Component ME() throws myException {
		return this;
	}

	@Override
	public Component C_ME() throws myException {
		return this;
	}

	@Override
	public void mark_Neutral() throws myException {
		if (this.tf_4_id.get_borderWasSet() != null) {
			this.tf_4_id.setBorder(this.tf_4_id.get_borderWasSet());
		} else {
			this.setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
		}
		this.setBackground(new E2_ColorEditBackground());
	}

	@Override
	public void mark_MustField() throws myException {
		this.tf_4_id.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.tf_4_id.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.tf_4_id.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.tf_4_id.getText().trim();
	}

	public XX_ActionAgent get_actionAfterFound() {

		return oActionAfterFound;
	}

	public void set_actionAfterFound(XX_ActionAgent oActionAfterFound) {

		this.oActionAfterFound = oActionAfterFound;
	}

	public RECORD_EAK_CODE get_avv_code_selected() {
		return found_and_stored_code;
	}

	public RB_TextField get_tf_4_id() {
		return tf_4_id;
	}

	public RB_TextField get_tf_4_anzeige() {
		return tf_4_anzeige;
	}

	public MyE2_Button get_bt_Select() {
		return ButtonSelect;
	}

	public MyE2_Button get_bt_Erase() {
		return ButtonErase;
	}

	private static FZ_TEXT_SIZE get_selected_size() throws myException{
		return ENUM_MANDANT_DECISION.FIELDSIZE_4_NEW_BEWEGUNGSMODEL.is_YES()? FZ_TEXT_SIZE.SMALL:FZ_TEXT_SIZE.NORMAL;
	}
}
