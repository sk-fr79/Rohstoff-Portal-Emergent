package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/*
 * neues SQLfield, was die Referenz-E2_ComponentMap mit uebergeben bekommt. Damit kann beim aufbau der
 * Listenabfragen dafuer gesorgt werden, dass solche felder, die zeitaufwaendig sind (Z.B. wegen subquerys) nur
 * mit abgefrage werden, wenn diese auch tatsaechlich sichtbar sind
 */
public class SQLField_OnlyInQueryWhenVisible extends SQLField
{
	private E2_ComponentMAP  oMAP_this_belongs_to = null;
	

	public SQLField_OnlyInQueryWhenVisible(String fieldAusdruck, String cfieldlabel, MyString cfieldlabelforUser, E2_ComponentMAP  oMAP) throws myException
	{
		super(fieldAusdruck, cfieldlabel, cfieldlabelforUser,  bibE2.get_CurrSession());
		this.oMAP_this_belongs_to = oMAP;
	}


	public E2_ComponentMAP get_oComponentMAP_REF_this_belongs_to()
	{
		return oMAP_this_belongs_to;
	}

	/**
	 * ist die spalte ausgeblendet, dann wird NULL als ergebnis zurueckgegeben
	 * @return
	 */
	public String  get_DummySelectBlock()
	{
		return "NULL as "+this.get_cFieldLabel();
	}


	
	
}
