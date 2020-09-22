export default {
  methods: {
    moveIssue({ fromColumnIndex, issueIndex }) {
      this.$store.commit('MOVE_ISSUE_CARD', {
        fromColumnIndex,
        toColumnIndex: this.columnIndex,
        issueIndex,
      });
    },
  },
};
