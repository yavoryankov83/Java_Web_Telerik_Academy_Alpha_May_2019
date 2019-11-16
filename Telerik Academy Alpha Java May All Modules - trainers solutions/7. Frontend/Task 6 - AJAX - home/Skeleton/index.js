let todos = [
  { id: 0, name: 'Buy milk', due: '2019-05-30', isDone: true },
  { id: 1, name: 'Buy beer', due: '2019-06-03', isDone: false },
  { id: 2, name: 'Read "The Hitchhiker\'s Guide to the Galaxy" book', due: '2019-06-14', isDone: false },
];

const statics = {
  id: todos.length,
};

const todoView = (todo, container) => {
  const $div = $(container);
  $div.append(`
  <div class="todo-single">
    <input data-check-id="${todo.id}" type="checkbox" ${todo.isDone ? 'checked' : ''}/>
    <span>${todo.name} - ${moment(todo.due).format('MMMM Do YYYY')}</span>
    <button data-id="${todo.id}">X</button>
  </div>
  `);
}

const populateTodos = (todos, container) => {
  const $div = $(container);
  $div.empty();
  todos.forEach((todo) => todoView(todo, container));
};

$(document).ready(function () {
  // Initial populate
  populateTodos(todos, '#todos');

  // Attach events here
  $(document).on('click', '[data-id]', (ev) => {
    const $target = $(ev.target);
    const targetID = $target.attr('data-id');
    todos = todos.filter((todo) => todo.id != targetID);

    populateTodos(todos, '#todos');
    alert('Todo Deleted!');
  });

  $(document).on('click', '#btn-add', (ev) => {
    const $text = $('#todo-text')
    const $date = $('#todo-date')

    if ($($date).val()) {
      statics.id++;
      todos.push({ id: statics.id, name: $text.val(), due: $date.val(), isDone: false });

      populateTodos(todos, '#todos');
      $text.val('');
      $date.val('');
      alert('Todo Added!');
    } else {
      alert('Invalid date');
    }
  });

  $(document).on('click', '[data-check-id]', (ev) => {
    const $target = $(ev.target);

    todos = todos.map((todo) => {
      if (todo.id == $target.attr('data-check-id')) {
        todo.isDone = !$target.attr('checked');
      }

      return todo;
    });

    populateTodos(todos, '#todos');
    alert('Todo Toggled!');
  });
});


