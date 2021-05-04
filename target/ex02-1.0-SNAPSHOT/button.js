/**
 * This is a js file that handles the script showing / hiding the answers
 */
document.addEventListener('DOMContentLoaded', function() {
    let list = document.getElementsByName("Show answers");
    for(let i=0; i<list.length; i++) {
        list[i].addEventListener("click", button);
    };
}, false);

function button() {
    let index = this.dataset.number;
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
                str += <button style="margin: 3px" type="button" class="btn btn-secondary" name="Hide answers">Hide answers</button>;
            }
            document.getElementById("ans" + index).innerHTML = str;
            document.getElementById("ans" + index).style.display="block" ;
        })}