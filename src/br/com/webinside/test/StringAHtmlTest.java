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

package br.com.webinside.test;

import junit.framework.TestCase;
import br.com.webinside.runtime.util.StringAHtml;

public class StringAHtmlTest extends TestCase {

	public void testHtmlToText() {
		String text = "&lt;texto&gt;cora&ccedil;&atilde;o"
				+ " &amp; estrela&lt;/texto&gt;";
		String result = "<texto>cora��o & estrela</texto>";
		String resultc = "&lt;texto&gt;cora��o &amp; estrela&lt;/texto&gt;";
		String results = "<texto>cora&ccedil;&atilde;o & estrela</texto>";
		assertTrue(StringAHtml.htmlToText(text, false, false).equals(
				text.toString()));
		assertTrue(StringAHtml.htmlToText(text, true, false).equals(resultc));
		assertTrue(StringAHtml.htmlToText(text, false, true).equals(results));
		assertTrue(StringAHtml.htmlToText(text, true, true).equals(result));
		String text2 = "nome & ; & &maria;";
		assertTrue(StringAHtml.htmlToText(text2, true, true).equals(text2));
	}

	public void testTextToHtml() {
		String text = "<texto>cora��o & estrela</texto>";
		String result = "&lt;texto&gt;cora&ccedil;&atilde;o"
				+ "&nbsp;&amp;&nbsp;estrela&lt;/texto&gt;";
		String resultc = "<texto>cora&ccedil;&atilde;o" + " & estrela</texto>";
		String results = "&lt;texto&gt;cora��o"
				+ "&nbsp;&amp;&nbsp;estrela&lt;/texto&gt;";
		assertTrue(StringAHtml.textToHtml(text, false, false).equals(
				text.toString()));
		assertTrue(StringAHtml.textToHtml(text, true, false).equals(resultc));
		assertTrue(StringAHtml.textToHtml(text, false, true).equals(results));
		assertTrue(StringAHtml.textToHtml(text, true, true).equals(result));
	}

}
