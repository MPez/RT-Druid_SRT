/**
 * Created on 01/ott/2008
 *
 * $Id$
 */
package com.eu.evidence.rtdruid.internal.modules.oil.codewriter.erikaenterprise.Banner;

import com.eu.evidence.rtdruid.modules.oil.abstractions.IOilWriterBuffer;
import com.eu.evidence.rtdruid.modules.oil.erikaenterprise.constants.IEEWriterKeywords;

/**
 * @author Nicola Serreli
 *
 */
public class BannerManager {

	/** Identifies the ARM7 cfg file (memory pointers) 
	 * @deprecated da eliminare
	 * */
	private final static String FILE_EE_E7T_H = "cfg_e7t.h";



	/***************************************************************************
	 * 
	 * HEADERS
	 *  
	 **************************************************************************/

	/** Common header for ".c" files */
	public final static String headerC =
			  "/*\n"
			+ " * Project: E.R.I.K.A. E. - Embedded Real tIme Kernel Architecture Enterprise\n"
			+ " *\n" + " * http://www.evidence.eu.com\n" + " *\n"
			+ " *	This file is generated by RTDruid.\n" + " */\n\n";

	/** Common header for ".h" files */
	public final static String headerS =
			  ";;; Project: E.R.I.K.A. E. - Embedded Real tIme Kernel Architecture Enterprise\n"
			+ ";;;\n"
			+ ";;; http://www.evidence.eu.com\n"
			+ ";;; \n"
			+ ";;; This file is generated by RTDruid.\n" + "\n\n";

	/** Common header for "makefile" files */
	public final static String headerMakefile = 
			  "# ERIKA Enterprise Makefile - generated by RT-Druid\n";


	/**
	 * Adds a banner/header to each buffer
	 * 
	 * @param buffers
	 *            all buffers
	 */
	public static void writeBanners(IOilWriterBuffer[] buffers) {
		// UPDRTD gestire in modo migliore la generazione dei banners
		
		if (buffers == null) {
			return;
		}
		
		final String sysSep = System.getProperty("file.separator");
		
		// look all buffers
		for (int bi = 0; bi < buffers.length; bi++) {

			// force to create ee.h
			buffers[bi].get(IEEWriterKeywords.FILE_EE_CFG_H);
			
			
			String[] keys = buffers[bi].getKeys();

			for (int ki = 0; ki < keys.length; ki++) {

				String tmp = keys[ki];

				// protect ee_cfg.h with #define
			    if (tmp.equalsIgnoreCase(IEEWriterKeywords.FILE_EE_CFG_H)) {
					
					String defName = new String("__EE_CPU"
							+ bi + "_CONFIG_H__");
					buffers[bi].get(keys[ki]).insert(0, "#ifndef " + defName + "\n" + "#define " + defName + "\n");

					buffers[bi].get(keys[ki]).append("\n#endif /* " + defName + " */ \n\n");
			    }

				// check the file type and choose the correct header
				if (tmp.equalsIgnoreCase(IEEWriterKeywords.FILE_MAKEFILE) || tmp.endsWith(".mk")) {
					// nothing

				} else if (tmp.equalsIgnoreCase(FILE_EE_E7T_H)) {
					// nothing

				} else if (tmp.endsWith(".c") || tmp.endsWith(".cpp")
						|| tmp.endsWith(".h")) {
					buffers[bi].get(keys[ki]).insert(0, headerC);

				} else if (tmp.endsWith(".s")) {
					buffers[bi].get(keys[ki]).insert(0, headerS);

				}
				
				
// UPDRTD sistemare ....
//				/***************************************************************
//                 * Set path
//                 **************************************************************/
//				if (checkKeyword(IWritersKeywords.CPU_NIOSII)) {
//				    
//				    if (tmp.equalsIgnoreCase(FILE_MAKEFILE) || tmp.equalsIgnoreCase(FILE_EE_COMMON_MK)) {
//				        buffers[bi].setFilePath(keys[ki], default_output_prefix);
//				        
//				    } else {
//				        final IOilObjectList ool = oilObjects[bi];
//			            final ISimpleGenRes sgrCpu = (ISimpleGenRes) ool.getList(IOilObjectList.OS).get(0);
//			            final String cpuName = getOSName(sgrCpu);
//				        buffers[bi].setFilePath(keys[ki], default_output_prefix 
//				        		+ sysSep + cpuName);
//				        
//				    }
//				}
			}
		}

	}
}