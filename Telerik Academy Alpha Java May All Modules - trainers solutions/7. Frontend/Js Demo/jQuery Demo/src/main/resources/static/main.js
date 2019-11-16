"use strict";

const changeElement = () => {
    const $header = $('#header');

    $header.css("background-color", "red");
    $header.css("color", "white");
};

const resetElement = () => {
    const $header = $('#header');

    $header.css("background-color", "white");
    $header.css("color", "black");
}

const addTodo = () => {
    const $input = $("#text-input");
    const $list = $("#todos-list");
    const $todo = $(`<li>${$input.val()}</li>`);

    // Add todo to the list
    $list.append($todo);

    // Clear input
    $input.val(' ');
}

$( document ).ready(() => {
    $('#clear-btn').on('click', (event) => {
        console.log(event);
        const $list = $("#todos-list");
        $list.html('');
    });

    $(document).on('click', 'div', (event) => {
        const $div = $("#divText")[0].id;
        console.log(`Event ${event.type} occurred on ${$div}`);
    });
});