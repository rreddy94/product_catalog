package com.example.practice.utility;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import com.example.practice.entity.Product;

@Component
public class ExportApachePdfUtility {

	Logger logger = LoggerFactory.getLogger(ExportToPdfUtility.class);

	public ByteArrayInputStream exportToPdf(List<Product> productList) {

		PDDocument pdfDoc = null;
		try {
			pdfDoc = new PDDocument();
			PDPage firstPage = new PDPage();
			// add page to the PDF document
			pdfDoc.addPage(firstPage);
			// For writing to a page content stream
			try (PDPageContentStream cs = new PDPageContentStream(pdfDoc, firstPage)) {
				cs.beginText();
				cs.setFont(PDType1Font.COURIER, 15);
				cs.setNonStrokingColor(Color.RED);
				cs.newLineAtOffset(20, 750);
				cs.showText("Product Name" + "\t" + "Description" + "\t" + "Price" + "\t" + "Quantity" + "\t"
						+ "Category Name");
				cs.newLine();

				for (Product product : productList) {
					cs.newLine();
					cs.setFont(PDType1Font.COURIER, 15);
					cs.setNonStrokingColor(Color.BLACK);
					cs.newLineAtOffset(20, 750);
					cs.showText(product.getProductName() + "\t" + product.getDescription() + "\t" + product.getPrice()
							+ "\t" + product.getQuantity() + "\t" + product.getCategory().getCategoryName());
					cs.newLine();

				}
				cs.endText();
			}
			// save PDF document
			pdfDoc.save("products.pdf");
			pdfDoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PDStream stream = new PDStream(pdfDoc);
		try {
			return new ByteArrayInputStream(stream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return null;
	}

}