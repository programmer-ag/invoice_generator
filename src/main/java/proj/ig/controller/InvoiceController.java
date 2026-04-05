package proj.ig.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proj.ig.entity.InvoiceHistory;
import proj.ig.entity.User;
import proj.ig.repos.HistoryRepository;
import proj.ig.repos.UserRepository;
import proj.ig.service.InvoiceRequest;
import proj.ig.service.InvoiceService;
import proj.ig.service.PdfGeneratorService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired UserRepository userRepository;
	@Autowired InvoiceService invoiceService;
	@Autowired HistoryRepository historyRepository;
	
    private final PdfGeneratorService pdfService;

    public InvoiceController(PdfGeneratorService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody InvoiceRequest request, Principal principal) {
    	
    	User user = userRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + principal.getName()));

        // 2. Execute the DB update logic
        invoiceService.finalizeInvoice(user, request);
    	
        byte[] pdfContents = pdfService.generateInvoicePdf(request);

        InvoiceHistory history = new InvoiceHistory();
        history.setUserEmail(principal.getName());
        history.setPdfName("Invoice_" + request.getInvoiceNumber() + ".pdf");
        history.setPdfData(pdfContents);
        historyRepository.save(history);
        
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