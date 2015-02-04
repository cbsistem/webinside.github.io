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

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.integration.Condition;
import br.com.webinside.runtime.integration.InterfaceFunction;
import br.com.webinside.runtime.integration.Producer;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class If implements InterfaceFunction {

	private WIMap wiMap;
	
	// If(condition,trueValue,falseValue)
    public String execute(ExecuteParams wiParams, String[] args) {
    	if (args.length == 0) return "";
        String cond = args[0];
        String trueValue = "true";
        String falseValue = "false";
        if (args.length > 1) {
            trueValue = args[1];
            falseValue = "";
        }
        if (args.length > 2) {
            falseValue = args[2];
        }
        if (wiMap == null) {
        	wiMap = wiParams.getWIMap();
        }
        Condition eval = new Condition(wiMap, cond);
        if (eval.execute()) {
            return Producer.execute(wiMap, trueValue);
        }
        return Producer.execute(wiMap, falseValue);
    }
    
    public void setWiMap(WIMap wiMap) {
		this.wiMap = wiMap;
	}

}
