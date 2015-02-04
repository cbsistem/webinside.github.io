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

import br.com.webinside.runtime.component.BlockElement;
import br.com.webinside.runtime.integration.Condition;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class CoreBlockElement extends CoreCommon {
    private BlockElement block;

    /**
     * Creates a new CoreSetElement object.
     *
     * @param wiParams DOCUMENT ME!
     * @param set DOCUMENT ME!
     */
    public CoreBlockElement(ExecuteParams wiParams, BlockElement block) {
        this.wiParams = wiParams;
        this.block = block;
        element = block;
    }

    /**
     * DOCUMENT ME!
     */
    public void execute() {
    	wiMap = wiParams.getWIMap();
    	String cond = element.getCondition();
        if (cond.trim().equalsIgnoreCase("true")) {
        	wiMap.remove("wi.block.cond");
        	wiMap.remove("wi.block.var");
        } else {
            boolean ret = new Condition(wiMap, cond).execute();
        	wiMap.put("wi.block.cond", ret + "");
        	String var = block.getVar().trim();
        	if (!var.equals("")) {
            	wiMap.put(var, ret + "");
            	wiMap.put("wi.block.var", var);
        	}
        }
        writeLog();
    }
    
}
