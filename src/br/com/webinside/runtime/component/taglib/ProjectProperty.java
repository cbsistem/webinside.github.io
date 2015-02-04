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

import br.com.webinside.runtime.component.AbstractProject;
import br.com.webinside.runtime.core.ExecuteParams;

/**
 * Classe que adiciona uma propriedade do projeto gerenciada como cole��o.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.2 $
 */
public class ProjectProperty extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String type;
    private String name;
    private String value;

    /**
     * Executa a transforma��o.
     *
     * @return a flag para n�o processar o body
     *
     * @throws JspException em caso de uma exce��o jsp.
     */
    public int doStartTag() throws JspException {
        Object obj = pageContext.getRequest().getAttribute("wiParams");
        if (obj instanceof ExecuteParams) {
            ExecuteParams wiParams = (ExecuteParams) obj;
            AbstractProject proj = wiParams.getProject();
            if (getType().equals("InitParam")) {
                if (wiParams.getWISession().isNew()) {
                    proj.addInitParam(getName(), getValue());
                }
            } else if (getType().equals("Function")) {
                proj.addFunction(getName(), getValue());
            } else if (getType().equals("SecureVar")) {
                proj.addSecureVar(getName());
            } else if (getType().equals("DBLogColumn")) {
                proj.addDBLogColumn(getName(), getValue());
            }
        }
        return SKIP_BODY;
    }

    /**
     * Indica o nome a ser armazenado.
     *
     * @return o nome a ser armazenado.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome a ser armazenado
     *
     * @param name o nome a ser armazenado.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indica o tipo de propriedade.
     *
     * @return o tipo de propriedade.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo de propriedade.
     *
     * @param type o tipo de propriedade.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Indica o valor a ser armazenado.
     *
     * @return o valor a ser armazenado.
     */
    public String getValue() {
        return value;
    }

    /**
     * Define o valor a ser armazenado.
     *
     * @param value o valor a ser armazenado.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
