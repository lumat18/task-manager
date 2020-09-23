import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    columns: '',
    isDragging: false,
  },
  mutations: {
    MOVE_ISSUE_CARD(state, { fromColumnIndex, toColumnIndex, issueIndex }) {
      const issueToMove = state.columns[fromColumnIndex - 1].issues[issueIndex];
      state.columns[fromColumnIndex - 1].issues.splice(issueIndex, 1);
      state.columns[toColumnIndex - 1].issues.push(issueToMove);
    },
    CHANGE_COLUMN_TITLE(state, payload) {
      state.columns[payload.columnIndex - 1].name = payload.newTitle;
    },
    CREATE_ISSUE(state, { title, description, columnIndex }) {
      state.columns[columnIndex - 1].issues.push({
        id: 20, title, description,
      });
    },
    LOAD_STATE(state, { loadedState }) {
      state.columns = loadedState;
    },
    DRAG_ON(state) {
      state.isDragging = true;
    },
    DRAG_OFF(state) {
      state.isDragging = false;
    },
  },
  actions: {
  },
  modules: {
  },
});
