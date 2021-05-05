/**
 * This is a js file that handles the script showing / hiding the answers
 */
document.addEventListener('DOMContentLoaded', function() {
        let list = document.getElementsByName("Show answers");
        for(let i=0; i<list.length; i++)
            list[i].addEventListener("click", button);
    }, false);

    function button() {
        let index = this.dataset.id;
        fetch("JsonServlet?" + "questionNumber=" + index, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(ans => ans.json())
            .then(answers => {
                let str = "";
                str += "<button style=\"margin: 3px\" type=\"button\" class=\"btn btn-secondary\" name=\"Hide answers\">Hide answers</button>";
                for (const answersKey in answers)
                    str += "<li>Author: " + answers[answersKey].Name + ". Answer: " + answers[answersKey].Answer + ".</li>";
                document.getElementById("ans" + index).innerHTML = str;
                document.getElementById("ans" + index).style.display="block";
                document.getElementById("Show answers" + index).style.display="none";
                let hide = document.getElementsByName("ans");
                for(let i=0; i<hide.length; i++)
                    hide[i].addEventListener("click", hiden);
            })
    }
    function hiden() {
        let index = this.dataset.id;
        document.getElementById("ans" + index).style.display="none";
        document.getElementById("Show answers" + index).style.display="block";
    };