package proj.ig.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proj.ig.service.InvoiceRequest;
import proj.ig.service.PdfGeneratorService;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "http://localhost:5173") // Allows React to talk to Spring Boot
public class InvoiceController {

    private final PdfGeneratorService pdfService;

    public InvoiceController(PdfGeneratorService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody InvoiceRequest request) {
        byte[] pdfContents = pdfService.generateInvoicePdf(request);

        return ResponseEntity.ok()
                // Tells the browser to treat this as a downloadable file
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContents);
    }
    
    @PostMapping("/preview")
    public ResponseEntity<String> getPreview(@RequestBody InvoiceRequest request) {
        // We use the same service, but we'll add a method to return just the HTML
        String htmlContent = pdfService.generateHtml(request);
        
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(htmlContent);
    }
}