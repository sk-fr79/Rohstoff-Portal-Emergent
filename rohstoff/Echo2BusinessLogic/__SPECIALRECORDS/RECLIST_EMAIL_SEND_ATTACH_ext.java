package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECLIST_EMAIL_SEND_ATTACH_ext extends RECLIST_EMAIL_SEND_ATTACH
{

	
	public RECLIST_EMAIL_SEND_ATTACH_ext() throws myException
	{
		super();
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(int MaxNumberOfRecords) throws myException
	{
		super(MaxNumberOfRecords);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(MyConnection Conn, int MaxNumberOfRecords) throws myException
	{
		super(Conn, MaxNumberOfRecords);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String QueryString, int MaxNumberOfRecords) throws myException
	{
		super(QueryString, MaxNumberOfRecords);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String QueryString, MyConnection Conn, int MaxNumberOfRecords, String cOwnKeyField) throws myException
	{
		super(QueryString, Conn, MaxNumberOfRecords, cOwnKeyField);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String QueryString, MyConnection Conn, int MaxNumberOfRecords) throws myException
	{
		super(QueryString, Conn, MaxNumberOfRecords);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String QueryString, MyConnection Conn) throws myException
	{
		super(QueryString, Conn);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String cWhereblock, String cOrderblock, MyConnection Conn) throws myException
	{
		super(cWhereblock, cOrderblock, Conn);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String cWhereblock, String cOrderblock) throws myException
	{
		super(cWhereblock, cOrderblock);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(String QueryString) throws myException
	{
		super(QueryString);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(Vector<String> vIDs, MyConnection Conn) throws myException
	{
		super(vIDs, Conn);
	}

	public RECLIST_EMAIL_SEND_ATTACH_ext(Vector<String> vIDs) throws myException
	{
		super(vIDs);
	}

	public RECLIST_ARCHIVMEDIEN  get_RECLIST_ARCHIVMEDIEN() throws myException {
		
		RECLIST_ARCHIVMEDIEN rl_am = new RECLIST_ARCHIVMEDIEN();
		
		for (RECORD_EMAIL_SEND_ATTACH rec: this) {
			rl_am.ADD(rec.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien(), true);
		}
		
		return rl_am;
	}
	
	
}
