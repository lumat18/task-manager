<template>
    <div class="creator" @click="openModal">
      +
      <div class="tooltip">Create issue</div>
    </div>
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
    position: relative;
    color: lightgray;
    font-size: 32px;
    padding: 8px 16px;
    display: block;
  }

  .creator:hover {
    font-weight: bolder;
    cursor: pointer;
  }

  .creator:hover .tooltip {
    visibility: visible;
  }

  .tooltip{
    display: block;
    position: absolute;
    background-color: #182538;
    color: white;
    padding: 8px;
    visibility: hidden;
    border-radius: 4px;
    font-size: 16px;
    z-index: 1;
    transform: translate(-70px, -60px);
  }
</style>
