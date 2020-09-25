<template>
  <DragWrapper class="wrapper"
               :transfer-data="{
                  fromColumnIndex: columnIndex,
                  issueIndex,
               }">
    <h3>{{ issue.title }}</h3>
    <h4>{{ issue.description }}</h4>
    <button class="delete-button" @click="issueDelete">
      <div class="delete-x">+</div>
    </button>
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
    issueDelete() {
      this.$store.dispatch('deleteIssue', { fromColumnIndex: this.columnIndex, issueIndex: this.issueIndex, issue: this.issue });
    },
  },
};
</script>

<style scoped>
  .wrapper {
    position: relative;
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
  .wrapper:hover{
    transform: translate(-1px, -1px);
  }
  .delete-button {
    position: absolute;
    top: 8px;
    right: 8px;
    padding: 4px;
    margin: 0;
    max-height: 30px;
    display: flex;
    align-items: center;
    background: none;
    border: none;
    visibility: hidden;
  }
  .delete-x {
    font-size: 32px;
    font-weight: lighter;
    transform: rotate(45deg);
  }

  .delete-x:hover{
    color: #182538;
  }

  .delete-button:focus{
    outline: none;
    box-shadow: none;
  }

  .wrapper:hover .delete-button{
    color: gray;
    visibility: visible;
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
