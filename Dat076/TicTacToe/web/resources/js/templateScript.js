/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var color =  "#243942";

window.addEventListener('load', function() {
  console.log(window.location.href);
  var loc = window.location.href;
  var site = loc.split("/");
  var page = site[4].split(".");
  if(page[0].includes("?"))
    {
        var s = page[0].split("?");
        console.log(s);
        if(s[0] === "forumthread")
        {
            var links = document.getElementsByTagName('a');
            links[2].style.background = color;
        }
    }
    else
    {
        switch(page[0]) {
            case "index":
                var links = document.getElementsByTagName('a');
                links[0].style.background = color;
                break;
            case "game":
                var links = document.getElementsByTagName('a');
                links[1].style.background = color;
                break;
            case "forum":
                var links = document.getElementsByTagName('a');
                links[2].style.background = color;
                break;
            case "thread":
                var links = document.getElementsByTagName('a');
                links[2].style.background = color;
                break;
            case "dbtest":
                var links = document.getElementsByTagName('a');
                links[3].style.background = color;
                break;
            case "modifyAccount":
                var links = document.getElementsByTagName('a');
                links[3].style.background = color;
                break;
            case "Account":
                var links = document.getElementsByTagName('a');
                links[3].style.background = color;
                break;
            case "dbtest":
                var links = document.getElementsByTagName('a');
                links[3].style.background = color;
                break;
            default:
                var links = document.getElementsByTagName('a');
                links[0].style.background = color;
            }
    }
})