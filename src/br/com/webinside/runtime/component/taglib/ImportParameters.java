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

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.integration.IntFunction;

/**
 * Classe que implementa a execu��o da importa��o de vari�veis.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class ImportParameters extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String expr;

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
            try {
            	IntFunction.importParameters(wiParams.getWIMap(), getExpr());
            } catch (Exception err) {
            	wiParams.getErrorLog().write("ImportParameters", "taglib", err);
            	throw new JspException(err);
            }
        }
        return SKIP_BODY;
    }
    
    /**
     * Expr indica a express�o de vari�veis.
     *
     * @return a express�o de vari�veis.
     */
    public String getExpr() {
        return expr;
    }

    /**
     * Define a express�o de vari�veis.
     *
     * @param expr indica a express�o de vari�veis.
     */
    public void setExpr(String expr) {
        this.expr = expr;
    }

}
