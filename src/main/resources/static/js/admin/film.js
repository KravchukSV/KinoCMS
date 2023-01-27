$(document).ready(function () {
    // bind form submission event
    $("#main-picture-form").on("submit", function (e) {

        // cancel the default behavior
        e.preventDefault();

        // use $.ajax() to upload file
        $.ajax({
            url: "/upload-main-picture",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                $('#mainPictureImg').attr("src", res);

            },
            error: function (err) {
                $('#mainPictureImg').attr("src", err);
            }
        });
    });

    $("#picture-gallery-form").on("submit", function (e) {

        // cancel the default behavior
        e.preventDefault();

        // use $.ajax() to upload file
        $.ajax({
            url: "/upload-picture-gallery",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                $('#listPicture').val(JSON.stringify(res));

            },
            error: function (err) {
                $('#listPicture').attr("src", err);
            }
        });
    });
});

