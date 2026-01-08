package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BANKENSTAMM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSK_MASK_BankInfo extends E2_Grid implements MyE2IF_DB_SimpleComponent, E2_IF_Copy{

	//tabelle, die infos zur Bank in der maske anzeigt
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		
		this._clear();
		
		MyLong l = new MyLong(oResultMAP.get_UnFormatedValue(BANKENSTAMM.id_bankenstamm.fn()));

		this.fill(l);
	}


	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
		this._setSize(150,400);
		RB_gld		ld_titel = new RB_gld()._left_mid()._col(new E2_ColorDDark());
		RB_gld		ld_inner = new RB_gld()._left_mid()._col(new E2_ColorDark());
		
		this._a(new RB_lab()._tr("Bank-Info")._fo_italic(),		ld_titel)._a("");
		this._a(new RB_lab()._tr("Name")._fo_italic(), 			ld_inner)._a(new RB_lab(""), 	ld_inner);
		this._a(new RB_lab()._tr("Ort")._fo_italic(), 			ld_inner)._a(new RB_lab(""), 	ld_inner);
		this._a(new RB_lab()._tr("BLZ")._fo_italic(), 			ld_inner)._a(new RB_lab(""), 	ld_inner);
		this._a(new RB_lab()._tr("BIC/Swift-Adresse")._fo_italic(), ld_inner)._a(new RB_lab(""),ld_inner);
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FSK_MASK_BankInfo();
	}
	
	
	public void fill(MyLong l) throws myException {
		this._clear();
		if (l.isOK()) {
			
			this._setSize(150,400);
			try {
				Rec20 		recB = 	new Rec20(_TAB.bankenstamm)._fill_id(l.get_cUF_LongString());
				Rec20 		recA = 	recB.get_up_Rec20(ADRESSE.id_adresse);
	
				RB_gld		ld_titel = new RB_gld()._left_mid()._col(new E2_ColorDDark());
				RB_gld		ld_inner = new RB_gld()._left_mid()._col(new E2_ColorDark());
				
				this._a(new RB_lab()._tr("Bank-Info")._fo_italic(),		ld_titel)._a("");
				this._a(new RB_lab()._tr("Name")._fo_italic(), 			ld_inner)._a(new RB_lab(recA.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2)), 	ld_inner);
				this._a(new RB_lab()._tr("Ort")._fo_italic(), 			ld_inner)._a(new RB_lab(recA.get_ufs_kette(" ", ADRESSE.ort)), 					ld_inner);
				this._a(new RB_lab()._tr("BLZ")._fo_italic(), 			ld_inner)._a(new RB_lab(recB.get_ufs_kette(" ", BANKENSTAMM.bankleitzahl)), 	ld_inner);
				this._a(new RB_lab()._tr("BIC/Swift-Adresse")._fo_italic(), ld_inner)._a(new RB_lab(recB.get_ufs_kette(" ", BANKENSTAMM.swiftcode)), 	ld_inner);
			} catch (myException e) {
				this.prepare_ContentForNew(false);
				e.printStackTrace();
			}
		}
	}
	
}
