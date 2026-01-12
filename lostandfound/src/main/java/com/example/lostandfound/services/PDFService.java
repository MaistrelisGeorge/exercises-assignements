package com.example.lostandfound.services;

import com.example.lostandfound.entities.Item;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Service for PDF generation
 */
@Service
public class PDFService {

    /**
     * Generate PDF for a single item
     */
    public ByteArrayOutputStream generateItemPDF(Item item) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Lost and Found Item Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Item details
            document.add(new Paragraph("Item Name: " + item.getItemname()));
            document.add(new Paragraph("Category: " + item.getCategory()));
            document.add(new Paragraph("Status: " + item.getStatus()));
            document.add(new Paragraph("Description: " + item.getDescription()));
            document.add(new Paragraph("Location: " + item.getLocation()));
            document.add(new Paragraph("Date Lost/Found: " + item.getDatelostfound()));
            document.add(new Paragraph("Contact Info: " + item.getContactinfo()));
            document.add(new Paragraph("Reported By: " + item.getReporter().getUsername()));
            document.add(new Paragraph("Date Reported: " + item.getDatereported()));

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        return outputStream;
    }

    /**
     * Generate PDF for search results
     */
    public ByteArrayOutputStream generateSearchResultsPDF(List<Item> items) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Lost and Found Search Results", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total Items: " + items.size()));
            document.add(new Paragraph(" "));

            // Table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Headers
            table.addCell("Item Name");
            table.addCell("Category");
            table.addCell("Status");
            table.addCell("Location");
            table.addCell("Date");

            // Data
            for (Item item : items) {
                table.addCell(item.getItemname());
                table.addCell(item.getCategory().name());
                table.addCell(item.getStatus().name());
                table.addCell(item.getLocation());
                table.addCell(item.getDatelostfound() != null ? item.getDatelostfound().toString() : "N/A");
            }

            document.add(table);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        return outputStream;
    }
}
