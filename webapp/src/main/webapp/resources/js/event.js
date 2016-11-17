$(document).ready(function(){
    $(document).on("click", ".deleteGroup",function() {
        $(this).closest('.group').remove();

        var nbGroups = parseInt($('#nbGroups').val()) - 1;
        $('#nbGroups').val(nbGroups)
    });
});
$(document).on("click", ".deleteTrafficLight",function() {
    $(this).closest('.trafficLight').remove();
});

$(document).on("click", ".addTrafficLight",function() {
    var name = $(this).closest('.group').find('input').first().attr("name");
    $(this).closest('.form-group').before(
        "<div class=\"form-group trafficLight\">" +
            "<label class=\"col-sm-2 control-label\">Traffic Light</label>" +
            "<div class=\"col-sm-10\">" +
                "<div class=\"input-group\">" +
                    "<input type=\"text\" class=\"form-control\" name=t" + name +">" +
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
                    "<input type=\"text\" class=\"form-control\" name="+ $('#nbGroups').val() +">" +
                "</div>" +
            "</div>" +
            "<div class=\"form-group\">" +
                "<div class=\"col-sm-offset-2 col-sm-10\">" +
                    "<a class=\"btn btn-default addTrafficLight\">Add Traffic Light</a>" +
                    "<a class=\"btn btn-default deleteGroup\">Delete Group</a>" +
                "</div>" +
            "</div>" +
    "</div>");

    var nbGroups = parseInt($('#nbGroups').val()) + 1;
    $('#nbGroups').val(nbGroups)
});

$(document).ready(function(){
        if ( $("form").length ) {
            var nbGroups = $('.group').length;
            $('#nbGroups').val(nbGroups);
        }
});
