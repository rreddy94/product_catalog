package com.example.practice.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.practice.entity.Product;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class ExportToPdfUtility {
	
	Logger logger = LoggerFactory.getLogger(ExportToPdfUtility.class);

	
	public ByteArrayInputStream exportToPdf(List<Product> productList) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Products Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(5);
			Stream.of("Product Name", "Description", "Price", "Quantity", "Category Name").forEach(headerTitle -> {
						
				        PdfPCell header = new PdfPCell();
						Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						header.setBackgroundColor(BaseColor.LIGHT_GRAY);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.setBorderWidth(2);
						header.setPhrase(new Phrase(headerTitle, headFont));
						table.addCell(header);
					});

			for (Product product : productList) {
				table.addCell(getPdfCell(product.getProductName()));
				table.addCell(getPdfCell(product.getDescription()));
				table.addCell(getPdfCell(String.valueOf(product.getPrice())));
				table.addCell(getPdfCell(String.valueOf(product.getQuantity())));
				if(product.getCategory() != null)
				 table.addCell(getPdfCell(product.getCategory().getCategoryName()));
			}
			document.add(table);
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	public PdfPCell getPdfCell(String value) {
		PdfPCell pdfCell = new PdfPCell(new Phrase(value));
		pdfCell.setPaddingLeft(4);
		pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return pdfCell;
	}
}