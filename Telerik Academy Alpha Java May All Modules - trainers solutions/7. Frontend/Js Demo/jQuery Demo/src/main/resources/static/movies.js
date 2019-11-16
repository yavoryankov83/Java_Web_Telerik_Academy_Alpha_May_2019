"use strict";

const $input = $("#search-term");
const $resultDiv = $("#result");

$(document).on("click", "#search-btn", () => {
    $resultDiv.empty();
    const searchTitle = $input.val();

    // fetch(`https://jsonmock.hackerrank.com/api/movies/search/?Title=${searchTitle}`)
    //     .then((res) => res.json())
    //     .then((res) => {
    //
    //         res.data.forEach((movie) => {
    //             $resultDiv.append(`<a href="https://www.imdb.com/title/${movie.imdbID}">${movie.Title}</a><br>`);
    //         });
    //
    //         $input.val('');
    //     });

    $.get(
        `https://jsonmock.hackerrank.com/api/movies/search/?Title=${searchTitle}`,
        function (res) {
            if (res.data.length == 0) {
                $resultDiv.append("<h4 style='color:red;'>Sorry, no movies found!</h4>");
            } else {
                res.data.forEach((movie) => {
                    $resultDiv.append(`<a target="_blank" href="https://www.imdb.com/title/${movie.imdbID}">${movie.Title}</a><br>`);
                });
            }
            $input.val('');
        }).fail(function () {
        // Something went wrong
        alert("Something went wrong, we are sorry :(");
    });

})
