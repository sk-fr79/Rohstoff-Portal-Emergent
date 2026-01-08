package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;


import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT_NG;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST__DRUCKFUHREN_STATEMENT_BUILDER;

public class FU_PRO_ActionAgent_StartPrintMail extends ACTIONAGENT_MAIL_AND_REPORT_NG {
	
	private E2_NavigationList  				oNaviList = 			null;
	
	private Vector<FU_PRO_JasperHash>      vFU_PRO_JASPER_HASH = new Vector<FU_PRO_JasperHash>();
	
	
	public FU_PRO_ActionAgent_StartPrintMail(	E2_NavigationList  NaviList) throws myException
	{
		super(new MyE2_String("Proformarechnung"), "PRINT_PROFORMA_RECHNUNG", "PRINT_PROFORMA_RECHNUNG", "PRINT_PROFORMA_RECHNUNG");
		this.oNaviList = NaviList;
		
	}

	
	@Override
	public E2_MassMailer get_MassMailer() throws myException
	{
		E2_MassMailer oMassMailer = new E2_MassMailer_STD(	"PROFORMA_RECHNUNG_MAIL_BETREFF",
															"PROFORMA_RECHNUNG_MAIL_TEXT",
															"PROFORMA_RECHNUNG");
		return oMassMailer;
	}


	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{
		Vector<FU_PRO_PassendeFuhrenInfos>   vFU_PRO_SuchePassendeFuhren = new Vector<FU_PRO_PassendeFuhrenInfos>();

		V_JasperHASH vJaspers = new V_JasperHASH();
		
		Vector<String> vIDs = new Vector<String>();
		
		if (this.oNaviList != null) {
			//verwendung als button in der Bedienleiste des moduls
			vIDs.addAll(this.oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only());
		} else {     
			//verwendung innerhalb der listenzeile
			if (	execInfo != null && 
					execInfo.get_MyActionEvent() != null && 
					execInfo.get_MyActionEvent().getSource() != null && 
					execInfo.get_MyActionEvent().getSource() instanceof MyE2IF__Component) {
				MyE2IF__Component  oComp=  (MyE2IF__Component)execInfo.get_MyActionEvent().getSource();
				
				if (oComp.EXT().get_oComponentMAP() != null && oComp.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null 
						&& oComp.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()!=null) {
					vIDs.add(oComp.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				}
			}
		}

		
//		FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS oFU_PRO_SuchePassendeFuhren = 
//				new FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS(vIDs);
		
		FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS_NG oFU_PRO_SuchePassendeFuhren = 
				new FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS_NG(vIDs);
		
		if (oFU_PRO_SuchePassendeFuhren.get_oMV().get_bHasAlarms()) {
			bibMSG.add_MESSAGE(oFU_PRO_SuchePassendeFuhren.get_oMV());
		} else {
			vFU_PRO_SuchePassendeFuhren.addAll(oFU_PRO_SuchePassendeFuhren.get_vFuhrenFuhrenOrt_kombi());
			
			if (vFU_PRO_SuchePassendeFuhren.size()>0) {
				for (FU_PRO_PassendeFuhrenInfos oFu_Info: vFU_PRO_SuchePassendeFuhren) {
					FU_PRO_JasperHash oHash = new FU_PRO_JasperHash(oFu_Info);
					vJaspers.add(oHash);

					oHash.set_oSettingsGrid(new FU_PRO_SettingsGrid(this, oFu_Info,oHash.get_oCB_DieseRechnungWirdGedruckt()));
				}
			}
		}
		
		this.vFU_PRO_JASPER_HASH.removeAllElements();
		
		for (E2_JasperHASH  oHash: vJaspers) {
			this.vFU_PRO_JASPER_HASH.add((FU_PRO_JasperHash)oHash);
		}
		
		return vJaspers;
	}

	
	



	@Override
	public boolean make_bActionBeforeStart(ExecINFO execInfo) throws myException {
		//this.oWaehle_DruckDatum.get_oTextField().setText(bibALL.get_cDateNOW());
		return true;
	}


	@Override
	public E2_BasicModuleContainer build_Container4Popup(ExecINFO execInfo) throws myException {
		ownContainer  oContainer4Popup = new ownContainer();
		
		int[] iBreit = {200,200,200};
		MyE2_Grid  oGrid = new MyE2_Grid(iBreit,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		//jetzt ein tabbed-pane mit pro report ein tab
		MyE2_TabbedPane  oTabbed = new MyE2_TabbedPane(450);
		
		oGrid.add(oTabbed,3,new Insets(2, 5, 2, 5));
		
		int iZaehler = 1;
		
		for (FU_PRO_JasperHash  oJHash: this.vFU_PRO_JASPER_HASH) {
			oTabbed.add_Tabb(new MyE2_String(""+(iZaehler++),false), oJHash.get_oSettingsGrid());
		}
		
		E2_MutableStyle  oStyle4Buttons = MyE2_Button.StyleTextButtonCentered(new E2_ColorDark(), new E2_ColorDark(), Color.BLACK, Color.LIGHTGRAY, new E2_FontPlain(4));
		
		MyE2_Button oButtonDruck = new MyE2_Button(new MyE2_String("Drucke"),oStyle4Buttons);
		MyE2_Button oButtonMail =  new MyE2_Button(new MyE2_String("Mail"),oStyle4Buttons);
		MyE2_Button oButtonPreview = new MyE2_Button(new MyE2_String("Vorschau"),oStyle4Buttons);
		
		oButtonDruck.setToolTipText(new MyE2_String("Erzeugt eine Druckdatei aller ausgewählten Proforma-Rechnungen").CTrans());
		oButtonMail.setToolTipText(new MyE2_String("Öffnet den Versende-Dialog für alle ausgewählten Proforma-Rechnungen").CTrans());
		oButtonPreview.setToolTipText(new MyE2_String("Abbuch des Vorgangs").CTrans());
		
		
		this.PrepareStandardPrintAndMailButton(oButtonDruck, oButtonMail, oButtonPreview);
		
		oGrid.add(oButtonDruck,new Insets(5, 10, 5, 5));
		oGrid.add(oButtonMail,new Insets(5, 10, 5, 5));
		oGrid.add(oButtonPreview,new Insets(5, 10, 5, 5));
		
		oContainer4Popup.add(oGrid,new Insets(5, 10, 5, 5));
		
		oContainer4Popup.set_oResizeHelper(new ownResizeHelper(oTabbed));
		
		return oContainer4Popup;
	}


	@Override
	public MyE2_MessageVector MakeAddonSettings_and_PreChecks_4_Print(E2_JasperHASH oJasperHash) throws myException {
		return stelle_SQL_Ein(oJasperHash,false,false);
	}


	@Override
	public MyE2_MessageVector MakeAddonSettings_and_PreChecks_4_Mail(E2_JasperHASH oJasperHash) throws myException {
		return stelle_SQL_Ein(oJasperHash,false,true);
	}


	@Override
	public MyE2_MessageVector MakeAddonSettings_and_PreChecks_4_Preview(E2_JasperHASH oJasperHash)	throws myException {
		return stelle_SQL_Ein(oJasperHash,true,false);
	}
	
	
	
	private MyE2_MessageVector stelle_SQL_Ein(E2_JasperHASH oJasperHash, boolean bPreview, boolean bMail) throws myException {

		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		FU_PRO_JasperHash  oFP_Hash = (FU_PRO_JasperHash) oJasperHash;
		
		if (oFP_Hash.get_bInactiv()) {
			return oMV;
		}
		
		//dann wird hier der RECORD_PROFORMA_RECHNUNG aktualisiert
		RECORD_PROFORMA_RECHNUNG  recProforma = oFP_Hash.get_oFU_PRO_PassendeFuhrenInfos().get_RecProforma();
		FU_PRO_SettingsGrid       oFU_PRO_SettingsGrid = oFP_Hash.get_oSettingsGrid();
		
		//hier wird geprueft, ob die eingabe korrekt ist
		oMV.add_MESSAGE(oFU_PRO_SettingsGrid.pruefe_Inputs_und_schreibe_werte_in_PROFORMA_RECHUNG_TABLE());
		
		if (oMV.get_bIsOK()) {
			
			if (!bPreview) {
				
				String cSEQ_Str = "SEQ_"+bibALL.get_ID_MANDANT()+"_"+myCONST.SONDERVORGANG_PROFORMA_RECHNUNG+".NEXTVAL";
				
				FU_PRO_PassendeFuhrenInfos oFU_PRO_PassendeFuhrenInfos = oFP_Hash.get_oFU_PRO_PassendeFuhrenInfos();
				
				oJasperHash.get_vSQL_STATEMENTS_BEFORE_REPORT().add(
							"UPDATE "+_DB.PROFORMA_RECHNUNG+" SET "+_DB.PROFORMA_RECHNUNG$BELEGNR_PROFORMA+"="+cSEQ_Str+
							" WHERE "+_DB.PROFORMA_RECHNUNG$ID_PROFORMA_RECHNUNG+"="+recProforma.get_ID_PROFORMA_RECHNUNG_cUF()+" AND "+
							_DB.PROFORMA_RECHNUNG$BELEGNR_PROFORMA+" IS NULL ");
		
				oJasperHash.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART(
							"SELECT NVL("+_DB.PROFORMA_RECHNUNG$BELEGNR_PROFORMA+",'-')||'_KD_"+oFU_PRO_PassendeFuhrenInfos.get_cID_ADRESSE_ZielFuhre_UF()+"' FROM "+
							bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+" WHERE "+_DB.PROFORMA_RECHNUNG$ID_PROFORMA_RECHNUNG+"="+oFU_PRO_PassendeFuhrenInfos.get_iID_VPOS_TPA_FUHRE());

			
				if (oFU_PRO_PassendeFuhrenInfos.get_iID_VPOS_TPA_FUHRE_ORT()>0) {
					//druckprotokoll-eintrag schreiben
					oJasperHash.get_vSQL_STATEMENTS_AFTER_REPORT().add(
							BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(
									""+oFU_PRO_PassendeFuhrenInfos.get_iID_VPOS_TPA_FUHRE(), "", "Proforma-Rechnung (Hauptfuhre)", bMail,oJasperHash));

		
				} else {
				
					//druckprotokoll-eintrag schreiben
					oJasperHash.get_vSQL_STATEMENTS_AFTER_REPORT().add(
							BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(
									""+oFU_PRO_PassendeFuhrenInfos.get_iID_VPOS_TPA_FUHRE(), "", "Proforma-Rechnung (Fuhren-Zusatzort)", bMail,oJasperHash));
				}
				
			}

			
		}
			

		return oMV;
		
	}
	
	
	private class ownContainer extends E2_BasicModuleContainer {

		public ownContainer() {
			super();
		}
	}

	private class ownResizeHelper extends XX_BasicContainerResizeHelper {
		private MyE2_TabbedPane oTabbed = null;
		public ownResizeHelper(MyE2_TabbedPane oTabbedPane) {
			super();
			this.oTabbed = oTabbedPane;
		}

		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			this.oTabbed.setWidth(new Extent(ownContainer.get_oExtWidth().getValue()-80));
			this.oTabbed.setHeight(new Extent(ownContainer.get_oExtHeight().getValue()-200));
		}
	}
	

	@Override
	public int get_iWidth_4_Popup() throws myException {
		return 700;
	}


	@Override
	public int get_iHeight_4_Popup() throws myException {
		return 500;
	}
	
	
}
