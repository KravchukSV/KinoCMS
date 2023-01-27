$(document).ready(function () {
    // bind form submission event
    $("#file-upload-form").on("submit", function (e) {

        // cancel the default behavior
        e.preventDefault();

        // use $.ajax() to upload file
        $.ajax({
            url: "/file-upload",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                $('#feedback').attr("src", res);

            },
            error: function (err) {
                $('#feedback').attr("src", err);
            }
        });
    });

    $("#logotype-form").on("submit", function (e) {

        // cancel the default behavior
        e.preventDefault();

        // use $.ajax() to upload file
        $.ajax({
            url: "/logo-upload",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (res) {
                $('#logotypeImg').attr("src", res);

            },
            error: function (err) {
                $('#logotypeImg').attr("src", err);
            }
        });
    });
});

