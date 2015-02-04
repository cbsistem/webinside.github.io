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

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.webinside.runtime.core.ExecuteParams;
import br.com.webinside.runtime.integration.ProducerParam;
import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.WIMap;

/**
 * Classe que implementa o Menu com hasrole.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.2 $
 */
public class Menu extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	public int doAfterBody() throws JspException {
        Object obj = pageContext.getRequest().getAttribute("wiParams");
        if (obj instanceof ExecuteParams) {
            ExecuteParams wiParams = (ExecuteParams) obj;
			try {
				BodyContent bodycontent = getBodyContent();
				String body = bodycontent.getString();
				JspWriter out = bodycontent.getEnclosingWriter();
				if (body != null) {
					WIMap wiMap = wiParams.getWIMap();
					if (wiMap.get("pvt.wimenu").equals("")) {
					    ProducerParam param = new ProducerParam();
					    param.setWIMap(wiParams.getWIMap());
					    param.setInput(body);
					    wiParams.getProducer().setParam(param);
					    wiParams.getProducer().execute();
					    Document doc = Jsoup.parse(param.getOutput().trim());
						processHasRole(wiMap, doc.body());
						wiParams.getWIMap().put("pvt.wimenu", doc.body().html());
					}
					out.print(wiMap.get("pvt.wimenu"));
				}
			} catch (IOException ioe) {
				throw new JspException("Error:" + ioe.getMessage());
			}
        }	
        reset();
		return SKIP_BODY;
	}
	
	private void processHasRole(WIMap wiMap, Element ele) {
		Elements list = ele.getElementsByAttribute("hasrole");
		for (Element child : list) {
			if (!child.nodeName().equals("li")) return;
			if (!hasRole(wiMap, child.attr("hasrole"))) {
				remove(child.parent(), child);
			}
		}
	}
	
	private void remove(Element ul, Element li) {
		li.remove();
		if (ul != null && ul.children().size() == 0) {
			if (ul.parent() != null && ul.parent().nodeName().equals("li")) {
				li = ul.parent();
				remove(li.parent(), li);
			} else {
				ul.remove();
			}
		}
	}
	
	private boolean hasRole(WIMap wiMap, String menuRole) {
    	String[] mods = wiMap.get("pvt.login.role.modules").split(",");
		for (String mod : mods) {
			String modRole = "module_" + mod.trim();
			if (modRole.equalsIgnoreCase(menuRole)) return true;
		}
		int size = Function.parseInt(wiMap.get("pvt.login.role.size()"));
		for (int i = 1; i <= size; i++) {
			String loginRole = wiMap.get("pvt.login.role[" + i + "].name");
			if (loginRole.equalsIgnoreCase(menuRole)) return true;
		}
		return false;
	}
	
    private void reset() {
    	// limpar os atributos
    }
	
}