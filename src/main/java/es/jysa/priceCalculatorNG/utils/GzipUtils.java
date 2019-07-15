package es.jysa.priceCalculatorNG.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

/**
 * Gzip utils, include detection of gzip files and uncompress
 * 
 * @version 20170718
 * 
 */
public class GzipUtils {
	
	/**
	 * Check if file in filePath is a gz compress file
	 * 
	 * @param filePath	fiel to check if is gzip compress file
	 * 
	 * @return	boolean true if is a gz file
	 */
	public static boolean isGZipped(String filePath) {
		int magic = 0;
		try {
			
			RandomAccessFile raf = new RandomAccessFile(filePath, "r");
			magic = raf.read() & 0xff | ((raf.read() << 8) & 0xff00);
			raf.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/0 error");
			//e.printStackTrace();
		}
		return magic == GZIPInputStream.GZIP_MAGIC;
	}
	
	/**
	 * Uncompres gzipfile passes as parameter in new file with name passed as second parameter
	 * 
	 * @param inputFile		input file gzipped
	 * @param outputFile	output file uncompressed
	 */
	public static void gunzipIt(String inputFile, String outputFile) {
		byte[] buffer = new byte[1024];
		
		try {
			GZIPInputStream gzInputStream = new GZIPInputStream(new FileInputStream(inputFile));
			FileOutputStream outStream = new FileOutputStream(outputFile);
			
			int len;
			while ((len = gzInputStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, len);
			}
			
			gzInputStream.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/0 error");
			//e.printStackTrace();
		}
	}

}
