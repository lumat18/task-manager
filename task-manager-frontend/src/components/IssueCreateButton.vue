<template>
  <div class="creator" @click="openModal">+</div>
</template>

<script>
export default {
  name: 'IssueCreateButton',
  props: {
    columnIndex: {
      type: Number,
      required: true,
    },
  },
  methods: {
    openModal() {
      this.$router.push({
        name: 'create-issue',
        params: { columnIndex: this.columnIndex },
      });
    },
  },
};
</script>

<style scoped>
  .creator {
    color: lightgray;
    font-size: 32px;
    padding: 8px 16px;
    display: block;
  }

  .creator:hover {
    font-weight: bolder;
    cursor: pointer;
  }
</style>
