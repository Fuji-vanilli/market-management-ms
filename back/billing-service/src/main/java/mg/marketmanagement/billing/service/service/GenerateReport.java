package mg.marketmanagement.billing.service.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import mg.marketmanagement.billing.service.dto.Product;
import mg.marketmanagement.billing.service.model.Bill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

import static mg.marketmanagement.billing.service.utils.Root.LOCAL_ROOT;

public class GenerateReport {

    public static void generate(Bill  bill) throws FileNotFoundException, DocumentException {

        Document document= new Document();
        PdfWriter.getInstance(document, new FileOutputStream(LOCAL_ROOT+generateUUID()+".pdf"));

        document.open();
        setRectangleInPdf(document);

        String data= "Name: "+bill.getUser().getFirstname()+
                "\nLastname: "+bill.getUser().getLastname()+
                "\nEmail: "+bill.getEmailUser()+
                "\nContact: "+bill.getUser().getContact()+
                "\nMethod Payment: "+bill.getMethodPayement();

        Paragraph chunk= new Paragraph("FACTURE DE PAIEMENT", getFont("header"));
        chunk.setAlignment(Element.ALIGN_CENTER);

        document.add(chunk);

        Paragraph elementTitle= new Paragraph(data+"\n\n", getFont("data"));
        document.add(elementTitle);

        PdfPTable table= new PdfPTable(5);
        table.setWidthPercentage(100);
        addTable(table);

        for(Map.Entry<Product, Integer> entry: bill.getProducts().entrySet()){
            addRow(table, entry.getKey());
        }
        document.add(table);
        Paragraph footer= new Paragraph("Total: "+bill.getTotal()+"\nMerci de votre visite à Bientot!");
        document.add(footer);

        document.close();
    }

    private static void addTable(PdfPTable table) {
        Stream.of("Name", "Category", "Price", "Quantity", "Sub total")
                .forEach(title->{
                    PdfPCell header= new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(title));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);

                    table.addCell(header);
                });
    }

    private static void addRow(PdfPTable table, Product product) {
        table.addCell(product.getName());
        table.addCell(product.getCategory());
        table.addCell(String.valueOf(product.getPrice()));
        table.addCell(String.valueOf(product.getQuantity()));
        table.addCell(String.valueOf(product.getSubTotal()));
    }

    private static Font getFont(String type) {
        switch (type){
            case "header":
                Font headerFont= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 14,BaseColor.BLUE);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "data":
                Font dataFont= FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.NORMAL);
                return dataFont;
            default:
                return new Font();
        }
    }

    private static void setRectangleInPdf(Document document) throws DocumentException {
        Rectangle rectangle= new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);

        rectangle.setBackgroundColor(BaseColor.WHITE);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private static String generateUUID(){
        final Date date= new Date();
        final long time = date.getTime();

        return "BILL"+time;
    }
}
