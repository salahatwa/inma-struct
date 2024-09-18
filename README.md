https://playwright.azureedge.net/builds/chromium/1135/chromium-linux.zip




try (Playwright playwright = Playwright.create()) {
			LaunchOptions d = new LaunchOptions();
			d.setHeadless(true);
			d.setArgs(Arrays.asList("--disable-web-security", "--headless"));
			String os = System.getProperty("os.name");
			
			System.out.println(os);
			try {
				Path path = Paths.get(TemplateBuilderApplication.class.getClassLoader()
						.getResource("tool/chrome-win/chrome.exe").toURI());
				System.out.println(path);
				d.setExecutablePath(path);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			Browser browser = playwright.chromium().launch(d);

			for (String pageContent : pages) {
				Page page = browser.newPage();
				page.setContent(PlaceholderEngine.process(pageContent, model));
//				page.set

				PdfOptions s = new PdfOptions();
				s.setPrintBackground(true);
				s.setPreferCSSPageSize(true);
				s.setFormat("A4");
				byte[] result = page.pdf(s); // parse website and return pdf
//				Api2PdfClient a2pClient = new Api2PdfClient("515aaed7-e089-49df-9c3a-693093aa581d");
//				Api2PdfResponse response = a2pClient.wkhtmlHtmlToPdf(pageContent, true, "test.pdf");
//				System.out.println(response.getFile());

//				ChromePdfRenderOptions renderOptions = new ChromePdfRenderOptions();
//				renderOptions.setWaitFor(new WaitFor(500));
//				renderOptions.setFitToPaperMode(FitToPaperModes.AutomaticFit);
//				renderOptions.setPaperOrientation(PaperOrientation.PORTRAIT);
//				renderOptions.setPaperSize(PaperSize.A4);
//				renderOptions.setMarginBottom(5);
//				renderOptions.setMarginLeft(5);
//				renderOptions.setMarginTop(5);
//				renderOptions.setMarginRight(5);
//
//				PdfDocument pdfFromHtmlString = PdfDocument.renderHtmlAsPdf(pageContent,
//						renderOptions);
//				License.setLicenseKey(
//						"IRONSUITE.SALAHSAYEDATWA.GMAIL.COM.6053-57AA5ABD8B-BO55H6JJVUUREUPM-XWW4DJFFTRV5-KVH23V2M6S64-QCSGCO53KC5V-IRSXA5YQBAEQ-2QQLOXQ5HX2E-ZG3RZP-T4REIBR5MKWNUA-DEPLOYMENT.TRIAL-QH2V46.TRIAL.EXPIRES.15.OCT.2024");

//				pdfFromHtmlString.saveAs(Paths.get("markup_with_assets.pdf"));

				inputPdfList.add(new ByteArrayInputStream(result));
			}

			browser.close();
		}
