<template>
  <DropWrapper class="column" @drop="moveIssue">
    <h1>{{ title }}</h1>
    <div class="issues">
      <IssueCard v-for="(issue, index) in issues"
                 :key="index"
                 :issue="issue"
                 :issue-index="index"
                 :column-index="columnIndex"
      />
    </div>
  </DropWrapper>
</template>

<script>
import IssueCard from './IssueCard.vue';
import DropWrapper from './DropWrapper.vue';
import issueMovement from '../mixins/issueMovement';

export default {
  name: 'BoardColumn',
  components: {
    IssueCard,
    DropWrapper,
  },
  mixins: [issueMovement],
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
};
</script>

<style scoped>
  .column {
    background-color: #2c3e50;
    border-radius: 4px;
    width: 300px;
    height: 600px;
    margin: 16px 0 16px 16px;
    box-shadow: 4px 4px 4px #182838;
  }
  h1 {
    color: lightgray;
  }
  .issues {
    display: flex;
    flex-direction: column;
    overflow-y: auto;
    overflow-x: hidden;
    max-height: 500px;
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
</style>
