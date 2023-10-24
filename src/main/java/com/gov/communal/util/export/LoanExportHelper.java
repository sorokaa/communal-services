package com.gov.communal.util.export;

import com.gov.communal.model.auth.client.dto.ClientDto;
import com.gov.communal.model.meter.dto.MeterPaymentResponse;
import com.gov.communal.model.meter.dto.Payment;
import com.gov.communal.model.meter.enumeration.Communal;
import com.gov.communal.model.meter.enumeration.ExportTextCode;
import com.gov.communal.service.LocalizedMessageService;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.gov.communal.model.meter.enumeration.Communal.ELECTRICITY;
import static com.gov.communal.model.meter.enumeration.Communal.GAS;
import static com.gov.communal.model.meter.enumeration.ExportTextCode.*;
import static com.gov.communal.util.export.ExportConstant.MONTSERRAT_REGULAR;

@Component
@RequiredArgsConstructor
public class LoanExportHelper {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    private static final Color TABLE_HEAD_COLOR = Color.decode("#e0e0e0");
    private static final Color PAIR_ROW_COLOR = Color.WHITE;
    private static final Color ODD_ROW_COLOR = Color.decode("#f5f5f5");

    private static final int REGULAR_FONT_SIZE = 14;
    private static final int PAGE_HEAD_FONT_SIZE = 25;

    private final LocalizedMessageService messageService;

    public byte[] export(MeterPaymentResponse loan, ClientDto client) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, out);
            document.open();
            generateExport(loan, client, document);
        }
        return out.toByteArray();
    }

    private void generateExport(MeterPaymentResponse loan, ClientDto client, Document document) {
        generatePersonalInfo(client, document);
        generateGasLoan(loan, document);
        generateElectricityLoan(loan, document);
    }

    private void generatePersonalInfo(ClientDto client, Document document) {
        Font headFont = ExportUtil.getFont(MONTSERRAT_REGULAR, PAGE_HEAD_FONT_SIZE);
        Paragraph personalInfo = new Paragraph(messageService.get(PERSONAL_INFO_HEADER), headFont);
        document.add(personalInfo);

        Font textFont = ExportUtil.getFont(MONTSERRAT_REGULAR, REGULAR_FONT_SIZE);

        addText(messageService.get(LAST_NAME), client.getLastName(), textFont, document);
        addText(messageService.get(FIRST_NAME), client.getFirstName(), textFont, document);
        addText(messageService.get(PATRONYMIC), client.getPatronymic(), textFont, document);
        addText(messageService.get(CITY), client.getCity().getValue(), textFont, document);
        addText(messageService.get(STREET), client.getStreet().getValue(), textFont, document);
        addText(messageService.get(HOUSE_NUMBER), client.getHouseNumber(), textFont, document);
        addText(messageService.get(EMAIL), client.getEmail(), textFont, document);
    }

    private void addText(String field, String text, Font font, Document document) {
        String result = field + ": " + text;
        Paragraph paragraph = new Paragraph(result, font);
        document.add(paragraph);
    }

    private void generateElectricityLoan(MeterPaymentResponse loan, Document document) {
        document.newPage();
        generateHeader(document, ELECTRICITY);
        List<Payment> payments = loan.getElectricityPayments();
        if (!payments.isEmpty()) {
            generateTable(payments, document);
        }
        generateConclusion(payments, loan.getElectricityLoan(), document);
    }

    private void generateGasLoan(MeterPaymentResponse loan, Document document) {
        document.newPage();
        generateHeader(document, GAS);
        List<Payment> payments = loan.getGasPayments();
        if (!payments.isEmpty()) {
            generateTable(payments, document);
        }
        generateConclusion(payments, loan.getGasLoan(), document);
    }

    private void generateConclusion(List<Payment> payments, BigDecimal totalLoan, Document document) {
        String conclusion;
        if (payments.isEmpty()) {
            conclusion = messageService.get(NO_LOANS_CONCLUSION);
        } else {
            conclusion = messageService.get(HAS_LOANS_CONCLUSION, payments.size(), totalLoan);
        }
        Font font = ExportUtil.getFont(MONTSERRAT_REGULAR, REGULAR_FONT_SIZE);
        Paragraph paragraph = new Paragraph(conclusion, font);
        document.add(paragraph);
    }

    private void generateHeader(Document document, Communal communal) {
        Font font = ExportUtil.getFont(MONTSERRAT_REGULAR, PAGE_HEAD_FONT_SIZE);
        ExportTextCode code = ELECTRICITY == communal ? ELECTRICITY_LOAN : GAS_LOAN;
        String gasLoanHeader = messageService.get(code);
        Paragraph paragraph = new Paragraph(gasLoanHeader, font);
        paragraph.setSpacingAfter(10);
        document.add(paragraph);
    }

    private void generateTable(List<Payment> payments, Document document) {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 3, 4});
        table.setSpacingAfter(15);

        table.addCell(createHeaderCell(messageService.get(LOAN)));
        table.addCell(createHeaderCell(messageService.get(VALUE)));
        table.addCell(createHeaderCell(messageService.get(PRICE)));
        table.addCell(createHeaderCell(messageService.get(DATE)));

        for (int i = 0; i < payments.size(); i++) {
            Payment payment = payments.get(i);
            Color color = i % 2 == 0 ? PAIR_ROW_COLOR : ODD_ROW_COLOR;
            table.addCell(createCell(String.valueOf(payment.getLoan()), color));
            table.addCell(createCell(String.valueOf(payment.getValue()), color));
            table.addCell(createCell(String.valueOf(payment.getPrice()), color));
            table.addCell(createCell(getDate(payment.getCreated()), color));
        }

        document.add(table);
    }

    private String getDate(Instant date) {
        return LocalDateTime.ofInstant(date, ZoneOffset.UTC)
                .format(DATE_FORMAT);
    }

    private PdfPCell createCell(String text, Color color) {
        Font font = ExportUtil.getFont(MONTSERRAT_REGULAR, REGULAR_FONT_SIZE);
        Phrase phrase = new Phrase(text, font);
        PdfPCell cell = new PdfPCell(phrase);

        cell.setPadding(10);
        cell.setBackgroundColor(color);

        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);

        return cell;
    }

    private PdfPCell createHeaderCell(String text) {
        PdfPCell cell = createCell(text, TABLE_HEAD_COLOR);
        cell.setBorderWidthBottom(1);
        cell.setBorderColorBottom(Color.decode("#d4d2d2"));
        return cell;
    }
}
