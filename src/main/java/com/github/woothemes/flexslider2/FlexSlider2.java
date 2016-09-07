package com.github.woothemes.flexslider2;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({
	"http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js",
	"vaadin://addons/flexslider2/jquery.flexslider.js",
	"vaadin://addons/flexslider2/flexslider2.js",
})
@StyleSheet({
	"vaadin://addons/flexslider2/flexslider.css",
})
public class FlexSlider2 extends AbstractJavaScriptComponent {

}
