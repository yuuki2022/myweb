<template>
    <el-container>
        <el-aside width=150px>
            <div class="username">{{ username }}</div>
            <!-- <button @click="logout" class="logout">退出</button> -->
            <el-button type="danger" @click="$root.logout" size="large" id="quit">退出</el-button>
            <hr>
            <div class="buttons">
                <el-button type="primary" @click="$root.CreateQuestion" size="large" id="createPaper">出试卷</el-button>
                <br>
                <el-button type="primary" @click="$root.Togradelookup" size="large" id="lookgrade">查询成绩</el-button>
                <br>
                <el-button type="primary" size="large" id="organizeStudent">管理学生</el-button>
                <br>
                <!-- <button class="purple">检查结果</button> -->
            </div>
        </el-aside>

        <el-container>

            <el-header>
                <h1>出试卷</h1>
            </el-header>
            <el-main>

                <el-form label-width="80px">
                    <div>
                        <el-select v-model="course" placeholder="选择科目" class="chooseSubject" style="width: 180px">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </div>
                    <p style="display: block; font-weight: bold; font-size: 30px;">输入题目</p>
                    <el-input id="textarea" v-model="question" style="width: 100%" :rows="3" type="textarea"
                        placeholder="输入题目" />
                    <p style="font-weight: 400; font-weight: bold; font-size: 30px;">输入选项</p>
                    <el-form-item label="选项A">
                        <el-input v-model="answer[0]" placeholder="请输入选项A"></el-input>
                    </el-form-item>
                    <el-form-item label="选项B">
                        <el-input v-model="answer[1]" placeholder="请输入选项B"></el-input>
                    </el-form-item>
                    <el-form-item label="选项C">
                        <el-input v-model="answer[2]" placeholder="请输入选项C"></el-input>
                    </el-form-item>
                    <el-form-item label="选项D">
                        <el-input v-model="answer[3]" placeholder="请输入选项D"></el-input>
                    </el-form-item>
                    <el-form-item label="正确答案">
                        <el-radio-group v-model="rightAnswer">
                            <el-radio :value=0>A</el-radio>
                            <el-radio :value=1>B</el-radio>
                            <el-radio :value=2>C</el-radio>
                            <el-radio :value=3>D</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="难度等级">
                        <el-radio-group v-model="hard">
                            <el-radio :value=0>简单</el-radio>
                            <el-radio :value=1>中等</el-radio>
                            <el-radio :value=2>困难</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-button type="success" class="save" size="large" @click="submitCreateQuestion" round>保存</el-button>

                </el-form>
            </el-main>
        </el-container>
    </el-container>




</template>

<script>
export default {
    name: 'CreateQuestionComponent',
    data() {
        return {
            question: '',
            optionA: '',
            optionB: '',
            optionC: '',
            optionD: '',
            answer: ''
        }
    },
    methods: {


    }
}
</script>

<script setup>


import { ref } from 'vue'
import {useStore} from 'vuex'
import axios from 'axios'
import { ElMessage } from 'element-plus'


const store = useStore()

const options = [
    {
        value: 1,
        label: '计算机网络',
    },
    {
        value: 2,
        label: '计算机操作系统',
    },
    {
        value: 3,
        label: '计算机组成原理',
    },
    {
        value: 4,
        label: '数据结构',
    },

]
let course = ref('')
let rightAnswer = ref('')

let hard = ref('')

let question = ref('')

let answer = ref(['', '', '', ''])
let username = ref(store.state.username)

const submitCreateQuestion = () => {

    axios.post('http://localhost:8081/createQuestion', {
        courseId: course.value,
        content: question.value,
        answer:[answer.value[0], answer.value[1], answer.value[2], answer.value[3]],
        rightAnswer: answer.value[rightAnswer.value],
        hard: hard.value
    }).then(res => {
       console.log(res)
       ElMessage.success("题目已进入题库")
    }).catch(err => {
        console.log(err)
    })
}

</script>

<style scoped>
p {
    text-align: left;
}

#question {
    text-align: left;

}

.save {
    background-color: #43CD72;
}

.el-form-item {
    margin-bottom: 20px;
}

.el-form-item .el-form-item__label {
    border-radius: 50%;
    font-size: 20px;
    font-weight: bold;
    background-color: #D9D9D9;
    width: 50px;
    height: 30px;
    display: inline-block;
    line-height: 100%;
}

.chooseSubject {
    position: relative;
    left: -43%;
    font-size: 20px;
    font-weight: bold;
}

.el-aside {
    height: 100%;
    position: fixed;
    left: 0px;
    top: 0px;
    flex-direction: column;
    background-color: #f0f0f0;
}

.el-main {
    width: 1100px;
    height:auto;
    left: 409px;
    border-radius: 15px;
    background-color: #F8F9FB;
}

textarea {
    width: 80%;
    height: 100px;
    border-radius: 10px;
    border: 1px solid #000;
    display: block;
    text-align: center;
    /*设置字体大小 */
    font-size: 20px;
    margin-bottom: 3%;

}
</style>
