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

package br.com.webinside.runtime.integration;

import br.com.webinside.runtime.util.WIMap;
import bsh.EvalError;

/**
 * Class to make any kind of calc
 *
 * @author Geraldo Moraes
 * @version 1.0
 */
public class Calculate extends BeanShell {

    public Calculate(WIMap wiMap, String expression) {
		super(wiMap, expression);
	}

	public String execute() {
    	if (getExpression().trim().equals("")) {
    		return "";
    	}
    	try {
    		return eval().toString();
    	} catch (EvalError e) {
        	String msg = "Invalid expression: " + getBshExpression();
        	getWiMap().put("wi.error", msg);
    		return "";
    	}
    }

}
