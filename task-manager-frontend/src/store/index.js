import Vue from 'vue';
import Vuex from 'vuex';
import * as API from '../services/api-service';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    columns: '',
  },
  mutations: {
    MOVE_ISSUE_CARD(state, { fromColumnIndex, toColumnIndex, issueIndex }) {
      const issueToMove = state.columns[fromColumnIndex - 1].issues[issueIndex];
      state.columns[fromColumnIndex - 1].issues.splice(issueIndex, 1);
      state.columns[toColumnIndex - 1].issues.push(issueToMove);
    },
    CHANGE_COLUMN_TITLE(state, { columnIndex, newTitle }) {
      state.columns[columnIndex - 1].name = newTitle;
    },
    CREATE_ISSUE(state, { title, description, columnIndex }) {
      state.columns[columnIndex - 1].issues.push({
        id: 20, title, description,
      });
    },
    DELETE_ISSUE(state, { fromColumnIndex, issueIndex }) {
      state.columns[fromColumnIndex - 1].issues.splice(issueIndex, 1);
    },
    LOAD_STATE(state, payload) {
      state.columns = payload;
    },
  },
  actions: {
    changeColumnTitle({ state, commit }, { columnIndex, newTitle }) {
      commit('CHANGE_COLUMN_TITLE', { columnIndex, newTitle });
      API.default.updateColumn(state.columns[columnIndex - 1]);
    },
    async loadState({ commit }) {
      const response = API.default.fetchIssues();
      commit('LOAD_STATE', response.data);
    },
    deleteIssue({ commit }, { fromColumnIndex, issueIndex, issue }) {
      commit('DELETE_ISSUE', { fromColumnIndex, issueIndex });
      API.default.deleteIssue(issue.id);
    },
    createIssue({ commit }, { title, description, columnIndex }) {
      commit('CREATE_ISSUE', { title, description, columnIndex });
      API.default.createIssue({ title, description, columnIndex });
    },
    moveIssue({ commit }, {
      fromColumnIndex, toColumnIndex, issueIndex, issue,
    }) {
      commit('MOVE_ISSUE_CARD', { fromColumnIndex, toColumnIndex, issueIndex });
      API.default.moveIssue({ ...issue, toColumnIndex });
    },
  },
  modules: {
  },
});
