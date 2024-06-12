package com.example.demo.entities;

import com.example.demo.services.InstrumentoService;
import com.lowagie.text.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
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

    public static void addMetaData(Document document) {
        document.addTitle("Reporte PDF");
        document.addSubject("Ejemplo PDF");
        document.addKeywords("PDF");
        document.addAuthor("Juan Vidable");
        document.addCreator("Juan Vidable");
    }

    private void addTableCell(PdfPTable table, String header, String value, int fontStyle) {
        Font headerFont = new Font(Font.HELVETICA, 12, fontStyle);
        Font valueFont = new Font(Font.HELVETICA, 12);

        PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
        headerCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(headerCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(valueCell);
    }
    public static void addEmptyLine(Document document, int number) {
        try {
            Paragraph espacio = new Paragraph();
            for (int i = 0; i < number; i++) {
                espacio.add(new Paragraph(" "));
            }
            document.add(espacio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException{
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
        cellOne.setBorder(Rectangle.BOTTOM);
        cellOne.setBorder(Rectangle.TOP);
        linea.addCell(cellOne);

        document.add(linea);
    }

    public void imprimirInstrumentosPdf(Long idInstrumento, ByteArrayOutputStream outputStream) throws Exception {
        try {
            System.out.println("Empiezo a imprimir pdf");
            Document document = new Document(PageSize.A4, 30, 30, 0, 30);
            addMetaData(document);

            Instrumento instrumento = instrumentoService.findById(idInstrumento);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Título del producto
            Paragraph paragraph = new Paragraph(instrumento.getInstrumento().toUpperCase(), new Font(Font.HELVETICA, 18, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            // Espacio entre el título y la imagen
            addEmptyLine(document, 1);

            // Imagen del producto
            String imageUrl = "http://localhost:9000/images/" + instrumento.getImagen();
            Image imgInstrumento = Image.getInstance(new URL(imageUrl));
            imgInstrumento.setAlignment(Image.ALIGN_CENTER);
            imgInstrumento.scaleAbsolute(300f, 200f);
            document.add(imgInstrumento);

            // Espacio entre la imagen y la descripción
            addEmptyLine(document, 2);

            // Tabla de características y precio
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            // Agregar filas a la tabla
            addTableCell(table, "Instrumento:", instrumento.getInstrumento(), Font.BOLD);
            addTableCell(table, "Precio:", "$ " + String.valueOf(instrumento.getPrecio()), Font.BOLD);
            addTableCell(table, "Marca:", instrumento.getMarca(), Font.BOLD);
            addTableCell(table, "Modelo:", instrumento.getModelo(), Font.BOLD);

            // Espacio para la descripción
            PdfPCell descripcionCell = new PdfPCell(new Phrase("Descripción:", new Font(Font.HELVETICA, 12, Font.BOLD)));
            descripcionCell.setColspan(2);
            descripcionCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(descripcionCell);

            PdfPCell descripcionValueCell = new PdfPCell(new Phrase(instrumento.getDescripcion(), new Font(Font.HELVETICA, 12)));
            descripcionValueCell.setColspan(2);
            descripcionValueCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(descripcionValueCell);

            document.add(table);

            System.out.println("Document close");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
