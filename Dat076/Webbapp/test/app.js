const http = require('http');
const fs = require('fs');

const hostname = '127.0.0.1';
const port = 3000;

const path = require('path');
console.log(path);

var express = require('express');
var app = express();

app.get('/', (req, res) => {
   res.sendFile(fs.readFile('html/index.html'));
});

app.listen(3000);

/*fs.readFile('html/index.html', (err, html) => {
    if(err){
        throw err;
    }
    const server = http.createServer((req, res) => {
        res.statusCode = 200;
        res.setHeader('Content-type', 'text/html');
        res.write(html);
        res.end();
    });

    server.listen(port, hostname, () => {
        console.log('Server started');
    })
});*/
