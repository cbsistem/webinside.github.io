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

import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.Function;

// Random(int length, boolean onlyNumbers, toLower)
public class Random extends AbstractFunction {

    /**
     * Creates a new Random object.
     */
    public Random() { }

    /**
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String execute(String[] args) {
        int max = 5;
        boolean onlynumber = true;
        if (args.length >= 1) {
            int newMax = Function.parseInt(args[0]);
            if (newMax > 0) max = newMax; 
        }
        if (args.length >= 2) {
            String p2 = args[1].trim().toLowerCase();
            if (p2.equals("false")) onlynumber = false;
        }
        String resp = Function.randomKey(max, onlynumber);
        if (args.length >= 3) {
            String p3 = args[2].trim().toLowerCase();
            if (p3.equals("true")) resp = resp.toLowerCase();
        }
        return resp;
    }

}
