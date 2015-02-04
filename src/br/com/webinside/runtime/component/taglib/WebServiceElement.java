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
import br.com.webinside.runtime.component.WebService;
import br.com.webinside.runtime.component.WebServiceFault;
import br.com.webinside.runtime.component.WebServiceMethod;
import br.com.webinside.runtime.core.ExecuteParams;

/**
 * Classe que implementa um TagLib para um elemento do webservice.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class WebServiceElement extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String webservice;
    private String method;
    private String name;

    /**
     * Executa a fun��o.
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
            WebService service =
                (WebService) proj.getWebServices().getElement(getWebservice());
            if (getName().indexOf("_") == -1) {
                WebServiceMethod m = new WebServiceMethod(getMethod());
                service.addMethod(m);
                pageContext.setAttribute(getName(), m);
            } else {
                WebServiceFault f = new WebServiceFault();
                service.getMethod(getMethod()).addFault(f);
                pageContext.setAttribute(getName(), f);
            }
        }
        return SKIP_BODY;
    }

    /**
     * Nome do webservice.
     *
     * @return o nome do webservice.
     */
    public String getWebservice() {
        return webservice;
    }

    /**
     * Nome do webservice.
     *
     * @param webservice indica o nome do webservice.
     */
    public void setWebservice(String webservice) {
        this.webservice = webservice;
    }

    /**
     * Nome do metodo.
     *
     * @return o nome do metodo.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Nome do metodo.
     *
     * @param method indica o nome do metodo.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Indica o nome no PageContext.
     *
     * @return Returns o nome no PageContext.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o o nome no PageContext.
     *
     * @param name o o nome no PageContext.
     */
    public void setName(String name) {
        this.name = name;
    }

}
