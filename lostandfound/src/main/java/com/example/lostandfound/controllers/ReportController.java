package com.example.lostandfound.controllers;

import com.example.lostandfound.entities.Category;
import com.example.lostandfound.entities.Item;
import com.example.lostandfound.entities.ItemStatus;
import com.example.lostandfound.services.ItemService;
import com.example.lostandfound.services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Controller for PDF report generation
 */
@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private ItemService itemService;

    /**
     * Generate PDF for a single item
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<Resource> downloadItemPDF(@PathVariable Integer id) {
        Item item = itemService.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayOutputStream pdfStream = pdfService.generateItemPDF(item);
        ByteArrayResource resource = new ByteArrayResource(pdfStream.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=item_" + id + ".pdf")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Generate PDF for search results
     */
    @GetMapping("/search")
    public ResponseEntity<Resource> downloadSearchResultsPDF(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) ItemStatus status,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate) {

        List<Item> items = itemService.searchItems(category, status, location, startdate, enddate);
        ByteArrayOutputStream pdfStream = pdfService.generateSearchResultsPDF(items);
        ByteArrayResource resource = new ByteArrayResource(pdfStream.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=search_results.pdf")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
