https://playwright.azureedge.net/builds/chromium/1135/chromium-linux.zip


System.setProperty(
"PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD"
,
"1"
);

System.setProperty("PLAYWRIGHT_BROWSERS_PATH", "0");


Map<String,String> map=new HashMap<>();
		map.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
		map.put("PLAYWRIGHT_BROWSERS_PATH", "0");
		CreateOptions opt=new CreateOptions();
		opt.setEnv(map);


RUN chmod -R 777 /var/www/html/tmp

chmod -R +x ~/.cache/ms-playwright
