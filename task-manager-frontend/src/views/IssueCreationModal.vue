<template>
  <div class="wrapper">
    <h1>Create new issue</h1>
    <input class="issue-title" type="text" placeholder="Issue title" v-model="title"/>
    <textarea class="issue-desc" type="text" placeholder="Description" v-model="description"/>
    <button @click="createIssue">Create issue</button>
  </div>
</template>

<script>
export default {
  name: 'IssueCreationModal',
  data() {
    return {
      title: '',
      description: '',
    };
  },
  props: {
    columnIndex: {
      type: Number,
      required: true,
    },
  },
  computed: {
    isTitleValid() {
      return this.title !== '';
    },
  },
  methods: {
    createIssue() {
      if (this.isTitleValid) {
        this.$store.dispatch('createIssue', {
          title: this.title,
          description: this.description,
          columnIndex: this.columnIndex,
        });
        this.clearFields();
        this.$router.push({ name: 'Board' });
      }
    },
    clearFields() {
      this.title = '';
      this.description = '';
    },
  },
};
</script>

<style scoped>
  .wrapper {
    width: 400px;
    height: 300px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border: 1px solid #2c3e50;
    border-radius: 8px;
    box-shadow: 4px 4px 4px #182538;
    background-color: #42b983;
  }

  input {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 16px;
    border: 1px solid #2c3e50;
    margin: 8px;
    padding: 4px;
    width: 350px
  }

  .issue-desc {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 16px;
    border: 1px solid #2c3e50;
    margin: 8px;
    padding: 4px;
    width: 350px;
    height: 32px;
  }

  button {
    border: 1px solid #2c3e50;
    font-weight: bold;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    font-size: 16px;
    margin: 8px;
    padding: 4px;
    width: 200px;
  }

  button:hover {
    background: #182538;
    color: white;
  }
</style>
