package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.Instrumento;
import com.example.demo.entities.InstrumentoPrintManager;
import com.example.demo.services.Impl.InstrumentoServiceImpl;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/instrumentos")
public class InstrumentoController extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl> {
    @Autowired
    private InstrumentoServiceImpl instrumentoService;

    @Autowired
    private InstrumentoPrintManager instrumentoPrintManager;


    @GetMapping("search")
    public ResponseEntity<?> search(@RequestParam String palabra) throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoService.search(palabra));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("downloadExcelInstrumentos")
    public ResponseEntity<byte[]> downloadExcelPedidos() throws SQLException {
        try {
            InstrumentoPrintManager mPrintInstrumento = new InstrumentoPrintManager(instrumentoService);
            SXSSFWorkbook libroExcel = mPrintInstrumento.imprimirExcelInstrumentos();
            // Escribir el libro de trabajo en un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            libroExcel.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "datos.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("downloadPdfPlato/{idInstrumento}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String idInstrumento) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            InstrumentoPrintManager mPrintInstrumento = new InstrumentoPrintManager(instrumentoService);
            // Crear un nuevo documento
            mPrintInstrumento.imprimirInstrumentosPdf(Long.parseLong(idInstrumento), outputStream);

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "documento.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
