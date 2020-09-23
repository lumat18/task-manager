<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      |
      <router-link to="/board">Board</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
  body {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    background-color: #42b983;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  #nav {
    padding: 30px;
    background-color: #2c3e50;
    box-shadow: 0 4px 4px 0 #182838;
  }

  #nav a {
    font-weight: bold;
    color: white;
  }

  #nav a.router-link-exact-active {
    color: #42b983;
  }
</style>
