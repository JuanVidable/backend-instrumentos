package com.example.demo.entities;
import com.example.demo.services.PedidoDetalleService;
import com.example.demo.services.PedidoService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
@Component
public class PedidoPrintManager {

    private final PedidoDetalleService pedidoDetalleService;

    @Autowired
    public PedidoPrintManager(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;
    }

    public SXSSFWorkbook imprimirExcelPedidos(String fechaDesde, String fechaHasta) throws IOException {
        // Se crea el libro
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Importante: el 50 indica el tamaño de paginación
        // Se crea una hoja dentro del libro
        SXSSFSheet hoja = libro.createSheet("Pedidos");

        // Estilo para los encabezados
        XSSFFont font = (XSSFFont) libro.createFont();
        font.setBold(true);
        XSSFCellStyle style = (XSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        // Se crea una fila para los encabezados
        SXSSFRow row = hoja.createRow(0);

        // Se crean los encabezados
        String[] headers = {"Fecha Pedido", "Cantidad", "Instrumento", "Marca", "Modelo", "Precio", "Subtotal"};
        for (int i = 0; i < headers.length; i++) {
            SXSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }

        // Se obtienen los detalles de los pedidos desde el servicio filtrando por rango de fechas
        try {
            List<Object[]> detallesPedidos = pedidoDetalleService.generarReporteExcel(fechaDesde, fechaHasta);
            if (detallesPedidos != null) {
                // Se agregan los detalles de los pedidos a la hoja de Excel
                int rowNum = 1;
                for (Object[] detallePedido : detallesPedidos) {
                    row = hoja.createRow(rowNum++);

                    // Fecha Pedido
                    String fechaPedido = (detallePedido[0] != null) ? detallePedido[0].toString() : "";
                    row.createCell(0).setCellValue(fechaPedido);

                    // Cantidad
                    int cantidad = 0;
                    if (detallePedido[1] != null) {
                        if (detallePedido[1] instanceof Integer) {
                            cantidad = (Integer) detallePedido[1];
                        } else if (detallePedido[1] instanceof String) {
                            try {
                                cantidad = Integer.parseInt((String) detallePedido[1]);
                            } catch (NumberFormatException e) {
                                // Manejar la conversión fallida, tal vez asignar un valor predeterminado o registrar un error
                            }
                        }
                    }
                    row.createCell(1).setCellValue(cantidad);

                    // Instrumento
                    String instrumento = (detallePedido[2] != null) ? detallePedido[2].toString() : "";
                    row.createCell(2).setCellValue(instrumento);

                    // Marca
                    String marca = (detallePedido[3] != null) ? detallePedido[3].toString() : "";
                    row.createCell(3).setCellValue(marca);

                    // Modelo
                    String modelo = (detallePedido[4] != null) ? detallePedido[4].toString() : "";
                    row.createCell(4).setCellValue(modelo);

                    // Precio
                    double precio = 0.0;
                    if (detallePedido[5] != null) {
                        System.out.println("Precio original: " + detallePedido[5]); // Añadir esta línea para depuración
                        if (detallePedido[5] instanceof Double) {
                            precio = (Double) detallePedido[5];
                        } else if (detallePedido[5] instanceof Float) {
                            precio = ((Float) detallePedido[5]).doubleValue();
                        } else if (detallePedido[5] instanceof String) {
                            try {
                                precio = Double.parseDouble((String) detallePedido[5]);
                            } catch (NumberFormatException e) {
                                // Manejar la conversión fallida, tal vez asignar un valor predeterminado o registrar un error
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Tipo de dato inesperado para precio: " + detallePedido[5].getClass());
                        }
                    }
                    row.createCell(5).setCellValue(precio);
                    // Subtotal
                    double subtotal = cantidad * precio;
                    row.createCell(6).setCellValue(subtotal);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo de Excel", e);
        }
        return libro;
    }
}
