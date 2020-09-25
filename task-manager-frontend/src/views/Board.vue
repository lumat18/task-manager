<template>
  <div class="board">
    <div class="column-wrapper">
      <BoardColumn v-for="(column, index) in columns"
                   :key="index"
                   :column-index="column.id"
                   :issues="column.issues"
                   :title="column.name"
      />
    </div>
    <div class="modal" v-if="isModalOpen" @click.self="closeModal">
      <router-view/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import BoardColumn from '../components/BoardColumn.vue';

export default {
  name: 'Board',
  components: { BoardColumn },
  data() {
    return {
      isDragging: false,
    };
  },
  computed: {
    ...mapState(['columns']),
    isModalOpen() {
      return this.$route.name === 'CreateIssue';
    },
  },
  created() {
    this.$store.dispatch('loadState');
  },
  methods: {
    closeModal() {
      this.$router.push({ name: 'Board' });
    },
  },
};
</script>

<style scoped>
  .board {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100%;
  }
  .column-wrapper{
    display: flex;
  }
  .modal{
    position: fixed;
    display: flex;
    align-items: center;
    justify-content: center;
    top: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(1, 1, 1, 0.5);
  }
</style>
