package panter.gmbh.Echo2.components.DB.QUALIFIER;

import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Q_DEF_CheckBox extends MyE2_CheckBox implements Comparable<Q_DEF_CheckBox>
{
	/*
	 * wird ein element erzeugt, dann exististiert ein datenbankeintrag oder nicht,
	 * bei existentem datenbank-eintrag  wird eine RECORD_QUALIFIER uebergeben oder bei nichtvorhandene, der evtl. geloescht wird,
	*/
	private RECORD_QUALIFIER        	recQualifier = 		null;
	private Q_DEF_Element               oDefElement = 		null;
	
	
	private MyE2IF__Component  			oComponentMother = null;
	
	public Q_DEF_CheckBox(Q_DEF_Element defElement, Font oFont) throws myException
	{
		super();
		this.oDefElement = 		defElement;
		
		this.setText(this.oDefElement.get_cLONG_TEXT_4_USER().CTrans());
		
		this.setFont(oFont);
		
		this.setSelected(false);
		
		this.setToolTipText(this.oDefElement.get_cToolTipText().CTrans());
		
		this.oComponentMother = this.oDefElement.get_oMotherComponent();
		
	}


	
	
	public Q_DEF_CheckBox(Q_DEF_Element defElement,  RECORD_QUALIFIER   rQualifier) throws myException
	{
		super();
		this.oDefElement = 		defElement;
		
		this.setText(this.oDefElement.get_cLONG_TEXT_4_USER().CTrans());
		
		//wird ein bestehender Record uebergeben, dann heist das automatisch: checkbox aktiv
		this.recQualifier = rQualifier;
		this.setSelected(recQualifier!=null);

		this.oComponentMother = this.oDefElement.get_oMotherComponent();

	}

	
	
	
	
	public RECORD_QUALIFIER get_RecQualifier()
	{
		return recQualifier;
	}



	public void set_RecQualifier(RECORD_QUALIFIER recQualifier) throws myException
	{
		this.recQualifier = recQualifier;
		this.setSelected(this.recQualifier!=null);
	}

	
	public boolean get_bSelected_old()
	{
		return (this.recQualifier!=null);
	}
	
	
	public boolean get_bSelected_new()
	{
		return (this.isSelected());
	}




	public Q_DEF_Element get_oDefElement()
	{
		return oDefElement;
	}
	
	
	public int compareTo(Q_DEF_CheckBox vergleich)
	{
		return S.NN(this.get_oDefElement().get_cSORTSTRING()).compareTo(S.NN(vergleich.get_oDefElement().get_cSORTSTRING()));
	}
	
	
	public void Reset_To_Old_State()
	{
		this.setSelected(this.get_bSelected_old());
	}




	public MyE2IF__Component get_oComponentMother()
	{
		return oComponentMother;
	}




	public void set_oComponentMother(MyE2IF__Component oComponentMother)
	{
		this.oComponentMother = oComponentMother;
	}
	
}
