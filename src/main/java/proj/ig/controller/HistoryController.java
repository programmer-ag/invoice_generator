package proj.ig.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proj.ig.entity.HistoryDTO;
import proj.ig.entity.InvoiceHistory;
import proj.ig.repos.HistoryRepository;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    // 1. LIST: Returns metadata only (no binary data)
    @GetMapping
    public List<HistoryDTO> getHistoryList(Principal principal) {
        // 1. findByUserEmail returns a List. We stream it directly.
        return historyRepository.findByUserEmail(principal.getName())
                .stream()
                .map(h -> new HistoryDTO(h.getPdfId(), h.getPdfName(), h.getCreatedAt()))
                .collect(Collectors.toList());
    }

    // 2. RENAME: Update filename by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> renameInvoice(@PathVariable Long id, @RequestParam String newName) {
        InvoiceHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        // Ensure it keeps the .pdf extension
        String sanitizedName = newName.toLowerCase().endsWith(".pdf") ? newName : newName + ".pdf";
        history.setPdfName(sanitizedName);
        
        historyRepository.save(history);
        return ResponseEntity.ok("Renamed successfully");
    }

    // 3. DELETE: Standard delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
        historyRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    // 4. VIEW: Open in new tab (inline disposition)
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {
        InvoiceHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                // "inline" tells the browser to open it, not download it
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + history.getPdfName())
                .body(history.getPdfData());
    }
}
