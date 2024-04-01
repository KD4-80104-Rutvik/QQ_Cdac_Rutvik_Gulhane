import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import net.sourceforge.tess4j.*;

public class PdfOcrExtractor {

    public static void main(String[] args) {
        String inputPath = "C:\\Users\\15rut\\Downloads\\FileForOcr\\input.pdf";
        String outputPath = "C:\\Users\\15rut\\Downloads\\FileForOutPut\\output.txt";

        try {
            PDDocument pd = PDDocument.load(new File(inputPath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pd);
            pd.close();

            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Set path to the directory containing your language data
            String extractedText = tesseract.doOCR(new File(inputPath));

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            writer.write(extractedText);
            writer.close();

            System.out.println("Text extracted and stored successfully!");

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        }
    }
}
