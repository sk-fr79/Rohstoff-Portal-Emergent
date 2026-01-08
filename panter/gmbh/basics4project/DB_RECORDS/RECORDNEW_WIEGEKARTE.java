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

public class RECORDNEW_WIEGEKARTE extends MyRECORD_NEW 
{

    public static String TABLENAME = "JT_WIEGEKARTE";
    private _TAB  tab = _TAB.wiegekarte;  


	public RECORDNEW_WIEGEKARTE() throws myException 
	{
		super("JT_WIEGEKARTE");
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE.TABLENAME);
	}


    //2013-09-20: erstellen des recordnew-objects ohne abfrage	
	public RECORDNEW_WIEGEKARTE(HashMap<String, MyMetaFieldDEF> hmMetadefs) throws myException 
	{
		super("JT_WIEGEKARTE", hmMetadefs);   //generiert die RECORD_NEW ohne abfrage

		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE.TABLENAME);
	}
	
	
	
	public RECORDNEW_WIEGEKARTE(MyConnection Conn) throws myException 
	{
		super(Conn, "JT_WIEGEKARTE");
		
		
		//2013-09-20: jedem MetaFieldDef-Objekt den richtigen tabellenname uebergeben
		this.set_Tablename_To_FieldMetaDefs(RECORDNEW_WIEGEKARTE.TABLENAME);
	}

	
	
	public RECORD_WIEGEKARTE do_WRITE_NEW_WIEGEKARTE(MyE2_MessageVector oMV) throws myException
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
		vExcludeFields.add("ID_WIEGEKARTE");
		oMV.add_MESSAGE(this.CheckNotNullables(vExcludeFields));
		
		if (!oMV.get_bHasAlarms())
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(this.get_InsertSQLStatementWith_Id_Field(false, true));
			
			oMV.add_MESSAGE(this.ExecMultiSQLVector(vSQL, true));
			
			if (!oMV.get_bHasAlarms())
			{
				return new RECORD_WIEGEKARTE(this.get_cLastSEQ_NUMBER());
			}
			else
			{
				if (bThrowExceptionWhenAlarm)   //falls fehler nicht in einen Messagevector geht, dann exception
				{
					throw new myException("Error writing new Dataset to Table WIEGEKARTE ::"+vSQL.get(0));
				}
			}
		}
		
		return null;
	}

    //2016-10-27
    public _TAB get_tab() {
        return this.tab;
    }


	
	

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ADRESSE_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ADRESSE_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ADRESSE_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ADRESSE_SPEDITION", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZ_ALLG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZ_ALLG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_ALLG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_ALLG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZ_BEHAELTER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZ_BEHAELTER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BEHAELTER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BEHAELTER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZ_BIGBAGS(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZ_BIGBAGS", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_BIGBAGS(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_BIGBAGS", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZ_GITTERBOXEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZ_GITTERBOXEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_GITTERBOXEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_GITTERBOXEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ANZ_PALETTEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ANZ_PALETTEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ANZ_PALETTEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ANZ_PALETTEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEFUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEFUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEFUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEFUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEFUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEFUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEFUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEFUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEFUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEFUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEFUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEFUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG1(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG1", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG1(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG1", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG2(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG2", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG2(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG2", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEMERKUNG_INTERN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEMERKUNG_INTERN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEMERKUNG_INTERN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEMERKUNG_INTERN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_BEZ_ALLG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("BEZ_ALLG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_BEZ_ALLG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("BEZ_ALLG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_ABSETZ_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_ABSETZ_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_ABSETZ_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINER_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_CONTAINER_TARA(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("CONTAINER_TARA", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_CONTAINER_TARA(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("CONTAINER_TARA", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKZAEHLER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_DRUCKZAEHLER_EINGANGSSCHEIN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("DRUCKZAEHLER_EINGANGSSCHEIN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ES_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ES_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ES_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ES_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ES_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ES_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ES_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ES_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ES_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ES_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ES_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ES_NR", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEDRUCKT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEDRUCKT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEDRUCKT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEDRUCKT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_ABZUG_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_ABZUG_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_NACH_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT_NACH_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GEWICHT_NACH_ABZUG_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GEWICHT_NACH_ABZUG_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GRUND_ABZUG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GRUND_ABZUG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GRUND_ABZUG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GRUND_ABZUG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_GUETERKATEGORIE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("GUETERKATEGORIE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_GUETERKATEGORIE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("GUETERKATEGORIE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_ABN_STRECKE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LAGER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LAGER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LAGER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LAGER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ADRESSE_SPEDITION(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ADRESSE_SPEDITION", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ADRESSE_SPEDITION(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ADRESSE_SPEDITION", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_ARTIKEL_SORTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_ARTIKEL_SORTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_ARTIKEL_SORTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_ARTIKEL_SORTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_CONTAINER_EIGEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_CONTAINER_EIGEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_CONTAINER_EIGEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_CONTAINER_EIGEN", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_ABSETZ(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_ABSETZ", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_LAGERPLATZ_SCHUETT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_LAGERPLATZ_SCHUETT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_VPOS_TPA_FUHRE_ORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WAAGE_STANDORT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WAAGE_STANDORT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WAAGE_STANDORT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WAAGE_STANDORT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_PARENT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE_PARENT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_PARENT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_PARENT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_ID_WIEGEKARTE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("ID_WIEGEKARTE_STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_ID_WIEGEKARTE_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("ID_WIEGEKARTE_STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_GESAMTVERWIEGUNG(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_GESAMTVERWIEGUNG", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_GESAMTVERWIEGUNG(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_GESAMTVERWIEGUNG", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_IST_LIEFERANT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("IST_LIEFERANT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_IST_LIEFERANT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("IST_LIEFERANT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_KENNZEICHEN(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("KENNZEICHEN", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_KENNZEICHEN(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("KENNZEICHEN", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_LFD_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("LFD_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_LFD_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("LFD_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_NETTOGEWICHT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("NETTOGEWICHT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_NETTOGEWICHT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("NETTOGEWICHT", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SIEGEL_NR(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SIEGEL_NR", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SIEGEL_NR(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SIEGEL_NR", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_HAND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_SORTE_HAND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("SORTE_HAND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_HAND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_HAND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_HAND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_SORTE_HAND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("SORTE_HAND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_AM(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_AM", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_AM(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_AM", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNIERT_VON(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNIERT_VON", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNIERT_VON(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNIERT_VON", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STORNO_GRUND(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STORNO_GRUND", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STORNO_GRUND(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STORNO_GRUND", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_STRAHLUNG_GEPRUEFT(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("STRAHLUNG_GEPRUEFT", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_STRAHLUNG_GEPRUEFT(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("STRAHLUNG_GEPRUEFT", calNewValueFormated);
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
		public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TARA_KORR_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TARA_KORR_CONTAINER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TARA_KORR_CONTAINER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TARA_KORR_CONTAINER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TRAILER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRAILER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TRAILER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TRAILER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TRAILER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRAILER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRAILER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRAILER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TRAILER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRAILER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TRAILER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TRAILER", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_TYP_WIEGEKARTE(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("TYP_WIEGEKARTE", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_TYP_WIEGEKARTE(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("TYP_WIEGEKARTE", calNewValueFormated);
	}
		public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", cNewValueFormated);
	}


	public MyE2_MessageVector check_NEW_VALUE_UVV_DATUM_CONTAINER(String cNewValueFormated) throws myException
	{
		 return super.check_NewValueForDatabase("UVV_DATUM_CONTAINER", cNewValueFormated);
	}

	
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(long lNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", lNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(double dNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", dNewValueFormated);
	}

	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(BigDecimal bdNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", bdNewValueFormated);
	}
	
	public MyE2_MessageVector set_NEW_VALUE_UVV_DATUM_CONTAINER(GregorianCalendar calNewValueFormated) throws myException
	{
		 return super.set_NewValueForDatabase("UVV_DATUM_CONTAINER", calNewValueFormated);
	}
	}
