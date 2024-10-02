pom.xml

	<dependency>
			<groupId>com.ironsoftware</groupId>
			<artifactId>ironpdf</artifactId>
			<version>2024.9.1</version>
		</dependency>

		<!--
		https://mvnrepository.com/artifact/com.ironsoftware/ironpdf-engine-linux-x64 -->
		<dependency>
			<groupId>com.ironsoftware</groupId>
			<artifactId>ironpdf-engine-linux-x64</artifactId>
			<version>2024.9.1</version>
		</dependency>

		<!--
		https://mvnrepository.com/artifact/com.ironsoftware/ironpdf-engine-windows-x64 -->
		<dependency>
			<groupId>com.ironsoftware</groupId>
			<artifactId>ironpdf-engine-windows-x64</artifactId>
			<version>2024.9.1</version>
		</dependency>
  





public class TemplateBuilderUtility {

	ChromePdfRenderOptions renderOptions = new ChromePdfRenderOptions();

	@PostConstruct
	public void init() {
		renderOptions.setWaitFor(new WaitFor(500));
		renderOptions.setFitToPaperMode(FitToPaperModes.AutomaticFit);
		renderOptions.setPaperOrientation(PaperOrientation.PORTRAIT);
		renderOptions.setPaperSize(PaperSize.A4);
		renderOptions.setMarginBottom(5);
		renderOptions.setMarginLeft(5);
		renderOptions.setMarginTop(5);
		renderOptions.setMarginRight(5);
		Settings.setIronPdfEngineHost("localhost");
	}

	public void htmlToPdf(List<String> pages, OutputStream outputStream) throws IOException {
		List<InputStream> inputPdfList = new ArrayList<InputStream>();

		for (String pageContent : pages) {

			
			PdfDocument pdfFromHtmlString = PdfDocument.renderHtmlAsPdf(pageContent, renderOptions);
			License.setLicenseKey(
					"IRONSUITE.SALAHSAYEDATWA.GMAIL.COM.6053-57AA5ABD8B-BO55H6JJVUUREUPM-XWW4DJFFTRV5-KVH23V2M6S64-QCSGCO53KC5V-IRSXA5YQBAEQ-2QQLOXQ5HX2E-ZG3RZP-T4REIBR5MKWNUA-DEPLOYMENT.TRIAL-QH2V46.TRIAL.EXPIRES.15.OCT.2024");

			inputPdfList.add(new ByteArrayInputStream(pdfFromHtmlString.getBinaryData()));
		}

		try {
			PDFMergeUtility.mergePdfFiles(inputPdfList, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}




