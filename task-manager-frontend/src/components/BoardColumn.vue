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
                   @drag="dragIssue"
        />
      </div>
    </DropWrapper>
    <div v-if="isDragging" class="delete-zone">
      <p>Drop here to delete</p>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import IssueCard from './IssueCard.vue';
import IssueCreateButton from './IssueCreateButton.vue';
import DropWrapper from './DropWrapper.vue';
import issueMovement from '../mixins/issueMovement';

export default {
  name: 'BoardColumn',
  components: {
    IssueCard,
    IssueCreateButton,
    DropWrapper,
  },
  mixins: [issueMovement],
  data() {
    return {
      columnTitle: this.title,
    };
  },
  computed: {
    ...mapState(['isDragging']),
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
      this.$emit('titleChange', { columnIndex: this.columnIndex, newTitle: this.columnTitle });
    },
    dragIssue() {
      this.$store.commit('DRAG_ON');
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

  .delete-zone {
    background-color: rgba(196, 195, 195, 0.6);
    margin: 0 8px 8px 8px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
    border: 2px lightgray dashed;
    border-radius: 4px;
  }

  .delete-zone:hover {
    background-color: rgba(196, 195, 195, 0.8);
  }

</style>
