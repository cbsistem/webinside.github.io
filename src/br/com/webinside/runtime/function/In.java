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

/**
 * Title: In<br>
 * Description: Classe que recebe um texto, uma lista de elementos e um delimitador
 *              (opcional). A sa�da � true caso o texto seja um dos elementos da
 *              lista, caso contr�rio ser� false<br>
 * Copyright:    Copyright (c) 2006<br>
 * Company: Infox<br>
 * @author  Daniel
 * @version 1.0
 * @see br.com.webinside.runtime.integration.AbstractFunction
 */

public class In extends AbstractFunction {
    
   /**
   * Construtor default da classe In
   */

    public In() { }
    
   /**
   * M�todo que retorna true casa o texto passado esteja contido na lista passada
   * na entrada entrada.
   * O �ltimo par�metro � opcional, sendo que o separador default usado
   * � a v�gula.
   * @param args - vetor de parametros na seguinte ordem:<br>
   *    1 - texto a ser procurado<br>
   *    2 - lista de valores<br>
   *    3 - separador da lista de entrada <b>(opcional - default:';')</b><br>
   */

    public String execute(String[] args) throws UserException {
        String separador = ":";
        if (args.length > 2) {
            separador = args[2];
            if (separador.equals("comma")) separador = ",";
        }
        String texto = separador + args[0] + separador;
        String lista = separador + args[1] + separador;
        if (lista.indexOf(texto) >= 0) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public static void main(String[] args) throws Exception {
        String[] teste = {"CA","SV;AT;MG;CG", ";"};
        System.out.println(new In().execute(teste));
    }   
}
