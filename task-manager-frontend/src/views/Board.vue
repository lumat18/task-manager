<template>
  <div class="board">
    <BoardColumn v-for="(column, index) in columns"
                 :key="index"
                 :column-index="column.id"
                 :issues="column.issues"
                 :title="column.name"
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
    this.$store.state.columns = await axios.get('http://localhost:8080/column')
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error(error);
        this.$store.state.columns = [];
      });
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
