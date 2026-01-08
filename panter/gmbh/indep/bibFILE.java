package panter.gmbh.indep;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class bibFILE
{

	/**
	 * 
	 * @param FileQuelle
	 * @param FileZiel
	 * @throws IOException
	 */
	public static void copyFile(File FileQuelle, File FileZiel) throws IOException
	{
		FileInputStream input = new FileInputStream(FileQuelle);
		FileInputStream output = new FileInputStream(FileZiel);
		
		
		FileChannel inChannel = input.getChannel();
		FileChannel outChannel = output.getChannel();
		try
		{
			inChannel.transferTo(0, inChannel.size(), outChannel);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
			
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
	}
	
	
	/**
	 * 
	 * @param path
	 * @return path mit File.separator falls der am ende fehlt (nur das ende wird betrachtet)
	 */
	public static String addFileSeparatorToPath(String path) {
		if (S.isFull(path)) {
			if (path.endsWith(File.separator)) {
				return path;
			} else {
				return path+File.separator;
			}
		}
		return path;
	}
	
	
}
