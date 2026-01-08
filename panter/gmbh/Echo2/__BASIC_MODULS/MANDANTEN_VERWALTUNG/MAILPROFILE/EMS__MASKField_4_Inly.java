package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;

public class EMS__MASKField_4_Inly extends XX_FULL_DAUGHTER
{
	
	public EMS__MASKField_4_Inly(SQLFieldForPrimaryKey osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public Component build_content_for_Mask(String cValueFormated, String cValueUnformated, String cMASK_STATUS) throws myException {
	
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY)) 	{
			return new MyE2_Label(new MyE2_String("Zusätze können erst erfasst werden, wenn der Mandant gespeichert wurde!"));
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT)){
			return new EMS_LIST_BasicModule_Inlay(cValueUnformated,true);
		} else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))	{
			return new EMS_LIST_BasicModule_Inlay(cValueUnformated,false);
		} else {
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		}
	}

	@Override
	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("Inaktiv"));
	}

	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
	}

	@Override
	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Zusätze können erst erfasst werden, wenn der Mandant gespeichert wurde!")));
	}

}
