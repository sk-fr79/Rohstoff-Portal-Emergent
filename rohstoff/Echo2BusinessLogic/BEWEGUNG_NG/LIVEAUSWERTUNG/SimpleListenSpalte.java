package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class SimpleListenSpalte
{
	private String       	cSpalteName = null;
	private String   		cBeschreibungsText = null;
	private int      		iSpaltenBreite = 0;
	private String   		cToolTips4Header = null;
	
	//fuer die anzeige gibt es zwei feldtypen: myE2_LabelInRow und MyE2_DB_CheckBox
	private boolean 		bIsCheckBox = false;  
	private SQLField  		oSQLField = null;
	
	private MyMetaFieldDEF  oMetaDef = null;
	
	public SimpleListenSpalte(String SpalteName, String BeschreibungsText, int SpaltenBreite)
	{
		super();
		this.cSpalteName = 			SpalteName;
		this.cBeschreibungsText = 	BeschreibungsText;
		this.iSpaltenBreite = 		SpaltenBreite;
	}

	public SimpleListenSpalte(String SpalteName, String BeschreibungsText, int SpaltenBreite, String ToolTips4Header)
	{
		super();
		this.cSpalteName = 			SpalteName;
		this.cBeschreibungsText = 	BeschreibungsText;
		this.iSpaltenBreite = 		SpaltenBreite;
		this.cToolTips4Header = 	ToolTips4Header;
	}

	public void init(SQLFieldMAP oSQLFieldMap) throws myException
	{
	    if (oSQLFieldMap.containsKey(this.cSpalteName))
	    {
			SQLField  oSQLField = oSQLFieldMap.get(this.cSpalteName);
			
			if (oSQLField.get_oFieldMetaDef().get_bIsTextType() && 
				oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()==1)
			{
				this.bIsCheckBox = 	true;
			}
			else
			{
				this.bIsCheckBox = false;
			}
			this.oSQLField = 	oSQLField;
			this.oMetaDef = 	oSQLField.get_oFieldMetaDef();
	    }
	    else
	    {
	    	throw new myException(this,":init: Field is not in SQLFieldMAP:"+this.cSpalteName);
	    }
	}
	
	
	public MyE2IF__DB_Component get_Component() throws myException
	{
		MyE2IF__DB_Component oCompRueck = null;
		if (this.bIsCheckBox)
		{
			oCompRueck = new MyE2_DB_CheckBox(this.oSQLField);
		}
		else
		{
			oCompRueck = new MyE2_DB_Label_INROW(this.oSQLField, true, this.iSpaltenBreite);
		}
		
		if (S.isFull(this.cToolTips4Header))
		{
			oCompRueck.EXT().set_ToolTipStringForListHeader(new MyE2_String(this.cToolTips4Header));
		}
		
		if (this.iSpaltenBreite>0)
		{
			oCompRueck.EXT().set_oColExtent(new Extent(this.iSpaltenBreite));
		}
		
		
		
		return oCompRueck;
	}
	
	
	
	public MyString get_oTitleString()
	{
		if (S.isFull(this.cBeschreibungsText))
		{
			return new MyE2_String(this.cBeschreibungsText);
		}
		
		return new MyE2_String(this.cSpalteName);
	}

	
	
	
	public String get_cSpalteName()
	{
		return cSpalteName;
	}

	public String get_cBeschreibungsText()
	{
		return cBeschreibungsText;
	}

	public int get_iSpaltenBreite()
	{
		return iSpaltenBreite;
	}

	public String get_cToolTips()
	{
		return cToolTips4Header;
	}

	public boolean get_bIsCheckBox()
	{
		return bIsCheckBox;
	}

	public SQLField get_oSQLField()
	{
		return oSQLField;
	}

	public MyMetaFieldDEF get_oMetaDef()
	{
		return oMetaDef;
	}

	public void set_cBeschreibungsText(String cBeschreibungsText)
	{
		this.cBeschreibungsText = cBeschreibungsText;
	}

	public void set_iSpaltenBreite(int iSpaltenBreite)
	{
		this.iSpaltenBreite = iSpaltenBreite;
	}

	public void set_cToolTips(String cToolTips)
	{
		this.cToolTips4Header = cToolTips;
	}


}
