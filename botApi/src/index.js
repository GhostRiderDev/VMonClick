const express = require("express");
const { getConnectionURL } = require("./connection");
const app = express();
const port = 3000;

app.use(express.json());

app.post("/connection", async (req, res) => {
  try {
    const { body } = req;
    const { ip } = body;
    const urlConn = await getConnectionURL(ip);
    res.json({ urlConn });
  } catch (error) {
    res.status(500).json({ error: "Some error..." });
  }
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
