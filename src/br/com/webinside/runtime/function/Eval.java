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

import br.com.webinside.runtime.exception.UserException;
import br.com.webinside.runtime.integration.AbstractFunction;
import br.com.webinside.runtime.integration.Calculate;
import br.com.webinside.runtime.util.StringA;

// Eval(String Expression, String mask)
public class Eval extends AbstractFunction {
    /**
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String execute(String[] args) throws UserException {
        if (args.length == 0) {
            return "";
        }
        String resp = "";
        if (args[0].equalsIgnoreCase("object")) {
            if (args.length == 2) {
        		resp = getWiMap().get(args[1]);
            } else if (args.length == 3){
            	String key = args[1];
        		key = StringA.change(key, "[]", "[" + args[2]+ "]");
        		resp = getWiMap().get(key);
            }        
        } else {
            if (args.length >= 1) {
                Calculate calc = new Calculate(getWiMap(), args[0]);
                resp = calc.execute();
            }
            if (args.length == 2) {
            	String mask = args[1];
            	String[] params = null;
            	String type = mask.toLowerCase();
            	if (type.startsWith("cbr") || type.equals("round")) {
                    params = new String[2];
                    params[0] = resp;
                    params[1] = mask;
            	} else {
                    params = new String[3];
                    params[0] = resp;
                    params[1] = "FMT";
                    params[2] = mask;
            	}
                NumberFormat nf = new NumberFormat(getWiMap());
                resp = nf.execute(getWiParams(), params);
            }
        }
        return resp;
    }
}
