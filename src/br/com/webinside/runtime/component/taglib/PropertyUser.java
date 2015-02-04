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

package br.com.webinside.runtime.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.webinside.runtime.component.Database;
import br.com.webinside.runtime.component.Host;
import br.com.webinside.runtime.util.Encrypter;

/**
 * Classe que define a propriedade USER de um componente.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class PropertyUser extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String name;
    private String user;
    private String pass;

    /**
     * Executa a transforma��o.
     *
     * @return a flag para n�o processar o body
     *
     * @throws JspException em caso de uma exce��o jsp.
     */
    public int doStartTag() throws JspException {
    	Object obj = pageContext.getAttribute(getName());
    	if (!getPass().equals("")) {
        	Encrypter enc = new Encrypter(getPass());
        	pass = enc.decodeDES();
    	}
    	if (obj instanceof Database) {
    		Database db = (Database)obj;
    		db.setUser(getUser(), pass);
    	} else if (obj instanceof Host) {
    		Host host = (Host)obj;
    		host.setUser(getUser(), pass);
    	}
        return SKIP_BODY;
    }

    /**
     * Nome da vari�vel no PageContext.
     *
     * @return indica o nome da vari�vel no PageContext.
     */
    public String getName() {
        return name;
    }

    /**
     * Nome da vari�vel no PageContext.
     *
     * @param name o nome da vari�vel no PageContext.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Senha a ser utilizada.
     *
     * @return indica a senha a ser utilizada.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Senha a ser utilizada.
     *
     * @param pass define a senha a ser utilizada.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Usuario a ser utilizado.
     *
     * @return indica o usuario a ser utilizado.
     */
    public String getUser() {
        return user;
    }

    /**
     * Usuario a ser utilizado.
     *
     * @param define o usuario a ser utilizado.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
