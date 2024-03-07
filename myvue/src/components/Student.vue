<template>
    <div id="Student">


        <div class="student-info">
            <p style="font-size: 20px;">{{ studentId }}</p>
            <p id="studentId">{{ studentName }}</p>
            <el-button @click="$root.logout" type="danger" id="exit-button">退出</el-button>
        </div>


        <div>
            <div class="header">
                <div class="title">选择科目</div>
            </div>
            <div class="subject-list">
                <div class="subject-item">
                    <span style="font-size: 25px;">计算机网络</span>
                    <el-button class="exam-button" type="testStateColor(0)" style="font-size: 16px;"
                        @click="handleClick(0)">{{ testState[0] }}</el-button>
                </div>
                <div class="subject-item">
                    <span style="font-size: 25px;">计算机操作系统</span>
                    <el-button class="exam-button" type="testStateColor(1)" style="font-size: 16px;"
                        @click="handleClick(1)">{{ testState[1] }}</el-button>
                </div>
                <div class="subject-item">
                    <span style="font-size: 25px;">计算机组成原理</span>
                    <el-button class="exam-button" type="testStateColor(2)" style="font-size: 16px;"
                        @click="handleClick(2)">{{ testState[2] }}</el-button>
                </div>
                <div class="subject-item">
                    <span style="font-size: 25px;">数据结构</span>
                    <el-button class="exam-button" type="testStateColor(3)" style="font-size: 16px;"
                        @click="handleClick(3)">{{ testState[3] }}</el-button>
                </div>
            </div>
        </div>
    </div>
</template>



<script>
import { useStore } from 'vuex';
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';


export default {
    name: 'StudentComponent',
    setup() {
        const store = useStore()
        const router = useRouter()
        let studentId = ref('')
        let studentName = ref('')
        let testState = ref(['参加考试', '参加考试', '参加考试', '参加考试'])
        console.log(store)

        onMounted(() => {
            console.log(store)
            axios.get('http://localhost:8081/student/exams', {
                params: {
                    studentId: store.state.userName
                }
            }).then(res => {
                console.log("res", res.data['data'])
                let student = JSON.parse(res.data['data'])
                console.log(student)
                studentId.value = student['studentId']
                studentName.value = student['studentName']

                let exams = student['exams']

                for (let i = 0; i < exams.length; i++) {
                    if (exams[i]['courseId'] != 0) {
                        testState.value[exams[i]['courseId'] - 1] = exams[i]['score']
                    }
                }


            })
        })
        const testStateColor = (index) => {
            if (testState.value[index] == '参加考试') {
                return 'success'
            } else {
                return 'danger'
            }
        }
        const exitQuit = () => {
            store.commit('setUserName', '')
            router.push('/Exam')
        }

        const handleClick = (courseId) => {
            if (testState.value[courseId] === '参加考试') {
                console.log(courseId)
                store.commit('setTestCourse', courseId + 1)
                router.push('/Exam')
            } else {
                console.log(courseId)
            }
        }

        return { studentId, studentName, exitQuit, store, testState, handleClick, testStateColor }
    },
}

</script>


<style scoped>
p {
    /*取消p标签的默认样式*/
    margin: 0;
    padding: 0;
    font-size: 20px;
    font-weight: bold;
}

#studentId {
    margin-top: 5%;
}

.header {
    text-align: center;
    padding: 20px;
    background-color: #FFFFFF;
    margin-left: 80%;
}

.title {
    font-size: 64px;
    font-weight: bold;
    margin-left: 80%;
    width: 300%;

}

.student-info {
    background-color: #F2F6FE;
    width: 10%;
    position: fixed;
    padding: 1%;
    left: 2%;
    top: 10%;
    border-radius: 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

#exit-button {
    top: 100%;
    margin-top: 10px;
    background-color: red;
    color: white;
    /* padding: 10px 20px;
    border: none;
    cursor: pointer;*/
    width: 80%;
}

.subject-list {
    margin-top: 10%;
    width: 100%;
    margin-left: 50%;
    margin-right: auto;
    border-radius: 25px;
    text-align: center;
    font-weight: bold;
    height: 120%;
    display: flex;
    flex-direction: column;
}

.subject-item {
    margin: 20px;
    height: 60px;
    padding: 30px;
    border: 2px;
    border-radius: 15px;
    ;
    background-color: #F8F9FB;
    /* 文字垂直居中*/
    box-sizing: border-box;
    height: 50%;
    padding-top: 3%;
}

.exam-button {
    background-color: #40CD70;
    color: white;
    padding: 10px 5px;
    border: none;
    cursor: pointer;
    float: right;
    width: 15%;
    height: 10%;
}
</style>