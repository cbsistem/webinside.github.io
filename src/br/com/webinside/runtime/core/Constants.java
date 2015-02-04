/*
 * WEBINSIDE - Ferramenta de produtividade Java
 * Copyright (c) 2011-2012 LINEWEB Solu��es Tecnol�gicas Ltda.
 * Copyright (c) 2009-2010 Inc�gnita Intelig�ncia Digital Ltda.
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou modific�-lo 
 * sob os termos da GNU LESSER GENERAL PUBLIC LICENSE (LGPL) conforme publicada 
 * pela Free Software Foundation; vers�o 2.1 da Licen�a.
 * Este programa � distribu�do na expectativa de que seja �til, por�m, SEM 
 * NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU 
 * ADEQUA��O A UMA FINALIDADE ESPEC�FICA.
 * 
 * Consulte a GNU LGPL para mais detalhes.
 * Voc� deve ter recebido uma c�pia da GNU LGPL junto com este programa; se n�o, 
 * veja em http://www.gnu.org/licenses/ 
 */

package br.com.webinside.runtime.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import br.com.webinside.runtime.util.FileIO;
import br.com.webinside.runtime.util.StringA;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class Constants {

    /**
     * DOCUMENT ME!
     *
     * @param sContext DOCUMENT ME!
     * @param wiMap DOCUMENT ME!
     *
     */
    public static void populate(ServletContext sContext, WIMap wiMap) {
    	if (sContext == null) return;
    	Map constants = (Map) sContext.getAttribute("wi.constants");
    	if (constants == null) {
    		constants = new HashMap();
    		sContext.setAttribute("wi.constants", constants);
    		sContext.setAttribute("wi.constants.time", new Long(0));
    	}
    	Long time = (Long) sContext.getAttribute("wi.constants.time");
    	File file = new File(sContext.getRealPath("/WEB-INF/constants.xml"));    	
    	if (file.isFile() && file.lastModified() != time.longValue()) {
        	time = new Long(file.lastModified());
    		WIMap aux = new WIMap();
        	populate(file, aux);
        	constants = aux.getAsMap();
    		sContext.setAttribute("wi.constants", constants);
    		sContext.setAttribute("wi.constants.time", time);
    	}
       	wiMap.putAll(constants);
    }

    /**
     * DOCUMENT ME!
     *
     * @param file DOCUMENT ME!
     * @param wiMap DOCUMENT ME!
     *
     */
    public static void populate(File file, WIMap wiMap) {
    	if (file.isFile()) {
        	FileIO fio = new FileIO(file.getAbsolutePath(), FileIO.READ);
        	String content = fio.readText();
        	content = StringA.change(content, "wi.", "tmp.wi_");
        	content = StringA.change(content, "wi_", "tmp.wi_");
        	content = StringA.change(content, "grid_", "grid.");
        	content = StringA.change(content, "tmp_", "tmp.");
        	content = StringA.change(content, "pvt_", "pvt.");
        	content = StringA.change(content, "app_", "app.");
        	new CoreXmlImport(wiMap, "").execute(content);
    	}
    }
}
