<template>
    <div draggable="true"
         @dragstart="onDrag"
         @dragend.prevent="onDrop"
         @dragover.prevent
         @dragenter.prevent
         :class="{dragged : isDragged}">
      <slot></slot>
    </div>
</template>

<script>
export default {
  name: 'DragWrapper',
  data() {
    return {
      isDragged: false,
    };
  },
  props: {
    transferData: {
      type: Object,
      required: true,
    },
  },
  methods: {
    onDrag(event) {
      this.isDragged = true;
      event.dataTransfer.setData('payload', JSON.stringify(this.transferData));
      this.$emit('start-drag');
    },
    onDrop() {
      this.$emit('stop-drag');
    },
  },
};
</script>

<style scoped>
  .dragged {
    box-shadow: 0 0;
  }
</style>
