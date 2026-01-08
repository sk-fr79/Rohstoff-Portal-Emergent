package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK.HADM_bt_Delete;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK.HADM_bt_Edit;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK.HADM_bt_Upload;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.AR_DataRow;
import panter.gmbh.Echo2.components.activeReport_NG.AR_LayoutData;
import panter.gmbh.Echo2.components.activeReport_NG.AR_MultiComponentGrid;
import panter.gmbh.Echo2.components.activeReport_NG.AR_MultiLineLabel;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VERSION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class HAD_HelpText_DataRow extends AR_DataRow {

	//sammelvektor von oben. damit wird eine info transportiert, welche datensaetze markiert sind
	private Vector<HAD__CheckBox>   v_cbs = null;

	
	private RECORD_HILFETEXT  	recHelpText = null;
	private String 				textEnthalten = null;
	
	private String[] 			vergleichsfelder = { HILFETEXT.hilfetext.fn()
													,HILFETEXT.titel.fn()
													,HILFETEXT.id_hilfetext.fn()
													};
	
	
	public HAD_HelpText_DataRow(RECORD_HILFETEXT _recHelpText, String p_textEnthalten, Vector<HAD__CheckBox>   p_v_cbs) throws myException {
		super();
		this.recHelpText = _recHelpText;
		this.textEnthalten = p_textEnthalten;
		this.v_cbs = p_v_cbs;
	

		this.add_daughter_DataBlock(new HAD_HelpImage_DataBlock(this.recHelpText));
		
	}

	@Override
	public IF_AR_Component[][] _generate_Components() throws myException {
		IF_AR_Component[][] comp = new  IF_AR_Component[bibALL.get_RECORD_USER().is_DEVELOPER_YES()?2:1][5];
		
		RECORD_VERSION  rv = this.recHelpText.get_UP_RECORD_VERSION_id_version();
		String cVersion = (rv!=null?rv.get_VERSION_CODE_cUF_NN("-"):"-");
		
		RECORD_USER     user_bearbeiter = this.recHelpText.get_UP_RECORD_USER_id_user_bearbeiter();
		RECORD_USER     user_ursprung = this.recHelpText.get_UP_RECORD_USER_id_user_ursprung();
		

		String          basisModulKenner = this.recHelpText.get_MODULKENNER_cUF_NN("");
		String    		basisModulText = "";
		MODUL           basisM =  null;
		if (S.isFull(basisModulKenner)) {
			basisM = E2_MODULNAME_ENUM.find_Corresponding_Enum(basisModulKenner); 
		}
		if (basisM != null) {
			if (basisM.get_userInfo()!=null) {
				basisModulText = basisM.get_userInfo().CTrans();
			}
		}
		
		
		String cNameBearbeiter = (user_bearbeiter!=null?user_bearbeiter.get___KETTE(USER.vorname.fn(),USER.name1.fn()):"-");
		String cNameUrsprung =   (user_ursprung!=null?user_ursprung.get___KETTE(USER.vorname.fn(),USER.name1.fn()):"-");
		
		String c_status = "";
		if (S.isFull(this.recHelpText.get_STATUS_cUF_NN(""))) {
			c_status = S.NN(HAD___CONST.get_hm_status().get(this.recHelpText.get_STATUS_cUF_NN("")));
		}
		
		int i=0;
		HAD__CheckBox cb = new HAD__CheckBox(		new glStd(1),this.recHelpText,new E2_FontPlain());
		this.v_cbs.add(cb);
		if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			comp[0][i++] = cb ;
		} else {
			comp[0][i++] = new HAD_comp_Label(		new glStd(1),this.recHelpText.get_ID_HILFETEXT_cF(),new E2_FontPlain());
		}
		comp[0][i++] = new AR_MultiLineLabel(	new glStd(2),new E2_FontBold(), S.NN(basisModulText,"-"),new E2_FontPlain(),	cVersion, c_status);
		comp[0][i++] = new AR_MultiLineLabel(	new glStd(14),new E2_FontBold(),	this.recHelpText.get_TITEL_cF_NN(""),new E2_FontPlain(),this.recHelpText.get_HILFETEXT_cUF_NN(""));
		comp[0][i++] = new AR_MultiLineLabel(	new glStd(2),new E2_FontPlain(),cNameUrsprung,cNameBearbeiter);
		
		//in die letzte spalte die buttons 
		comp[0][i++] = new AR_MultiComponentGrid(new glStd(1), 
													MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS(), 
													new HADM_bt_Edit(this.recHelpText.get_ID_HILFETEXT_cUF()),
													new HADM_bt_Delete(this.recHelpText.get_ID_HILFETEXT_cUF()),
													new HADM_bt_Upload(this.recHelpText.get_ID_HILFETEXT_cUF()));;
		
		i=0;
		//2.zeile, wenn der benutzer ein entwickler ist
		if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			comp[1][i++] = new HAD_comp_Label(new glStd(1),"",	new E2_FontPlain());
			comp[1][i++] = new HAD_comp_Label(new glStd(2),"",	new E2_FontPlain());
			comp[1][i++] = new AR_MultiLineLabel(new glStd(14),new E2_FontPlain(),	this.recHelpText.get_INFO_DEVELOPER_cUF_NN(""));
			comp[1][i++] = new HAD_comp_Label(new glStd(2),"",	new E2_FontPlain());
			comp[1][i++] = new HAD_comp_Label(new glStd(1),"",	new E2_FontPlain());
		}
		
		
		return comp;
	}

	@Override
	public IF_AR_Component[][] _generate_titelComponentsInFrontOfRow()	throws myException {
		return null;
	}

	@Override
	public IF_AR_Component[][] _generate_footComponentsAfterRow()	throws myException {
		
		IF_AR_Component[][] comp = new IF_AR_Component[1][1];
		
		comp[0][0] = new HAD_comp_Label(new AR_LayoutData(20, E2_INSETS.I(0,5,5,5),0),"",new E2_FontBold());
	
		return comp;
	}

	@Override
	public boolean _must_be_filled() throws myException {
		if (S.isFull(this.textEnthalten)) {
			boolean bRueck = false;
			
			for (String feld: this.vergleichsfelder) {
				bRueck = bRueck || this.recHelpText.get_UnFormatedValue(feld,"").toUpperCase().contains(this.textEnthalten.trim().toUpperCase());
			}
			
			return bRueck;
		}
		
		return true; 
	}


	private class glStd extends AR_LayoutData {
		public glStd(int columnspan) {
			super(columnspan, E2_INSETS.I(2,2,4,3), new Alignment(Alignment.LEFT, Alignment.TOP),-20);
		}
		
	}
	
}
