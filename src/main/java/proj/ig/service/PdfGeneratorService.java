package proj.ig.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateHtml(InvoiceRequest request) {
        Context context = new Context();
        context.setVariable("invoice", request);
        // This looks for src/main/resources/templates/invoice.html
        return templateEngine.process("invoice", context);
    }
    public byte[] generateInvoicePdf(InvoiceRequest request) {
        // 1. Prepare Thymeleaf Context
        Context context = new Context();
        context.setVariable("invoice", request);

        // 2. Render HTML to String
        String html = templateEngine.process("invoice", context);

        // 3. Convert HTML String to PDF Bytes
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
