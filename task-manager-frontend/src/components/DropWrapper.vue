<template>
  <div @drop.stop="onDrop"
       @dragover.prevent
       @dragenter.prevent>
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'DropWrapper',
  methods: {
    onDrop(event) {
      const transferData = JSON.parse(event.dataTransfer.getData('payload'));
      this.$emit('drop', transferData);
      this.$store.commit('DRAG_OFF');
    },
  },
};
</script>

<style scoped>
div{
  margin-bottom: 8px;
}
</style>
