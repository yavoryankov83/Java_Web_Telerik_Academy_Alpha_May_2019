let todos = [
  { id: 0, name: 'Buy milk', due: '2019-05-30', isDone: true },
  { id: 1, name: 'Buy beer', due: '2019-06-03', isDone: false },
  { id: 2, name: 'Read "The Hitchhiker\'s Guide to the Galaxy" book', due: '2019-06-14', isDone: false },
];

const statics = {
  id: todos.length,
};

const populateTodos = (todos, container) => {
  const $div = $(container);
  $div.empty();

  todos.forEach((todo) => todoView(todo, container));
};

const todoView = (todo, container) => {
  const $div = $(container);
  $div.append(`
  <div class="todo-single">
    <input data-check-id="${todo.id}" type="checkbox" ${todo.isDone ? 'checked' : ''}/>
    <span>${todo.name} - ${moment(todo.due).format('MMMM Do YYYY')}</span>
    <button data-id="${todo.id}">X</button>
  </div>`);
}

$(document).ready(function () {
  // Initial populate
  populateTodos(todos, '#todos');

  // Attach events here
  // Add event
  $(document).on('click', '#btn-add', (ev) => {

    alert('Todo Added!');
  });

  // Remove event
  $(document).on('click', '', (ev) => {

    alert('Todo Deleted!');
  });

  // Check event
  $(document).on('click', '', (ev) => {

    alert('Todo Toggled!');
  });
})


