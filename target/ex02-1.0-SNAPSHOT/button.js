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
            'Content-Type': 'application/json' +
                '',
            }
        })
            .then(ans => ans.json())
            .then(answers => {
                for (const answersKey in answers) {
                    console.log(answers[answersKey])
                }
            })}