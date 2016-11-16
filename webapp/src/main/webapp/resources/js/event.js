$(document).on("click", ".deleteGroup",function() {
    $(this).closest('.group').remove();
});

$(document).on("click", ".deleteTrafficLight",function() {
    $(this).closest('.trafficLight').remove();
});

$(document).on("click", ".addTrafficLight",function() {
    $(this).closest('.form-group').before(
        "<div class=\"form-group trafficLight\">" +
            "<label class=\"col-sm-2 control-label\">Traffic Light</label>" +
            "<div class=\"col-sm-10\">" +
                "<div class=\"input-group\">" +
                    "<input type=\"text\" class=\"form-control\">" +
                    "<span class=\"input-group-btn\">" +
                        "<a class=\"btn btn-default deleteTrafficLight\" type=\"button\">-</a>" +
                    "</span>" +
                "</div>" +
            "</div>" +
        "</div>")
});

$(document).on("click", ".addGroup",function() {
    $(this).closest('.form-group').prev().before(
        "<div class=\"group\">" +
            "<div class=\"form-group\">" +
                "<label class=\"col-sm-2 control-label\">GreenTime</label>" +
                "<div class=\"col-sm-10\">" +
                    "<input type=\"text\" class=\"form-control\">" +
                "</div>" +
            "</div>" +
            "<div class=\"form-group\">" +
                "<div class=\"col-sm-offset-2 col-sm-10\">" +
                    "<a class=\"btn btn-default addTrafficLight\">Add Traffic Light</a>" +
                    "<a class=\"btn btn-default deleteGroup\">Delete Group</a>" +
                "</div>" +
            "</div>" +
    "</div>")
});
