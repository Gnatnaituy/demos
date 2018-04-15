var fs = require('fs');

fs.readFile('Study.js', 'utf-8', function (err, data) {
    if(err) {
        console.log(err)
    }else {
        console.log(data)
    }
});

fs.readFile('a.png', function (err, data) {
   if(err) {console.log(err)}
   else {
       console.log(data);
       console.log(data.length + 'bytes');
   }
});