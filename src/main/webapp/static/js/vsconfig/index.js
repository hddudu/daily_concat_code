$(function () {
	$("#dmb").click(function () {
        $("#main_content").load("content/dmb_content");
    });
	$("#csb").click(function () {
        $("#main_content").load("content/csb_content");
    });
    $("#conf").click(function () {
        $("#main_content").load("content/conf_content");
    });
    $("#conf_rule").click(function () {
        $("#main_content").load("content/conf_rule_content");
    });
    $("#indexMenu").click(function () {
        $("#main_content").load("content/index_menu_content");
    });
    $("#businessMenu").click(function () {
        $("#main_content").load("content/business_menu_content");
    });
    $("#gnMenu").click(function () {
        $("#main_content").load("content/gn_content");
    });
    $("#qjssMenu").click(function () {
        $("#main_content").load("content/qjss_menu_content");
    });
});