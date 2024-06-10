import * as puppeteer from "puppeteer";

async function loginAutomatically() {
  const browser = await puppeteer.launch({
    headless: false,
    args: [
      "--disable-features=IsolateOrigins,site-per-process",
      "--disable-web-security",
      "--disable-notifications",
      "--disable-popup-blocking",
      "--disable-save-password-bubble",
      "--disable-prompt-on-repost",
      "--disable-infobars", // Disable infobars for password save prompts
      "--disable-blink-features=AutomationControlled", // Hide the automation from being detected
    ],
  });

  const page = await browser.newPage();

  // Enable request interception
  await page.setRequestInterception(true);

  // Intercept and dismiss dialogs automatically
  page.on("dialog", async (dialog) => {
    await dialog.dismiss();
  });

  // Intercept and handle permission requests (like clipboard access)
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

  // Bypass permissions (e.g., clipboard access)
  const context = browser.defaultBrowserContext();
  await context.overridePermissions("http://localhost:6060", [
    "clipboard-read",
    "clipboard-write",
  ]);

  await page.goto("http://localhost:6060");

  await page.type(".fields input", "cos4h"); // first input
  await page.type(".password-field input", "R@@t123");

  // Replace '#login-button' with the actual selector for the login button.
  await Promise.all([
    page.waitForNavigation(), // The promise resolves after navigation has finished
    page.click(".login"),
    page.goto("http://localhost:6060/#/manage/postgresql/connections/"),
    page.waitForNavigation(),
  ]);
  const selector = ".ng-empty, .ng-touched";
  await page.waitForSelector(selector);
  await page.click(selector);

  // Re-fetch the element before typing into it
  await page.waitForSelector(selector);
  await page.type(selector, "Connection Name cos4h");

  const selectorSelect = "select";
  const optionValue = "string:ssh";
  await page.waitForSelector(selectorSelect);
  await page.select(selectorSelect, optionValue);

  const selectorHost = ".connection-parameters input";
  await page.waitForSelector(selectorHost);
  await page.click(selectorHost);

  // Re-fetch the element before typing into it
  await page.waitForSelector(selectorHost);
  await page.type(selectorHost, "192.168.1.8");

  const selectorPort = ".connection-parameters input[type=number]";

  await page.waitForSelector(selectorPort);
  await page.type(selectorPort, "22");

  const selectorPassword = ".connection-parameters input[type=password]";

  await page.waitForSelector(selectorPassword);
  await page.type(selectorPassword, "ubuntu");

  const labelText = "Usuario:";
  const selectorLabel = await page.evaluate((text) => {
    const labels = Array.from(document.querySelectorAll("label"));
    const targetLabel = labels.find(
      (label) => label.textContent.trim() === text
    );
    return targetLabel ? targetLabel.htmlFor : null;
  }, labelText);

  if (selectorLabel) {
    // Aquí puedes interactuar con el elemento de entrada asociado con la etiqueta
    await page.waitForSelector(`#${selectorLabel}`);
    await page.type(`#${selectorLabel}`, "ubuntu"); // Reemplaza "your_text" con el texto que quieres escribir
  } else {
    console.log(`No se encontró una etiqueta con el texto "${labelText}"`);
  }

  const buttonSelector = ".action-buttons button";
  await page.waitForSelector(buttonSelector);
  await page.click(buttonSelector);
  // await browser.close();
}

loginAutomatically();
