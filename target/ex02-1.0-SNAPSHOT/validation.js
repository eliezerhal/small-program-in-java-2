document.addEventListener('DOMContentLoaded', function(){
    console.log("I'm here")
    document.getElementById("add").addEventListener("click", nameValidator);
}, false);

    function nameValidator() {
        let x = document.forms["myForm"]["fname"].value;
        console.log("I'm there");
        if (x == "") {
            alert("Name must be filled out");
            return false;
        }
    }



