const puppeteer = require("puppeteer");

async function getConnectionURL(ip) {
  username = "cos4h";
  password = "R@@t123";
  port = "22";
  usernameSSH = "vmonclick";
  passwordSSH = "vmonclick";
  const browser = await puppeteer.launch({
    headless: true,
    args: [
      "--disable-features=IsolateOrigins,site-per-process",
      "--disable-web-security",
      "--disable-notifications",
      "--disable-popup-blocking",
      "--disable-save-password-bubble",
      "--disable-prompt-on-repost",
      "--disable-infobars",
      "--disable-blink-features=AutomationControlled",
    ],
  });

  const page = await browser.newPage();

  await page.setRequestInterception(true);

  page.on("dialog", async (dialog) => {
    await dialog.dismiss();
  });

  page.on("request", (request) => {
    if (request.url().includes("clipboard")) {
      request.respond({
        status: 403,
        body: "Clipboard access denied",
      });
    } else {
      request.continue();
    }
  });

  page.on("uncaughtException", (err) => {
    console.error("Uncaught exception: " + err);
  });

  const context = browser.defaultBrowserContext();
  await context.overridePermissions("http://localhost:6060", [
    "clipboard-read",
    "clipboard-write",
  ]);

  await page.goto("http://localhost:6060");

  await page.type(".fields input", username);
  await page.type(".password-field input", password);

  await Promise.all([
    page.waitForNavigation(),
    page.click(".login"),
    page.goto("http://localhost:6060/#/manage/postgresql/connections/"),
    page.waitForNavigation(),
  ]);
  const selector = ".ng-empty, .ng-touched";
  await page.waitForSelector(selector);
  await page.click(selector);

  await page.waitForSelector(selector, {
    visible: true,
  });

  const nameConn = ip + ":" + port + "_" + new Date().getTime();
  await page.type(selector, nameConn);

  const selectorSelect = "select";
  const optionValue = "string:ssh";
  await page.waitForSelector(selectorSelect);
  await page.select(selectorSelect, optionValue);

  const selectorHost = ".connection-parameters input";
  await page.waitForSelector(selectorHost);
  await page.click(selectorHost);

  await page.waitForSelector(selectorHost);
  await page.type(selectorHost, ip);

  const selectorPort = ".connection-parameters input[type=number]";

  await page.waitForSelector(selectorPort);
  await page.type(selectorPort, port);

  const selectorPassword = ".connection-parameters input[type=password]";

  await page.waitForSelector(selectorPassword);
  await page.type(selectorPassword, passwordSSH);

  const labelText = "Usuario:";
  const selectorLabel = await page.evaluate((text) => {
    const labels = Array.from(document.querySelectorAll("label"));
    const targetLabel = labels.find(
      (label) => label.textContent.trim() === text
    );
    return targetLabel ? targetLabel.htmlFor : null;
  }, labelText);

  if (selectorLabel) {
    await page.waitForSelector(`#${selectorLabel}`);
    await page.type(`#${selectorLabel}`, usernameSSH);
  } else {
    console.log(`No se encontró una etiqueta con el texto "${labelText}"`);
  }

  const buttonSelector = ".action-buttons button";
  await page.waitForSelector(buttonSelector);
  await page.click(buttonSelector);

  await page.waitForNavigation();
  await page.goto("http://localhost:6060/#/");

  await page.waitForSelector(".all-connections .home-connection");
  const connections = await page.evaluate(() => {
    const connections = Array.from(
      document.querySelectorAll(".all-connections .home-connection")
    ).map((connection) => ({
      innerText: connection.innerText,
      href: connection.href,
    }));
    return connections;
  });

  const connection = connections.find(
    (connection) => connection.innerText === " " + nameConn
  );

  await browser.close();
  return connection.href;
}

module.exports = { getConnectionURL };
