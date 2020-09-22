const toDoIssues = {
  id: 0,
  name: 'To Do',
  issues: [
    { id: 0, title: 'Drink coffee', description: 'Double espresso to start' },
    { id: 1, title: 'Walk the dog', description: 'Go to the woods' },
    { id: 2, title: 'Code', description: 'All day all night' },
  ],
};

const inProgressIssues = {
  id: 1,
  name: 'In Progress',
  issues: [
    { id: 0, title: 'Go to the gym', description: 'Train hard' },
    { id: 1, title: 'Eat lunch', description: 'I\'m super hungry' },
    { id: 2, title: 'Grab a beer', description: 'Drunk as fuck' },
    { id: 3, title: 'Play guitar', description: 'Practice makes perfect' },
  ],
};
const doneIssues = {
  id: 2,
  name: 'Done',
  issues: [
    { id: 0, title: 'Go to the office', description: 'Air condition here' },
  ],
};

export default (function columns() {
  return [
    toDoIssues, inProgressIssues, doneIssues,
  ];
}());
