$(document).ready(function(){
    boxOff();
    $("#home-default-box").fadeIn("slow");
    $(".btn-body").click(function(){
        boxOff();
        $("#monster-box-body").fadeIn("slow");
    });
    $(".btn-head").click(function(){
        boxOff();
        $("#monster-box-head").fadeIn("slow");
    });
    $(".btn-worm").click(function(){
        boxOff();
        $("#monster-box-worm").fadeIn("slow");
    });
    $(".btn-fly").click(function(){
        boxOff();
        $("#monster-box-fly").fadeIn("slow");
    });
    $(".btn-crown").click(function(){
        boxOff();
        $("#item-box-crown").fadeIn("slow");
    });
    $(".btn-darkwings").click(function(){
        boxOff();
        $("#item-box-darkwings").fadeIn("slow");
    });
    $(".btn-greenmus").click(function(){
        boxOff();
        $("#item-box-greenmus").fadeIn("slow");
    });
    $(".btn-mantle").click(function(){
        boxOff();
        $("#item-box-mantle").fadeIn("slow");
    });
    $(".btn-meat").click(function(){
        boxOff();
        $("#item-box-meat").fadeIn("slow");
    });
    $(".btn-piggybank").click(function(){
        boxOff();
        $("#item-box-piggybank").fadeIn("slow");
    });
    $(".btn-pillcollecter").click(function(){
        boxOff();
        $("#item-box-pillcollecter").fadeIn("slow");
    });
    $(".btn-pyro").click(function(){
        boxOff();
        $("#item-box-pyro").fadeIn("slow");
    });
    $(".btn-question").click(function(){
        boxOff();
        $("#item-box-question").fadeIn("slow");
    });
    $(".btn-redmus").click(function(){
        boxOff();
        $("#item-box-redmus").fadeIn("slow");
    });
    $(".btn-wings").click(function(){
        boxOff();
        $("#item-box-wings").fadeIn("slow");
    });


})

function boxOff() {
    var x = document.getElementsByClassName("main-slide-box");
    for (var i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
}