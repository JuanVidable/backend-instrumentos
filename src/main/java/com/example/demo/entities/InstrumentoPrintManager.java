package com.example.demo.entities;

import com.example.demo.services.InstrumentoService;
import com.lowagie.text.Font;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstrumentoPrintManager {

    private final InstrumentoService instrumentoService;

    @Autowired
    public InstrumentoPrintManager(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }


    protected static Font titulo = new Font(Font.COURIER, 14, Font.BOLD);
    protected static Font redFont = new Font(Font.COURIER, 12, Font.NORMAL, Color.RED);
    protected static Font textoHeader = new Font(Font.COURIER, 17, Font.BOLD);
    protected static Font texto = new Font(Font.COURIER, 11, Font.NORMAL);
    protected static Font textoBold = new Font(Font.COURIER, 11, Font.BOLD);
    protected static Font textoMini = new Font(Font.COURIER, 8, Font.NORMAL);
    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);

    public SXSSFWorkbook imprimirExcelInstrumentos() throws IOException, SQLException {

        // Se crea el libro
        SXSSFWorkbook libro = new SXSSFWorkbook(50); //importante !! el 50 indica el tamaño de paginación
        // Se crea una hoja dentro del libro
        SXSSFSheet hoja = libro.createSheet("Instrumentos");
        //estilo
        XSSFFont font = (XSSFFont) libro.createFont();
        font.setBold(true);
        XSSFCellStyle style = (XSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        // Se crea una fila dentro de la hoja
        SXSSFRow row = hoja.createRow(0);
        // Se crea una celda dentro de la fila
        int nroColumna = 0;

        String[] headers = {"Id", "Nombre", "Precio", "Marca"};
        for (String header : headers) {
            SXSSFCell cell = row.createCell(nroColumna++);
            cell.setCellValue(header);
            cell.setCellStyle(style);
        }

        int nroFila = 1;

        try {
            List<Instrumento> instrumentos = instrumentoService.findAll();
            System.out.println("Lista de instrumentos"+instrumentos);
            if (instrumentos != null) {
                for (Instrumento instrumento : instrumentos) {
                    nroColumna = 0;
                    row = hoja.createRow(nroFila++);
                    row.createCell(nroColumna++).setCellValue(instrumento.getId());
                    row.createCell(nroColumna++).setCellValue(instrumento.getInstrumento());
                    row.createCell(nroColumna++).setCellValue(instrumento.getPrecio());
                    row.createCell(nroColumna++).setCellValue(instrumento.getMarca());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo de Excel", e);
        }
        return libro;
    }
}
