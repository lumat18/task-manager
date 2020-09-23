<template>
  <DragWrapper class="wrapper"
               :transfer-data="{
                  fromColumnIndex: columnIndex,
                  issueIndex,
               }"
               @start-drag="pickUpIssue"
               @stop-drag="finishDrag">
    <h3>{{ issue.title }}</h3>
    <h4>{{ issue.description }}</h4>
  </DragWrapper>

</template>

<script>
import DragWrapper from './DragWrapper.vue';

export default {
  name: 'IssueCard',
  components: { DragWrapper },
  props: {
    issue: {
      type: Object,
      required: true,
    },
    issueIndex: {
      type: Number,
      required: true,
    },
    columnIndex: {
      type: Number,
      required: true,
    },
  },
  methods: {
    pickUpIssue() {
      this.$emit('drag-issue', { issueIndex: this.issueIndex, columnIndex: this.columnIndex });
    },
    finishDrag() {
      this.$emit('drop-issue');
    },
  },
};
</script>

<style scoped>
  .wrapper {
    display: flex;
    justify-content: center;
    align-items: start;
    flex-direction: column;
    margin: 0px 8px 8px 8px;
    background-color: lightgray;
    border-radius: 8px;
    max-height: 100px;
    min-height: 100px;
    color: #2c3e50;
    padding: 8px;
    box-shadow: 4px 4px 4px #182538;
    transform: translate(0, 0);
  }

  h3 {
    margin: 0;
  }

  h4 {
    font-weight: normal;
    text-align: left;
  }

  p {
    margin: 0;
  }
</style>
