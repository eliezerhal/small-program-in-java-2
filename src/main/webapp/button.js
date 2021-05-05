/**
 * This is a js file that handles the script showing / hiding the answers
 */
document.addEventListener('DOMContentLoaded', function() {
    let index = this.dataset.number;
    let ans = document.getElementById("Show answers" + index);
    ans.addEventListener("click", button);


}, false);

function button() {
    let index = this.dataset.number;
    console.log("index = " + index);
    fetch("JsonServlet?" + "questionNumber=" + index, {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(ans => ans.json())
        .then(answers => {
            //document.getElementById("ans" + index).innerHTML = "answers[answersKey]";
            let str = "";
            for (const answersKey in answers) {
                console.log(answers[answersKey]);
                str += "<li>Author: " + answers[answersKey].Name + "Answer: " + answers[answersKey].Answer + "</li>";
                str += "<button style=\"margin: 3px\" type=\"button\" class=\"btn btn-secondary\" name=\"Hide answers\">Hide answers</button>";
            }
            let hide = document.getElementsByName("Hide answers");
            for(let i=0; i<hide.length; i++) {
                hide[i].addEventListener("click", hiden);
            }
            document.getElementById("ans" + index).innerHTML = str;
            document.getElementById("ans" + index).style.display="block" ;
            document.getElementById("Show answers" + index).style.display="none" ;
        })
}


function hiden() {
    document.getElementById("ans" + index).style.display="none" ;
};