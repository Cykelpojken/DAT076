/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.addEventListener('load', function() {
  console.log('All assets are loaded')
  document.getElementById('createGameView').style.display = 'none';
  //document.getElementById('findGameView').style.display = 'none';
  document.getElementById('inGame').style.display = 'none';
})

function swapDisplay(id)
{
    var element = document.getElementById(id);
    if(element.style.display === 'none')
    {
        element.style.display = 'block';
    }
    else
    {
        
    element.style.display = 'none';
    }
}
