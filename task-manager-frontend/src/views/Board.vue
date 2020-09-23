<template>
  <div class="board">
    <BoardColumn v-for="(column, index) in columns"
                 :key="index"
                 :column-index="column.id"
                 :issues="column.issues"
                 :title="column.name"
                 @titleChange="changeTitle"
    />
    <div class="modal" v-if="isModalOpen" @click.self="closeModal">
      <router-view/>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';
import BoardColumn from '../components/BoardColumn.vue';

export default {
  name: 'Board',
  components: { BoardColumn },
  computed: {
    ...mapState(['columns']),
    isModalOpen() {
      return this.$route.name === 'CreateIssue';
    },
  },
  async created() {
    const state = await axios.get('http://localhost:8080/column')
      .then((response) => response.data)
      .catch((error) => {
        console.error(error);
        this.columns = [];
      });
    this.loadState(state);
  },
  methods: {
    loadState(state) {
      this.$store.commit('LOAD_STATE', { loadedState: state });
    },
    changeTitle({ columnIndex, newTitle }) {
      this.$store.commit('CHANGE_COLUMN_TITLE', { columnIndex, newTitle });
      axios.put('http://localhost:8080/column',
        this.columns[columnIndex - 1],
        {
          headers: {
            'Content-Type': 'application/json',
          },
        });
    },
    closeModal() {
      this.$router.push({ name: 'Board' });
    },
  },
};
</script>

<style scoped>
  .board {
    display: flex;
    height: 100%;
    width: 100%;
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
