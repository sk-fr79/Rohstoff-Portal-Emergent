package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
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

public class MMD_MASK_SimpleDAUGHTER_MailVerteiler extends MyE2_DBC_DaughterTable
{
	
	public MMD_MASK_SimpleDAUGHTER_MailVerteiler(	SQLFieldMAP 		fieldMAPMotherTable, 
													E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapEMAIL = new SQLFieldMAP(_DB.MAIL_AUS_MASK_EMAIL ,bibE2.get_CurrSession());
		oSQLFieldMapEMAIL.addCompleteTable_FIELDLIST(
				_DB.MAIL_AUS_MASK_EMAIL,
				":"+_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK+
				":"+_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK_EMAIL+
				":ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		
		
		oSQLFieldMapEMAIL.add_SQLField(
				new SQLFieldForPrimaryKey(	_DB.MAIL_AUS_MASK_EMAIL,
											_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK_EMAIL,
											_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK_EMAIL,
											new MyE2_String("ID"),bibE2.get_CurrSession(),
											"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MAIL_AUS_MASK_EMAIL.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapEMAIL.add_SQLField(new SQLFieldJoinOutside(_DB.MAIL_AUS_MASK_EMAIL,_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK,_DB.MAIL_AUS_MASK_EMAIL$ID_MAIL_AUS_MASK,
											new MyE2_String("ID-Mail-aus-Mask"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField(_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK)), false);

		oSQLFieldMapEMAIL.get_(_DB.MAIL_AUS_MASK_EMAIL$MAILADRESSE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapEMAIL.initFields();
		
		E2_ComponentMAP 			oMapEmail = 			new E2_ComponentMAP(oSQLFieldMapEMAIL);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 		new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_MailAdresse = 		new MyE2_DB_TextField(oSQLFieldMapEMAIL.get_SQLField(_DB.MAIL_AUS_MASK_EMAIL$MAILADRESSE),true,245,0,false);
		
		oTF_MailAdresse.EXT().set_oColExtent(new Extent(250));
		
		oTF_MailAdresse.EXT_DB().set_bIsSortable(false);
		
		oMapEmail.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapEmail.add_Component(oTF_MailAdresse,new MyE2_String("Mailadresse"));
			
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));

		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapEmail,
							null);
		
	}

	
}
