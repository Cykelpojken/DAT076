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
    case "dbtest":
        var links = document.getElementsByTagName('a');
        links[3].style.background = color;
        break;
    case "modifyaccount":
        var links = document.getElementsByTagName('a');
        links[3].style.background = color;
        break;
    default:
        var links = document.getElementsByTagName('a');
        links[0].style.backrgound = color;
    }
})