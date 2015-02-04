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

package br.com.webinside.runtime.integration.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.integration.Condition;
import br.com.webinside.runtime.integration.ProducerParam;
import br.com.webinside.runtime.integration.Validator;
import br.com.webinside.runtime.util.StringA;

/**
 * Classe que transforma uma vari�vel do wi em vari�vel java.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.1 $
 */
public class Set extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private String var;
    private String value;
    private String scope;
    private String test;
    private String validation;
    private String produce;

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
            try {
                if (validation != null &&
                		validation.equalsIgnoreCase("true")) {
                    if (Validator.isDisabledCondition(wiParams.getWIMap(), test)) {
                    	reset();
                        return SKIP_BODY;
                    }
                }
                boolean cond = 
                	new Condition(wiParams.getWIMap(), test).execute();
                if (test != null && !cond) {
                	reset();
                    return SKIP_BODY;
                }
                String aux = null;
                if (value != null) {
                    aux = produce(wiParams);
                }
                if (scope == null) {
                    scope = "page";
                }
                int iScope = 0;
                if (scope.equals("wi")) {
                    var = getVar().toLowerCase().trim();
                    if (!getVar().startsWith("wi.")) {
                    	wiParams.getWIMap().put(getVar(), aux);
                    }
                } else if (scope.equals("page")) {
                    iScope = PageContext.PAGE_SCOPE;
                } else if (scope.equals("request")) {
                    iScope = PageContext.REQUEST_SCOPE;
                } else if (scope.equals("session")) {
                    iScope = PageContext.SESSION_SCOPE;
                } else if (scope.equals("application")) {
                    iScope = PageContext.APPLICATION_SCOPE;
                }
                if (iScope > 0) {
                	if (!aux.trim().equals("")) {
                        pageContext.setAttribute(getVar(), aux, iScope);
                	} else {
                        pageContext.removeAttribute(getVar());
                	}
                }
            } catch (Exception err) {
                wiParams.getErrorLog().write("Set", "taglib", err);
                throw new JspException(err);
            }
        }
        reset();
        return SKIP_BODY;
    }
    
	private String produce(ExecuteParams wiParams) {
    	StringBuffer auxValue = new StringBuffer();
    	int from = 0;
    	int pos = 0;
    	while ((pos = value.indexOf("${", from)) > -1) {
    		auxValue.append(value.substring(from, pos));
    		int end = value.indexOf("}", pos);
    		if (end == -1) {
    			end = value.length();
    		}
    		String varId = StringA.changeChars(
    			value.substring(pos, end), "${}", "");
            Object obj = pageContext.findAttribute(varId);
            if (obj != null) {
            	auxValue.append(obj.toString());
            }
            from = end + 1;
    	}
    	if (from < value.length()) {
    		auxValue.append(value.substring(from, value.length()));
    	}	
    	// Produzindo pipes do WI
    	String response = auxValue.toString().trim();
		if (produce == null || produce.trim().equalsIgnoreCase("true")) {
	        ProducerParam param = new ProducerParam();
	        param.setWIMap(wiParams.getWIMap());
	        param.setInput(response);
	        wiParams.getProducer().setParam(param);
	        wiParams.getProducer().execute();
	        response = param.getOutput();
		}
        return response;
    }
	
    private void reset() {
    	var = null;
    	value = null;
        scope = null;
        test = null;
        validation = null;
        produce = null;
    }

    /**
     * Retorna a vari�vel java que armazenar� o valor.
     *
     * @return a vari�vel java que armazenar� o valor.
     */
    public String getVar() {
        return var;
    }

    /**
     * Define a vari�vel java que armazenar� o valor.
     *
     * @param string a vari�vel java que armazenar� o valor.
     */
    public void setVar(String string) {
        var = string;
    }

    /**
     * Retorna o valor a ser armazenado.
     *
     * @return o valor a ser armazenado.
     */
    public String getValue() {
        return value;
    }

    /**
     * Define o valor a ser armazenado.
     *
     * @param string valor a ser armazenado.
     */
    public void setValue(String string) {
        value = string;
    }

    /**
     * Retorna em qual escope a vari�vel existir�. Op��es: page (default),
     * request, session, application.
     *
     * @return em qual escope a vari�vel existir�.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Define em qual escope a vari�vel existir�. Op��es: page (default),
     * request, session, application.
     *
     * @param string em qual escope a vari�vel existir�.
     */
    public void setScope(String string) {
        scope = string;
    }
    
    /**
     * Retorna condi��o utilizada.
     *
     * @return condi��o utilizada.
     */
    public String getTest() {
        return test;
    }

    /**
     * Define a condi��o a ser utilizada.
     *
     * @param s condi��o a ser utilizada.
     */
    public void setTest(String s) {
        test = s;
    }

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}
	
}
