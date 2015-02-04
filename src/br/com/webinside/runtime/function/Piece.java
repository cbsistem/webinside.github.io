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
import br.com.webinside.runtime.util.StringA;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class Piece extends AbstractFunction {
    // uso: |$piece(texto,delim,inicio,fim)$|
    // Parametros:
    // texto = texto a ser particionado
    // delim = delimitador, se for v�rgula, usar \,
    // inicio = posi�ao inicial. Se delim � vazio, � a posi�ao na string
    // fim = posi�ao final. Se delim � vazio, � a posi�ao na string
    public String execute(String[] args) {
        if (args.length < 3) {
            return "";
        }

        StringA text = new StringA(args[0]);
        String delim = args[1];
        String strIni = args[2];
        String strFim = "";
        if (args.length > 3) {
            strFim = args[3];
        }
        String result = "";

        if (args.length == 3) {
            if (strIni.equals("?")) {
                result = text.pieceAsList(delim, 0, 0, false).size() + "";
            } else {
                int ini = parseInt(strIni);
                if (ini != 0) {
                    if (delim.equals("")) {
                        result = text.mid(ini - 1, ini - 1);
                    } else {
                        result = text.piece(delim, ini);
                    }
                }
            }
        } else if (args.length == 4) {
            int ini = parseInt(strIni);
            int fim = parseInt(strFim);
            if ((ini != 0) && (fim != 0)) {
                if (delim.equals("")) {
                    result = text.mid(ini - 1, fim - 1);
                } else {
                    result = text.piece(delim, ini, fim);
                }
            }
        }

        return result;
    }

    private int parseInt(String number) {
        int ret = 0;
        try {
            ret = Integer.parseInt(number);
        } catch (Exception ex) {
        }
        return ret;
    }
}
