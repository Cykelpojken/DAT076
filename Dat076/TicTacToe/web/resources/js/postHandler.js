/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var edits = [];
var id;
var text;
var submit;

function editableText(element)
{
    if(element.value === "Edit")
        startEdit(element);
    
    else if(element.value === "Cancel")
        cancelEdit(element);
}

function startEdit(element) {
    element.value = "Cancel";
    var e = element.parentNode.parentNode.parentNode.children[0].children[1].children[0];
    for(var i = 0; i < e.children.length; i++){
        if(!(e.children[i].value === "Submit")){
             id = e.children[i];
            text = e.children[i].value;
        }
         else if(e.children[i].value === "Submit"){
            submit = e.children[i];
        }

    }
    edits.push({id: id, text: text});
    id.removeAttribute('readonly');
    submit.style = "visibility:visible";
  
}

function cancelEdit(element) {
    element.value = "Edit";
    
    var e = element.parentNode.parentNode.parentNode.children[0].children[1].children[0];
   
    for(var i = 0; i < e.children.length; i++){
        if(!(e.children[i].value === "Submit")){
             id = e.children[i];
            text = e.children[i].value;
        }
        else if(e.children[i].value === "Submit"){
            submit = e.children[i];
        }
        
    }
    
    for(var i = 0; i < edits.length; i++)
    {
        if(id === edits[i].id)
        {
            id.value = edits[i].text;
            edits.splice(i, 1);
        }
    }
    id.setAttribute('readonly', '');
    submit.style = "visibility:hidden";
}

function getText(element)
{
    return element.parentNode.children[0].value;
}