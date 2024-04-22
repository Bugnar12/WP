if(!window.scriptLoaded) {
    window.scriptLoaded = true;

    $(document).ready(function () {
        //when we enter
        $(".tabContent").not(":first").hide();

        //logic of the tabs
        $(".tabLink").click(function () {
            var tabName = $(this).attr("data-tab");
            console.log(tabName);

            //used to hide the content and show the corresponding tab
            $(".tabContent").hide();
            $("#" + tabName).show();
        });

        $(".navbar").css({
            "display": "flex",
            "justify-content": "space-around",
            "background-color": "#f2f2f2",
            "padding": "10px 0"
        });
        $(".tabLink").css({
            "padding": "10px 20px",
            "border": "none",
            "background-color": "transparent",
            "cursor": "pointer"
        });

        //load shared HTML into each tab and apply shared CSS
        $(".tabContent").each(function() {
            var tabContent = $(this); // Store the current tabContent element
            tabContent.load("TabsContent.html", function() {
                applySharedStyles();
                // Add images to the divs within the current tabContent
                if (tabContent.attr('id') === 'tab1' || tabContent.attr('id') === 'tab3') {
                    tabContent.find("#div1").html('This text will be displayed on the odd pages');
                    tabContent.find("#div2").html('<img id="img1" src="image1.jpeg" alt="Image 1">');
                    tabContent.find("#div3").html('<img id="img2" src="image2.webp" alt="Image 2">');
                } else if (tabContent.attr('id') === 'tab2' || tabContent.attr('id') === 'tab4') {
                    tabContent.find("#div1").html('This text will be displayed on the even pages');
                    tabContent.find("#div2").html('<img id="img3" src="image3.jpg" alt="Image 3">');
                    tabContent.find("#div3").html('<img id="img4" src="image4.jpeg" alt="Image 4">');
                }
                else if(tabContent.attr('id') === 'tab5')
                {
                    tabContent.find("#div1").html('This text will be displayed on the fifth page');
                    tabContent.find("#div2").html('<img id="img1" src="image1.jpeg" alt="Image 1">');
                    tabContent.find("#div3").html('<img id="img2" src="image2.webp" alt="Image 2">');
                }
            });
        });

        // Hover effect for tab buttons
        $(".tabLink").hover(function () {
            $(this).css("background-color", "#ddd");
        }, function () {
            $(this).css("background-color", "transparent");
        });
    })
}
function applySharedStyles() {
    $("#img1, #img2, #img3, #img4").css({
        "width": "200px",
        "height": "200px"
    });

    $("#div1, #div2, #div3").css({
        "width": "200px",
        "height": "200px",
        "background-color": "#f2f2f2",
        "padding": "10px",
        "margin": "10px",
        "border-radius": "10px"
    });

    // Add this to style the parent div
    $(".card-container").css({
        "display": "flex",
        "justify-content": "space-between"
    });
}