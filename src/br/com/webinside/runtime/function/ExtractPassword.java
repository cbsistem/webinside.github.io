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
import br.com.webinside.runtime.util.JavaScriptDES;
import br.com.webinside.runtime.util.StringA;

public class ExtractPassword extends AbstractFunction {
	/**
	 * Creates a new ExtractPassword object.
	 */
	public ExtractPassword() { }

	// arg[0] = oldpass into BD
	// arg[1] = received DES password
	public String execute(String[] args) {
		if ((args == null) || (args.length < 2)) {
			return "";
		}	
		if (args[0].trim().equals("")) {
			return "";
		}
		String oldpass = args[0];
		String despass = args[1];
		JavaScriptDES des = new JavaScriptDES(oldpass);
		String open = des.decode(despass);
		String ini = StringA.mid(oldpass, 0, 7);
		if (!open.startsWith(ini)) {
			return "";
		} else {
			return StringA.mid(open, 8, open.length());
		}	
	}
}
