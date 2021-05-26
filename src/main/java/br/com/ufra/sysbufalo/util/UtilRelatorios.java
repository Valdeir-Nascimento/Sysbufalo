/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author presto
 */
public class UtilRelatorios {
    
    public static void imprimeRelatorio(String nomeRelatorio, HashMap parametros, List lista) throws Exception{
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.responseComplete();
        ServletContext scontext = (ServletContext)
                facesContext.getExternalContext().getContext();
        String path = scontext.getRealPath("/WEB-INF/relatorios/");
        if(parametros != null) {       
            parametros.put("SUBREPORT_DIR", path);
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                scontext.getRealPath("/WEB-INF/relatorios/") + nomeRelatorio + ".jasper", parametros, dataSource);
        HttpServletResponse res = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        res.setContentType("application/pdf");
        int codigo = (int) (Math.random() * 1000);
        res.setHeader("Content-disposition","inline;filename=relatorio_" + codigo + ".pdf");
        byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
        res.getOutputStream().write(b);
        res.getCharacterEncoding();
        facesContext.responseComplete();
        
        System.out.println( (true) ? "" : "");
    }
    
}
