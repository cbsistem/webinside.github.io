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
import br.com.webinside.runtime.integration.Producer;
import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.WIMap;

/**
 * Title: HasRole
 * Description: Classe verifica se o usuario tem algum dos perfis de acesso
 * Copyright:    Copyright (c) 2010
 * @author  Geraldo Moraes
 * @version 1.0
 * @see br.com.webinside.runtime.integration.AbstractFunction
 */

public class HasRole extends AbstractFunction {
    
    public HasRole() { }
    
   /**
   * M�todo que retorna se o usu�rio possui algum dos perfis de acesso.
   * Os parametros s�o os perfis de acesso.
   */

    public String execute(String[] args) throws UserException {
        String separador = ":";
        if (args.length > 1) {
            separador = args[1];
            if (separador.equals("comma")) separador = ",";
        }
    	WIMap wiMap = getWiMap();
    	String[] roles = args[0].split(separador);
    	String[] mods = wiMap.get("pvt.login.role.modules").split(",");
		for (String role : roles) {
			String pageRole = Producer.execute(wiMap, role).trim();
			for (String mod : mods) {
				String modRole = "module_" + mod.trim();
				if (modRole.equalsIgnoreCase(pageRole)) return "true";
			}
		}    	
    	int size = Function.parseInt(wiMap.get("pvt.login.role.size()"));
    	for (int i = 1; i <= size; i++) {
			String loginRole = wiMap.get("pvt.login.role[" + i + "].name");
			for (String role : roles) {
				String pageRole = Producer.execute(wiMap, role).trim();
				if (loginRole.equalsIgnoreCase(pageRole)) {
					return "true";
				}
			}
		}
    	return "false";
    }
    
}
