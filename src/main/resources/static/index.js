function init() {
    loadSectors();
}

function loadSectors() {
    // noinspection JSUnusedGlobalSymbols
    $.ajax({
        url: "/getSectors",
        success: function(sectors) {
            console.log("Sectors loaded");
            $.each(sectors, function(_, sector) {
                $("#sectorSelectbox").append($("<option>", {value: sector.id, text: formatSectorName(sector)}));
            });
        },
        error: function() {
            console.log("Failed to load sectors");
        }
    });
}

function formatSectorName(sector) {
    var formattedSectorName = sector.name;
    for (var i = 0; i < sector.id.toString().length - 1; i++) {
        formattedSectorName = "\u00A0\u00A0\u00A0\u00A0" + formattedSectorName;
    }
    return formattedSectorName;
}

function saveUserData() {
    clearErrors();
    clearMessages();
    $.ajax({
        url: "/saveUserData",
        method: "PUT",
        data: {
            id: $("#userId").val(),
            name: $("#nameInput").val(),
            sectorIds: $("#sectorSelectbox").val().join(","),
            agreeToTerms: $("#agreeToTermsCheckbox").is(":checked")
        },
        success: function(saveUserResponse) {
            if (saveUserResponse.errors) {
                displayErrors(saveUserResponse.errors);
                console.log("Errors when saving user data")
            } else {
                console.log("User data saved");
                $("#userId").val(saveUserResponse.userData.id);
                $("#nameInput").val(saveUserResponse.userData.name);
                $("#sectorSelectbox").val(saveUserResponse.userData.sectorIds);
                $("#agreeToTermsCheckbox").val(saveUserResponse.userData.agreeToTerms);
                $("#messages").append($("<p>", {text: "Success!"}));
            }
        }
    });
}

function displayErrors(errors) {
    $.each(errors, function(_, error) {
        $("#errors").append($("<p>", {text: error}));
    });
}

function clearErrors() {
    $("#errors").empty();
}

function clearMessages() {
    $("#messages").empty();
}

window.onload = init;