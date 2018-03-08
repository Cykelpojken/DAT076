/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var edits = [];

function editableText(element)
{
    if(element.value === "Edit")
        startEdit(element);
    
    else if(element.value === "Cancel")
        cancelEdit(element);
}

function startEdit(element) {
    element.value = "Cancel";
    edits.push({id: element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0], text: element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0].value});
    element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0].removeAttribute('readonly');
    element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[1].style = "visibility:visible";
}

function cancelEdit(element) {
    element.value = "Edit";
    for(var i = 0; i < edits.length; i++)
    {
        if(element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0] === edits[i].id)
        {
            element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0].value = edits[i].text;
            edits.splice(i, 1);
        }
    }
    element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[0].setAttribute('readonly', '');
    element.parentNode.parentNode.parentNode.children[0].children[1].children[0].children[1].style = "visibility:hidden";
}

function getText(element)
{
    return element.parentNode.children[0].value;
}