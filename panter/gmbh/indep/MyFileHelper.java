package panter.gmbh.indep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import panter.gmbh.indep.exceptions.myException;



public class MyFileHelper
{

	/**
	 * 
	 * @param Source  (File)
	 * @param Target  (File)
	 * @throws myException
	 */
	public static void CopyFile(File Source, File Target) throws myException
	{
		if (!Source.exists())
		{
			throw new myException("MyFileHelper:CopyFile:Source does not exist !!");
		}
		
		if (Target.exists())
		{
			throw new myException("MyFileHelper:CopyFile:Target-File exists !!!");
		}
		
		
		FileInputStream in = null;
		FileOutputStream out = null;
		try
		{
			in = new FileInputStream(Source);	
			out = new FileOutputStream(Target);
			
			byte[] puffer = new byte[4096];
			int iCount;

			while ((iCount = in.read(puffer)) != -1)
			{
				out.write(puffer,0,iCount);
			}

		    in.close();
		    out.close();
		    
		    in = null;
		    out = null;
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new myException("MyFileHelper:CopyFile:Error finding Files ..."+Source.getName()+" -->"+Target.getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new myException("MyFileHelper:CopyFile:Error opening Files ..."+Source.getName()+" -->"+Target.getName());
		}
		finally
		{
			
			try
			{
				if ( in !=null) in.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			try
			{
				if ( out !=null) out.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	/**
	 * 
	 * @param cCompletePath
	 * @return s in [0] den String bis zum letzten File.Separator inclusive Separator
	 *            in [1] den String ab dem letzten File.Separator ohne Separator
	 */
	public static String[] Separate_Filename_From_Path(String cCompletePath) throws myException
	{
		if ( S.isEmpty(cCompletePath))
		{
			throw new myException("MyFileHelper:Separate_Filename_From_Path:Empty Complete-Path is not allowed !!");
		}
		if ( cCompletePath.indexOf(File.separator)==-1)
		{
			throw new myException("MyFileHelper:Separate_Filename_From_Path:IT is not a Path:"+cCompletePath);
		}

		String[] cRueck = new String[2];
		
		int iPos = cCompletePath.lastIndexOf(File.separator);
		cRueck[0] = cCompletePath.substring(0,iPos+1);
		cRueck[1] = cCompletePath.substring(iPos+1);
		
		return cRueck;
		
	}
	
	
}
