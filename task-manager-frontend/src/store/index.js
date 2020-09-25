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
    async changeColumnTitle({ state, commit }, { columnIndex, newTitle }) {
      console.log('newTitle ', newTitle);
      commit('CHANGE_COLUMN_TITLE', { columnIndex, newTitle });
      await API.default.updateColumn(state.columns[columnIndex - 1]);
    },
    async loadState({ commit }) {
      const response = await API.default.fetchIssues();
      commit('LOAD_STATE', response.data);
    },
    async deleteIssue({ commit }, { fromColumnIndex, issueIndex, issue }) {
      commit('DELETE_ISSUE', { fromColumnIndex, issueIndex });
      await API.default.deleteIssue(issue.id);
    },
  },
  modules: {
  },
});
