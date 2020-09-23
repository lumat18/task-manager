import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Board from '../views/Board.vue';
import IssueCreationModal from '../views/IssueCreationModal.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/board',
    name: 'Board',
    component: Board,
    children: [
      {
        path: '/issue/create',
        name: 'CreateIssue',
        component: IssueCreationModal,
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
