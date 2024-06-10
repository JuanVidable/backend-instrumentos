package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.PedidoDetalle;
import com.example.demo.entities.PedidoPrintManager;
import com.example.demo.services.Impl.PedidoDetalleServiceImpl;
import com.example.demo.services.PedidoDetalleService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/detalles")
public class PedidoDetalleController extends BaseControllerImpl<PedidoDetalle, PedidoDetalleServiceImpl> {
@Autowired
    PedidoDetalleService pedidoDetalleService;

@Autowired
PedidoPrintManager pedidoPrintManager;
    @GetMapping("/cantidad-por-instrumento")
    public List<Map<String, Object>> obtenerPedidosPorInstrumento() {
        return pedidoDetalleService.obtenerPedidosPorInstrumentos();
    }


    @GetMapping("/generar-reporte-excel")
    public ResponseEntity<byte[]> generarReporteExcel(@RequestParam("fechaDesde") String fechaDesde,
                                                      @RequestParam("fechaHasta") String fechaHasta) throws IOException {
        // Obtiene los datos para el reporte Excel desde el servicio de detalles de pedidos
        // Suponemos que el método generarReporteExcel devuelve un SXSSFWorkbook con el reporte generado
        SXSSFWorkbook libroExcel = pedidoPrintManager.imprimirExcelPedidos(fechaDesde, fechaHasta);

        // Convierte el libro de trabajo en un flujo de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libroExcel.write(outputStream);

        // Configura las cabeceras de la respuesta para indicar el tipo de contenido y el nombre del archivo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "reporte_pedidos.xlsx");

        // Retorna la respuesta con el archivo Excel generado
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

}
