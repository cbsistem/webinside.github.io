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

package br.com.webinside.modules.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.textmining.text.extraction.WordExtractor;

import br.com.webinside.runtime.exception.UserException;

/**
 * @author Luiz Ruiz
 *
 */
public class DocumentParser {

    private DocumentParser() {
    }
    
    public static String parse(File file) throws UserException {
        if (!file.exists()) {
            throw new UserException(
                    "Arquivo " + file.getAbsolutePath() + " n�o encontrado");
        }
        String content = "";
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        try {
            if (".pdf".equalsIgnoreCase(ext)) {
                PDFTextStripper pdf = new PDFTextStripper();
                PDDocument doc = PDDocument.load(file);
                content = pdf.getText(doc);
                doc.close();
            }
            if (".doc".equalsIgnoreCase(ext)) {
                InputStream in = new FileInputStream(file);
                content = parseWord(in);
            }
            if (".xls".equalsIgnoreCase(ext)) {
                XlsExtractor xe = new XlsExtractor();
                InputStream fi = new FileInputStream(file);
                content = xe.extractText(fi);
            }
        } catch (IOException e) {
            throw new UserException(e);
        }
        return content;
    }

    private static String parseWord(InputStream is) throws UserException {
        WordExtractor we = new WordExtractor();
        String content = "";
        try {
            content = we.extractText(is);
        } catch (Exception e) {
            throw new UserException(e);
        }
        return content;
    }
}
