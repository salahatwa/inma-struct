https://playwright.azureedge.net/builds/chromium/1135/chromium-linux.zip


 public static void main(String[] args) {
        String fileUrl = "https://example.com/path/to/your/file.zip";  // The URL of the ZIP file
        String downloadLocation = "downloaded.zip";  // Where the ZIP file will be saved locally
        String extractToFolder = "extracted";  // Folder where ZIP content will be extracted

        try {
            // Step 1: Download the ZIP file
            downloadZipFile(fileUrl, downloadLocation);

            // Step 2: Extract the ZIP file to the specified folder
            extractZipFile(downloadLocation, extractToFolder);

            System.out.println("Download and extraction completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to download a ZIP file from the URL
    public static void downloadZipFile(String fileUrl, String destination) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded to: " + destination);
        }
    }

    // Method to extract the ZIP file
    public static void extractZipFile(String zipFilePath, String extractTo) throws IOException {
        File destDir = new File(extractTo);
        if (!destDir.exists()) {
            destDir.mkdir();  // Create the destination folder if it doesn't exist
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = extractTo + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // Extract files
                    extractFile(zipIn, filePath);
                } else {
                    // Create directories
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        System.out.println("Files extracted to: " + extractTo);
    }

    // Method to extract a single file from the ZIP
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
