package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_CONST;

public class WF_HEAD_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4937886483751391769L;

	private static E2_ResourceIcon icon_private = E2_ResourceIcon.get_RI("privat_16.png");

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)
			throws myException {
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)
			throws myException {

		String cIDColumn = null;
		RECORD_USER oUser = null;
		String cAnzeige = "";
		Object o = null;

		// PRIVAT
		cIDColumn = oUsedResultMAP.get_FormatedValue(WF_HEAD_CONST.HASH_SONDERFELD_WORKFLOW_PRIVAT_NN);
		o = oMAP.get_hmRealComponents().get(WF_HEAD_CONST.HASH_SONDERFELD_WORKFLOW_PRIVAT_NN);
		if (S.NN(cIDColumn).equals("Y")) {
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setIcon(icon_private);
				((MyE2_DB_Label) o).setText(null);
			}
		} else {
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setIcon(null);
				((MyE2_DB_Label) o).setText(null);
			}
		}
		o = null;
		cIDColumn = null;

		// BESITZER
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_USER_BESITZER");
		if (cIDColumn != null && !cIDColumn.trim().equals("")) {
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " " + S.NN(oUser.get_NAME1_cUF());
			if (cAnzeige.trim().equals("")){
				cAnzeige = S.NN(oUser.get_NAME_cUF());
			}
			o = oMAP.get_hmRealComponents().get("ID_USER_BESITZER");

			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(cAnzeige);
			}
		}

		// Abgeschlossen von
		cAnzeige = "";
		cIDColumn = oUsedResultMAP
				.get_UnFormatedValue("ID_USER_ABGESCHLOSSEN_VON");
		if (cIDColumn != null && !cIDColumn.trim().equals("")) {
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " "+ S.NN(oUser.get_NAME1_cUF());
			if (cAnzeige.trim().equals("")){
				cAnzeige = S.NN(oUser.get_NAME_cUF());
			}
			o = oMAP.get__Comp("ID_USER_ABGESCHLOSSEN_VON");

			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) oMAP.get__Comp("ID_USER_ABGESCHLOSSEN_VON"))
						.setText(cAnzeige);

			}
		}

		// Abgeschlossen von
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_USER_SUPERVISOR");
		if (cIDColumn != null && !cIDColumn.trim().equals("")) {
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " " + S.NN(oUser.get_NAME1_cUF());
			if (cAnzeige.trim().equals("")){
				cAnzeige = S.NN(oUser.get_NAME_cUF());
			}
			o = oMAP.get_hmRealComponents().get("ID_USER_SUPERVISOR");

			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(cAnzeige);
			}
		}

		// Status
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_LAUFZETTEL_STATUS");
		if (cIDColumn != null && !cIDColumn.trim().equals("")) {
			RECORD_LAUFZETTEL_STATUS oStatus = new RECORD_LAUFZETTEL_STATUS(cIDColumn);
			cAnzeige = S.NN(oStatus.get_STATUS_cF());
			o = oMAP.get__Comp("ID_LAUFZETTEL_STATUS");

			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(cAnzeige);

				// todo: eventuell mit einem farbigen Hintergrund belegen, für
				// eine deutlichere Sicht der Dinge
				// ((MyE2_DB_Label)o).setBackground(new Color(0xFFFFCC));
			}
		}

		if (S.NN(oUsedResultMAP.get_FormatedValue("GELOESCHT")).equals("Y")) {
			oMAP.set_AllComponentsAsDeleted();
		}

		
		// Textfeld, höhe anpassen
		cAnzeige = "";
		String sText = oUsedResultMAP.get_UnFormatedValue("TEXT");
		String[] rows = sText.split("\n");
		
		o = oMAP.get_hmRealComponents().get("TEXT");

		int r = (int) Math.round((rows.length + 2) * 1.1);
		if (r > 10) r = 10;
		
		if (o instanceof MyE2_DB_TextArea) {
			((MyE2_DB_TextArea) o).set_iRows(r);
		}
		
		
	}

}
