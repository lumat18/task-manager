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
    CREATE_ISSUE(state, {
      id, title, description, columnIndex,
    }) {
      state.columns[columnIndex - 1].issues.push({
        id, title, description,
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
      const { data } = await API.default.fetchIssues();
      commit('LOAD_STATE', data);
    },
    deleteIssue({ commit }, { fromColumnIndex, issueIndex, issue }) {
      commit('DELETE_ISSUE', { fromColumnIndex, issueIndex });
      API.default.deleteIssue(issue.id);
    },
    async createIssue({ commit }, { title, description, columnIndex }) {
      const { data: { id } } = await API.default.createIssue({ title, description, columnIndex });
      commit('CREATE_ISSUE', {
        id, title, description, columnIndex,
      });
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
