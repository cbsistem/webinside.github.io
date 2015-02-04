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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.webinside.runtime.integration.DatabaseAliases;
import br.com.webinside.runtime.integration.InterfaceGrid;
import br.com.webinside.runtime.util.WIMap;

/**
 * Grid Java de exemplo
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class GridSample implements InterfaceGrid {

	private int returntype;

    public Map[] execute(WIMap wiMap, DatabaseAliases dbAliases) {
        returntype = HAS_MORE_ROWS;
        int from = 1;
        int limit = 0;
        try {
        	limit = Integer.parseInt(wiMap.get("grid.limit"));
            String next = "grid." + wiMap.get("grid.id") + ".next";
            from = Integer.parseInt(wiMap.get(next));
        } catch (NumberFormatException err) {
        	// ignorado.
        }
        List completa = new ArrayList();
        Enumeration e = System.getProperties().keys();
        while (e.hasMoreElements()) {
        	String key = (String) e.nextElement();
        	String value = System.getProperty(key);
            Map aux = new HashMap();
            aux.put("chave", key);
            aux.put("valor", value);
            completa.add(aux);
        }
        if (limit == 0) {
        	returntype = COMPLETE;
        	return (Map[])completa.toArray(new Map[0]);        	
        }
        List parcial = new ArrayList();
        for (int i = from - 1; i < completa.size() && i < from + limit - 1; i++) {
        	parcial.add(completa.get(i));
        }
        if (from + limit > completa.size()) {
            returntype = NO_MORE_ROWS;
        }
        return (Map[])parcial.toArray(new Map[0]);
    }

    public int returnType() {
        return returntype;
    }

}
