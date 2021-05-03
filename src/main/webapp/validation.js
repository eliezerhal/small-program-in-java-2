    function validatorForm(){
    let error = true;
    let name = document.myform.myName.value.trim();
    let text = document.myform.text.value.trim();
    document.getElementById("errorMessage1").style.display="none" ;
    document.getElementById("errorMessage2").style.display="none" ;
    if (name == null || name === ""){
    document.getElementById("errorMessage1").style.display="block" ;
    document.getElementById("errorMessage1").innerHTML = "you need to enter your name please";
    error = false;
}
    if(text == null || text === ""){
    document.getElementById("errorMessage2").style.display="block" ;
    document.getElementById("errorMessage2").innerHTML = "you need to enter your response please";
    error = false;
}
    return error;
}
