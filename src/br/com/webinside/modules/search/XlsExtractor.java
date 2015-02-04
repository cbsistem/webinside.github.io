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

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Classe para extrair texto de planilhas Excel
 * 
 * @author Luiz Ruiz
 */
public class XlsExtractor {

    /**
     * Constructor
     */
    public XlsExtractor() {
    }

    public String extractText(InputStream in) throws IOException  {
        StringBuffer text = new StringBuffer();
        HSSFWorkbook hwb = new HSSFWorkbook(in);
        for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
            HSSFSheet sheet = hwb.getSheetAt(i);
            for (Iterator itRow = sheet.rowIterator(); itRow.hasNext();) {
                StringBuffer rowText = new StringBuffer();
                HSSFRow row = (HSSFRow) itRow.next();
                for (Iterator itCell = row.cellIterator(); itCell.hasNext();) {
                    HSSFCell cell = (HSSFCell) itCell.next();
                    String content = "";
                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        content = cell.getStringCellValue();
                    	break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        content = Double.toString(cell.getNumericCellValue());
                    	break;
                    }
                    content = content.trim();
                    if (! "".equals(content)) {
                        rowText.append(content).append(" ");
                    }
                }
                String content = rowText.toString().trim();
                if (! "".equals(content)) {
                    text.append(content).append("\n");
                }
            }
        }
        
        return text.toString();
    }
}
