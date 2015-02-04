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

package br.com.webinside.runtime.integration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.StringA;
import br.com.webinside.runtime.util.WIMap;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;

public class BeanShell {
	
    private WIMap wiMap;
    private String expression;
    private Producer producer;
    private StringBuffer bshExpression;
    private Map bshMap;

    public BeanShell(WIMap wiMap, String expression) {
        if (wiMap == null) {
            wiMap = new WIMap();
        }
        if (expression == null) {
            expression = "";
        }
        this.wiMap = wiMap;
        this.expression = expression;
    }

    public Object eval() throws EvalError {
    	producer = new Producer();
    	bshExpression = new StringBuffer();
    	bshMap = new HashMap();
    	decode();
    	if (bshExpression.toString().trim().equals("")) {
    		return null;
    	}
    	Interpreter interpreter = new Interpreter();
    	for (Iterator it = bshMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
	        interpreter.set((String)entry.getKey(), entry.getValue());
		}
		interpreter.eval("bshResult = (double) " + getBshExpression());
        return interpreter.get("bshResult");
    }

    private void decode() {
		int count = 0, pos = 0, from = 0;
        ProducerParam param = new ProducerParam();
        param.setWIMap(wiMap);
        producer.setParam(param);
		while ((pos = expression.indexOf("|", pos)) > -1) {
			count ++;
			int last = Function.lastPipePos(expression, pos);
			String part = StringA.mid(expression, pos, last);
			if (part.equals("||")) {
				pos = last + 1;
				continue;
			}
			param.setInput(part);
			double value = 0;
	        try {
	        	producer.execute();
				value = Double.parseDouble(param.getOutput().trim());
	        } catch (NumberFormatException err) {
	        	String msg = part + " as " + param.getOutput().trim()
	        		+ " is not a valid number";
	            wiMap.put("wi.error", msg);
	        }
	        bshExpression.append(StringA.mid(expression, from, pos - 1));
	        bshExpression.append("par" + count);
	        bshMap.put("par" + count, new Primitive(value));
			from = last + 1;
			pos = from;
		}
		bshExpression.append(StringA.mid(expression, from, expression.length()));
    }
    
	public String getExpression() {
		return expression;
	}

	public Producer getProducer() {
		return producer;
	}

	public WIMap getWiMap() {
		return wiMap;
	}

	public String getBshExpression() {
		return bshExpression.toString();
	}

	public Map getBshMap() {
		return bshMap;
	}
    
}
