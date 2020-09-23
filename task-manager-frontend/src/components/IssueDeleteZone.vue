<template>
  <DropWrapper @drop="deleteIssue">
    <div class="delete-zone">
      <p>Drop here to delete</p>
    </div>
  </DropWrapper>
</template>

<script>
import DropWrapper from './DropWrapper.vue';

export default {
  name: 'IssueDeleteZone',
  components: { DropWrapper },
  methods: {
    deleteIssue({ fromColumnIndex, issueIndex }) {
      this.$store.commit('DELETE_ISSUE', { fromColumnIndex, issueIndex });
      this.$emit('drop-issue');
    },
  },
};
</script>

<style scoped>
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
