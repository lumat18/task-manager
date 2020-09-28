<template>
  <div class="column">
    <div class="header">
      <input class="column-title" type="text" v-model="columnTitle" @blur="changeTitle"/>
      <IssueCreateButton :column-index="columnIndex"/>
    </div>
    <DropWrapper @drop="moveIssue">
      <div class="issues">
        <IssueCard v-for="(issue, index) in issues"
                   :key="index"
                   :issue="issue"
                   :issue-index="index"
                   :column-index="columnIndex"
        />
      </div>
    </DropWrapper>
  </div>
</template>

<script>
import IssueCard from './IssueCard.vue';
import IssueCreateButton from './IssueCreateButton.vue';
import DropWrapper from './DropWrapper.vue';

export default {
  name: 'BoardColumn',
  components: {
    IssueCard,
    IssueCreateButton,
    DropWrapper,
  },
  data() {
    return {
      columnTitle: this.title,
    };
  },
  props: {
    title: {
      type: String,
      required: true,
    },
    issues: {
      type: Array,
      required: true,
    },
    columnIndex: {
      type: Number,
      required: true,
    },
  },
  methods: {
    changeTitle() {
      this.$store.dispatch('changeColumnTitle', { columnIndex: this.columnIndex, newTitle: this.columnTitle });
    },
    moveIssue({ fromColumnIndex, issueIndex, issue }) {
      this.$store.dispatch('moveIssue', {
        fromColumnIndex,
        toColumnIndex: this.columnIndex,
        issueIndex,
        issue,
      });
    },
  },
};
</script>

<style scoped>
  .column {
    background-color: #2c3e50;
    border-radius: 4px;
    width: 300px;
    margin: 16px 0 16px 16px;
    box-shadow: 4px 4px 4px #182838;
  }

  .column-title {
    color: lightgray;
    background: none;
    border: none;
    font-weight: bold;
    font-size: 28px;
    margin: 16px;
    width: 200px;
  }

  .issues {
    display: flex;
    flex-direction: column;
    overflow-y: auto;
    overflow-x: hidden;
    height: 600px;
  }

  *::-webkit-scrollbar {
    width: 8px;
  }

  *::-webkit-scrollbar-track {
    background: none;
  }

  *::-webkit-scrollbar-thumb {
    background-color: lightgray;
    border-radius: 4px;
  }

  .header {
    display: flex;
    justify-content: space-evenly;
  }

</style>
