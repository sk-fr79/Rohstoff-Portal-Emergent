package panterdt;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panterdt.common.VERSION;



/**
 *  Starter-klasse fuer import oder export
 * 
 */
public class Starter
{
    

 
    public static void main(String[] args)
    {
       	MyConnection 		oConn = null;

       	/*
         * first checking args
         */
        if (args.length < 5)
        {
            System.out.println("Please start with arguments: \n" +
            					"<JDBC-Connection-String> \n" +
            					"DBTYP["+DB_META.DB_ORA+"|"+DB_META.DB_SAP+ "]\n"+
            					"<Tableowner-Name> \n" +
            					"<Password> \n" +
            					"<Path> \n" +
            					"<[EXPORT[MIGRATE]]|[[IMPORT] "+
            					"[FILE-PATTERNS FOR IMPORT [FILES]]]>\n\n"+
            					"*********** "+VERSION.get_VERSION()+" ****************\n");    
        }
        else
        {
        	
        	String cJDBC_Conn 			= null;
        	String cDBTYP 				= null;
        	String cTableOwnerName  	= null;
        	String cTableOwnerPassword	= null;
        	String cPathForAction		= null;
        	
        	/*
        	 * ACTION can be: IMPORT or EXPORT
        	 */
        	String cACTION				= null;

            /*
             * first check action-type
             */
        	cJDBC_Conn 			= args[0];
        	cDBTYP				= args[1];
        	cTableOwnerName  	= args[2];
        	cTableOwnerPassword	= args[3];
        	cPathForAction		= args[4];
        	cACTION				= args[5].toUpperCase();
        	
        	boolean	bMigrate = cACTION.endsWith("MIGRATE");   			
        	
  			String cClassName = null;
			if (cDBTYP.equals(DB_META.DB_ORA))
			{
				cClassName="oracle.jdbc.driver.OracleDriver";
			}
			else if (cDBTYP.equals(DB_META.DB_SAP))
			{
				cClassName="com.sap.dbtech.jdbc.DriverSapDB";
			}

        	if (cACTION.startsWith("EXPORT"))
        	{
        		try
				{
  
        			oConn = new MyConnection(cClassName,cJDBC_Conn,cTableOwnerName,cTableOwnerPassword,cDBTYP);

        			System.out.println("Start ...");

        			
        			Exporter oExporter = new Exporter(oConn,cPathForAction,bMigrate,false);
        			oExporter.doExport();

        			System.out.println("OK.Done ...");
        			
				}
        		catch (Exception ex)
				{
        			System.out.println("Error EXPORT:"+ex.getLocalizedMessage());
				}
        		
        	}
            else if (cACTION.equals("IMPORT"))
            {
                if (args.length<=6)
                {
                    System.out.println("Please start with arguments:  \n" +
        					"<JDBC-Connection-String>  \n" +
        					"DBTYP["+DB_META.DB_ORA+"|"+DB_META.DB_SAP+ "]\n"+
        					"<Tableowner-Name> \n" +
        					"<Password> \n" +
        					"<Path> \n" +
        					"<[EXPORT[MIGRATE]]|[[IMPORT] "+
        					"<FILE-PATTERNS [FILES]> \n \n"+
        					"*********** "+VERSION.get_VERSION()+" ****************\n");
                    
                }
                else
                {
                    /*
                     * die namen der importfiles
                     */
                    String[] cFilenames = new String[args.length-6];
                    for (int i=6;i<args.length;i++)
                    {
                        cFilenames[i-6]=args[i];
                    }
                    
                    try
					{
                        
                        
               			oConn = new MyConnection(cClassName,cJDBC_Conn,cTableOwnerName,cTableOwnerPassword,cDBTYP);
	
	        			System.out.println("Start ..."+oConn.get_oConnection().hashCode());
	       			
	                    Importer oImporter = new Importer(oConn,cPathForAction,cFilenames);
	                    
	                    for (int i=0;i<oImporter.get_vImportFiles().size();i++)
	                    {
	                        System.out.println((String)oImporter.get_vImportFiles().get(i));
	                    }
	                    oImporter.doImport();
	                    
	                    
	        			System.out.println("OK.Done ...");
	        			
					}
	        		catch (Exception ex)
					{
	        			System.out.println("Error IMPORT:"+ex.getLocalizedMessage());
					}
                }
        	}
        	else
        	{
        		System.out.println("Error: The 6. argument MUST be EXPORT or IMPORT !!");   
         	}
        }
        
        try
        {
            oConn.get_oConnection().close();
        }
        catch (Exception ex)  {}
 
    }
}
