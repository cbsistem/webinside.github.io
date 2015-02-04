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

package br.com.webinside.runtime.function;

import br.com.webinside.runtime.integration.AbstractFunction;
import br.com.webinside.runtime.util.WIMap;

public class SVList extends AbstractFunction {
	
	final private String KEY = "wi5_svlist_initialized"; 
	
    public String execute(String[] args) {
		WIMap wiMap = getWiParams().getWIMap();
		WIMap superMap = (WIMap)wiMap.getObj("super.");
		if (superMap != null) wiMap = superMap;
    	if (args.length == 2) {
        	String action = args[0].toLowerCase().trim();
        	String value = args[1].toLowerCase().trim();
        	if (action.equals("put")) {
        		String init = (String)getWiParams().getRequestAttribute(KEY);
        		if (init == null) {
        			getWiParams().setRequestAttribute(KEY, "true");
        			wiMap.put("pvt.svlist", "");
        		}
        		String svlist = wiMap.get("pvt.svlist");
        		if (svlist.length() > 0) svlist += ",";
        		svlist += value;
        		wiMap.put("pvt.svlist", svlist);
        		return value;
        	} else if (action.equals("get")) {
        		String[] arr = wiMap.get("pvt.svlist").split(",");
        		for (int i = 0; i < arr.length; i++) {
					if (arr[i].equals(value)) return value;
				}
        	}
    	}
        return "";
    }
    
}
