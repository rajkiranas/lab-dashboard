com_github_woothemes_flexslider2_FlexSlider2 = function() {
    var galleryElem = $('<div class="flexslider">' +
            '<ul class="slides">' +
            '<li><img src="./VAADIN/images/kitchen_adventurer_cheesecake_brownie.jpg" /></li>' +
            '<li><img src="./VAADIN/images/kitchen_adventurer_lemon.jpg" /></li>' +
            '<li><img src="./VAADIN/images/kitchen_adventurer_donut.jpg" /></li>' +
            '<li><img src="./VAADIN/images/kitchen_adventurer_caramel.jpg" /></li>' +
            '</ul>' +
          '</div>');

galleryElem.appendTo(this.getElement());
	
//Can also be used with $(document).ready()
//setTimeout(function() {


var me = this;
me.onStateChange = function() {
   $('.flexslider').flexslider({
    animation: "slide"
  });
};

//, 0);
};