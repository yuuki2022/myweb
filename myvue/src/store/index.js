import {createStore} from 'vuex'

export default createStore({
    state:{
        userName: '',
        courseIds: {
            "计算机网络":1,
            "计算机操作系统":2,
            "计算机组成原理":3,
            "数据结构":4,
        },
        course:['参加考试','计算机网络','计算机操作系统','计算机组成原理','数据结构'],
        testCourse:'',
        path: 'http://127.0.0.1:8081/'
    },
    mutations:{
        setUserName(state, userName){
            state.userName = userName
        },
        setTestCourse(state, testCourse){
            state.testCourse = testCourse
        }
    },
    actions:{

    },
    modules:{

    }
})


