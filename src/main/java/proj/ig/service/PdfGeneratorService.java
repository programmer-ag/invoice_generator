package proj.ig.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    private String getDefaultLogoBase64() {
        try {
            ClassPathResource imgFile = new ClassPathResource("static/images/default_logo.png");
            byte[] bytes = imgFile.getInputStream().readAllBytes();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return "";
        }
    }

    public String generateHtml(InvoiceRequest request) {
        String template = (request.getTemplateName() == null || request.getTemplateName().isEmpty()) 
                ? "invoice" : request.getTemplateName();
        
        if (request.isShowLogo() && (request.getLogoBase64() == null || request.getLogoBase64().isEmpty())) {
            request.setLogoBase64(getDefaultLogoBase64());
        }

        Context context = new Context();
        context.setVariable("invoice", request);
        return templateEngine.process(template, context);
    }

    @SuppressWarnings("deprecation")
	public byte[] generateInvoicePdf(InvoiceRequest request) {
        String html = generateHtml(request);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            
            // --- THE UPDATED FONT LOADING LOGIC ---
            try {
                // We use a ClassPathResource to find the file
                ClassPathResource fontResource = new ClassPathResource("fonts/DejaVuSans.ttf");
                
                // OpenHTMLtoPDF often requires an FSSupplier (a lambda that returns the stream)
                // The parameters are: Supplier, Family Name, Weight, Style, and Embed (true)
                builder.useFont(
                    () -> {
						try {
							return fontResource.getInputStream();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}, 
                    "DejaVu Sans", 
                    400, 
                    BaseRendererBuilder.FontStyle.NORMAL, 
                    true
                );
                
            } catch (Exception fontEx) {
                System.err.println("Could not load font: " + fontEx.getMessage());
            }

            builder.useFastMode();
            builder.withHtmlContent(html, "/");
            builder.toStream(os);
            builder.run();
            
            return os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}