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

import br.com.webinside.runtime.integration.AbstractConnector;
import br.com.webinside.runtime.integration.DatabaseAliases;
import br.com.webinside.runtime.integration.IntFunction;
import br.com.webinside.runtime.integration.InterfaceHeaders;
import br.com.webinside.runtime.integration.InterfaceParameters;
import br.com.webinside.runtime.integration.JavaParameter;
import br.com.webinside.runtime.util.WIMap;

public class LoginRoles extends AbstractConnector implements InterfaceParameters {

	public LoginRoles() { }

    public void execute(WIMap wiMap, DatabaseAliases databases,
        InterfaceHeaders headers) {
    	String var = wiMap.get("tmp.role_var").trim();
    	if (!var.equals("")) {
        	wiMap.put(var, wiMap.get("tmp.role_value").trim());
            IntFunction.loginRoles(getParams());
    	}
    }

	@Override
	public JavaParameter[] getInputParameters() {
        JavaParameter[] params = new JavaParameter[2];
        params[0] = new JavaParameter("tmp.role_var", "Vari�vel do perfil");
        params[1] = new JavaParameter("tmp.role_value", "Valor do perfil");
        return params;
	}

	@Override
	public JavaParameter[] getOutputParameters() {
		return new JavaParameter[0];
	}
    
}
