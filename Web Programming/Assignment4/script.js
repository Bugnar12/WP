const button1Element = document.getElementById("button1");
const backgroundArray = ['background1.jpeg', 'background2.jpg', 'background3.webp', 'background4.webp',
                                'background5.jpg']

let index = 0;
button1Element.addEventListener("click", function () {
    const bodyElement = document.body;
    bodyElement.style.backgroundImage = `url(${backgroundArray[index]})`;

    //rotate the background images constantly
    index++;
    if(index === 5)
       index = 0;

})

const button2Element = document.getElementById("button2");
button2Element.addEventListener("click", () => {
    var anchors = document.querySelectorAll(".anchor-style");

    anchors.forEach((anchor) => {
        //for each anchor the css should be .anchor-style-changed
        anchor.classList.toggle("anchor-style-changed");
    })

})