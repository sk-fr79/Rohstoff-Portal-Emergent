package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_SimpleDAUGHTER_Report extends MyE2_DBC_DaughterTable
{
	
	
	public MMD_MASK_SimpleDAUGHTER_Report(	SQLFieldMAP 		fieldMAPMotherTable, 
											E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapJASPER = new SQLFieldMAP(_DB.MAIL_AUS_MASK_JASPER ,bibE2.get_CurrSession());
		oSQLFieldMapJASPER.addCompleteTable_FIELDLIST(
				_DB.MAIL_AUS_MASK_JASPER,
				":"+_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK+
				":"+_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK_JASPER+
				":ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		
		
		oSQLFieldMapJASPER.add_SQLField(
				new SQLFieldForPrimaryKey(	_DB.MAIL_AUS_MASK_JASPER,
											_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK_JASPER,
											_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK_JASPER,
											new MyE2_String("ID"),bibE2.get_CurrSession(),
											"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MAIL_AUS_MASK_JASPER.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapJASPER.add_SQLField(new SQLFieldJoinOutside(_DB.MAIL_AUS_MASK_JASPER,_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK,_DB.MAIL_AUS_MASK_JASPER$ID_MAIL_AUS_MASK,
											new MyE2_String("ID-Mail-aus-Mask"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField(_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK)), false);

		oSQLFieldMapJASPER.get_(_DB.MAIL_AUS_MASK_JASPER$REPORTNAME).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapJASPER.get_(_DB.MAIL_AUS_MASK_JASPER$DOWNLOADNAME).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapJASPER.initFields();
		
		E2_ComponentMAP 			oMapJasper = 			new E2_ComponentMAP(oSQLFieldMapJASPER);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 		new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_JasperReport =		new MyE2_DB_TextField(oSQLFieldMapJASPER.get_SQLField(_DB.MAIL_AUS_MASK_JASPER$REPORTNAME),true,150,0,false);
		MyE2_DB_TextField			oTF_JasperDownload = 	new MyE2_DB_TextField(oSQLFieldMapJASPER.get_SQLField(_DB.MAIL_AUS_MASK_JASPER$DOWNLOADNAME),true,150,0,false);

		
		oTF_JasperReport.EXT().set_oColExtent(new Extent(155));
		oTF_JasperDownload.EXT().set_oColExtent(new Extent(155));
		
		
		oMapJasper.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapJasper.add_Component(oTF_JasperReport,new MyE2_String("Jasper-Datei"));
		oMapJasper.add_Component(oTF_JasperDownload,new MyE2_String("Downloadname"));
		
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL01, "01");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL02, "02");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL03, "03");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL04, "04");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL05, "05");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL06, "06");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL07, "07");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL08, "08");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL09, "09");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL10, "10");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL11, "11");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL12, "12");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL13, "13");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL14, "14");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL15, "15");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL16, "16");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL17, "17");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL18, "18");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL19, "19");
		this.add_col(oMapJasper, oSQLFieldMapJASPER, MMD__CONST.COL20, "20");
		
			
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));

		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapJasper,
							null);
		
	}

	
	private void add_col(	E2_ComponentMAP    	oMapJasper,
							SQLFieldMAP 		oSQLFieldMapJASPER,
							String     		   	cHASHKEY,
							String             	NumberTitel) throws myException {
		MyE2_DB_MultiComponentColumn  oCOL = new MyE2_DB_MultiComponentColumn();
		E2_Font  oFont = new E2_FontPlain(-2);
		MyE2_DB_TextField	oTF_JasperPARAM = 	new MyE2_DB_TextField(oSQLFieldMapJASPER.get_SQLField("PARAMETER"+NumberTitel),true,120,0,false,oFont);
		MyE2_DB_TextField	oTF_JasperWERT = 	new MyE2_DB_TextField(oSQLFieldMapJASPER.get_SQLField("WERT"+NumberTitel),true,120,0,false,oFont);

		oCOL.add_Component(oTF_JasperPARAM, new MyE2_String("Param."+NumberTitel), null);
		oCOL.add_Component(oTF_JasperWERT, new MyE2_String("Wert "+NumberTitel), null);
		oMapJasper.add_Component(cHASHKEY, oCOL, new MyE2_String("Block "+NumberTitel));

	}
							
							
							
	
	
}
