<template>
  <div class="board">
    <BoardColumn v-for="(column, index) in columns"
                 :key="index"
                 :column-index="column.id"
                 :issues="column.issues"
                 :title="column.name"
                 @titleChange="changeTitle"
    />
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
      console.log('column: ', this.columns[columnIndex - 1]);
      axios.put('http://localhost:8080/column',
        this.columns[columnIndex - 1],
        {
          headers: {
            'Content-Type': 'application/json',
          },
        });
    },
  },
};
</script>

<style scoped>
  .board {
    display: flex;
    height: 100%;
    width: 100%;
    background-color: #42b983;
  }
</style>
