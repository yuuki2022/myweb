import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    userName: "",
    courseIds: {
      计算机网络: 1,
      计算机操作系统: 2,
      计算机组成原理: 3,
      数据结构: 4,
    },
    course: [
      "参加考试",
      "计算机网络",
      "计算机操作系统",
      "计算机组成原理",
      "数据结构",
    ],
    testCourse: "",
    path: "http://192.168.18.144:8081/",
  },
  mutations: {
    setUserName(state, userName) {
      state.userName = userName;
    },
    setTestCourse(state, testCourse) {
      state.testCourse = testCourse;
    },
  },
  actions: {},
  modules: {},
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],
});
