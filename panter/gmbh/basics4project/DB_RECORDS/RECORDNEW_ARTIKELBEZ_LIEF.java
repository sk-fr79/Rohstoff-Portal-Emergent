package panter.gmbh.basics4project.DB_RECORDS;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public class RECORDNEW_ARTIKELBEZ_LIEF extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_ARTIKELBEZ_LIEF";
    private _TAB  tab = _TAB.artikelbez_lief;  


	public RECORDNEW_ARTIKELBEZ_LIEF() throws myException 
	{
		super("JT_ARTIKELBEZ_LIEF");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKELBEZ_LIEF.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_ARTIKELBEZ_LIEF(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_ARTIKELBEZ_LIEF", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKELBEZ_LIEF.TABLENAME);
	}
	
	
	
	public RECORDNEW_ARTIKELBEZ_LIEF(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_ARTIKELBEZ_LIEF");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_ARTIKELBEZ_LIEF.TABLENAME);
	}

	
	
	public RECORD_ARTIKELBEZ_LIEF do_WRITE_NEW_ARTIKELBEZ_LIEF(MyE2_MessageVector oMV) throws myException
	{
	
		boolean bThrowExceptionWhenAlarm = false;
	
		if (oMV==null)
		{
			oMV=new MyE2_MessageVector();
			bThrowExceptionWhenAlarm = true;
		}
		
		//zuerst die NotNull-felder pruefen (ausser den automatismen)
		Vector<String> vExcludeFields = new Vector<String>();
		vExcludeFields.addAll(this.get_vSonderFelder());
		vExcludeFields.add("ID_ARTIKELBEZ_LIEF");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_ARTIKELBEZ_LIEF(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table ARTIKELBEZ_LIEF ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANGEBOT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANGEBOT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANGEBOT_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT_ABNEHMER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANGEBOT_ABNEHMER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANGEBOT_ABNEHMER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT_ABNEHMER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT_ABNEHMER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT_ABNEHMER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT_ABNEHMER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT_ABNEHMER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT_ABNEHMER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANGEBOT_ABNEHMER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANGEBOT_ABNEHMER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_ALTERNATIV(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_ALTERNATIV", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ2_ALTERNATIV(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ2_ALTERNATIV", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_ALTERNATIV(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_ALTERNATIV", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_ALTERNATIV(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_ALTERNATIV", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_ALTERNATIV(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_ALTERNATIV", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ2_ALTERNATIV(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ2_ALTERNATIV", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_TYP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ_TYP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ARTBEZ_TYP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ARTBEZ_TYP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_TYP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ_TYP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_TYP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ_TYP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_TYP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ_TYP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ARTBEZ_TYP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ARTBEZ_TYP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ERZEUGT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_FIXKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXKOSTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_FIXKOSTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("FIXKOSTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_FIXKOSTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXKOSTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXKOSTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXKOSTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_FIXKOSTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXKOSTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_FIXKOSTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("FIXKOSTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEAENDERT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_MECH_ZUSTAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_MECH_ZUSTAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTBEZ_VERUNREINIGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTBEZ_VERUNREINIGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKELBEZ_LIEF(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKELBEZ_LIEF(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKELBEZ_LIEF(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKELBEZ_LIEF(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKELBEZ_LIEF(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKELBEZ_LIEF(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKELBEZ_LIEF", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_BEZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_BEZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_BEZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_BEZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_EAK_CODE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_EAK_CODE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_EAK_CODE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_EAK_CODE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_MANDANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LETZTE_AENDERUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LIEFERBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LIEFERBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LIEFERBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LIEFERBEDINGUNGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_TIMESTAMP(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_TIMESTAMP", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_UUID(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_UUID", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_UUID(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_UUID", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SYS_TRIGGER_VERSION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SYS_TRIGGER_VERSION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SYS_TRIGGER_VERSION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SYS_TRIGGER_VERSION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNGS_INFO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNGS_INFO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_VERARBEITUNGS_INFO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("VERARBEITUNGS_INFO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNGS_INFO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNGS_INFO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNGS_INFO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNGS_INFO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNGS_INFO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNGS_INFO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_VERARBEITUNGS_INFO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("VERARBEITUNGS_INFO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ZAHLUNGSBEDINGUNGEN", calNewValueFormated);
	}
	}
